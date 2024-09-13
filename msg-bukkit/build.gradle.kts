plugins {
    id("com.github.johnrengelman.shadow")
}

dependencies {
    compileOnly("ink.ptms.core:v12004:12004:mapped")
    compileOnly("org.jetbrains:annotations:20.1.0")
    compileOnly("commons-lang:commons-lang:2.6")
    compileOnly("net.afyer.afybroker:afybroker-client:${project.ext.get("brokerVersion")}")
    implementation(project(":msg-common"))
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.processResources {
    val props = mapOf(
            "version" to project.version
    )
    inputs.properties(props)
    filesMatching("plugin.yml") {
        expand(props)
    }
}