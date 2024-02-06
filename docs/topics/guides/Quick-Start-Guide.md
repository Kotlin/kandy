# Quick Start Guide

<show-structure for="chapter,procedure" depth="3" xmlns=""></show-structure>

<web-summary>
Embark on your journey with Kandy, the Kotlin-based data visualization library.
Our Quick Start Guide is tailored to help beginners and seasoned users alike,
offering straightforward instructions for creating impactful visualizations.
From data preparation to crafting detailed plots, this guide ensures a smooth start with Kandy.
</web-summary>

<card-summary>
This guide covers all the essentials, from data handling to advanced plotting techniques,
to kickstart your Kandy experience.
</card-summary>

<link-summary>
Explore data visualization in Kotlin with the Kandy Quick Start Guide,
a comprehensive resource for creating effective charts and plots.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.QuickStartGuide-->

## Basics

### Usage

<tabs>
<tab title="Notebooks">

To integrate Kandy and Dataframe into an interactive notebook, use the following commands:

<tabs>
<tab title="Latest versions">

> Without specifying `%useLatestDescriptors`,
> the version included in the Kotlin Jupyter kernel will be used.
> {style="note"}

<br/>

```
// Fetches the latest versions
%useLatestDescriptors
// Adds the dataframe library with the latest version
%use dataframe
// Adds the kandy library with the latest version
%use kandy
```

</tab>
<tab title="Specify versions">

```
// Adds the dataframe library with a specific version
%use dataframe(%dataframe_latest_version%)
// Adds the kandy library with a specific version
%use kandy(%kandy_latest_version%)
```

</tab>
</tabs>

> Kotlin notebook offers unique features with the dataframe library.


</tab>
<tab title="Gradle">

To include Kandy in your Gradle project, add the following to your dependencies:

```kotlin
dependencies {
    implementation("org.jetbrains.kotlinx:kandy-lets-plot:%kandy_latest_version%")
}
```

</tab>
</tabs>

### Data

In Kandy, the primary data model for plotting is a _"named data"_ or _"dataframe"_.
This model comprises a set of named columns with values of equal length.
The input data should be structured as `Map<String, List<*>>`.

Example of a simple dataset in Kandy:

<!---FUN basicsDataSimpleDataset-->

```kotlin
val simpleDataset = mapOf(
    "time, ms" to listOf(12, 87, 130, 149, 200, 221, 250),
    "relativeHumidity" to listOf(0.45, 0.3, 0.21, 0.15, 0.22, 0.36, 0.8),
    "flowOn" to listOf(true, true, false, false, true, false, false),
)
```

<!---END-->

To reference dataset columns in your plots, create pointers for each column. There are several ways to do this:

<!---FUN basicsDataDefineColumns-->

```kotlin
// 1. Using the `column()` function with the specified column type and name:
val timeMs = column<Int>("time, ms")
// 2. Utilizing the String API, similar to the method above, but using String invocation:
val humidity = "relativeHumidity"<Double>()
// 3. Delegating an unnamed column - its name will be derived from the variable name:
val flowOn by column<Boolean>()
```

<!---END-->

### Plot Creation

In Kandy, to create a plot, you start by calling the `plot()` function and passing your dataset as an argument.
This function establishes a context within which you can add various layers to your plot.

A _layer_ is essentially a collection of mappings from your data to the visual attributes of the plot,
known as _aesthetic attributes_.
These attributes define how your data is represented visually, such as through points, lines, or bars.

Here's an example demonstrating the creation of a plot with a single layer:

<!---FUN basicsPlotCreationHumidityData-->

```kotlin
plot(simpleDataset) {
    points {
        // Maps values from the "time, ms" column to the X-axis
        x(timeMs)
        // Maps values from the "relativeHumidity" column to the Y-axis
        y(humidity)
        // Sets the size of the points to 4.5
        size = 4.5
        // Maps values from the "flowOn" column to the color attribute
        color(flowOn)
    }
}
```

<!---END-->

![Plot Humidity Data](basicsPlotCreationHumidityData.svg)

#### Layers, aesthetics, mappings, and scales

Each plot layer is defined by its _geometrical entity_ (or <tooltip term="geom">_geom_</tooltip>),
which determines the layer's visual representation.
<tooltip term="geom">Geoms</tooltip> have associated _aesthetic attributes_
(or <tooltip term="aes">_aesthetics/aes_</tooltip>),
which can be either <tooltip term="posAes">_positional_</tooltip>
(like `x`, `y`, `yMin`, `yMax`, `middle`) or _non-positional_ (such as `color`, `size`, `width`).
Non-positional aesthetics have specific types (e.g., `size` is associated with `Double`, `color` with `Color`).

Aesthetic values can be assigned in two ways: _setting_ and _mapping_.

* _Setting_ involves assigning a constant value directly:

```kotlin
// Using `.constant()` for positional aes:
x.constant(12.0f)
yMin.constant(10.0)

// Assignment for non-positional aes:
size = 5.0
color = Color.RED
```

* _Mapping_ links data column values to <tooltip term="aes">aesthetic</tooltip> attributes.
   This can be defined in various ways:

