# Complex Bubble Chart

<web-summary>
Explore the intricacies of data visualization with the "Complex Bubble Chart" in Kotlin using Kandy.
This example demonstrates how bubble charts can be utilized to represent multidimensional data,
providing insights into relationships and distributions through varying bubble sizes and colors.
</web-summary>

<card-summary>
Delve into multi-dimensional data visualization with the "Complex Bubble Chart" example using Kandy in Kotlin.
It showcases how bubble size and color can enhance understanding of complex datasets.
</card-summary>

<link-summary>
Discover multidimensional insights with 'Complex Bubble Chart' using Kandy, highlighting how bubble size and color reveal dataset complexities.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Points-->

<!---FUN complex_bubble_chart-->

```kotlin
val country by columnOf(
    "Australia", "Canada", "China", "Cuba", "Finland", "France", "Germany",
    "Iceland", "India", "Japan", "North Korea", "South Korea", "New Zealand",
    "Norway", "Poland", "Russia", "Turkey", "United Kingdom", "United States"
)
val lifeExp1998 = columnOf(
    75.49, 76.03, 66.35, 58.75, 79.64, 82.92, 80.68,
    70.71, 60.30, 82.78, 69.57, 74.61, 71.18,
    71.43, 75.22, 72.65, 74.56, 75.19, 81.12
) named "lifeExp"
val pop1998 = columnOf(
    19000, 30000, 1250000, 11000, 5000,
    59000, 82000, 280, 950000, 126000,
    24000, 47000, 3800, 4400, 38600,
    147000, 63000, 59000, 273000
) named "population"
val gdpPerCapita1998 = columnOf(
    29000, 32000, 750, 5000, 28000,
    27000, 32000, 34000, 450, 33000,
    600, 12000, 22000, 40000, 9000,
    4000, 3000, 28000, 35000
) named "gdp"
val data1998 = dataFrameOf(country, lifeExp1998, pop1998, gdpPerCapita1998).add("year") { 1998 }

val lifeExp2023 = columnOf(
    79.44, 73.60, 70.46, 63.34, 72.10, 78.15, 80.70,
    79.88, 63.13, 81.53, 59.33, 71.59, 76.56,
    71.97, 78.21, 78.38, 79.76, 84.77, 82.39
) named "lifeExp"
val pop2023 = columnOf(
    25000, 38000, 1400000, 11300, 5500,
    67000, 83000, 340, 1350000, 126000,
    25000, 51000, 5000, 5300, 38000,
    146000, 82000, 67000, 331000
) named "population"
val gdpPerCapita2023 = columnOf(
    55000, 52000, 10000, 7000, 48000,
    44000, 50000, 60000, 2000, 45000,
    1200, 30000, 34000, 70000, 15000,
    9000, 10000, 40000, 60000
) named "gdp"
val data2023 = dataFrameOf(country, lifeExp2023, pop2023, gdpPerCapita2023).add("year") { 2023 }

val data = data1998.fullJoin(data2023)

data.groupBy("year").plot {
    layout.title = "Life Expectancy and GDP by Country"
    points {
        x("gdp") { axis.name = "GDP per capita (in dollars)" }
        y("lifeExp") { axis.name = "Life expectancy (years)" }
        size("population") {
            legend.type = LegendType.None
            scale = continuous(5.0..20.0)
        }
        color("year") {
            legend.name = ""
        }
    }
}
```

<!---END-->

![Complex Bubble Chart](complex_bubble_chart.svg) { border-effect="rounded" }

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Points-->

<!---FUN complex_bubble_chart_part2-->

```kotlin
data.filter { "year"<Int>() == 1998 }.plot {
    layout.title = "Life Expectancy and GDP by Country (1998)"
    points {
        x("gdp") { axis.name = "GDP per capita (in dollars)" }
        y("lifeExp") { axis.name = "Life expectancy (years)" }
        size("population") {
            legend.type = LegendType.None
            scale = continuous(2.0..15.0)
        }
        color("country") {
            scale = categoricalColorHue()
        }
    }
}
```

<!---END-->

![Complex Bubble Chart](complex_bubble_chart_part2.svg) { border-effect="rounded" }

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Points-->

<!---FUN complex_bubble_chart_part3-->

```kotlin
data.filter { "year"<Int>() == 2023 }.plot {
    layout.title = "Life Expectancy and GDP by Country (2023)"
    points {
        x("gdp") { axis.name = "GDP per capita (in dollars)" }
        y("lifeExp") { axis.name = "Life expectancy (years)" }
        size("population") {
            legend.type = LegendType.None
            scale = continuous(2.0..15.0)
        }
        color("country") {
            scale = categoricalColorHue()
        }
    }
}
```

<!---END-->

![Complex Bubble Chart](complex_bubble_chart_part3.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/points/complex_bubble_chart.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/UYByVDOspLWvTPEsMqOEZs" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
