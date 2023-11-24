# Jittered Points

<web-summary>
Explore 'Jittered Points' in Kotlin using Kandy, a plot that demonstrates the technique of jittering to spread out overlapping data points.
This example is perfect for datasets where individual observations need to be distinctly visualized in dense areas.
</web-summary>

<card-summary>
'Jittered Points' in Kotlin with Kandy: An effective way to prevent data overlap in scatter plots,
ideal for clearer visualization of dense data clusters.
</card-summary>

<link-summary>
Dive into 'Jittered Points' using Kotlin and Kandy, where jittering techniques are applied to scatter plots to enhance the readability of closely-packed data points.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Points-->

<!---FUN jittered_points-->
<tabs>
<tab title="Dataframe">

```kotlin
val random = kotlin.random.Random(42)
val data = dataFrameOf(
    "type" to List(50) { "a" } + List(50) { "b" },
    "value" to List(50) { kotlin.random.Random.nextDouble(0.1, 0.6) } +
            List(50) { random.nextDouble(-0.5, 0.4) }
)

val type = column<String>("type")
val value = column<Double>("value")

data.plot {
    points {
        x(type)
        y(value)
        color(type)
        position = Position.jitter()
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val random = kotlin.random.Random(42)
val type = List(50) { "a" } + List(50) { "b" }
val value = List(50) { random.nextDouble(0.1, 0.6) } +
        List(50) { random.nextDouble(-0.5, 0.4) }

plot {
    points {
        x(type)
        y(value)
        color(type)
        position = Position.jitter()
    }
}
```

</tab></tabs>
<!---END-->

![Jittered Points](jittered_points.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/points/jittered_points.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/2nG3SGUxJmB6dCIPyMmDyh" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
