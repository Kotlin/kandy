# Bar Settings

<web-summary>
Discover the 'Bar Settings' example in Kotlin using Kandy, showcasing various customizations and settings for bar plots.
This example is ideal for learning how to tweak bar plot appearances for more engaging and informative visualizations.
</web-summary>

<card-summary>
'Bar Settings' in Kotlin with Kandy: A detailed demonstration of the versatility in bar plot customization,
perfect for tailoring visual representations to specific data storytelling needs.
</card-summary>

<link-summary>
Explore the 'Bar Settings' example using Kotlin and Kandy to understand the depth of customization available in bar plotting, from color adjustments to layout tweaks.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Bars-->

<!---FUN bar_settings-->
<tabs>
<tab title="Dataframe">

```kotlin
val candy by columnOf(
    "Honey Stars", "Fairy Tale Caramels", " ChocoDream", "Fruity Clouds",
    "Minty Spheres", "Sour Strips", "Vanilla Bars"
)
val sugar by columnOf(65, 58, 53, 35, 40, 45, 50)

plot {
    layout {
        title = "Sugar content"
        xAxisLabel = "Candy Name"
        yAxisLabel = "Sugar Content (g per 100g)"
    }
    bars {
        x(candy)
        y(sugar) { scale = continuous(0..100) }
        fillColor = Color.ORANGE
        alpha = 0.85
        borderLine {
            color = Color.GREY
            width = 1.3
        }
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val candy = listOf(
    "Honey Stars", "Fairy Tale Caramels", " ChocoDream", "Fruity Clouds",
    "Minty Spheres", "Sour Strips", "Vanilla Bars"
)
val sugar = listOf(65, 58, 53, 35, 40, 45, 50)

plot {
    layout {
        title = "Sugar content"
        xAxisLabel = "Candy Name"
        yAxisLabel = "Sugar Content (g per 100g)"
    }
    bars {
        x(candy)
        y(sugar) { scale = continuous(0..100) }
        fillColor = Color.ORANGE
        alpha = 0.85
        borderLine {
            color = Color.GREY
            width = 1.3
        }
    }
}
```

</tab></tabs>
<!---END-->

![Bar Settings](bar_settings.png) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/bars/bar_settings.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/3V1NvF05jxMzPwhoJ029Qf" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
