import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("jupyter.api")
}

repositories {
    mavenCentral()
}

val html_version: String by project
val datetime_version: String by project
val lets_plot_kotlin_version: String by project
val lets_plot_image_version: String by project
//val serialization_version: String by project

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(mapOf("path" to ":kandy-api")))
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$html_version")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime-jvm:$datetime_version")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:$lets_plot_kotlin_version")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:$lets_plot_image_version")
    // todo(multiplatform library)
    implementation("org.jetbrains.lets-plot:platf-awt-jvm:$lets_plot_image_version")
    api(project(":kandy-api"))
    implementation(project(":kandy-util"))
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        val friendModule = project(":kandy-api")
        val jarTask = friendModule.tasks.getByName("jar") as Jar
        val jarPath = jarTask.archiveFile.get().asFile.absolutePath
        freeCompilerArgs += "-Xfriend-paths=$jarPath"
    }
}