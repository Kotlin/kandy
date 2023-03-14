plugins {
    kotlin("jvm")
    kotlin("jupyter.api")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

val html_version: String by project
val serialization_version: String by project
val datetime_version: String by project

dependencies {
    api(project(":ggdsl-api"))
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-datetime-jvm:$datetime_version")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$html_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serialization_version")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}

tasks.withType<JavaCompile> {
    sourceCompatibility = JavaVersion.VERSION_11.toString()
    targetCompatibility = JavaVersion.VERSION_11.toString()
}
