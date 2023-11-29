# Ribbon

<web-summary>
Unveil the power of data visualization in Kotlin with the Kandy Ribbon Guide.
Dive into creating sophisticated ribbon plots that skillfully represent uncertainty and variability in your data.
</web-summary>

<card-summary>
Discover the Ribbon Guide in Kandy for Kotlin: A comprehensive tutorial on crafting detailed ribbon plots,
perfect for showcasing data variability and confidence intervals.
</card-summary>

<link-summary>
Explore the art of ribbon plotting in Kotlin with the Kandy Ribbon Guide.
Learn to represent uncertainty and trends in data with visually intuitive ribbon charts.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.Ribbon-->

<!---FUN guideRibbonData-->

```kotlin
fun generateDataMap(n: Int = 15, a: Double = 1.0): Map<String, List<Any>> {
    val rand = java.util.Random(42)
    val x = List(2 * n + 1) { i -> a * (i - n).toDouble() / n }
    val tMin = x.map { a * a - it.pow(2) - abs(rand.nextGaussian()) }
    val tMax = x.map { a * a - it.pow(2) + abs(rand.nextGaussian()) }
    return mapOf("day" to (1..x.size).toList(), "minTemp" to tMin, "maxTemp" to tMax)
}

val dataMap = generateDataMap(a = 2.0)
```

<!---END-->

<!---FUN guideRibbonPlot-->

```kotlin
plot(dataMap) {
    ribbon {
        x("day"<Int>()) { axis.name = "time" }
        yMin("minTemp"<Double>())
        yMax("maxTemp"<Double>())
    }
}
```

<!---END-->

![Ribbon Plot](guideRibbonPlot.svg)

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/ribbon.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/IUovTaQR0LSlg60f5GdSSp" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
