# Heatmap

<web-summary>
Explore the 'Heatmap' guide in Kandy,
a powerful tool for visualizing complex data matrices and uncovering hidden patterns and correlations.
With the aid of this guide,
you'll be able to manipulate large data sets and unlock insights to drive your decision-making process.
</web-summary>

<card-summary>
Kandy's 'Heatmap' guide simplifies visual analysis of data matrices, making it easier to spot trends and relationships.
</card-summary>

<link-summary>
Explore Kandy's Heatmap guide to become proficient in depicting data matrices. 
Effortlessly discover patterns and insights within your data visualizations. 
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.Heatmap-->

Statistics "count2d" are calculated on the sample of two categorical variables
(usually provided as two samples of single variable — `x` and `y`).
It counts the number of observations in each pair of x-category and y-category.
It's weighted, it means the weighted count for each pair is calculated
(each element within a pair is counted along with its weight).

This notebook uses definitions from [DataFrame](https://kotlin.github.io/dataframe/overview.html).

## Usage

"Count2D" plots give a visual representation of the two-variable discrete sample distribution.

## Arguments

* Input (mandatory):
    - `x` — `x`-part of input sample
    - `y` — `y`-part of input sample
* Weights (optional):
    - `weights` — set of weights of the same size as the input samples.
      `null` (by default) means all weights are equal to `1.0` and the weighted count is equal to the normal one

### Generalized signature

The specific signature depends on the function,
but all functions related to "count2d"
statistic (which will be discussed further below — different variations of `statCount2D()`, `heatmap()`)
have approximately the same signature with the arguments above:

```
statCount2DArgs := 
   x,
   y, 
   weights = null
```

The possible types of `x`, `y` and `weights` depend on where a certain function is used.
They can be simply `Iterable` (`List`, `Set`, etc.) or a reference to a column in a `DataFrame`
(`String`, `ColumnAccessor`) or the `DataColumn` itself.
`x` elements are type of `X` — generic type parameter, `y` elements are type of `Y` — generic type parameter.

## Output statistics

| name               | type   | description                                                   |
|--------------------|--------|---------------------------------------------------------------|
| Stat.x             | X      | `x`-category                                                  |
| Stat.y             | Y      | `y`-category                                                  |
| Stat.count         | Int    | Number of observations in this category                       |
| Stat.countWeighted | Double | Weighted count (sum of observations weights in this category) |

## StatCount plots

<!---FUN guideHeatmapReadAutoDf-->

```kotlin
// Use "mpg" dataset
val mpgDF =
    DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
mpgDF.head(5)
```

<!---END-->

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


<!---FUN guideHeatmapTakeThreeCols-->

```kotlin
// We need only three columns
val df = mpgDF["class", "drv", "hwy"]
df.head(5)
```

<!---END-->

| class   | drv | hwy |
|---------|-----|-----|
| compact | f   | 29  |
| compact | f   | 29  |
| compact | f   | 31  |
| compact | f   | 30  |
| compact | f   | 26  |

It has a signature

| class | drv | hwy |
|-------|-----|-----|

Let's take a look at `StatCount2D` output DataFrame:

<!---FUN guideHeatmapStatCount2D-->

```kotlin
df.statCount2D("class", "drv", "hwy")
```

<!---END-->

<table>
<tr><td><b>Stat</b></td></tr>
<tr><td>
<table>
<tr><td>x</td><td>y</td><td>count</td><td>countWeighted</td></tr>
<tr><td>compact</td><td>f</td><td>35</td><td>1020</td></tr>
<tr><td>compact</td><td>4</td><td>12</td><td>310</td></tr>
<tr><td>midsize</td><td>4</td><td>3</td><td>72</td></tr>
<tr><td>suv</td><td>r</td><td>11</td><td>192</td></tr>
<tr><td>2seater</td><td>r</td><td>5</td><td>124</td></tr>
</table>
</td></tr>
</table>

It has the following signature:

<table>
  <thead>
    <tr>
      <th alignt="left" colspan="4">Stat</th>
    </tr>
  </thead>
  <thead>
    <tr>
      <th>x</th>
      <th>y</th>
      <th>count</th>
      <th>countWeighted</th>
    </tr>
  </thead>
</table>


As you can see, we got a `DataFrame` with one `ColumnGroup` called `Stat` which contains several columns with statics.
For `statCount2D`, each row corresponds to one pair of categories.
`Stat.x` is the column with its `x`-category.
`Stat.y` is the column with its `y`-category.
`Stat.count` contains the number of observations in the pair.
`Stat.countWeighted` — weighted version of `count`.
`DataFrame` with "count2D" statistics is called `StatCount2DFrame`

### `statCount2D` plot transform

`statCount2D(statCount2DArgs) { /*new plotting context*/ }` modifies a plotting context — instead of original data
(no matter was it empty or not) new `statCount2D` dataset (calculated on given arguments;
inputs and weights can be provided as `Iterable` or as dataset column reference - by name as a `String`,
as a `ColumnReference` or as a `DataColumn`) is used inside a new context
(original dataset and primary context are not affected —
you can add layers using initial dataset outside the `statCount2D` context).
Since the old dataset is irrelevant, we cannot use references for its columns.
But we can refer to the new ones.
They are all contained in the `Stat` group and can be called inside the new context:

<!---FUN guideHeatmapStatCount2DPlot-->

```kotlin
df.plot {
    statCount2D(`class`, drv) {
        // New `StatCount` dataset here
        points {
            // Use `Stat.*` columns for mappings
            x(Stat.x) {
                axis.expand(0.0, 0.5)
            }
            y(Stat.y)
            size(Stat.count) {
                scale = continuous(10.0..30.0)
            }
            color = Color.RED
        }
    }
}
```

<!---END-->

![StatCount2D with Points](guideHeatmapStatCount2DPlot.svg)

### Heatmap layer

Heatmap is a statistical plot used for visualizing the distribution of two categorical variables sample.
It's a tile plot where each tile is representing one of a pair of categories:
its `x` coordinate is corresponding to `x` category, `y` to `y` category, and its color is to count of this pair.
So basically, we can build a heatmap with `statCount2D` as follows:

<!---FUN guideHeatmapStatCount2DTile-->

```kotlin
val statCount2DAndTilePlot = df.plot {
    statCount2D("class", "drv") {
        tiles {
            x(Stat.x)
            y(Stat.y)
            fillColor(Stat.count)
        }
    }
    layout.title = "`statCount2D()` + `tile()` layer"
}
statCount2DAndTilePlot
```

<!---END-->

![StatCount Tile Plot](guideHeatmapStatCount2DTile.svg)

But we can do it even faster with `heatmap(statCount2DArgs)` method:

<!---FUN guideHeatmapHeatmapInDf-->

```kotlin
val heatmapLayerPlot = df.plot {
    heatmap(`class`, drv)
    layout.title = "`heatmap()` layer"
}
heatmapLayerPlot
```

<!---END-->

![Heatmap Plot](guideHeatmapHeatmapInDf.svg)

Let's compare them:

<!---FUN guideHeatmapTileVsHeatMap-->

```kotlin
plotGrid(listOf(statCount2DAndTilePlot, heatmapLayerPlot))
```

<!---END-->

![Compare StatCount Tile and Heatmap](guideHeatmapTileVsHeatMap.svg)

These two plots are identical.
Indeed, `heatmap` just uses `statCount2D` and `tile` and performs coordinates and `fillColor` mappings under the hood.
And we can customize heatmap layer: `heatmap()` optionally opens a new context,
where we can configure tiles (as in the usual context opened by `tile { ... }`) — even change default mappings.
`StatCount2D` dataset of heatmap also can be accessed here.

<!---FUN guideHeatmapWithFillColor-->

```kotlin
df.plot {
    heatmap(`class`, drv) {
        // Swap coordinate mappings:
        x(Stat.y)
        y(Stat.x)
        // Default mapping but with custom scale
        fillColor(Stat.count) {
            scale = continuousColorBrewer(BrewerPalette.Sequential.Reds)
        }
    }
}
```

<!---END-->

![Heatmap with Continuous Color Brewer](guideHeatmapWithFillColor.svg)

If we specify weights, `Stat.countWeighted` is mapped to `fillColor` by default:

<!---FUN guideHeatmapSimpleHeatmapInDf-->

```kotlin
df.plot {
    heatmap(`class`, drv, hwy)
}
```

<!---END-->

![Default color Mapping for Heatmap](guideHeatmapSimpleHeatmapInDf.svg)

### `heatmap` plot

`heatmap(statCount2DArgs)` and `DataFrame.heatmap(statCount2DArgs)` are a family of functions for fast plotting a
heatmap.

<!---FUN guideHeatmapWithIterable-->

```kotlin
heatmap(
    listOf("A", "A", "A", "B", "B", "C", "B", "B"),
    listOf(1, 1, 1, 2, 1, 2, 1, 2),
)
```

<!---END-->

![Heatmap on Iterable](guideHeatmapWithIterable.svg)

<!---FUN guideHeatmapHeatmapOnDf-->

```kotlin
df.heatmap("class", "drv")
```

<!---END-->

![Simple Heatmap](guideHeatmapHeatmapOnDf.svg)

In case you want to provide inputs and weights using column selection DSL, it's a bit different from the usual one —
you should assign `x` and `y` inputs and (optionally) `weight` throw invocation eponymous functions:

<!---FUN guideHeatmapWithWeight-->

```kotlin
df.heatmap {
    x(`class`)
    y(drv)
    weight(hwy)
}
```

<!---END-->

![Heatmap with Weight](guideHeatmapWithWeight.svg)

Heatmap plot can be configured with `.configure {}` extension — it opens context that combines tile,
`StatCount2D` and plot context.
That means you can configure tile settings, mappings using `StatCount2D` dataset and any plot adjustments:

<!---FUN guideHeatmapConfigureHeatmap-->

```kotlin
df.heatmap {
    x(`class`)
    y(drv)
    weight(hwy)
}.configure {
    // Tile + StatCount2D + PlotContext
    // Can't add new layer
    // Can add tile mapping, including for `Stat.*` columns
    fillColor(Stat.count) {
        scale = continuous(Color.GREEN..Color.RED)
    }
    alpha = 0.6
    // Can configure general plot adjustments
    layout {
        title = "Configured `heatmap` plot"
        size = 600 to 350
    }
}
```

<!---END-->

![Configured Heatmap](guideHeatmapConfigureHeatmap.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/heatmap.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/2W0Os6XQiMJLuoctHDLos4" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
