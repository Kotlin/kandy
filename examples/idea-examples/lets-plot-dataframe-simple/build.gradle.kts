plugins {
    kotlin("jvm")
    //kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

val dataframe_version: String by project

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:dataframe:$dataframe_version")
    implementation(project(":ggdsl-lets-plot"))
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
