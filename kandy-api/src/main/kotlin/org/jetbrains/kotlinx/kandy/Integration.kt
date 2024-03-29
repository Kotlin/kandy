/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy

import org.jetbrains.kotlinx.jupyter.api.annotations.JupyterLibrary
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration

@JupyterLibrary
internal class Integration : JupyterIntegration() {

    override fun Builder.onLoaded() {
        import("org.jetbrains.kotlinx.kandy.ir.scale.Scale")
        import("org.jetbrains.kotlinx.kandy.dsl.*")
        import("org.jetbrains.kotlinx.kandy.util.color.*")
        import("org.jetbrains.kotlinx.kandy.util.context.*")
    }

}
