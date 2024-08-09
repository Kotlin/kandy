# Pie Settings

<web-summary>
Learn to customize pie graphs in Kotlin using the Kandy library with the 'Pie Settings' example.
This demonstration includes altering pie parameters and colors for a dataset of programming languages.
</web-summary>

<card-summary>
'Pie Settings' Example: A Kotlin demonstration with Kandy to customize plot charts, featuring 
pie size, hole and color adjustments.
</card-summary>

<link-summary>
Dive into 'Pie Settings' for insights on customizing pie plots in Kotlin with Kandy, 
showcasing a visualization of prgramming language data.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Pie-->

<!---FUN pie_settings-->
<tabs>
<tab title="Dataframe">

```kotlin
val language by columnOf("Kotlin", "Java", "C++", "JavaScript", "C#", "Other")
val users by columnOf(563, 481, 202, 406, 150, 312)
val usersLanguages = dataFrameOf(language, users)

usersLanguages.plot {
    pie {
        slice(users)
        fillColor(language) {
            scale = categorical(
                "Kotlin" to Color.hex("#1E88E5"),
                "Java" to Color.hex("#D32F2F"),
                "C++" to Color.hex("#7B1FA2"),
                "JavaScript" to Color.hex("#FBC02D"),
                "C#" to Color.hex("#388E3C"),
                "Other" to Color.hex("#757575")
            )
        }
        size = 33.0
        hole = 0.8
        alpha = 0.8
    }
    layout.style(Style.Void)
}
```

</tab>
<tab title="Collections">

```kotlin
val language = listOf("Kotlin", "Java", "C++", "JavaScript", "C#", "Other")
val users = listOf(563, 481, 202, 406, 150, 312)

plot {
    pie {
        slice(users)
        fillColor(language) {
            scale = categorical(
                "Kotlin" to Color.hex("#1E88E5"),
                "Java" to Color.hex("#D32F2F"),
                "C++" to Color.hex("#7B1FA2"),
                "JavaScript" to Color.hex("#FBC02D"),
                "C#" to Color.hex("#388E3C"),
                "Other" to Color.hex("#757575")
            )
        }
        size = 33.0
        hole = 0.8
        alpha = 0.8
    }
    layout.style(Style.Void)
}
```

</tab></tabs>
<!---END-->

![Pie Settings](pie_settings.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/pie/pie_settings.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/MGG8fOjy0RNcT4zoOh0Cf7" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
