# Algebraic Curve

<web-summary>
Discover the elegance of algebraic curves with Kandy's 'Algebraic Curve' example.
This interactive exploration in Kotlin illustrates the simplicity of plotting complex mathematical functions using a raster layer,
showcasing the curve y² = x³ + ax + b.
</web-summary>

<card-summary>
Explore the beauty of elliptic curves in Kotlin with Kandy's 'Algebraic Curve' example.
Effortlessly plot complex algebraic functions,
demonstrating the power of raster layers in visualizing mathematical concepts.
</card-summary>

<link-summary>
Unveil the beauty of elliptic curves using Kandy's 'Algebraic Curve' in Kotlin.
This example adeptly demonstrates plotting intricate algebraic functions with ease.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.AlgebraicCurve-->

<!---FUN guideAlgebraicCurveLinspace-->

```kotlin
fun linspace(start: Double, stop: Double, num: Int): List<Double> {
    return List(num) { i -> start + i * ((stop - start) / (num - 1)) }
}
```

<!---END-->

<!---FUN guideAlgebraicCurveFLevel-->

```kotlin
fun F(x: Double, y: Double, a: Double = 0.0, b: Double = 0.0): Double {
    return y.pow(2) - x.pow(3) - a * x - b
}

fun level(z: Double, c: Double = 1.0): Double {
    return exp(-c * abs(z))
}
```

<!---END-->

<!---FUN guideAlgebraicCurveData-->

```kotlin
val n = 300
val a = -1.0
val b = 0.0
val xRange = linspace(-3.0, 3.0, n + 1)
val yRange = linspace(-3.0, 3.0, n + 1)
val zippedData = xRange.map { x ->
    yRange.map { y -> Triple(x, y, level(F(x, y, a = a, b = b), c = 10.0)) }
}.flatten()
val dataset = mapOf(
    "x" to zippedData.map { it.first },
    "y" to zippedData.map { it.second },
    "z" to zippedData.map { it.third },
)
```

<!---END-->

<!---FUN guideAlgebraicCurvePlot-->

```kotlin
plot(dataset) {
    raster {
        x("x"<Double>())
        y("y"<Double>())
        fillColor("z"<Double>()) {
            scale = continuous(range = Color.hex("#253494")..Color.hex("#ffffcc"))
            legend.type = LegendType.None
        }
    }

    layout {
        title = "Elliptic curve with a = $a, b = $b"
        subtitle = "Simple way to draw an algebraic curve - with `raster` layer"
        size = 800 to 600

        theme(Theme.Classic) {
            blankAxes()
        }
    }
}
```

<!---END-->

![Algebraic Curve Plot](guideAlgebraicCurvePlot.svg)

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/algebraic_curve.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/h5iGaQfTkJudmpdSBaGh7j" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
