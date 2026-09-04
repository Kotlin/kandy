import com.google.devtools.ksp.gradle.KspTaskJvm
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

    // `api` scope for everything a caller of our public API has to resolve (#1920).
    // Beyond the usual signature types, our `public inline fun`s inline calls to `geometry()` and `crs()`,
    // so JTS and the GeoTools interfaces are part of the effective ABI as well:
    //  - dataframe-geo supplies `GeoDataFrame` / `WithGeometry` (GeoDataFrame<T>.plot, withData)
    //  - jts-core supplies `Geometry`, `Puntal`, `Polygonal`, `Lineal`, `MultiPolygon`
    //    (mergePolygons, and the geometry checks inlined into geoPoints/geoPolygon/geoPath)
    //  - gt-api supplies `CoordinateReferenceSystem` (crs(), inlined into geoMap)
    api(libs.kotlinx.dataframe.geo) {
        exclude("org.geotools")
    }
    api(libs.jts.core)
    api(libs.geotools.api) { excludeJaiCore() }

    // used internally only; `implementation` still puts them on the consumer's runtime classpath
    implementation(libs.geotools.main) { excludeJaiCore() }
    implementation(libs.geotools.shapefile) { excludeJaiCore() }
    implementation(libs.geotools.geojson) { excludeJaiCore() }
    implementation(libs.geotools.referencing) { excludeJaiCore() }
    implementation(libs.geotools.epsg.hsql) { excludeJaiCore() }

    implementation(libs.jai.core)

    testImplementation(project(":kandy-samples-utils"))
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
    jvmArgs("-Xmx8G")
}

tasks.withType<KspTaskJvm> {
    if (name == "kspTestKotlin") {
        dependsOn("jar")
    }
}

tasks.processJupyterApiResources {
    libraryProducers = listOf("org.jetbrains.kotlinx.kandy.letsplot.geo.jupyter.IntegrationGeo")
}

korro {
    docs = fileTree(rootProject.rootDir) {
        include("docs/topics/samples/geo/*.md")
        include("docs/topics/guides/Geo-Plotting-Guide.md")
    }

    samples = fileTree(project.projectDir) {
        include("src/test/kotlin/org/jetbrains/kotlinx/kandy/geo/samples/gallery/*.kt")
        include("src/test/kotlin/org/jetbrains/kotlinx/kandy/geo/samples/guides/*.kt")
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
