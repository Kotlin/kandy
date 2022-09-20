/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat

sealed interface SmoothMethod {
    val name: String
    data class Linear(val degree: Int = 1): SmoothMethod{
        override val name: String = "lm"
    }
    data class Loess(
        val span: Double = 0.5,
        val maxNumber: Int = 1000,
        val seed: Long? = null
    ) : SmoothMethod {
        override val name: String = "loess"
    }
}