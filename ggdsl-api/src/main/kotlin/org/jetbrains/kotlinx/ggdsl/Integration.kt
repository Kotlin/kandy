package org.jetbrains.kotlinx.ggdsl

import org.jetbrains.kotlinx.jupyter.api.annotations.JupyterLibrary
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration

@JupyterLibrary
internal class Integration : JupyterIntegration() {

    override fun Builder.onLoaded() {
        import("org.jetbrains.kotlinx.ggdsl.dsl.*")
        import("org.jetbrains.kotlinx.ggdsl.ir.data.NamedData")
        import("org.jetbrains.kotlinx.ggdsl.util.color.*")
        import("org.jetbrains.kotlinx.ggdsl.util.context.*")
    }

}

