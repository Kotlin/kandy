import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.BaseKotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    with(libs.plugins) {
        alias(kotlin.jvm)
    }
}

dependencies {
    implementation(project(":kandy-lets-plot"))

    implementation("junit:junit:4.13.2")
    implementation(libs.lets.plot)
    implementation(libs.lets.plot.image)
    implementation(libs.lets.plot.awt)
}

// add friend modules to access internal properties
tasks.withType<KotlinCompile>().configureEach {
    val friendModules = listOf(project(":kandy-lets-plot"))
    val jarTasks = friendModules.map { it.tasks.getByName("jar") as Jar }
    val jarPaths = jarTasks.map { it.archiveFile.get().asFile.absolutePath }
    (this as BaseKotlinCompile).friendPaths.from(jarPaths)
}
