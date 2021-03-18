object Version {
    // Kotlin
    const val kotlin = "1.4.10"
    const val stdLib = "1.4.10"

    const val jaicf = "0.13.0"

    const val ktor = "1.4.0"
    const val serializationRuntime = "1.0.0"
}

infix fun String.version(versionProvider: Version.() -> String) = "$this:${versionProvider(Version)}"