```kotlin
// With `ColumnReference`:
x(timeMs)
size(humidity)
color(flowOn)

// Using raw `String`:
x("time, ms")
size("relativeHumidity")

// Directly providing values, e.g., with `Iterable`:
x(listOf(12, 87, 130, 149, 200, 221, 250))
// Optional source ID can be set:
color(listOf(true, true, false, false, true, false, false), "flow on")
```

**Scales** determine how data values are translated into visual representations.
They can be **categorical** (discrete) or **continuous**, based on their domain and range types.
**Continuous** scales use limits for their domain and range,
while **categorical** scales use lists of categories and corresponding values.
Scales are typed and may include a _transform_ parameter to define a transformation function (linear by default).

Explicitly specifying scales after mapping:

<!---FUN basicsLayersCustomizedScales-->

```kotlin
plot(simpleDataset) {
    points {
        x(listOf(12, 87, 130, 149, 200, 221, 250)) {
            scale = continuous(min = 0, max = 270) // Using `min`/`max`
        }
        y(humidity) {
            scale = continuous(0.0..1.0) // Using `Range`
        }
        size(humidity) {
            scale = continuous(range = 5.0..20.0)
        }
        color(flowOn) {
            scale = categorical(true to Color.RED, false to Color.BLUE)
        }
        symbol(flowOn) {
            scale = categorical()// default scale
        }
        alpha = 0.9
    }
}
```

<!---END-->

![Plot Customized Scales for Humidity Data](basicsLayersCustomizedScales.svg)

Scales can also be created separately and applied later:

<!---FUN basicsLayersDefineCustomScales-->

```kotlin
val xReversedScale = Scale.continuousPos<Int>(transform = Transformation.REVERSE)
val yScale = Scale.continuousPos(0.0..0.9)
val colorScale = Scale.categorical<Color, Boolean>(
    range = listOf(Color.RED, Color.GREEN)
)
```

<!---END-->

Applying pre-defined scales:

<!---FUN basicsLayersPlotWithCustomScales-->

```kotlin
plot(simpleDataset) {
    points {
        x(listOf(12, 87, 130, 149, 200, 221, 250)) {
            scale = xReversedScale
        }
        y(humidity) {
            scale = yScale
        }
        color(flowOn) {
            scale = colorScale
        }
        symbol = Symbol.ASTERIX
        size = 6.6
    }
}
```

<!---END-->

![Plot Humidity Data With Custom Scales](basicsLayersPlotWithCustomScales.svg)

#### Scale parameters: axis and legend

**Guides** play a crucial role in interpreting charts.
They function as mini-charts for scales, with positional scales using axes as guides and non-positional ones utilizing
legends.
Each scale comes with a default guide, but you can tailor it to your needs.

Here's how to customize the axis and legend in a plot:

<!---FUN basicsScaleParamTimeHumidityGraph-->

```kotlin
// Creating a plot with customized axis and legend
plot(simpleDataset) {
    points {
        // Configuring the x-axis for time
        x(timeMs) {
            axis.name = "Time from start of counting,\n milliseconds"
        }
        // Configuring the y-axis for humidity
        y(humidity) {
            scale = continuous(0.0..1.0) // Setting scale for humidity
            axis {
                name = "Relative humidity" // Axis label
                breaksLabeled(0.0 to "0%", 0.3 to "30%", 0.6 to "60%", 0.9 to "90%") // Custom axis breaks
            }
        }
        size = 12.0 // Set size of points
        // Configuring the legend for humidity
        color(humidity) {
            scale = continuous()
            legend {
                name = "rel. humidity" // Legend label
                type = LegendType.ColorBar(40.0, 190.0, 15) // Legend type and dimensions
                breaks(format = "e") // Legend breaks format
            }
        }
    }
}
```

<!---END-->

![Plot Humidity Data With Time](basicsScaleParamTimeHumidityGraph.svg)

#### Global X-axis and Y-axis mappings and scales

In Kandy, you can use global mappings for `X` and `Y` across multiple layers, simplifying the process when these
mappings are common.
Each layer inherits the global mapping unless overridden locally.

Example with global X and Y mappings:

<!---FUN basicsGlobalXYPlotLine-->

```kotlin
// Using global mappings for X and Y
plot(simpleDataset) {
    x(timeMs) { scale = continuous(max = 275) }
    y(humidity)
    points {
        // Inherits X and Y from the global context
        size = 4.5
        color(flowOn)
    }
    line {
        // Inherits X, overrides Y
        y(listOf(0.49, 0.39, 0.1, 0.4, 0.8, 0.8, 0.9))
        width = 3.0
        color = Color.RED
    }
}
```

<!---END-->

![Plot Humidity Data With Line](basicsGlobalXYPlotLine.svg)

Configuring axis scales without explicit mapping:

<!---FUN basicsGlobalXYConfigureAxisScales-->

```kotlin
// Configuring axis scales independently
plot(simpleDataset) {
    points {
        x(listOf(10, 20, 30, 40, 50, 60, 70))
        y(humidity)
        size = 4.5
        color(flowOn)
    }
    x.axis.name = "time, ms"
    y {
        scale = continuous(min = 0.0)
        axis.breaks(format = ".2f")
    }
}
```

<!---END-->

![Configure Axis Scales](basicsGlobalXYConfigureAxisScales.svg)

<!---FUN basicsGlobalXYConfigureAxisScalesWithAltApi-->

