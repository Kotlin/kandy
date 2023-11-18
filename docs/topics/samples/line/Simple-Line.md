# Simple Line

<web-summary>
Explore a simple line plot creation with the 'Simple Line' example using the Kandy library in Kotlin,
illustrating cost changes over years.
</web-summary>

<card-summary>
'Simple Line' in Kotlin with Kandy: Effortlessly visualize yearly cost trends in a clear line plot.
</card-summary>

<link-summary>
Check out 'Simple Line' for an easy demonstration of line chart plotting with Kandy in Kotlin,
featuring yearly cost data.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Lines-->

<!---FUN simple_line-->
<tabs>
<tab title="Dataframe">

```kotlin
val years by columnOf("2018", "2019", "2020", "2021", "2022")
val cost by columnOf(62.7, 64.7, 72.1, 73.7, 68.5)
val df = dataFrameOf(years, cost)

df.plot {
    line {
        x(years)
        y(cost)
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val years = listOf("2018", "2019", "2020", "2021", "2022")
val cost = listOf(62.7, 64.7, 72.1, 73.7, 68.5)

plot {
    line {
        x(years)
        y(cost)
    }
}
```

</tab></tabs>
<!---END-->

![Simple Line](simple_line.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/line/simple_line.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/NFGYJFW8oMlsu5aROAxRGq" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
