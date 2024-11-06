/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.letsplot.style.*
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.themes.*

internal fun Margin.wrap(): List<Double> {
    return listOf(top, right, bottom, left)
}

internal fun LineParameters.wrap(): Map<String, Any> {
    return elementLine(
        color = color?.wrap(),
        size = width,
        linetype = lineType?.description,
        blank = blank
    )
}

internal fun BackgroundParameters.wrap(): Map<String, Any> {
    return elementRect(
        fill = fillColor?.wrap(),
        color = borderLineColor?.wrap(),
        size = borderLineWidth,
        linetype = borderLineType?.description,
        blank = blank
    )
}

internal fun TextParameters.wrap(): Map<String, Any> {
    return elementText(
        color = color?.wrap(),
        family = fontFamily?.value,
        face = fontFace?.toString(),
        size = fontSize,
        angle = angle,
        hjust = hJust,
        vjust = vJust,
        margin = margin?.wrap(),
        blank = blank
    )
}

internal fun CustomStyle.wrap(): theme {
    var buffer = theme(
        line = global.line?.wrap(),
        rect = global.background?.wrap(),
        text = global.text?.wrap(),
        title = global.title?.wrap(),
        axis = if (axis.blank == true) {
            "blank"
        } else null,
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
        plotMargin = plotCanvas.margin?.wrap(),
        plotInset = plotCanvas.inset?.wrap(),
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

internal fun Style.wrap(): Feature {
    return when (this) {
        Style.Grey -> themeGrey()
        Style.Classic -> themeClassic()
        Style.Light -> themeLight()
        Style.Minimal -> themeMinimal()
        Style.Minimal2 -> themeMinimal2()
        Style.None -> themeNone()
        Style.BW -> themeBW()
        Style.Void -> themeVoid()
        is CustomStyle -> this.wrap()
    }
}
