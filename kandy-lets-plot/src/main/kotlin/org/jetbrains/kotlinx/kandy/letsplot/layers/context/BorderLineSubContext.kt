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

public abstract class LayerWithBorderLineContext(parent: LayerCollectorContext) : LayerContext(parent) {
    public val borderLine: BorderLineContext = BorderLineContext(this)
}

/*
import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.internal.ColorAes
import org.jetbrains.kotlinx.kandy.letsplot.internal.LineTypeAes
import org.jetbrains.kotlinx.kandy.letsplot.internal.SizeAes
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext


/*@PlotDslMarker*/
public interface BorderLineContextInterface: SelfInvocationContext {
    // todo hide
    public val parentContext: BindingContext
    public val color: ColorAes get() = ColorAes(parentContext)
    public val type: LineTypeAes get() = LineTypeAes(parentContext)
    public val width: SizeAes get() = SizeAes(parentContext)
}

public class BorderLineContextImmutable(override val parentContext: BindingContext) : BorderLineContextInterface

public class BorderLineContextMutable(override val parentContext: TableBindingContextInterfaceMutable) :
    BorderLineContextInterface, TableSubContextMutable(parentContext, false, false)

public interface WithBorderLineContextInterface: BindingContext {
  //  public val parent: LayerContextInterface
    public val borderLine: BorderLineContextInterface
}

public interface WithBorderLineContextImmutable : WithBorderLineContextInterface {
 //   public override val parent: LayerContextImmutable
    public override val borderLine: BorderLineContextImmutable
}

public abstract class LayerWithBorderLineContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerContextImmutable(parent), WithBorderLineContextImmutable {
    override val borderLine: BorderLineContextImmutable by lazy { BorderLineContextImmutable(this) }
}

public interface WithBorderLineContextMutable : WithBorderLineContextInterface {
    //   public override val parent: LayerContextMutable
    public override val borderLine: BorderLineContextMutable
}

public abstract class LayerWithBorderLineContextMutable(parent: LayerCollectorContextMutable)
    : LayerContextMutable(parent), WithBorderLineContextMutable {
    override val borderLine: BorderLineContextMutable by lazy { BorderLineContextMutable(this) }
}

 */

