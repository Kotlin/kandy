package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.FATTEN

public interface WithFatten : BindingContext {
    public var fatten: Double?
        get() = null
        set(value) {
            addNonPositionalSetting(FATTEN, value)
        }
}