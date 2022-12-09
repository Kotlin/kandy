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

* [Modules](#modules)
* [Using in your projects](#using-in-your-projects)
    * [Gradle](#gradle)
    * [Kotlin Jupyter Notebook](#kotlin-jupyter-notebook)

<!--- END -->

## What does it look-and-feel like?

Seeing is believing; One look is worth a thousand words.

### Lets-Plot



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

Kotlin DSL:

- Add the Maven Central repository if it is not already there:

```kotlin
repositories {
    mavenCentral()
}
```
- Add one of the api/implementation dependency:

```groovy
dependencies {
    implementation("org.jetbrains.kotlinx:ggdsl-dataframe-lets-plot:$todo_release_version")
}
```

TODO: GroovyDSL?

### Kotlin Jupyter Notebook

Install [Kotlin kernel](https://github.com/Kotlin/kotlin-jupyter) for
[Jupyter](https://jupyter.org/)
or just visit to [Datalore](https://datalore.jetbrains.com/).

You can include all necessary dependencies, imports and renders to a notebook with "line magic":

``%use ggdsl($version)``

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

## Contributing

If the library does not provide any functionality that you need, you can create an issue.

You are welcome to contribute &mdash; you can provide an IR translation for a new render engine or create add-ons for 
an existing one.


// TODO contribution guide

## *About the name*

*The name of the library is temporary and should be changed soon. We will be very happy with suggestions.*





[Examples(Jupyter notebook with a Kotlin Kernel)](https://github.com/AndreiKingsley/lib-ggdsl/tree/main/examples)

