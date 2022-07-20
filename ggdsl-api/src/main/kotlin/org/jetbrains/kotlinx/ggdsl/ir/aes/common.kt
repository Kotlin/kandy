package org.jetbrains.kotlinx.ggdsl.ir.aes

import org.jetbrains.kotlinx.ggdsl.dsl.BaseBindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext

data class XAes(override val context: BindingContext): ScalablePositionalAes {
    override val name: String = "x"
}

data class YAes(override val context: BindingContext): ScalablePositionalAes {
    override val name: String = "y"
}