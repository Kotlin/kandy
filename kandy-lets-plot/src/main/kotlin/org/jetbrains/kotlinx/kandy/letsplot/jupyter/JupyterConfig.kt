/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.jupyter

import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

public class JupyterConfig : SelfInvocationContext {
    /**
     * Is IDEA theme applied to plots in the notebook outputs rendered with Swing.
     *
     * Enabled by default.
     */
    public var themeApplied: Boolean = true

    /**
     * Is Java Swing applied to plots in the notebook outputs.
     * Otherwise, web (HTML + JS) rendering is used.
     *
     * Enabled by default.
     */
    public var swingEnabled: Boolean = true
}
