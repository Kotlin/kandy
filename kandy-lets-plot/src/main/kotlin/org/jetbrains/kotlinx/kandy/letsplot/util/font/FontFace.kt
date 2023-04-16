/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.util.font

/**
 * Font face.
 */
public enum class FontFace {
    PLAIN, ITALIC, BOLD, BOLD_ITALIC;

    override fun toString(): String = super.toString().lowercase()
}