package org.jetbrains.kotlinx.ggdsl.letsplot.theme

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.util.font.FontFace
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext

sealed interface LayoutParameters {
    companion object {
        fun line(
            color: Color? = null,
            width: Number? = null, blank:
            Boolean = false
        ) = LineParameters(color, width, blank)

        fun text(
            color: Color? = null,
            font: FontFace? = null,
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
    var font: FontFace? = null,
    var blank: Boolean = false
) : LayoutParameters

@PlotDslMarker
data class BackgroundParameters internal constructor(
    var fillColor: Color? = null,
    var borderLineColor: Color? = null,
    var borderLineWidth: Number? = null,
    var blank: Boolean = false
) : LayoutParameters

interface WithBackground {
    var background: BackgroundParameters?

    fun background(block: BackgroundParameters.() -> Unit) {
        background = BackgroundParameters().apply(block)
    }

    fun background(parameters: BackgroundParameters) {
        background = parameters
    }
}

interface WithLine {
    var line: LineParameters?

    fun line(block: LineParameters.() -> Unit) {
        line = LineParameters().apply(block)
    }

    fun line(parameters: LineParameters) {
        line = parameters
    }
}

interface WithText {
    var text: TextParameters?

    fun text(block: TextParameters.() -> Unit) {
        text = TextParameters().apply(block)
    }

    fun text(parameters: TextParameters) {
        text = parameters
    }
}

interface WithTitle {
    var title: TextParameters?

    fun title(block: TextParameters.() -> Unit) {
        title = TextParameters().apply(block)
    }

    fun title(parameters: TextParameters) {
        title = parameters
    }
}



@PlotDslMarker
data class Global internal constructor(
    override var line: LineParameters? = null,
    override var background: BackgroundParameters? = null,
    override var text: TextParameters? = null,
    var axis: LineParameters? = null,
) : SelfInvocationContext, WithLine, WithBackground, WithText {
    fun axis(block: LineParameters.() -> Unit) {
        axis = LineParameters().apply(block)
    }

    fun axis(parameters: LineParameters) {
        axis = parameters
    }
}

@PlotDslMarker
data class LayerTooltips internal constructor(
    override var background: BackgroundParameters? = null,
    override var title: TextParameters? = null,
    override var text: TextParameters? = null,
) : SelfInvocationContext, WithBackground, WithText, WithTitle

@PlotDslMarker
data class AxisTooltip internal constructor(
    override var background: BackgroundParameters? = null,
    override var text: TextParameters? = null,
) : SelfInvocationContext, WithBackground, WithText

@PlotDslMarker
data class Axis internal constructor(
    var onTop: Boolean? = false,
    override var title: TextParameters? = null,
    override var text: TextParameters? = null,
    var ticks: LineParameters? = null,
    var ticksLength: Number? = null,
    override var line: LineParameters? = null,
    val tooltip: AxisTooltip = AxisTooltip(),
    // TODO blank all??
    //var blank: Boolean?
) : SelfInvocationContext, WithText, WithTitle, WithLine {
    fun ticks(block: LineParameters.() -> Unit) {
        ticks = LineParameters().apply(block)
    }

    fun ticks(parameters: LineParameters) {
        ticks = parameters
    }
}

sealed interface LegendPosition {

    object None : LegendPosition
    object Left : LegendPosition
    object Right : LegendPosition
    object Bottom : LegendPosition
    object Top : LegendPosition

    data class Custom(val x: Double, val y: Double) : LegendPosition

}

sealed interface LegendJustification {
    object Center : LegendJustification
    data class Custom(val x: Double, val y: Double) : LegendJustification
}

enum class LegendDirection {
    HORIZONTAL,
    VERTICAL
}

@PlotDslMarker
data class Legend internal constructor(
    override var background: BackgroundParameters? = null,
    override var title: TextParameters? = null,
    override var text: TextParameters? = null,

    var position: LegendPosition? = null,
    var justification: LegendJustification? = null,
    var direction: LegendDirection? = null,
) : SelfInvocationContext, WithText, WithTitle, WithBackground {
    fun justification(x: Double, y: Double) {
        justification = LegendJustification.Custom(x, y)
    }

    /*
    fun justification(value: LegendJustification) {
        justification = value
    }

     */
    fun position(x: Double, y: Double) {
        position = LegendPosition.Custom(x, y)
    }
    /*
    fun position(value: LegendPosition) {
        position = value
    }
    fun direction(value: LegendDirection) {
        direction = value
    }

     */
}

@PlotDslMarker
data class Grid internal constructor(
    var lineGlobal: LineParameters? = null,
    var majorLine: LineParameters? = null,
    var majorXLine: LineParameters? = null,
    var majorYLine: LineParameters? = null,
    var minorLine: LineParameters? = null,
    var minorXLine: LineParameters? = null,
    var minorYLine: LineParameters? = null,
) : SelfInvocationContext {
    fun lineGlobal(block: LineParameters.() -> Unit) {
        lineGlobal = LineParameters().apply(block)
    }

    fun lineGlobal(parameters: LineParameters) {
        lineGlobal = parameters
    }

    fun majorLine(block: LineParameters.() -> Unit) {
        majorLine = LineParameters().apply(block)
    }

    fun majorLine(parameters: LineParameters) {
        majorLine = parameters
    }

    fun majorXLine(block: LineParameters.() -> Unit) {
        majorXLine = LineParameters().apply(block)
    }

    fun majorXLine(parameters: LineParameters) {
        majorXLine = parameters
    }

    fun majorYLine(block: LineParameters.() -> Unit) {
        majorYLine = LineParameters().apply(block)
    }

    fun majorYLine(parameters: LineParameters) {
        majorYLine = parameters
    }

    fun minorLine(block: LineParameters.() -> Unit) {
        minorLine = LineParameters().apply(block)
    }

    fun minorLine(parameters: LineParameters) {
        minorLine = parameters
    }

    fun minorXLine(block: LineParameters.() -> Unit) {
        majorLine = LineParameters().apply(block)
    }

    fun minorXLine(parameters: LineParameters) {
        majorLine = parameters
    }

    fun minorYLine(block: LineParameters.() -> Unit) {
        minorYLine = LineParameters().apply(block)
    }

    fun minorYLine(parameters: LineParameters) {
        minorYLine = parameters
    }
    // TODO
    /*
    var blank: Boolean = false
        set(value) {
            if (value) {
                majorLine.blank = true
                majorXLine.blank = true
                majorYLine.blank = true
            }
            field = value
        }

     */
}

@PlotDslMarker
data class Panel internal constructor(
    override var background: BackgroundParameters? = null,
    var borderLine: LineParameters? = null,
    val grid: Grid = Grid()
) : SelfInvocationContext, WithBackground {
    fun borderLine(block: LineParameters.() -> Unit) {
        borderLine = LineParameters().apply(block)
    }

    fun borderLine(parameters: LineParameters) {
        borderLine = parameters
    }
}

@PlotDslMarker
data class Plot internal constructor(
    override var background: BackgroundParameters? = null,
    override var title: TextParameters? = null,
    var subtitle: TextParameters? = null,
    var caption: TextParameters? = null,
) : SelfInvocationContext, WithBackground, WithTitle {
    fun subtitle(block: TextParameters.() -> Unit) {
        subtitle = TextParameters().apply(block)
    }

    fun subtitle(parameters: TextParameters) {
        subtitle = parameters
    }

    fun caption(block: TextParameters.() -> Unit) {
        caption = TextParameters().apply(block)
    }

    fun caption(parameters: TextParameters) {
        caption = parameters
    }
}

@PlotDslMarker
data class Strip internal constructor(
    override var background: BackgroundParameters? = null,
    override var text: TextParameters? = null
) : SelfInvocationContext, WithText, WithBackground

@PlotDslMarker
data class CustomTheme @PublishedApi internal constructor(
    val global: Global = Global(),
    val axis: Axis = Axis(),
    val xAxis: Axis = Axis(),
    val yAxis: Axis = Axis(),
    val legend: Legend = Legend(),
    val panel: Panel = Panel(),
    val plotCanvas: Plot = Plot(),
    val strip: Strip = Strip(),
    val layerTooltips: LayerTooltips = LayerTooltips()
) : Theme

inline fun theme(block: CustomTheme.() -> Unit): CustomTheme {
    return CustomTheme().apply(block)
}
