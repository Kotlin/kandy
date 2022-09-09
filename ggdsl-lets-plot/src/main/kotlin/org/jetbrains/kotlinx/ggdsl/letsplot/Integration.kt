package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.PlotBunch
import org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.PlotGrid
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
            frontendContext = LetsPlot.setupNotebook("2.4.0", false) {
                display(HTML(it))
            }
            LetsPlot.apiVersion = "4.0.0"
            display(HTML(frontendContext.getConfigureHtml()))
        }

        import("org.jetbrains.kotlinx.ggdsl.letsplot.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.export.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.facet.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.layers.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.layers.label.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.multiplot.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.translator.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.scales.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.theme.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.tooltips.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.position.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.util.font.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.*")

        render<Plot> { HTML(frontendContext.getHtml(it.toLetsPlot())) }
        render<PlotBunch> { HTML(frontendContext.getHtml(it.wrap())) }
        render<PlotGrid> { HTML(frontendContext.getHtml(it.wrap())) }
    }

}


