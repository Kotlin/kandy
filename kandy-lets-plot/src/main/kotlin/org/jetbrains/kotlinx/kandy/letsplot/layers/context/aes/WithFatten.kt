/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

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