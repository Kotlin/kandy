plugins {
    kotlin("jvm")
    kotlin("jupyter.api")
}

repositories {
    mavenCentral()
}

//val serialization_version: String by project
val datetime_version: String by project
val dataframe_version: String by project
val mockk_version: String by project

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:${mockk_version}")
    api("org.jetbrains.kotlinx:kotlinx-datetime:$datetime_version")
    api("org.jetbrains.kotlinx:dataframe:$dataframe_version")
}
