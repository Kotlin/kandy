/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

import java.time.Duration

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    val kotlin_version: String by System.getProperties()
    val jupyter_api_version: String by System.getProperties()
    val nexus_version: String by System.getProperties()
    val dokka_version: String by System.getProperties()

    kotlin("jvm") version kotlin_version
    kotlin("plugin.serialization") version kotlin_version
    kotlin("jupyter.api") version jupyter_api_version
    id("maven-publish")
    id("io.github.gradle-nexus.publish-plugin") version nexus_version
    id("org.jetbrains.dokka") version dokka_version
}

val ggdslVersion = "0.3.0-rc-2"

allprojects {
    repositories {
        mavenCentral()
    }

    group = "org.jetbrains.kotlinx"
    version = ggdslVersion
    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.dokka")

    kotlin.explicitApi()

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            allWarningsAsErrors = true
        }
    }
}

val unpublished = listOf("examples")

configure(subprojects.filter { it.name !in unpublished }) {
    apply(from = project.rootProject.file("gradle/publish.gradle"))
}

val sonatypeUser: String = System.getenv("SONATYPE_USER") ?: ""
val sonatypePassword: String = System.getenv("SONATYPE_PASSWORD") ?: ""

nexusPublishing {
    packageGroup.set(project.group.toString())
    repositories {
        sonatype {
            username.set(sonatypeUser)
            password.set(sonatypePassword)
            repositoryDescription.set("ggdsl staging repository, version: $version")
        }
    }

    transitionCheckOptions {
        maxRetries.set(100)
        delayBetween.set(Duration.ofSeconds(5))
    }
}
