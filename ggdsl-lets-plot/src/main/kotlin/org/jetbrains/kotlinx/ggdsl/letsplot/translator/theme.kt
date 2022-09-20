package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.letsplot.theme.*
import org.jetbrains.kotlinx.ggdsl.util.color.StandardColor
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.themes.*

public fun LineParameters.wrap(): Map<String, Any> {
    return elementLine(
        (color as? StandardColor)?.description,
        width, blank
    )
}

public fun BackgroundParameters.wrap(): Map<String, Any> {
    return elementRect(
        (fillColor as? StandardColor)?.description,
        (borderLineColor as? StandardColor)?.description,
        borderLineWidth, blank
    )
}

public fun TextParameters.wrap(): Map<String, Any> {
    return elementText(
        (color as? StandardColor)?.description,
        font?.toString(),
        blank
    )
}

public fun CustomTheme.wrap(): theme {
    var buffer = theme(
        line = global.line?.wrap(),
        rect = global.background?.wrap(),
        text = global.text?.wrap(),// TODO
        title = global.title?.wrap(),// TODO
        axis = if (axis.blank == true) {
            "blank"
        } else null, //TODO
        axisOntop = axis.onTop,
        axisOntopX = xAxis.onTop,
        axisOntopY = yAxis.onTop,
        axisTitle = axis.title?.wrap(),
        axisTitleX = xAxis.title?.wrap(),
        axisTitleY = yAxis.title?.wrap(),
        axisText = axis.text?.wrap(),
        axisTextX = xAxis.text?.wrap(),
        axisTextY = yAxis.text?.wrap(),
        axisTicks = axis.ticks?.wrap(),
        axisTicksX = xAxis.ticks?.wrap(),
        axisTicksY = yAxis.ticks?.wrap(),
        axisTicksLength = axis.ticksLength,
        axisTicksLengthX = xAxis.ticksLength,
        axisTicksLengthY = yAxis.ticksLength,
        axisLine = axis.line?.wrap(),
        axisLineX = xAxis.line?.wrap(),
        axisLineY = yAxis.line?.wrap(),
        legendBackground = legend.background?.wrap(),
        legendText = legend.text?.wrap(),
        legendTitle = legend.title?.wrap(),
        panelBackground = panel.background?.wrap(),
        panelBorder = panel.borderLine?.wrap(),
        panelGrid = panel.grid.lineGlobal?.wrap(),
        panelGridMajor = panel.grid.majorLine?.wrap(),
        panelGridMajorX = panel.grid.majorXLine?.wrap(),
        panelGridMajorY = panel.grid.majorYLine?.wrap(),
        panelGridMinor = panel.grid.minorLine?.wrap(),
        panelGridMinorX = panel.grid.minorXLine?.wrap(),
        panelGridMinorY = panel.grid.minorYLine?.wrap(),
        plotBackground = plotCanvas.background?.wrap(),
        plotCaption = plotCanvas.caption?.wrap(),
        plotSubtitle = plotCanvas.subtitle?.wrap(),
        plotTitle = plotCanvas.title?.wrap(),
        stripBackground = strip.background?.wrap(),
        stripText = strip.text?.wrap(),
        axisTooltip = axis.tooltip.background?.wrap(),
        axisTooltipX = xAxis.tooltip.background?.wrap(),
        axisTooltipY = yAxis.tooltip.background?.wrap(),
        axisTooltipText = axis.tooltip.text?.wrap(),
        axisTooltipTextX = xAxis.tooltip.text?.wrap(),
        axisTooltipTextY = yAxis.tooltip.text?.wrap(),
        tooltip = layerTooltips.background?.wrap(),
        tooltipText = layerTooltips.text?.wrap(),
        tooltipTitleText = layerTooltips.title?.wrap()
        /*
              */
    )

    when (val justification = this@wrap.legend.justification) {
        LegendJustification.Center -> {
            buffer = buffer.legendJustificationCenter()
        }

        is LegendJustification.Custom -> {
            buffer = buffer.legendJustification(justification.x, justification.y)
        }

        null -> {}
    }

    when (this@wrap.legend.direction) {
        LegendDirection.HORIZONTAL -> buffer = buffer.legendDirectionHorizontal()
        LegendDirection.VERTICAL -> buffer = buffer.legendDirectionHorizontal()
        null -> {}
    }


    when (val position = this@wrap.legend.position) {
        LegendPosition.Bottom -> buffer = buffer.legendPositionBottom()
        is LegendPosition.Custom -> {
            buffer = buffer.legendPosition(position.x, position.y)
        }

        LegendPosition.Left -> buffer = buffer.legendPositionLeft()
        LegendPosition.None -> buffer = buffer.legendPositionNone()
        LegendPosition.Right -> buffer = buffer.legendPositionRight()
        LegendPosition.Top -> buffer = buffer.legendPositionTop()
        null -> {}
    }

    return buffer
}

public fun Theme.wrap(): OptionsMap {
    return when (this) {
        Theme.Grey -> themeGrey()
        Theme.Classic -> themeClassic()
        Theme.Light -> themeLight()
        Theme.Minimal -> themeMinimal()
        Theme.Minimal2 -> themeMinimal2()
        Theme.None -> themeNone()
        is CustomTheme -> this.wrap()
    }
}