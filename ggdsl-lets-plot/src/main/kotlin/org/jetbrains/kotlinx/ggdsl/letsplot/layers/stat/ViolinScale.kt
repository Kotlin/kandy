/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat

public enum class ViolinScale {
    AREA, COUNT, WIDTH;

    override fun toString(): String = super.toString().lowercase()
}