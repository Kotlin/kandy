plugins {
    kotlin("jvm")
    kotlin("jupyter.api")

}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:dataframe:0.8.0")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.0.0")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:2.4.0")
    api(project(":ggdsl-lets-plot"))
    api(project(":ggdsl-dataframe"))
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

tasks.processJupyterApiResources {
    libraryProducers = listOf("org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.Integration")
}
