plugins {
    kotlin("jvm")
    kotlin("jupyter.api")
    kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.20")
    implementation("org.jetbrains.kotlinx:dataframe:0.9.0-dev-1139")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:4.1.0")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:2.5.0")
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
