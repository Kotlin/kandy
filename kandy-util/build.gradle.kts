plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(libs.kotlinx.serialization.json)

    testImplementation(project(":kandy-lets-plot"))
    testImplementation(libs.kotlin.test)
    testImplementation(libs.lets.plot)
}
