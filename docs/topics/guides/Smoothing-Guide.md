# Smoothing

<web-summary>
Become proficient in data smoothing with Kandy's 'Smoothing' guide.
Explore methods to improve data visualization by lessening the noise and emphasizing trends
</web-summary>

<card-summary>
Kandy's 'Smoothing' guide demystifies the process of smoothing data.
Learn how to refine your visualizations for clearer insights.
</card-summary>

<link-summary>
Explore Kandy's 'Smoothing' guide for advanced data visualization techniques.
Understand how smoothing can elevate your plots by emphasizing underlying patterns.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.Smoothing-->

Statistics "smooth" are calculated on the sample of two continuous variables (i.e., sample of points or lines).
It interpolates data points to create a smoother curve.

This notebook uses definitions from [DataFrame](https://kotlin.github.io/dataframe/overview.html).

## Usage

The "Smooth" statistic proves beneficial in scenarios with over-plotting or noise,
simplifying the process of identifying inherent trends and patterns.
It can also be used to make a more pretty line with a small number of points.

## Arguments

* Input (mandatory):
    - `x` — numeric sample of input points `x` coordinates
    - `y` — numeric sample of input points `y` coordinates
* Parameters (optional):
    - `method: SmoothMethod` — smoothing model:
        - `SmoothMethod.Linear(confidenceLevel: Double)` — linear model
        - `SmoothMethod.Polynomial(degree: Int, confidenceLevel: Double)` — polynomial model
        - `SmoothMethod.LOESS(span: Double, loessCriticalSize: Int, samplingSeed: Long, confidenceLevel: Double)` —
          Local Polynomial Regression model
    - `smootherPointCount: Int` — number of sampled points

### Generalized signature

The specific signature depends on the function,
but all functions related to "smooth" statistic (which will be discussed further below —
different variations of `statSmooth()`, `smoothLine()`) have approximately the same signature with the arguments above:

```
statSmoothArgs := 
   x, 
   y,
   method: SmoothMethod = SmoothMethod.LOESS(),
   smootherPointCount: Int = 100
```

The possible types of `x` and `y` depend on where a certain function is used.
They can be simply `Iterable` (`List`, `Set`, etc.) or a reference to a column in a `DataFrame`
(`String`, `ColumnAccessor`) or the `DataColumn` itself.

## Output statistics

| name      | type   | description                                          |
|-----------|--------|------------------------------------------------------|
| Stat.x    | Double | `x` coordinate                                       |
| Stat.y    | Double | `y` coordinate                                       |
| Stat.yMin | Double | Lower point-wise confidence interval around the mean |
| Stat.yMax | Double | Upper point-wise confidence interval around the mean |
| Stat.se   | Double | Standard error                                       |

## StatSmooth plots

<!---FUN guideSmoothGenerateData-->

```kotlin
// To generate the data, we use a standard java math library
// https://commons.apache.org/proper/commons-math/

// Generate line with formula
val xs = (-100..100).map { it / 50.0 }
val lineFormula = { x: Double -> 2.0 / (x * x + 0.5) }
// Generate noises from normal distribution
val noises = NormalDistribution(0.0, 0.1).sample(xs.size).toList()
val ys = xs.zip(noises).map { lineFormula(it.first) + it.second }
// And drop 2/3 points
val random = Random(42)
val (newXs, newYs) = xs.zip(ys).shuffled(random).take(xs.size * 1 / 3).sortedBy { it.first }.unzip()
```

<!---END-->


<!---FUN guideSmoothGatherIntoDF-->

```kotlin
// Gather them into the DataFrame
val df = dataFrameOf(
    "speed" to newXs,
    "efficiency" to newYs
)
df.head(5)
```

<!---END-->

| speed | efficiency |
|-------|------------|
| -2.00 | 0.500380   |
| -1.92 | 0.459302   |
| -1.84 | 0.636746   |
| -1.78 | 0.623408   |
| -1.68 | 0.839757   |

`df` has a signature

| speed | efficiency |
|-------|------------|

Let's take a look at `StatSmooth` output DataFrame:

<!---FUN guideSmoothDfStatSmooth-->

```kotlin
df.statSmooth("speed", "efficiency").head(5)
```

<!---END-->

<table>
<tr><td><b>Stat</b></td></tr>
<tr><td><table>
<tr><td>x</td><td>y</td><td>yMin</td><td>yMax</td><td>se</td></tr>
<tr><td>-2</td><td>0.197</td><td>-0.006</td><td>0.401</td><td>0.102</td></tr>
<tr><td>-1.96</td><td>0.247</td><td>0.047</td><td>0.447</td><td>0.1</td></tr>
<tr><td>-1.919</td><td>0.297</td><td>0.1</td><td>0.493</td><td>0.099</td></tr>
<tr><td>-1.879</td><td>0.347</td><td>0.153</td><td>0.54</td><td>0.097</td></tr>
<tr><td>-1.838</td><td>0.397</td><td>0.206</td><td>0.588</td><td>0.096</td></tr>
</table></td></tr>
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
      <th>y</th>
      <th>yMin</th>
      <th>yMax</th>
      <th>se</th>
    </tr>
  </thead>
</table>


As you can see, we got a `DataFrame` with one `ColumnGroup` called `Stat` which contains several columns with statics.
For `statSmooth`, each row corresponds to one of the line points.
`Stat.x` is the column with this point `x` coordinate.
`Stat.y` is points `y` coordinate; `Stat.yMin` — lower point of confidence level.
`Stat.yMax` — upper point of confidence level.
`Stat.se` — standard error.

`DataFrame` with "smooth" statistics is called `StatSmoothFrame`

### `statSmooth` transform

`statSmooth(statSmoothArgs) { /*new plotting context*/ }` modifies a plotting context —
instead of original data (no matter was it empty or not) new `StatSmooth` dataset
(calculated on given arguments. Inputs can be provided as `Iterable` or as dataset column reference —
by name as a `String`, as a `ColumnReference` or as a `DataColumn`) is used inside a new context
(original dataset and primary context are not affected —
you can add layers using initial dataset outside the `statSmooth` context).
Since the old dataset is irrelevant, we cannot use references for its columns.
But we can refer to the new ones.
They are all contained in the `Stat` group and can be called inside the new context:


<!---FUN guideSmoothPlotWithAreaAndPoints-->

```kotlin
plot {
    statSmooth(newXs, newYs) {
        // new `StatSmooth` dataset here
        area {
            // use `Stat.*` columns for mappings
            x(Stat.x)
            y(Stat.y)
        }
    }
    points {
        x(newXs)
        y(newYs)
    }
}
```

<!---END-->

![StatSmooth Area and Points Plot](guideSmoothPlotWithAreaAndPoints.svg)

<!---FUN guideSmoothPlotWithRibbonAndPoints-->

```kotlin
df.plot {
    statSmooth(speed, efficiency, method = SmoothMethod.Polynomial(2), smootherPointCount = 250) {
        ribbon {
            x(Stat.x)
            yMin(Stat.yMin)
            yMax(Stat.yMax)
        }
    }
    // Dataset is not changed here
    points {
        x(speed)
        y(efficiency)
    }
}
```

<!---END-->

![StatSmooth Ribbon and Points Plot](guideSmoothPlotWithRibbonAndPoints.svg)

### `smoothLine` layer

`smoothLine` layer is a shortcut for fast plotting a smoothed line:

<!---FUN guideSmoothLineLayer-->

```kotlin
val smoothLineLayerPlot = plot {
    smoothLine(newXs, newYs)
    layout.title = "`smoothLine()` layer"
}
smoothLineLayerPlot
```

<!---END-->

![SmoothLine Shortcut](guideSmoothLineLayer.svg)

<!---FUN guideSmoothCompareLineAndSmoothLine-->

```kotlin
// Compare it with `statSmooth` + usual `line`
val statSmoothAndLinePlot = plot {
    statSmooth(newXs, newYs) {
        line {
            x(Stat.x)
            y(Stat.y)
        }
    }
    layout.title = "`statSmooth()` + non-statistical `line` layer"
}
plotGrid(listOf(smoothLineLayerPlot, statSmoothAndLinePlot))
```

<!---END-->

![Compare SmoothLine and StatSmooth Line Plots](guideSmoothCompareLineAndSmoothLine.svg)

`smoothLine` uses `statSmooth` and `line` and performs coordinate mappings under the hood.
And we can customize `smoothLine` layer: `smoothLine()` optionally opens a new context,
where we can configure the line (as in the usual context opened by `line { ... }`) —
even change coordinate mappings from default ones.
`StatSmooth` dataset of `smoothLine` is also can be accessed here.

<!---FUN guideSmoothSmoothLineWithPoints-->

```kotlin
df.plot {
    smoothLine(speed, efficiency, SmoothMethod.LOESS(span = 0.1), 120) {
        // change a column mapped on `y` to `Stat.scaled`
        y(Stat.yMax)
        color = Color.RED
        width = 4.0
    }
    points {
        x(speed)
        y(efficiency)
    }
}
```

<!---END-->

![SmoothLine with mapped y-axis](guideSmoothSmoothLineWithPoints.svg)

## Grouped `statSmooth`

`statSmooth` can be applied for grouped data —
statistics will be calculated on each group independently but with equal categories.
This application returns a new `GroupBy`
dataset with the same keys as the old one but with `StatSmooth` groups instead of old ones.

<!---FUN guideSmoothGroupedData-->

```kotlin
// Generate two lines
val fA = { x: Double -> 0.02 * x * x * x - 0.2 * x * x + 0.1 * x + 2.1 }
val fB = { x: Double -> -0.1 * x * x * x + 0.5 * x * x - 0.8 }
val xRange = (-500..500).map { it / 100.0 }
val noisesA = NormalDistribution(0.0, 0.05).sample(xRange.size).toList()
val noisesB = NormalDistribution(0.0, 0.2).sample(xRange.size).toList()
val valuesA = xRange.zip(noisesA).map { fA(it.first) + it.second }
val valuesB = xRange.zip(noisesB).map { fB(it.first) + it.second }

val (xsA, ysA) = xRange.zip(valuesA).shuffled(Random(17)).take(xRange.size * 1 / 3).sortedBy { it.first }
    .unzip()
val (xsB, ysB) = xRange.zip(valuesB).shuffled(Random(17)).take(xRange.size * 1 / 3).sortedBy { it.first }
    .unzip()
```

<!---END-->


<!---FUN guideSmoothGatherGroupedDataIntoDF-->

```kotlin
// Gather them into `DataFrame` with "A" and "B" keys in "category" column
val valuesDF = dataFrameOf(
    "time" to xsA + xsB,
    "value" to ysA + ysB,
    "category" to List(xsA.size) { "A" } + List(xsB.size) { "B" }
)
valuesDF.head(5)
```

<!---END-->

| time  | value  | category |
|-------|--------|----------|
| -4.96 | -5.735 | A        |
| -4.89 | -5.57  | A        |
| -4.87 | -5.384 | A        |
| -4.84 | -5.261 | A        |
| -4.83 | -5.333 | A        |

It has the following signature:

| time | value | category |
|------|-------|----------|

<!---FUN guideSmoothGroupingDf-->

```kotlin
// Group it by "category"
val groupedDF = valuesDF.groupBy { category }
groupedDF
```

<!---END-->

<table>
<tr><td>category</td><td>group</td></tr>
<tr><td>A</td>
<td><table>
<tr><td>time</td><td>value</td><td>category</td></tr>
<tr><td>-4.96</td><td>-5.69</td><td>A</td></tr>
<tr><td>-4.89</td><td>-5.449</td><td>A</td></tr>
<tr><td>-4.87</td><td>-5.509</td><td>A</td></tr>
<tr><td>-4.84</td><td>-5.377</td><td>A</td></tr>
<tr><td>-4.83</td><td>-5.265</td><td>A</td></tr>
</table></td>
</tr>
<tr><td>B</td>
<td><table>
<tr><td>time</td><td>value</td><td>category</td></tr>
<tr><td>-4.96</td><td>23.806</td><td>B</td></tr>
<tr><td>-4.89</td><td>22.735</td><td>B</td></tr>
<tr><td>-4.87</td><td>22.503</td><td>B</td></tr>
<tr><td>-4.84</td><td>22.293</td><td>B</td></tr>
<tr><td>-4.83</td><td>22.072</td><td>B</td></tr>
</table></td>
</tr>
</table>

Now we have a `GroupBy` with a signature

<table>
  <thead>
    <tr>
      <th>key: [category]</th>
      <th>group: DataFrame[time|value|category]</th>
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

<!---FUN guideSmoothGroupedDFStatSmooth-->

```kotlin
groupedDF.statSmooth { x(time); y(value) }
```

<!---END-->

<table>
<tr><td>category</td><td>group</td></tr>
<tr><td>A</td>
<td><table>
<tr><td>Stat</td></tr>
<tr><td>{ x: -5.0, y: -4.2, y: -4.2, ... }</td></tr>
</table></td>
</tr>
<tr><td>B</td>
<td><table>
<tr><td>Stat</td></tr>
<tr><td>{ x: -5.0, y: 17.4, y: 17.1, ... }</td></tr>
</table></td>
</tr>
</table>

After `statSmooth` applying it's still a `GroupBy` but with different signature of `group` —
all groups have the same signature as usual `DataFrame` after `statSmooth` applying (i.e. `StatSmoothFrame`):

<table>
  <thead>
    <tr>
      <th>key: [drv]</th>
      <th>group: StaSmoothFrame</th>
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


As you can see, we did indeed do a `statSmooth` transformation within groups, the grouping keys did not change.

The plotting process doesn't change much — we do everything the same.


<!---FUN guideSmoothGroupedStatSmoothPlot-->

```kotlin
groupedDF.plot {
    statSmooth(time, value) {
        line {
            x(Stat.x)
            y(Stat.y)
        }
    }
}
```

<!---END-->

![StatSmooth on Grouped DataFrame](guideSmoothGroupedStatSmoothPlot.svg)

As you can see, there are two lines because we have two groups of data.
To distinguish them, we need to add mapping to the color from the key.
This is convenient — the key is available in the context

<!---FUN guideSmoothGroupedStatSmoothPlotWithColor-->

```kotlin
groupedDF.plot {
    statSmooth(time, value, method = SmoothMethod.Polynomial(3)) {
        line {
            x(Stat.x)
            y(Stat.y)
            color(category)
        }
    }
}
```

<!---END-->

![StatSmooth on Grouped DataFrame with Color](guideSmoothGroupedStatSmoothPlotWithColor.svg)

The `smoothLine()` layer also works.
Moreover, if we have exactly one grouping key, a mapping from it to `color` will be created by default.

<!---FUN guideSmoothGroupedSmoothLine-->

```kotlin
groupedDF.plot {
    smoothLine(time, value)
}
```

<!---END-->

![SmoothLine with Default Colors](guideSmoothGroupedSmoothLine.svg)

We can customize it like we used to. From the differences — access to `key` columns:

<!---FUN guideSmoothCustomizedGroupedSmoothLine-->

```kotlin
groupedDF.plot {
    smoothLine(time, value) {
        color = Color.GREEN
        type(category)
    }
}
```

<!---END-->

![SmoothLine with Mapping on Type](guideSmoothCustomizedGroupedSmoothLine.svg)

### Inside `groupBy{}` plot context

We can apply `groupBy` modification to the initial dataset and build a histogram with grouped data the same way:

<!---FUN guideSmoothGroupBySmoothLine-->

```kotlin
valuesDF.plot {
    groupBy(category) {
        smoothLine(time, value)
    }
}
```

<!---END-->

![GroupBy into Plot](guideSmoothGroupBySmoothLine.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/smoothing.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/TW5QSgta5WysX0zhtCfja2" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
