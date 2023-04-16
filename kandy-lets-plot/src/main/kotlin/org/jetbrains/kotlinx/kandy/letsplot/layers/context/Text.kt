/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.internal.SubBindingContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext


public class FontContext internal constructor(override val parentContext: BindingContext) :
    SelfInvocationContext, SubBindingContext, WithColor, WithSize, WithFace, WithFamily

public class TextContext(parent: LayerCollectorContext) : LayerContext(parent), WithX, WithY, WithAlpha, WithLabel {
    public val font: FontContext = FontContext(this)
}

/*
import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

public interface FontSubContextInterface : SelfInvocationContext {
    public val parentContext: BindingContext
    public val color: ColorAes get() = ColorAes(parentContext)
    public val size: SizeAes get() = SizeAes(parentContext)
    public val family: FontFamilyAes get() = FontFamilyAes(parentContext)
    public val face: FontFaceAes get() = FontFaceAes(parentContext)
}

public class FontSubContextImmutable(override val parentContext: BindingContext) : FontSubContextInterface
public class FontSubContextMutable(override val parentContext: TableBindingContextInterfaceMutable) :
    FontSubContextInterface, TableSubContextMutable(parentContext, false, false)

public interface TextContextInterface : BindingContext {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)
    public val label: LabelAes get() = LabelAes(this)

    public val alpha: AlphaAes get() = AlphaAes(this)
    public val angle: AngleAes get() = AngleAes(this)
    public val format: FormatAes get() = FormatAes(this)
    public val horizontalJustification: HorizontalJustificationAes get() = HorizontalJustificationAes(this)
    public val verticalJustification: VerticalJustificationAes get() = VerticalJustificationAes(this)

    public val font: FontSubContextInterface
}

*/
/*@PlotDslMarker*//*

public class TextContextImmutable(parent: LayerCollectorContextImmutable) : LayerContextImmutable(parent),
    TextContextInterface {
    override val font: FontSubContextImmutable = FontSubContextImmutable(this)
}

*/
/*@PlotDslMarker*//*

public class TextContextMutable(parent: LayerCollectorContextMutable) :
    LayerContextMutable(parent), TextContextInterface {
    override val font: FontSubContextMutable = FontSubContextMutable(this)
}*/
