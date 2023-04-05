plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

val serialization_version: String by project
val lets_plot_kotlin_version: String by project

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:$serialization_version")
    testImplementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:$lets_plot_kotlin_version")
    testImplementation(project(":kandy-lets-plot"))
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

