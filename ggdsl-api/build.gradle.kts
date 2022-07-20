plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka") version "1.6.21"
    kotlin("jupyter.api")
    id("maven-publish")

}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.6.21")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
