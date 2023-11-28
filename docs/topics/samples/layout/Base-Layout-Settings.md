# Base Layout Settings

<web-summary>
Explore 'Base Layout Settings' in Kotlin using Kandy to perform base plot layout adjustments: plot size and title.
</web-summary>

<card-summary>
'Base Layout Settings' in Kotlin with Kandy: simple plot layout customizations - size and title settings.
</card-summary>

<link-summary>
Dive into 'Base Layout Settings' using Kotlin and Kandy, a primary plot adjustments.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Layout-->

<!---FUN base_layout_settings-->

```kotlin
plot {
    line {
        x(listOf(1, 2, 3, 4, 5))
        y(listOf(5, 4, 7, 9, 10))
    }
    layout {
        title = "Plot title"
        size = 800 to 300
    }
}
```

<!---END-->


![Base Layout Settings](base_layout_settings.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/layout/base_layout_settings.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/5ZiTQ2sgWAyjffeD0NYlRP" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
