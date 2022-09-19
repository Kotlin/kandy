package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.Layout

public var PlotContext.layoutAccessor: Layout?
    get() = this._layout
    set(value) {
        this._layout = value
    }
