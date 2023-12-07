# BorderLine in ErrorBars

<web-summary>
Explore the 'BorderLine in ErrorBars' example in Kandy to see how borderlines can enhance error bar visualization.
This example provides a clear view of data uncertainty with defined borders.
</web-summary>

<card-summary>
'BorderLine in ErrorBars': A clear approach to highlighting error margins in Kandy's data plots.
</card-summary>

<link-summary>
Experience enhanced error bar clarity with 'BorderLine in ErrorBars' in Kandy,
focusing on defined border lines for better data understanding.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.ErrorBars-->

<!---FUN border_line_error_bars-->

```kotlin
val years = listOf("2018", "2019", "2020", "2021", "2022")
val costMin = listOf(62.7, 64.7, 72.1, 73.7, 68.5)
val costMax = listOf(68.9, 71.3, 78.9, 76.5, 72.1)
val mid = costMin.zip(costMax).map { (it.first + it.second) / 2.0 }

plot {
    errorBars {
        x(years)
        yMin(costMin)
        yMax(costMax)
        borderLine {
            color(mid) {
                scale = continuous(Color.BLACK..Color.GREEN)
            }
            width = 1.8
        }
    }
}
```

<!---END-->

![BorderLine in ErrorBars](border_line_error_bars.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/errorBars/border_line_error_bars.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/9SfHmYqwahZ7zNXV6JJW6K" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
