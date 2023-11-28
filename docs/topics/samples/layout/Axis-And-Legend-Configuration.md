# Axis And Legend Configuration

<web-summary>
Explore 'Axis And Legend Configuration' in Kotlin using Kandy to customize plot axes and legends.
</web-summary>

<card-summary>
'Axis And Legend Configuration' in Kotlin with Kandy: full customization of all guides on the chart.
</card-summary>

<link-summary>
Dive into 'Axis And Legend Configuration' using Kotlin and Kandy, full and detailed customization of plot legends and axes improving its informativeness.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Layout-->

<!---FUN axis_and_legend_configuration-->

```kotlin
val df = DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")

df.plot {
    points {
        x("displ") {
            axis.name = "engine displacement, liters"
        }
        y("hwy") {
            axis {
                name = "highway mileage, mpg"
                breaks(listOf(15, 25, 35, 45), format = "d")
            }
        }
        color("cty") {
            legend {
                name = "city mileage"
                breaks(format = "{d} mpg")
            }
        }
        symbol("drv") {
            legend {
                type = LegendType.DiscreteLegend(nRow = 2)
                name = "drive type"
                breaksLabeled("4" to "4WD", "r" to "RWD", "f" to "FWD")
            }
        }
        size = 4.0
    }
}
```

<!---END-->


![Axis And Legend Configuration](axis_and_legend_configuration.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/layout/axis_and_legend_configuration.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/3vKiaDuDGZM4jFaGDUeLAz" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
