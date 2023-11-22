# Area with Reversed Axis

<web-summary>
Explore 'Area with Reversed Axis' in Kotlin using Kandy, an intriguing example that visualizes weekly star ratings with a reversed y-axis.
This plot creatively flips the conventional chart perspective, offering a unique view of the data."
</web-summary>

<card-summary>
'Area with Reversed Axis' in Kotlin with Kandy: An innovative visualization that turns the traditional area chart upside down,
presenting star ratings across the week in a reversed manner.
</card-summary>

<link-summary>
Dive into the 'Area with Reversed Axis' example using Kotlin and Kandy, where the concept of reversing axis in area charts is applied to depict weekly ratings uniquely.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Area-->

<!---FUN area_with_reversed_axis-->
<tabs>
<tab title="Dataframe">

```kotlin
val `Day of the Week` by columnOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
val `Star Rating (Reversed)` by columnOf(4, 2, 1, 2, 3, 4, 1)

plot {
    layout.title = "Weekly Star Ratings"
    layout.subtitle = "A reversed perspective"
    area {
        x(`Day of the Week`)
        y(`Star Rating (Reversed)`) {
            scale = continuous(0..5, transform = Transformation.REVERSE)
        }
        fillColor = Color.hex("#FCF84A")
        alpha = 0.75
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val week = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
val stars = listOf(4, 2, 1, 2, 3, 4, 1)

plot {
    layout.title = "Weekly Star Ratings"
    layout.subtitle = "A reversed perspective"
    area {
        x(week) { axis.name = "Day of the Week" }
        y(stars) {
            axis.name = "Star Rating (Reversed)"
            scale = continuous(0..5, transform = Transformation.REVERSE)
        }
        fillColor = Color.hex("#FCF84A")
        alpha = 0.75
    }
}
```

</tab></tabs>
<!---END-->

![Simple Area](simple_area.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/area/area_with_reversed_axis.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/DxJ4t8ObFgypRBMFXGvw8Y" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
