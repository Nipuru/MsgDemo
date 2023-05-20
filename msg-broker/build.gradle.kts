plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

dependencies {
    compileOnly("net.afyer.afybroker:afybroker-server:${project.ext.get("brokerVersion")}")
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
    filesMatching("broker.yml") {
        expand(props)
    }
}