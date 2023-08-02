plugins {
    kotlin("jvm")
    kotlin("jupyter.api")
    //kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

//val serialization_version: String by project
val datetime_version: String by project
val dataframe_version: String by project

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    api("org.jetbrains.kotlinx:kotlinx-datetime:$datetime_version")
    api("org.jetbrains.kotlinx:dataframe:$dataframe_version")
   // implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serialization_version")
}
