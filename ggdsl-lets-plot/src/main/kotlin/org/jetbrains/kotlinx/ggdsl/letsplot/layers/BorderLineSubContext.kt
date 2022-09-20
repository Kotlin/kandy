/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.ColorAes
import org.jetbrains.kotlinx.ggdsl.letsplot.LineTypeAes
import org.jetbrains.kotlinx.ggdsl.letsplot.SizeAes

public abstract class SubContext(public val parentContext: BindingContext) : BindingContext()

@PlotDslMarker
public class BorderLineSubContext(parentContext: BindingContext) : SubContext(parentContext) {
    override var data: MutableNamedData = mutableMapOf()
    public val color: ColorAes = ColorAes(parentContext)
    public val type: LineTypeAes = LineTypeAes(parentContext)
    public val width: SizeAes = SizeAes(parentContext)
}

public abstract class WithBorderLineContext : LayerContext() {
    public val borderLine: BorderLineSubContext = BorderLineSubContext(this)

    public inline operator fun BorderLineSubContext.invoke(block: BorderLineSubContext.() -> Unit) {
        apply(block)
    }
}
