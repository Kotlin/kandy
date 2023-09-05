# Overview

<web-summary>
    Get introduced to Kandy, the powerful plotting library for Kotlin.
    Understand the philosophy driving its design, its standout features, and the motivations behind its creation.
</web-summary>
<card-summary>
    Dive into Kandy's philosophy, core features, and what makes it a unique choice for plotting in Kotlin.
</card-summary>
<link-summary>
    Kandy Unveiled: Philosophy, Features, and Motivations.
</link-summary>

## What is Kandy?

Kandy is an open-source data visualization library crafted exclusively for Kotlin.
Embracing a contemporary approach to data visualization within a statically typed language framework,
Kandy presents an idiomatic and flexible DSL.
This DSL integrates seamlessly with Kotlin's type safety and robust tooling,
facilitating the rapid crafting of graphs with markedly reduced exceptions.
Furthermore, it capitalizes on various popular engines,
enhancing its versatility and performance for a streamlined and powerful graph-building experience.

## Main Features

* _**Simplicity**_ — Kandy offers an easy-to-use, straightforward, and intuitive API that facilitates a quick start in
  crafting visualizations, removing the steep learning curve usually associated with data visualization tools.
* _**Flexibility**_ — with Kandy, users are granted a vast array of options to cater to a diverse range of visualization
  needs, making it a versatile choice for data representation projects.
* _**Readability**_ — leveraging a concise DSL, Kandy ensures a clear and succinct representation of the graphs being
  constructed.
  This DSL, which is both easy to read and understand, opens the door to users with varying levels of
  experience, promoting accessibility and user-friendliness.
* _**Multi-engine**_ — Kandy features a universal DSL compatible with various renowned engines, affording users the
  luxury to select an engine that aligns with their needs and preferences, all without the necessity to grasp a new
  syntax.
* _**Type Safety**_ — Kandy prioritizes secure data handling by offering type safety and fully supporting Kotlin's null
  safety features, thereby guaranteeing a secure and efficient data manipulation experience.
* _**Data Flexibility**_ —
  Kandy effortlessly accommodates collections and [DataFrame](https://kotlin.github.io/dataframe/overview.html),
  empowering users to integrate a
  broad spectrum of data formats into their visualizations, enhancing both utility and adaptability.
* _**Tooling Support**_ — Kandy is fully integrated with
  [![Kotlin Notebook logo](pluginIcon.svg){style="inline"}Kotlin Notebook](https://plugins.jetbrains.com/plugin/16340-kotlin-notebook),
  leveraging the comprehensive capabilities and features of IntelliJ IDEA.

## DSL and Syntax

The DSL of Kandy is both hierarchical and sequential,
comprising components such as plot configurations, layers, mappings, and scales.
This structure fosters a potent tool that is user-friendly, allowing for straightforward navigation and utilization.

As with many data visualization libraries, Kandy adopts a data-driven approach,
wherein the construction of graphs is fundamentally based on the underlying data,
enhancing the accuracy and relevance of visualizations.

The following diagram offers a simplified sketch of the DSL structure,
providing a foundational understanding of the DSL approach without delving into intricate details:

![Basic structure of DSL](tree-basic.svg) { thumbnail="true" }

Moreover, Kandy leverages the diverse functionalities of Kotlin to streamline code writing processes,
making the task of creating visual representations more intuitive and less time-consuming.
Here is an example of the syntax that Kandy employs:

```kotlin
val time = columnOf(0, 1, 2, 4, 5, 7, 8, 9) named "time"
val temperature = columnOf(12.0, 14.2, 15.1, 15.9, 17.9, 15.6, 14.2, 24.3) named "temperature"
val humidity = columnOf(0.5, 0.32, 0.11, 0.89, 0.68, 0.57, 0.56, 0.5) named "humidity"

val simpleDataFrame = dataFrameOf(time, temperature, humidity)

plot(simpleDataFrame) {
    x("time")
    y("temperature") {
        scale = continuous(0.0..25.5)
    }

    bars {
        fillColor("humidity") {
            scale = continuous(range = Color.YELLOW..Color.RED)
        }
        borderLine.width = 0.0
    }

    line {
        width = 3.0
        color = Color.hex("#6e5596")
        type = LineType.DOTDASH
    }

    layout {
        title = "Simple plot with kandy-lets-plot"
        size = 700 to 450
        caption = "See `examples` section for more\n complicated and interesting examples!"
    }
}
```

![Simple plot with Kandy](overview_sample.svg) { border-effect="rounded" }

More examples of working with the library can be found [here](Examples.topic).