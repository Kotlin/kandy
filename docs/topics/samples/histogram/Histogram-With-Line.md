# Histogram With Line

<web-summary>
Explore 'Histogram With Line' in Kotlin using Kandy, where histogram is enhanced with line.
This example effectively showcases the usage of statistics with several layers, useful when you want to visualise distribution in several ways on one plot.
</web-summary>

<card-summary>
'Histogram With Line' in Kotlin with Kandy: A powerful tool for visualising a distribution with several layers.
</card-summary>

<link-summary>
Dive into 'Histogram With Line' using Kotlin and Kandy, showing multiple layers with statistical data transformation.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Histogram-->


<!---FUN histogram_with_line-->
<tabs>
<tab title="Dataframe">

```kotlin
val random = java.util.Random(42)

val sampleDf = dataFrameOf(
    "sample" to List(1000) { random.nextGaussian() }
)

sampleDf.plot {
    statBin("sample", binsOption = BinsOption.byNumber(15)) {
        bars {
            alpha = 0.9
            x(Stat.x)
            y(Stat.count)
        }
        line {
            x(Stat.x)
            y(Stat.count)
            color = Color.RED
            width = 1.5
            type = LineType.LONGDASH
        }
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val random = java.util.Random(42)

val sample = List(1000) { random.nextGaussian() }

plot {
    statBin(sample, binsOption = BinsOption.byNumber(15)) {
        bars {
            alpha = 0.9
            x(Stat.x)
            y(Stat.count)
        }
        line {
            x(Stat.x)
            y(Stat.count)
            color = Color.RED
            width = 1.5
            type = LineType.LONGDASH
        }
    }
}
```

</tab></tabs>
<!---END-->


![Histogram With Line](histogram_with_line.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/histogram/histogram_with_line.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/CaLnKJdWJWISnAHqhjevNV" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
