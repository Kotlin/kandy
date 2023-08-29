/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.jupyter

import org.jetbrains.letsPlot.core.util.PlotHtmlHelper
import org.jetbrains.kotlinx.jupyter.api.HTML
import org.jetbrains.kotlinx.jupyter.api.Notebook
import org.jetbrains.kotlinx.jupyter.api.annotations.JupyterLibrary
import org.jetbrains.kotlinx.jupyter.api.declare
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration
import org.jetbrains.kotlinx.jupyter.api.libraries.resources
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotGrid
import org.jetbrains.kotlinx.kandy.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.kandy.letsplot.translator.wrap
import org.jetbrains.kotlinx.kandy.letsplot.util.NotebookRenderingContext
import org.jetbrains.kotlinx.kandy.letsplot.util.figureToMimeResult
import org.jetbrains.letsPlot.LetsPlot
import org.jetbrains.letsPlot.frontend.NotebookFrontendContext

@JupyterLibrary
internal class Integration(
    private val notebook: Notebook,
    private val options: MutableMap<String, String?>,
) : JupyterIntegration() {
    private val jsVersion = "3.1.0"

    private val frontendContext: NotebookFrontendContext = LetsPlot.setupNotebook(jsVersion, true) {
        HTML(it)
    }
    private val config = JupyterConfig()

    override fun Builder.onLoaded() {

        resources {
            js("kandyLetsPlot") {
                url(PlotHtmlHelper.scriptUrl(jsVersion))
            }
        }

        onLoaded {
            display(frontendContext.getConfigureHtml(), null)
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

        onLoaded {
            declare("kandyConfig" to config)
        }

        with(NotebookRenderingContext(frontendContext, config)) {
            render<Plot> { figureToMimeResult(it.toLetsPlot()) }
            render<PlotBunch> { figureToMimeResult(it.wrap()) }
            render<PlotGrid> { figureToMimeResult(it.wrap()) }
        }
    }
}
