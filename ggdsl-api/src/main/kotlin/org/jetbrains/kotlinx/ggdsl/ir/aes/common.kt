package org.jetbrains.kotlinx.ggdsl.ir.aes

import org.jetbrains.kotlinx.ggdsl.dsl.BaseBindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext

val X = AesName("x")
data class XAes(override val context: BindingContext): ScalablePositionalAes {
    override val name: AesName = X
}

val Y = AesName("y")
data class YAes(override val context: BindingContext): ScalablePositionalAes {
    override val name: AesName = Y
}