/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.model.PlotBunch
import org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.model.PlotGrid
import org.jetbrains.kotlinx.ggdsl.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.ggdsl.letsplot.translator.wrap
import org.jetbrains.kotlinx.jupyter.api.HTML
import org.jetbrains.kotlinx.jupyter.api.annotations.JupyterLibrary
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration
import org.jetbrains.letsPlot.LetsPlot
import org.jetbrains.letsPlot.frontend.NotebookFrontendContext

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

        render<Plot> { HTML(frontendContext.getHtml(it.toLetsPlot())) }
        render<PlotBunch> { HTML(frontendContext.getHtml(it.wrap())) }
        render<PlotGrid> { HTML(frontendContext.getHtml(it.wrap())) }
    }

}


