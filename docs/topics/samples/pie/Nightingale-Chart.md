# Nightingale Chart

<web-summary>
Discover the 'Nightingale Chart' example in Kotlin using Kandy, showcasing an amount ratio in pie chart using slice sizes.
</web-summary>

<card-summary>
Kotlin 'Nightingale Chart' Example: An engaging display of the ratios
with slice proportion.
</card-summary>

<link-summary>
Explore the 'Nightingale Chart' in Kotlin with Kandy, a visually appealing representation of
anount distribution with a slice sizes scale.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Pie-->

<!---FUN nightingale_chart-->
<tabs>
<tab title="Dataframe">

```kotlin
val month by columnOf("Jan", "Feb", "Mar", "May", "Apr")
val amount by columnOf(34.4, 25.1, 33.6, 20.0, 15.9)
val df = dataFrameOf(month, amount)

df.plot {
    pie {
        slice(amount)
        fillColor(month)
        size(amount) {
            scale = continuous(10.0..25.0)
            legend.type = LegendType.None
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
val month = listOf("Jan", "Feb", "Mar", "May", "Apr")
val amount = listOf(34.4, 25.1, 33.6, 20.0, 15.9)

plot {
    pie {
        slice(amount)
        fillColor(month, "month")
        size(amount) {
            scale = continuous(10.0..25.0)
            legend.type = LegendType.None
        }
    }
    layout {
        style(Style.Void)
    }
}
```

</tab></tabs>
<!---END-->

![Nightingale Chart](nightingale_chart.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/pie/nightingale_chart.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/qcfI3urwzFPmmWYEltJh1A" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
