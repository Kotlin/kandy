# Histogram Grouped

<web-summary>
Explore 'Histogram Grouped' in Kotlin using Kandy, a clear demonstration of grouping in histogram charts.
This example effectively showcases the comparison of multiple data sets side by side within the same category, ideal for comparative analysis across different groups or variables.
</web-summary>

<card-summary>
'Histogram Grouped' in Kotlin with Kandy: A powerful tool for visual comparison,
this example highlights how to group data sets in histogram charts for a comprehensive comparative view.
</card-summary>

<link-summary>
Dive into 'Histogram Grouped' using Kotlin and Kandy,
where the concept of grouped histogram plotting is used to juxtapose different data sets within the same categories.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Histogram-->

<!---FUN histogram_grouped-->
<tabs>
<tab title="Dataframe">

```kotlin
val random = java.util.Random(42)

val sampleA = List(1000) {random.nextGaussian() * 0.7 + 2.0}
val sampleB = List(1000) {random.nextGaussian() * 1.4 + 3.5}

val df = dataFrameOf(
    "sample" to sampleA + sampleB,
    "group" to sampleA.map { "A" } + sampleB.map { "B" }
)

df.groupBy("group").plot {
    histogram("sample")
}
```

</tab>
<tab title="Collections">

```kotlin
val random = java.util.Random(42)

val sampleA = List(1000) {random.nextGaussian() * 0.7 + 2.0}
val sampleB = List(1000) {random.nextGaussian() * 1.4 + 3.5}

val data = mapOf(
    "sample" to sampleA + sampleB,
    "group" to sampleA.map { "A" } + sampleB.map { "B" }
)

data.plot {
    groupBy("group") {
        histogram("sample")
    }
}
```

</tab></tabs>
<!---END-->

![Histogram Grouped](histogram_grouped.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/histogram/histogram_grouped.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/NTXK23kNjizpzQqHh4IhoH" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
