/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.style

/**
 * Plot stlye.
 */
public sealed interface Style {
    public object Grey : Style
    public object Light : Style
    public object Classic : Style
    public object Minimal : Style
    public object Minimal2 : Style
    public object None : Style
}
