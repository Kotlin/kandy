import com.google.devtools.ksp.gradle.KspTaskJvm
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    with(libs.plugins) {
        alias(kotlin.jvm)
        alias(kotlin.jupyter.api)
        alias(korro)
    }
}

repositories {
    maven("https://packages.jetbrains.team/maven/p/kds/kotlin-ds-maven")
}

dependencies {
    api(project(":kandy-api"))
    implementation(project(":kandy-util"))

    implementation(libs.kotlinx.html)
    implementation(libs.lets.plot)
    implementation(libs.lets.plot.image)
    implementation(libs.lets.plot.awt)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.statistics)
    testImplementation(project(":samples-utils"))
}

tasks.test {
    dependsOn("jar")
    jvmArgs("-Xmx4G")
}

tasks.withType<KspTaskJvm> {
    if (name == "kspTestKotlin") {
        dependsOn("jar")
    }
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
        include("docs/topics/samples/pie/*.md")
        include("docs/topics/samples/histogram/*.md")
        include("docs/topics/samples/countPlot/*.md")
        include("docs/topics/samples/densityPlot/*.md")
        include("docs/topics/samples/heatmap/*.md")
        include("docs/topics/samples/layout/*.md")
        include("docs/topics/samples/candlestick/*.md")
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