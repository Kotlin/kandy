# ErrorBars

<web-summary>
Explore the versatility of Error Bars in Kotlin's Kandy library.
This guide presents a comprehensive approach to depicting data variability and precision in your visualizations,
enhancing the analytical depth of your charts.
</web-summary>

<card-summary>
Error Bars in Kandy: A detailed guide to effectively representing data variability and precision in Kotlin,
enriching your graphical data analysis.
</card-summary>

<link-summary>
Enhance your charts with Error Bars in Kandy, showcasing data variability and precision in Kotlin-based visualizations.
</link-summary>

### Plotting means and error ranges.

There are several ways to show error ranges on a plot. Among them are

* `errorBars`
* `crossBars`
* `lineRanges`
* `pointRanges`

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.ErrorBars-->

<!---FUN guideErrorBarsData-->

```kotlin
// This example was found at: www.cookbook-r.com/Graphs/Plotting_means_and_error_bars_(ggplot2)
val supp by columnOf("OJ", "OJ", "OJ", "VC", "VC", "VC")
val dose by columnOf(0.5, 1.0, 2.0, 0.5, 1.0, 2.0)
val length by columnOf(13.23, 22.70, 26.06, 7.98, 16.77, 26.14)
val len_min by columnOf(11.83, 21.2, 24.50, 4.24, 15.26, 23.35)
val len_max by columnOf(15.63, 24.9, 27.11, 10.72, 19.28, 28.93)
val dataset = dataFrameOf(supp, dose, length, len_min, len_max)
```

<!---END-->

## Error Bars with lines and points

<!---FUN guideErrorBarsWithLines-->

```kotlin
plot(dataset) {
    x(dose)
    errorBars {
        yMin(len_min)
        yMax(len_max)
        borderLine.color(supp)

        width = .1
    }
    line {
        y(length)
        color(supp)
    }
    points {
        y(length)
        color(supp)
    }
}
```

<!---END-->

![Error Bars with Lines](guideErrorBarsWithLines.svg)

<!---FUN guideErrorBarsWithLinesAndPosition-->

```kotlin
val posD = Position.dodge(0.1)
plot(dataset) {
    x(dose)

    errorBars {
        yMin(len_min)
        yMax(len_max)
        borderLine.color(supp)

        width = .1
        position = posD
    }

    line {
        y(length)
        color(supp)

        position = posD
    }

    points {
        y(length)
        color(supp)

        position = posD
    }
}
```

<!---END-->

![Error Bars with Lines and Position](guideErrorBarsWithLinesAndPosition.svg)

<!---FUN guideErrorBarsWithLinesGrouped-->

```kotlin
plot(dataset) {
    x(dose)
    groupBy(supp) {
        errorBars {
            yMin(len_min)
            yMax(len_max)

            borderLine.color = Color.BLACK
            width = .1
            position = posD
        }
    }


    line {
        y(length)
        color(supp)

        position = posD
    }

    points {
        y(length)
        color(supp)

        size = 5.0
        position = posD
    }
}
```

<!---END-->

![Error Bars with Grouped Lines](guideErrorBarsWithLinesGrouped.svg)

<!---FUN guideErrorBarsCustomColorScale-->

```kotlin
val customColorScale = Scale.categorical<Color, String>(
    range = listOf(Color.ORANGE, Color.named("dark_green"))
)
```

<!---END-->

<!---FUN guideErrorBarsWithLinesCustomColorScale-->

```kotlin
plot(dataset) {
    x(dose)

    groupBy(supp) {
        errorBars {
            yMin(len_min)
            yMax(len_max)

            borderLine.color = Color.BLACK
            width = .1
            position = posD
        }
    }

    line {
        y(length)
        color(supp) { scale = customColorScale }

        position = posD
    }

    points {
        y(length)
        color(supp) { scale = customColorScale }

        symbol = Symbol.CIRCLE_FILLED
        size = 5.0
        fillColor = Color.WHITE
        position = posD
    }

    layout {
        title = "The Effect of Vitamin C on Tooth Growth in Guinea Pigs"
        size = 700 to 400
        xAxisLabel = "Dose (mg)"
        yAxisLabel = "Tooth length (mm)"

        theme {
            legend {
                justification(1.0, 0.0)
                position(1.0, 0.0)
            }
        }
    }

}
```

