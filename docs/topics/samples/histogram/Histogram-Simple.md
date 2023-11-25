# Histogram Simple

<web-summary>
Explore 'Histogram Simple' in Kotlin using Kandy to understand the distribution of a dataset.
This example demonstrates the effective use of histograms in visualizing the frequency of data points.
</web-summary>

<card-summary>
'Histogram Simple' in Kotlin with Kandy: A clear and straightforward representation of data distribution,
perfect for statistical analysis and understanding data trends.
</card-summary>

<link-summary>
Dive into 'Histogram Simple' using Kotlin and Kandy, a fundamental tool in data analysis for visualizing the distribution and frequency of dataset values.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Histogram-->

<!---FUN histogram_simple-->
<tabs>
<tab title="Dataframe">

```kotlin
val random = java.util.Random(42)

val dataframe = dataFrameOf(
    "sample" to List(1000) { random.nextGaussian() }
)

dataframe.plot {
    histogram("sample")
}
```

</tab>
<tab title="Collections">

```kotlin
val random = java.util.Random(42)

val sample = List(1000) { random.nextGaussian() }

plot {
    histogram(sample)
}
```

</tab></tabs>
<!---END-->


![Histogram Simple](histogram_simple.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/histogram/histogram_simple.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/c0z2x3H4svD595YuuqpexU" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
