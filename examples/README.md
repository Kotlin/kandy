# Examples of Kandy

## Table of Contents

<!--- TOC -->

* [User Guide](#user-guide)
* [IDEA Examples](#idea-examples)
* [Notebook Examples](#notebook-examples)
    * [Line](#line)
    * [Area](#area)
    * [Bars](#bars)
    * [Points](#points)
    * [Error Bars](#error-bars)
    * [Boxplot](#boxplot)
    * [Tiles](#tiles)

<!--- END -->

## User Guide

* [**Quick Start Guide**](https://kotlin.github.io/kandy/quick-start-guide.html) - [Notebook](https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/quick_start_guide.ipynb) / [Datalore](https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/TKul6gxAVDbJmocwQi9UjB)
* **Geoms**
    * algebraic_curve - Notebook / Datalore
    * error_bars - Notebook / Datalore
    * jitter_points - Notebook / Datalore
    * lines - Notebook / Datalore
    * pie - Notebook / Datalore
    * ribbon - Notebook / Datalore
    * scatter_plot - Notebook / Datalore
* **Statistical**
    * statistics - Notebook / Datalore
    * smoothing - Notebook / Datalore
    * histogram - Notebook / Datalore
    * boxplot - Notebook / Datalore
    * heatmap - Notebook / Datalore
    * countPlot - Notebook / Datalore
    * densityPlot - Notebook / Datalore
* **Multiplot**
    * anscombes_quartet
    * facets
    * plot_bunch
* **Layout customization**
    * label_format - Notebook / Datalore
    * legend_and_axis - Notebook / Datalore
    * themes - Notebook / Datalore
    * title_subtitle_caption - Notebook / Datalore
    * tooltips - Notebook / Datalore
* **Other guides**
    * export_to_file
    * kotlin_notebook_features
    * series_hack

## IDEA examples

[**lets-plot-simple**](idea-examples/lets-plot-simple/src/main/kotlin/org/jetbrains/kotlinx/kandy/letsplot/simple_lets_plot.kt) —
The following is an example of using the `kandy-lets-plot` library in an IntelliJ IDEA project.

___


[**echarts-simple**](idea-examples/echarts-simple/src/main/kotlin/org/jetbrains/kotlinx/kandy/echarts/simple_echarts.kt) —
The following is an example of using the `kandy-echarts` library in an IntelliJ IDEA project.

## Notebook Examples

### Line

<table>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/simple-line.html">
                <img src="../docs/images/samples/line/simple_line.svg" alt="Simple Line"/>
                <b>Simple Line</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/line-settings.html">
                <img src="../docs/images/samples/line/simple_line_settings.svg" alt="Line Settings"/>
                <b>Line Settings</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/line-with-points.html">
                <img src="../docs/images/samples/line/line_with_points.svg" alt="Line with Points"/>
                <b>Line with Points</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/fixed-line-coordinate.html">
                <img src="../docs/images/samples/line/line_fixed_coord.svg" alt="Fixed Line Coordinate"/>
                <b>Fixed Line Coordinate</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/line-gradient.html">
                <img src="../docs/images/samples/line/line_color_gradient.svg" alt="Line Gradient"/>
                <b>Line Gradient</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/line-with-reversed-axis.html">
                <img src="../docs/images/samples/line/line_reversed_axis.svg" alt="Line with Reversed Axis"/>
                <b>Line with Reversed Axis</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/several-lines.html">
                <img src="../docs/images/samples/line/several_lines.svg" alt="Several Lines"/>
                <b>Several Lines</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/functional-line-plot.html">
                <img src="../docs/images/samples/line/line_by_fun.svg" alt="Functional Line Plot"/>
                <b>Functional Line Plot</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/marked-line.html">
                <img src="../docs/images/samples/line/line_mark.svg" alt="Marked Line"/>
                <b>Marked Line</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/path-line.html">
                <img src="../docs/images/samples/line/path_line.svg" alt="Path Line"/>
                <b>Path Line</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/step-line.html">
                <img src="../docs/images/samples/line/step_line.svg" alt="Step Line"/>
                <b>Step Line</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/smoothed-line.html">
                <img src="../docs/images/samples/line/smoothed_line.svg" alt="Smoothed Line"/>
                <b>Smoothed Line</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/smoothed-curve-with-points.html">
                <img src="../docs/images/samples/line/smoothed_curve_with_points.svg" alt="Smoothed Curve with Points"/>
                <b>Smoothed Curve with Points</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/comparing-line-vs-path-plots.html">
                <img src="../docs/images/samples/line/preview_line_and_path_comp.svg" alt="Comparing Line vs. Path Plots"/>
                <b>Comparing Line vs. Path Plots</b>
            </a>
        </td>
    </tr>
</table>

### Area

<table>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/simple-area.html">
                <img src="../docs/images/samples/area/simple_area.svg" alt="Simple Area"/>
                <b>Simple Area</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/area-settings.html">
                <img src="../docs/images/samples/area/area_settings.svg" alt="Area Settings"/>
                <b>Area Settings</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/fixed-area-coordinate.html">
                <img src="../docs/images/samples/area/area_fixed.svg" alt="Area with Fixed Coordinate"/>
                <b>Area with Fixed Coordinate</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/area-with-reversed-axis.html">
                <img src="../docs/images/samples/area/area_with_reversed_axis.svg" alt="Area with Reversed Axis"/>
                <b>Area with Reversed Axis</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/several-areas.html">
                <img src="../docs/images/samples/area/several_areas.svg" alt="Several Areas"/>
                <b>Several Areas</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/functional-area.html">
                <img src="../docs/images/samples/area/functional_area_plot.svg" alt="Functional Area Plot"/>
                <b>Functional Area Plot</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/area-with-mark-line.html">
                <img src="../docs/images/samples/area/area_with_mark_line.svg" alt="Area with Mark Line"/>
                <b>Area with Mark Line</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/smoothed-area.html">
                <img src="../docs/images/samples/area/smoothed_area.svg" alt="Smoothed Area"/>
                <b>Smoothed Area</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/smoothed-area-with-points.html">
                <img src="../docs/images/samples/area/smoothed_area_with_points.svg" alt="Smoothed Area with Points"/>
                <b>Smoothed Area with Points</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/density-plot.html">
                <img src="../docs/images/samples/area/density_plot.svg" alt="Density Plot"/>
                <b>Density Plot</b>
            </a>
        </td>
    </tr>
</table>

### Bars

<table>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/simple-bars.html">
                <img src="../docs/images/samples/bars/simple_bar_plot.svg" alt="Simple Bar"/>
                <b>Simple Bar</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/bar-settings.html">
                <img src="../docs/images/samples/bars/bar_settings.svg" alt="Bar Settings"/>
                <b>Bar Settings</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/bar-gradient.html">
                <img src="../docs/images/samples/bars/bar_gradient.svg" alt="Bar Gradient"/>
                <b>Bar Gradient</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/fixed-bar-coordinate.html">
                <img src="../docs/images/samples/bars/fixed_bar.svg" alt="Fixed Bar Coordinate"/>
                <b>Fixed Bar Coordinate</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/bar-with-reversed-axis.html">
                <img src="../docs/images/samples/bars/bar_with_reversed_axis.svg" alt="Bar with Reversed Axis"/>
                <b>Bar with Reversed Axis</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/horizontal-bars.html">
                <img src="../docs/images/samples/bars/horizontal_bars.svg" alt="Horizontal Bars"/>
                <b>Horizontal Bars</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/grouped-bars.html">
                <img src="../docs/images/samples/bars/grouped_bars.svg" alt="Grouped Bars"/>
                <b>Grouped Bars</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/stacked-bars.html">
                <img src="../docs/images/samples/bars/stacked_bars.svg" alt="Stacked Bars"/>
                <b>Stacked Bars</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/functional-bars.html">
                <img src="../docs/images/samples/bars/functional_bars_plot.svg" alt="Functional Bar Plot"/>
                <b>Functional Bar Plot</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/histogram-bar-plot.html">
                <img src="../docs/images/samples/bars/histogram_2.svg" alt="Histogram Plot"/>
                <b>Histogram Plot</b>
            </a>
        </td>
    </tr>
</table>

### Points

<table>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/basic-points-plot.html">
                <img src="../docs/images/samples/points/basic_points_plot.svg" alt="Basic Points Plot"/>
                <b>Basic Points Plot</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/points-settings.html">
                <img src="../docs/images/samples/points/points_settings.svg" alt="Points Settings"/>
                <b>Points Settings</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/points-with-abline.html">
                <img src="../docs/images/samples/points/points_with_abLine.svg" alt="Points with ABLine"/>
                <b>Points with ABLine</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/points-gradient.html">
                <img src="../docs/images/samples/points/points_gradient.svg" alt="Points Gradient"/>
                <b>Points Gradient</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/points-with-color-by-category.html">
                <img src="../docs/images/samples/points/points_with_color_by_category.svg" alt="Points with Color by Category"/>
                <b>Points with Color by Category</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/fixed-points.html">
                <img src="../docs/images/samples/points/fixed_points.svg" alt="Fixed Points Coordinate"/>
                <b>Fixed Points Coordinate</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/jittered-points.html">
                <img src="../docs/images/samples/points/jittered_points.svg" alt="Jittered Points"/>
                <b>Jittered Points</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/bubble-chart.html">
                <img src="../docs/images/samples/points/bubble_chart.svg" alt="Bubble Chart"/>
                <b>Bubble Chart</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/complex-bubble-chart.html">
                <img src="../docs/images/samples/points/complex_bubble_chart.svg" alt="Complex Bubble Chart"/>
                <b>Complex Bubble Chart</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/functional-scatter-plot.html">
                <img src="../docs/images/samples/points/functional_scatter_plot.svg" alt="Functional Scatter Plot"/>
                <b>Functional Scatter Plot</b>
            </a>
        </td>
    </tr>
</table>

### Error Bars

<table>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/simple-errorbars.html">
                <img src="../docs/images/samples/errorBars/simple_error_bar_plot.svg" alt="Simple ErrorBars"/>
                <b>Simple ErrorBars</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/errorbars-settings.html">
                <img src="../docs/images/samples/errorBars/error_bars_settings.svg" alt="ErrorBars Settings"/>
                <b>ErrorBars Settings</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/errorbars-with-line.html">
                <img src="../docs/images/samples/errorBars/error_bars_with_line.svg" alt="ErrorBars with Line"/>
                <b>ErrorBars with Line</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/fixed-errorbars.html">
                <img src="../docs/images/samples/errorBars/fixed_error_bars.svg" alt="Fixed ErrorBars Coordinate"/>
                <b>Fixed ErrorBars Coordinate</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/borderline-errorbars.html">
                <img src="../docs/images/samples/errorBars/border_line_error_bars.svg" alt="BorderLine in ErrorBars"/>
                <b>BorderLine in ErrorBars</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/grouped-errorbars.html">
                <img src="../docs/images/samples/errorBars/grouped_error_bars.svg" alt="Grouped ErrorBars"/>
                <b>Grouped ErrorBars</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/errorbars-with-boxplot.html">
                <img src="../docs/images/samples/errorBars/error_bars_with_boxplot.svg" alt="ErrorBars with Boxplot"/>
                <b>ErrorBars with Boxplot</b>
            </a>
        </td>
    </tr>
</table>

### Boxplot

<table>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/boxplot-expr.html">
                <img src="../docs/images/samples/boxplot/boxplot_expr.svg" alt="Boxplot of Experiments"/>
                <b>Boxplot of Experiments</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/boxplot-life-exp.html">
                <img src="../docs/images/samples/boxplot/boxplot_life_exp.svg" alt="Boxplot Life Expectations by Country"/>
                <b>Boxplot Life Expectations by Country</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/boxplot-categories.html">
                <img src="../docs/images/samples/boxplot/boxplot_categories.svg" alt="Boxplot Categories"/>
                <b>Boxplot Categories</b>
            </a>
        </td>
    </tr>
</table>

### Tiles

<table>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/basic-tile-plot.html">
                <img src="../docs/images/samples/tiles/basic_tile_plot.svg" alt="Basic Tile Plot"/>
                <b>Basic Tile Plot</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/tiles-settings.html">
                <img src="../docs/images/samples/tiles/tiles_settings.svg" alt="Tiles Settings"/>
                <b>Tiles Settings</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/fixed-tiles.html">
                <img src="../docs/images/samples/tiles/fixed_tile.svg" alt="Fixed Tiles Coordinate"/>
                <b>Fixed Tiles Coordinate</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/tiles-gradient.html">
                <img src="../docs/images/samples/tiles/tiles_gradient.svg" alt="Tiles Gradient"/>
                <b>Tiles Gradient</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/tiles-color-categories.html">
                <img src="../docs/images/samples/tiles/tiles_color_categories.svg" alt="Tiles with Color by Category"/>
                <b>Tiles with Color by Category</b>
            </a>
        </td>
        <td>
            <a href="https://kotlin.github.io/kandy/basic-heatmap.html">
                <img src="../docs/images/samples/tiles/basic_heatmap.svg" alt="Basic Heatmap"/>
                <b>Basic Heatmap</b>
            </a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="https://kotlin.github.io/kandy/tiles-logo.html">
                <img src="../docs/images/samples/tiles/tiles_ktnb_logo.svg" alt="Ktnb Logo Tiles"/>
                <b>Ktnb Logo Tiles</b>
            </a>
        </td>
    </tr>
</table>
