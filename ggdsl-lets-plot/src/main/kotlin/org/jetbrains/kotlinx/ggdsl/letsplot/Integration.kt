package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.jupyter.api.annotations.JupyterLibrary
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration

@JupyterLibrary
internal class Integration : JupyterIntegration() {


    override fun Builder.onLoaded() {
        import("org.jetbrains.kotlinx.ggdsl.letsplot.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.translator.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.facet.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.layers.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.scale.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.scale.guide.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.position.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.*")
        import("org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.*")

        render<Plot> { it.toLetsPlot() }
    }

}
