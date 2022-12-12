[![Experimental](https://kotl.in/badges/experimental.svg)](https://kotlinlang.org/docs/components-stability.html)
[![JetBrains incubator project](https://jb.gg/badges/incubator.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![Maven Central](https://img.shields.io/maven-central/v/org.jetbrains.kotlinx/ggdsl-api)](https://search.maven.org/artifact/org.jetbrains.kotlinx/ggdsl-api)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

# GGDSL

Kotlin plotting DSL (and more!) inspired
by [The Grammar of Graphics](https://www.goodreads.com/book/show/2549408.The_Grammar_of_Graphics). 
*Currently, JVM-only*.

## Table of contents

<!--- TOC -->

* [What does it offer?](#what-does-it-offer)
* [What does it look-and-feel like?](#what-does-it-look-and-feel-like)
   * [Lets-Plot](#lets-plot)
   * [ECharts](#echarts)
* [Modules](#modules)
* [Using in your projects](#using-in-your-projects)
    * [Gradle](#gradle)
    * [Kotlin Jupyter Notebook](#kotlin-jupyter-notebook)
* [Examples and documentation](#examples-and-documentation)
* [Contributing](#contributing)

<!--- END -->

## What does it offer?

1) Convenient, universal and readable DSL for plotting in Kotlin.
2) Multi-engine support &mdash; DSL for different engines have a common base (which makes porting the source code easy), 
but at the same time you can use all the possibilities of engines with DSL features.
3) Simple add-ons and superstructures making for your needs. 

## What does it look-and-feel like?

Seeing is believing; One look is worth a thousand words.

### Lets-Plot

```kotlin
val simpleDataset = mapOf(
    "time" to listOf(0, 1, 2, 4, 5, 7, 8, 9),
    "temperature" to listOf(12.0, 14.2, 15.1, 15.9, 17.9, 15.6, 14.2, 24.3),
    "humidity" to listOf(0.5, 0.32, 0.11, 0.89, 0.68, 0.57, 0.56, 0.5)
)

plot(simpleDataset) {
  x("time"<Int>())
  y("temperature"<Double>().scaled(
    continuousPos(0.0 to 25.5)
  ))

  bar {
    color("humidity"<Double>().scaled(continuous(
      rangeLimits = Color.YELLOW to Color.RED
    )))
    borderLine.width(0.0)
  }

  line {
    width(3.0)
    color(Color.hex("#6e5596"))
    type(LineType.DOTDASH)
  }

  layout {
    title = "Simple plot with lets-plot"
    caption = "See `examples` section for more\n complicated and interesting examples!"
  }
}
```
![GGDSL example via Lets-plot](examples/images/lets_plot_simple.png)

### ECharts

```kotlin
val dataset = mapOf(
        "days_of_week" to listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
        "evaporation" to listOf(2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6),
        "precipitation" to listOf(2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6),
        "temp" to listOf(2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3)
)

plot(dataset) {
  x("days_of_week"<String>())
  
  bars {
    name("Precipitation")
    y("precipitation"<Double>().scaled(continuousPos(0.0 to 200.0)))
    color("temp"<Double>().scaled(continuous(rangeLimits = Color.GREY to Color.BLUE)))
    label {
      position = LabelPosition.TOP
      formatter = "{@precipitation} ml"
    }
  }
  
  line {
    name("Evaporation")
    y("evaporation"<Double>())
    symbol(Symbol.diamond(20.0))
  }
  
  layout {
    title.text = "Precipitation and evaporation per week"
    legend {
      left = 50.pct
      bottom = 0.px
    }
  }
}
```
![GGDSL example echarts](examples/images/echarts_readme_sample.png)

## Modules
* `ggdsl-api` &mdash; contains `Plot` intermediate representation (IR), base plotting DSL and internal API.
* `ggdsl-lets-plot` &mdash; plot render implementation for [Lets-Plot](https://github.com/JetBrains/lets-plot) 
with additional DSL and IR features.
* `ggdsl-dataframe` &mdash; integration with [Kotlin Dataframe](https://github.com/Kotlin/dataframe).
* `ggdsl-dataframe-lets-plot` &mdash; additional methods compatible with `dataframe` API for `lets-plot` implementation.
* `ggdsl-echarts` &mdash; plot render implementation for [Apache ECharts](https://echarts.apache.org/en/index.html)
  with additional DSL and IR features.

## Using in your projects

### Gradle 

#### Kotlin DSL:

- Add the Maven Central repository if it is not already there:

```kotlin
repositories {
    mavenCentral()
}
```
- Add one of the api/implementation dependency:

```groovy
dependencies {
    implementation("org.jetbrains.kotlinx:ggdsl-dataframe-lets-plot:$ggdsl_version")
}
```

#### Groovy DSL:

- Add the Maven Central repository if it is not already there:

```kotlin
repositories {
    mavenCentral()
}
```
- Add one of the api/implementation dependency:

```groovy
dependencies {
    implementation "org.jetbrains.kotlinx:ggdsl-dataframe-lets-plot:$ggdsl_version"
}
```


### Kotlin Jupyter Notebook

Install [Kotlin kernel](https://github.com/Kotlin/kotlin-jupyter) for
[Jupyter](https://jupyter.org/)
or just visit to [Datalore](https://datalore.jetbrains.com/).

You can include all necessary dependencies, imports and renders to a notebook with "line magic":

``%use ggdsl($ggdsl_version)``

Use
```
%useLatestDescriptors
%use ggdsl
```
for the latest stable version.


Available descriptors:
    
* `ggdsl` &mdash; api, Lets-Plot implementation & DSL features + DataFrame integration.
* `ggdsl-lets-plot` &mdash; api, Lets-Plot implementation & DSL features.
* `ggdsl-dataframe` &mdash; api, DataFrame integration (todo remove???).
* `ggdsl-echarts` &mdash; api, ECharts implementation & DSL features. // todo with dataframe???

## Examples and documentation


Examples can be found [here](https://github.com/AndreiKingsley/lib-ggdsl/tree/main/examples).


## Contributing

If the library does not provide any functionality that you need, you can [create an issue](https://github.com/AndreiKingsley/lib-ggdsl/issues).

You are welcome to contribute &mdash; you can provide an IR translation for a new render engine or create add-ons for 
an existing one.


## *Note:*

*The name of the library is temporary and should be changed soon. We will be very happy with suggestions.*

