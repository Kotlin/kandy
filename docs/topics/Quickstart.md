# Quickstart

<web-summary xmlns="">
Discover Kandy, a Kotlin library crafted for efficient and versatile data visualization,
offering a distinctive DSL tailored for diverse charting needs.
</web-summary>

<card-summary>
Kandy: Transform your data visualization in Kotlin projects.
Kandy simplifies crafting complex charts with its intuitive and flexible DSL.
</card-summary>

<link-summary>
Unveil the power of Kandy for data visualization in Kotlin.
Kandy streamlines chart creation, combining flexibility and simplicity in its DSL.
</link-summary>

Kandy can be integrated into Kotlin projects and interactive editors.
Learn more in [Getting Started](Getting-Started.md).

This Quickstart uses Kandy in a Kotlin notebook, demonstrating a swift setup process.

## Integrating Kandy

To include Kandy in your notebook, execute the following code in a cell:

```
%use kandy
```

This command makes all necessary classes and methods available for chart construction.

## Crafting Your First Plot

Now, let's create a chart. Use the code below to build your first plot with Kandy:

```kotlin
val time = listOf(0, 1, 2, 4, 5, 7, 8, 9)
val temperature = listOf(12.0, 14.2, 15.1, 15.9, 17.9, 15.6, 14.2, 24.3)
val humidity = listOf(0.5, 0.32, 0.11, 0.89, 0.68, 0.57, 0.56, 0.5)

val weatherData = mapOf(
    "time" to time,
    "temperature" to temperature,
    "humidity" to humidity
)  // Combine data into a map

plot(weatherData) { // Begin plotting
    x("time") // Set x-axis with time data
    y("temperature") { // Set y-axis with temperature data
        // Define scale for temperature (y-axis)
        scale = continuous(0.0..25.5)
    }

    bars { // Add a bar layer
        fillColor("humidity") { // Customizing bar colors based on humidity
            // Setting the color range
            scale = continuous(range = Color.YELLOW..Color.RED)
        }
        borderLine.width = 0.0 // Define border line width
    }

    line {
        width = 3.0 // Set line width
        color = Color.hex("#6e5596") // Define line color
        type = LineType.DOTDASH // Specify line type
    }

    layout { // Set plot layout
        title = "Simple plot with kandy-lets-plot" // Add title
        // Add caption
        caption = "See `examples` section for more\n complicated and interesting examples!"
        size = 700 to 450 // Plot dimension settings
    }
}
```

This code results in your inaugural plot using Kandy.

![Simple plot with Kandy](overview_sample.svg) { border-effect="rounded" }



<seealso style="cards">
    <category ref="get-start">
        <a href="Getting-Started.md">Getting Started</a>
        <a href="User-Guide.topic">User Guide</a>
        <a href="Examples.topic">Examples</a>
        <a href="API.md">API Reference</a>
    </category>
</seealso>
