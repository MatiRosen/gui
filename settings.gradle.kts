rootProject.name = "gui"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}

// item modules
arrayOf("api", "skull-api").forEach {
    includePrefixed("item:$it")
}

// menu modules
arrayOf("api", "plugin").forEach {
    includePrefixed("menu:$it")
}

fun includePrefixed(name: String) {
    val kebabName = name.replace(':', '-')
    val path = name.replace(':', '/')
    val baseName = "${rootProject.name}-$kebabName"

    include(baseName)
    project(":$baseName").projectDir = file(path)
}