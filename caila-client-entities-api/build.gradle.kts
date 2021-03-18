plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.4.10"
    `maven-publish`
}

group = "com.denire.jaicf"
version = "0.13.0"

repositories {
    mavenCentral()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/denire/incubator-jaicf-caila-client-entities")
            credentials {
                username = project.findProperty("gpr.user") as? String? ?: System.getenv("GITHUB_USERNAME")
                password = project.findProperty("gpr.key") as? String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(jaicf("core"))
    implementation(jaicf("caila"))

    implementation(ktor("ktor-client-cio"))
    implementation(ktor("ktor-client-logging-jvm"))
    implementation(ktor("ktor-client-json-jvm"))
    implementation(kotlinx("kotlinx-serialization-json") version { serializationRuntime })
}
