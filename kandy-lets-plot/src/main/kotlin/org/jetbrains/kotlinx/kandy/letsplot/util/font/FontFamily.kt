/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.util.font

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.letsplot.util.SimpleValueWrapper

/**
 * Font family.
 */
//@Serializable
public data class FontFamily internal constructor(override val value: String) : SimpleValueWrapper {
    public companion object {
        public val SANS: FontFamily = FontFamily("sans")
        public val SERIF: FontFamily = FontFamily("serif")
        public val MONO: FontFamily = FontFamily("mono")

        public fun custom(name: String): FontFamily = FontFamily(name)
    }
}
