# Statistics Guide

<web-summary>
Delve into the versatile world of data visualization with Kandy's 'Statistics' guide.
Learn to perform statistical pre-transformations within your plots, enhancing data analysis and visual clarity.
</web-summary>

<card-summary>
Statistical transformations in Kandy.
This guide demonstrates how to use built-in functions for insightful and visually striking data representations.
</card-summary>

<link-summary>
Explore the 'Statistics' guide in Kandy for in-depth data analysis.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.StatisticsGuide-->


`kandy-statistics` allows you to build statistical plots, i.e., plots with statistical transformations of data.
With them, you can explore your data in a better way as well as visualize important statistical observations.

## How do statistics work?

The process of statistical transformations is straightforward and intuitive.
You have some dataset — it can be a single `List` or a whole `DataFrame`.
Statistics consume one or more sets of values (`List`, `DataColumn`) from this dataset and
import a new dataset with the transformed data.
Then this dataset is used for visualization.
Kandy has an API for explicit work with this dataset as well as more simplified for quick plotting.

### `statBin` anatomy example

Let's look at an example.
The `bin` statistic is one of the most used — it allows you to split observations by bins and
count the number of observations in each one.
It is used to construct one of the most common statistical plots — histogram.
But before we build a histogram, let's examine the statistics.

<!---FUN guideStatGenerateSampleAndWeights-->

```kotlin
// Generate sample from normal distribution
val sample = NormalDistribution().sample(1000).toList()
// Generate weights from uniform distribution
val weights = UniformRealDistribution(0.0, 1.0).sample(1000).toList()
```

<!---END-->

Each statistic has several types of arguments:

1. Main inputs — one or more sets of values (usually named `x`, `y`, `z`) on which the statistic is counted —
   these are the only mandatory arguments.
   All inputs must be of the same size.
2. weight — some statistics are weighted,
   i.e., the weight of each element will be taken into account. To pass it, the optional argument `weights` is used.
   This set must have the same size as the main inputs.
3. Statistics parameters.
   Each statistic has its unique parameters, on which its calculation depends directly.
   All of them have a default value.

Let's look at the checklist of these arguments for `statBin`:

1. `statBin` consumes exactly one values set — sample of values to bin (`x`).
2. It's weighted. In addition, to `count` (i.e., the number of observations within bin) `statBin` counts `countWeighted`
   statistic, i.e., the weighted count refers to the total sum of the observation weights within a specific bin. To
   calculate this, pass `weights` set of the same size as the sample.
3. `statBin` has two parameters, both of which configure bins
    * `binOptions` - allows you to specify either the number of bins or their width.
    * `binAlign` - sets the alignment of the bin.

Let's use it on our sample...

<!---FUN guideStatBinData-->

```kotlin
val statBinData = statBin(
    sample, // Pass a sample as an input
    null, // Don't provide weights
    BinsOption.byNumber(20), // Set the number of bins
    BinsAlign.center(0.0) // Set the align of bins
)
```

<!---END-->

...and take a look at the output dataset:

```kotlin
statBinData
```

<table>
<tr><td>Stat</td></tr>
<tr>
<td>
<table>
<tr><td>x</td><td>count</td><td>countWeighted</td><td>density</td><td>densityWeighted</td></tr>
<tr><td>-3.140605</td><td>2</td><td>2.0</td><td>0.006368</td><td>0.006368</td></tr>
<tr><td>-2.826544</td><td>0</td><td>0.0</td><td>0.000000</td><td>0.000000</td></tr>
<tr><td>-2.512484</td><td>2</td><td>2.0</td><td>0.006368</td><td>0.006368</td></tr>
<tr><td>-2.198423</td><td>14</td><td>14.0</td><td>0.044577</td><td>0.044577</td></tr>
<tr><td>-1.884363</td><td>21</td><td>21.0</td><td>0.066866</td><td>0.066866</td></tr>
</table>
</td>
</tr>
</table>

