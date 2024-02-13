package org.jetbrains.kotlinx.kandy.letsplot.util

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import org.jetbrains.kotlinx.jupyter.api.MimeTypedResultEx
import org.jetbrains.kotlinx.jupyter.api.MimeTypes
import org.jetbrains.kotlinx.kandy.letsplot.internal.letsPlotJSUrl
import org.jetbrains.kotlinx.kandy.letsplot.jupyter.JupyterConfig
import org.jetbrains.kotlinx.kandy.util.serialization.serializeSpec
import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.awt.plot.PlotSvgExport
import org.jetbrains.letsPlot.core.util.PlotHtmlExport
import org.jetbrains.letsPlot.core.util.PlotHtmlHelper
import org.jetbrains.letsPlot.intern.toSpec
import java.util.*

internal class NotebookRenderingContext(
    val config: JupyterConfig
)

internal fun NotebookRenderingContext.figureToHtml(figure: Figure): String {
    val spec = figure.toSpec()
    return PlotHtmlExport.buildHtmlFromRawSpecs(spec, letsPlotJSUrl, true)
}

internal fun NotebookRenderingContext.figureToMimeJson(figure: Figure): JsonObject {
    val spec = figure.toSpec()
    val html = figureToHtml(figure)
    return buildJsonObject {
        put(MimeTypes.HTML, JsonPrimitive(html))
        put("application/plot+json", buildJsonObject {
            put("output_type", JsonPrimitive("lets_plot_spec"))
            put("output", serializeSpec(spec))
            put("apply_color_scheme", JsonPrimitive(config.themeApplied))
            put("swing_enabled", JsonPrimitive(config.swingEnabled))
        })
    }
}

internal fun updateSvg(svgString: String, id: String): String {
    val regex = Regex("""width=["']([^"']*)["']\s*height=["']([^"']*)["']""")

    return regex.replace(svgString) {
        val currentWidth = it.groupValues[1]
        val currentHeight = it.groupValues[2]
        """id=$id width="100%" height="100%" style="max-width: ${currentWidth}px; max-height: ${currentHeight}px;" viewBox="0 0 $currentWidth $currentHeight" preserveAspectRatio="xMinYMin meet""""
    }
}

internal fun NotebookRenderingContext.figureToMimeResult(figure: Figure): MimeTypedResultEx {
    val basicResult = figureToMimeJson(figure)

    val plotSVG = PlotSvgExport.buildSvgImageFromRawSpecs(figure.toSpec())
    val id = UUID.randomUUID().toString()
    val svgWithID = with(plotSVG) {
        val splitted = split('\n')
        (listOf(updateSvg(splitted.first(), id)) + splitted.drop(1)).joinToString("\n")
    }
    val extraHTML = """
        $svgWithID
        <script>document.getElementById("$id").style.display = "none";</script>
    """.trimIndent()

    val extraResult = mapOf(MimeTypes.HTML to JsonPrimitive(extraHTML))
    return MimeTypedResultEx(basicResult extendedByJson extraResult)
}
