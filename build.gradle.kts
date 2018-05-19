buildscript {
    var kotlinVer: String by extra
    kotlinVer = "1.2.40"
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVer")
    }
}

allprojects {
    apply { plugin("idea") }
    group = "com.github.epabst.kotlin-showcase"
    version = "1.0-SNAPSHOT"
    var kotlinVer: String by extra
    kotlinVer = "1.2.40"
    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
        maven {
            url = uri("http://nexus.yested.net/nexus/content/repositories/releases/")
        }
    }
}
