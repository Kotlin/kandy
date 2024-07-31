# Histogram

<web-summary>
Delve into Kandy's 'Histogram' guide to understand the nuances of visualizing frequency distributions.
Learn how to effectively represent data variations and trends.
</web-summary>

<card-summary>
Kandy's 'Histogram' guide offers a deep dive into frequency visualization.
Discover how to bring data distributions to life in your plots.
</card-summary>

<link-summary>
Explore Kandy's guide on creating histograms for insightful data analysis.
Learn the keys to visualizing and interpreting frequency distributions.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.Histogram-->

Statistics "bin" are counted on the sample of a single continuous variable.
Firstly, it divides the range of values into bins (sequential, non-overlapping sections),
and then it counts the number of observations in each bin.
It's weighted, it means the weighted count for each bin is calculated
(each element within a bin counted along with its weight).
It's really important to carefully choose a bin-constructing method
(for example, by the exact number of bins or by their width).
This decision has a big impact on how the data is shown and studied.
It makes sure that the way the data is shown is natural to understand and gives a true picture of the information.

This notebook uses definitions from [DataFrame](https://kotlin.github.io/dataframe/overview.html).

## Usage

Binning is commonly used in statistics and data analysis
to simplify complex data sets and make them easier to interpret.
Histogram (or any other plot with "bin" statistics) helps to give an overview of the sample distribution.

## Arguments

* Input (mandatory):
    - `x` — numeric sample on which the statistics are calculated
* Weights (optional):
    - `weights` — set of weights of the same size as the input sample.
      `null` (by default) means all weights are equal to `1.0` and the weighted count is equal to the normal one
* Parameters (optional):
    - `binsOption: BinsOption` — specifies either the number of bins or their width:
        - `BinsOption.byNumber(n: Int)` — values are divided into `n` bins (bins width is derived)
        - `BinsOption.byWidth(width: Double)` — values are divided into bins of width `width`
          (the number of bins is derived)
    - `binsAlign: BinsAlign` — specifies bins aligning:
        - `BinsAlign.center(pos: Double)` — bins are aligned by centering bin in `pos`
        - `BinsAlign.boundary(pos: Double)` — bins are aligned by boundary between two bins in `pos`
        - `BinsAlign.none()` — no aligning

### Generalized signature

The specific signature depends on the function,
but all functions related to "bin" statistic
(which will be discussed further below - different variations of `statBin()`, `histogram()`)
have approximately the same signature with the arguments above:

```
statBinArgs := 
   x, 
   weights = null, 
   binsOption: BinsOption = BinsOption.byNumber(20), 
   binsAlign: BinsAlign = BinsAlign.center(0.0)
```

The possible types of `x` and `weights` depend on where a certain function is used.
They can be simply `Iterable` (`List`, `Set`, etc.) or a reference to a column in a `DataFrame`
(`String`, `ColumnAccessor`) or the `DataColumn` itself.

## Output statistics

| name                 | type   | description                                              |
|----------------------|--------|----------------------------------------------------------|
| Stat.x               | Double | Center of bin                                            |
| Stat.count           | Int    | Number of observations in this bin                       |
| Stat.countWeighted   | Double | Weighted count (sum of observations weights in this bin) |
| Stat.density         | Double | Empirically estimated density in this bin                |
| Stat.densityWeighted | Double | Weighted density                                         |

## StatBin plots

<!---FUN guideHistogramGenerateData-->

```kotlin
// Generate sample from normal distribution
val depthList = NormalDistribution(500.0, 100.0).sample(1000).toList()
// Generate sample from uniform distribution
val coeffList = UniformRealDistribution(0.0, 1.0).sample(1000).toList()
// gather them into the DataFrame
val df = dataFrameOf(
    "depth" to depthList,
    "coeff" to coeffList
)
df.head()
```

<!---END-->

| depth   | coeff |
|---------|-------|
| 458.195 | 0.343 |
| 336.811 | 0.807 |
| 762.538 | 0.101 |
| 692.733 | 0.51  |
| 424.594 | 0.873 |

`df` has a signature

| depth | coeff |
|-------|-------|

Let's take a look at `StatBin` output DataFrame:


<!---FUN guideHistogramStatBin-->

```kotlin
df.statBin("depth", "coeff", binsOption = BinsOption.byNumber(10))
```

<!---END-->

<table>
<tr><td><b>Stat</b></td></tr>
<tr><td>
<table>
<tr><td>x</td><td>count</td><td>countWeighted</td><td>density</td><td>densityWeighted</td></tr>
<tr><td>167.131</td><td>1</td><td>0.325</td><td>0</td><td>0</td></tr>
<tr><td>233.984</td><td>8</td><td>3.68</td><td>0</td><td>0</td></tr>
<tr><td>300.836</td><td>33</td><td>18.901</td><td>0</td><td>0.001</td></tr>
<tr><td>367.689</td><td>110</td><td>57.011</td><td>0.002</td><td>0.002</td></tr>
<tr><td>434.541</td><td>216</td><td>112.568</td><td>0.003</td><td>0.003</td></tr>
</table>
</td></tr>
</table>

It has the following signature:

<table>
  <thead>
    <tr>
      <th alignt="left" colspan="5">Stat</th>
    </tr>
  </thead>
  <thead>
    <tr>
      <th>x</th>
      <th>count</th>
      <th>countWeighted</th>
      <th>density</th>
      <th>densityWeighted</th>
    </tr>
  </thead>
</table>


As you can see, we got a `DataFrame` with one `ColumnGroup` called `Stat` which contains several columns with statics.
For `statBin`, each row corresponds to one bin.
`Stat.x` is the column with the centers of the bins.
`Stat.count` contains the number of observations in the bin.
`Stat.countWeighted` — weighted version of `count`.
There are also `Stat.density` and `Stat.densityWeighted`.
They contain empirically estimated density (both normal and weighted)
of the sample in the points corresponding to the centers of bins.

`DataFrame` with "bin" statistics is called `StatBinFrame`

### `statBin` context transform

`statBin(statBinArgs) { /*new plotting context*/ }` modifies a plotting context — instead of original data
(no matter was it empty or not) new `StatBin` dataset (calculated on given arguments.
Inputs and weights can be provided as `Iterable` or as dataset column reference — by name as a `String`,
as a `ColumnReference` or as a `DataColumn`) is used inside a new context
(original dataset and primary context are not affected —
you can add layers using initial dataset outside the `statBin` context).
Since the old dataset is irrelevant, we cannot use references for its columns.
But we can refer to the new ones.
They are all contained in the `Stat` group and can be called inside the new context:


<!---FUN guideHistogramStatBinAreaPlot-->

```kotlin
plot {
    statBin(depthList, binsAlign = BinsAlign.center(500.0)) {
        // new `StatBin` dataset here
        area {
            // use `Stat.*` columns for mappings
            x(Stat.x)
            y(Stat.count)
            fillColor = Color.RED
            alpha = 0.5
        }
    }
}
```

<!---END-->

![StatBin with Area Plot](guideHistogramStatBinAreaPlot.svg)

### Histogram layer

A histogram is a statistical chart that serves to visually approximate the distribution of a numerical variable.
It's a bar plot where each bar is representing a bin: its x coordinate is corresponding to bin range and y to count.
So basically, we can build a histogram with `statBin` as follows:

<!---FUN guideHistogramStatBinBarsPlot-->

```kotlin
val statBinBarsPlot = df.plot {
    statBin("depth") {
        bars {
            x(Stat.x)
            y(Stat.count)
        }
    }
    layout.title = "`statBin` + `bars`"
}
statBinBarsPlot
```

<!---END-->

![StatBin Bars Plot](guideHistogramStatBinBarsPlot.svg)

But we can do it even faster with `histogram(statBinArgs)` method:

<!---FUN guideHistogramHistPlot-->

```kotlin
val histogramPlot = plot {
    histogram(depthList)
    layout.title = "`histogram`"
}
histogramPlot
```

<!---END-->

![Histogram Plot](guideHistogramHistPlot.svg)

Let's compare them:

<!---FUN guideHistogramCompareBarsVsHist-->

```kotlin
plotGrid(listOf(statBinBarsPlot, histogramPlot))
```

<!---END-->

![Compare Histogram vs StatBin Bar Plots](guideHistogramCompareBarsVsHist.svg)

These two plots are identical.
Indeed, `histogram` just uses `statBin` and `bars` and performs coordinates mappings under the hood.
And we can customize histogram layer: `histogram()` optionally opens a new context,
where we can configure bars (as in the usual context opened by `bars { ... }`) —
even change coordinate mappings from default ones.
`StatBin` dataset of histogram is also can be accessed here.

<!---FUN guideHistogramHistFillColor-->

```kotlin
df.plot {
    histogram(depth, binsAlign = BinsAlign.center(500.0)) {
        // Change a column mapped on `y` to `Stat.density`
        y(Stat.density)
        // Filling color depends on `density` statistic
        fillColor(Stat.density) {
            scale = continuous(Color.YELLOW..Color.RED)
        }
        borderLine.color = Color.BLACK
    }
}
```

<!---END-->

![Customized Histogram Plot](guideHistogramHistFillColor.svg)

If we specify weights, `Stat.countWeighted` is mapped to `y` by default:

<!---FUN guideHistogramSmoothLineWithPoints-->

```kotlin
df.plot {
    // Count sample mean
    val mean = depth.mean()
    // Add weighted histogram
    histogram(depth, coeff, binsOption = BinsOption.byNumber(10), binsAlign = BinsAlign.boundary(mean))
    // We can add other layers as well.
    // Let's add a vertical mark line in the mean of sample
    vLine {
        xIntercept.constant(mean)
        tooltips { line("Depth mean: ${String.format("%.2f", mean)}m") }
        color = Color.RED; width = 3.0
    }
    x.axis.name = "depth, m"
}
```

<!---END-->

![Histogram with mapping on countWeighted](guideHistogramSmoothLineWithPoints.svg)

### `histogram` plot

`histogram(statBinArgs)` and `DataFrame.histogram(statBinArgs)` are a family of functions for fast plotting a histogram.

<!---FUN guideHistogramSimpleHistPlot-->

```kotlin
histogram(depthList, binsAlign = BinsAlign.center(500.0))
```

<!---END-->

![Simple Histogram Plot](guideHistogramSimpleHistPlot.svg)



<!---FUN guideHistogramSimpleHistOnDf-->

```kotlin
df.histogram("depth")
```

<!---END-->

![Simple Histogram on DataFrame](guideHistogramSimpleHistOnDf.svg)

In case you want to provide inputs and weights using column selection DSL, it's a bit different from the usual one —
you should assign `x` input and (optionally) `weight` throw invocation eponymous functions:

<!---FUN guideHistogramHistWithWeight-->

```kotlin
df.histogram(binsOption = BinsOption.byNumber(10)) {
    x(depth)
    weight(coeff)
}
```

<!---END-->

![Histogram with mapping on DataColumns](guideHistogramHistWithWeight.svg)

Histogram plot can be configured with `.configure {}` extension —
it opens a context that combines bars, `StatBin` and plot context.
That means you can configure bars settings, mappings using `StatBin` dataset and any plot adjustments:

<!---FUN guideHistogramConfiguredHistPlot-->

```kotlin
df.histogram(binsOption = BinsOption.byNumber(15)) {
    x(depth)
}.configure {
    // Bars + StatBin + PlotBuilder
    // Can't add a new layer
    x.limits = 100..900
    // Can add bar mapping, include on `Stat.*` columns
    fillColor(Stat.count) { scale = continuous(Color.GREEN..Color.RED) }
    // Can configure general plot adjustments
    layout {
        title = "Configured histogram plot"
        size = 600 to 350
    }
}
```

<!---END-->

![Configured Histogram Plot](guideHistogramConfiguredHistPlot.svg)

## Grouped `staBin`

`statBin` can be applied for grouped data — statistics will be counted on each group independently but with equal bins.
This application returns a new `GroupBy`
dataset with the same keys as the old one but with `StatBin` groups instead of old ones.

<!---FUN guideHistogramGenerateGropedData-->

```kotlin
// Create two samples from normal distribution with different mean/std
val rangesA = NormalDistribution(500.0, 100.0).sample(5000).toList()
val rangesB = NormalDistribution(400.0, 80.0).sample(5000).toList()

// Gather them into `DataFrame` with "A" and "B" keys in the "category" column
val rangesDF = dataFrameOf(
    "range" to rangesA + rangesB,
    "category" to List(5000) { "A" } + List(5000) { "B" }
)
rangesDF.head(5)
```

<!---END-->

| range   | category |
|---------|----------|
| 347.452 | A        |
| 467.839 | A        |
| 527.679 | A        |
| 538.295 | A        |
| 654.991 | A        |

It has the following signature:

| range | category |
|-------|----------|

<!---FUN guideHistogramGroupedData-->

```kotlin
// Group it by "category"
val groupedRangesDF = rangesDF.groupBy { category }
groupedRangesDF
```

<!---END-->

<table>
<tr><td>category</td><td>group</td></tr>
<tr><td>A</td><td>
<table>
<tr><td>range</td><td>category</td></tr>
<tr><td>527.679</td><td>A</td></tr>
<tr><td>654.991</td><td>A</td></tr>
<tr><td>538.295</td><td>A</td></tr>
<tr><td>467.839</td><td>A</td></tr>
<tr><td>347.452</td><td>A</td></tr>
</table>
</td></tr>
<tr><td>B</td><td>
<table>
<tr><td>range</td><td>category</td></tr>
<tr><td>377.8</td><td>B</td></tr>
<tr><td>266.069</td><td>B</td></tr>
<tr><td>306.389</td><td>B</td></tr>
<tr><td>543.127</td><td>B</td></tr>
<tr><td>482.897</td><td>B</td></tr>
</table>
</td></tr>
</table>


Now we have a `GroupBy` with a signature

<table>
  <thead>
    <tr>
      <th>key: [category]</th>
      <th>group: DataFrame[range|category]</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>A</td>
      <td>A-Group</td>
    </tr>
    <tr>
      <td>B</td>
      <td>B-Group</td>
    </tr>
  </tbody>
</table>

<!---FUN guideHistogramGroupedStatBin-->

```kotlin
groupedRangesDF.statBin { x(range) }
```

<!---END-->


<table>
<tr><td>category</td><td>group</td></tr>
<tr><td>A</td><td>
<table>
<tr><td>Stat</td></tr>
<tr><td>{ x: 116.91, count: 1, countWeighted: 1, ... }</td></tr>
</table>
</td></tr>
<tr><td>B</td><td>
<table>
<tr><td>Stat</td></tr>
<tr><td>{ x: 116.91, count: 5, countWeighted: 5, ... }</td></tr>
</table>
</td></tr>
</table>

After `statBin` applying it's still a `GroupBy` but with different signature of `group` —
all groups have the same signature as usual `DataFrame` after `statBin` applying (i.e. `StatBinFrame`):

<table>
  <thead>
    <tr>
      <th>key: [category]</th>
      <th>group: StaBinFrame</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>A</td>
      <td>A-Group</td>
    </tr>
    <tr>
      <td>B</td>
      <td>B-Group</td>
    </tr>
  </tbody>
</table>

As you can see, we did indeed do a `statBin` transformation within groups, the grouping keys did not change.
Also, all bin centers match — it helps to build grouped histogram.

The plotting process doesn't change much — we do everything the same.

<!---FUN guideHistogramGroupedStatBinAreaPlot-->

```kotlin
groupedRangesDF.plot {
    statBin(range) {
        area {
            x(Stat.x)
            y(Stat.density)
        }
    }
}
```

<!---END-->

![Grouped StatBin Plot](guideHistogramGroupedStatBinAreaPlot.svg)

As you can see, there are two areas because we have two groups of data.
To distinguish them, we need to add mapping to the filling color from the key.
This is convenient — the key is available in the context

<!---FUN guideHistogramGroupedStatBinAreaWithColor-->

```kotlin
groupedRangesDF.plot {
    statBin(range) {
        area {
            x(Stat.x)
            y(Stat.density)
            // can access "key." columns and create mapping from them
            fillColor(category)
            alpha = 0.6
        }
    }
}
```

<!---END-->

![StatBin Area with Mapped fillColor](guideHistogramGroupedStatBinAreaWithColor.svg)

The `histogram` layer also works.
Moreover, if we have exactly one grouping key, a mapping from it to `fillColor` will be created by default.

<!---FUN guideHistogramGroupedHist-->

```kotlin
groupedRangesDF.plot {
    histogram(range)
}
```

<!---END-->

![Simple Grouped Histogram Plot](guideHistogramGroupedHist.svg)

We can customize it like we used to.
From the differences - access to `key` columns, and we can customize the `position`
of bars (within a single x-coordinate), for example — stack them:

<!---FUN guideHistogramCustomizedGroupedHist-->

```kotlin
groupedRangesDF.plot {
    histogram(range) {
        fillColor(category) {
            scale = categorical(listOf(Color.GREEN, Color.ORANGE))
        }
        borderLine.width = 0.0
        width = 1.0
        // Adjust position of bars from different groups
        position = Position.stack()
    }
}
```

<!---END-->

![Stack Position on Histogram Plot](guideHistogramCustomizedGroupedHist.svg)

Histogram plot for `GroupBy` (i.e. `GroupBy.histogram(statBinArgs)` extensions) works as well:

<!---FUN guideHistogramGrSimpleHist-->

```kotlin
groupedRangesDF.histogram("range")
```

<!---END-->

![Simple Histogram on Grouped Data](guideHistogramGrSimpleHist.svg)

... and can be configured the same way:

<!---FUN guideHistogramConfiguredGrSimpleHist-->

```kotlin
groupedRangesDF.histogram(binsAlign = BinsAlign.center(500.0)) { x(range) }.configure {
    alpha = 0.6
    // make the bars from different groups overlap with each other
    position = Position.identity()
    // can access key column by name as `String`
    fillColor("category") { scale = categoricalColorBrewer(BrewerPalette.Qualitative.Dark2) }
}
```

<!---END-->

![Configured Grouped Histogram](guideHistogramConfiguredGrSimpleHist.svg)

### Inside `groupBy{}` plot context

We can apply `groupBy` modification to the initial dataset and build a histogram with grouped data the same way:

<!---FUN guideHistogramGroupByHistPlot-->

```kotlin
rangesDF.plot {
    groupBy(category) {
        histogram(range)
    }
}
```

<!---END-->

![GroupBy in Plot](guideHistogramGroupByHistPlot.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/histogram.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/3w385Us8bVePkCFn0glPeg" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
