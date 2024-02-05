# Title, subtitle, and caption

<web-summary>
Enhance your ability to communicate effectively in data visualization using Kandy's guide on Titles,
Subtitles, and Captions. 
Improve your skills in creating effective and insightful titles that boost the comprehensibility of your charts.
</web-summary>

<card-summary>
Enhance your charts with clear messaging using Kandy's guide on Titles, Subtitles, and Captions.
Discover how to convey your data's story more effectively.
</card-summary>

<link-summary>
Explore Kandy's guide to Titles, Subtitles, and Captions,
and learn how to add descriptive elements to your charts for better clarity and impact.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.TitleSubtitleCaption-->

<!---FUN guideTitleReadData-->

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

<!---FUN guideTitleDefaultPointPlot-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(drv)
    }
}
```

<!---END-->

![Default Point Plot](guideTitleDefaultPointPlot.svg)

<!---FUN guideTitleSetTitle1-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(drv)
    }
    layout.title = "The plot title using 'layout.title'"
}
```

<!---END-->

![Plot with Title](guideTitleSetTitle1.svg)

<!---FUN guideTitleSetTitle2-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(drv)
    }
    layout {
        title = "The plot title using 'Layout context'"
    }
}
```

<!---END-->

![Plot with Title](guideTitleSetTitle2.svg)

<!---FUN guideTitleSetTitleAndSubtitle-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(drv)
    }
    layout {
        title = "The plot title"
        subtitle = "The plot subtitle"
    }
}
```

<!---END-->

![Plot with Title and Subtitle](guideTitleSetTitleAndSubtitle.svg)

<!---FUN guideTitleSetTitleSubtitleCaption-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(drv)
    }
    layout {
        title = "The plot title"
        subtitle = "The plot subtitle"
        caption = "The plot caption"
    }
}
```

<!---END-->

![Plot with Title, Subtitle, and Caption](guideTitleSetTitleSubtitleCaption.svg)

<!---FUN guideTitleChangeTitlesColor-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(drv)
    }
    layout {
        title = "The plot title"
        subtitle = "The plot subtitle"
        caption = "The plot caption"
        style {
            global.title {
                color = Color.BLUE
            }
        }
    }
}
```

<!---END-->

![Titles Color](guideTitleChangeTitlesColor.svg)

<!---FUN guideTitleChangeColorAndFontFace-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(drv)
    }
    layout {
        title = "The plot title"
        subtitle = "The plot subtitle"
        caption = "The plot caption"
        style {
            global {
                text {
                    fontFace = FontFace.ITALIC
                }
                title {
                    fontFace = FontFace.ITALIC
                }
            }
            plotCanvas {
                title {
                    color = Color.BLUE
                }
                subtitle {
                    fontFace = FontFace.ITALIC
                }
                caption {
                    fontFace = FontFace.ITALIC
                }
            }
        }
    }
}
```

<!---END-->

![Color and Font Face for Titles](guideTitleChangeColorAndFontFace.svg)

<!---FUN guideTitleSetColorsForTitleSubtitleCaption-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(drv)
    }
    layout {
        title = "The plot title"
        subtitle = "The plot subtitle"
        caption = "The plot caption"
        style {
            plotCanvas {
                title {
                    color = Color.BLUE
                }
                subtitle {
                    color = Color.RED
                }
                caption {
                    color = Color.named("dark_green")
                }
            }
        }
    }
}
```

<!---END-->

![Title, Subtitle and Caption Color](guideTitleSetColorsForTitleSubtitleCaption.svg)

<!---FUN guideTitleComplexTitles-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(drv)
    }
    layout {
        title = "The plot title:\nFuel efficiency for most popular models of car"
        subtitle = "The plot subtitle:\nPoints are colored by the type of drive train"
        caption = "The plot caption:\nmpg dataset"
        style {
            plotCanvas {
                subtitle {
                    color = Color.GREY
                }
            }
        }
    }
}
```

<!---END-->

![Two Lines Title](guideTitleComplexTitles.svg)

<!---FUN guideTitleLegendName-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(cty) { legend.name = "City mileage" }
        symbol(drv) { legend.name = "Drive type" }
        size = 4.0
    }
}
```

<!---END-->

![Legend Name](guideTitleLegendName.svg)

<!---FUN guideTitleLegendPosition-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(cty) { legend.name = "City mileage" }
        symbol(drv) { legend.name = "Drive type" }
        size = 4.0
    }
    layout.style {
        legend.position = LegendPosition.Bottom
    }
}
```

<!---END-->

![Position Legend](guideTitleLegendPosition.svg)

<!---FUN guideTitleComplexLegendNames-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(cty) { legend.name = "City mileage\n(mpg)" }
        symbol(drv) { legend.name = "Drive type\n(front/4/rear wheel)" }
        size = 4.0
    }
}
```

<!---END-->

![Two Lines Legend Name](guideTitleComplexLegendNames.svg)

<!---FUN guideTitleComplexLegendBottom-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(cty) { legend.name = "City mileage\n(mpg)" }
        symbol(drv) { legend.name = "Drive type\n(front/4/rear wheel)" }
        size = 4.0
    }
    layout.style {
        legend.position = LegendPosition.Bottom
    }
}
```

<!---END-->

![Bottom Two Lines Legend Name](guideTitleComplexLegendBottom.svg)

<!---FUN guideTitleTypeOfLegend-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(hwy)
        color(cty) { legend.name = "City mileage\n(mpg)" }
        symbol(drv) {
            legend {
                type = LegendType.DiscreteLegend(nRow = 3)
                name = "Drive type\n(front/4/rear wheel)"
            }
        }
        size = 4.0
    }
    layout.style {
        legend.position = LegendPosition.Bottom
    }
}
```

<!---END-->

![Type of Legend](guideTitleTypeOfLegend.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/title_sibtitile_caption.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/5uitRTbG011UFPvsbClotl" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
