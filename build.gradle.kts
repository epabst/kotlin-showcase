import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

plugins {
    kotlin("multiplatform") version "1.4-M2"
}
group = "com.github.epabst"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
    maven { url = uri("https://dl.bintray.com/kotlin/ktor") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlinx") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
}
val kotlinVersion = "1.4-M2"
val ktorVersion = "1.3.2-$kotlinVersion"
val logbackVersion = "1.2.3"
val reactVersion = "16.13.0"
val reactWrapperVersion = "$reactVersion-pre.93-kotlin-$kotlinVersion"
val reactRouterDomVersion = "5.1.2-pre.105-kotlin-$kotlinVersion-eap-83"
val artifactName = rootProject.name

kotlin {
    js {
        browser {
            binaries.executable()
        }
        compilations.all {
            kotlinOptions {
                sourceMap = true
                moduleKind = "commonjs"
                metaInfo = true
            }
        }
    }
    jvm {
        compilations.named("main") {
            tasks.getByName<Copy>(processResourcesTaskName) {
                dependsOn("jsBrowserProductionWebpack")
                tasks.named<KotlinWebpack>("jsBrowserProductionWebpack") {
                    from(entry.name, destinationDirectory)
                }
            }
        }
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.0-M2")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        named("jvmMain") {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
                implementation("io.ktor:ktor-html-builder:$ktorVersion")
                implementation("ch.qos.logback:logback-classic:$logbackVersion")
            }
        }
        named("jvmTest") {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        named("jsMain") {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.0-M2")
                implementation("org.jetbrains:kotlin-react-dom:$reactWrapperVersion")
                implementation(npm("pouchdb", "7.2.1"))
                implementation(npm("@types/pouchdb", "6.4.0"))
                implementation(npm("react", reactVersion)) // used by kotlin-react-dom
                implementation(npm("react-dom", reactVersion)) // used by kotlin-react-dom
                implementation(npm("react-is", reactVersion)) // used by kotlin-react-dom
                implementation("org.jetbrains:kotlin-styled:1.0.0-pre.107-kotlin-$kotlinVersion")
                implementation(npm("styled-components","5.0.0"))
                implementation(npm("inline-style-prefixer","5.1.0"))
                implementation(npm("core-js", "3.1.4")) // used by kotlin-react-dom
                implementation(npm("text-encoding", "0.7.0")) // used by ktor-client-js
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-js:$ktorVersion")
                implementation(npm("react-bootstrap", "1.0.0-beta.16"))
                implementation(npm("bootstrap-css-only", "4.3.1"))
                implementation(npm("pickadate", "3.6.4"))
                implementation(npm("firebase", "7.6.1"))
                implementation(npm("numeral", "2.0.6"))
                implementation("org.jetbrains:kotlin-react-router-dom:$reactRouterDomVersion")
                implementation(npm("react-router-dom", "5.1.2"))
                implementation(npm("abort-controller", "3.0.0"))
            }
        }
        named("jsTest") {
            dependencies {
                implementation(kotlin("test-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.3.0-M2")
            }
        }
    }
}

tasks.register<JavaExec>("run") {
    dependsOn("jvmJar")
    group = "application"
    main = "sample.SampleJvmKt"
    val t = tasks.named<Jar>("jvmJar")

    classpath(configurations.named("jvmRuntimeClasspath"), t.get() )
}

val copyDependenciesToPublic by tasks.registering(Copy::class) {
    dependsOn("jsBrowserWebpack")
    val regex = Regex("""~/(?:src|href)="../../(build/js/node_modules)/([^"]+)"""")
    file("src/jsMain/resources/index.html").readLines().forEach { line ->
        val matchResult = regex.find(line)
        if (matchResult != null) {
            from(matchResult.groupValues[1])
            include(matchResult.groupValues[2])
            doLast {
                assert(file("build/public/lib/${matchResult.groupValues[2]}").exists())
            }
        }
    }
    from("build/distributions")
    include("${artifactName}.js")
    include("${artifactName}-${version}.js")
    rename { it.replace("${artifactName}-${version}.js", "${artifactName}.js") }
    into("build/public/lib")
    doLast {
        assert(file("build/public/lib/${artifactName}.js").exists())
    }
}

val copyWebToPublicExceptHtml by tasks.registering(Copy::class) {
    from("src/jsMain/resources")
    include("**/*")
    exclude("index.html")
    into("build/public")
}

val copyHtmlToPublic by tasks.registering(Copy::class) {
    from("src/jsMain/resources")
    include("index.html")
    into("build/public")
    outputs.upToDateWhen { false }
    filter {
        it.replace("../../build/js/node_modules/", "lib/")
                .replace("distributions/${artifactName}-${version}.js", "distributions/${artifactName}.js")
                .replace("../../build/distributions/${artifactName}.js", "lib/${artifactName}.js?" + Math.random())
    }
    doLast {
        file("build/public/index.html").readLines().forEach { line ->
            assert(!line.contains("../"))
        }
    }
}

val copyToPublic by tasks.registering {
    dependsOn(copyHtmlToPublic, copyWebToPublicExceptHtml, copyDependenciesToPublic)
    doLast {
        assert(file("build/public/css/index.css").exists())
        assert(file("build/public/index.html").exists())
    }
}

tasks["assemble"].dependsOn(copyToPublic)

NodeJsRootPlugin.apply(project).apply {
    versions.dukat.version = "0.0.21"
}