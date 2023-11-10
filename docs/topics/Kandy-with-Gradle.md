# Kandy with Gradle

<web-summary>
    Dive into the process of setting up a Kotlin JVM project managed with Gradle,
    introducing the Kandy library to your workspace.
    This section provides a step-by-step guide to initiating a project,
    incorporating the Kandy library, and crafting your first plot.
</web-summary>
<card-summary>
    Discover the essentials of starting a Kotlin JVM project with Gradle and integrating the Kandy library.
    This segment elucidates the step-by-step procedure
    to get your first Kandy visualization up and running in a Gradle managed project.
</card-summary>
<link-summary>
    Your Roadmap to Creating First Visualizations with Kandy in a Gradle Managed Kotlin Project
</link-summary>

Using the Kandy library is possible in Kotlin projects on the JVM managed by Gradle.

## Getting started with Kandy on Gradle

1. Install [IntelliJ IDEA](https://www.jetbrains.com/idea/download)
2. From the main menu, select <ui-path>File | New | Project</ui-path>.
3. In the panel on the left, select <ui-path>New Project</ui-path>.
4. Choose <ui-path>Kotlin</ui-path> as the programming language and <ui-path>Gradle</ui-path> as the build system.
   ![New Project in IDEA](new_project.png)
5. Click <ui-path>Create</ui-path> button.
6. Navigate to your Gradle build file, which could either be <path>build.gradle.kts</path> (for Kotlin DSL)
   or <path>build.gradle</path> (for Groovy DSL), and add the Kandy library as a dependency:

<tabs>
   <tab title="Kotlin DSL">

```kotlin
dependencies {
    implementation("org.jetbrains.kotlinx:kandy-lets-plot:%latest_version%")
}
```

   </tab>
   <tab title="Groovy DSL">

```groovy
dependencies {
    implementation 'org.jetbrains.kotlinx:kandy-lets-plot:%latest_version%'
}
```

   </tab>
</tabs>

You now have access to the Kandy library in your Kotlin project.

## Plotting a Simple Example

```kotlin
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars

fun main() {
    // Create a DataFrame with data on average temperatures in various cities
    val averageTemperature = dataFrameOf(
        "city" to listOf("New York", "London", "Berlin", "Erevan", "Tokyo"),
        "average temperature" to listOf(12.5, 11.0, 9.6, 11.5, 16.0)
    )

    // Construct a plot using the data from the DataFrame
    averageTemperature.plot {
        // Add bars to the plot
        // Each bar represents the average temperature in a city
        bars {
            x("city") // Set the cities' data on the X-axis
            y("average temperature") { // Set the temperatures' data on the Y-axis
                axis.name = "Average Temperature (°C)" // Assign a name to the Y-axis
            }
        }
        // Set the title of the plot
        layout.title = "Kandy Getting Started Example"
    }.save("Started Example.png") // Save plot as PNG image
}
```

![Kandy Getting Started Example](getting_sample.svg){ border-effect="rounded" }