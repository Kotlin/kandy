plugins {
    kotlin("jvm")
    //kotlin("plugin.serialization")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":kandy-lets-plot"))
}
