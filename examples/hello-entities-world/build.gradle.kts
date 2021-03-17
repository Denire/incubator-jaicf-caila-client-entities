plugins {
    kotlin("jvm")
}

group = "com.justai.jaicf"
version = "0.14.0"

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.justai.jaicf:core:0.13.0")
    implementation("com.justai.jaicf:caila:0.13.0")
}
