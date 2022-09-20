/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters

// todo enum???
public data class Kernel internal constructor(override val value: String) : SimpleValueWrapper {
    public companion object {
        public val GAUSSIAN: Kernel = Kernel("gaussian")
        public val COSINE: Kernel = Kernel("cosine")
        public val OPT_COSINE: Kernel = Kernel("optcosine")
        public val RECTANGULAR: Kernel = Kernel("rectangular")
        public val UNIFORM: Kernel = Kernel("uniform")
        public val TRIANGULAR: Kernel = Kernel("triangular")
        public val BI_WEIGHT: Kernel = Kernel("biweight")
        public val QUARTIC: Kernel = Kernel("quartic")
        public val EPANECHIKOV: Kernel = Kernel("epanechikov")
        public val PARABOLIC: Kernel = Kernel("parabolic")
    }
}
