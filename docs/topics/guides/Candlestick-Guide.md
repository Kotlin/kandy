# Candlestick 

<web-summary>
Delve into Kandy's 'Candlestick' guide to understand the nuances of visualizing financial data.
Learn how to effectively represent data variations and trends.
</web-summary>

<card-summary>
Kandy's 'Candlestick' guide offers a deep dive into financial data visualization with candlestick charts.
</card-summary>

<link-summary>
Explore Kandy's guide on creating candlesticks for insightful financial data analysis.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.Candlestick-->

***Candlestick*** is a type of chart commonly used in financial markets to represent the price movement of an asset, 
such as stocks or cryptocurrencies. It consists of individual "candles" that display the *opening*, 
*closing*, *high*, and *low* prices for a specific time period. 
Each candle has a rectangular body, representing the opening and closing prices, 
and thin lines, called wicks or shadows, indicating the highest and lowest prices during that time frame.

Candlestick charts often use colors to visually indicate whether the value 
within a specific candle has increased or decreased. For example, a positive 
price movement is typically represented by a green candle, indicating that the 
closing price is higher than the opening price (and by a red color otherwise). 
Basically "candlestick" main statistic is an indicator of whether the price has increased.

This guide describes comprehensively the process of building candlestick chart and all the details of its customization.

