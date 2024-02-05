# Plot Bunch

<web-summary>
Explore flexible data visualization with Kandy's Plot Bunch guide.
Learn to arrange multiple plots in a single display, tailoring their size and position for impactful presentations.
</web-summary>

<card-summary>
Kandy's Plot Bunch guide offers innovative ways to display multiple plots.
Customize your data visualization with adjustable sizes and positions for maximum impact.
</card-summary>

<link-summary>
Dive into the world of custom plot arrangements with Kandy's Plot Bunch guide.
Perfect the art of displaying multiple plots in one view, with full control over their layout.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.PlotBunch-->

`plotBunch()` allows showing a collection of plots on one figure.
Each plot in the collection can have an arbitrary location and size.
There is no automatic layout inside the bunch.

<!---FUN guideBunchGenerateData-->

```kotlin
val cov: Array<DoubleArray> = arrayOf(
    doubleArrayOf(1.0, 0.0),
    doubleArrayOf(0.0, 1.0)
)
val means: DoubleArray = doubleArrayOf(0.0, 0.0)
val xy = MultivariateNormalDistribution(means, cov).sample(400)

val xs = xy.map { it[0] }
val ys = xy.map { it[1] }
```

<!---END-->

### View this data as a scatter plot and as a histogram

<!---FUN guideBunchScatterPlot-->

```kotlin
plot {
    points {
        x(xs)
        y(ys)
        color = Color.GREY
        alpha = .4
    }
    layout {
        size = 600 to 200
        xAxisLabel = "x"
        yAxisLabel = "y"
    }
}
```

<!---END-->

![Scatter Plot](guideBunchScatterPlot.svg)

<!---FUN guideBunchHistogramPlot-->

```kotlin
plot {
    histogram(xs) {
        fillColor = Color.named("dark_magenta")
    }
    layout {
        size = 600 to 200
        xAxisLabel = "x"
    }
}
```

<!---END-->

![Histogram Plot](guideBunchHistogramPlot.svg)

### Combine both plots in one figure

<!---FUN guideBunchCombineScatterAndHistogram-->

```kotlin
val scaleX = Scale.continuousPos(-3.5..3.5)
plotBunch {
    add(plot {
        histogram(xs) {
            x { scale = scaleX }
            fillColor = Color.named("dark_magenta")
        }
        layout {
            size = 600 to 200
            xAxisLabel = "x"
        }
    }, 0, 0)
    add(plot {
        points {
            x(xs) { scale = scaleX }
            y(ys)
            color = Color.GREY
            alpha = .4
        }
        layout {
            size = 600 to 200
            xAxisLabel = "x"
            yAxisLabel = "y"
        }
    }, 0, 200)
}
```

<!---END-->

![Combine Scatter and Histogram](guideBunchCombineScatterAndHistogram.svg)

### Adjust visuals of the bunch figure

<!---FUN guideBunchCreateThemes-->

```kotlin
val upperTheme = style {
    blankAxes()
    axis.text { }
    yAxis.title { }
    panel.grid.majorYLine {
        blank = true
    }
}
val lowerTheme = style {
    blankAxes()
    yAxis.text { }
    axis.title { }
}
```

<!---END-->


<!---FUN guideBunchCombineWithThemes-->

```kotlin
plotBunch {
    add(plot {
        histogram(xs) {
            x { scale = scaleX }
            fillColor = Color.named("dark_magenta")
        }
        layout {
            size = 600 to 200
            xAxisLabel = "x"
            style(upperTheme)
        }
    }, 0, 0)
    add(plot {
        points {
            x(xs) { scale = scaleX }
            y(ys)
            color = Color.GREY
            alpha = .4
        }
        layout {
            size = 600 to 200
            xAxisLabel = "x"
            yAxisLabel = "y"
            style(lowerTheme)
        }
    }, 0, 200)
}
```

<!---END-->

![Plot Bunch with Themes](guideBunchCombineWithThemes.svg)

### Adjust plot sizes

`add` method has two more (optional) parameters: `width` and `height`.

These values will override the plot size earlier defined via *size* property.

<!---FUN guideBunchConfigureSizes-->

```kotlin
plotBunch {
    add(plot {
        histogram(xs) {
            x { scale = scaleX }
            fillColor = Color.named("dark_magenta")
        }
        layout {
            size = 600 to 200
            xAxisLabel = "x"
            style(upperTheme)
        }
    }, 0, 0, 600, 100)
    add(plot {
        points {
            x(xs) { scale = scaleX }
            y(ys)
            color = Color.GREY
            alpha = .4
        }
        layout {
            size = 600 to 200
            xAxisLabel = "x"
            yAxisLabel = "y"
            style(lowerTheme)
        }
    }, 0, 100, 600, 300)
}
```

<!---END-->

![Configured Sizes](guideBunchConfigureSizes.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/plot_bunch.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/oL0VY2neMbB7xKITtPQzaw" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
