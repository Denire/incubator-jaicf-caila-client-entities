plugins {
    kotlin("jvm") version "1.4.10"
}

group = "com.denire.jaicf"
version = "0.13.1"

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }
}

dependencies {
    implementation(kotlin("stdlib"))
}