```kotlin
plot(simpleDataset) {
    points {
        x(listOf(10, 20, 30, 40, 50, 60, 70))
        y(humidity)
        size = 4.5
        color(flowOn)
    }
    // Alternate brief notation if we want to set the axis limits (continuous scale)
    x.limits = 0..80
    y.limits = 0.0..1.0
}
```

<!---END-->

![Configure Axis Scales Alternative API](basicsGlobalXYConfigureAxisScalesWithAltApi.svg)

**Free scale** mechanism in Kandy:

This feature, known as _"free scale"_ allows the setting of scale parameters without direct data mapping.
It's particularly useful for sub-positional <<tooltip term="aes">aesthetics</tooltip>
that don't have individual scales but depend on the parent <tooltip term="posAes">positional aesthetics</tooltip> scale.

Example of using free scale in a `boxplot`:

<!---FUN basicsGlobalXYBoxplotWithFreeScale-->

```kotlin
// Demonstrating free scale in a boxplot
plot(
    mapOf(
        "x" to listOf("a", "b", "c"),
        // Boxplot sub-positional aesthetics
        "min" to listOf(0.8, 0.4, 0.6),
        "lower" to listOf(0.9, 1.4, 0.8),
        "middle" to listOf(1.5, 2.4, 1.6),
        "upper" to listOf(1.9, 3.4, 1.7),
        "max" to listOf(3.1, 4.4, 2.6),
        "width" to listOf(0.1, 1.4, 14.0)
    )
) {
    boxes {
        x("x"<String>())
        // Sub-y aesthetics with a free scale
        yMin("min"<Double>())
        lower("lower"<Double>())
        middle("middle"<Double>())
        upper("upper"<Double>())
        yMax("max"<Double>())
        fatten = 4.5
        alpha = 0.2
        borderLine.color = Color.hex(0x9A2A2A)
    }
    y {
        axis.name = "weight"
        limits = 0.0..5.0
    }
}
```

<!---END-->

![Boxplot With Free Scale](basicsGlobalXYBoxplotWithFreeScale.svg)

#### Raw source mapping

Kandy allows for direct data source mapping in plots, providing an alternative to using dataset column pointers.
This method supports mapping from an `Iterable` source and offers the option to name these sources for clearer
visualization.

Example dataset:

<!---FUN basicsRawSourceInitializeMonthData-->

```kotlin
// Sample data with months, number of days, and seasons
val month = listOf(
    "January", "February",
    "March", "April", "May",
    "June", "July", "August",
    "September", "October", "November",
    "December"
)
val numberOfDays = listOf(31, 28, 31, 30, 31, 30, 31, 30, 31, 30, 31, 30)
val season = listOf(
    "winter", "winter",
    "spring", "spring", "spring",
    "summer", "summer", "summer",
    "autumn", "autumn", "autumn",
    "winter"
)
```

<!---END-->

Using raw source mapping:

<!---FUN basicsRawSourceDaysPerMonthBarPlot-->

```kotlin
// Plotting using raw source mapping
plot {
    bars {
        // Mapping 'month' directly with a name
        x(month, "month") { scale = categorical() }
        // Mapping 'numberOfDays' directly
        y(numberOfDays, "number of days")
        // Mapping 'season' with color, named source, and categorical scale
        fillColor(season, "season") {
            scale = categorical(
                listOf(Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE),
                listOf("winter", "spring", "summer", "autumn"),
            )
        }
    }
}
```

<!---END-->

![Bars with Raw Source Mapping](basicsRawSourceDaysPerMonthBarPlot.svg)

## Kotlin DataFrame API

