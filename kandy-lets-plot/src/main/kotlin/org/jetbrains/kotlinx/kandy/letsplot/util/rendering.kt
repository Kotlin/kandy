package org.jetbrains.kotlinx.kandy.letsplot.util

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import org.jetbrains.kotlinx.jupyter.api.MimeTypedResultEx
import org.jetbrains.kotlinx.jupyter.api.MimeTypes
import org.jetbrains.kotlinx.kandy.letsplot.jupyter.JupyterConfig
import org.jetbrains.kotlinx.kandy.util.serialization.serializeSpec
import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.GGBunch
import org.jetbrains.letsPlot.awt.plot.PlotSvgExport
import org.jetbrains.letsPlot.frontend.NotebookFrontendContext
import org.jetbrains.letsPlot.intern.figure.SubPlotsFigure
import org.jetbrains.letsPlot.intern.toSpec
import java.util.*

internal class NotebookRenderingContext(
    val frontendContext: NotebookFrontendContext,
    val config: JupyterConfig,
)

internal fun NotebookRenderingContext.figureToHtml(figure: Figure): String {
    return when (figure) {
        is org.jetbrains.letsPlot.intern.Plot -> frontendContext.getHtml(figure)
        is SubPlotsFigure -> frontendContext.getHtml(figure)
        is GGBunch -> frontendContext.getHtml(figure)
        else -> error("Unsupported Figure")
    }
}

internal fun NotebookRenderingContext.figureToMimeJson(figure: Figure): JsonObject {
    val spec = figure.toSpec()
    val html = figureToHtml(figure)
    return buildJsonObject {
        put(MimeTypes.HTML, JsonPrimitive(html))
        put("application/plot+json", buildJsonObject {
            put("output_type", JsonPrimitive("lets_plot_spec"))
            put("output", serializeSpec(spec))
            put("apply_color_scheme", JsonPrimitive(config.applyColorScheme))
            put("swing_enabled", JsonPrimitive(config.swingEnabled))
        })
    }
}

internal fun NotebookRenderingContext.figureToMimeResult(figure: Figure): MimeTypedResultEx {
    val basicResult = figureToMimeJson(figure)

    val plotSVG = PlotSvgExport.buildSvgImageFromRawSpecs(figure.toSpec())
    val id = UUID.randomUUID().toString()
    val svgWithID = with(plotSVG) {
        take(4) + " id=$id" + drop(4)
    }
    val extraHTML = """
        
        $svgWithID
        <script>document.getElementById("$id").style.display = "none";</script>
    """.trimIndent()

    val extraResult = mapOf(MimeTypes.HTML to JsonPrimitive(extraHTML))
    return MimeTypedResultEx(basicResult extendedByJson extraResult)
}
