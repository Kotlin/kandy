import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.time.Duration

plugins {
    with(libs.plugins) {
        alias(kotlin.jvm)
        alias(kotlin.serialization) apply false
        alias(kotlin.jupyter.api) apply false
        alias(dokka) apply false
        alias(korro) apply false
        alias(nexus.publish)
    }
}

val buildNumber: String? = properties["build_counter"]?.toString()
val kandyVersion = version.toString() + if (buildNumber == null) "" else "-dev-$buildNumber"

allprojects {
    group = "org.jetbrains.kotlinx"
    version = kandyVersion

    apply(plugin = "kotlin")

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
