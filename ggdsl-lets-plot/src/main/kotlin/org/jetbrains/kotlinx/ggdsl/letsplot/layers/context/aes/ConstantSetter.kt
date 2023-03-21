package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes

import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingCollector
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalSetting

public class ConstantSetter(private val aesName: AesName, private val bindingCollector: BindingCollector) {
    public fun<T> constant(value: T){
        bindingCollector.settings[aesName] = PositionalSetting<T>(aesName, value)
    }
}
