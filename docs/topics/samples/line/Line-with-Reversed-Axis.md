# Line with Reversed Axis

<web-summary>
Delve into the 'Line with Reversed Axis' example in Kotlin with Kandy, demonstrating the impactful use of axis inversion in line graph visualization.
</web-summary>

<card-summary>
Experience the 'Line with Reversed Axis' in Kotlin with Kandy, showcasing how reversing an axis can offer a fresh perspective in graph plotting.
</card-summary>

<link-summary>
Explore the unique approach of 'Line with Reversed Axis' in Kotlin using Kandy, highlighting the effectiveness of axis inversion in data representation.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Lines-->

<!---FUN line_reversed_axis-->
<tabs>
<tab title="Dataframe">

```kotlin
val product = ('A'..'F').toColumn("product")
val rating = listOf(10, 7, 3, 5, 2, 1).toColumn("rating")
val data = dataFrameOf(product, rating)

plot(data) {
    line {
        x(rating) {
            scale = continuous(min = 0, max = 12)
        }
        y(product) {
            scale = continuous(transform = Transformation.REVERSE)
        }
        color = Color.RED
        alpha = 0.85
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val product = ('A'..'F')
val rating = listOf(10, 7, 3, 5, 2, 1)

plot {
    line {
        x(rating) {
            scale = continuous(min = 0, max = 12)
        }
        y(product) {
            scale = continuous(transform = Transformation.REVERSE)
        }
        color = Color.RED
        alpha = 0.85
    }
}
```

</tab></tabs>
<!---END-->

![Line with Reversed Axis](line_reversed_axis.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/line/line_with_reversed_axis.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/mMx81pnzWS66YyTV1pJ2ZA" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
