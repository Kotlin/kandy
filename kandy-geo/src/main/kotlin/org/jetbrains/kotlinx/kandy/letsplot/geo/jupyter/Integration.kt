package org.jetbrains.kotlinx.kandy.letsplot.geo.jupyter

import org.jetbrains.kotlinx.jupyter.api.annotations.JupyterLibrary
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration

@JupyterLibrary
internal class IntegrationGeo: JupyterIntegration() {
    override fun Builder.onLoaded() {
        import("org.jetbrains.kotlinx.kandy.letsplot.geo.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.*")
        import("org.jetbrains.kotlinx.kandy.letsplot.geo.layers.*")
    }
}
