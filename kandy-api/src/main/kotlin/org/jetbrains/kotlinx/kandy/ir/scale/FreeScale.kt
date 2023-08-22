/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.scale

import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.MappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParameters

/**
 * Defines constraints and conditions for an axis.
 * This mechanism is applied to aesthetics.
 * It's particularly useful for aesthetic attributes that don't have their own axis scales
 * but instead inherit the scales of a parent aesthetic.
 *
 * @property aes the name of the aesthetic this scale is applied to.
 * @property parameters [parameters][MappingParameters] defining the mapping for the aesthetic.
 */
public sealed interface FreeScale {
    public val aes: Aes
    public val parameters: MappingParameters
}


/**
 * [FreeScale] for positional aesthetics.
 * This pertains to the primary coordinates of a plot,
 * which determine the position of elements on the plot.
 * The `PositionalFreeScale` provides a mechanism to modify the scales of such aesthetics.
 *
 * For example, in the context of a `boxplot`, there are typically five positional aesthetic attributes:
 * **`yMin`**, **`yMax`**, **`middle`**, **`lower`**, and **`upper`**.
 * These attributes don't have their own scales.
 * Therefore, to adjust their scales,
 * the free scale mechanism is applied to their parent aesthetic - **`y`**.
 *
 * @property aes the name of the aesthetic this scale is applied to.
 * @property parameters parameters defining the mapping for the positional aesthetic.
 */
public data class PositionalFreeScale<DomainType>(
    override val aes: Aes,
    override val parameters: PositionalMappingParameters<DomainType>
) : FreeScale
