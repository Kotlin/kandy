/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.util.font

import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.SimpleValueWrapper

data class FontFamily internal constructor(override val value: String): SimpleValueWrapper{
    companion object {
        val SANS = FontFamily("sans")
        val SERIF = FontFamily("serif")
        val MONO = FontFamily("mono")

        fun custom(name: String) = FontFamily(name)
    }
}
