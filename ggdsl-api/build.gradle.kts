plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka") version "1.6.21"
    kotlin("jupyter.api")
    `maven-publish`
}

group = "com.andreikingsley"
val ggDSLVersion = "0.6.5-3"
version = ggDSLVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

