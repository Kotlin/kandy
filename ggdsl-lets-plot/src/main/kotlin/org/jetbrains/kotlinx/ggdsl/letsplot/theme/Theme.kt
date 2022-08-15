package org.jetbrains.kotlinx.ggdsl.letsplot.theme

sealed interface Theme {
    object Grey: Theme
    object Light: Theme
    object Classic: Theme
    object Minimal: Theme
    object Minimal2: Theme
    object None: Theme
}
