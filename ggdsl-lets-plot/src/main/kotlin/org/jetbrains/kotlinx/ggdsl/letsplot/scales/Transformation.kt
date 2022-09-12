/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales

import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalTransform
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalTransform

data class Transformation internal constructor(val name: String) : PositionalTransform, NonPositionalTransform {
    companion object {
        val IDENTITY = Transformation("identity")
        val LOG10 = Transformation("log10")
        val REVERSE = Transformation("reverse")
        val SQRT = Transformation("sqrt")
    }
}
