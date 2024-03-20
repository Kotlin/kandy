# Horizontal Bars

<web-summary>
Explore 'Horizontal Bars' in Kotlin using Kandy, an example that demonstrates the effective use of horizontal bar charts in data visualization.
This approach is ideal for comparing categories or groups where the length of the bar represents a value, offering a clear and straightforward comparative view.
</web-summary>

<card-summary>
'Horizontal Bars' in Kotlin with Kandy: A classic approach to bar chart visualization,
perfect for showcasing comparisons across categories or groups in a horizontal layout.
</card-summary>

<link-summary>
Dive into 'Horizontal Bars' using Kotlin and Kandy,
showcasing the versatility and readability of horizontal bar plots in presenting categorical data.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Bars-->

<!---FUN horizontal_bars-->
<tabs>
<tab title="Dataframe">

```kotlin
val actors by columnOf(
    "John Doe", "Emma Stone", "Ryan Gosling", "Natalie Portman",
    "Brad Pitt", "Marilyn Monroe", "Leonardo DiCaprio"
)
val screenTime by columnOf(90, 75, 60, 85, 50, 40, 95)

val dataset = dataFrameOf(actors, screenTime)

dataset.plot {
    layout.title = "Screen Time of Hollywood Actors"
    barsH {
        y(actors) { axis.name = "Actors" }
        x(screenTime) { axis.name = "minutes" }
        alpha = 0.75
        fillColor(actors) {
            scale = categoricalColorHue()
        }
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val actors = listOf(
    "John Doe", "Emma Stone", "Ryan Gosling", "Natalie Portman",
    "Brad Pitt", "Marilyn Monroe", "Leonardo DiCaprio"
)
val screenTime = listOf(90, 75, 60, 85, 50, 40, 95)

plot {
    layout.title = "Screen Time of Hollywood Actors"
    barsH {
        y(actors) { axis.name = "Actors" }
        x(screenTime) { axis.name = "minutes" }
        alpha = 0.75
        fillColor(actors) {
            legend.name = "actors"
            scale = categoricalColorHue()
        }
    }
}
```

</tab></tabs>
<!---END-->

![Horizontal Bars](horizontal_bars.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/bars/horizontal_bars.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/dCru6yzGCKmGNZipnQC2kt" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
