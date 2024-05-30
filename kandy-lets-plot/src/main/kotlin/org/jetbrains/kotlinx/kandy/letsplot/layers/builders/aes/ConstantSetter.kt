/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting

// TODO docs
@Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
public class ConstantSetter internal constructor(private val aes: Aes, private val bindingCollector: BindingCollector) {
    public fun <T> constant(value: T) {
        bindingCollector.settings[aes] = PositionalSetting<T>(aes, value)
    }
}
