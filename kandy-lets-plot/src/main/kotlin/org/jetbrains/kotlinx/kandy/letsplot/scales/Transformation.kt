/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.scales

import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalTransform
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalTransform

//@Serializable
public enum class Transformation(public val description: String) : PositionalTransform, NonPositionalTransform {
    IDENTITY("identity"),
    LOG10("log10"),
    REVERSE("reverse"),
    SQRT("sqrt");
}
