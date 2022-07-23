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
   // implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0") // todo remove
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:3.3.0")
   // implementation("org.jetbrains.lets-plot:lets-plot-image-export:2.4.0") // todo remove?
    api(project(":ggdsl-api"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}
