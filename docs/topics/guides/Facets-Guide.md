# Facets

<web-summary>
Get proficient in presenting multidimensional data with the guide by Kandy on Facets.
Discover how to use faceting successfully for a comprehensive comparative analysis among diverse data subsets.
</web-summary>

<card-summary>
Kandy's Facets guide: A deep dive into multi-faceted data visualization.
Explore techniques to compare and contrast data segments effectively.
</card-summary>

<link-summary>
Enhance your data analysis with Kandy's Facets guide.
Discover advanced strategies for comparative visualization, bringing clarity to complex data sets.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.Facets-->

### Creating multi-panel plots using `facets`.

#### Problem

You want to see more aspects of your data, and it's not practical to use the regular
<tooltip term="aes">`aesthetics`</tooltip> approach for that.

#### Solution - `facets`

You can add one or more new dimensions to your plot using `faceting`.

This approach allows you to split up your data by one or more variables and plot the subsets of data together.

In this demo, we will explore how various faceting functions work,
as well as the built-in `sorting` and `formatting` options.

To learn more about formatting templates, see:
[Formatting](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md).

<!---FUN guideFacetsReadDataFrame-->

```kotlin
val dataset = DataFrame.readCSV(
    "https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg2.csv",
    parserOptions = ParserOptions(Locale.ENGLISH)
)

dataset.head(3)
```

<!---END-->

| miles per gallon | number of cylinders | engine displacement \(cu. inches\) | engine horsepower | vehicle weight \(lbs.\) | time to accelerate \(sec.\) | model year | origin of car | vehicle name              |
|:-----------------|:--------------------|:-----------------------------------|:------------------|:------------------------|:----------------------------|:-----------|:--------------|:--------------------------|
| 18               | 8                   | 307                                | 130               | 3504                    | 12                          | 70         | US            | chevrolet chevelle malibu |
| 15               | 8                   | 350                                | 165               | 3693                    | 11.5                        | 70         | US            | buick skylark 320         |
| 18               | 8                   | 318                                | 150               | 3436                    | 11                          | 70         | US            | plymouth satellite        |

### One plot

Create a scatter plot to show how `mpg` is related to a car's `engine horsepower`.

Also use the `color` <tooltip term="aes">aesthetic</tooltip> to visualize the region where a car was designed.

<!---FUN guideFacetsScatterPlotByHorsePower-->

```kotlin
dataset.plot {
    x(`engine horsepower`)
    y(`miles per gallon`)
    points {
        color(`origin of car`)
    }
    layout {
        style(Style.Grey)
        size = 800 to 350
    }
}
```

<!---END-->

![Simple Plot without Facet](guideFacetsScatterPlotByHorsePower.svg)

## More dimensions

There are two functions for faceting:

- `facetGrid()`
- `facetWrap()`

The former creates 2-D matrix of plot panels, and the latter creates 1-D strip of plot panels.

We'll be using the `number of cylinders` variable as the first faceting variable,
and sometimes the `origin of car` as a second faceting variable.

### facetGrid()

The data can be split up by one or two variables that vary in the X and/or Y direction.

#### One facet

Let's split up the data by `number of cylinders`.

<!---FUN guideFacetsGridXPlot-->

```kotlin
dataset.plot {
    points {
        x(`engine horsepower`)
        y(`miles per gallon`)
        color(`origin of car`)
    }
    layout {
        style(Style.Grey)
    }
    facetGridX(`number of cylinders`)
}
```

<!---END-->

![Facet Grid X](guideFacetsGridXPlot.svg)

#### Two facets

Split up the data by two faceting variables: `number of cylinders` and `origin of car`.

<!---FUN guideFacetsTwoPlotsGrid-->

```kotlin
dataset.plot {
    points {
        x(`engine horsepower`)
        y(`miles per gallon`)
        color(`origin of car`)
    }
    layout {
        style(Style.Grey)
    }
    facetGrid(`number of cylinders`, `origin of car`)
}
```

<!---END-->

![Facet Grid](guideFacetsTwoPlotsGrid.svg)

#### Formatting and sorting.

Apply a formatting template to the `number of cylinders` and
sort the `origin of car` values in descending order.

To learn more about formatting templates, see:
[Formatting](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md).

<!---FUN guideFacetsWithFormattingAndSorting-->

```kotlin
dataset.plot {
    points {
        x(`engine horsepower`)
        y(`miles per gallon`)
        color(`origin of car`)
    }
    layout {
        style(Style.Grey)
    }
    facetGrid(`number of cylinders`, `origin of car`, xFormat = "{d} cyl", yOrder = OrderDirection.DESCENDING)
}
```

<!---END-->

![Facet Grid with Formatting and Sorting](guideFacetsWithFormattingAndSorting.svg)

### facetWrap()

The data can be split up by one or more variables.
The panel layout is flexible and controlled by `ncol`, `nrow` and `dir` options.

