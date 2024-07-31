# Boxplot

<web-summary>
Unlock the power of boxplot in Kandy's 'Boxplot' guide.
This comprehensive resource provides insights into creating boxplot
to effectively analyze and compare distributions in your data.
</web-summary>

<card-summary>
Explore boxplot creation with Kandy's detailed guide.
Learn to visualize data distributions and identify outliers for comprehensive data analysis.
</card-summary>

<link-summary>
Discover how to create impactful boxplot with Kandy.
The 'Boxplot' guide offers step-by-step instructions for visualizing data variations and outliers.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.Boxplot-->

A boxplot, alternatively referred to as a whisker plot, serves as a statistical visualization technique, illustrating
the distribution and summary statistics of a dataset in a graphical format.
It consists of several components:

1. Median (Q2): the line inside the box represents the median of the dataset, which is the middle value when the data is
   sorted in ascending order.
   It divides the data into two equal halves, with 50% of the data falling below and 50% above the median.
2. Interquartile Range (IQR): the box itself spans the interquartile range, which is the range between the first
   quartile (Q1) and the third quartile (Q3).
   The first quartile (Q1) is the 25th percentile, meaning that 25% of the
   data falls below it, while the third quartile (Q3) is the 75th percentile,
   indicating that 75% of the data falls below it.
   The IQR captures the middle 50% of the data.
3. Whiskers: the whiskers extend from the top and bottom edges of the box to the minimum and maximum non-outlier data
   points within a certain range.
   The range is typically determined by a multiplier (often 1.5 times the IQR),
   and it defines the outer limits for what is considered a potential outlier.
4. Outliers (optional): individual data points that fall outside the whiskers are considered potential outliers.
   These are data points that are significantly different from the rest of the data
   and may warrant special attention in further analysis.
   The auxiliary statistic "boxplotOutliers" is used to count outliers.
   This statistic is not weighted.

