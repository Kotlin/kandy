# Candlestick Settings DSL

<web-summary>
Explore 'Candlestick Settings DSL' in Kotlin using Kandy, showcasing various customizations 
and settings for candlestick chart plots. This example is ideal for learning how to customize
your plot with special candlestick settings DSL.
</web-summary>

<card-summary>
'Candlestick Settings DSL' in Kotlin with Kandy: A detailed demonstration of the versatility in
candlestick chart customization with special candlestick settings DSL.
</card-summary>

<link-summary>
Explore the 'Candlestick Settings DSL' example using Kotlin and Kandy to understand the depth of 
customization available in candlestick chart plotting.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Candlestick-->

<!---FUN candlestick_settings_dsl-->
<tabs>
<tab title="Dataframe">

```kotlin
val df = dataFrameOf(
    "date" to List(10) { LocalDate(2022, 1, it + 1) },
    "open" to listOf(10.0, 15.0, 12.0, 18.0, 14.0, 16.0, 20.0, 22.0, 19.0, 25.0),
    "high" to listOf(18.0, 17.0, 20.0, 22.0, 18.0, 22.0, 25.0, 24.0, 27.0, 28.0),
    "low" to listOf(8.0, 10.0, 9.0, 11.0, 12.0, 15.0, 18.0, 17.0, 18.0, 22.0),
    "close" to listOf(15.0, 12.0, 18.0, 14.0, 16.0, 20.0, 22.0, 19.0, 25.0, 23.0),
)

df.plot {
    candlestick("date", "open", "high", "low", "close") {
        increase {
            fillColor = Color.hex("#00fefe")
            alpha = 0.9
        }
        decrease {
            fillColor = Color.hex("#ea2211")
            alpha = 0.5
        }
        borderLine.color = Color.GREY
        width = 0.7
    }
    y.axis.name = "Price, €"
    x.axis.name = "Date"
}
```

</tab>
<tab title="Collections">

```kotlin
val date = List(10) { LocalDate(2022, 1, it + 1) }
val open = listOf(10.0, 15.0, 12.0, 18.0, 14.0, 16.0, 20.0, 22.0, 19.0, 25.0)
val high = listOf(18.0, 17.0, 20.0, 22.0, 18.0, 22.0, 25.0, 24.0, 27.0, 28.0)
val low = listOf(8.0, 10.0, 9.0, 11.0, 12.0, 15.0, 18.0, 17.0, 18.0, 22.0)
val close = listOf(15.0, 12.0, 18.0, 14.0, 16.0, 20.0, 22.0, 19.0, 25.0, 23.0)

plot {
    candlestick(date, open, high, low, close) {
        increase {
            fillColor = Color.hex("#00fefe")
            alpha = 0.9
        }
        decrease {
            fillColor = Color.hex("#ea2211")
            alpha = 0.5
        }
        borderLine.color = Color.GREY
        width = 0.7
    }
    y.axis.name = "Price, €"
    x.axis.name = "Date"
}
```

</tab></tabs>
<!---END-->


![Candlestick Settings DSL](candlestick_settings_dsl.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/candlestick/candlestick_settings_dsl.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/0VZzfzOogM3v1ePEqDdF4y" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