Kandy integrates seamlessly with the [Kotlin DataFrame](https://kotlin.github.io/dataframe) library,
allowing the use of `DataFrame` as a data source and `DataColumn` for mappings.
This integration simplifies the process of creating visualizations,
as you can utilize auto-generated property columns without manually creating column pointers.

```kotlin
// Reading a CSV file into a DataFrame
val mpgDF =
    DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
// Display the first five rows of the DataFrame
mpgDF.head()
```

<table style="header-row">
<tr>
   <td><b>untitled</b></td>
   <td><b>manufacturer</b></td>
   <td><b>model</b></td>
   <td><b>displ</b></td>
   <td><b>year</b></td>
   <td><b>cyl</b></td>
   <td><b>trans</b></td>
   <td><b>drv</b></td>
   <td><b>cty</b></td>
   <td><b>hwy</b></td>
   <td><b>fl</b></td>
   <td><b>class</b></td>
</tr>
<tr>
   <td>1</td>
   <td>audi</td>
   <td>a4</td>
   <td>18,0</td>
   <td>1999</td>
   <td>4</td>
   <td>auto(l5)</td>
   <td>f</td>
   <td>18</td>
   <td>29</td>
   <td>p</td>
   <td>compact</td>
</tr>
<tr>
   <td>2</td>
   <td>audi</td>
   <td>a4</td>
   <td>18,0</td>
   <td>1999</td>
   <td>4</td>
   <td>manual(m5)</td>
   <td>f</td>
   <td>21</td>
   <td>29</td>
   <td>p</td>
   <td>compact</td>
</tr>
<tr>
   <td>3</td>
   <td>audi</td>
   <td>a4</td>
   <td>2,0</td>
   <td>2008</td>
   <td>4</td>
   <td>manual(m6)</td>
   <td>f</td>
   <td>20</td>
   <td>31</td>
   <td>p</td>
   <td>compact</td>
</tr>
<tr>
   <td>4</td>
   <td>audi</td>
   <td>a4</td>
   <td>2,0</td>
   <td>2008</td>
   <td>4</td>
   <td>auto(av)</td>
   <td>f</td>
   <td>21</td>
   <td>30</td>
   <td>p</td>
   <td>compact</td>
</tr>
<tr>
   <td>5</td>
   <td>audi</td>
   <td>a4</td>
   <td>28,0</td>
   <td>1999</td>
   <td>6</td>
   <td>auto(l5)</td>
   <td>f</td>
   <td>16</td>
   <td>26</td>
   <td>p</td>
   <td>compact</td>
</tr>
</table>

```kotlin
// Show the schema of the DataFrame
mpgDF.schema()
```

```text
untitled: Int
manufacturer: String
model: String
displ: Double
year: Int
cyl: Int
trans: String
drv: String
cty: Int
hwy: Int
fl: String
class: String
```

Example of using DataFrame with Kandy:

<!---FUN kotlinDataframeApiPlotMpgInfo-->

```kotlin
// Create a plot using the DataFrame
val mpgInfoPlot = mpgDF.plot {
    points {
        x(displ) // Auto-generated DataFrame columns
        y(cty) {
            scale = continuous(8..34)
        }
        symbol = Symbol.CIRCLE_FILLED
        color = Color.GREY
        alpha = 0.7
        fillColor(drv)
        size(hwy) {
            scale = continuous(5.0..15.0)
            legend.breaks(listOf(15, 30, 40), format = "d")
        }
    }
}
mpgInfoPlot
```

<!---END-->

![Mpg Info Plot](kotlinDataframeApiPlotMpgInfo.svg)

In scenarios where auto-generated property-columns are not available,
you can manually create pointers to DataFrame columns:

<!---FUN kotlinDataframeApiDrvCountPlot-->

```kotlin
// Creating a plot with manual column pointers
val mpgCountPlot = mpgDF.groupBy { drv }.aggregate {
    count() into "count"
}.plot {
    bars {
        x(drv)
        y("count"<Int>())
        alpha = 0.75
    }
}
mpgCountPlot
```

<!---END-->

![drv count Plot](kotlinDataframeApiDrvCountPlot.svg)

This integration with Kotlin DataFrame enriches Kandy's plotting capabilities,
providing a more streamlined and efficient approach to data visualization in Kotlin projects.

## Grouping

The grouping feature in Kandy is particularly useful for plotting collective geometries,
where multiple data units are represented by a single geometric object, such as a `line`.
Grouping can be performed either by providing a grouped dataframe (`GroupBy`) as a dataset or directly within the plot
DSL.

Here's how you can use grouping in Kandy:

1. **Grouping with a Map as Dataset**:
   You can define your dataset as a map and then use `groupBy` within the plotting DSL to create groups based on a
   specific column.

<!---FUN groupingDatasetWithGrouping-->

```kotlin
// Dataset with a grouping column
val lineDataset = mapOf(
    "timeG" to listOf(1.0, 2.2, 3.4, 6.6, 2.1, 4.4, 6.0, 1.5, 4.7, 6.7),
    "value" to listOf(112.0, 147.3, 111.1, 200.6, 90.8, 110.2, 130.4, 100.1, 90.0, 121.8),
    "c-type" to listOf("A", "A", "A", "A", "B", "B", "B", "C", "C", "C")
)
```

<!---END-->

<!---FUN groupingSimpleGrouping-->

```kotlin
// Using grouping in the plot
plot(lineDataset) {
    groupBy("c-type") {
        line {
            x("timeG")
            y("value")
        }
    }
}
```

<!---END-->

![Grouping in the Plot](groupingSimpleGrouping.svg)

2. **Grouping with DataFrame API**:
   If you are using the Kotlin DataFrame API, you can apply grouping on a dataframe.

<!---FUN groupingMapToDataFrame-->

```kotlin
// Convert the map to a DataFrame
val lineDF = lineDataset.toDataFrame()
```

<!---END-->

<!---FUN groupingPlotOnDataFrame-->

```kotlin
// Apply grouping on the DataFrame
lineDF.groupBy {
    `c-type`
}.plot {
    line {
        x(timeG)
        y(value)
    }
}
```

<!---END-->

![Grouping in the Plot with DataFrame](groupingPlotOnDataFrame.svg)

3. Advanced Grouping with Color Mapping: For more complex visualizations,
   you can use grouping along with additional aesthetic mappings like color.

<!---FUN groupingWithColorMapping-->

```kotlin
// Grouping with additional color mapping
lineDF.plot {
    groupBy(`c-type`) {
        line {
            x(timeG)
            y(value)
            color(`c-type`)
            width = 4.0
        }
    }
}
```

<!---END-->

![Grouping in the Plot with Color Mapping](groupingWithColorMapping.svg)

### Implicit grouping

Kandy provides a convenient way to perform implicit grouping by utilizing categorical scales.
This approach is especially useful when you want to differentiate data points based on a specific category without
explicitly defining groups.

Plotting with Implicit Grouping: By specifying a categorical scale for an aesthetic attribute like color,
Kandy automatically groups the data based on the categories presented in the data column.

<!---FUN implicitGroupingSimplePlot-->

```kotlin
// Plotting with implicit grouping
lineDF.plot {
    line {
        x(timeG)
        y(value)
        // Implicit grouping based on `c-type`
        color(`c-type`)
        width = 4.0
    }
}
```

<!---END-->

![Implicit Grouping in the Plot](implicitGroupingSimplePlot.svg)

In this example, the `color` aesthetic is mapped to the `c-type` column, which is a categorical variable.
Kandy implicitly groups the data based on the unique values in the `c-type` column and assigns different colors to each
group.
This results in a visually distinct representation for each category, making it easier to differentiate between them in
the plot.

### Position

The `position` parameter plays a crucial role in adjusting the spatial arrangement of objects within a single layer,
especially when dealing with grouped data. It controls how objects from different groups are positioned relative to each
other.

Here's a breakdown of how position can be applied:

<!---FUN groupingPositionGroupedDataframeMean-->

```kotlin
val meanCylDf = mpgDF.groupBy { cyl and drv }.mean { cty }
// Display the first five rows of the grouped DataFrame
meanCylDf.head(5)
```

<!---END-->

<table>
<tr><td><b>cyl</b></td><td><b>drv</b></td><td><b>cty</b></td></tr>
<tr><td>4</td><td>f</td><td>22,068966</td></tr>
<tr><td>6</td><td>f</td><td>17,186047</td></tr>
<tr><td>4</td><td>4</td><td>18,347826</td></tr>
<tr><td>6</td><td>4</td><td>14,781250</td></tr>
<tr><td>8</td><td>4</td><td>12,104167</td></tr>
</table>

1. **Dodge Position**: Separates bars side by side, making it easy to compare groups.

<!---FUN groupingPositionDodgePlot-->

```kotlin
val meanCylPlot = meanCylDf.groupBy { cyl }.plot {
    bars {
        x(drv)
        y(cty)
        fillColor(cyl) { legend.breaks(format = "d") }

        position = Position.dodge()
    }
}
meanCylPlot
```

<!---END-->

![Dodge Position](groupingPositionDodgePlot.svg)

2. **Identity Position**: Overlays bars directly on top of each other, useful for highlighting overlaps.

<!---FUN groupingPositionIdentityPlot-->

```kotlin
meanCylDf.groupBy { cyl }.plot {
    bars {
        x(drv)
        y(cty)
        fillColor(cyl) { legend.breaks(format = "d") }
        alpha = 0.4

        position = Position.identity()
    }
}
```

<!---END-->

![Identity Position](groupingPositionIdentityPlot.svg)

3. **Stack Position**: Stacks bars on top of each other, ideal for cumulative comparisons.

<!---FUN groupingPositionStackPlot-->

```kotlin
meanCylDf.groupBy { cyl }.plot {
    bars {
        x(drv)
        y(cty)
        fillColor(cyl) { legend.breaks(format = "d") }

        position = Position.stack()
    }
}
```

<!---END-->

![Stack Position](groupingPositionStackPlot.svg)

> The `position` parameter is applicable only within a single layer.
> It doesn't affect the positioning of objects across different layers.
> {style="note"}

## Experimental

This section of Kandy explores experimental APIs which are subject to change in future versions.
They offer innovative features for advanced data visualization,
inviting exploration and feedback for further development.

### Multiplot

There are three ways to create a multiplot: `plotGrid`, `plotBunch` and faceting.

#### Plot Grid

The `plotGrid` function allows for arranging multiple plots in a structured grid format,
offering a cohesive view of different data visualizations. Example:

<!---FUN experimentalMultiplotPlotGrid-->

```kotlin
plotGrid(listOf(mpgInfoPlot, mpgCountPlot, meanCylPlot), nCol = 2)
```

<!---END-->

![Plot Grid](experimentalMultiplotPlotGrid.svg)

#### Plot Bunch

`plotBunch` provides a more flexible approach to multiplot creation, enabling custom placement and sizing for each plot.
This is ideal for tailored data presentations. Example:

<!---FUN experimentalMultiplotPlotBunch-->

```kotlin
plotBunch {
    add(mpgCountPlot, 0, 0, 400, 200)
    add(meanCylPlot, 400, 0, 300, 200)
    add(mpgInfoPlot, 0, 200, 700, 300)
}
```

<!---END-->

![Plot Bunch](experimentalMultiplotPlotBunch.svg)

#### Faceting

Faceting in Kandy is a powerful feature that splits a single plot into multiple plots based on dataset categories.
This method is akin to groupBy in data manipulation, providing detailed insights into subcategories.
Faceting methods include:

* `facetGridX` and `facetGridY` for single-parameter faceting along the X or Y axes, respectively:

<!---FUN experimentalMultiplotFacetingGridX-->

```kotlin
mpgDF.plot {
    points {
        x(displ)
        y(cty) {
            scale = continuous(8..34)
        }
        symbol = Symbol.CIRCLE_FILLED
        color = Color.GREY
        alpha = 0.7
        fillColor(drv)
        size(hwy) {
            scale = continuous(2.0..10.0)
        }
    }

    layout.size = 750 to 450

    facetGridX(drv, scalesSharing = ScalesSharing.FREE_X)
}
```

<!---END-->

![Plot Facet Grid X](experimentalMultiplotFacetingGridX.svg)

<!---FUN experimentalMultiplotFacetingGridY-->

```kotlin
mpgDF.plot {
    points {
        x(displ)
        y(cty) {
            scale = continuous(8..34)
        }
        symbol = Symbol.CIRCLE_FILLED
        color = Color.GREY
        alpha = 0.7
        fillColor(drv)
        size(hwy) {
            scale = continuous(2.0..10.0)
        }
    }

    layout.size = 750 to 450

    facetGridY(cyl, format = "d")
}
```

<!---END-->

![Plot Facet Grid Y](experimentalMultiplotFacetingGridY.svg)

* `facetGrid` for two-parameter faceting along both X and Y axes:

<!---FUN experimentalMultiplotFacetingGrid-->

```kotlin
mpgDF.plot {
    points {
        x(displ)
        y(cty) {
            scale = continuous(8..34)
        }

        symbol = Symbol.CIRCLE_FILLED
        color = Color.GREY
        fillColor(drv)
        size(hwy) {
            scale = continuous(2.0..10.0)
        }
    }

    layout.size = 750 to 450

    facetGrid(drv, cyl, yFormat = "d")
}
```

<!---END-->

![Plot Facet Grid](experimentalMultiplotFacetingGrid.svg)

* `facetWrap` for multi-parameter faceting with additional layout control:

<!---FUN experimentalMultiplotFacetingWrap-->

```kotlin
mpgDF.plot {
    points {
        x(displ)
        y(cty) {
            scale = continuous(8..34)
        }

        symbol = Symbol.CIRCLE_FILLED
        color = Color.GREY
        fillColor(drv)
        size(hwy) {
            scale = continuous(2.0..10.0)
        }
    }

    layout.size = 750 to 450

    facetWrap(nCol = 3, scalesSharing = ScalesSharing.FREE) {
        facet(drv)
        facet(cyl, order = OrderDirection.DESCENDING, format = "d")
    }
}
```

<!---END-->

![Plot Facet Grid Wrap](experimentalMultiplotFacetingWrap.svg)

### Statistics

Kandy offers a robust API for computing statistics directly within its DSL.
This functionality is achieved through the `stat` family of functions,
which transform raw data into a new dataset with calculated statistics.
These statistics become accessible as columns and can be mapped to various plot aesthetics, scaled,
or incorporated into tooltips.

Here's an example using a dataset of random observations:

<!---FUN experimentalStatisticsObservationsDataset-->

```kotlin
val random = kotlin.random.Random(42)

val observation = List(1000) { random.nextDouble() }
val observationDataset = dataFrameOf(
    "observations" to observation
)
```

<!---END-->

You can create statistical visualizations such as a bin plot with bars and lines:

<!---FUN experimentalStatisticsBinPlotWithBarsAndLines-->

```kotlin
plot(observationDataset) {
    statBin(obs) {
        bars {
            // Simple mapping
            x(Stat.x)
            // Mapping with the scale
            y(Stat.count) {
                scale = continuous(0..100, transform = Transformation.REVERSE)
            }

            alpha = 0.5

            // Formatting of stat value format
            tooltips {
                // Line with the name of stat (i.e. "count") on the left side and its value on the right side
                line(Stat.count, format = "d")
            }
        }

        line {
            x(Stat.x)
            y(Stat.count)

            width = 2.5
            color = Color.RED
        }
    }
}
```

<!---END-->

![Stats Bin with Bars and Lines](experimentalStatisticsBinPlotWithBarsAndLines.svg)

Kandy also simplifies the creation of standard statistical charts, like histograms,
by combining statistical calculations and layer creation into a single function:

<!---FUN experimentalStatisticsShortcutHistogram-->

```kotlin
val histPlot = plot(observationDataset) {
    histogram(obs)

    layout.title = "`histogram`"
}
histPlot
```

<!---END-->

![Simple Histogram](experimentalStatisticsShortcutHistogram.svg)

You can compare it to a bar chart with the calculation of bins stat:

<!---FUN experimentalStatisticsStatBinsAndHist-->

```kotlin
val binBarPlot = plot(observationDataset) {
    statBin(obs) {
        bars {
            x(Stat.x)
            y(Stat.count)
        }
    }
    layout.title = "`statBin` + `bar`"
}

plotGrid(listOf(histPlot, binBarPlot), 2)
```

<!---END-->

![StatBin Bars and Histogram](experimentalStatisticsStatBinsAndHist.svg)

The `histogram` in Kandy is flexible,
allowing for custom <tooltip term="aes">aesthetic</tooltip> bindings and the use of "stat-bin" statistics for mapping:

<!---FUN experimentalStatisticsConfiguredHistogram-->

```kotlin
plot(observationDataset) {
    histogram(obs, binsOption = BinsOption.byWidth(0.05), binsAlign = BinsAlign.boundary(0.1)) {
        y(Stat.density)

        fillColor(Stat.count) {
            scale = continuous(Color.GREEN..Color.RED)
        }

        borderLine {
            color = Color.BLACK
            width = 0.3
        }

        tooltips(title = "${value(Stat.x)} ± 0.025") {
            line(Stat.density)
        }
    }
}
```

<!---END-->

![Configured Histogram](experimentalStatisticsConfiguredHistogram.svg)

This statistical API is also compatible with `Iterable` data types,
expanding its applicability across various data structures:

<!---FUN experimentalStatisticsStatBinOnIterableData-->

```kotlin
plotGrid(listOf(
    plot {
        statBin(observation) {
            points {
                x(Stat.density)
                y(Stat.x)
            }
        }
    },
    plot {
        histogram(observation)
    }
), 2)
```

<!---END-->

![statBin and Histogram with Iterable Data](experimentalStatisticsStatBinOnIterableData.svg)

### Layout

#### Title, subtitle, caption, and size

You can personalize your plot's layout by configuring the title, subtitle, caption, and overall size.
The `layout` block provides direct access to these properties, making it easy to create visually compelling charts.

<!---FUN experimentalLayoutTitleSubtitleCapSize-->

```kotlin
mpgDF.plot {
    points {
        x(cty)
        y(hwy)
    }

    // Can access of layout parameters in this context
    layout {
        subtitle = "plot subtitle"
        caption = "plot caption \n important info"
        size = 800 to 600
    }
    // If you just want to put a title
    layout.title = "PLOT TITLE"
}
```

<!---END-->

![Plot with Title, Subtitle, Caption, and Size](experimentalLayoutTitleSubtitleCapSize.svg)

#### Themes

Themes in Kandy offer extensive customization options for your plot's appearance,
including styles for lines, text, backgrounds, and more.
You can use a pre-built theme or create your own custom theme.

To apply a `theme`:

<!---FUN experimentalLayoutWithClassicTheme-->

```kotlin
mpgDF.plot {
    points {
        x(cty)
        y(hwy)
    }
    layout.theme(Theme.Classic)
}
```

<!---END-->

![Plot with Classic Theme](experimentalLayoutWithClassicTheme.svg)

For example, creating a plot with different themes:

<!---FUN experimentalLayoutFunPlotWithTheme-->

```kotlin
fun plotWithTheme(theme: Theme? = null, title: String? = null): org.jetbrains.kotlinx.kandy.ir.Plot {
    return mpgDF.plot {
        points {
            x(cty)
            y(hwy)
        }
        layout {
            theme?.let {
                theme(it)
            }
            this.title = title
        }
    }
}
```

<!---END-->

<!---FUN experimentalLayoutPlotAllThemes-->

```kotlin
plotGrid(
    listOf(
        plotWithTheme(Theme.Classic, "\"Classic\" theme"),
        plotWithTheme(Theme.Grey, "\"Grey\" theme"),
        plotWithTheme(Theme.Light, "\"Light\" theme"),
        plotWithTheme(Theme.Minimal, "\"Minimal\" theme"),
        plotWithTheme(Theme.Minimal2, "\"Minimal2\" theme (by default)"),
        plotWithTheme(Theme.None, "\"None\" theme"),
    ), 2
)
```

<!---END-->

![Plot with All Themes](experimentalLayoutPlotAllThemes.svg)

##### Custom Themes

Kandy's DSL allows you to craft custom themes.
You can set parameters for lines, text, backgrounds, etc.,either separately or in-place.

Creating a simple custom theme:

<!---FUN experimentalLayoutSimpleCustomTheme-->

```kotlin
val redLine = LayoutParameters.line(Color.RED)

val simpleCustomTheme = theme {
    // use previously created parameters
    xAxis.line(redLine)
    // set up parameters
    yAxis.line {
        color = Color.RED
        width = 0.3
    }
    // remove ticks on both axes
    axis.ticks {
        blank = true
    }
}

plotWithTheme(simpleCustomTheme)
```

<!---END-->

![Plot with Simple Custom Theme](experimentalLayoutSimpleCustomTheme.svg)

Example of a theme with blank axes:

<!---FUN experimentalLayoutCustomThemeBlankAxes-->

```kotlin
val blankAxesTheme = theme {
    blankAxes()
}
plotWithTheme(blankAxesTheme)
```

<!---END-->

![Plot with Blank Axes](experimentalLayoutCustomThemeBlankAxes.svg)

### Custom scales

Kandy extends the versatility of scales beyond standard options,
providing custom color scales like `hue`, `grey`, `brewer`, `gradient2`, and `gradientN`.

Creating plots with different color scales:

<!---FUN experimentalCustomScalesCategoricalColor-->

```kotlin
mpgDF.plot {
    points {
        x(cty)
        y(hwy)
        size = 5.0
        color(drv) {
            scale = categoricalColorHue()
        }
    }
}
```

<!---END-->

![Plot with Categorical Color Scale](experimentalCustomScalesCategoricalColor.svg)

<!---FUN experimentalCustomScalesContinuousColorGrey-->

```kotlin
mpgDF.plot {
    points {
        x(cty)
        y(hwy)
        size = 5.0
        color(cty) {
            scale = continuousColorGrey()
        }
    }
}
```

<!---END-->

![Plot with Continuous Color Grey](experimentalCustomScalesContinuousColorGrey.svg)

<!---FUN experimentalCustomScalesContinuousColorGradient2-->

```kotlin
mpgDF.plot {
    points {
        x(cty)
        y(hwy)
        size = 5.0
        color(hwy) {
            scale = continuousColorGradient2(Color.BLUE, Color.ORANGE, Color.RED, 30.0)
        }
    }
}
```

<!---END-->

![Plot with Continuous Color Gradient2](experimentalCustomScalesContinuousColorGradient2.svg)

<!---FUN experimentalCustomScalesContinuousColorGradientN-->

```kotlin
mpgDF.plot {
    points {
        x(cty)
        y(hwy)
        size = 5.0
        color(cty) {
            scale = continuousColorGradientN(
                gradientColors = listOf(
                    Color.RED, Color.hex("#fa89c7"),
                    Color.rgb(200, 89, 12), Color.LIGHT_GREEN
                )
            )
        }
    }
}
```

<!---END-->

![Plot with Continuous Color GradientN](experimentalCustomScalesContinuousColorGradientN.svg)

<!---FUN experimentalCustomScalesCategoricalColorBrewer-->

```kotlin
mpgDF.plot {
    points {
        x(cty)
        y(hwy)
        size = 5.0
        color(drv) {
            scale = categoricalColorBrewer(BrewerPalette.Qualitative.Set1)
        }
    }
}
```

<!---END-->

![Plot with Categorical Color Brewer](experimentalCustomScalesCategoricalColorBrewer.svg)

### Tooltips

Tooltips in Kandy allow for an interactive exploration of data by displaying additional information about visual
objects.
These tooltips are established within each layer's context using the `tooltips()` method.

While tooltips are enabled by default, Kandy allows for their customization or complete deactivation.
This flexibility is achieved by adjusting the `hide` flag within the tooltips' settings:

<!---FUN experimentalTooltipsHideTooltips-->

```kotlin
mpgDF.plot {
    points {
        x(cty)
        y(hwy)
        color(drv)
        size = 3.5

        tooltips(isEnabled = false)
    }
}
```

<!---END-->

![Plot without Tooltips](experimentalTooltipsHideTooltips.svg)

Kandy offers extensive customization for tooltips,
enabling users to not only display data from specific columns but also format these data points for better clarity and
interpretation:

<!---FUN experimentalTooltipsByColumns-->

```kotlin
mpgDF.plot {
    points {
        x(cty)
        y(hwy)
        color(drv)
        size = 3.5

        tooltips(drv, cyl, displ, formats = mapOf(displ to ".1g"))
    }
}
```

<!---END-->

![Display Data from specific Columns in Tooltips](experimentalTooltipsByColumns.svg)

Kandy allows for further customization of tooltips, including title adjustment, minimum width settings,
and fixed positioning:

<!---FUN experimentalTooltipsWithTitleAnchorMinWidth-->

```kotlin
mpgDF.plot {
    points {
        x(cty)
        y(hwy)
        color(drv)
        size = 3.5

        tooltips(
            cyl, displ,
            title = "Car info",
            anchor = Anchor.TOP_RIGHT,
            minWidth = 80.0,
        )
    }
}
```

<!---END-->

![Tooltips with title, position, anchor, width](experimentalTooltipsWithTitleAnchorMinWidth.svg)

For more detailed tooltip configurations, Kandy offers a special DSL.
This DSL enables manual adjustments of tooltip lines,
allowing users to add specific data values and customize the layout:


<!---FUN experimentalTooltipsCustomizedWithLine-->

```kotlin
mpgDF.plot {
    points {
        x(cty)
        y(hwy)
        color(drv)
        size = 3.5

        tooltips(
            // use column values in the title
            title = "${value(manufacturer)} ${value(model)}",
        ) {
            // standard line with column name and value
            line(displ)
            // solid line
            line(trans.tooltipValue())
            // two-sided line
            line("cty/hwy [mpg]", "${cty.tooltipValue()}/${hwy.tooltipValue()}")
        }
    }
}
```

<!---END-->

![Customizing Tooltips](experimentalTooltipsCustomizedWithLine.svg)

### Export

Kandy's plotting library provides an efficient way to export your plots as images in various formats,
including **.jpg/.jpeg**, **.png**, **.html**, and **.svg**.
This feature is facilitated by the `save()` extension method.

First, create a plot that you wish to export. Here's an example plot with some basic configurations:

<!---FUN experimentalExportPlotForExport-->

```kotlin
val plotForExport = mpgDF.plot {
    points {
        x(cty)
        y(hwy)
        color(drv)
        size = 3.5
    }
    layout {
        title = "Plot for export"
        size = 600 to 400
    }
}
plotForExport
```

<!---END-->

![Plot For Export](experimentalExportPlotForExport.svg)

Once your plot is ready, you can export it as an image file.
The `save()` method allows you to specify the file format, scale, and dpi, and the path where the image will be saved:

```kotlin
val pathPNG = plotForExport.save("plot.png", scale = 2.5, dpi = 9000, path = "./saved_plots")
```

To view the exported image, you can use the following code snippet:

```kotlin
javax.imageio.ImageIO.read(java.io.File(pathPNG))
```

![Import Plot](experimentalExportPlotForExport.svg)


In addition to saving as a file, you can also export the plot as a `BufferedImage`.
This is particularly useful for further manipulation or display within a Kotlin application:

```kotlin
plotForExport.toBufferedImage(scale = 2.5, dpi = 9000)
```

![BufferedImage Plot](experimentalExportPlotForExport.svg)

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/quick_start_guide.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/TKul6gxAVDbJmocwQi9UjB" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
