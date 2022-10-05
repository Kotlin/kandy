package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonScalablePositionalAes

const val MERGED_GROUPS = "&merged_groups"

val GROUP = AesName("group")
data class GroupAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name=GROUP
}

/**
 * TODO
 */
val LayerContext.splitBy: NonScalablePositionalAes
    get() = GroupAes(this)

