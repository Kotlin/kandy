# Heatmap Settings

<web-summary>
Explore 'Heatmap Settings' in Kotlin using Kandy, showcasing various customizations and settings for heatmap plots.
This example is ideal for learning how to tweak heatmap plot appearances for more engaging and informative visualizations.
</web-summary>

<card-summary>
'Heatmap Settings' in Kotlin with Kandy: A detailed demonstration of the versatility in heatmap plot customization,
perfect for tailoring visual representations to specific data storytelling needs.
</card-summary>

<link-summary>
Explore the 'Heatmap Settings' example using Kotlin and Kandy to understand the depth of customization available in heatmap plotting, from color adjustments to layout tweaks.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Heatmap-->

<!---FUN heatmap_settings-->

```kotlin
val df = DataFrame.readCSV(
    fileOrUrl = "https://raw.githubusercontent.com/Kotlin/dataframe/master/examples/idea-examples/titanic/src/main/resources/titanic.csv",
    delimiter = ';', parserOptions = ParserOptions(locale = java.util.Locale.FRENCH)
)

df.plot {
    heatmap("embarked", "pclass") {
        borderLine {
            width = 0.8
            color = Color.BLACK
        }
        fillColor(Stat.count) {
            scale = continuous(Color.WHITE..Color.RED)
            legend.name = "number of\n passangers"
        }
    }
    y.axis.breaks(df["pclass"].distinct().toList(), format = "d")
}
```

<!---END-->


![Heatmap Settings](heatmap_settings.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/heatmap/heatmap_settings.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/TL4NFC1Q4w0lBxFttfPeQR" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
