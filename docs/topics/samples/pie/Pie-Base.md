# Pie Base

<web-summary>
Explore a simple pie chart creation with the 'Base Pie' example using the Kandy library in Kotlin.
</web-summary>

<card-summary>
'Base Pie' in Kotlin with Kandy: Effortlessly visualize ratios in a clear pie chart.
</card-summary>

<link-summary>
Check out 'Base Pie' for an easy demonstration of pie chart plotting with Kandy in Kotlin,
featuring ratios data.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Pie-->

<!---FUN pie_base-->
<tabs>
<tab title="Dataframe">

```kotlin
val value by columnOf(15, 22, 40, 7, 31)
val type by columnOf("A", "B", "C", "A", "D")
val df = dataFrameOf(value, type)

df.plot {
    pie {
        slice(value)
        fillColor(type)
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val value = listOf(15, 22, 40, 7, 31)
val type = listOf("A", "B", "C", "A", "D")

plot {
    pie {
        slice(value)
        fillColor(type, "type")
    }
}
```

</tab></tabs>
<!---END-->

![Base Pie](pie_base.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/pie/pie_base.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/yOENpffRR5kE9LaPFCNKPN" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
