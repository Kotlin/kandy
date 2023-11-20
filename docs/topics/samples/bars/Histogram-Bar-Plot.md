# Histogram Plot

<web-summary>
Explore 'Histogram Plot' in Kotlin using Kandy to understand the distribution of a dataset.
This example demonstrates the effective use of histograms in visualizing the frequency of data points.
</web-summary>

<card-summary>
'Histogram Plot' in Kotlin with Kandy: A clear and straightforward representation of data distribution,
perfect for statistical analysis and understanding data trends.
</card-summary>

<link-summary>
Dive into 'Histogram Plot' using Kotlin and Kandy, a fundamental tool in data analysis for visualizing the distribution and frequency of dataset values."
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Bars-->

<tabs>
<tab title="First option">
<!---FUN histogram_1-->

```kotlin
val random = java.util.Random(1111)
val sample = List(1000) { random.nextGaussian() }

dataFrameOf("sample" to sample).histogram("sample")
```

<!---END-->
</tab>
<tab title="Second option">
<!---FUN histogram_2-->

```kotlin
val random = java.util.Random(1111)
val sample = List(1000) { random.nextGaussian() }

plot {
    histogram(sample)
}
```

<!---END-->

</tab>
<tab title="Third option">
<!---FUN histogram_3-->

```kotlin
val random = java.util.Random(1111)
val sample = List(1000) { random.nextGaussian() }

plot {
    statBin(sample) {
        bars {
            x(Stat.x)
            y(Stat.count)
        }
    }
}
```

<!---END-->
</tab>
</tabs>

![Histogram Plot](histogram_2.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/bars/histogram_bar_plot.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/MXxEf1q7ACuzgCkFtV0bkT" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
