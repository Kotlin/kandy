package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.FATTEN

public interface WithFatten : BindingContext {
    public var fatten: Double?
        get() = null
        set(value) {
            addNonPositionalSetting(FATTEN, value)
        }
}