# Styles

<web-summary>
Unleash the power of visual styling with Kandy's Styles guide.
Explore a variety of styles to add a professional and aesthetically pleasing touch to your data visualizations.
</web-summary>

<card-summary>
Transform your data plots with Kandy's Styles guide.
Learn how to apply different styles to enhance the visual appeal and clarity of your charts.
</card-summary>

<link-summary>
Step into the world of thematic data visualization with Kandy's Styles guide.
Discover how to select and apply styles for more engaging and informative charts.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.Styles-->

## Preparation

<!---FUN guideStylesReadData-->

```kotlin
val df =
    DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
df.head(3)
```

<!---END-->

| untitled | manufacturer | model | displ | year | cyl | trans        | drv | cty | hwy | fl | class   |
|:---------|:-------------|:------|:------|:-----|:----|:-------------|:----|:----|:----|:---|:--------|
| 1        | audi         | a4    | 18    | 1999 | 4   | auto\(l5\)   | f   | 18  | 29  | p  | compact |
| 2        | audi         | a4    | 18    | 1999 | 4   | manual\(m5\) | f   | 21  | 29  | p  | compact |
| 3        | audi         | a4    | 2     | 2008 | 4   | manual\(m6\) | f   | 20  | 31  | p  | compact |

<!---FUN guideStylesPointPlotWithoutStyle-->

```kotlin
fun pointPlotWithStyle(
    name: String,
    baseStyle: Style = Style.Minimal2,
    customStyleBuilder: CustomStyle.() -> Unit = {}
) =
    df.plot {
        points {
            x(cty)
            y(hwy)
        }
        layout {
            title = name
            style(baseStyle, customStyleBuilder)
        }
    }

df.plot {
    points {
        x(cty)
        y(hwy)
    }
}
```

<!---END-->

![Point Plot without Style](guideStylesPointPlotWithoutStyle.svg)

<!---FUN guideStylesCountFlDf-->

```kotlin
val bPlotDf = df.groupBy { fl }.count()
```

<!---END-->

<!---FUN guideStylesBarPlotWithoutStyle-->

```kotlin
fun barPlotWithStyle(
    name: String,
    baseStyle: Style = Style.Minimal2,
    customStyleBuilder: CustomStyle.() -> Unit = {}
) =
    bPlotDf.plot {
        bars {
            x(fl)
            y(count)
            fillColor(fl)
        }
        layout {
            title = name
            style(baseStyle, customStyleBuilder)
        }
    }

bPlotDf.plot {
    bars {
        x(fl)
        y(count)
        fillColor(fl)
    }
}
```

<!---END-->

![Bar Plot without Style](guideStylesBarPlotWithoutStyle.svg)

<!---FUN guideStylesCountFlAndYearDf-->

```kotlin
val fPlotDf = df.groupBy { fl and year }.count()
```

<!---END-->


<!---FUN guideStylesBarsFacetGridX-->

```kotlin
fun barFacetPlotWithStyle(
    name: String,
    baseStyle: Style = Style.Minimal2,
    customStyleBuilder: CustomStyle.() -> Unit = {}
) =
    fPlotDf.plot {
        bars {
            x(fl)
            y(count)
            fillColor(fl)
        }
        facetGridX(year)
        layout {
            title = name
            style(baseStyle, customStyleBuilder)
        }
    }

fPlotDf.plot {
    bars {
        x(fl)
        y(count)
        fillColor(fl)
    }
    facetGridX(year)
}
```

<!---END-->

![Bar Facet Grid X Plot without Style](guideStylesBarsFacetGridX.svg)

### Elements

<!---FUN guideStylesUtilElements-->

```kotlin
val eLine = LayoutParameters.line(color = Color.RED, width = 4.0)
val eLine2 = LayoutParameters.line(color = Color.hex("#fcae91"), width = 1.0)
val eLine3 = LayoutParameters.line(width = 4.0)
val eBackground = LayoutParameters.background(
    borderLineColor = Color.hex("#2c7fb8"),
    fillColor = Color.hex("#edf8b1"),
    borderLineWidth = 2.0
)
val eText = LayoutParameters.text(color = Color.hex("#31a354"))
val (w, h) = listOf(400) to listOf(300)
```

<!---END-->

## Styles

<!---FUN guideStylesAllPrepStyles-->

```kotlin
plotGrid(
    listOf(
        pointPlotWithStyle("Minimal2 Style - by default"),
        pointPlotWithStyle("Light Style", Style.Light),
        pointPlotWithStyle("Classic Style", Style.Classic),
        pointPlotWithStyle("Grey Style", Style.Grey),
        pointPlotWithStyle("Minimal Style", Style.Minimal),
        pointPlotWithStyle("None Style", Style.None)
    ),
    nCol = 2,
    fit = true
)
```

<!---END-->

![All Default Styles](guideStylesAllPrepStyles.svg)

