import org.apache.tools.ant.taskdefs.condition.Os

group = "com.github.epabst.kickstart"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm")
}

repositories {
    jcenter()
    mavenCentral()
    maven {
        url = uri("http://repository.jetbrains.com/all")
    }
}

val kotlinVer: String by extra

dependencies {
    compile(kotlin("stdlib", version = kotlinVer))
    runtime(kotlin("runtime", version = kotlinVer))
    testCompile(kotlin("reflect", version = kotlinVer))
    testCompile(kotlin("runtime", version = kotlinVer))
    testCompile(kotlin("test", version = kotlinVer))
    testCompile("org.jetbrains.spek:spek:1.0.25")
}

tasks {
    val cleanupCommonCommon by creating {
        if (file("${rootDir}/common/src/main/kotlin/common/common").exists()) {
            doLast {
                if (Os.isFamily(Os.FAMILY_WINDOWS)) {
                    exec {
                        commandLine("cmd", "/C", "rmdir", "${rootDir}\\common\\src\\main\\kotlin\\common\\common")
                    }
                } else {
                    ant.withGroovyBuilder {
                        "symlink"("action" to "delete", "link" to "${rootDir}/common/src/main/kotlin/common/common")
                    }
                }
            }
        }
    }
    "clean" {
        dependsOn(cleanupCommonCommon)
    }
}
