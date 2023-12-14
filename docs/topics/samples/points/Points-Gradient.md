# Points Gradient

<web-summary>
Discover 'Points Gradient' in Kotlin using Kandy, showcasing how gradient colors can be used to enhance scatter plots.
This example illustrates the use of color gradients to represent variations in data, adding depth and dimension to the visualization.
</web-summary>

<card-summary>
'Points Gradient' in Kotlin with Kandy: A visually appealing approach to scatter plots,
where gradient colors add layers of meaning to each data point.
</card-summary>

<link-summary>
Explore 'Points Gradient' using Kotlin and Kandy,
where color gradients in scatter plots bring a new level of insight and aesthetic to data visualization.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Points-->

<!---FUN points_gradient-->

```kotlin
val random = kotlin.random.Random(42)
val xs = List(100) { random.nextDouble(0.0, 10.0) }
val ys = List(100) { random.nextDouble(0.0, 10.0) }
val gradient = List(100) { random.nextDouble(0.0, 100.0) }
plot {
    points {
        x(xs)
        y(ys)
        size = 7.5
        color(gradient) {
            scale = continuous(Color.LIGHT_BLUE..Color.PURPLE, domain = 0.0..100.0)
        }
    }
}
```

<!---END-->

![Points Gradient](points_gradient.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/points/points_gradient.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/VWLOmBBOxVQ2zB9Nl6k120" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
