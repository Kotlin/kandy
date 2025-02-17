# Legend and Axis

<web-summary>
Refine your plots with Kandy's Legend and Axis guide.
Learn to manipulate legends and axes for improved readability and aesthetics in your data visualizations.
</web-summary>

<card-summary>
Enhance plot legibility with Kandy's Legend and Axis guide.
Gain insights on customizing legends and axes for clearer, more impactful data presentations.
</card-summary>

<link-summary>
Dive into the nuances of plot customization with Kandy's Legend and Axis guide. 
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.LegendAndAxis-->

<!---FUN guideLegendAxisReadData-->

```kotlin
val mpgDf =
    DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
mpgDf.head()
```

<!---END-->

| untitled | manufacturer | model | displ | year | cyl | trans        | drv | cty | hwy | fl | class   |
|:---------|:-------------|:------|:------|:-----|:----|:-------------|:----|:----|:----|:---|:--------|
| 1        | audi         | a4    | 18    | 1999 | 4   | auto\(l5\)   | f   | 18  | 29  | p  | compact |
| 2        | audi         | a4    | 18    | 1999 | 4   | manual\(m5\) | f   | 21  | 29  | p  | compact |
| 3        | audi         | a4    | 2     | 2008 | 4   | manual\(m6\) | f   | 20  | 31  | p  | compact |
| 4        | audi         | a4    | 2     | 2008 | 4   | auto\(av\)   | f   | 21  | 30  | p  | compact |
| 5        | audi         | a4    | 28    | 1999 | 6   | auto\(l5\)   | f   | 16  | 26  | p  | compact |

<!---FUN guideLegendAxisDefaultLegend-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(manufacturer)
        size = 5.0
    }
    layout.size = 600 to 250
}
```

<!---END-->

![Plot with Default Legend](guideLegendAxisDefaultLegend.svg)

<!---FUN guideLegendAxisTwoColumnsLegend-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(manufacturer) {
            legend.type = LegendType.DiscreteLegend(nCol = 2)
        }
        size = 5.0
    }
    layout {
        title = "Two columns legend"
        size = 600 to 250
    }
}
```

<!---END-->

![Legend with Two Columns](guideLegendAxisTwoColumnsLegend.svg)

<!---FUN guideLegendAxisFillingLegendCols-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(manufacturer) {
            legend.type = LegendType.DiscreteLegend(nCol = 2, byRow = true)
        }
        size = 5.0
    }
    layout {
        title = "Two columns legend filled by rows"
        size = 600 to 250
    }
}
```

<!---END-->

![Filling Legend by Rows](guideLegendAxisFillingLegendCols.svg)

<!---FUN guideLegendAxisBottomLegend-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(manufacturer) {
            legend.type = LegendType.DiscreteLegend(nRow = 5)
        }
        size = 5.0
    }
    layout {
        title = "Five rows legend and below"
        size = 700 to 400
        style {
            axis.title { blank = true }
            legend.position = LegendPosition.Bottom
        }
    }
}
```

<!---END-->

![Bottom Legend](guideLegendAxisBottomLegend.svg)

<!---FUN guideLegendAxisContinuousLegend-->

```kotlin
mpgDf.plot {
    x(displ)
    y(hwy)
    points {
        size = 5.0
        color(cty)
        symbol(drv)
    }
    layout.size = 700 to 350
}
```

<!---END-->

![Continuous Legend](guideLegendAxisContinuousLegend.svg)

<!---FUN guideLegendAxisLegendHorizontalDirection-->

```kotlin
mpgDf.plot {
    x(displ)
    y(hwy)
    points {
        size = 5.0
        color(cty)
        symbol(drv)
    }
    layout {
        size = 700 to 350
        style {
            legend {
                justification(1.0, 1.0)
                position(1.0, 1.0)
                direction = LegendDirection.HORIZONTAL
            }
        }
    }
}
```

<!---END-->

![Legend with Horizontal Direction](guideLegendAxisLegendHorizontalDirection.svg)

<!---FUN guideLegendAxisConfigureLegend-->

```kotlin
mpgDf.plot {
    x(displ)
    y(hwy)
    points {
        size = 5.0
        color(cty) {
            scale = continuous(range = Color.named("dark_blue")..Color.named("light_blue"))
            legend {
                name = "City MPG"
                type = LegendType.ColorBar(barHeight = 10.0, barWidth = 300.0)
            }
        }
        symbol(drv) {
            legend {
                name = "Drive-train"
                breaksLabeled('f' to "front", 'r' to "rear", '4' to "4X4")
            }
        }
    }
    x.axis.name = "Engine displacement (L)"
    y.axis.name = "Highway MPG"
    layout {
        size = 700 to 350
        style {
            yAxis.line { blank = true }
            legend {
                justification(1.0, 1.0)
                position(1.0, 1.0)
                direction = LegendDirection.HORIZONTAL
            }
        }
    }
}
```

<!---END-->

![Configured Legend](guideLegendAxisConfigureLegend.svg)



<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/legend_and_axis.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/R73h4ihwEqtyjlnYRowPUy" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
