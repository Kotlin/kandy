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
    //implementation(project(":ggdsl-dataframe"))
    implementation(project(":ggdsl-echarts"))
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
