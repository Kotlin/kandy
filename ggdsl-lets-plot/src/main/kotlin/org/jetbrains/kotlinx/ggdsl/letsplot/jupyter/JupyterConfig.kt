package org.jetbrains.kotlinx.ggdsl.letsplot.jupyter

import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext

public class JupyterConfig: SelfInvocationContext {
    public var applyColorScheme: Boolean = true
    public var swingEnabled: Boolean = true
}
