apply {
    plugin("base")
}

tasks {
    val build = getByName("build")
    val clean = getByName("clean")
    clean.doLast {
        file("platforms/android/assets/www").deleteRecursively()
        file("platforms/android/build").deleteRecursively()
        file("www/css").deleteRecursively()
        file("www/js").deleteRecursively()
        file("www/webclient").deleteRecursively()
        file("www/index.html").delete()
        file("www/config.xml").delete()
        file("www/webclient.js").delete()
        file("www/webclient.js.map").delete()
        file("www/webclient.meta.js").delete()
        file("www/lib").deleteRecursively()
    }

    val copyWebClientExceptHtml by creating(Copy::class) {
        from("${projectDir}/../build/public")
        include("**/*")
        exclude("index.html")
        into("www")
        dependsOn(clean)
    }

    // Copy webclient files
    // Automatically copies files from the webclient module to this module.
    // This allows sharing code between the two modules and enables using KotlinJS

    val copyWebClientHtml by creating(Copy::class) {
        from("${projectDir}/../build/public")
        include("index.html")
        into("www")
        filter { line -> line.replace(Regex("<!--CORDOVA (.*?)-->"), "\$1") }
    }

    val copyIcon by creating(Copy::class) {
        from("resources")
        include("logo.png")
        into("www/img")
    }

    val copyConfigForGenIcon by creating(Copy::class) {
        from(".")
        include("config.xml")
        into("www")
        filter { line -> line.replace("www/", "") }
    }

    val genIcon by creating(Exec::class) {
        workingDir = file(".")
        commandLine("cordova-gen-icon")
        doFirst {
            assert(file("www/config.xml").exists()) { "File www/config.xml didn't get added" }
        }
        dependsOn(copyIcon)
        dependsOn(copyConfigForGenIcon)
        doLast {
            file("www/config.xml").delete()
        }
    }

    val prepareMobile by creating {
        dependsOn(copyWebClientExceptHtml)
        dependsOn(copyWebClientHtml)
        doLast {
            assert(file("www/lib/kotlin.js").exists())
            assert(file("www/lib/Yested.js").exists())
            assert(file("www/js/index.js").exists())
            assert(file("www/css/index.css").exists())
            assert(file("www/index.html").exists())
        }
    }

    val makeZip by creating(Zip::class) {
        from("./")
        exclude("build/")
        exclude("publish-folder/")
        exclude("platforms/android/build/")
        exclude("platforms/android/gradle/")
        exclude("platforms/android/platform_www/")
        exclude("platforms/android/src/")
        exclude("plugins/")
        exclude("node_modules/")
        exclude("hooks/before_prepare/")
        archiveFileName.set("PhoneGap.zip")
        destinationDirectory.set(buildDir)
    }
    makeZip.dependsOn(prepareMobile)
    build.dependsOn(makeZip)
    makeZip.doFirst {
        assert(file("config.xml").exists())
    }
}
