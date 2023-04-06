/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.jupyter

import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

public class JupyterConfig: SelfInvocationContext {
    public var applyColorScheme: Boolean = true
    public var swingEnabled: Boolean = true
}
