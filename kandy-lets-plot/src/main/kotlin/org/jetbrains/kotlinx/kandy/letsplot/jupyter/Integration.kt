/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.jupyter

import jetbrains.datalore.plot.PlotHtmlHelper
import jetbrains.datalore.plot.PlotSvgExport
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import org.jetbrains.kotlinx.jupyter.api.*
import org.jetbrains.kotlinx.jupyter.api.annotations.JupyterLibrary
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration
import org.jetbrains.kotlinx.jupyter.api.libraries.resources
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotGrid
import org.jetbrains.kotlinx.kandy.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.kandy.letsplot.translator.wrap
import org.jetbrains.kotlinx.kandy.letsplot.util.extendedByJson
import org.jetbrains.kotlinx.kandy.util.serialization.serializeSpec
import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.GGBunch
import org.jetbrains.letsPlot.LetsPlot
import org.jetbrains.letsPlot.frontend.NotebookFrontendContext
import org.jetbrains.letsPlot.intern.figure.SubPlotsFigure
import org.jetbrains.letsPlot.intern.toSpec
import java.util.*

@JupyterLibrary
internal class Integration(
    private val notebook: Notebook,
    private val options: MutableMap<String, String?>,
) : JupyterIntegration() {

    lateinit var frontendContext: NotebookFrontendContext

    private val jsVersion = "3.1.0"

    override fun Builder.onLoaded() {

        resources {
            js("kandyLetsPlot") {
                url(PlotHtmlHelper.scriptUrl(jsVersion))
            }
        }

        onLoaded {
            frontendContext = LetsPlot.setupNotebook("3.1.0", true) {
                display(HTML(it), null)
            }
            LetsPlot.apiVersion = "4.3.0"
            //display(HTML(frontendContext.getConfigureHtml()), null)
        }

        import("org.jetbrains.kotlinx.kandy.letsplot.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.export.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.facet.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.layers.*")
        //import("org.jetbrains.kotlinx.kandy.letsplot.layers.stat.*")
        //import("org.jetbrains.kotlinx.kandy.letsplot.series.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.multiplot.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.translator.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.scales.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.stat.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.stat.bin.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.stat.layers.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.scales.guide.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.theme.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.tooltips.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.position.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.util.linetype.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.util.symbol.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.util.font.*")
        // import("org.jetbrains.kotlinx.kandy.letsplot.util.statParameters.*")


        fun Figure.toHTML(): String {
            return when (this) {
                is org.jetbrains.letsPlot.intern.Plot -> frontendContext.getHtml(this)
                is SubPlotsFigure -> frontendContext.getHtml(this)
                is GGBunch -> frontendContext.getHtml(this)
                else -> error("Unsupported Figure")
            }
        }

        val config = JupyterConfig()

        onLoaded {
            declare("kandyConfig" to config)
        }

        fun Figure.toMimeJson(): JsonObject {
            val spec = toSpec()
            val html = toHTML()
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

        fun Figure.toMimeResult(): MimeTypedResultEx {
            val basicResult = toMimeJson()

            val plotSVG = PlotSvgExport.buildSvgImageFromRawSpecs(toSpec())
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

        render<Plot> { it.toLetsPlot().toMimeResult() }
        render<PlotBunch> { it.wrap().toMimeResult() }
        render<PlotGrid> { it.wrap().toMimeResult() }
    }


}
