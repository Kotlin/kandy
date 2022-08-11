package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonScalablePositionalAes

val GROUP = AesName("group")
data class GroupAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name=GROUP
}

/**
 * TODO
 */
val LayerContext.group: NonScalablePositionalAes
    get() = GroupAes(this)
