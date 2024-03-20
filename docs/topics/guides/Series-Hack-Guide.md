# Series Hack

<web-summary>
Explore advanced charting techniques with the 'Series Hack' guide from Kandy.
This guide delves into creative ways to manipulate data series for more effective and innovative visualizations.
</web-summary>

<card-summary>
Boost your visualization tactics with the advanced guide on 'Series Hack' from Kandy.
Discover how to alter series for creating distinctive and insightful chart displays.
</card-summary>

<link-summary>
Embrace the 'Series Hack' guide from Kandy for professional advice on morphing data series into engaging visual stories.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.SeriesHack-->

Sometimes you have several sources of data, and you want to visualize them by displaying color mapping from a group
(i.e., color of plot objects corresponds to their group).

Let's assume we have three samples with different y-values and the same x-values

<!---FUN guideSeriesHackCreateLists-->

```kotlin
val xs = listOf(1, 2, 3, 4, 5)
val ysA = listOf(1.0, 2.5, 3.0, 3.5, 5.0)
val ysB = listOf(0.5, 1.5, 3.0, 1.5, 0.0)
val ysC = listOf(3.0, 5.0, 2.0, 3.0, 5.0)
```

<!---END-->

So you can try something like that:

<!---FUN guideSeriesHackLinesPlot-->

```kotlin
plot {
    x(xs)
    line {
        y(ysA)
        color = Color.RED
    }
    line {
        y(ysB)
        color = Color.GREEN
    }
    line {
        y(ysC)
        color = Color.BLUE
    }
}
```

<!---END-->

![](guideSeriesHackLinesPlot.svg)

But unfortunately, "lets-plot" doesn't support it — legend is not displayed (because of Lets-Plot limitations).
So you need to gather them into one layer.
You need to change your data - add grouping variable
(so now each point has exactly three parameters: x and y coordinates and its group).

<!---FUN guideSeriesHackGatheredLines-->

```kotlin
plot {
    line {
        x(xs + xs + xs)
        y(ysA + ysB + ysC)
        color(xs.map { "A" } + xs.map { "B" } + xs.map { "C" })
    }
}
```

<!---END-->

![](guideSeriesHackGatheredLines.svg)

## DataFrame solution

In case a similar situation occurred with a dataframe, it is even easier to solve it with the `gather` function

<!---FUN guideSeriesHackDataFrame-->

```kotlin
val df = dataFrameOf(
    "xs" to xs,
    "ysA" to ysA,
    "ysB" to ysB,
    "ysC" to ysC,
)
df
```

<!---END-->

| xs | ysA | ysB | ysC |
|:---|:----|:----|:----|
| 1  | 1   | 0.5 | 3   |
| 2  | 2.5 | 1.5 | 5   |
| 3  | 3   | 3   | 2   |
| 4  | 3.5 | 1.5 | 3   |
| 5  | 5   | 0   | 5   |

For example, we cant to build several bars:

<!---FUN guideSeriesHackBarsSeries-->

```kotlin
// Doesn't work - bars are overlapped, and we can't change that
df.plot {
    x(xs)
    bars {
        y(ysA)
        fillColor = Color.RED
    }
    bars {
        y(ysB)
        fillColor = Color.GREEN
    }
    bars {
        y(ysC)
        fillColor = Color.BLUE
    }
}
```

<!---END-->

![](guideSeriesHackBarsSeries.svg)

<!---FUN guideSeriesHackGatherDataFrame-->

```kotlin
val gatheredDF = df.gather {
    ysA and ysB and ysC
}
    .mapKeys { it.drop(2) } // Take group name as a key
    .into(keyColumn = "group", valueColumn = "ys")
gatheredDF.head()
```

<!---END-->

| xs | group | ys  |
|:---|:------|:----|
| 1  | A     | 1   |
| 1  | B     | 0.5 |
| 1  | C     | 3   |
| 2  | A     | 2.5 |
| 2  | B     | 1.5 |



<!---FUN guideSeriesHackBarOnGatheredDf-->

```kotlin
gatheredDF.plot {
    bars {
        x(xs)
        y(ys)
        fillColor(group)
    }
}
```

<!---END-->

![](guideSeriesHackBarOnGatheredDf.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/series_hack.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/GlmWNkXxphDcAAJCaOjswN" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
