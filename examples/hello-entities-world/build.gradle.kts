plugins {
    kotlin("jvm")
}

group = "com.denire.jaicf"
version = "0.14.0"

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(jaicf("core"))
    implementation(jaicf("caila"))
    implementation(jaicf("jaicp"))
    implementation(project(":caila-client-entities-api"))

    implementation("ch.qos.logback:logback-classic:1.2.3")
}
