# Scatter

<web-summary>
Primary the intricacies of scatter plotting in Kotlin with the Kandy Scatter Guide.
This comprehensive resource helps you create detailed and insightful scatter plots,
revealing hidden patterns and correlations in your data.
</web-summary>

<card-summary>
Enhance your data storytelling with the Kandy Scatter Guide for Kotlin.
Dive into creating expressive scatter plots that illuminate relationships and trends in complex datasets.
</card-summary>

<link-summary>
Unlock the potential of scatter plots in Kotlin using the Kandy Scatter Guide.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.Scatter-->

<!---FUN guideScatterData-->

```kotlin
// This example was found at:
// www.cookbook-r.com/Graphs/Scatterplots_(ggplot2)
val rand = java.util.Random(123)
val n = 20
val dataset = dataFrameOf(
    "cond" to List(n / 2) { "A" } + List(n / 2) { "B" },
    "xvar" to List(n) { i: Int -> i },
    "yvar" to List(n) { i: Int -> i + rand.nextGaussian() * 3 }
)
val cond = "cond"<String>()
val xvar = "xvar"<Int>()
val yvar = "yvar"<Double>()
```

<!---END-->

## Basic Scatter Plot

<!---FUN guideScatterBasicScatterPlot-->

```kotlin
plot(dataset) {
    points {
        x(xvar)
        y(yvar)
        symbol = Symbol.CIRCLE_OPEN
    }
}
```

<!---END-->

![Basic Scatter Plot](guideScatterBasicScatterPlot.svg)

<!---FUN guideScatterScatterPlotWithDiffSymbol-->

```kotlin
plot(dataset) {
    points {
        x(xvar)
        y(yvar)
        color(cond)
        symbol(cond)
        size = 5.0
    }
    layout {
        size = 700 to 350
    }
}
```

<!---END-->

![Scatter Plot with Symbols](guideScatterScatterPlotWithDiffSymbol.svg)

<!---FUN guideScatterScatterPlotWithOpenSymbol-->

```kotlin
plot(dataset) {
    points {
        x(xvar)
        y(yvar)
        color(cond)
        symbol(cond) {
            scale = categorical(range = listOf(Symbol.CIRCLE_OPEN, Symbol.TRIANGLE_OPEN))
        }
        size = 5.0
    }
    layout {
        size = 700 to 350
    }
}
```

<!---END-->

![Scatter Plot with Open Symbols](guideScatterScatterPlotWithOpenSymbol.svg)

### Handling Over-plotting

<!---FUN guideScatterDatasetOverlapping-->

```kotlin
// Create data with overlapping points.
val datasetOverlapping = dataset.convert { xvar and yvar }.with {
    (it.toDouble() / 5).toInt() * 5
}
```

<!---END-->

<!---FUN guideScatterHandlingOverPlotting-->

```kotlin
plot(datasetOverlapping) {
    points {
        x(xvar) {
            axis.breaks(listOf(0, 5, 10, 15))
        }
        y(yvar)
        alpha = .3
        size = 7.0
    }
    layout {
        size = 700 to 350
    }
}
```

<!---END-->

![Scatter Plot Over-Plotting](guideScatterHandlingOverPlotting.svg)

<!---FUN guideScatterHandlingOverPlottingJitter-->

```kotlin
plot(datasetOverlapping) {
    points {
        x(xvar) {
            axis.breaks(listOf(0, 5, 10, 15))
        }
        y(yvar)
        symbol = Symbol.CIRCLE_OPEN

        position = Position.jitter(.1, .1)
    }
    layout {
        size = 700 to 350
    }
}
```

<!---END-->

![Scatter Plot with Jitter](guideScatterHandlingOverPlottingJitter.svg)

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/scatter.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/p6dtdln0YInK4dwtOY5fWs" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