As you can see, we got a `DataFrame` with one `ColumnGroup` called `Stat` which contains several columns with statics.
For `statBin`, each row corresponds to one bin.
`Stat.x` is the column with the centers of the bins;
`Stat.count` contains the number of observations in the bin.
`Stat.countWeighted` - weighted version of `count` (but since we do not pass weights,
this column differs from the previous one only in type - `Double` instead of `Int`; values are the same).
There are also `Stat.density` and `Stat.densityWeighted`.
They contain empirically estimated density (both normal and weighted) of the sample in the points corresponding
to the centers of bins.

### Awesome! But what about plotting?

As mentioned earlier, `statBin` is used to plot a histogram.
And now, having our new dataset,
it is really easy to build it — for a classic histogram we need bars with coordinates (x: bin center (i.e. `Stat.x`),
y = bin count (i.e. `Stat.count`)):

<!---FUN guideStatBinDataPlot-->

```kotlin
statBinData.plot {
    bars {
        x(Stat.x)
        y(Stat.count)
    }
    layout.title = "Our awesome histogram!"
}
```

<!---END-->

![StatBin Plot](guideStatBinDataPlot.svg)

Of course, we won't need to explicitly calculate a new dataset every time.
Moreover, we will not need to define the histogram manually again each time.
There are different types of APIs for this purpose, which are described in the next chapter.

## Statistics APIs

### Stat-transform API

"Stat-transform" API allows you to transform a dataset right inside `PlotBuilder`, calculating stats on the fly.
It is essentially a set of extensions for `PlotBuilder` that have the usual statistics API (input samples, weights and
parameters) but also open a new context. As usual, new layers can be created in this context, but within it, they will
have a new dataset — a dataset with a statistical transformation.

<!---FUN guideStatDfFromSampleAndWeights-->

```kotlin
val df = dataFrameOf("sample" to sample, "weights" to weights)
```

<!---END-->

<!---FUN guideStatDfStatBinAndVLinePlot-->

```kotlin
df.plot {
    statBin(sample, weights, binsOption = BinsOption.byWidth(0.25)) {
        // New `StatBin` dataset inside this context
        line {
            // The old dataset is not actual, so we can use `Stat.` columns of a new one
            x(Stat.x)
            y(Stat.density)
        }
    }
    // Dataset hasn't changed here, so we can use it in the usual way
    vLine {
        xIntercept.constant(sample.mean())
        width = 3.0
        color = Color.RED
    }
}
```

<!---END-->

![StatBin with vLine](guideStatDfStatBinAndVLinePlot.svg)

### Stat-layers API

