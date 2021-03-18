plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.4.10"
}

group = "com.justai.jaicf"
version = "0.13.0"

repositories {
    mavenCentral()
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
