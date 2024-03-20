# Lines

<web-summary>
Kandy Lines Guide: Learn to craft detailed line plots in Kotlin,
perfect for visualizing trends and relationships in data.
This comprehensive guide helps you navigate through various line styles and options.
</web-summary>

<card-summary>
Line Plots in Kotlin with Kandy: A concise guide on creating sophisticated line visualizations, ideal for depicting data trends and correlations.
</card-summary>

<link-summary>
Enhance your Kotlin projects with Kandy's line plot capabilities.
This guide offers insights into creating impactful line charts, showcasing data trends with precision and style.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.Lines-->

```kotlin
val mpgDf =
    DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
mpgDf.head()
```

<table style="header-row">
<tr>
   <td><b>untitled</b></td>
   <td><b>manufacturer</b></td>
   <td><b>model</b></td>
   <td><b>displ</b></td>
   <td><b>year</b></td>
   <td><b>cyl</b></td>
   <td><b>trans</b></td>
   <td><b>drv</b></td>
   <td><b>cty</b></td>
   <td><b>hwy</b></td>
   <td><b>fl</b></td>
   <td><b>class</b></td>
</tr>
<tr>
   <td>1</td>
   <td>audi</td>
   <td>a4</td>
   <td>18,0</td>
   <td>1999</td>
   <td>4</td>
   <td>auto(l5)</td>
   <td>f</td>
   <td>18</td>
   <td>29</td>
   <td>p</td>
   <td>compact</td>
</tr>
<tr>
   <td>2</td>
   <td>audi</td>
   <td>a4</td>
   <td>18,0</td>
   <td>1999</td>
   <td>4</td>
   <td>manual(m5)</td>
   <td>f</td>
   <td>21</td>
   <td>29</td>
   <td>p</td>
   <td>compact</td>
</tr>
<tr>
   <td>3</td>
   <td>audi</td>
   <td>a4</td>
   <td>2,0</td>
   <td>2008</td>
   <td>4</td>
   <td>manual(m6)</td>
   <td>f</td>
   <td>20</td>
   <td>31</td>
   <td>p</td>
   <td>compact</td>
</tr>
<tr>
   <td>4</td>
   <td>audi</td>
   <td>a4</td>
   <td>2,0</td>
   <td>2008</td>
   <td>4</td>
   <td>auto(av)</td>
   <td>f</td>
   <td>21</td>
   <td>30</td>
   <td>p</td>
   <td>compact</td>
</tr>
<tr>
   <td>5</td>
   <td>audi</td>
   <td>a4</td>
   <td>28,0</td>
   <td>1999</td>
   <td>6</td>
   <td>auto(l5)</td>
   <td>f</td>
   <td>16</td>
   <td>26</td>
   <td>p</td>
   <td>compact</td>
</tr>
</table>

## Vertical, horizontal and oblique lines

<!---FUN guideLinesCalculateMedian-->

```kotlin
val ctyMedian = mpgDf.median { cty }
val hwyMedian = mpgDf.median { hwy }
```

<!---END-->

<!---FUN guideLinesSmoothLinear-->

```kotlin
val medianColor = Color.hex("#756bb1")
mpgDf.plot {
    points {
        x(cty)
        y(hwy)
    }
    vLine {
        xIntercept.constant(ctyMedian)
        color = medianColor
        type = LineType.DASHED
    }
    hLine {
        yIntercept.constant(hwyMedian)
        color = medianColor
        type = LineType.DASHED
    }
    smoothLine("cty", "hwy", method = SmoothMethod.Linear()) {
        color = Color.hex("#de2d26")
    }
}
```

<!---END-->

![Vertical, horizontal, and oblique lines](guideLinesSmoothLinear.svg)

## Broken Lines

<!---FUN guideLinesBrokenLinesData-->

```kotlin
fun generateParabolicDataMap(n: Int = 25, a: Double = 1.0): Map<String, List<Double>> {
    val rand = java.util.Random(42)
    val x = List(2 * n + 1) { i -> a * (i - n).toDouble() / n }
    val y = x.map { i -> i * i + rand.nextGaussian() }
    return mapOf("x" to x, "y" to y)
}

val pDataMap = generateParabolicDataMap(a = 3.0)
val xSrc = column<Double>("x")
val ySrc = column<Double>("y")
```

<!---END-->

<!---FUN guideLinesBrokenLinesLinePlot-->

```kotlin
plot(pDataMap) {
    line {
        x(xSrc)
        y(ySrc)
    }
}
```

<!---END-->

![Line Plot](guideLinesBrokenLinesLinePlot.svg)

<!---FUN guideLinesBrokenLinesPathPlot-->

```kotlin
plot(pDataMap) {
    path {
        x(xSrc)
        y(ySrc)
    }
}
```

<!---END-->

![Path Plot](guideLinesBrokenLinesPathPlot.svg)

<!---FUN guideLinesBrokenLinesStepPlot-->

```kotlin
plot(pDataMap) {
    step {
        x(xSrc)
        y(ySrc)
    }
}
```

<!---END-->

![Step Plot](guideLinesBrokenLinesStepPlot.svg)

And what is the difference between `line` and `path`?

Let's have a look at the following example:

<!---FUN guideLinesCompareLinePathSegmentsData-->

```kotlin
fun generateArchimedeanDataMap(n: Int = 25, k: Double = 1.0, a: Double = 1.0): Map<String, List<Double>> {
    val phi = List(n) { i -> 2.0 * PI * k * i.toDouble() / (n - 1) }
    val r = phi.map { angle -> (a * angle) / (2.0 * PI) }
    val x = (r zip phi).map { p -> p.first * cos(p.second) }
    val y = (r zip phi).map { p -> p.first * sin(p.second) }
    return mapOf("x" to x, "y" to y)
}

val aDataMap = generateArchimedeanDataMap(n = 200, k = 2.0)
```

<!---END-->

<!---FUN guideLinesCompareLinePathSegmentsPlotBunch-->

```kotlin
val linePlot = plot(aDataMap) {
    line {
        x(xSrc)
        y(ySrc)
    }
    layout.title = "Line Plot"
}

val pathPlot = plot(aDataMap) {
    path {
        x(xSrc)
        y(ySrc)
    }
    layout.title = "Path Plot"
}

val segments = plot(generateArchimedeanDataMap(n = 50)) {
    segments {
        xBegin(xSrc)
        yBegin(ySrc)
        xEnd.constant(0.0)
        yEnd.constant(0.0)
    }
}
plotBunch {
    add(linePlot, 0, 0, 350, 300)
    add(pathPlot, 350, 0, 350, 300)
    add(segments, 0, 300, 700, 300)
}
```

<!---END-->

![Compare line, path and segments](guideLinesCompareLinePathSegmentsPlotBunch.svg)



<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/lines.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/wQEVUIyGlfq53YtiaZjUPD" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
