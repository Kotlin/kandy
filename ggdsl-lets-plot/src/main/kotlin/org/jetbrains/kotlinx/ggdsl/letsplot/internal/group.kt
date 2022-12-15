/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.internal

import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName

internal const val MERGED_GROUPS: String = "&merged_groups"

internal val GROUP: AesName = AesName("group")

/*
//TODO
internal data class GroupAes(override val context: BindingContext): NonScalablePositionalAes {
    override val name: AesName = GROUP
}

 */

/**
 * TODO

public val LayerContextInterface.splitBy: NonScalablePositionalAes
    get() = GroupAes(this)

*/