<!---END-->

![Error Bars with Custom Color Scale](guideErrorBarsWithLinesCustomColorScale.svg)

## Error Bars on bar plot

<!---FUN guideErrorBarsOnBars-->

```kotlin
plot(dataset) {
    x(dose)

    bars {
        y(length)
        fillColor(supp) { scale = customColorScale }

        borderLine.color = Color.BLACK
        position = Position.dodge()
    }

    groupBy(supp) {
        errorBars {
            yMin(len_min)
            yMax(len_max)

            borderLine.color = Color.BLACK
            width = .1
            position = Position.dodge(0.9)
        }
    }


    layout {
        size = 700 to 400
        xAxisLabel = "Dose (mg)"
        yAxisLabel = "Tooth length (mm)"

        theme {
            legend {
                justification(0.0, 1.0)
                position(0.0, 1.0)
            }
        }
    }
}
```

<!---END-->

![Error Bars on Bar Plot](guideErrorBarsOnBars.svg)

## Crossbars

<!---FUN guideErrorBarsCrossbars-->

```kotlin
plot(dataset) {
    x(dose)

    crossBars {
        yMin(len_min)
        yMax(len_max)
        y(length)
        borderLine.color(supp) { scale = customColorScale }

        fatten = 5.0
        position = Position.dodge(0.95)
    }

    layout {
        size = 700 to 400
        xAxisLabel = "Dose (mg)"
        yAxisLabel = "Tooth length (mm)"
    }
}
```

<!---END-->

![Error Bars with Crossbars](guideErrorBarsCrossbars.svg)

## LineRanges

<!---FUN guideErrorBarsLineRanges-->

```kotlin
plot(dataset) {
    x(dose)

    lineRanges {
        yMin(len_min)
        yMax(len_max)
        borderLine.color(supp) { scale = customColorScale }

        position = posD
    }

    line {
        y(length)
        color(supp) { scale = customColorScale }

        position = posD
    }

    layout {
        size = 700 to 400
        xAxisLabel = "Dose (mg)"
        yAxisLabel = "Tooth length (mm)"
    }
}
```

<!---END-->

![Error Bars with LineRanges](guideErrorBarsLineRanges.svg)

## PointRanges

<!---FUN guideErrorBarsPointRanges-->

```kotlin
plot(dataset) {
    x(dose)

    pointRanges {
        y(length)
        yMin(len_min)
        yMax(len_max)
        color(supp) { scale = customColorScale }

        position = posD
    }

    line {
        y(length)
        color(supp) { scale = customColorScale }

        position = posD
    }

    layout {
        size = 700 to 400
        xAxisLabel = "Dose (mg)"
        yAxisLabel = "Tooth length (mm)"
    }
}
```

<!---END-->

![Error Bars with PointRanges](guideErrorBarsPointRanges.svg)

<!---FUN guideErrorBarsConfiguredPointRanges-->

```kotlin
plot(dataset) {
    x(dose)

    line {
        y(length)
        color(supp) { scale = customColorScale }

        position = posD
    }

    pointRanges {
        y(length)
        yMin(len_min)
        yMax(len_max)

        innerPoint {
            fatten = 1.0
            symbol = Symbol.DIAMOND_FILLED
            fillColor(supp) { scale = customColorScale }
        }

        size = 5.0
        color = Color.rgb(230, 230, 230)
        position = posD
    }

    layout {
        size = 700 to 400
        xAxisLabel = "Dose (mg)"
        yAxisLabel = "Tooth length (mm)"
    }
}
```

<!---END-->

![Error Bars with Configured PointRanges](guideErrorBarsConfiguredPointRanges.svg)

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/error_bars.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/Sq7CLBITOQPqlAyu54Nx02" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
