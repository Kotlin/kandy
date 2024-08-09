# Pie With Void Style

<web-summary>
Discover the 'Pie With Void Style' example in Kotlin with Kandy, where a pie plot is used with `Void`
style, enhancing its appearance.
</web-summary>

<card-summary>
'Pie With Void Style' in Kotlin with Kandy: convenient customisation for pie chart view.
</card-summary>

<link-summary>
Explore the 'Pie With Void Style' example in Kotlin using Kandy to see how style can enhance pie chart appearance. 
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Pie-->

<!---FUN pie_with_void-->
<tabs>
<tab title="Dataframe">

```kotlin
val platform by columnOf("Linux", "MacOS", "Windows")
val count by columnOf(30, 239, 566)
val df = dataFrameOf(platform, count)

df.plot {
    pie {
        slice(count)
        fillColor(platform)
    }
    layout {
        style(Style.Void)
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val platform = listOf("Linux", "MacOS", "Windows")
val count = listOf(30, 239, 566)

plot {
    pie {
        slice(count)
        fillColor(platform)
    }
    layout {
        style(Style.Void)
    }
}
```

</tab></tabs>
<!---END-->

![Path Pie With Void Style](pie_with_void.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/pie/pie_with_void.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/hMzuKrRdvYzWEKdXt6dP7q" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
