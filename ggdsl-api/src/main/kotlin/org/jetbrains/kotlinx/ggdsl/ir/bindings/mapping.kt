/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir.bindings

import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.scale.ScaleParameters
import kotlin.reflect.KType

/**
 * Mapping base interface
 *
 * @property aes the aesthetic attribute to be mapped to.
 */
public sealed interface Mapping {
    public val aes: AesName
}

/**
 * Mapping with an implicit scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property source the source to which the mapping is applied
 * @property domainType the type of domain
 */
public data class NonScalablePositionalMapping<DomainType : Any>(
    override val aes: AesName,
    val source: ColumnPointer<DomainType>,
    val domainType: KType
) : Mapping

/**
 * Mapping interface with an explicit scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property columnScaled the scaled source to which the mapping is applied
 * @property domainType the type of domain
 */
public sealed interface ScaledMapping<DomainType : Any> : Mapping {
    override val aes: AesName
    public val columnScaled: ColumnScaled<DomainType>
    public val domainType: KType
    public var scaleParameters: ScaleParameters?
}

public sealed interface BaseScaledPositionalMapping<DomainType : Any> : ScaledMapping<DomainType> {
    override val aes: AesName
    override val columnScaled: ColumnScaled<DomainType>
    override val domainType: KType
    override var scaleParameters: ScaleParameters?
}

public sealed interface BaseScaledNonPositionalMapping<DomainType : Any, RangeType : Any> : ScaledMapping<DomainType> {
    override val aes: AesName
    override val columnScaled: ColumnScaled<DomainType>
    override val domainType: KType
    override var scaleParameters: ScaleParameters?
}

/**
 * Mapping with an unspecified default scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property columnScaled the scaled default unspecified source to which the mapping is applied
 * @property domainType type of domain
 */
public sealed interface ScaledUnspecifiedDefaultMapping<DomainType : Any> : ScaledMapping<DomainType> {
    override val aes: AesName
    override val columnScaled: ColumnScaledUnspecifiedDefault<DomainType>
    override val domainType: KType
    override var scaleParameters: ScaleParameters?
}

public data class ScaledUnspecifiedDefaultPositionalMapping<DomainType : Any>(
    override val aes: AesName,
    override val columnScaled: ColumnScaledUnspecifiedDefault<DomainType>,
    override val domainType: KType
) : BaseScaledPositionalMapping<DomainType>, ScaledUnspecifiedDefaultMapping<DomainType> {
    override var scaleParameters: ScaleParameters? = null
}

public data class ScaledUnspecifiedDefaultNonPositionalMapping<DomainType : Any, RangeType : Any>(
    override val aes: AesName,
    override val columnScaled: ColumnScaledUnspecifiedDefault<DomainType>,
    override val domainType: KType
) : BaseScaledNonPositionalMapping<DomainType, RangeType>, ScaledUnspecifiedDefaultMapping<DomainType> {
    override var scaleParameters: ScaleParameters? = null
}

/**
 * Mapping with a positional default scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property columnScaled the scaled default positional source to which the mapping is applied
 * @property domainType type of domain
 */
public data class ScaledPositionalUnspecifiedMapping<DomainType : Any>(
    override val aes: AesName,
    override val columnScaled: ColumnScaledPositionalUnspecified<DomainType>,
    override val domainType: KType
) : BaseScaledPositionalMapping<DomainType> {
    override var scaleParameters: ScaleParameters? = null
}

/**
 * Mapping with a non-positional default scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property columnScaled the scaled default non-positional source to which the mapping is applied
 * @property domainType type of domain
 */
public data class ScaledNonPositionalDefaultMapping<DomainType : Any, RangeType : Any>(
    override val aes: AesName,
    override val columnScaled: ColumnScaledNonPositionalUnspecified<DomainType>,
    override val domainType: KType
) : BaseScaledNonPositionalMapping<DomainType, RangeType> {
    override var scaleParameters: ScaleParameters? = null
}

/**
 * Mapping with a positional scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property columnScaled the scaled positional source to which the mapping is applied
 * @property domainType type of domain
 */
public data class ScaledPositionalMapping<DomainType : Any>(
    override val aes: AesName,
    override val columnScaled: ColumnScaledPositional<DomainType>,
    override val domainType: KType
) : BaseScaledPositionalMapping<DomainType> {
    override var scaleParameters: ScaleParameters? = null
}


/**
 * Mapping with a non-positional scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property columnScaled the scaled non-positional source to which the mapping is applied
 * @property domainType type of domain
 */
public data class ScaledNonPositionalMapping<DomainType : Any, RangeType : Any>(
    override val aes: AesName,
    override val columnScaled: ColumnScaledNonPositional<DomainType, RangeType>,
    override val domainType: KType,
) : BaseScaledNonPositionalMapping<DomainType, RangeType> {
    override var scaleParameters: ScaleParameters? = null
}
