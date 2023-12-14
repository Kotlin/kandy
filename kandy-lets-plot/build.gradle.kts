import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("jupyter.api")
    id("io.github.devcrocod.korro") version "0.1.5"
}

repositories {
    mavenCentral()
    maven("https://packages.jetbrains.team/maven/p/kds/kotlin-ds-maven")
}

val html_version: String by project
val datetime_version: String by project
val lets_plot_kotlin_version: String by project
val lets_plot_image_version: String by project
val mockk_version: String by project

dependencies {
    api(project(":kandy-api"))
    implementation(kotlin("stdlib"))
    implementation(project(":kandy-util"))

    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$html_version")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime-jvm:$datetime_version")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:$lets_plot_kotlin_version")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:$lets_plot_image_version")
    implementation("org.jetbrains.lets-plot:platf-awt-jvm:$lets_plot_image_version") // todo(multiplatform library)

    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:${mockk_version}")
    testImplementation("org.jetbrains.kotlinx:kotlin-statistics-jvm:0.0.5")
}

tasks.test {
    dependsOn("jar")
    jvmArgs("-Xmx4G")
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        val friendModule = project(":kandy-api")
        val jarTask = friendModule.tasks.getByName("jar") as Jar
        val jarPath = jarTask.archiveFile.get().asFile.absolutePath
        freeCompilerArgs += "-Xfriend-paths=$jarPath"
    }
}

korro {
    docs = fileTree(rootProject.rootDir) {
        include("docs/topics/Quickstart.md")
        include("docs/topics/Overview.md")
        include("docs/topics/samples/*.md")
        include("docs/topics/samples/line/*.md")
        include("docs/topics/samples/area/*.md")
        include("docs/topics/samples/bars/*.md")
        include("docs/topics/samples/points/*.md")
        include("docs/topics/samples/errorBars/*.md")
        include("docs/topics/samples/ribbon/*.md")
        include("docs/topics/samples/boxplot/*.md")
        include("docs/topics/samples/tiles/*.md")
        include("docs/topics/samples/histogram/*.md")
        include("docs/topics/samples/countPlot/*.md")
        include("docs/topics/samples/densityPlot/*.md")
        include("docs/topics/samples/heatmap/*.md")
        include("docs/topics/samples/layout/*.md")
        include("docs/topics/guides/*.md")
        include("README.md")
    }

    samples = fileTree(project.projectDir) {
        include("src/test/kotlin/org/jetbrains/kotlinx/kandy/letsplot/samples/*.kt")
        include("src/test/kotlin/org/jetbrains/kotlinx/kandy/letsplot/samples/guides/*.kt")
    }

    groupSamples {
        beforeSample.set("<tab title=\"NAME\">\n")
        afterSample.set("\n</tab>")

        funSuffix("_dataframe") {
            replaceText("NAME", "Dataframe")
        }
        funSuffix("_collections") {
            replaceText("NAME", "Collections")
        }
        beforeGroup.set("<tabs>\n")
        afterGroup.set("</tabs>")
    }
}