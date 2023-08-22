/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.LABEL
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.TEXT
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext


public class FontContext(override val parentContext: BindingContext) :
    SelfInvocationContext, SubBindingContext, WithColor, WithSize, WithFace, WithFamily

public interface TextInterface: LayerContextInterface, WithX, WithY, WithAlpha, WithLabel {
    override val geom: Geom
        get() = TEXT
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y, LABEL)
    public val font: FontContext
}

public open class TextContext(parent: LayerCollectorContext) : LayerContext(parent), TextInterface {
    // todo fix
    override val font: FontContext = FontContext(this)
}
