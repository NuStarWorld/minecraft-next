plugins {
    alias(libs.plugins.retrofuturagradle)
}

val minecraftVersion = "1.12.2"

minecraft {
    mcVersion.set(minecraftVersion)

    // Enable assertions in the mod's package when running the client or server
    extraRunJvmArguments.add("-ea:${project.group}")

    // Exclude some Maven dependency groups from being automatically included in the reobfuscated runs
    groupsToExcludeFromAutoReobfMapping.addAll(
            "com.diffplug",
            "com.diffplug.durian",
            "net.industrial-craft",
            "team.idealstate.sugar",
    )
}

repositories {
    maven {
        name = "GTNH Maven"
        url = uri("https://nexus.gtnewhorizons.com/repository/public/")
    }
}

tasks.processResources {
    includeEmptyDirs = false
    val props = mapOf(
            "mod_id" to rootProject.name,
            "mod_name" to rootProject.name,
            "mod_version" to version,
            "minecraft_version" to minecraftVersion,
    )
    filesMatching(listOf("assets/**/*.lang", "**/mcmod.info", "**/pack.mcmeta")) {
        expand(props)
    }
    val assetsDir = "assets/${rootProject.name}"
    eachFile {
        if (path.startsWith("assets/")) {
            print("$path >> ")
            path = assetsDir + path.substring(6)
            println(path)
        }
    }
}

tasks.jar {
    finalizedBy(tasks.reobfJar)
}

tasks.reobfJar {
    dependsOn(tasks.jar)
    inputJar.set(tasks.jar.get().archiveFile)
}