#### Facet Wrap One Column

Split data by the `number of cylinders` variable and arrange tiles in two rows.

<!---FUN guideFacetsWrapOnePlot-->

```kotlin
dataset.plot {
    points {
        x(`engine horsepower`)
        y(`miles per gallon`)
        color(`origin of car`)
    }
    layout {
        style(Style.Grey)
    }
    facetWrap(nRow = 2) {
        facet(`number of cylinders`)
    }
}
```

<!---END-->

![Wrap One Plot](guideFacetsWrapOnePlot.svg)

#### Facet Wrap Two Columns

Split data by `origin of car` and `number of cylinders` and arrange tiles in five columns.

<!---FUN guideFacetsWrapTwoPlots-->

```kotlin
dataset.plot {
    points {
        x(`engine horsepower`)
        y(`miles per gallon`)
        color(`origin of car`)
    }
    layout {
        style(Style.Grey)
    }
    facetWrap(nCol = 5) {
        facet(`origin of car`)
        facet(`number of cylinders`)
    }
}
```

<!---END-->

![Wrap Two Plots](guideFacetsWrapTwoPlots.svg)

#### Arrange panels vertically.

Use the `dir` parameter to arrange tiles by columns, in three columns (the default tile arrangement is "by row").

Also, format `number of cylinders` labels and reverse the sorting direction for this faceting variable.

<!---FUN guideFacetsArrangeVertically-->

```kotlin
dataset.plot {
    points {
        x(`engine horsepower`)
        y(`miles per gallon`)
        color(`origin of car`)
    }
    layout {
        style(Style.Grey)
    }
    facetWrap(nCol = 3, direction = Direction.VERTICAL) {
        facet(`origin of car`, OrderDirection.ASCENDING, null)
        facet(`number of cylinders`, OrderDirection.DESCENDING, "{} cyl")
    }
}
```

<!---END-->

![Arrange Vertical Facets](guideFacetsArrangeVertically.svg)

## Free scales on faceted plot

<!---FUN guideFreeFacetSimplePlot-->

```kotlin
dataset.plot {
    x(`engine horsepower`)
    y("engine displacement (cu. inches)"<Double>())
    points {
        color(`origin of car`)
    }
    layout {
        style(Style.Grey)
        size = 800 to 350
    }
}
```

<!---END-->

![Simple Plot without Free Scale](guideFreeFacetSimplePlot.svg)

### Faceted plot

#### `facetGrid()` with `fixed` scales (the default)

Scales are constant across all panels.

<!---FUN guideFreeFacetWithFixedScales-->

```kotlin
dataset.plot {
    x(`engine horsepower`)
    y("engine displacement (cu. inches)"<Double>())
    points {
        color(`origin of car`)
    }
    layout {
        style(Style.Grey)
        size = 800 to 500
    }
    facetGridY(`origin of car`)
}
```

<!---END-->

![Fixed Scale](guideFreeFacetWithFixedScales.svg)

#### `facetGrid()` with `free` Y-scales

<!---FUN guideFreeFacetGridYFree-->

```kotlin
dataset.plot {
    x(`engine horsepower`)
    y("engine displacement (cu. inches)"<Double>())
    points {
        color(`origin of car`)
    }
    layout {
        style(Style.Grey)
        size = 800 to 500
    }
    facetGridY(`origin of car`, scalesSharing = ScalesSharing.FREE_Y)
}
```

<!---END-->

![Free Y Scale](guideFreeFacetGridYFree.svg)

#### `facetWrap()` with `fixed` scales (the default)

Scales are constant across all panels.

<!---FUN guideFreeFacetWrapWithFixedScale-->

```kotlin
dataset.plot {
    x(`engine horsepower`)
    y("engine displacement (cu. inches)"<Double>())
    points {
        color(`origin of car`)
    }
    layout {
        style(Style.Grey)
        size = 800 to 500
    }
    facetWrap {
        facet(`number of cylinders`, order = OrderDirection.ASCENDING)
    }
}
```

<!---END-->

![Facet Wrap with Fixed Scale](guideFreeFacetWrapWithFixedScale.svg)

#### `facetWrap()` with `free` scales along both axes

<!---FUN guideFreeFacetWrapWithFreeScale-->

```kotlin
dataset.plot {
    x(`engine horsepower`)
    y("engine displacement (cu. inches)"<Double>())
    points {
        color(`origin of car`)
    }
    layout {
        style(Style.Grey)
        size = 800 to 500
    }
    facetWrap(scalesSharing = ScalesSharing.FREE) {
        facet(`number of cylinders`, order = OrderDirection.ASCENDING)
    }
}
```

<!---END-->

![Facet Wrap with Free Scale](guideFreeFacetWrapWithFreeScale.svg)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/facets.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/dmNOKlhZ7bfnXUS3GhvZIu" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
