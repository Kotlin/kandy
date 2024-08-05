# Pie With Count

<web-summary>
Discover the 'Pie With Count' example in Kotlin using Kandy, showcasing length distribution with "exploded" pie slices.
</web-summary>

<card-summary>
Kotlin 'Pie With Count' Example: An engaging display of the range ratios
customised with slice explosion.
</card-summary>

<link-summary>
Explore the 'Pie With Count' in Kotlin with Kandy, a visually appealing representation of
length distribution with exploded pie slices.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Pie-->

<!---FUN pie_with_count-->
<tabs>
<tab title="Dataframe">

```kotlin
val continent by columnOf(
    "EU", "AF", "SA", "OC", "EU", "AF", "SA", "AF", "AS", "SA",
    "OC", "OC", "SA", "NA", "AF", "NA", "EU", "AF", "OC", "SA",
    "AF", "SA", "OC", "EU", "AF"
)
val df = dataFrameOf(continent)

df.plot {
    statCount(continent) {
        pie {
            slice(Stat.count)
            fillColor(Stat.x named "continent")
            size = 25.0
        }
    }
    layout {
        style(Style.Void)
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val continent = listOf(
    "EU", "AF", "SA", "OC", "EU", "AF", "SA", "AF", "AS", "SA",
    "OC", "OC", "SA", "NA", "AF", "NA", "EU", "AF", "OC", "SA",
    "AF", "SA", "OC", "EU", "AF"
)

plot {
    statCount(continent) {
        pie {
            slice(Stat.count)
            fillColor(Stat.x named "continent")
            size = 25.0
        }
    }
    layout {
        style(Style.Void)
    }
}
```

</tab></tabs>
<!---END-->

![Pie With Count](pie_with_count.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/pie/pie_with_count.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/ai8toEu3NJQIRongwsThkp" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
