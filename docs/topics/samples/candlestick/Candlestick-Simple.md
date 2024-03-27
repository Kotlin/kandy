# Candlestick Simple

<web-summary>
Explore 'Candlestick Simple' in Kotlin using Kandy to understand the trend of a financial dataset
with candlestick chart.
</web-summary>

<card-summary>
'Candlestick Simple' in Kotlin with Kandy: A clear and straightforward representation of price movements,
perfect for financial analysis and understanding market trends.
</card-summary>

<link-summary>
'Candlestick Simple' using Kotlin and Kandy, a fundamental tool in financial analysis for visualizing 
the price movements and trends in a dataset.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Candlestick-->

<!---FUN candlestick_simple-->
<tabs>
<tab title="Dataframe">

```kotlin
val df = dataFrameOf(
    "month" to listOf("Jan", "Feb", "Mar", "Apr", "May"),
    "open" to listOf(14.2, 6.7, 8.8, 11.2, 4.0),
    "high" to listOf(15.5, 9.6, 10.7, 11.7, 9.9),
    "low" to listOf(7.5, 6.1, 8.5, 5.4, 4.0),
    "close" to listOf(8.0, 8.6, 10.7, 6.5, 9.8)
)

df.plot {
    candlestick("month", "open", "high", "low", "close")
}
```

</tab>
<tab title="Collections">

```kotlin
val month = listOf("Jan", "Feb", "Mar", "Apr", "May")
val open = listOf(14.2, 6.7, 8.8, 11.2, 4.0)
val high = listOf(15.5, 9.6, 10.7, 11.7, 9.9)
val low = listOf(7.5, 6.1, 8.5, 5.4, 4.0)
val close = listOf(8.0, 8.6, 10.7, 6.5, 9.8)

plot {
    candlestick(month, open, high, low, close)
}
```

</tab></tabs>
<!---END-->


![Candlestick Simple](candlestick_simple.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/candlestick/candlestick_simple.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/U7lef0pS1yW5vjxNvY1klh" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
