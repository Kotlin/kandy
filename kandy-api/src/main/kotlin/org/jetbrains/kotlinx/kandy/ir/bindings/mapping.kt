/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.bindings

import org.jetbrains.kotlinx.kandy.ir.aes.AesName

/**
 * Mapping from column in the dataset to an aesthetic attribute.
 *
 * @property aes aesthetic attribute to which the mapping is performed.
 * @property columnID id of mapped column in dataset.
 * @property parameters mapping parameters, optional.
 */
public sealed interface Mapping {
    public val aes: AesName
    public val columnID: String

    public val parameters: MappingParameters?
}

/**
 * Mapping to positional aes.
 */
public data class PositionalMapping<DomainType>(
    override val aes: AesName,
    override val columnID: String,
    override val parameters: PositionalMappingParameters<DomainType>?
) : Mapping

/**
 * Mapping to non-positional aes.
 */
public data class NonPositionalMapping<DomainType, RangeType>(
    override val aes: AesName,
    override val columnID: String,
    override val parameters: NonPositionalMappingParameters<DomainType, RangeType>?
) : Mapping