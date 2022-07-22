buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("jupyter.api") version "0.11.0-134"
    id("maven-publish")
    id("io.codearte.nexus-staging") version "0.22.0"
}

val ggdslVersion = "0.1.1-dev-10"

allprojects {
    repositories {
        mavenCentral()
    }

    group = "org.jetbrains.kotlinx"
    version = ggdslVersion
    apply(plugin = "maven-publish")
    apply(plugin = "kotlin")
}

subprojects {
    apply(from = project.rootProject.file("gradle/publish.gradle"))
}