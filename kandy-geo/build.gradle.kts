import com.google.devtools.ksp.gradle.KspTaskJvm
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.BaseKotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    with(libs.plugins) {
        alias(kotlin.jvm)
        alias(kotlin.jupyter.api)
        alias(korro)
    }
}

repositories {
    maven("https://repo.osgeo.org/repository/release")
    mavenCentral()
}

// https://stackoverflow.com/questions/26993105/i-get-an-error-downloading-javax-media-jai-core1-1-3-from-maven-central
// jai core dependency should be excluded from geotools dependencies and added separately
fun ExternalModuleDependency.excludeJaiCore() = exclude("javax.media", "jai_core")

dependencies {
    api(project(":kandy-api"))
    api(project(":kandy-lets-plot"))
    implementation(libs.lets.plot)
    implementation(libs.lets.plot.geotools)
    implementation(libs.kotlinx.dataframe)
    implementation(libs.kotlinx.dataframe.geo)

    implementation(libs.geotools.main) { excludeJaiCore() }
    implementation(libs.geotools.shapefile) { excludeJaiCore() }
    implementation(libs.geotools.geojson) { excludeJaiCore() }
    implementation(libs.geotools.referencing) { excludeJaiCore() }
    implementation(libs.geotools.epsg.hsql) { excludeJaiCore() }

    implementation(libs.jai.core)

    testImplementation(project(":samples-utils"))
    testImplementation(kotlin("test"))
}

// add friend modules to access internal properties
tasks.withType<KotlinCompile>().configureEach {
    val friendModules = listOf(project(":kandy-api"), project(":kandy-lets-plot"))
    val jarTasks = friendModules.map { it.tasks.getByName("jar") as Jar }
    val jarPaths = jarTasks.map { it.archiveFile.get().asFile.absolutePath }
    (this as BaseKotlinCompile).friendPaths.from(jarPaths)
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

tasks.processJupyterApiResources {
    libraryProducers = listOf("org.jetbrains.kotlinx.kandy.letsplot.geo.jupyter.IntegrationGeo")
}