This notebook uses definitions from [DataFrame](https://kotlin.github.io/dataframe/overview.html).

## Usage

Binning is commonly used in statistics and data analysis
to simplify complex data sets and make them easier to interpret.
Histogram (or any other plot with "bin" statistics) helps to give an overview of the sample distribution.

## Arguments

* Input (mandatory):
    - `x` — candle `x`-position (often time or date describing)
    - `open` — candle open value
    - `high` — candle high value
    - `low` — candle low value
    - `close` — candle close value

### Generalized signature

The specific signature depends on the function,
but all functions related to "candlestick" statistic
(which will be discussed further below - different variations of `statCandlestick()`, `candlestick()`)
have approximately the same signature with the arguments above:

```
statCandlestickArgs := 
   x, open, high, low, close
```

The possible types of `x`, `open`, `high`, `low`, `close` depend on where a certain function is used.
They can be simply `Iterable` (`List`, `Set`, etc.) or a reference to a column in a `DataFrame`
(`String`, `ColumnAccessor`) or the `DataColumn` itself.

## Output statistics

| name             | type    | description                                                              |
|------------------|---------|--------------------------------------------------------------------------|
| Stat.x           | X       | Candle `x`-position                                                      |
| Stat.open        | Double  | Candle open. Equals to input `open`.                                     |
| Stat.close       | Double  | Candle close. Equals to input `close`.                                   |
| Stat.min         | Double  | Candle minimum. Equals to input `low`.                                   |
| Stat.lower       | Double  | Candle lower value (i.e. smaller of `open`/`close`)                      |
| Stat.upper       | Double  | Candle lower value (i.e. greater of `open`/`close`)                      |
| Stat.max         | Double  | Candle maximum. Equals to input `high`.                                  |
| Stat.isIncreased | Boolean | Increase indicator: `true` if `close` value is greater than `open` value |

## StatCandlestick plots

<!---FUN guideCandlestickGenerateData-->

```kotlin
// Create a simple candlestick dataset
val xList = listOf("Jan", "Feb", "Mar", "Apr", "May")
val openList = listOf(14.2, 6.7, 8.8, 11.2, 4.0)
val highList = listOf(15.5, 9.6, 10.7, 11.7, 9.9)
val lowList = listOf(7.5, 6.1, 8.5, 5.4, 4.0)
val closeList = listOf(8.0, 8.6, 10.7, 6.5, 9.8)
// Gather lists into df as columns
val df = dataFrameOf(
    "x" to xList,
    "open" to openList,
    "high" to highList,
    "low" to lowList,
    "close" to closeList,
)
df.head()
```

<!---END-->

| x   | open | high | low | close |
|-----|------|------|-----|-------|
| Jan | 14.2 | 15.5 | 7.5 | 8.0   |
| Feb | 6.7  | 9.6  | 6.1 | 8.6   |
| Mar | 8.8  | 10.7 | 8.5 | 10.7  |
| Apr | 11.2 | 11.7 | 5.4 | 6.5   |
| May | 4.0  | 9.9  | 4.0 | 9.8   |

`df` has a signature

| x | open | high | low | close |
|---|------|------|-----|-------|

Let's take a look at `StatCandlestick` output DataFrame:

<!---FUN guideCandlestickStatCandlestick-->

```kotlin
df.statCandlestick("x", "open", "high", "low", "close")
```

<!---END-->
<table>
<tr><td><b>Stat</b></td></tr>
<tr><td>
<table>
  <tr>
    <td><b>x</b></td>
    <td><b>open</b></td>
    <td><b>close</b></td>
    <td><b>min</b></td>
    <td><b>lower</b></td>
    <td><b>upper</b></td>
    <td><b>max</b></td>
    <td><b>isIncreased</b></td>
  </tr>
  <tr>
    <td>Jan</td>
    <td>14.2</td>
    <td>8.0</td>
    <td>7.5</td>
    <td>8.0</td>
    <td>14.2</td>
    <td>15.5</td>
    <td>false</td>
  </tr>
  <tr>
    <td>Feb</td>
    <td>6.7</td>
    <td>8.6</td>
    <td>6.1</td>
    <td>6.7</td>
    <td>8.6</td>
    <td>9.6</td>
    <td>true</td>
  </tr>
  <tr>
    <td>Mar</td>
    <td>8.8</td>
    <td>10.7</td>
    <td>8.5</td>
    <td>8.8</td>
    <td>10.7</td>
    <td>10.7</td>
    <td>true</td>
  </tr>
  <tr>
    <td>Apr</td>
    <td>11.2</td>
    <td>6.5</td>
    <td>5.4</td>
    <td>6.5</td>
    <td>11.2</td>
    <td>11.7</td>
    <td>false</td>
  </tr>
  <tr>
    <td>May</td>
    <td>4.0</td>
    <td>9.8</td>
    <td>4.0</td>
    <td>4.0</td>
    <td>9.0</td>
    <td>9.9</td>
    <td>true</td>
  </tr>
</table>

</td></tr>
</table>

It has the following signature:

<table>
  <thead>
    <tr>
      <th alignt="left" colspan="8">Stat</th>
    </tr>
  </thead>
  <thead>
    <tr>
      <th>x</th>
      <th>open</th>
      <th>close</th>
      <th>min</th>
      <th>lower</th>
      <th>upper</th>
      <th>max</th>
      <th>isIncreased</th>
    </tr>
  </thead>
</table>


As you can see, we got a `DataFrame` with one `ColumnGroup` called `Stat` which contains several columns with statics.
For `statCandlestick`, each row corresponds to one candle.
`Stat.x` is the candle `x`-coordinate.
`Stat.open` and `Stat.close` correspond to candle open and close.
`Stat.min` and `Stat.max` correspond to candle low and high.
`Stat.lower` and `Stat.upper` correspond to candle edges.
`Stat.isIncreased` shows if value is increased (i.e `close > open`).

`DataFrame` with "candlestick" statistics is called `StatCandlestickFrame`


### `statCandlestick` context transform

`statCandlestick(statCandlestickArgs) { /*new plotting context*/ }` modifies a plotting context — instead of original data
(no matter was it empty or not) new `StatCandlestick` dataset (calculated on given arguments.
Inputs and weights can be provided as `Iterable` or as dataset column reference — by name as a `String`,
as a `ColumnReference` or as a `DataColumn`) is used inside a new context
(original dataset and primary context are not affected —
you can add layers using initial dataset outside the `statCandlestick` context).
Since the old dataset is irrelevant, we cannot use references for its columns.
But we can refer to the new ones.
They are all contained in the `Stat` group and can be called inside the new context:

<!---FUN guideCandlestickStatCandlestickErrorBarsPlot-->

```kotlin
plot {
    statCandlestick(xList, openList, lowList, highList, closeList) {
        errorBars {
            x(Stat.x)
            yMin(Stat.lower)
            yMax(Stat.upper)
            borderLine.color(Stat.isIncreased) {
                scale = categorical(true to Color.GREEN, false to Color.RED)
                legend.type = LegendType.None
            }
        }
    }
}
```

<!---END-->

![StatCandlestick with Error bars Plot](guideCandlestickStatCandlestickErrorBarsPlot.svg)

### Candlestick layer

Basically, candlestick plot is a box plot where each box represents one candle. Box whisker's ends correspond to `low` and `high` values; and lower and upper edges to `open` and `close` (so here we need to determine which is greater and which is lesser — that's what we counted in the statistics). Non-positional attributes (most often color) indicate whether an increase or decrease has occurred.
So basically, we can build a candlestick with `statCandlestick` and `boxes` as follows:

<!---FUN guideCandlestickStatCandlestickBoxesPlot-->

```kotlin
val statCandlestickBoxesPlot = df.plot {
    statCandlestick("x", "open", "high", "low", "close") {
        boxes {
            x(Stat.x)
            yMin(Stat.min)
            lower(Stat.lower)
            upper(Stat.upper)
            yMax(Stat.max)
            // temporary solution, middle shoudn't be necceasary
            middle(List(Stat.x.size()) { null })
            val colorScale = Scale.categorical(true to Color.GREEN, false to Color.RED)
            fillColor(Stat.isIncreased) {
                scale = colorScale
                // remove legend
                legend.type = LegendType.None
            }
            borderLine.color(Stat.isIncreased) {
                scale = colorScale
                // remove legend
                legend.type = LegendType.None
            }
            alpha = 0.6
            // remove whisker ends
            whiskerWidth = 0.0
        }
    }
    layout.title = "`statCandlestick` + `boxes`"
}
statCandlestickBoxesPlot
```

