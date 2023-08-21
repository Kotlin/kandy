/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.internal.SubBindingContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithColor
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithType
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithWidthAsSize
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

public class BorderLineContext internal constructor(override val parentContext: BindingContext) :
    SelfInvocationContext, SubBindingContext, WithColor, WithType, WithWidthAsSize

public interface WithBorderLineContext {
    public val borderLine: BorderLineContext
}

public abstract class LayerWithBorderLineContext(parent: LayerCollectorContext) : LayerContext(parent), WithBorderLineContext {
    // todo fix
    public override val borderLine: BorderLineContext = BorderLineContext(this)
}
