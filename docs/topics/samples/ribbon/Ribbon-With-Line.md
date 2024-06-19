# Ribbon With Line

<web-summary>
Discover 'Ribbon with Line' in Kotlin using Kandy, where ribbon plots are enhanced with mark line.
This example compares the minimal, middle and maximal price value over week.
</web-summary>

<card-summary>
'Ribbon with Line' in Kotlin with Kandy: An innovative plot combining ribbon charts with line, providing a visual comparison of price value characteristics.
</card-summary>

<link-summary>
Explore 'Ribbon with Line' in Kotlin using Kandy, a creative approach to show value interval with middle value.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Ribbon-->

<!---FUN ribbon_with_line-->
<tabs>
<tab title="Dataframe">

```kotlin
val day by columnOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
val low by columnOf(16345f, 18718f, 17541f, 17302f, 15991f, 18315f, 20189f)
val high by columnOf(18252f, 19912f, 19001f, 21540f, 18770f, 20945f, 23007f)
val mid by (low.values().zip(high.values()).map { (it.second + it.first) / 2 }).toColumn()
val df = dataFrameOf(day, low, mid, high)

df.plot {
    x(day) { axis.name = "day of week" }
    y {
        axis.name = "price"
        scale = continuous(15000f..24000f)
    }
    line {
        y(mid)
        color = Color.GREEN
        type = LineType.DOTTED
        width = 2.0
    }
    ribbon {
        yMin(low)
        yMax(high)
        borderLine.color = Color.BLUE
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val day = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
val low = listOf(16345f, 18718f, 17541f, 17302f, 15991f, 18315f, 20189f)
val high = listOf(18252f, 19912f, 19001f, 21540f, 18770f, 20945f, 23007f)
val mid = low.zip(high).map { (it.second + it.first) / 2 }

plot {
    x(day) { axis.name = "day of week" }
    y {
        axis.name = "price"
        scale = continuous(15000f..24000f)
    }
    line {
        y(mid)
        color = Color.GREEN
        type = LineType.DOTTED
        width = 2.0
    }
    ribbon {
        yMin(low)
        yMax(high)
        borderLine.color = Color.BLUE
    }
}
```

</tab></tabs>
<!---END-->

![Ribbon with Line](ribbon_with_line.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/ribbon/ribbon_with_line.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/7u6dkpL865aJpn4he5KWmi" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
