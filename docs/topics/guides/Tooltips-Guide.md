# Tooltips

<web-summary>
Unlock interactive data exploration with Kandy's Tooltips guide.
Learn how to implement and customize tooltips to reveal detailed data insights on hover,
enhancing user engagement with your visualizations.
</web-summary>

<card-summary>
Kandy's Tooltips guide: A key to interactive charts.
Discover how to use tooltips to display rich data details, providing a more engaging and informative visual experience.
</card-summary>

<link-summary>
Dive into Kandy's guide on Tooltips to add interactive elements to your charts.
Learn to display data insights dynamically, making your visualizations more engaging and informative.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.Tooltips-->

> This guide is taken from lets-plot:
> [Tooltip Customization](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/tooltips.md).


You can customize the content of tooltips for the layer by using the parameter `tooltips` of layer functions.

The following parameters and functions set lines, define the formatting of the tooltip, its location and width:

```
tooltips(variables, formats, title, anchor, minWidth, hide) {
  LayerTooltipsContext -> line(template)
}
```

### Tooltip `variables` parameter (optional)

The `variables` parameter defines a list of variable names, which values will be placed line by line in the general
tooltip.
If formatting is specified for a variable from this list (with the `format` function), it will be applied.
Otherwise, the default formatting is used.
Additional tooltip lines can be specified using the `line` functions.

This is useful for configuring the tooltip content, instead of using the `line()` method to configure each line of the
tooltip.

Set the list of variables to place them in a multiline tooltip with the default formatting:

<!---FUN guideTooltipReadAutoDf-->

```kotlin
val mpgDf =
    DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
mpgDf.head()
```

<!---END-->

| untitled | manufacturer | model | displ | year | cyl | trans        | drv | cty | hwy | fl | class   |
|:---------|:-------------|:------|:------|:-----|:----|:-------------|:----|:----|:----|:---|:--------|
| 1        | audi         | a4    | 18    | 1999 | 4   | auto\(l5\)   | f   | 18  | 29  | p  | compact |
| 2        | audi         | a4    | 18    | 1999 | 4   | manual\(m5\) | f   | 21  | 29  | p  | compact |
| 3        | audi         | a4    | 2     | 2008 | 4   | manual\(m6\) | f   | 20  | 31  | p  | compact |
| 4        | audi         | a4    | 2     | 2008 | 4   | auto\(av\)   | f   | 21  | 30  | p  | compact |
| 5        | audi         | a4    | 28    | 1999 | 6   | auto\(l5\)   | f   | 16  | 26  | p  | compact |

<!---FUN guideTooltipVariablesTooltip-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(cty)
        fillColor(drv)
        size(hwy)

        symbol = Symbol.CIRCLE_FILLED
        color = Color.GREY

        tooltips(manufacturer, model, `class`, drv)
    }
}
```

<!---END-->

![](guideTooltipVariablesTooltip.png)

### Tooltip `formats` parameter

Parameter `formats` defines the format for displaying the value.
The format will be applied to the mapped value in the default tooltip or to the corresponding value
specified in the `line` template.
The format to apply to the field.
The format contains a number format (`'1.f'`) or a string template (`'{.1f}'`).
The numeric format for non-numeric value will be ignored.
The string template contains “replacement fields” surrounded by curly braces `{}`.
Any code not in the braces is considered literal text, and it will be copied unchanged to the result string.
If you need to include a brace character in the literal text,
it can be escaped by doubling: {{ and }}. For example:

- `cty to ".1f")` -> `"17.0"`
- `cty to "{.2f} (mpg)"))` -> `"17.00 (mpg)"`
- `cty to "{{{.2f}}}")` -> `"{17.00}"`
- `model to "{} {{text}}")` -> `"mustang {text}"`

### Customizing tooltip lines

Specifies the string template to use in a general tooltip. If you add `line()`, it overrides the default tooltip.

- `line("text")` -> `"text"`
- `line("{{text}}")` -> `"{text}"`
- `line("${value(model)}")` -> `"mustang"`
- `line("${model.tooltipValue()}")` -> `"mustang"`

#### Labels configuration

The default tooltip has a label before the value, usually containing the name of the mapped variable.
It has its own behavior similar to a blank label for an axis <tooltip term="aes">aesthetics</tooltip>.
This default label can be set in the template by using a pair of symbols `@|`.
You can override the label by specifying a string value before `|` symbol.

Within the tooltip line, you can align the label to left.
The string formed by a template can be aligned to right.
If you do not specify a label, the string will be centered in the tooltip.
For example:

- `line("^color")`: no label, value is centered;
- `line("|^color")`: label is empty, value is right-aligned;
- `line("@|^color")`: default label is used, value is right-aligned;
- `line("my label|^color")`: label is specified, value is right-aligned.

### Tooltip Title Parameter

Adds a title template to the tooltip.

The specification rules are the same as for the `line()` function.

A long title can be split into multiple lines using \n as a text separator.

### Tooltip anchor

Specifies a fixed position for a general tooltip.

The `Anchor` object accepts the following values:

- `TOP_RIGHT`
- `TOP_CENTER`
- `TOP_LEFT`
- `BOTTOM_RIGHT`
- `BOTTOM_CENTER`
- `BOTTOM_LEFT`
- `MIDDLE_RIGHT`
- `MIDDLE_CENTER`
- `MIDDLE_LEFT`

### Minimum width of general tooltip

Specifies minimum width of a general tooltip in pixels.

<!---FUN guideTooltipLinesTooltip-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(cty)
        fillColor(drv)
        size(hwy)

        symbol = Symbol.CIRCLE_FILLED
        color = Color.GREY

        tooltips {
            line("${manufacturer.tooltipValue()} ${model.tooltipValue()}")
            line("cty/hwy|${cty.tooltipValue(".1f")}/${hwy.tooltipValue(".1f")}")
            line(`class`)
            line("drive train", drv.tooltipValue("{}wd"))
            line(year, format = "d")
        }
    }

    layout {
        theme {
            legend.position = LegendPosition.None
        }
    }
}
```

