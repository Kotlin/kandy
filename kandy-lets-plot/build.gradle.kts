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
val mockk_version: String by project

dependencies {
    api(project(":kandy-api"))
    implementation(kotlin("stdlib"))
    implementation(project(":kandy-util"))

    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$html_version")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime-jvm:$datetime_version")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:$lets_plot_kotlin_version")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:$lets_plot_image_version")
    implementation("org.jetbrains.lets-plot:platf-awt-jvm:$lets_plot_image_version") // todo(multiplatform library)

    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:${mockk_version}")
}

tasks.test {
    jvmArgs("-Xmx4G")
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        val friendModule = project(":kandy-api")
        val jarTask = friendModule.tasks.getByName("jar") as Jar
        val jarPath = jarTask.archiveFile.get().asFile.absolutePath
        freeCompilerArgs += "-Xfriend-paths=$jarPath"
    }
}