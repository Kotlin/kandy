/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonScalablePositionalAes

public const val MERGED_GROUPS: String = "&merged_groups"

public val GROUP: AesName = AesName("group")
public data class GroupAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: AesName =GROUP

}

/**
 * TODO
 */
public val LayerContext.splitBy: NonScalablePositionalAes
    get() = GroupAes(this)

