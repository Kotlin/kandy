/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.bindings

import org.jetbrains.kotlinx.kandy.ir.aes.AesName

/**
 * Mapping parameters with mapping itself.
 *
 * @property aes the aesthetic attribute name.
 */
public sealed interface FreeScale {
    public val aes: AesName
    public val parameters: MappingParameters
}

/**
 * [FreeScale] for positional aes.
 */
public data class PositionalFreeScale<DomainType>(
    override val aes: AesName,
    override val parameters: PositionalMappingParameters<DomainType>
) : FreeScale
