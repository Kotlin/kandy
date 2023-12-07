# Kotlin Notebook Features

<web-summary>
Unlock the full potential of data visualization in Kotlin notebooks with the Kandy Kotlin Notebook Features guide.
This comprehensive guide offers insights into unique features
tailored for interactive and dynamic chart creation in Kotlin notebooks.
</web-summary>

<card-summary>
Kandy Kotlin Notebook Features guide: A gateway to enhanced data visualization in Kotlin notebooks.
Discover unique interactive capabilities that elevate your charting experience.
</card-summary>

<link-summary>
Dive into the Kandy Kotlin Notebook Features guide for an in-depth understanding
of how to leverage Kotlin notebooks for sophisticated data visualization.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.KotlinNotebookFeatures-->

This notebook demonstrates the features available
when working with kandy-lets-plot in [Kotlin Notebook](https://plugins.jetbrains.com/plugin/16340-kotlin-notebook)

## Swing render

By default, Kotlin Notebook uses Swing to display plots:

<!---FUN guideKTNBFeaturesGenerateData-->

```kotlin
val rand = Random(42)

val simpleData = dataFrameOf(
    "time" to (1..10).toList(),
    "value" to List(10) { rand.nextDouble(0.0, 1.0) },
    "type" to (List(5) { "a" } + List(5) { "b" }).shuffled(rand),
    "active" to List(10) { rand.nextBoolean() },
)
```

<!---END-->

<!---FUN guideKTNBFeaturesPointsPlot-->

```kotlin
val plot = simpleData.plot {
    points {
        x(time)
        y(value)
        color(type)
        size = 6.0

        tooltips(type, active)
    }
}
plot
```

<!---END-->

![](guideKTNBFeaturesDefaultSwing.png){width="700" border-effect="rounded"}

Swing rendering has a lot of advantages: it is more performant (when using tooltips),
you can change its size (in this case it will be scaled),
and also has a dynamically changing color scheme (see [the relevant section](#dynamic-color-scheme)).

However, you can disable it manually and use web rendering
(may not work at all in Kotlin Notebook — try to restart kernel).
To do this, before displaying the plot, it is necessary to change the corresponding option in `kandyConfig`
(notebook parameter).

```kotlin
kandyConfig.swingEnabled = false
```

```kotlin
plot
```

![](guideKTNBFeaturesSwingFalse.png){width="700" border-effect="rounded"}

## Dynamic color scheme

If you are using a non-light color scheme in the editor,
you may have noticed that the swing-rendered plot has automatically changed its theme to match the environment theme.
If not already, you can check this by changing the theme of your editor to, for example, _Darcula_.
Even without restarting the cell, the next plot (just like the first swing plot) will change the theme to match yours.

```kotlin
// Switching the swing rendering back on
kandyConfig.swingEnabled = true
```

```kotlin
plot
```

![](guideKTNBFeaturesSwingTrue.png){width="700" border-effect="rounded"}

To disable the automatic theme change, you need to change the corresponding flag in `kandyConfig`:

```kotlin
kandyConfig.applyColorScheme = false
```

```kotlin
plot
```

![](guideKTNBFeaturesColorSchema.png){width="700" border-effect="rounded"}

Also note that automatic theme change does not work if you set the theme manually (via `layout.flavor`):

```kotlin
kandyConfig.applyColorScheme = true
```

<!---FUN guideKTNBFeaturesHighContrastDark-->

```kotlin
simpleData.plot {
    points {
        x(time)
        y(value)
        color(type)
        size = 6.0

        tooltips(type, active)
    }

    layout.flavor = Flavor.HIGH_CONTRAST_DARK
}
```

<!---END-->

![](guideKTNBFeaturesHighContrast.png){width="700" border-effect="rounded"}

## Plot Export from Output

You can export plot from the cell output with the "Export Plot As..."
button in the output menu or with "Export Plot As..." action.

![Plot Export Button](guideKTNBFeaturesExportOutput.png){width="200" border-effect="rounded"}

After that, a settings menu will open where you can select the image format,
the path to where you want to save it, as well as the file name itself.

![Plot Export Button](guideKTNBFeaturesExportOutputSettings.png){width="400" border-effect="rounded"}

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/kotlin_notebook_features.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/cCnuz4K3mLMHYGfXfnL1pM" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
