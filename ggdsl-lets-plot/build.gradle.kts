plugins {
    kotlin("jvm")
    kotlin("jupyter.api")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

val html_version: String by project
val datetime_version: String by project
val lets_plot_kotlin_version: String by project
val lets_plot_image_version: String by project
val serialization_version: String by project

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$html_version")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime-jvm:$datetime_version")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:$lets_plot_kotlin_version")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:$lets_plot_image_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serialization_version")
    api(project(":ggdsl-api"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.withType<JavaCompile> {
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    targetCompatibility = JavaVersion.VERSION_1_8.toString()
}