"Stat-layers" API is a set of shortcuts for the most popular statistical graphs (such as a histogram); it's an
integration of "stat-transform" API and regular layers — with just one function we can plot a statistical layer (i.e.,
it's an amalgamation of three whole things — stat counting, layer creation and default mappings)

<!---FUN guideStatSimpleHistogram-->

```kotlin
plot {
    // Equal to `statBin` + `bars` + x/y mappings on Stat.x/Stat.count
    histogram(sample)
}
```

<!---END-->

![Histogram](guideStatSimpleHistogram.svg)

Everything is the same, however, three times less code! But that doesn't mean we lose flexibility. First of
all, `.histogram()` has all the same arguments as `.statBin()`, which means we can fully control the counting of
statistics. Second, it optionally creates a new context — a union of `bars` and `statBin` contexts. This will allow you
to customize `bars` (including overriding default mappings!).

<!---FUN guideStatCustomizedHistogram-->

```kotlin
plot {
    histogram(sample, weights, binsAlign = BinsAlign.center(0.0)) {
        // This context combines `bars` and `statBin` context; that means we can
        // make `bars` mappings and use `Stat.` columns.
        // By default, `Stat.count` is mapped on `y` if weights are not provided;
        // however, we can easily override mapping to `y`, for example, from `Stat.density`
        y(Stat.density)
        fillColor(Stat.density) {
            scale = continuous(Color.GREEN..Color.RED)
        }
    }
    x.axis.limits = -3.5..3.5
}
```

<!---END-->

![Customised Histogram](guideStatCustomizedHistogram.svg)

### Stat-plots API

"Stats-plots" API allows you to build a histogram even faster — only with one function! Usually it is a function or set
of extensions for a `DataFrame` with standard statistic arguments (inputs, weights, parameters).

<!---FUN guideStatSimpleHistogram2-->

```kotlin
histogram(sample)
```

<!---END-->

![Histogram from Iterable](guideStatSimpleHistogram2.svg)

or

<!---FUN guideStatSimpleHistogram3-->

```kotlin
df.histogram("sample", binsOption = BinsOption.byNumber(10))
```

<!---END-->

![Histogram with configured bins](guideStatSimpleHistogram3.svg)

Column selection DSL for stat plots is slightly different from the standard one.
You can still open a new scope in which you can access the columns of the dataframe.
However, unlike the classic one, you must not return the columns as the result of the expression,
but rather access the inputs of the statistics through the function of the same name.
Weights are provided in the same way.

<!---FUN guideStatSimpleHistogram4-->

```kotlin
df.histogram {
    x(sample)
    weight(weights)
}
```

<!---END-->

![Histogram with weights](guideStatSimpleHistogram4.svg)

And stat plots can be configured. We can configure layer mappings and settings exactly as in stat layer, and
also change the general settings of the plot. The `.configure()` extension is used for this purpose — it opens a context
that combines several contexts you are familiar with — stat context, layer context and plot context:

<!---FUN guideStatConfiguredHistogram4-->

```kotlin
df.histogram(BinsOption.byNumber(14), BinsAlign.boundary(0.0)) {
    x(sample)
}.configure {
    // StatBin + Bars + Plot contexts
    x.axis.limits = -3.5..3.5
    y(Stat.density)
    borderLine.color = Color.BLACK
    layout.title = "Configured histogram"
}
```

<!---END-->

![Configured Histogram](guideStatConfiguredHistogram4.svg)

### Statistics and grouped data

Everything described above works with grouped data as well. Statistics are calculated independently inside each group
(however, sometimes not exactly; for example, to plot a histogram, we want the centers of bins in different groups to be
equals for better plotting). Thus, a statistical transformation for `GroupBy` will return a `GroupBy` with the same
keys, but instead of the original datasets we will have a `Stat` dataframes.

Let's make sure of that:

<!---FUN guideStatGenerateSampleAAndSampleB-->

```kotlin
// Generate two samples from a normal distribution with different mean/std
val sampleA = NormalDistribution(1.5, 1.0).sample(1000).toList()
val sampleB = NormalDistribution(4.0, 2.0).sample(1000).toList()

// Gather them into `DataFrame` with "A" and "B" keys in the "category" column
val dfAB = dataFrameOf(
    "sample" to sampleA + sampleB,
    "type" to List(1000) { "A" } + List(1000) { "B" }
)
```

<!---END-->

```kotlin
val gbAB = dfAB.groupBy { type }
gbAB
```

<table>
<tr><td>type</td><td>group</td></tr>
<tr><td>A</td><td>
<table>
<tr><td>sample</td><td>type</td></tr>
<tr><td>0.481969</td><td>A</td></tr>
<tr><td>0.849284</td><td>A</td></tr>
<tr><td>5.044135</td><td>A</td></tr>
<tr><td>-0.037175</td><td>A</td></tr>
<tr><td>1.547424</td><td>A</td></tr>
</table>
</td></tr>
<tr><td>B</td><td>
<table>
<tr><td>sample</td><td>type</td></tr>
<tr><td>2.394755</td><td>B</td></tr>
<tr><td>3.440403</td><td>B</td></tr>
<tr><td>7.718361</td><td>B</td></tr>
<tr><td>4.209521</td><td>B</td></tr>
<tr><td>2.030533</td><td>B</td></tr>
</table>
</td></tr>
</table>

```kotlin
gbAB.statBin("sample")
```

<table>
<tr><td>type</td><td>group</td></tr>
<tr><td>A</td><td>
<table>
<tr><td>Stat</td></tr>
<tr><td>{ x: -2.8, count: 0, countWeigh..., ... }</td></tr>
<tr><td>{ x: -2.1, count: 0, countWeigh..., ... }</td></tr>
<tr><td>{ x: -1.4, count: 4, countWeigh..., ... }</td></tr>
<tr><td>{ x: -0.7, count: 22, countWeigh..., ... }</td></tr>
<tr><td>{ x: 0.0, count: 99, countWeigh..., ... }</td></tr>
</table>
</td></tr>
<tr><td>B</td><td>
<table>
<tr><td>Stat</td></tr>
<tr><td>{ x: -2.8, count: 2, countWeigh..., ... }</td></tr>
<tr><td>{ x: -2.1, count: 2, countWeigh..., ... }</td></tr>
<tr><td>{ x: -2.4, count: 2, countWeigh..., ... }</td></tr>
<tr><td>{ x: -0.7, count: 8, countWeigh..., ... }</td></tr>
<tr><td>{ x: 0.0, count: 20, countWeigh..., ... }</td></tr>
</table>
</td></tr>
</table>


As you can see, we did indeed do a `statBin` transformation within groups, the grouping keys did not change.

The plotting process isn't much different either. As usual, different sets of points are plotted for different groups.
Within the new "stat" context, we also can access columns corresponding to the grouping keys. Also, we can configure
position inside the layer.


<!---FUN guideStatGroupedStatBinPlot-->

```kotlin
gbAB.plot {
    statBin(sample) {
        bars {
            x(Stat.x)
            y(Stat.count)
            fillColor(type)
            borderLine.width = 0.0
            position = Position.dodge()
        }
        line {
            x(Stat.x)
            y(Stat.count)
            color(type)
        }
    }
}
```

<!---END-->

![statBin with Bars and Line](guideStatGroupedStatBinPlot.svg)

For `histogram` layer, this also works. Moreover, if we have exactly one grouping key,
it will be mapped to `fillColor` by default:

<!---FUN guideStatGroupedHistogram-->

```kotlin
gbAB.plot {
    histogram(sample)
}
```

<!---END-->

![Histogram on the Grouped Data](guideStatGroupedHistogram.svg)

And we can customize it:

<!---FUN guideStatCustomizedGroupedHistogram-->

```kotlin
gbAB.plot {
    histogram(sample, binsOption = BinsOption.byNumber(12)) {
        fillColor(type)
        borderLine.color = Color.BLACK
        position = Position.stack()
    }
}
```

<!---END-->

![Customized Histogram on the Grouped Data](guideStatCustomizedGroupedHistogram.svg)

And `GroupBy` has a `.histogram()` extension that works exactly like one for `DataFrame` and can be configured the same
way:

<!---FUN guideStatSimpleGroupedHistogram-->

```kotlin
gbAB.histogram("sample")
```

<!---END-->

![Simple Histogram on the Grouped Data](guideStatSimpleGroupedHistogram.svg)

<!---FUN guideStatConfiguredGroupedHistogram-->

```kotlin
gbAB.histogram(BinsOption.byNumber(20), binsAlign = BinsAlign.boundary(0.0)) {
    x(sample)
}.configure {
    fillColor(type) {
        scale = categorical(listOf(Color.GREEN, Color.ORANGE))
    }
    layout {
        size = 650 to 350
        title = "Configured grouped histogram!"
    }
}
```

<!---END-->

![Configured Histogram on the Grouped Data](guideStatConfiguredGroupedHistogram.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/statistics_guide.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/1pLl2eMo1hN8Ujn2vIK3VP" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
