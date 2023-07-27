/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
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

val buildNumber: String? = properties["build_counter"]?.toString()
val kandy_version = version.toString() + if (buildNumber == null) "" else "-dev-$buildNumber"

allprojects {
    repositories {
        mavenCentral()
    }

    group = "org.jetbrains.kotlinx"
    version = kandy_version
    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.dokka")

    kotlin.explicitApi()

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            allWarningsAsErrors = true
            jvmTarget = "11"
        }
    }

    java { toolchain { languageVersion.set(JavaLanguageVersion.of(11)) } }
}


val published = listOf(
    "kandy-api",
    "kandy-echarts",
    "kandy-lets-plot",
    "kandy-util"
)

configure(subprojects.filter { it.name in published }) {
    apply(from = project.rootProject.file("gradle/publish.gradle"))
}

val sonatypeUser: String = System.getenv("SONATYPE_USER") ?: ""
val sonatypePassword: String = System.getenv("SONATYPE_PASSWORD") ?: ""

nexusPublishing {
    packageGroup.set(project.group.toString())
    this.repositories {
        sonatype {
            username.set(sonatypeUser)
            password.set(sonatypePassword)
            repositoryDescription.set("kandy staging repository, version: $version")
        }
    }

    transitionCheckOptions {
        maxRetries.set(100)
        delayBetween.set(Duration.ofSeconds(5))
    }
}
