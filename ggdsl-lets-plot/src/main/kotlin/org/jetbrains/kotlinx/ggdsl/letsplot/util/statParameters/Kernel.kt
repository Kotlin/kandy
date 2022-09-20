/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters

// todo enum???
data class Kernel internal constructor(override val value: String): SimpleValueWrapper {
    companion object {
        val GAUSSIAN = Kernel("gaussian")
        val COSINE = Kernel("cosine")
        val OPT_COSINE = Kernel("optcosine")
        val RECTANGULAR = Kernel("rectangular")
        val UNIFORM = Kernel("uniform")
        val TRIANGULAR = Kernel("triangular")
        val BI_WEIGHT = Kernel("biweight")
        val QUARTIC = Kernel("quartic")
        val EPANECHIKOV = Kernel("epanechikov")
        val PARABOLIC = Kernel("parabolic")
    }
}
