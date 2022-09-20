import java.time.Duration

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("jupyter.api") version "0.11.0-150"
    id("maven-publish")
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    id("org.jetbrains.dokka") version "1.7.10"
}

val ggdslVersion = "0.2.0-alpha-5"

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

subprojects {
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