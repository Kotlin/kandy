# Density Plot

<web-summary>
Become proficient in creating density plot visualizations with Kandy.
This manual offers a detailed exploration into the creation and understanding of density plots,
giving you the ability to explicitly and efficiently represent data distributions.
</web-summary>

<card-summary>
Enhance your data analysis skills with Kandy's Density Plot guide.
Learn how to visualize and interpret data distributions for insightful analyses.
</card-summary>

<link-summary>
Explore Kandy's Density Plot guide for an in-depth understanding of data distributions.
This guide offers essential techniques for creating and analyzing density plots.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.DensityPlot-->

Statistics "density" are calculated on the sample of a single continuous variable.
It approximates the Probability Density Function (PDF) of this sample.
"Density" statistic samples this function point.
It's weighted, it means the counted density depends on observation weights.

This notebook uses definitions from [DataFrame](https://kotlin.github.io/dataframe/overview.html).

## Usage

"Density" statistic is useful when you have a large dataset,
and you want to understand the underlying probability distribution.
Density plot visualizes the PDF and also allows you to compare the distribution of different samples.
This is a useful alternative to the histogram for continuous data that comes from an underlying smooth distribution.

## Arguments

* Input (mandatory):
    - `x` — numeric sample on which the statistics are calculated
* Weights (optional):
    - `weights` — set of weights of the same size as the input sample.
      `null` (by default) means all weights are equal to `1.0` and the weighted density is equal to the normal one
* Parameters (optional):
    - `n: Int` — number of sampled points;
    - `trim: Boolean` — if `false`, each density is computed on the full range of the data,
      if `true`, each density is computed over the range of that group (only for grouped inputs).
    - `adjust: Double` — adjusts the value of bandwidth by multiplying it; changes how smooth the frequency curve is.
    - `kernel: Kernel` — the kernel used to calculate the density function:
        - `Kernel.GAUSSIAN`
        - `Kernel.RECTANGULAR`
        - `Kernel.TRIANGULAR`
        - `Kernel.BIWEIGHT`
        - `Kernel.EPANECHNIKOV`
        - `Kernel.OPTCOSINE`
    - `fullScanMax: Int` — maximum size of data to use density computation with 'full scan'.
      For bigger data, less accurate but more efficient density computation is applied
    - `bandWidth: BandWidth` — the method (or exact value) of bandwidth:
        - `BandWidth.Method.NRD`
        - `BandWidth.Method.NRD0`
        - `BandWidth.value(value: Double)`

### Generalized signature

The specific signature depends on the function,
but all functions related to "density"
statistic (which will be discussed further below — different variations of `statDensity()`, `densityPlot()`)
have approximately the same signature with the arguments above:

```
statDensityArgs := 
   x, 
   weights = null,
   n: Int = 512,
   trim: Boolean = false,
   adjust: Double = 1.0,
   kernel: Kernel = Kernel.GAUSSIAN,
   fullScanMax: Int = 5000,
   bandWidth: BandWidth = BandWidth.Method.NRD0,
```

The possible types of `x` and `weights` depend on where a certain function is used.
They can be simply `Iterable` (`List`, `Set`, etc.) or a reference to a column in a `DataFrame`
(`String`, `ColumnAccessor`) or the `DataColumn` itself.

## Output statistics

| name                 | type   | description                                 |
|----------------------|--------|---------------------------------------------|
| Stat.x               | Double | `x` coordinate                              |
| Stat.density         | Double | Density estimate                            |
| Stat.densityWeighted | Double | Weighted density                            |
| Stat.scaled          | Double | Density estimate, scaled to maximin of 1.0. |
| Stat.scaledWeighted  | Double | Weighted scaled                             |

## StatDensity plots

<!---FUN guideDensityGenerateData-->

```kotlin
// To generate the data, we use a standard java math library
// https://commons.apache.org/proper/commons-math/
// Generate sample from normal distribution
val depthList = NormalDistribution(500.0, 100.0).sample(1000).toList()
// Generate sample from uniform distribution
val coeffList = UniformRealDistribution(0.0, 1.0).sample(1000).toList()
```

<!---END-->


<!---FUN guideDensityGatherDataIntoDf-->

```kotlin
// Gather them into the DataFrame
val df = dataFrameOf(
    "depth" to depthList,
    "coeff" to coeffList
)
df.head()
```

<!---END-->

| depth   | coeff |
|---------|-------|
| 495.7   | 0.818 |
| 666.918 | 0.863 |
| 466.139 | 1     |
| 488.06  | 0.489 |
| 338.757 | 0.917 |

`df` has a signature

| depth | coeff |
|-------|-------|

Let's take a look at `StatDensity` output DataFrame:


<!---FUN guideDensityStatDensity-->

```kotlin
df.statDensity("depth", "coeff").head()
```

<!---END-->

<table>
<tr><td><b>Stat</b></td></tr>
<tr><td>
<table>
<tr><td>x</td><td>density</td><td>densityWeighted</td><td>scaled</td><td>scaledWeighted</td></tr>
<tr><td>181.351</td><td>0</td><td>0</td><td>0.011</td><td>0.015</td></tr>
<tr><td>182.64</td><td>0</td><td>0</td><td>0.012</td><td>0.016</td></tr>
<tr><td>183.929</td><td>0</td><td>0</td><td>0.012</td><td>0.016</td></tr>
<tr><td>185.218</td><td>0</td><td>0</td><td>0.012</td><td>0.017</td></tr>
<tr><td>186.506</td><td>0</td><td>0</td><td>0.013</td><td>0.017</td></tr>
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
      <th>density</th>
      <th>densityWeighted</th>
      <th>scaled</th>
      <th>scaledWeighted</th>
    </tr>
  </thead>
</table>


As you can see, we got a `DataFrame` with one `ColumnGroup` called `Stat` which contains several columns with statics.
For `statDensity`, each row corresponds to one PDF point.
`Stat.x` is the column with this point `x` coordinate.
`Stat.density` contains the estimated density.
`Stat.densityWeighted` — weighted version of `density`.
`Stat.scaled` is a density scaled to a maximum of 1.0.
`Stat.scaledWeighted` — weighted version of `scaled`.
`DataFrame` with "density" statistics is called `StatDensityFrame`

### `statDensity` transform

`statDensity(statDensityArgs) { /*new plotting context*/ }` modifies a plotting context —
instead of original data (no matter was it empty or not) new `StatDensity` dataset
(calculated on given arguments, inputs and weights can be provided as `Iterable` or as dataset column reference —
by name as a `String`, as a `ColumnReference` or as a `DataColumn`) is used inside a new context
(original dataset and primary context are not affected —
you can add layers using initial dataset outside the `statDensity` context).
Since the old dataset is irrelevant, we cannot use references for its columns.
But we can refer to the new ones.
They are all contained in the `Stat` group and can be called inside the new context:


<!---FUN guideDensityStatDensityLinePlot-->

```kotlin
plot {
    statDensity(depthList, adjust = 0.2) {
        // New `StatDensity` dataset here
        line {
            // Use `Stat.*` columns for mappings
            x(Stat.x)
            y(Stat.density)
            color(Stat.density)
        }
    }
}
```

<!---END-->

![StatDensity Plot](guideDensityStatDensityLinePlot.svg)

### DensityPlot layer

Density plot is a statistical plot used for visualizing the distribution of continuous variables.
It's an area graph of kernel-estimated PDF.
So basically, we can build a histogram with `statDensity` as follows:

<!---FUN guideDensityStatDensityAreaPlot-->

```kotlin
val statDensityAndAreaPlot = df.plot {
    statDensity("depth") {
        area {
            x(Stat.x)
            y(Stat.density)
        }
    }
    layout.title = "`statDensity()` + `area()` layer"
}
statDensityAndAreaPlot
```

<!---END-->

![StatDensity Area Plot](guideDensityStatDensityAreaPlot.svg)

But we can do it even faster with `densityPlot(statDensityArgs)` method:

<!---FUN guideDensityLayerPlot-->

```kotlin
val densityLayerPlot = plot {
    densityPlot(depthList)
    layout.title = "`densityPlot()` layer"
}
densityLayerPlot
```

<!---END-->

![Density Plot](guideDensityLayerPlot.svg)

Let's compare them:

<!---FUN guideDensityStatDensityVsLayerPlot-->

```kotlin
plotGrid(listOf(statDensityAndAreaPlot, densityLayerPlot))
```

<!---END-->

![Compare StatDensity Area vs Density Plot](guideDensityStatDensityVsLayerPlot.svg)

These two plots are identical.
Indeed, `densityPlot` just uses `statDensity` and `area` and performs coordinate mappings under the hood.
And we can customize `densityPlot` layer: `densityPlot()` optionally opens a new context,
where we can configure bars (as in the usual context opened by `area { ... }`) —
even change coordinate mappings from default ones.
`StatDensity` dataset of `densityPlot` is also can be accessed here.

<!---FUN guideDensityColorAndBorderLine-->

```kotlin
df.plot {
    densityPlot(depth) {
        // Change a column mapped on `y` to `Stat.scaled`
        y(Stat.scaled)
        alpha = 0.7
        fillColor = Color.RED
        borderLine.color = Color.BLACK
    }
}
```

<!---END-->

![Scaled y-axis in Density Plot](guideDensityColorAndBorderLine.svg)

If we specify weights, `Stat.densityWeighted` is mapped to `y` by default:

<!---FUN guideDensityVLinePlot-->

```kotlin
df.plot {
    densityPlot(depth, coeff, n = 700, adjust = 0.8, bandWidth = BandWidth.value(17.0))
    // We can add other layers as well.
    // Let's add a horizontal mark line with constant y intercept:
    vLine {
        // Count sample mean
        val mean = depth.mean()
        xIntercept.constant(mean)
        tooltips { line("Depth mean: ${String.format("%.2f", mean)}m") }
        color = Color.RED; width = 2.0
    }
    x.axis.name = "depth, m"
}
```

<!---END-->

![Density Plot with Mark Line](guideDensityVLinePlot.svg)

### `densityPlot` plot

`densityPlot(statDensityArgs)` and `DataFrame.densityPlot(statDensityArgs)`
are a family of functions for fast plotting a density plot.

<!---FUN guideDensitySimpleDensityPlot-->

```kotlin
densityPlot(depthList, kernel = Kernel.COSINE)
```

<!---END-->

![Density Plot](guideDensitySimpleDensityPlot.svg)

<!---FUN guideDensitySimpleDensityPlotOnDf-->

```kotlin
df.densityPlot("depth")
```

<!---END-->

![Simple Density Plot](guideDensitySimpleDensityPlotOnDf.svg)

In case you want to provide input and weights using column selection DSL,
it's a bit different from the usual one — you should assign `x`
input and (optionally) `weight` throw invocation eponymous functions:

<!---FUN guideDensitySimpleDensityPlotWithWeight-->

```kotlin
df.densityPlot(adjust = 0.5) {
    x(depth)
    weight(coeff)
}
```

<!---END-->

![Density Plot with Weight](guideDensitySimpleDensityPlotWithWeight.svg)

`densityPlot` plot can be configured with `.configure {}` extension — it opens context that combines area,
`StatDensity` and plot context.
That means you can configure bars settings, mappings using `StatDensity` dataset and any plot adjustments:

<!---FUN guideDensityConfigureDensityPlot-->

```kotlin
df.densityPlot {
    x(depth)
}.configure {
    // Area + StatDensity + PlotBuilder
    // Can't add new layer
    // Can add area mapping, including for `Stat.*` columns
    fillColor(Stat.scaled) // doesn't work properly for now
    alpha = 0.6
    // Can configure general plot adjustments
    layout {
        title = "Configured `densityPlot` plot"
        size = 600 to 350
    }
}
```

<!---END-->

![Configured Density Plot](guideDensityConfigureDensityPlot.svg)

## Grouped `statDensity`

`statDensity` can be applied for grouped data —
statistics will be calculated on each group independently but with equal categories.
This application returns a new `GroupBy`
dataset with the same keys as the old one but with `StatDensity` groups instead of old ones.

<!---FUN guideDensityGenerateGroupedData-->

```kotlin
// Create two samples from normal distribution with different mean/std
val rangesA = NormalDistribution(500.0, 100.0).sample(5000).toList()
val rangesB = NormalDistribution(400.0, 80.0).sample(5000).toList()

// Gather them into `DataFrame` with "A" and "B" keys in the "category" column
val rangesDF = dataFrameOf(
    "range" to rangesA + rangesB,
    "category" to List(5000) { "A" } + List(5000) { "B" }
)
rangesDF.head()
```

<!---END-->

| range   | category |
|---------|----------|
| 503.671 | A        |
| 560.585 | A        |
| 525.12  | A        |
| 488.74  | A        |
| 357.084 | A        |

It has the following signature:

| range | category |
|-------|----------|

<!---FUN guideDensityGroupingData-->

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
<tr><td>503.671</td><td>A</td></tr>
<tr><td>560.585</td><td>A</td></tr>
<tr><td>525.12</td><td>A</td></tr>
<tr><td>488.74</td><td>A</td></tr>
<tr><td>357.084</td><td>A</td></tr>
</table>
</td></tr>
<tr><td>B</td><td>
<table>
<tr><td>range</td><td>category</td></tr>
<tr><td>391.811</td><td>B</td></tr>
<tr><td>291.449</td><td>B</td></tr>
<tr><td>378.368</td><td>B</td></tr>
<tr><td>408.26</td><td>B</td></tr>
<tr><td>388.129</td><td>B</td></tr>
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


<!---FUN guideDensityGroupedStatDensity-->

```kotlin
groupedRangesDF.statDensity { x(range) }
```

<!---END-->


<table>
<tr><td>category</td><td>group</td></tr>
<tr><td>A</td><td>
<table>
<tr><td>Stat</td></tr>
<tr><td>{ x: 107.258, density: 0, densityWeighted: 0, ... }</td></tr>
</table>
</td></tr>
<tr><td>B</td><td>
<table>
<tr><td>Stat</td></tr>
<tr><td>{ x: 117.39, density: 0, densityWeighted: 0, ... }</td></tr>
</table>
</td></tr>
</table>


After `statDensity` applying it's still a `GroupBy` but with different signature of `group` -
all groups have the same signature as usual `DataFrame` after `statDensity` applying (i.e. `StatDensityFrame`):

<table>
  <thead>
    <tr>
      <th>key: [drv]</th>
      <th>group: StaDensityFrame</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>"A"</td>
      <td>"A"-Group</td>
    </tr>
    <tr>
      <td>"B"</td>
      <td>"B"-Group</td>
    </tr>
  </tbody>
</table>


As you can see, we did indeed do a `statDensity` transformation within groups, the grouping keys did not change.
The plotting process doesn't change much — we do everything the same.


<!---FUN guideDensityGroupedStatDensityLinePlot-->

```kotlin
groupedRangesDF.plot {
    statDensity(range) {
        line {
            x(Stat.x)
            y(Stat.density)
        }
    }
}
```

<!---END-->

![StatDensity Grouped Line](guideDensityGroupedStatDensityLinePlot.svg)

As you can see, there are two lines because we have two groups of data.
To distinguish them, we need to add mapping to the color from the key.
This is convenient — the key is available in the context

<!---FUN guideDensityGrStatDensityLinePlotWithColor-->

```kotlin
groupedRangesDF.plot {
    statDensity(range) {
        line {
            x(Stat.x)
            y(Stat.density)
            color(category)
        }
    }
}
```

<!---END-->

![StatDensity Grouped Line with Color](guideDensityGrStatDensityLinePlotWithColor.svg)

The `densityPlot()` layer also works.
Moreover, if we have exactly one grouping key, a mapping from it to `fillColor`
and `borderLine.color` will be created by default.

<!---FUN guideDensityGroupedDensityPlot-->

```kotlin
groupedRangesDF.plot {
    densityPlot(range)
}
```

<!---END-->

![Grouped Density Plot](guideDensityGroupedDensityPlot.svg)

We can customize it like we used to. From the differences — access to `key` columns:

<!---FUN guideDensityCustomizeGroupedDensityPlot-->

```kotlin
groupedRangesDF.plot {
    densityPlot(range) {
        // Customize scale of default mapping
        fillColor(category) {
            scale = categorical("A" to Color.GREEN, "B" to Color.ORANGE)
        }
        borderLine.color = Color.BLACK
        alpha = 0.5
    }
}
```

<!---END-->

![Customized Density Plot](guideDensityCustomizeGroupedDensityPlot.svg)

Also, we can stack areas (for that we need `x` coordinates to match — use `trim = true`):

<!---FUN guideDensityStackDensityPlot-->

```kotlin
groupedRangesDF.plot {
    // Use trim
    densityPlot(range, trim = true) {
        // Adjust position of areas from different groups
        position = Position.stack()
        alpha = 0.8
    }
}
```

<!---END-->

![Stacked Density Plot](guideDensityStackDensityPlot.svg)

`densityPlot` plot for `GroupBy` (i.e. `GroupBy.densityPlot(statDensityArgs)` extensions) works as well:

<!---FUN guideDensitySimpleGroupedDensityPlot-->

```kotlin
groupedRangesDF.densityPlot("range", bandWidth = BandWidth.value(10.0))
```

<!---END-->

![Density Plot by Range](guideDensitySimpleGroupedDensityPlot.svg)

... and can be configured the same way:

<!---FUN guideDensityConfigureSimpleGroupedDensityPlot-->

```kotlin
groupedRangesDF.densityPlot(n = 750, trim = true, adjust = 0.75) { x(range) }.configure {
    alpha = 0.6
    position = Position.stack()
    // Can access key column by name as `String`
    fillColor("category") { scale = categoricalColorBrewer(BrewerPalette.Qualitative.Dark2) }
}
```

<!---END-->

![Configured Density Plot](guideDensityConfigureSimpleGroupedDensityPlot.svg)

### Inside `groupBy{}` plot context

We can apply `groupBy` modification to the initial dataset and build a density plot with grouped data the same way:

<!---FUN guideDensityGroupByDensityPlot-->

```kotlin
rangesDF.plot {
    groupBy(category) {
        densityPlot(range)
    }
}
```

<!---END-->

![GroupBy in Plot](guideDensityGroupByDensityPlot.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/density_plot.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/hnLsJe6iBhGTTqjTbszDDy" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