## Custom Style

<!---FUN guideStylesOrangeCustomStyle-->

```kotlin
val yellowLight = Color.hex("#ffffcc")
val orangeDark = Color.hex("#7f2704")
val orangeNormal = Color.hex("#f16913")
val orangeLight = Color.hex("#fff5eb")

val styleOrangeConstructor: CustomStyle.() -> Unit = {
    global {
        line {
            color = orangeNormal
            width = 2.0
        }
        background {
            borderLineColor = orangeNormal
            fillColor = orangeLight
            borderLineWidth = 2.0
        }
        text {
            color = orangeDark
        }
    }
    axis {
        ticks {
            color = orangeNormal
            width = 1.0
        }
        ticksLength = 7.0
        onTop = true
    }
    legend {
        background {
            borderLineWidth = 1.0
        }
        position = LegendPosition.Bottom
    }
    panel.grid {
        majorLine {
            color = orangeNormal
            width = .5
        }
        minorLine {
            blank = true
        }
    }
    plotCanvas.background {
        fillColor = yellowLight
        borderLineWidth = 1.0
    }
    axis.tooltip.background {
        borderLineColor = orangeDark
    }
}

plotGrid(
    listOf(
        pointPlotWithStyle("Scatter plot", Style.None, styleOrangeConstructor),
        barPlotWithStyle("Bar plot", Style.None, styleOrangeConstructor)
    ),
    nCol = 2,
    fit = true
)
```

<!---END-->

![Orange Custom Style](guideStylesOrangeCustomStyle.svg)

<!---FUN guideStylesCustomStyleForLegend-->

```kotlin
barPlotWithStyle("Place legend") {
    legend {
        position(1.0, 1.0)
        justification(1.0, 1.0)
        direction = LegendDirection.HORIZONTAL
    }
}
```

<!---END-->

![Custom Legend](guideStylesCustomStyleForLegend.svg)

### Axis Tooltip

<!---FUN guideStylesAxisTooltip-->

```kotlin
plotGrid(
    listOf(
        pointPlotWithStyle("blank tooltip x-axis") {
            xAxis.tooltip.background {
                blank = true
            }
        },
        pointPlotWithStyle("background tooltip x-axis") {
            xAxis.tooltip.background(eBackground)
        }
    ),
    nCol = 2
)
```

<!---END-->

![Axis Tooltip](guideStylesAxisTooltip.svg)

### Line, Background, Text

<!---FUN guideStylesConfigureGridLine-->

```kotlin
plotGrid(
    listOf(
        pointPlotWithStyle("Default"),
        pointPlotWithStyle("Configured grid line") {
            global.line(eLine)
        }
    ),
    nCol = 2
)
```

<!---END-->

![Configured Grid Line](guideStylesConfigureGridLine.svg)

<!---FUN guideStylesConfigureBackgroundGrid-->

```kotlin
plotGrid(
    listOf(
        barPlotWithStyle("None style", Style.None),
        barPlotWithStyle("None style + Rect", Style.None) {
            global.background(eBackground)
        }
    ),
    nCol = 2
)
```

<!---END-->

![Configured Background Grid](guideStylesConfigureBackgroundGrid.svg)

<!---FUN guideStylesTextLegendStrip-->

```kotlin
plotGrid(
    listOf(
        barFacetPlotWithStyle("Default"),
        barFacetPlotWithStyle("Text") { global.text(eText) },
        barFacetPlotWithStyle("Legend text") { legend.text(eText) },
        barFacetPlotWithStyle("Strip text") { strip.text(eText) }
    ),
    nCol = 2
)
```

<!---END-->

![Custom Text 1](guideStylesTextLegendStrip.svg)

<!---FUN guideStylesTextTitleLegend-->

```kotlin
plotGrid(
    listOf(
        barPlotWithStyle("Default"),
        barPlotWithStyle("Text") { global.text(eText) },
        barPlotWithStyle("Plot title") { plotCanvas.title(eText) },
        barPlotWithStyle("Legend title") { legend.title(eText) }
    ),
    nCol = 2
)
```

<!---END-->

![Custom Text 2](guideStylesTextTitleLegend.svg)

<!---FUN guideStylesBlankAxis-->

```kotlin
plotGrid(
    listOf(
        pointPlotWithStyle("Default"),
        pointPlotWithStyle("Blank axis") {
            axis.line {
                blank = true
            }
        }
    ),
    nCol = 2
)
```

<!---END-->

![Blank Axis](guideStylesBlankAxis.svg)

<!---FUN guideStylesBackgroundPanel-->

```kotlin
plotGrid(
    listOf(
        pointPlotWithStyle("Default"),
        pointPlotWithStyle("Panel background") {
            panel.background(eBackground)
        }
    ),
    nCol = 2
)
```

<!---END-->

![Bakground Panel](guideStylesBackgroundPanel.svg)

