# Ktnb Logo Tiles

<web-summary>
Experience creative data visualization with 'Ktnb Logo Tiles' in Kandy.
This unique example artfully combines the use of tiles to form the Ktnb logo, showcasing the versatility of data representation.
</web-summary>

<card-summary>
'Ktnb Logo Tiles' in Kandy artfully demonstrates how tiles can be used to create visually striking patterns and images,
like the Ktnb logo, offering a novel take on data visualization.
</card-summary>

<link-summary>
Explore the artistic side of data with 'Ktnb Logo Tiles' in Kandy.
This imaginative example uses tile plots to form the Ktnb logo, highlighting innovative ways to present data.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Tiles-->

<!---FUN tiles_ktnb_logo-->

```kotlin
val zipperCells = (0 until 40).flatMap { x ->
    val n = 3
    val sector = if (x <= 15) {
        0
    } else if (x <= 23) {
        1
    } else 2
    when (sector) {
        0 -> {
            (0 until n).map { y -> x to x + (x - 1) / 2 + y }
        }

        1 -> {
            (0 until n).map { y -> x to 38 - x + y }
        }

        else -> {
            (0 until n).map { y -> x to x - 11 + (x - 19) / 2 + y }
        }
    }
}

val cells = (0 until 40).flatMap { x -> (0 until 40).map { y -> x to y } }.filter {
    it !in zipperCells
}

val xs = cells.map { it.first }
val ys = cells.map { it.second }
val colorFactor = xs.zip(ys).map { it.first + it.second }

plot {
    tiles {
        x(xs)
        y(ys)
        fillColor(colorFactor) {
            scale = continuous(Color.hex("#7d52fc")..Color.hex("#e34860"))
            legend.type = LegendType.None
        }
    }
    layout {
        style(Style.Void)
    }
}
```

<!---END-->

![Ktnb Logo Tiles](tiles_ktnb_logo.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/tiles/tiles_ktnb_logo.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/XLj6Nz9GK2DCD6ORUyXyOA" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
