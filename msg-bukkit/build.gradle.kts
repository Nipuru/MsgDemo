plugins {
    id("com.github.johnrengelman.shadow")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")
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