<!---FUN guideStylesPanelGrid-->

```kotlin
plotGrid(
    listOf(
        barPlotWithStyle("Blank panel grid") {
            panel.grid.lineGlobal {
                blank = true
            }
        },
        pointPlotWithStyle("Line panel grid") {
            panel.grid.lineGlobal(eLine)
        },
        pointPlotWithStyle("Major line panel grid\n Minor line panel grid") {
            panel.grid {
                majorLine(eLine)
                minorLine(eLine2)
            }
        },
        barPlotWithStyle("Blank major x-line panel grid\n major y-line panel grid\n minor y-line panel grid") {
            panel.grid {
                majorXLine {
                    blank = true
                }
                majorYLine(eLine)
                minorYLine(eLine2)
            }
        }
    ),
    nCol = 2
)
```

<!---END-->

![Panel Grid](guideStylesPanelGrid.svg)

<!---FUN guideStylesStripBackground-->

```kotlin
plotGrid(
    listOf(
        barFacetPlotWithStyle("Grey Style", Style.Grey),
        barFacetPlotWithStyle("Strip background") { strip.background(eBackground) },
        barFacetPlotWithStyle("Blank strip background") {
            strip.background {
                blank = true
            }
        },
        barFacetPlotWithStyle("Blank strip text") {
            strip.text {
                blank = true
            }
        }
    ),
    nCol = 2
)
```

<!---END-->

![Background Strip](guideStylesStripBackground.svg)

<!---FUN guideStylesOnTopXAxis-->

```kotlin
plotGrid(
    listOf(
        barPlotWithStyle("x-axis line") { xAxis.line(eLine3) },
        barPlotWithStyle("On top x-axis line") {
            xAxis {
                line(eLine3)
                onTop = true
            }
        }
    ),
    nCol = 2
)
```

<!---END-->

![Configured X-Axis Line](guideStylesOnTopXAxis.svg)

### Miscellaneous

<!---FUN guideStylesCoordFlip-->

```kotlin
plotGrid(
    listOf(
        fPlotDf.plot {
            bars {
                x(fl)
                y(count)
                fillColor(fl)
            }
            coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
            layout {
                title = "Default"
            }
        },
        fPlotDf.plot {
            bars {
                x(fl)
                y(count)
                fillColor(fl)
            }
            coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
            layout {
                title = "Plot 1"
                style {
                    global {
                        line(eLine)
                        text(eText)
                    }
                }
            }
        },
        fPlotDf.plot {
            bars {
                x(fl)
                y(count)
                fillColor(fl)
            }
            coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
            layout {
                title = "Plot 2"
                style {
                    axis {
                        line(eLine)
                        onTop = true
                    }
                    legend.title(eText)
                }
            }
        },
        fPlotDf.plot {
            bars {
                x(fl)
                y(count)
                fillColor(fl)
            }
            coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
            layout {
                title = "Plot 3"
                style {
                    plotCanvas.title(eText)
                    panel {
                        background(eBackground)
                        grid {
                            majorXLine {
                                blank = true
                            }
                            minorXLine {
                                blank = true
                            }
                            majorYLine(eLine)
                            minorYLine(eLine2)
                        }
                    }
                }
            }
        },
        fPlotDf.plot {
            bars {
                x(fl)
                y(count)
                fillColor(fl)
            }
            coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
            layout {
                title = "Plot 4"
                style {
                    strip {
                        background(eBackground)
                        text(eText)
                    }
                }
            }
        },
        fPlotDf.plot {
            bars {
                x(fl)
                y(count)
                fillColor(fl)
            }
            coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
            layout {
                title = "Plot 5"
                style {
                    xAxis {
                        text(eText)
                        title(eText)
                        ticks(eLine)
                        line(eLine2)
                        tooltip.background {
                            blank = true
                        }
                    }
                }
            }
        }
    ),
    nCol = 2
)
```

<!---END-->

![Miscellaneous](guideStylesCoordFlip.svg)

<!---FUN guideStylesTooltipAndBackground-->

```kotlin
plotGrid(
    listOf(
        df.plot {
            points {
                x(cty)
                y(hwy)
                color(fl)

                tooltips(anchor = Anchor.TOP_CENTER, minWidth = 50.0) {}
            }
            layout.title = "Tooltip: top-center"
        },
        df.plot {
            points {
                x(cty)
                y(hwy)
                color(fl)

                tooltips(anchor = Anchor.TOP_CENTER, minWidth = 50.0) {}
            }
            layout {
                title = "Grey background"
                style {
                    panel.background {
                        borderLineColor = Color.PURPLE
                        fillColor = Color.GREY
                    }
                }
            }
        }
    ),
    nCol = 2
)
```

<!---END-->

![Configured Tooltip and Background](guideStylesTooltipAndBackground.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/styles.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/1UjHFJP6F7W9OO42UpevDA" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
