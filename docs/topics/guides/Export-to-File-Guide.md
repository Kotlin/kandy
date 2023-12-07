# Export to File

<web-summary>
Enhance your proficiency in preserving data visualizations using the guide to Kandy Export to File.
Learn effective strategies to secure your graphs in a multitude of formats,
thereby ensuring that your insights can be shared and remain longstanding.
</web-summary>

<card-summary>
Kandy's Export to File guide: Save and share your visual masterpieces. 
earn how to export charts in multiple formats, making your data visualization accessible and durable.
</card-summary>

<link-summary>
Dive into Kandy's Export to File guide to understand the ways of preserving your charts for future reference.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.ExportToFile-->

<!---FUN guideExportGenerateRandomData-->

```kotlin
// Create random density data
val rand = java.util.Random(42)
val n = 500
val dataset = dataFrameOf(
    "rating" to List(n / 2) { rand.nextGaussian() } + List(n / 2) { rand.nextGaussian() * 1.5 + 1.5 },
    "cond" to List(n / 2) { "A" } + List(n / 2) { "B" }
)
```

<!---END-->

<!---FUN guideExportDensityPlot-->

```kotlin
// Density plot
val myPlot = plot(dataset) {
    groupBy(cond) {
        densityPlot(rating, trim = true) {
            fillColor = Color.GREY
            alpha = 0.6
            borderLine.color("cond")
        }
    }
}

myPlot
```

<!---END-->

![](guideExportDensityPlot.svg)

## Export PNG to file

```kotlin
val pathPNG = myPlot.save("myDensity.png")
```

```kotlin
javax.imageio.ImageIO.read(File(pathPNG))
```

![](guideExportDensityPlot.svg)

## Export SVG to file

```kotlin
val pathSVG = myPlot.save("myDensity.svg")
```

```kotlin
HTML(File(pathSVG).readText())
```

![](guideExportDensityPlot.svg)

## Export HTML to file

```kotlin
val pathHTML = myPlot.save("myDensity.html")
```

```kotlin
HTML(File(pathHTML).readText())
```

![](guideExportDensityPlot.svg)

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/export_to_file.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/YiB4pG0ApubGHnP1MVr26j" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
