/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.*
import org.jetbrains.kotlinx.ggdsl.letsplot.ColorAes
import org.jetbrains.kotlinx.ggdsl.letsplot.LineTypeAes
import org.jetbrains.kotlinx.ggdsl.letsplot.SizeAes
import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext


@PlotDslMarker
public interface BorderLineContextInterface: SelfInvocationContext {
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

