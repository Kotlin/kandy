# Simple Ribbon

<web-summary>
Explore a simple ribbon plot creation with the 'Simple Ribbon' example using the Kandy library in Kotlin.
</web-summary>

<card-summary>
'Simple Ribbon' in Kotlin with Kandy: Effortlessly visualize min/max range trends in a clear ribbon plot.
</card-summary>

<link-summary>
Check out 'Simple Ribbon' for an easy demonstration of ribbon chart plotting with Kandy in Kotlin.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Ribbon-->

<!---FUN ribbon_simple-->
<tabs>
<tab title="Dataframe">

```kotlin
val xs by columnOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
val mins by columnOf(0.2, 0.9, 0.55, 1.32, 2.2, 1.5)
val maxs by columnOf(0.7, 1.4, 1.1, 2.1, 2.6, 2.2)
val df = dataFrameOf(xs, mins, maxs)

df.plot {
    ribbon {
        x(xs)
        yMin(mins)
        yMax(maxs)
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val xs = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)
val mins = listOf(0.2, 0.9, 0.55, 1.32, 2.2, 1.5)
val maxs = listOf(0.7, 1.4, 1.1, 2.1, 2.6, 2.2)

plot {
    ribbon {
        x(xs)
        yMin(mins)
        yMax(maxs)
    }
}
```

</tab></tabs>
<!---END-->

![Simple Ribbon](ribbon_simple.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/ribbon/ribbon_simple.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/EgW8jL92LXom0YY9IK8IJ0" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
