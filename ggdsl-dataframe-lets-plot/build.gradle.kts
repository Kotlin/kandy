plugins {
    kotlin("jvm")
    kotlin("jupyter.api")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

val kotlin_version: String by System.getProperties()
val serialization_version: String by project
val dataframe_version: String by project
val lets_plot_kotlin_version: String by project
val lets_plot_image_version: String by project


dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serialization_version")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
    implementation("org.jetbrains.kotlinx:dataframe:$dataframe_version")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:$lets_plot_kotlin_version")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:$lets_plot_image_version")
    api(project(":ggdsl-lets-plot"))
    api(project(":ggdsl-dataframe"))
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


tasks.processJupyterApiResources {
    libraryProducers = listOf("org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.Integration")
}
