plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.jupyter.api)
}

dependencies {
    api(libs.kotlinx.dataframe)
    api(libs.kotlinx.datetime)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockk)
}
