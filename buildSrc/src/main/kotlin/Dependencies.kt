import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.core() = "implementation"(project(":core"))

fun DependencyHandlerScope.ktor(module: String): String = "io.ktor:$module" version { ktor }

fun DependencyHandlerScope.jaicf(module: String): String = "com.justai.jaicf:$module" version { jaicf }

fun DependencyHandlerScope.kotlinx(module: String): String = "org.jetbrains.kotlinx:$module"
