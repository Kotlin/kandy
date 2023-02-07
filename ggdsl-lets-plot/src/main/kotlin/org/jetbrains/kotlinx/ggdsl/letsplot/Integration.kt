/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot

import jetbrains.datalore.plot.config.Option
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.model.PlotBunch
import org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.model.PlotGrid
import org.jetbrains.kotlinx.ggdsl.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.ggdsl.letsplot.translator.wrap
import org.jetbrains.kotlinx.ggdsl.util.serialization.serializeSpec
import org.jetbrains.kotlinx.jupyter.api.HTML
import org.jetbrains.kotlinx.jupyter.api.MimeTypedResultEx
import org.jetbrains.kotlinx.jupyter.api.annotations.JupyterLibrary
import org.jetbrains.kotlinx.jupyter.api.libraries.ColorScheme
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration
import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.GGBunch
import org.jetbrains.letsPlot.LetsPlot
import org.jetbrains.letsPlot.frontend.NotebookFrontendContext
import org.jetbrains.letsPlot.intern.OptionsMap
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.themes.flavorDarcula

@JupyterLibrary
internal class Integration : JupyterIntegration() {

    lateinit var frontendContext: NotebookFrontendContext


    override fun Builder.onLoaded() {

        onLoaded {
            frontendContext = LetsPlot.setupNotebook("2.5.0", null) {
                display(HTML(it), null)
            }
            LetsPlot.apiVersion = "4.1.0"
            display(HTML(frontendContext.getConfigureHtml()), null)
        }

        import("org.jetbrains.kotlinx.ggdsl.letsplot.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.export.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.facet.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.layers.*")
        //import("org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.series.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.translator.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.scales.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.stat.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.stat.layers.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.theme.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.position.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.util.font.*")
       // import("org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.*")

        fun org.jetbrains.letsPlot.intern.Plot.applyColorScheme(): org.jetbrains.letsPlot.intern.Plot {
            // todo check auto applying
            if (features.any {(it is OptionsMap) && (it.kind == Option.Plot.THEME)}) {
                return this
            }
            return if (notebook.currentColorScheme == ColorScheme.DARK) {
                this + flavorDarcula()
            } else {
                this
            }
        }

        render<Plot> { it.toLetsPlot().applyColorScheme().toMimeResult() }
        render<PlotBunch> { it.wrap().toMimeResult() }
        render<PlotGrid> { it.wrap().toMimeResult() }
    }

    internal fun Figure.toHTML(): String {
        return when(this) {
            is org.jetbrains.letsPlot.intern.Plot -> frontendContext.getHtml(this)
            is GGBunch -> frontendContext.getHtml(this)
            else -> error("Unsupported Figure")
        }
    }

    internal fun Figure.toMimeResult(): MimeTypedResultEx {
        val spec = toSpec()
        val html = toHTML()
        return MimeTypedResultEx(
            buildJsonObject {
                put("text/html", JsonPrimitive(html))
                put("application/plot", buildJsonObject {
                    put("output_type", JsonPrimitive("lets_plot_spec"))
                    put("output", serializeSpec(spec))
                })
            }
        )
    }

}
