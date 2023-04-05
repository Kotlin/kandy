/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.util.font

import kotlinx.serialization.Serializable

/**
 * Font face.
 */
//@Serializable
public enum class FontFace {
    PLAIN, ITALIC, BOLD, BOLD_ITALIC;

    override fun toString(): String = super.toString().lowercase()
}