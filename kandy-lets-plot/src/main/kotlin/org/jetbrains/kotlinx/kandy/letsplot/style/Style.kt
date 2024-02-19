/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.style

/**
 * Plot style.
 */
public sealed interface Style {
    public data object Grey : Style
    public data object Light : Style
    public data object Classic : Style
    public data object Minimal : Style
    public data object Minimal2 : Style
    public data object None : Style
    public data object BW: Style
    public data object Void: Style

    public companion object {
        public inline fun createCustom(block: CustomStyle.() -> Unit): CustomStyle {
            return CustomStyle().apply(block)
        }
    }
}
