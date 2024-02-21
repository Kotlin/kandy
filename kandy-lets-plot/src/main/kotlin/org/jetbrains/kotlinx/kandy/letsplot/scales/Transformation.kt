/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.scales

import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalTransform
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalTransform

/**
 * Lets-Plot scale transformation (applicable to both positional and non-positional).
 */
public enum class Transformation(public val description: String) : PositionalTransform, NonPositionalTransform {
    IDENTITY("identity"),
    LOG10("log10"),
    REVERSE("reverse"),
    SQRT("sqrt"),
    SYMLOG("symlog"),
    LOG2("log2");
}
