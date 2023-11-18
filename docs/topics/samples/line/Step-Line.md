# Step Line

<web-summary>
Explore the 'Step Line' example in Kotlin using Kandy, where step lines are utilized to visualize data changes over a week.
This example combines step lines with points for a clear data representation.
</web-summary>

<card-summary>
'Step Line' in Kotlin with Kandy: A creative depiction of weekly data trends using step lines and distinct points for enhanced clarity.
</card-summary>

<link-summary>
Dive into the 'Step Line' example in Kotlin using Kandy, showcasing an innovative way to represent weekly data variations with step lines and points.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Lines-->

<!---FUN step_line-->

```kotlin
val week = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
val start = listOf(120, 132, 101, 134, 90, 230, 210)
val middle = listOf(220, 282, 201, 234, 290, 430, 410)
val end = listOf(450, 432, 401, 454, 590, 530, 510)

plot {
    x(week)
    step { y(start); color = Color.LIGHT_BLUE }
    points { y(start); symbol = Symbol.CIRCLE_OPEN; color = Color.BLUE }

    step {
        y(middle)
        color = Color.GREEN
        lineType = LineType.LONGDASH
    }
    points {
        y(middle)
        symbol = Symbol.CIRCLE_PLUS
        color = Color.GREEN
    }

    step {
        y(end)
        color = Color.YELLOW
    }
    points {
        y(end)
        symbol = Symbol.CIRCLE_FILLED
        fillColor = Color.YELLOW
        color = Color.GREY
    }
    layout {
        title = "Step line"
    }
}
```

<!---END-->

![Step Line](step_line.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/line/step_line.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/zlE5H6Y5aeORp7fvCcIhNH" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
