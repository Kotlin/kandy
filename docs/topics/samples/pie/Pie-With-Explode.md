# Pie With Explode

<web-summary>
Discover the 'Pie With Explode' example in Kotlin using Kandy, showcasing length distribution with "exploded" pie slices.
</web-summary>

<card-summary>
Kotlin 'Pie With Explode' Example: An engaging display of the range ratios
customised with slice explosion.
</card-summary>

<link-summary>
Explore the 'Pie With Explode' in Kotlin with Kandy, a visually appealing representation of
length distribution with exploded pie slices.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Pie-->

<!---FUN pie_explode-->
<tabs>
<tab title="Dataframe">

```kotlin
val range by columnOf("0-10m", "10-20m", "20-40m", "40-100m", "100-250m", ">250m",)
val share by columnOf(0.42, 0.23, 0.15, 0.11, 0.06, 0.03)
val explode by columnOf(0.20, 0.0, 0.04, 0.08, 0.12, 0.16)
val df = dataFrameOf(range, share, explode)

df.plot {
    pie {
        slice(share)
        fillColor(range) {
            scale = continuous(Color.RED..Color.LIGHT_GREEN)
        }
        explode(explode)
        size = 25.0
    }
    layout {
        style(Style.Void)
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val range = listOf("0-10m", "10-20m", "20-40m", "40-100m", "100-250m", ">250m",)
val share = listOf(0.42, 0.23, 0.15, 0.11, 0.06, 0.03)
val explode = listOf(0.20, 0.0, 0.04, 0.08, 0.12, 0.16)

plot {
    pie {
        slice(share)
        fillColor(range, "range") {
            scale = continuous(Color.RED..Color.LIGHT_GREEN)
        }
        explode(explode)
        size = 25.0
    }
    layout {
        style(Style.Void)
    }
}
```

</tab></tabs>
<!---END-->

![Pie With Explode](pie_explode.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/pie/pie_explode.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/EZKM8SiaqlSJMoMfdsYUib" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
