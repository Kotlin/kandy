/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.dsl.NonScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName

public const val MERGED_GROUPS: String = "&merged_groups"

public val GROUP: AesName = AesName("group")

//TODO
public data class GroupAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: AesName = GROUP
}

/**
 * TODO

public val LayerContextInterface.splitBy: NonScalablePositionalAes
    get() = GroupAes(this)

*/