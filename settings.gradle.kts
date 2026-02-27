rootProject.name = "minecraft-next"

pluginManagement {
    repositories {
        mavenLocal()
        maven {
            name = "Sonatype-Snapshots"
            url = uri("https://central.sonatype.com/repository/maven-snapshots/")
        }
        maven {
            name = "GTNH Maven"
            url = uri("https://nexus.gtnewhorizons.com/repository/public/")
        }
        maven {
            url = uri("https://maven.nustar.top/repository/nustar-public/")
        }
        gradlePluginPortal()
    }
}

plugins {
    id("team.idealstate.glass") version "0.1.0-SNAPSHOT"
}

multiModule()
