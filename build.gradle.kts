plugins {
    kotlin("jvm") version "1.4.10"
}

group = "com.justai.jaicf"
version = "0.13.0"

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }
}

dependencies {
    implementation(kotlin("stdlib"))
}