This notebook uses definitions from [DataFrame](https://kotlin.github.io/dataframe/overview.html).

## Usage

A boxplot is a visual representation of a dataset's distribution, showing the median, quartiles, and potential outliers.
It's a useful tool for understanding the spread and central tendency of data, as well as identifying outliers.
The compactness of this chart also makes it
convenient to visually compare the characteristics of different samples with each other.

## Arguments

Both `statBoxplot()` and `statBoxplotOutliers()`
(as well as statistical `boxplot()` layer and plot functions) have the same arguments and signature.

* Input (mandatory):
    - `x` - a categorical variable dividing the data into different groups (in some versions of functions it is absent,
      i.e., all calculations will be performed for one sample without a division);
    - `y` - numeric sample on which the statistics are calculated;
* Parameters (optional):
    - `whiskerIQRRatio: Double` - interquartile range multiplier of whiskers lengths.

### Generalized signature

The specific signature depends on the function,
but all functions related to "boxplot" statistic
(which will be discussed further below - different variations of `statBoxplot()`, `statBoxplotOutliers()` `boxplot()`)
have approximately the same signature with the arguments above:

```
statBoxplotArgs := 
   x, // not necessarily
   y, 
   whiskerIQRRatio: Double = 1.5
```

The possible types `y` depend on where a certain function is used.
It can be simply `Iterable` (`List`, `Set`, etc.) or a reference to a column in a
`DataFrame` (`String`, `ColumnAccessor`) or the `DataColumn` itself.
It's used only with `DataFrame` - it's a reference to a column of the same type as an `y`.
`x` elements are type of `X` - generic type parameter.

## Output statistics

### "boxplot"

| name        | type   | description                                            |
|-------------|--------|--------------------------------------------------------|
| Stat.x      | X      | Boxplot `x` category                                   |
| Stat.min    | Double | Lower whisker end - the minimum non-outlier data point |
| Stat.lower  | Double | Lower box edge - the first quartile (Q1)               |
| Stat.middle | Double | Median / the second quartile (Q2)                      |
| Stat.upper  | Double | Upper box edge - the third quartile (Q3)               |
| Stat.max    | Double | Upper whisker end - the maximum non-outlier data point |

### "boxplotOutliers"

| name   | type   | description          |
|--------|--------|----------------------|
| Stat.x | X      | Boxplot `x` category |
| Stat.y | Double | Outlier value        |

## StatBoxplot plots

<!---FUN guideBoxplotGenerateData-->

```kotlin
// To generate the data, we use a standard java math library
// https://commons.apache.org/proper/commons-math/
// Generate sample from normal distribution
val rateA = NormalDistribution(37.8, 4.3).sample(5000).toList()
// Generate sample from uniform distribution
val rateB = UniformRealDistribution(20.0, 50.0).sample(1000).toList()
// Combine two previous samples and filter them by lower bound for third sample
val rateC = (rateA + rateB).filter { it >= 36.0 }
```

<!---END-->

<!---FUN guideBoxplotGatherDataIntoDf-->

```kotlin
// gather them into the DataFrame in a single column and with corresponding keys in column `cond`
val df = dataFrameOf(
    "rate" to rateA + rateB + rateC,
    "cond" to List(rateA.size) { "A" } + List(rateB.size) { "B" } + List(rateC.size) { "C" }
)
df.head(5)
```

<!---END-->

| rate   | cond |
|--------|------|
| 38.387 | A    |
| 33.406 | A    |
| 33.51  | A    |
| 36.099 | A    |
| 38.703 | A    |

`df` has a signature

| rate | cond |
|------|------|


Let's take a look at `StatBoxplot` output DataFrame:

<!---FUN guideBoxplotStatBoxplot-->

```kotlin
df.statBoxplot("cond", "rate")
```

<!---END-->

<table>
<tr><td><b>Stat</b></td></tr>
<tr><td><table>
<tr><td>x</td><td>min</td><td>lower</td><td>middle</td><td>upper</td><td>max</td></tr>
<tr><td>A</td><td>26.244</td><td>34.841</td><td>37.76</td><td>40.612</td><td>49.267</td></tr>
<tr><td>B</td><td>20.001</td><td>27.273</td><td>34.671</td><td>42.025</td><td>49.997</td></tr>
<tr><td>C</td><td>36.002</td><td>37.941</td><td>39.894</td><td>42.41</td><td>49.097</td></tr>
</table></td></tr>
</table>

It has the following signature:

<table>
  <thead>
    <tr>
      <th alignt="left" colspan="6">Stat</th>
    </tr>
  </thead>
  <thead>
    <tr>
      <th>x</th>
      <th>min</th>
      <th>lower</th>
      <th>middle</th>
      <th>upper</th>
      <th>max</th>
    </tr>
  </thead>
</table>

As you can see, we got a `DataFrame` with one `ColumnGroup` called `Stat` which contains several columns with statics.
For `statBoxplot`, each row corresponds to one boxplot.
It's the column with the `x`-coordinate category.
`Stat.min`, `Stat.lower`, `Stat.upper` and `Stat.max` correspond boxplot statistics—box and whiskers `y`-coordinates.
`Stat.middle` - median value, middle line `y`-coordinate.

`DataFrame` with "boxplot" statistics is called `StatBoxplotFrame`

Also, we can calculate outliers of these boxplot:

<!---FUN guideBoxplotStatBoxplotOutliers-->

```kotlin
df.statBoxplotOutliers("cond", "rate").head(5)
```

<!---END-->

<table>
<tr><td><b>Stat</b></td></tr>
<tr><td><table>
<tr><td>x</td><td>y</td></tr>
<tr><td>A</td><td>50.433</td></tr>
<tr><td>A</td><td>51.032</td></tr>
<tr><td>A</td><td>49.306</td></tr>
<tr><td>A</td><td>23.615</td></tr>
<tr><td>A</td><td>25.988</td></tr>
</table></td></tr>
</table>


It has the following signature:

<table>
  <thead>
    <tr>
      <th alignt="left" colspan="2">Stat</th>
    </tr>
  </thead>
  <thead>
    <tr>
      <th>x</th>
      <th>y</th>
    </tr>
  </thead>
</table>


There are only two columns in `Stat` group: `Stat.x` with `x` boxplot category and `Stat.y` with `y` outlier coordinate.

`DataFrame` with "boxplotOutliers" statistics is called `StatBoxplotOutliersFrame`

### `statBoxplot` and `statBoxplotOutliers` transforms

`statBoxplot(statBoxplotArgs) { /*new plotting context*/ }` modifies a plotting context - instead of original data (no
matter was it empty or not) new `StatBoxplot` dataset (calculated on given arguments; inputs and weights can be provided
as `Iterable` or as dataset column reference - by name as a `String`, as a `ColumnReference` or as a `DataColumn`) is
used inside a new context (original dataset and primary context are not affected - you can add layers using initial
dataset outside the `statBoxplot` context). Since the old dataset is irrelevant, we cannot use references for its
columns. But we can refer to the new ones. They are all contained in the `Stat` group and can be called inside the new
context.


<!---FUN guideBoxplotStatBoxplotErrorBarsPlotWithPoints-->

```kotlin
df.plot {
    statBoxplot(cond, rate) {
        // New "StatBoxplot" dataset here
        errorBars {
            // Use `Stat.*` columns for mappings
            x(Stat.x)
            yMin(Stat.min)
            yMax(Stat.max)
            borderLine.color(Stat.x)
        }
    }
    // Initial dataset here
    points {
        x("cond")
        y("rate")
        size = 0.5
        alpha = 0.2
        color("cond")
        position = Position.jitter()
    }
}
```

<!---END-->

![StatBoxplot with ErrorBars and Points](guideBoxplotStatBoxplotErrorBarsPlotWithPoints.svg)

`statBoxplotOutliers(statBoxplotArgs) { /*new plotting context*/ }` works the same way with a new StatBoxplotOutliers`
dataset.

<!---FUN guideBoxplotStatBoxplotOutlierPointsPlot-->

```kotlin
df.plot {
    statBoxplotOutliers(cond, rate) {
        // New "StatBoxplotOutliers" dataset here
        points {
            x(Stat.x)
            y(Stat.y)
            color(Stat.x)
        }
    }
}
```

<!---END-->

![statBoxplotOutliers with Points](guideBoxplotStatBoxplotOutlierPointsPlot.svg)

### `boxplot` layer

To make a boxplot (statistical chart), we need boxplot statistics and `boxes` <tooltip term="geom">geom</tooltip>.
Boxes attributes and boxplot statistics match.
Also, we can add outliers using `boxplotOutliers` statistic and `points` layer.

<!---FUN guideBoxplotManualBoxes-->

```kotlin
val manualBoxplot = df.plot {
    statBoxplot(cond, rate) {
        boxes {
            // All positional aesthetics match boxplot statistics
            x(Stat.x)
            yMin(Stat.min)
            lower(Stat.lower)
            middle(Stat.middle)
            upper(Stat.upper)
            yMax(Stat.max)
        }
    }
    statBoxplotOutliers(cond, rate) {
        points {
            x(Stat.x)
            y(Stat.y)
        }
    }
    layout {
        title = "`statBoxplot` + `boxes` \n" +
                "and `statBoxplotOutliers` + `points`"
    }
}
manualBoxplot
```

<!---END-->

![Manual Settings for Boxes](guideBoxplotManualBoxes.svg)

But we can do it much faster with `boxplot(statBoxplotArgs)` method:

<!---FUN guideBoxplotDefaultMappings-->

```kotlin
val boxplotPlot = df.plot {
    // Statistical boxplot layer - receives "statBoxplotArgs" and has default mappings
    boxplot(cond, rate)
    layout.title = "`boxplot()`"
}
boxplotPlot
```

<!---END-->

![Faster Way for Boxplot](guideBoxplotDefaultMappings.svg)

Let's compare them:

<!---FUN guideBoxplotManualVsDefault-->

```kotlin
plotGrid(listOf(manualBoxplot, boxplotPlot))
```

<!---END-->

![Compare Manual and Default Boxplot](guideBoxplotManualVsDefault.svg)

These two plots are identical.
Indeed, statistical `boxplot` just uses the combination of statistics and layers above
(`statBoxplot` + `boxes` and `statBoxplotOutlier` + `points`) and performs coordinates mappings under the hood.
And we can customize statistical boxplot layer: `boxplot()` optionally opens a new context,
where we can configure both boxes and outliers (as in usual contexts opened by `boxes { ... }`/`points { ... }`).
Moreover, `Stat.` columns of `StatBoxplot` dataset are available in the context of boxes,
exactly as `Stat.` columns of `StatBoxplotOutliers` are available in the context of outliers.
Also, we can hide outliers.

<!---FUN guideBoxplotWithColor-->

```kotlin
df.plot {
    boxplot(cond, rate) {
        boxes {
            // Boxes context + StatBoxplot context
            // filling color depends on `x` category
            fillColor(Stat.x)
        }
        // hide outliers
        outliers.show = false
    }
}
```

<!---END-->

![Fill Color for Boxes](guideBoxplotWithColor.svg)

<!---FUN guideBoxplotCustomizedOutliers-->

```kotlin
df.plot {
    boxplot(cond, rate) {
        boxes {
            fatten = 0.5
            alpha = 0.6
            // Border line color depends on `x` category
            borderLine.color(Stat.x)
        }
        outliers {
            // points context + StatBoxplotOutliers context
            // color depends on `x` category
            color(Stat.x)
            symbol = Symbol.ASTERIX
        }
    }
}
```

<!---END-->

![Boxplot with Borderline and Outliers](guideBoxplotCustomizedOutliers.svg)

Boxplot layer by a single sample (without `x` categories) - receives only one sample (`Iterable` or column reference)

<!---FUN guideBoxplotSimpleBoxplotOnDf-->

```kotlin
plot {
    boxplot(rateC)
}
```

<!---END-->

![Boxplot with Iterable](guideBoxplotSimpleBoxplotOnDf.svg)

### `boxplot` plot

`boxplot(statBoxplotArgs)` and `DataFrame.boxplot(statBoxplotArgs)` is a family of functions for fast plotting a
boxplot.

<!---FUN guideBoxplotSimpleBoxplot-->

```kotlin
// There's an additional argument "showOutliers"
df.boxplot("cond", "rate", showOutliers = false)
```

<!---END-->

![Simple Boxplot](guideBoxplotSimpleBoxplot.svg)

In case you want to provide inputs and weights using column selection DSL, it's a bit different from the usual one -
you should assign `x` and `y` inputs throw invocation eponymous functions:

<!---FUN guideBoxplotSimpleBoxplotWithWhisker-->

```kotlin
df.boxplot(whiskerIQRRatio = 2.0) {
    x(cond)
    y(rate)
}
```

<!---END-->

![Boxplot with Selection DSL](guideBoxplotSimpleBoxplotWithWhisker.svg)

Boxplot plot can be configured with `.configure {}` extension -
it opens context similar to the one that creates a statistical `boxplot` layer,
where you can configure boxes and outliers the same way, but also can configure any plot adjustments:

<!---FUN guideBoxplotConfiguredBoxplot-->

```kotlin
df.boxplot {
    x(cond)
    y(rate)
}.configure {
    // BoxplotLayer + PlotBuilder
    // can't add new layer but can configure `boxes` and `outliers`
    boxes {
        alpha = 0.7
        fillColor(Stat.middle) { scale = continuous(Color.GREEN..Color.RED) }
    }
    outliers {
        color(Stat.x)
        // jittered outliers
        position = Position.jitter(0.1, 0.0)
    }
    // can configure general plot adjustments
    layout {
        title = "Configured boxplot"
        size = 600 to 350
    }
}
```

<!---END-->

![Configured Boxplot](guideBoxplotConfiguredBoxplot.svg)

## Grouped `staBoxplot`

Sometimes you need it to group data within `x` categories.
Can be applied for grouped data — statistics will be counted on each group independently
(each is counted for some `x` category).
This application returns a new `GroupBy` dataset with the same keys as the old one but with `StatBoxplot` groups instead
of old ones.

<!---FUN guideBoxplotReadAutoDf-->

```kotlin
// Use "mpg" dataset
val mpgDF =
    DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
mpgDF.head()
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


<!---FUN guideBoxplotSelectThreeCols-->

```kotlin
// We need only three columns
val mpgShortDF = mpgDF["class", "hwy", "drv"]
mpgShortDF.head(5)
```

<!---END-->

<table style="header-row">
<tr>
   <td><b>class</b></td>
   <td><b>hwy</b></td>
   <td><b>drv</b></td>
</tr>
<tr>
   <td>compact</td>
   <td>29</td>
   <td>f</td>
</tr>
<tr>
   <td>compact</td>
   <td>29</td>
   <td>f</td>
</tr>
<tr>
   <td>compact</td>
   <td>31</td>
   <td>f</td>
</tr>
<tr>
   <td>compact</td>
   <td>30</td>
   <td>f</td>
</tr>
<tr>
   <td>compact</td>
   <td>26</td>
   <td>f</td>
</tr>
</table>


<!---FUN guideBoxplotGroupedDf-->

```kotlin
// group it by "drv"
val groupedDF = mpgShortDF.groupBy { drv }
groupedDF
```

<!---END-->

| drv | group                        |
|-----|------------------------------|
| f   | {drv: "f", hwy: 29, class:...} |
| 4   | {drv: "4", hwy: 26, class:...} |
| r   | {drv: "r", hwy: 20, class:...} |

Now we have a `GroupBy` with a signature

<table>
  <thead>
    <tr>
      <th>key: [drv]</th>
      <th>group: DataFrame[class|hwy|drv]</th>
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


<!---FUN guideBoxplotGroupedStatBoxplot-->

```kotlin
groupedDF.statBoxplot { x(`class`); y(hwy) }
```

<!---END-->

<table>
<tr><td>drv</td><td>group</td></tr>
<tr><td>f</td><td>{Stat: {min: 23, middle: 29,...}</td></tr>
<tr><td>4</td><td>{Stat: {min: 25, middle: 25,...}</td></tr>
<tr><td>r</td><td>{Stat: {min: 16, middle: 17,...}</td></tr>
</table>

After `statBoxplot` applying it's still a `GroupBy` but with different signature of `group` -
all groups have the same signature as usual `DataFrame` after `statBoxplot` applying (i.e. `StatBoxplotFrame`):

<table>
  <thead>
    <tr>
      <th>key: [drv]</th>
      <th>group: StaBoxplotFrame</th>
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

As you can see, we did indeed do a `statBoxplot` transformation within groups, the grouping keys did not change.

The plotting process doesn't change much — we do everything the same.

<!---FUN guideBoxplotGroupedStatBoxplotEBPlot-->

```kotlin
groupedDF.plot {
    statBoxplot(`class`, hwy) {
        errorBars {
            x(Stat.x)
            yMin(Stat.min)
            yMax(Stat.max)
        }
    }
}
```

<!---END-->

![Grouped StatBoxplot with ErrorBars](guideBoxplotGroupedStatBoxplotEBPlot.svg)

As you can see there are two or three error bars in some `x` categories because we have three groups of data.
To distinguish them, we need to adjust position and add mapping to the color from the key.
This is convenient — the key is available in the context

<!---FUN guideBoxplotGroupedBorderLines-->

```kotlin
groupedDF.plot {
    statBoxplot(`class`, hwy) {
        errorBars {
            x(Stat.x)
            yMin(Stat.min)
            yMax(Stat.max)
            borderLine.color(drv)
            position = Position.dodge()
        }
    }
}
```

<!---END-->

![Grouped StatBoxplot Dodge](guideBoxplotGroupedBorderLines.svg)

The statistical `boxplot` layer also works.
Moreover, if we have exactly one grouping key, a mapping from it to `fillColor` will be created by default.

<!---FUN guideBoxplotSimpleGroupedBoxplotInDf-->

```kotlin
groupedDF.plot {
    boxplot(`class`, hwy)
}
```

<!---END-->

![Simple Grouped Boxplot](guideBoxplotSimpleGroupedBoxplotInDf.svg)

We can customize it like we used to.
From the differences -
access to `key` columns, and we can customize the `position` of boxes (within a single x-coordinate),
for example - overlap them:

<!---FUN guideBoxplotCustomizeGroupedBoxplot-->

```kotlin
groupedDF.plot {
    boxplot(`class`, hwy) {
        boxes {
            borderLine.color(drv)
            // `identity` position, i.e boxes are overlapping
            position = Position.identity()
            alpha = 0.5
        }
        outliers.show = false
    }
}
```

<!---END-->

![Customized Grouped Boxplot](guideBoxplotCustomizeGroupedBoxplot.svg)

`boxplot` plot for `GroupBy` (i.e. `GroupBy.boxplot(statBoxplotArgs)` extensions) works as well:

<!---FUN guideBoxplotSimpleGrBoxplotOnDf-->

```kotlin
groupedDF.boxplot("class", "hwy")
```

<!---END-->

![Simple Boxplot](guideBoxplotSimpleGrBoxplotOnDf.svg)

... and can be configured the same way:

<!---FUN guideBoxplotConfigureGroupedBoxplot-->

```kotlin
groupedDF.boxplot {
    x(`class`)
    y(hwy)
}.configure {
    boxes.borderLine.color = Color.hex("#000080")
    outliers {
        color(drv)
    }
    layout {
        size = 750 to 450
        title = "Configured grouped boxplot"
    }
}
```

<!---END-->

![Configured Boxplot](guideBoxplotConfigureGroupedBoxplot.svg)

### Inside `groupBy{}` plot context

We can apply `groupBy` modification to the initial dataset and build a boxplot with grouped data the same way:

<!---FUN guideBoxplotGroupByBoxplot-->

```kotlin
mpgShortDF.plot {
    groupBy(drv) {
        boxplot(`class`, hwy)
    }
}
```

<!---END-->

![Image](guideBoxplotGroupByBoxplot.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/boxplot.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/LqqkzoO6iMxIpActrtStpw" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
