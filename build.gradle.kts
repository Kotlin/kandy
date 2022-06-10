buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("jvm") version "1.6.0" apply false
    kotlin("jupyter.api") version "0.11.0-89-1"
   // id("org.jetbrains.kotlin.jupyter.api") version "0.11.0-89-1"
}

val ggdslVersion = "0.0.1"

allprojects {
    repositories {
        mavenCentral()
    }

    group = "org.jetbrains.kotlinx"
    version = ggdslVersion
}
