# Pie

<show-structure for="chapter,procedure" depth="2"></show-structure>

<web-summary>
Discover the art of pie chart creation in Kotlin with the Kandy Pie Guide.
This resource teaches you how to effectively represent categorical data proportions through engaging and informative pie charts.
</web-summary>

<card-summary>
Kandy Pie Guide: Elevate your data storytelling in Kotlin with compelling pie charts.
Learn how to visually communicate categorical data proportions with clarity and impact.
</card-summary>

<link-summary>
Learn to effectively represent categorical data with visually engaging charts.
</link-summary>



<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.Pie-->

<!---FUN guidePieBlankTheme-->

```kotlin
val blankTheme = theme {
    global.line {
        blank = true
    }
    blankAxes()
}
```

<!---END-->

<!---FUN guidePieData-->

```kotlin
val dataset = dataFrameOf(
    "name" to listOf('a', 'b', 'c', 'd', 'b'),
    "value" to listOf(40, 90, 10, 50, 20)
)
```

<!---END-->

## Basic Pie Chart

<!---FUN guidePieBasicPieChart-->

```kotlin
dataset.plot {
    pie {
        slice(value)
        fillColor(name)
    }
}
```

<!---END-->

![Basic Pie Chart](guidePieBasicPieChart.svg)

#### Improve appearance

* configure stroke
* make the pie bigger
* add hole to draw donut-like chart
* use blank theme
* use better colors

<!---FUN guidePieCustomizedPieChart-->

```kotlin
dataset.plot {
    pie {
        slice(value)
        fillColor(name) {
            scale = categoricalColorBrewer(BrewerPalette.Qualitative.Set1)
        }
        size = 20.0
        stroke = 1.0
        strokeColor = Color.WHITE
        hole = 0.5
    }
    layout {
        theme(blankTheme)
    }
}
```

<!---END-->

![Configured Pie Chart](guidePieCustomizedPieChart.svg)

## Explode

<!---FUN guidePieLengthData-->

```kotlin
val length = dataFrameOf(
    "name" to listOf(
        "20-50 km",
        "50-75 km",
        "10-20 km",
        "75-100 km",
        "3-5 km",
        "7-10 km",
        "5-7 km",
        ">100 km",
        "2-3 km"
    ),
    "count" to listOf(1109, 696, 353, 192, 168, 86, 74, 65, 53),
    "explode" to listOf(.0, .0, .0, .1, .1, .2, .3, .4, .6)
)
```

<!---END-->

<!---FUN guidePieExplodePieChart-->

```kotlin
length.plot {
    pie {
        fillColor(name) {
            scale = continuous(Color.named("dark_blue"), Color.LIGHT_GREEN)
        }
        slice(count)
        explode(explode)
        stroke = 1.0
        strokeColor = Color.BLACK
        size = 20.0
    }
    layout.theme(blankTheme)
}
```

<!---END-->

![Explode Pie Chart](guidePieExplodePieChart.svg)

<!---FUN guidePieCaloriesData-->

```kotlin
val calories = dataFrameOf(
    "slice" to listOf(35, 25, 25, 15),
    "label" to listOf("Apples", "Bananas", "Cherries", "Dates"),
    "explode" to listOf(.1, .0, .0, .0)
)
```

<!---END-->

<!---FUN guidePieChartsInPlotGrid-->

```kotlin
plotGrid(
    listOf(
        calories.plot {
            pie {
                slice(slice)
                explode(explode)
                fillColor(label) {
                    scale = categoricalColorBrewer(BrewerPalette.Qualitative.Set1)
                }
                size = 15.0
            }
            layout {
                theme(blankTheme)
            }
        },
        calories.plot {
            pie {
                slice(slice)
                explode(explode)
                fillColor(label) {
                    scale = categoricalColorBrewer(BrewerPalette.Qualitative.Set1)
                }
                size = 15.0
                hole = 0.8
            }
            layout {
                theme(blankTheme)
            }
        }
    )
)
```

<!---END-->

![Two Pie Charts](guidePieChartsInPlotGrid.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/pie.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/NLBjw7czv1wZOB7wyuIQz7" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
