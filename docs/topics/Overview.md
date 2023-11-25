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
  [![Kotlin Notebook logo](ktn_plugin_icon.svg){style="inline"}Kotlin Notebook](https://plugins.jetbrains.com/plugin/16340-kotlin-notebook),
  leveraging the comprehensive capabilities and features of IntelliJ IDEA.

## DSL and Syntax

The Domain-Specific Language (DSL) of Kandy is designed for intuitive use,
offering a hierarchical and sequential approach to building data visualizations.
It combines flexibility with a clear structure,
enabling both novice and experienced users to efficiently create complex visualizations.
Below is a simplified overview of the Kandy DSL structure, as depicted in the following diagram:

![Kandy DSL Schema](kandy_dsl_schema.svg)

* **Plot** — the `plot` block forms the foundation of every visualization in Kandy, setting the stage for all other
  elements.
* **Data Manipulation** — this block plays a key role in transforming the initial data utilized in `plot`.
    * **Grouping** — this method groups data according to specified keys,
      allowing for organized and structured visualization based on distinct data segments.
    * **Statistical** — this set of methods applies statistical operations to the data,
      producing new datasets derived from these computations.
* **Layout** — this part deals with the graph's design, including elements like titles, subtitles, size,
  and thematic elements.
* **Layers** — it introduces different types of visual elements, such as lines, points, bars, etc.
* **Aesthetic Mappings / Settings** — This feature enables mapping data attributes to visual properties like color,
  shape, and size, offering extensive customization options.
* **Scale Specification** — this aspect is crucial for translating data values into appropriate visual scales on the
  graph, including settings for color gradients, size ranges, and positioning.

This structured approach provides a clear and logical pathway for navigating through Kandy's DSL,
enabling the construction of both informative and visually appealing visualizations.
The DSL is design is crafted to ensure ease of use, facilitating the efficient creation of sophisticated graphs.

Having explored the key components and organization of Kandy's DSL,
let's now illustrate how these elements come together in practice.
The following example demonstrates how the DSL is structure facilitates the creation of a visualization,
showcasing the ease and flexibility of crafting a plot in Kandy:

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