<!---END-->

![](guideTooltipLinesTooltip.png)

Change format for the default tooltip:

<!---FUN guideTooltipVarLineTooltip-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(cty)
        fillColor(drv)
        size(hwy)

        symbol = Symbol.CIRCLE_FILLED
        color = Color.GREY

        tooltips {
            varLine("hwy", "{.2f} (mpg)")
        }
    }
}
```

<!---END-->

![](guideTooltipVarLineTooltip.png)

Place a general tooltip at the top center and define its minimum width:

<!---FUN guideTooltipAnchorAndMinWidth-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(cty)
        fillColor(drv)
        size(hwy)

        symbol = Symbol.CIRCLE_FILLED
        color = Color.GREY

        tooltips(anchor = Anchor.TOP_CENTER, minWidth = 200.0) {
            line("${manufacturer.tooltipValue()} ${model.tooltipValue()}")
            line("cty/hwy|${cty.tooltipValue(".1f")}/${hwy.tooltipValue(".1f")}")
            line(`class`)
            line("drive train", drv.tooltipValue("{}wd"))
            line(year, format = "d")
        }
    }
}
```

<!---END-->

![](guideTooltipAnchorAndMinWidth.png)

Move the tooltips to the top right corner:

<!---FUN guideTooltipReadIrisDf-->

```kotlin
val irisDf = DataFrame.readCSV(
    "https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/iris.csv",
    parserOptions = ParserOptions(Locale.ENGLISH)
)
irisDf.head()
```

<!---END-->

| sepal\_length | sepal\_width | petal\_length | petal\_width | species |
|:--------------|:-------------|:--------------|:-------------|:--------|
| 5.1           | 3.5          | 1.4           | 0.2          | setosa  |
| 4.9           | 3            | 1.4           | 0.2          | setosa  |
| 4.7           | 3.2          | 1.3           | 0.2          | setosa  |
| 4.6           | 3.1          | 1.5           | 0.2          | setosa  |
| 5             | 3.6          | 1.4           | 0.2          | setosa  |

<!---FUN guideTooltipDensityPlotTooltip-->

```kotlin
irisDf.groupBy { species }.plot {
    densityPlot(sepal_length) {
        tooltips(anchor = Anchor.TOP_RIGHT) {
            line(species.tooltipValue())
            line("length", Stat.x.tooltipValue())
            line("density", Stat.density.tooltipValue())
        }
    }
    layout {
        theme { legend.position = LegendPosition.None }
    }
}
```

<!---END-->

![](guideTooltipDensityPlotTooltip.png)

## Side tooltips configuration

In Kandy, certain <tooltip term="aes">aesthetics</tooltip> by default are represented by a so-called "side tooltip"
— a small tipped box containing just a single numeric value.

You can override these defaults using the `line()` function.
Configuring a "line" in a general multi-line tooltip disables side tooltip for the correspondent aesthetic.

<!---FUN guideTooltipBoxplotTooltip-->

```kotlin
mpgDf.plot {
    boxplot(`class`, hwy) {
        boxes {
            tooltips {
                line("y min/max", "${Stat.min.tooltipValue()}, ${Stat.max.tooltipValue()}")
                line("lower/upper", "${Stat.lower.tooltipValue()}, ${Stat.upper.tooltipValue()}")
                line("middle", Stat.middle.tooltipValue())
            }
        }
    }
}
```

