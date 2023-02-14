/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalTransform
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalTransform

//@Serializable
public data class Transformation internal constructor(val name: String) : PositionalTransform, NonPositionalTransform {
    public companion object {
        public val IDENTITY: Transformation = Transformation("identity")
        public val LOG10: Transformation = Transformation("log10")
        public val REVERSE: Transformation = Transformation("reverse")
        public val SQRT: Transformation = Transformation("sqrt")
    }
}
