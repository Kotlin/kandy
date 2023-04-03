/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.bindings

//import org.jetbrains.kotlinx.kandy.ir.data.KTypeSerializer
import org.jetbrains.kotlinx.kandy.ir.aes.AesName

/**
 * Mapping base interface
 *
 * @property aes the aesthetic attribute to be mapped to.
 */
public sealed interface FreeScale {
    public val aes: AesName
    public val parameters: MappingParameters
}
/*
public data class NonScalablePositionalMapping<DomainType>(
    override val aes: AesName,
    override val columnID: String,
) : Mapping {
    override val mappingParameters: MappingParameters? = null
}

/**
 * Non-positional mapping without an individual scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property source the source to which the mapping is applied
 * @property domainType the type of domain
 */
public data class NonScalableNonPositionalMapping<DomainType>(
    override val aes: AesName,
    override val columnID: String,
) : Mapping {
    override val mappingParameters: MappingParameters? = null
}

/**
 * Mapping interface with an individual scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property columnScaled the scaled source to which the mapping is applied
 * @property domainType the type of domain
 */
public sealed interface ScaledMapping<DomainType> : Mapping {
    override val mappingParameters: MappingParameters
}

 */

public data class PositionalFreeScale<DomainType>(
    override val aes: AesName,
    override val parameters: PositionalMappingParameters<DomainType>
) : FreeScale
/*
/**
 * Non-positional mapping without an individual scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property source the source to which the mapping is applied
 * @property domainType the type of domain
 */
public data class NonPositionalMapping<DomainType, RangeType>(
    override val aes: AesName,
    override val columnID: String,
    override val parameters: NonPositionalMappingParameters<DomainType, RangeType>?
) : Mapping

 */