<!---END-->

![](guideTooltipBoxplotTooltip.png)

Place the tooltip at the top center:

<!---FUN guideTooltipBoxplotAnchorCenter-->

```kotlin
mpgDf.plot {
    boxplot(`class`, hwy) {
        boxes {
            tooltips(anchor = Anchor.TOP_CENTER) {
                line("middle", Stat.middle.tooltipValue(".2f"))
                line("lower/upper", "${Stat.lower.tooltipValue("d")}, ${Stat.upper.tooltipValue("d")}")
                line("y min/max", "${Stat.min.tooltipValue("d")}, ${Stat.max.tooltipValue("d")}")
            }
        }
    }
}
```

<!---END-->

![](guideTooltipBoxplotAnchorCenter.png)

## Hiding tooltips

Set `tooltips(true)` to hide tooltips from the layer.

To hide axis tooltips, you can set "blank" parameters of the `theme()` function.

<!---FUN guideTooltipHide-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(cty)
        fillColor(drv)
        size(hwy)

        symbol = Symbol.CIRCLE_FILLED
        color = Color.GREY

        tooltips(hide = true)
    }
}
```

<!---END-->

## Tooltip Title

### Splitting text using `\n`

<!---FUN guideTooltipSplittingText-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(cty)
        fillColor(drv)
        size(hwy)

        symbol = Symbol.CIRCLE_FILLED
        color = Color.GREY

        tooltips {
            line("${value(manufacturer)} \n${value(model)}")
            line(`class`)
            line(year)
        }
    }
}
```

<!---END-->

![](guideTooltipSplittingText.png)

### Adding a title

<!---FUN guideTooltipTitle-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(cty)
        fillColor(drv)
        size(hwy)

        symbol = Symbol.CIRCLE_FILLED
        color = Color.GREY

        tooltips(title = "${value(manufacturer)} ${value(model)}") {}
    }
    layout {
        theme { legend.position = LegendPosition.None }
    }
}
```

<!---END-->

![](guideTooltipTitle.png)

<!---FUN guideTooltipVariablesTitle-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(cty)
        fillColor(drv)
        size(hwy)

        symbol = Symbol.CIRCLE_FILLED
        color = Color.GREY

        tooltips(`class`, year, title = "${value(manufacturer)} ${value(model)}") {}
    }
    layout {
        theme { legend.position = LegendPosition.None }
    }
}
```

<!---END-->

![](guideTooltipVariablesTitle.png)

<!---FUN guideTooltipSplittingTextInTitle-->

```kotlin
mpgDf.plot {
    points {
        x(displ)
        y(cty)
        fillColor(drv)
        size(hwy)

        symbol = Symbol.CIRCLE_FILLED
        color = Color.GREY

        tooltips(title = "Car info: \n${value(manufacturer)} ${value(model)}") {
            line(`class`)
            line("drive train", value(drv))
            line(year)
        }
    }
    layout {
        theme { legend.position = LegendPosition.None }
    }
}
```

<!---END-->

![](guideTooltipSplittingTextInTitle.png)

## Tooltip Theme

<!---FUN guideTooltipDatasetForTheme-->

```kotlin
val dataset = dataFrameOf("x" to listOf(0.0, 1.0), "y" to listOf(0.0, 1.0))
```

<!---END-->

<!---FUN guideTooltipWithoutTheme-->

```kotlin
plot(dataset) {
    tiles {
        x("x")
        y("y")
        fillColor = Color.GREY

        tooltips(title = "Tooltip title") {
            line("label|value")
        }
    }
}
```

<!---END-->

![](guideTooltipWithoutTheme.png)

<!---FUN guideTooltipWithTheme-->

```kotlin
plot(dataset) {
    tiles {
        x("x")
        y("y")
        fillColor = Color.GREY

        tooltips(title = "Tooltip title") {
            line("label|value")
        }
    }

    layout.theme {
        layerTooltips {
            background {
                borderLineColor = Color.hex("#225e32")
                fillColor = Color.hex("#238b45")
                borderLineWidth = 2.0
            }
            text {
                color = Color.hex("#bae4b3")
            }
            title {
                color = Color.hex("#edf8e9")
            }
        }
        xAxis.tooltip.text {
            color = Color.GREEN
        }
    }
}
```

<!---END-->

![](guideTooltipWithTheme.png)


<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/tooltips.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/qWdPqSTjZnv6El7H1nfrp9" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
