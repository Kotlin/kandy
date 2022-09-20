/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.theme

sealed interface Theme {
    object Grey: Theme
    object Light: Theme
    object Classic: Theme
    object Minimal: Theme
    object Minimal2: Theme
    object None: Theme
}