<!---END-->

![Candlestick Plot using StatCandlestick with Boxes](guideCandlestickCandlestickPlot.svg)

But we can do it even faster with `candlestick(statCandlestickArgs)` method:

<!---FUN guideCandlestickCandlestickPlot-->

```kotlin
val candlestickPlot = plot {
    candlestick(xList, openList, highList, lowList, closeList)
    layout.title = "`candlestick`"
}
candlestickPlot
```

<!---END-->

![Candlestick Plot using Candlestick Layer](guideCandlestickCandlestickPlot.svg)

Let's compare them:

<!---FUN guideCandlestickCompareBoxesVsCandlestick-->

```kotlin
plotGrid(listOf(statCandlestickBoxesPlot, candlestickPlot))
```

<!---END-->

![Candlestick Plots comparation](guideCandlestickCompareBoxesVsCandlestick.svg)

These two plots are almost identical (the only difference in tooltips). Indeed, 
candlestick just uses statCandlestick and boxes and performs aesthetic mappings under the hood.

### `candlestick` customization

We can customize candlestick layer: `candlestick()` optionally opens a new context,
where we can configure it. We can set different color, borderline color, etc. for candles with 
increasing and decreasing value with special DSL, or make general settings 
(as in the usual context opened by `boxes { ... }`):

<!---FUN guideCandlestickLayerCustomizationDSL-->

```kotlin
df.plot {
    candlestick(x, open, high, low, close) {
        // Boxes context + StatCandlestick context
        // change fill color when increased
        increase {
            fillColor = Color.hex("#00FFFF")
        }
        // change fill color when decreased
        decrease.fillColor = Color.hex("#FF0000")
        // set constant border line color for all candles
        borderLine.color = Color.GREY
    }
}
```

<!---END-->

![Candlestick Plot_Customization DSL](guideCandlestickLayerCustomizationDSL.svg)

However, it can also be done as with other statistical layers API
(i.e., through mappings from statistics from `StatCandlestick` dataset):

<!---FUN guideCandlestickLayerCustomizationStatAPI-->

```kotlin
df.plot {
    candlestick(x, open, high, low, close) {
        // Boxes context + StatCandlestick context
        alpha(Stat.isIncreased) {
            scale = categorical(true to 1.0, false to 0.1)
            legend {
                name = ""
                breaksLabeled(true to "increase", false to "decrease")
            }
        }
        fillColor = Color.GREY
        borderLine.color = Color.GREY
    }
}
```

<!---END-->

![Candlestick Plot_customization_Stat API](guideCandlestickLayerCustomizationStatAPI.svg)

### `candlestick` plot

candlestick(statCandlestickArgs) and DataFrame.candlestick(statCandlestickArgs) 
are a family of functions for fast plotting a candlestick.

<!---FUN guideCandlestickSimpleCandlestickPlot-->

```kotlin
candlestick(xList, openList, highList, lowList, closeList)
```

<!---END-->

![Candlestick Plot_Simple](guideCandlestickSimpleCandlestickPlot.svg)


<!---FUN guideCandlestickSimpleCandlestickPlotOnDf-->

```kotlin
df.candlestick("x", "open", "high", "low", "close")
```

<!---END-->

![Candlestick Plot_Simple](guideCandlestickSimpleCandlestickPlot.svg)

In case you want to provide inputs using column selection DSL, it’s a bit different from the usual one 
— you should assign them throw invocation eponymous functions:

<!---FUN guideCandlestickSimpleCandlestickPlotOnDfWithSelectors-->

```kotlin
df.candlestick {
    x(x)
    open(open)
    high(high)
    low(low)
    close(close)
}
```

<!---END-->

![Candlestick Plot_Simple](guideCandlestickSimpleCandlestickPlot.svg)

Candlestick plot can be configured with `.configure {}` extension —
it opens a context that combines bars, `StatCandlestick` and plot context.
That means you can configure boxes settings, mappings using `StatCandlestick` dataset and any plot adjustments:

<!---FUN guideCandlestickCandlestickPlotConfigured-->

```kotlin
df.candlestick("x", "open", "high", "low", "close").configure {
    // Boxes + StatCandlestick + PlotBuilder
    // Can't add a new layer
    y.limits = 3.0..17.0
    increase {
        borderLine.color = Color.BLUE
    }
    decrease.borderLine.color = Color.YELLOW
    borderLine.width = 2.5
    fillColor = Color.GREY
    alpha = 0.6
    // Can configure general plot adjustments
    layout {
        title = "Configured candlestick plot"
        size = 800 to 400
    }
}
```

<!---END-->

![Candlestick Plot_Simple](guideCandlestickCandlestickPlotConfigured.svg)

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/candlestick.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/a3Q5v3cdhUj6qZ9Ub8ODz9" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
