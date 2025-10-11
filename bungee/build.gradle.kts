dependencies {
    compileOnly(libs.bungeecord.api)
}

tasks.processResources {
    val props = mapOf(
        "name" to rootProject.name,
        "version" to project.version,
    )
    filesMatching(listOf("bungee.yml")) {
        expand(props)
    }
}
