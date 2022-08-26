package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot

import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration


//@JupyterLibrary
internal class Integration : JupyterIntegration() {

    override fun Builder.onLoaded() {
        import("org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.*")
        import("org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers.*")
        println("df-lp")
    }

}

