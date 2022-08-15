package org.jetbrains.kotlinx.ggdsl.letsplot.theme

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext

enum class Font {
    PLAIN, ITALIC, BOLD, BOLD_ITALIC;

    override fun toString(): String = super.toString().lowercase()
}

sealed interface LayoutParameters : SelfInvocationContext {
    companion object {
        fun line(
            color: Color? = null,
            width: Number? = null, blank:
            Boolean = false
        ) = LineParameters(color, width, blank)

        fun text(
            color: Color? = null,
            font: Font? = null,
            blank: Boolean = false
        ) = TextParameters(color, font, blank)

        fun background(
            fillColor: Color? = null,
            borderLineColor: Color? = null,
            borderLineWidth: Number? = null,
            blank: Boolean = false
        ) = BackgroundParameters(fillColor, borderLineColor, borderLineWidth, blank)
    }
}

@PlotDslMarker
data class LineParameters internal constructor(
    var color: Color? = null,
    var width: Number? = null,
    var blank: Boolean = false,
) : LayoutParameters


@PlotDslMarker
data class TextParameters internal constructor(
    var color: Color? = null,
    var font: Font? = null,
    var blank: Boolean = false
) : LayoutParameters

@PlotDslMarker
data class BackgroundParameters internal constructor(
    var fillColor: Color? = null,
    var borderLineColor: Color? = null,
    var borderLineWidth: Number? = null,
    var blank: Boolean = false
) : LayoutParameters

@PlotDslMarker
data class Global internal constructor(
    val line: LineParameters = LineParameters(),
    val background: BackgroundParameters = BackgroundParameters(),
    val text: TextParameters = TextParameters(),
    val axis: LineParameters = LineParameters(),
) : SelfInvocationContext

@PlotDslMarker
data class LayerTooltips internal constructor(
    val background: BackgroundParameters = BackgroundParameters(),
    val title: TextParameters = TextParameters(),
    val text: TextParameters = TextParameters()
) : SelfInvocationContext

@PlotDslMarker
data class AxisTooltip internal constructor(
    val background: BackgroundParameters = BackgroundParameters(),
    val text: TextParameters = TextParameters()
) : SelfInvocationContext

@PlotDslMarker
data class Axis internal constructor(
    var onTop: Boolean? = null,
    val title: TextParameters = TextParameters(),
    val text: TextParameters = TextParameters(),
    val ticks: LineParameters = LineParameters(),
    var ticksLength: Number? = null,
    val line: LineParameters = LineParameters(),
    val tooltip: AxisTooltip = AxisTooltip(),
    // TODO blank all??
    //var blank: Boolean?
) : SelfInvocationContext

interface LegendPosition {
    companion object {
        object None : LegendPosition
        object Left : LegendPosition
        object Right : LegendPosition
        object Bottom : LegendPosition
        object Top : LegendPosition

        data class Custom (val x: Double, val y: Double) : LegendPosition
    }
}

interface LegendJustification {
    companion object {
        object Center : LegendPosition
        data class Custom(val x: Double, val y: Double) : LegendPosition
    }
}

enum class LegendDirection {
    HORIZONTAL,
    VERTICAL
}

@PlotDslMarker
data class Legend internal constructor(
    val background: BackgroundParameters = BackgroundParameters(),
    val text: TextParameters = TextParameters(),
    val title: TextParameters = TextParameters(),

    var position: LegendPosition? = null,
    var justification: LegendJustification? = null,
    var direction: LegendDirection? = null,
) : SelfInvocationContext

@PlotDslMarker
data class Grid internal constructor(
    val lineGlobal: LineParameters = LineParameters(),
    val majorLine: LineParameters = LineParameters(),
    val majorXLine: LineParameters = LineParameters(),
    val majorYLine: LineParameters = LineParameters(),
    val minorLine: LineParameters = LineParameters(),
    val minorXLine: LineParameters = LineParameters(),
    val minorYLine: LineParameters = LineParameters(),
) : SelfInvocationContext

@PlotDslMarker
data class Panel internal constructor(
    val background: BackgroundParameters = BackgroundParameters(),
    val borderLine: LineParameters = LineParameters(),
    val grid: Grid = Grid()
) : SelfInvocationContext

@PlotDslMarker
data class Plot internal constructor(
    val background: BackgroundParameters = BackgroundParameters(),
    val title: TextParameters = TextParameters(),
    val subtitle: TextParameters = TextParameters(),
    val caption: TextParameters = TextParameters(),
) : SelfInvocationContext

@PlotDslMarker
data class Strip internal constructor(
    val background: BackgroundParameters = BackgroundParameters(),
    val text: TextParameters = TextParameters(),
) : SelfInvocationContext

@PlotDslMarker
data class CustomTheme @PublishedApi internal constructor(
    val global: Global = Global(),
    val axis: Axis = Axis(),
    val xAxis: Axis = Axis(),
    val yAxis: Axis = Axis(),
    val legend: Legend = Legend(),
    val panel: Panel = Panel(),
    val plot: Plot = Plot(),
    val strip: Strip = Strip(),
    val layerTooltips: LayerTooltips = LayerTooltips()
) : Theme

inline fun theme(block: CustomTheme.() -> Unit): CustomTheme {
    return CustomTheme().apply(block)
}
