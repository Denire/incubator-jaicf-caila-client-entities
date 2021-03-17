plugins {
    kotlin("jvm") version "1.4.21"
}

group = "com.justai.jaicf"
version = "0.14.0"

repositories {
    mavenCentral()
    jcenter()
}

val jaicf = "0.13.0"

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.justai.jaicf:caila:$jaicf")
    implementation("com.justai.jaicf:core:$jaicf")
}
