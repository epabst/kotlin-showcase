import org.apache.tools.ant.taskdefs.condition.Os
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile
import com.moowork.gradle.node.NodeExtension
import com.moowork.gradle.node.npm.NpmTask
import com.moowork.gradle.node.task.NodeTask

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        classpath("com.moowork.gradle:gradle-node-plugin:1.2.0")
    }
}

val kotlinVer: String by extra

apply {
    plugin("kotlin2js")
    plugin("com.moowork.node")
}

dependencies {
    "compile"("org.jetbrains.kotlin:kotlin-stdlib-js:$kotlinVer")
    "compile"("net.yested:Yested:1.0-SNAPSHOT")
    "testCompile"("org.jetbrains.kotlin:kotlin-test-js:$kotlinVer")
}

tasks {
    val cleanupLinkCommonSource = task("cleanupLinkCommonSource") {
        if (file("$projectDir/src/main/kotlin/common").exists()) {
            doLast {
                if (Os.isFamily(Os.FAMILY_WINDOWS)) {
                    exec {
                        commandLine("cmd", "/C", "rmdir", "${rootDir}\\webclient\\src\\main\\kotlin\\common")
                    }
                } else {
                    ant.withGroovyBuilder {
                        "symlink"("action" to "delete", "link" to "$projectDir/src/main/kotlin/common")
                    }
                }
            }
        }
    }

    "clean" {
        dependsOn(cleanupLinkCommonSource)
        doLast {
            file("out").deleteRecursively()
            file("$projectDir/node_modules").listFiles().forEach {
                if (it.isFile() && it.name.endsWith(".js")) it.delete()
            }
        }
    }

    // linkCommonSource is here to solve the problem that I need the common module to be compiled to the JVM for unit tests
    // and for the backend, and compiled to Javascript for the webclient and mobile modules.
    // I wanted the build to just work,
    // IntelliJ to just work for test-driven development for both JVM unit tests,
    // as well as opening the webclient app's index.html or test runner.html in a browser.
    //
    // Here are some ideas I tried:
    // 1. Apply the 'kotlin' and the 'kotlin2js' plugins both in the common module.
    //    Result: I wasn't able to get this to work yet and it was awkward and problematic.
    //      I struggled with how to have different gradle configurations that without causing problems for IDE.
    //      IntelliJ would complain about not finding methods or that libraries are not compatible with KotlinJS.
    // 2. Add common/src/main/kotlin as a source directory for both the common module and the webclient module.
    //      IntelliJ would not allow the tests or the webclient to compile because this setup is not allowed.
    //      I could manually choose to remove it from one of the modules and be able to work,
    //      but I'd prefer no manual steps, and to easily switch between JVM unit tests and Javascript development.
    // 3. Automate copying common/src/main/kotlin for use by webclient's kotlin2js.
    //      Result: This worked really well.  The build and IntelliJ both work well.
    //      However, I do have to be careful to change the original files and not the copies.
    // 4. Symlink the common directory to make IntelliJ allow two modules to share the same source.
    //      Result:  This worked well.
    val linkCommonSource by creating {
        outputs.upToDateWhen { _ -> file("$projectDir/src/main/kotlin/common").exists() }
        doLast {
            if (Os.isFamily(Os.FAMILY_WINDOWS)) {
                exec {
                    commandLine("cmd", "/C", "mklink /D ${rootDir}\\webclient\\src\\main\\kotlin\\common ${rootDir}\\common\\src\\main\\kotlin\\common")
                    setIgnoreExitValue(true)
                }
            } else {
                ant.withGroovyBuilder {
                    "symlink"("resource" to "${rootDir}/common/src/main/kotlin/common",
                            "link" to "$projectDir/src/main/kotlin/common")
                }
            }
        }
    }

    val extractJsLibs by creating(Copy::class) {
        configurations["compile"].forEach {
            from(zipTree(it.absolutePath).matching { include("*.js") })
        }
        into("out/production/classes/lib")
    }

    val extractTestJsLibs by creating(Copy::class) {
        configurations["testCompile"].forEach {
            from(zipTree(it.absolutePath).matching { include("*.js") })
        }
        into("out/test/classes/lib")
    }

    val compileKotlin2Js by getting(Kotlin2JsCompile::class) {
        kotlinOptions.moduleKind = "umd"
        kotlinOptions.outputFile = "$projectDir/out/production/classes/webclient.js"
        kotlinOptions.sourceMap = true
        dependsOn(linkCommonSource, extractJsLibs)
    }
    val compileTestKotlin2Js by getting(Kotlin2JsCompile::class) {
        kotlinOptions.moduleKind = "umd"
        kotlinOptions.outputFile = "$projectDir/out/test/classes/webclient_test.js"
        kotlinOptions.sourceMap = true
        dependsOn(extractTestJsLibs)
    }

    val copyToPublicExceptHtml by creating(Copy::class) {
        from("$projectDir/src/main/web")
        from("$projectDir/out/production/classes")
        include("**/*")
        exclude("webclient")
        exclude("index.html")
        into("$projectDir/build/public")
        rename { fileName -> fileName.replace("webclient.js", "js/index.js") }
        dependsOn("compileKotlin2Js")
        // kotlin.js and Yested.js must exist.
        doFirst {
            assert(file("$projectDir/out/production/classes/lib/kotlin.js").exists())
            assert(file("$projectDir/out/production/classes/lib/Yested.js").exists())
        }
    }

    val copyHtmlToPublic by creating(Copy::class) {
        from("$projectDir/src/main/web")
        include("index.html")
        into("$projectDir/build/public")
        filter { it.replace("../../../out/production/classes/", "").replace("webclient.js", "js/index.js?" + Math.random()) }
        dependsOn(extractJsLibs)
    }

    val copyToPublic by creating {
        dependsOn(copyHtmlToPublic, copyToPublicExceptHtml)
        doLast {
            assert(file("$projectDir/build/public/lib/kotlin.js").exists())
            assert(file("$projectDir/build/public/lib/Yested.js").exists())
            assert(file("$projectDir/build/public/js/index.js").exists())
            assert(file("$projectDir/build/public/css/index.css").exists())
            assert(file("$projectDir/build/public/index.html").exists())
        }
    }
    "assemble" {
        dependsOn(copyToPublic)
    }

    /**** Unit tests. ****/
    val populateNodeModules by creating(Copy::class) {
        dependsOn(compileKotlin2Js)
        from("$projectDir/out/production/classes") { include("*.js") }
        configurations["testCompile"].forEach {
            from(zipTree(it.absolutePath).matching { include("*.js") })
        }
        into("$projectDir/node_modules")
    }
    val installJest = task<NpmTask>("installJest") {
        setNpmCommand("install", "jest", "jquery", "bootstrap", "popper.js", "moment", "pickadate", "firebase", "jest-localstorage-mock", "numeral")
    }
    val runJest = task<NodeTask>("runJest") {
        dependsOn(compileTestKotlin2Js, populateNodeModules, installJest)
        setScript(file("$projectDir/node_modules/jest/bin/jest.js"))
        addArgs(compileTestKotlin2Js.outputFile)
    }
    "test" {
        dependsOn(runJest)
    }
    /**** End Unit tests. ****/
}

configure<NodeExtension> {
    download = true
}
