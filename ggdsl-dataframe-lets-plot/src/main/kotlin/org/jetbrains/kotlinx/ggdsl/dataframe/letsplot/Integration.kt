package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot

import org.jetbrains.kotlinx.jupyter.api.annotations.JupyterLibrary
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration

class A()

@JupyterLibrary
internal class Integration : JupyterIntegration() {

    override fun Builder.onLoaded() {
        import("org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.*")
        import("org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers.*")
        println("df-lp")

        render<A> {
            println(12)
            12
        }
    }

}

