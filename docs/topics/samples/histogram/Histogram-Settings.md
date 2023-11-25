# Histogram Settings

<web-summary>
Explore 'Histogram Settings' in Kotlin using Kandy, showcasing various customizations and settings for histogram plots.
This example is ideal for learning how to tweak histogram plot appearances for more engaging and informative visualizations.
</web-summary>

<card-summary>
'Histogram Settings' in Kotlin with Kandy: A detailed demonstration of the versatility in histogram plot customization,
perfect for tailoring visual representations to specific data storytelling needs.
</card-summary>

<link-summary>
Explore the 'Histogram Settings' example using Kotlin and Kandy to understand the depth of customization available in histogram plotting, from color adjustments to layout tweaks.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Histogram-->

<!---FUN histogram_settings-->
<tabs>
<tab title="Dataframe">

```kotlin
val expreimentalDF = dataFrameOf("length" to listOf(
    5.92, 6.44, 5.87, 4.99, 5.23,
    5.67, 4.89, 5.34, 5.78, 5.12,
    5.56, 5.23, 5.78, 6.01, 5.56,
    5.67, 5.89, 5.45, 6.12, 5.78,
    6.34, 5.67, 6.45, 5.34, 5.89,
    6.01, 5.78, 5.23, 5.67, 6.12,
    6.23, 5.45, 5.56, 5.67, 5.78,
    5.56, 6.23, 5.78, 6.34, 6.12,
    5.89, 6.45, 5.78, 6.34, 5.67,
    6.56, 5.45, 5.78, 5.89, 6.12,
    4.67, 4.79, 5.14, 5.28, 5.22,
))
expreimentalDF.plot {
    histogram("length", binsOption = BinsOption.byNumber(12)) {
        width = 0.8
        alpha = 0.9
        fillColor = Color.RED
        borderLine {
            color = Color.GREEN
            width = 0.5
        }
        x.axis.name = "length"
    }
    layout.title = "Flight length experiment"
}
```

</tab>
<tab title="Collections">

```kotlin
val experimentalData = listOf(
    5.92, 6.44, 5.87, 4.99, 5.23,
    5.67, 4.89, 5.34, 5.78, 5.12,
    5.56, 5.23, 5.78, 6.01, 5.56,
    5.67, 5.89, 5.45, 6.12, 5.78,
    6.34, 5.67, 6.45, 5.34, 5.89,
    6.01, 5.78, 5.23, 5.67, 6.12,
    6.23, 5.45, 5.56, 5.67, 5.78,
    5.56, 6.23, 5.78, 6.34, 6.12,
    5.89, 6.45, 5.78, 6.34, 5.67,
    6.56, 5.45, 5.78, 5.89, 6.12,
    4.67, 4.79, 5.14, 5.28, 5.22,
)

plot {
    histogram(experimentalData, binsOption = BinsOption.byNumber(12)) {
        width = 0.8
        alpha = 0.9
        fillColor = Color.RED
        borderLine {
            color = Color.GREEN
            width = 0.5
        }
        x.axis.name = "length"
    }
    layout.title = "Flight length experiment"
}
```

</tab></tabs>
<!---END-->


![Histogram Settings](histogram_settings.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/histogram/histogram_settings.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/0VZzfzOogM3v1ePEqDdF4y" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
