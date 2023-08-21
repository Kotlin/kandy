/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.POINT_RANGE
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext


public class InnerPointContext internal constructor(override val parentContext: BindingContext) :
    SelfInvocationContext, SubBindingContext, WithSymbol, WithFillColor, WithFatten

public class InnerLineContext internal constructor(override val parentContext: BindingContext) :
    SelfInvocationContext, SubBindingContext, WithType

public interface PointRangeInterface: LayerContextInterface , WithX, WithY, WithAlpha,
    WithColor, WithYMin, WithYMax, WithSize {
    public val innerPoint: InnerPointContext
    public val innerLine: InnerLineContext
    override val geom: Geom
        get() = POINT_RANGE
    override val requiredAes: Set<AesName>
        get() = setOf(X, Y, Y_MIN, Y_MAX)
}


public open class PointRangeContext(parent: LayerCollectorContext) : LayerContext(parent), PointRangeInterface {
    // todo fix
    public override val innerPoint: InnerPointContext = InnerPointContext(this)
    public override val innerLine: InnerLineContext = InnerLineContext(this)
}
