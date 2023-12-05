# Anscombe's quartet

<web-summary>
Explore the intriguing Anscombe's Quartet with Kandy.
This guide delves into creating visualizations for this famous dataset,
demonstrating the importance of graphical representation in statistical analysis.
</web-summary>

<card-summary>
Discover Anscombe's Quartet through Kandy's visualization guide.
Learn to graphically represent this unique dataset, highlighting the essence of data visualization.
</card-summary>

<link-summary>
Unravel the mysteries of Anscombe's Quartet with Kandy.
This guide offers a clear pathway to visualize and understand this classic example of statistical importance.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.AnscombesQuartet-->

<!---FUN guideAnscombesQuartetReadData-->

```kotlin
val df =
    DataFrame.readCSV(
        "https://gist.githubusercontent.com/ericbusboom/b2ac1d366c005cd2ed8c/raw/c92c66e43d144fa9c29dbd602d5af6988e8db533/anscombes.csv",
        parserOptions = ParserOptions(locale = Locale.ENGLISH)
    )

df.head()
```

<!---END-->

| id | dataset | x  | y    |
|----|---------|----|------|
| 0  | I       | 10 | 8.04 |
| 1  | I       | 8  | 6.95 |
| 2  | I       | 13 | 7.58 |
| 3  | I       | 9  | 8.81 |
| 4  | I       | 11 | 8.33 |



<!---FUN guideAnscombesQuartetDataDescribe-->

```kotlin
df.describe()
```

<!---END-->

| name    | type   | count | unique | nulls | top  | freq | mean  | std    | min | median | max   |
|:--------|:-------|:------|:-------|:------|:-----|:-----|:------|:-------|:----|:-------|:------|
| id      | Int    | 44    | 44     | 0     | 0    | 1    | 21.5  | 12.845 | 0   | 21     | 43    |
| dataset | String | 44    | 4      | 0     | I    | 11   | null  | null   | I   | II     | IV    |
| x       | Double | 44    | 12     | 0     | 8.0  | 13   | 9     | 3.199  | 4.0 | 8.0    | 19.0  |
| y       | Double | 44    | 43     | 0     | 8.84 | 2    | 7.501 | 1.959  | 3.1 | 7.52   | 12.74 |



<!---FUN guideAnscombesQuartetPlot-->

```kotlin
df.plot {
    points {
        x("x") {
            scale = continuous(0.0..20.0)
        }
        y("y") {
            scale = continuous(0.0..20.0)
        }
        color(dataset)
        size = 5.0
    }

    abLine {
        slope.constant(0.5)
        intercept.constant(3)

        width = 1.7
        alpha = 0.7
    }

    facetWrap(nCol = 2) {
        facet(dataset)
    }
}
```

<!---END-->

![Anscombe's Quartet Plot](guideAnscombesQuartetPlot.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/anscombes_quartet.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/EfOrYMfWAwoPvTdkXsQr64" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
