# Count Plot

<web-summary>
Learn to create compelling count plots with Kandy.
This guide introduces you to the techniques for visualizing frequency distributions in datasets,
helping you to effectively communicate data patterns and insights.
</web-summary>

<card-summary>
Kandy's Count Plot guide: A straightforward approach to visually representing frequency distributions in your data.
</card-summary>

<link-summary>
Discover Kandy's guide on count plots for a deeper understanding of frequency distributions in datasets,
enhancing your data visualization skills.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.CountPlot-->

Statistics "count" are calculated on the sample of a single categorical variable.
It counts the number of observations in each category.
It's weighted, it means the weighted count for each category is calculated
(each element within a category is counted along with its weight).

This notebook uses definitions from [DataFrame](https://kotlin.github.io/dataframe/overview.html).

## Usage

"Count" is one of the most important statistics with different usages.
The count plot provides a graphical depiction of how categories are distributed.

## Arguments

* Input (mandatory):
    - `x` — discrete sample on which the statistics are calculated
* Weights (optional):
    - `weights` — set of weights of the same size as the input sample.
      `null` (by default) means all weights are equal to `1.0` and the weighted count is equal to the normal one

### Generalized signature

The specific signature depends on the function,
but all functions related to "count" statistic (which will be discussed further below —
different variations of `statCount()`, `countPlot()`) have approximately the same signature with the arguments above:

```
statCountArgs := 
   x, 
   weights = null
```

The possible types of `x` and `weights` depend on where a certain function is used.
They can be simply `Iterable` (`List`, `Set`, etc.) or a reference to a column in a `DataFrame`
(`String`, `ColumnAccessor`) or the `DataColumn` itself.
`x` elements are type of `X` — generic type parameter.

## Output statistics

| name               | type   | description                                                   |
|--------------------|--------|---------------------------------------------------------------|
| Stat.x             | X      | Category                                                      |
| Stat.count         | Int    | Number of observations in this category                       |
| Stat.countWeighted | Double | Weighted count (sum of observations weights in this category) |

## StatCount plots

<!---FUN guideCountReadAutoDf-->

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

<!---FUN guideCountTakeThreeCols-->

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

Let's take a look at `StatCount` output DataFrame:


<!---FUN guideCountStatCount-->

```kotlin
df.statCount("class", "hwy")
```

<!---END-->

<table>
<tr><td><b>Stat</b></td></tr>
<tr><td>
<table>
<tr><td>x</td><td>count</td><td>countWeighted</td></tr>
<tr><td>compact</td><td>47</td><td>1330</td></tr>
<tr><td>midsize</td><td>41</td><td>1119</td></tr>
<tr><td>suv</td><td>62</td><td>1124</td></tr>
<tr><td>2seater</td><td>5</td><td>124</td></tr>
<tr><td>minivan</td><td>11</td><td>246</td></tr>
</table>
</td></tr>
</table>

It has the following signature:

<table>
  <thead>
    <tr>
      <th alignt="left" colspan="3">Stat</th>
    </tr>
  </thead>
  <thead>
    <tr>
      <th>x</th>
      <th>count</th>
      <th>countWeighted</th>
    </tr>
  </thead>
</table>

As you can see, we got a `DataFrame` with one `ColumnGroup` called `Stat` which contains several columns with statics.
For `statCount2D`, each row corresponds to one category.
`Stat.x` is the column with this category.
`Stat.count` contains the number of observations in the category.
`Stat.countWeighted` - weighted version of `count`.
`DataFrame` with "count" statistics is called `StatCountFrame`

### `statCount` transform

`statCount(statCountArgs) { /*new plotting context*/ }` modifies a plotting context - instead of original data
(no matter was it empty or not) new `StatCount` dataset (calculated on given arguments,
inputs and weights can be provided as `Iterable` or as dataset column reference - by name as a `String`,
as a `ColumnReference` or as a `DataColumn`) is used inside a new context
(original dataset and primary context are not affected -
you can add layers using initial dataset outside the `statCount` context).
Since the old dataset is irrelevant, we cannot use references for its columns.
But we can refer to the new ones.
They are all contained in the `Stat` group and can be called inside the new context:



<!---FUN guideCountStatCountPointsPlot-->

```kotlin
plot {
    statCount(df["class"]) {
        // New `StatCount` dataset here
        points {
            // Use `Stat.*` columns for mappings
            x(Stat.x)
            y(Stat.count)
            size(Stat.count)
            color(Stat.x)
        }
    }
}
```

<!---END-->

![StatCount Points Plot](guideCountStatCountPointsPlot.svg)

### CountPlot layer

CountPlot is a statistical plot used for visualizing the distribution of categorical variables.
It's a bar plot where each bar is representing one of the categories:
its `x` coordinate is corresponding to the category and `y` to its count.
So basically, we can build a histogram with `statCount` as follows:

<!---FUN guideCountStatCountBarsPlot-->

```kotlin
val statCountAndBarsPlt = df.plot {
    statCount("class") {
        bars {
            x(Stat.x)
            y(Stat.count)
        }
    }
    layout.title = "`statCount()` + `bars()` layer"
}
statCountAndBarsPlt
```

<!---END-->

![StatCount Bar Plot](guideCountStatCountBarsPlot.svg)

But we can do it even faster with `countPlot(statCountArgs)` method:

<!---FUN guideCountSimplePlot-->

```kotlin
val countPlt = plot {
    countPlot(df["class"])
    layout.title = "`countPlot()` layer"
}
countPlt
```

<!---END-->

![Count Plot](guideCountSimplePlot.svg)

Let's compare them:

<!---FUN guideCountBarsVsCountPlot-->

```kotlin
plotGrid(listOf(statCountAndBarsPlt, countPlt))
```

<!---END-->

![Compare StatCount Bar vs Count Plot](guideCountBarsVsCountPlot.svg)

These two plots are identical.
Indeed, `countPlot` just uses `statCount` and `bars` and performs coordinate mappings under the hood.
And we can customize count plot layer: `countPlot()` optionally opens a new context,
where we can configure bars (as in the usual context opened by `bars { ... }`) —
even change coordinate mappings from default ones.
`StatCount` dataset of count plot is also can be accessed here.

<!---FUN guideCountStatCountFillColor-->

```kotlin
df.plot {
    countPlot(`class`) {
        // filling color depends on `count` statistic
        fillColor(Stat.count) {
            scale = continuous(Color.GREEN..Color.RED)
        }
        borderLine.color = Color.BLACK
    }
}
```

<!---END-->

![Count Plot with Filled Color](guideCountStatCountFillColor.svg)

If we specify weights, `Stat.countWeighted` is mapped to `y` by default:

<!---FUN guideCountWithHLine-->

```kotlin
df.plot {
    countPlot(`class`, hwy)
    // We can add other layers as well.
    // Let's add a horizontal mark line with constant y intercept:
    hLine {
        val criticalCount = 500
        yIntercept.constant(criticalCount)
        tooltips { line("Critical count: ${String.format("%d", criticalCount)}") }
        color = Color.RED; width = 3.0
    }
    x.axis.name = "Car class"
}
```

<!---END-->

![Count Plot with Mark Line](guideCountWithHLine.svg)

### `countPlot` plot

`countPlot(statCountArgs)` and `DataFrame.countPlot(statCountArgs)`
are a family of functions for fast plotting a count plot.


<!---FUN guideCountPlotWithItr-->

```kotlin
countPlot(listOf("A", "A", "A", "B", "B", "C", "B", "B"))
```

<!---END-->

![Count Plot on Iterable](guideCountPlotWithItr.svg)

<!---FUN guideCountSimpleCountPlot-->

```kotlin
df.countPlot("class")
```

<!---END-->

![Simple Count Plot](guideCountSimpleCountPlot.svg)

In case you want to provide inputs and weights using column selection DSL,
it's a bit different from the usual one - you should assign `x`
input and (optionally) `weight` throw invocation eponymous functions:

<!---FUN guideCountPlotWithWeight-->

```kotlin
df.countPlot {
    x(`class`)
    weight(hwy)
}
```

<!---END-->

![Count Plot with Weight](guideCountPlotWithWeight.svg)

CountPlot plot can be configured with `.configure {}` extension — it opens a context that combines bars,
`StatCount` and plot context.
That means you can configure bars settings, mappings using `StatCount` dataset and any plot adjustments:

<!---FUN guideCountConfigurePlot-->

```kotlin
df.countPlot {
    x(`class`)
}.configure {
    // Bars + StatCount + PlotContext
    // can't add new layer
    // can add bars mapping, including for `Stat.*` columns
    fillColor(Stat.x)
    alpha = 0.6
    // can configure general plot adjustments
    layout {
        title = "Configured `countPlot` plot"
        size = 600 to 350
    }
}
```

<!---END-->

![Configured Count Plot](guideCountConfigurePlot.svg)

## Grouped `statCount`

`statCount` can be applied for grouped data —
statistics will be calculated on each group independently but with equal categories.
This application returns a new `GroupBy`
dataset with the same keys as the old one but with `StatCount` groups instead of old ones.

<!---FUN guideCountGroupDf-->

```kotlin
// group our dataframe by `drv` column
val groupedDF = df.groupBy { drv }
groupedDF
```

<!---END-->


<table>
<tr><td>drv</td><td>group</td></tr>
<tr><td>f</td><td>
<table>
<tr><td>class</td><td>drv</td><td>hwy</td></tr>
<tr><td>compact</td><td>f</td><td>29</td></tr>
<tr><td>compact</td><td>f</td><td>29</td></tr>
<tr><td>compact</td><td>f</td><td>31</td></tr>
<tr><td>compact</td><td>f</td><td>30</td></tr>
<tr><td>compact</td><td>f</td><td>26</td></tr>
</table>
</td></tr>
<tr><td>4</td><td>
<table>
<tr><td>class</td><td>drv</td><td>hwy</td></tr>
<tr><td>compact</td><td>4</td><td>26</td></tr>
<tr><td>compact</td><td>4</td><td>25</td></tr>
<tr><td>compact</td><td>4</td><td>28</td></tr>
<tr><td>compact</td><td>4</td><td>27</td></tr>
<tr><td>compact</td><td>4</td><td>25</td></tr>
</table>
</td></tr>
<tr><td>r</td><td>
<table>
<tr><td>class</td><td>drv</td><td>hwy</td></tr>
<tr><td>compact</td><td>r</td><td>20</td></tr>
<tr><td>compact</td><td>r</td><td>15</td></tr>
<tr><td>compact</td><td>r</td><td>20</td></tr>
<tr><td>compact</td><td>r</td><td>17</td></tr>
<tr><td>compact</td><td>r</td><td>17</td></tr>
</table>
</td></tr>
</table>


Now we have a `GroupBy` with a signature

<table>
  <thead>
    <tr>
      <th>key: [drv]</th>
      <th>group: DataFrame[class|drv|hwy]</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>"f"</td>
      <td>"f"-Group</td>
    </tr>
    <tr>
      <td>"4"</td>
      <td>"4"-Group</td>
    </tr>
    <tr>
      <td>"r"</td>
      <td>"r"-Group</td>
    </tr>
  </tbody>
</table>

<!---FUN guideCountGroupedStatCount-->

```kotlin
groupedDF.statCount { x(`class`) }
```

<!---END-->

<table>
<tr><td>drv</td><td>group</td></tr>
<tr><td>f</td><td>
<table>
<tr><td>Stat</td></tr>
<tr><td>{ x: compact, count: 35, countWeighted: 35}</td></tr>
</table>
</td></tr>
<tr><td>4</td><td>
<table>
<tr><td>Stat</td></tr>
<tr><td>{ x: compact, count: 12, countWeighted: 12}</td></tr>
</table>
</td></tr>
<tr><td>r</td><td>
<table>
<tr><td>Stat</td></tr>
<tr><td>{ x: suv, count: 11, countWeighted: 11}</td></tr>
</table>
</td></tr>
</table>


After `statCount` applying it's still a `GroupBy` but with different signature of `group` -
all groups have the same signature as usual `DataFrame` after `statCount` applying (i.e. `StatCountFrame`):

<table>
  <thead>
    <tr>
      <th>key: [drv]</th>
      <th>group: StaCountFrame</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>"f"</td>
      <td>"f"-Group</td>
    </tr>
    <tr>
      <td>"4"</td>
      <td>"4"-Group</td>
    </tr>
    <tr>
      <td>"r"</td>
      <td>"r"-Group</td>
    </tr>
  </tbody>
</table>

As you can see, we did indeed do a `statCount` transformation within groups, the grouping keys did not change.

The plotting process doesn't change much — we do everything the same.


<!---FUN guideCountGroupedStatCountBarsPlot-->

```kotlin
groupedDF.plot {
    statCount(`class`) {
        bars {
            x(Stat.x)
            y(Stat.countWeighted)
        }
    }
}
```

<!---END-->

![Grouped StatCount Plot](guideCountGroupedStatCountBarsPlot.svg)

As you can see, there are several bars in some categories because we have three groups of data.
To distinguish them, we need to add mapping to the filling color from the key.
This is convenient — the key is available in the context

<!---FUN guideCountGroupedStatCountBarsWithColorPlot-->

```kotlin
groupedDF.plot {
    statCount(`class`) {
        bars {
            x(Stat.x)
            y(Stat.countWeighted)
            fillColor(drv)
        }
    }
}
```

<!---END-->

![Grouped StatCount Plot with Filled Color](guideCountGroupedStatCountBarsWithColorPlot.svg)

The `countPlot` layer also works.
Moreover, if we have exactly one grouping key, a mapping from it to `fillColor` will be created by default.

<!---FUN guideCountGroupedCountPlot-->

```kotlin
groupedDF.plot {
    countPlot("class")
}
```

<!---END-->

![Grouped Count Plot with Default Colors](guideCountGroupedCountPlot.svg)

We can customize it like we used to.
From the differences - access to `key` columns, and we can customize the `position`
of bars (within a single x-coordinate), for example — stack them:

<!---FUN guideCountConfigureGroupedCountPlot-->

```kotlin
groupedDF.plot {
    countPlot("class") {
        fillColor(drv) {
            scale = categorical(listOf(Color.GREEN, Color.ORANGE, Color.LIGHT_PURPLE))
        }
        borderLine.width = 0.0
        width = 1.0
        // adjust position of bars
        position = Position.stack()
    }
}
```

<!---END-->

![Stacked Count Plot](guideCountConfigureGroupedCountPlot.svg)

CountPlot plot for `GroupBy` (i.e. `GroupBy.countPlot(statCountArgs)` extensions) works as well:

<!---FUN guideCountSimpleGroupedCountPlot-->

```kotlin
groupedDF.countPlot("class")
```

<!---END-->

![Simple Grouped Count Plot](guideCountSimpleGroupedCountPlot.svg)

... and can be configured the same way:

<!---FUN guideCountConfigureSimpleGroupedCountPlot-->

```kotlin
groupedDF.countPlot { x(`class`) }.configure {
    alpha = 0.6
    // make the bars from different groups overlap with each other
    position = Position.identity()
    // can access key column by name as `String`
    fillColor("drv") { scale = categoricalColorBrewer(BrewerPalette.Qualitative.Dark2) }
}
```

<!---END-->

![Configured Grouped Count Plot](guideCountConfigureSimpleGroupedCountPlot.svg)

### Inside `groupBy{}` plot context

We can apply `groupBy` modification to the initial dataset and count plot a histogram with grouped data the same way:

<!---FUN guideCountGroupByCountPlot-->

```kotlin
df.plot {
    groupBy(drv) {
        countPlot(`class`)
    }
}
```

<!---END-->

![GroupBy in Plot](guideCountGroupByCountPlot.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/count_plot.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/iuxAAQoiG5rCczGotibJ1S" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
