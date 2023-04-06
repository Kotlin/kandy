/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.theme

/**
 * Plot theme.
 */
public sealed interface Theme {
    //@Serializable
    public object Grey : Theme
    //@Serializable
    public object Light : Theme
    //@Serializable
    public object Classic : Theme
    //@Serializable
    public object Minimal : Theme
    //@Serializable
    public object Minimal2 : Theme
    //@Serializable
    public object None : Theme
}
