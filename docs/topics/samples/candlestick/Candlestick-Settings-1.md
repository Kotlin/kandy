# Candlestick Settings 1

<web-summary>
Explore 'Candlestick Settings 1' in Kotlin using Kandy, showcasing various customizations 
and settings for candlestick chart plots. This example is ideal for learning how to tweak 
candlestick chart appearances for more engaging and 
informative visualizations in the context of financial analysis.
</web-summary>

<card-summary>
'Candlestick Settings 1' in Kotlin with Kandy: A detailed demonstration of the versatility in
candlestick chart customization, perfect for tailoring visual representations to 
specific data storytelling needs it in the context of financial analysis.
</card-summary>

<link-summary>
Explore the 'Candlestick Settings 1' example using Kotlin and Kandy to understand the depth of 
customization available in candlestick chart plotting, from color adjustments to layout tweaks. 
This provides insights into tailoring visual representations for a nuanced understanding in the context 
of financial analysis.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Candlestick-->

<!---FUN candlestick_settings_1-->
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


![Candlestick Settings 1](candlestick_settings_1.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/candlestick/candlestick_settings_1.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/0VZzfzOogM3v1ePEqDdF4y" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
