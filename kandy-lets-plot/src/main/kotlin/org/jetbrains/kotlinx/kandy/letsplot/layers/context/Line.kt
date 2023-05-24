/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*

public class LineContext(parent: LayerCollectorContext) : LayerContext(parent), WithX, WithY, WithAlpha, WithColor,
    WithWidthAsSize, WithType {
    override val requiredAes: Set<AesName> = setOf(X, Y)
    }

/*
public interface LineContextInterface: BindingContext {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val type: LineTypeAes get() = LineTypeAes(this)
    public val width: SizeAes get() = SizeAes(this)
}

*/
/*@PlotDslMarker*//*

public class LineContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerContextImmutable(parent), LineContextInterface

*/
/*@PlotDslMarker*//*

public class LineContextMutable(parent: LayerCollectorContextMutable):
    LayerContextMutable(parent), LineContextInterface*/
