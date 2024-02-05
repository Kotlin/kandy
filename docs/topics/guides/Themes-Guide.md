# Themes

<web-summary>
Unleash the power of visual styling with Kandy's Themes guide.
Explore a variety of themes to add a professional and aesthetically pleasing touch to your data visualizations.
</web-summary>

<card-summary>
Transform your data plots with Kandy's Themes guide.
Learn how to apply different themes to enhance the visual appeal and clarity of your charts.
</card-summary>

<link-summary>
Step into the world of thematic data visualization with Kandy's Themes guide.
Discover how to select and apply themes for more engaging and informative charts.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.Themes-->

## Preparation

<!---FUN guideThemesReadData-->

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

<!---FUN guideThemesPointPlotWithoutTheme-->

```kotlin
fun pointPlotWithTheme(name: String, inStyle: Style = Style.Minimal2, cusTheme: CustomStyle.() -> Unit = {}) =
    df.plot {
        points {
            x(cty)
            y(hwy)
        }
        layout {
            title = name
            style(inStyle, cusTheme)
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

![Point Plot without Theme](guideThemesPointPlotWithoutTheme.svg)

<!---FUN guideThemesCountFlDf-->

```kotlin
val bPlotDf = df.groupBy { fl }.count()
```

<!---END-->

<!---FUN guideThemesBarPlotWithoutTheme-->

```kotlin
fun barPlotWithTheme(name: String, inStyle: Style = Style.Minimal2, cusTheme: CustomStyle.() -> Unit = {}) =
    bPlotDf.plot {
        bars {
            x(fl)
            y(count)
            fillColor(fl)
        }
        layout {
            title = name
            style(inStyle, cusTheme)
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

![Bar Plot without Theme](guideThemesBarPlotWithoutTheme.svg)

<!---FUN guideThemesCountFlAndYearDf-->

```kotlin
val fPlotDf = df.groupBy { fl and year }.count()
```

<!---END-->


<!---FUN guideThemesBarsFacetGridX-->

```kotlin
fun barFacetPlotWithTheme(
    name: String, inStyle: Style = Style.Minimal2, cusTheme: CustomStyle.() -> Unit = {}
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
            style(inStyle, cusTheme)
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

![Bar Facet Grid X Plot without Theme](guideThemesBarsFacetGridX.svg)

### Elements

<!---FUN guideThemesUtilElements-->

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

## Themes

<!---FUN guideThemesAllPrepThemes-->

```kotlin
plotGrid(
    listOf(
        pointPlotWithTheme("Minimal2 Theme - by default"),
        pointPlotWithTheme("Light Theme", Style.Light),
        pointPlotWithTheme("Classic Theme", Style.Classic),
        pointPlotWithTheme("Grey Theme", Style.Grey),
        pointPlotWithTheme("Minimal Theme", Style.Minimal),
        pointPlotWithTheme("None Theme", Style.None)
    ),
    nCol = 2,
    fit = true
)
```

<!---END-->

![All Default Themes](guideThemesAllPrepThemes.svg)

## Custom Theme

<!---FUN guideThemesOrangeCustomTheme-->

```kotlin
val yellowLight = Color.hex("#ffffcc")
val orangeDark = Color.hex("#7f2704")
val orangeNormal = Color.hex("#f16913")
val orangeLight = Color.hex("#fff5eb")

val themeOrangeConstructor: CustomStyle.() -> Unit = {
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
        pointPlotWithTheme("Scatter plot", Style.None, themeOrangeConstructor),
        barPlotWithTheme("Bar plot", Style.None, themeOrangeConstructor)
    ),
    nCol = 2,
    fit = true
)
```

<!---END-->

![Orange Custom Theme](guideThemesOrangeCustomTheme.svg)

<!---FUN guideThemesCustomThemeForLegend-->

```kotlin
barPlotWithTheme("Place legend") {
    legend {
        position(1.0, 1.0)
        justification(1.0, 1.0)
        direction = LegendDirection.HORIZONTAL
    }
}
```

<!---END-->

![Custom Legend](guideThemesCustomThemeForLegend.svg)

### Axis Tooltip

<!---FUN guideThemesAxisTooltip-->

```kotlin
plotGrid(
    listOf(
        pointPlotWithTheme("blank tooltip x-axis") {
            xAxis.tooltip.background {
                blank = true
            }
        },
        pointPlotWithTheme("background tooltip x-axis") {
            xAxis.tooltip.background(eBackground)
        }
    ),
    nCol = 2
)
```

<!---END-->

![Axis Tooltip](guideThemesAxisTooltip.svg)

### Line, Background, Text

<!---FUN guideThemesConfigureGridLine-->

```kotlin
plotGrid(
    listOf(
        pointPlotWithTheme("Default"),
        pointPlotWithTheme("Configured grid line") {
            global.line(eLine)
        }
    ),
    nCol = 2
)
```

<!---END-->

![Configured Grid Line](guideThemesConfigureGridLine.svg)

<!---FUN guideThemesConfigureBackgroundGrid-->

```kotlin
plotGrid(
    listOf(
        barPlotWithTheme("None Theme", Style.None),
        barPlotWithTheme("None theme + Rect", Style.None) {
            global.background(eBackground)
        }
    ),
    nCol = 2
)
```

<!---END-->

![Configured Background Grid](guideThemesConfigureBackgroundGrid.svg)

<!---FUN guideThemesTextLegendStrip-->

```kotlin
plotGrid(
    listOf(
        barFacetPlotWithTheme("Default"),
        barFacetPlotWithTheme("Text") { global.text(eText) },
        barFacetPlotWithTheme("Legend text") { legend.text(eText) },
        barFacetPlotWithTheme("Strip text") { strip.text(eText) }
    ),
    nCol = 2
)
```

<!---END-->

![Custom Text 1](guideThemesTextLegendStrip.svg)

<!---FUN guideThemesTextTitleLegend-->

```kotlin
plotGrid(
    listOf(
        barPlotWithTheme("Default"),
        barPlotWithTheme("Text") { global.text(eText) },
        barPlotWithTheme("Plot title") { plotCanvas.title(eText) },
        barPlotWithTheme("Legend title") { legend.title(eText) }
    ),
    nCol = 2
)
```

<!---END-->

![Custom Text 2](guideThemesTextTitleLegend.svg)

<!---FUN guideThemesBlankAxis-->

```kotlin
plotGrid(
    listOf(
        pointPlotWithTheme("Default"),
        pointPlotWithTheme("Blank axis") {
            axis.line {
                blank = true
            }
        }
    ),
    nCol = 2
)
```

<!---END-->

![Blank Axis](guideThemesBlankAxis.svg)

<!---FUN guideThemesBackgroundPanel-->

```kotlin
plotGrid(
    listOf(
        pointPlotWithTheme("Default"),
        pointPlotWithTheme("Panel background") {
            panel.background(eBackground)
        }
    ),
    nCol = 2
)
```

<!---END-->

![Bakground Panel](guideThemesBackgroundPanel.svg)

<!---FUN guideThemesPanelGrid-->

```kotlin
plotGrid(
    listOf(
        barPlotWithTheme("Blank panel grid") {
            panel.grid.lineGlobal {
                blank = true
            }
        },
        pointPlotWithTheme("Line panel grid") {
            panel.grid.lineGlobal(eLine)
        },
        pointPlotWithTheme("Major line panel grid\n Minor line panel grid") {
            panel.grid {
                majorLine(eLine)
                minorLine(eLine2)
            }
        },
        barPlotWithTheme("Blank major x-line panel grid\n major y-line panel grid\n minor y-line panel grid") {
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

![Panel Grid](guideThemesPanelGrid.svg)

<!---FUN guideThemesStripBackground-->

```kotlin
plotGrid(
    listOf(
        barFacetPlotWithTheme("Grey Theme", Style.Grey),
        barFacetPlotWithTheme("Strip background") { strip.background(eBackground) },
        barFacetPlotWithTheme("Blank strip background") {
            strip.background {
                blank = true
            }
        },
        barFacetPlotWithTheme("Blank strip text") {
            strip.text {
                blank = true
            }
        }
    ),
    nCol = 2
)
```

<!---END-->

![Background Strip](guideThemesStripBackground.svg)

<!---FUN guideThemesOnTopXAxis-->

```kotlin
plotGrid(
    listOf(
        barPlotWithTheme("x-axis line") { xAxis.line(eLine3) },
        barPlotWithTheme("On top x-axis line") {
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

![Configured X-Axis Line](guideThemesOnTopXAxis.svg)

### Miscellaneous

<!---FUN guideThemesCoordFlip-->

```kotlin
plotGrid(
    listOf(
        fPlotDf.plot {
            bars {
                x(fl)
                y(count)
                fillColor(fl)
            }
            coordFlip()
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
            coordFlip()
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
            coordFlip()
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
            coordFlip()
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
            coordFlip()
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
            coordFlip()
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

![Miscellaneous](guideThemesCoordFlip.svg)

<!---FUN guideThemesTooltipAndBackground-->

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

![Configured Tooltip and Background](guideThemesTooltipAndBackground.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/themes.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/1UjHFJP6F7W9OO42UpevDA" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
