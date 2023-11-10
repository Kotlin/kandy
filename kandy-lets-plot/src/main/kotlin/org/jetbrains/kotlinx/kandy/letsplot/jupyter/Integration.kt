/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.jupyter

import org.jetbrains.kotlinx.jupyter.api.HTML
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
import org.jetbrains.letsPlot.core.util.PlotHtmlHelper
import org.jetbrains.letsPlot.frontend.NotebookFrontendContext

@JupyterLibrary
internal class Integration : JupyterIntegration() {
     private val jsVersion = "4.0.1"

    /*private val frontendContext: NotebookFrontendContext = LetsPlot.setupNotebook(jsVersion, true) {
        HTML(it)
    }*/
    private val config = JupyterConfig()

    override fun Builder.onLoaded() {

        /*resources {
            js("kandyLetsPlot") {
                url(PlotHtmlHelper.scriptUrl(jsVersion))
            }
        }*/

        onLoaded {
            //display()
            //display(frontendContext.getConfigureHtml(), null)
        }

        import("org.jetbrains.kotlinx.kandy.letsplot.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.export.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.feature.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.layers.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.multiplot.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.translator.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.scales.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.scales.guide.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.theme.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.tooltips.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.settings.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.settings.font.*")

        onLoaded {
            declare("kandyConfig" to config)
        }

        with(NotebookRenderingContext(jsVersion, config)) {
            render<Plot> { figureToMimeResult(it.toLetsPlot()) }
            render<PlotBunch> { figureToMimeResult(it.wrap()) }
            render<PlotGrid> { figureToMimeResult(it.wrap()) }
        }
    }
}
