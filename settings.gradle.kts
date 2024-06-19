pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "kandy"
include("kandy-api")
include("kandy-lets-plot")
//include("kandy-echarts")
include("kandy-util")

include("examples:idea-examples:lets-plot-simple")
//include("examples:idea-examples:echarts-simple")
