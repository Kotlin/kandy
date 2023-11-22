# Tiles Gradient

<web-summary>
Enhance your data visualization skills with 'Tiles Gradient' in Kandy.
This example showcases how gradient colors can be used to represent varying data intensities in tile plots.
</web-summary>

<card-summary>
'Tiles Gradient' in Kandy — a striking example of using gradients to convey data intensity in tile plots.
</card-summary>

<link-summary>
Delve into 'Tiles Gradient' in Kandy, where gradients bring a new dimension to data representation in tile plots.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Tiles-->

<!---FUN tiles_gradient-->
<tabs>
<tab title="Dataframe">

```kotlin
val cities = listOf("Yerevan", "Berlin", "Amsterdam", "Paphos")
val random = kotlin.random.Random(42)
val year22 = List(4) { random.nextDouble() }
val year23 = List(4) { random.nextDouble() }
val year24 = List(4) { random.nextDouble() }

val data = dataFrameOf(
    "city" to cities,
    "2022" to year22,
    "2023" to year23,
    "2024" to year24
).gather("2022", "2023", "2024").into("year", "value")

data.plot {
    tiles {
        x("city")
        y("year") {
            scale = categorical()
            axis.breaks(format = "d")
        }
        fillColor("value")
    }
}
```

</tab>
<tab title="Collections">

```kotlin
val cities = listOf("Yerevan", "Berlin", "Amsterdam", "Paphos")
val random = kotlin.random.Random(42)
val year22 = List(4) { random.nextDouble() }
val year23 = List(4) { random.nextDouble() }
val year24 = List(4) { random.nextDouble() }

val data = mapOf(
    "city" to cities + cities + cities,
    "year" to List(4) { "2022" } + List(4) { "2023" } + List(4) { "2024" },
    "value" to year22 + year23 + year24,
)

data.plot {
    tiles {
        x("city")
        y("year") {
            scale = categorical()
            axis.breaks(format = "d")
        }
        fillColor("value")
    }
}
```

</tab></tabs>
<!---END-->

![Tiles Gradient](tiles_gradient.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/tiles/tiles_gradient.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/VPkbJNGGffyf32dUYomnTz" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
