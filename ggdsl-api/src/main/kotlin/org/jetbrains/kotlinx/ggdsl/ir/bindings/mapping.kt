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
sealed interface Mapping {
    val aes: AesName
}

/**
 * Mapping with an implicit scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property source the source to which the mapping is applied
 * @property domainType the type of domain
 */
data class NonScalablePositionalMapping<DomainType : Any>(
    override val aes: AesName,
    val source: ColumnPointer<DomainType>,
    val domainType: KType
) : Mapping

/**
 * Mapping interface with an explicit scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property sourceScaled the scaled source to which the mapping is applied
 * @property domainType the type of domain
 */
sealed interface ScaledMapping<DomainType : Any> : Mapping {
    override val aes: AesName
    val sourceScaled: SourceScaled<DomainType>
    val domainType: KType
    var scaleParameters: ScaleParameters?
}

sealed interface BaseScaledPositionalMapping<DomainType : Any> : ScaledMapping<DomainType> {
    override val aes: AesName
    override val sourceScaled: SourceScaled<DomainType>
    override val domainType: KType
    override var scaleParameters: ScaleParameters?
}

sealed interface BaseScaledNonPositionalMapping<DomainType : Any, RangeType : Any> : ScaledMapping<DomainType> {
    override val aes: AesName
    override val sourceScaled: SourceScaled<DomainType>
    override val domainType: KType
    override var scaleParameters: ScaleParameters?
}

/**
 * Mapping with an unspecified default scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property sourceScaled the scaled default unspecified source to which the mapping is applied
 * @property domainType type of domain
 */
sealed interface ScaledUnspecifiedDefaultMapping<DomainType : Any> : ScaledMapping<DomainType> {
    override val aes: AesName
    override val sourceScaled: SourceScaledUnspecifiedDefault<DomainType>
    override val domainType: KType
    override var scaleParameters: ScaleParameters?
}

data class ScaledUnspecifiedDefaultPositionalMapping<DomainType : Any>(
    override val aes: AesName,
    override val sourceScaled: SourceScaledUnspecifiedDefault<DomainType>,
    override val domainType: KType
) : BaseScaledPositionalMapping<DomainType>, ScaledUnspecifiedDefaultMapping<DomainType> {
    override var scaleParameters: ScaleParameters? = null
}

data class ScaledUnspecifiedDefaultNonPositionalMapping<DomainType : Any, RangeType : Any>(
    override val aes: AesName,
    override val sourceScaled: SourceScaledUnspecifiedDefault<DomainType>,
    override val domainType: KType
) : BaseScaledNonPositionalMapping<DomainType, RangeType>, ScaledUnspecifiedDefaultMapping<DomainType> {
    override var scaleParameters: ScaleParameters? = null
}

/**
 * Mapping with a positional default scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property sourceScaled the scaled default positional source to which the mapping is applied
 * @property domainType type of domain
 */
data class ScaledPositionalUnspecifiedMapping<DomainType : Any>(
    override val aes: AesName,
    override val sourceScaled: SourceScaledPositionalUnspecified<DomainType>,
    override val domainType: KType
) : BaseScaledPositionalMapping<DomainType> {
    override var scaleParameters: ScaleParameters? = null
}

/**
 * Mapping with a non-positional default scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property sourceScaled the scaled default non-positional source to which the mapping is applied
 * @property domainType type of domain
 */
data class ScaledNonPositionalDefaultMapping<DomainType : Any, RangeType : Any>(
    override val aes: AesName,
    override val sourceScaled: SourceScaledNonPositionalUnspecified<DomainType>,
    override val domainType: KType
) : BaseScaledNonPositionalMapping<DomainType, RangeType> {
    override var scaleParameters: ScaleParameters? = null
}

/**
 * Mapping with a positional scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property sourceScaled the scaled positional source to which the mapping is applied
 * @property domainType type of domain
 */
data class ScaledPositionalMapping<DomainType : Any>(
    override val aes: AesName,
    override val sourceScaled: SourceScaledPositional<DomainType>,
    override val domainType: KType
) : BaseScaledPositionalMapping<DomainType> {
    override var scaleParameters: ScaleParameters? = null
}


/**
 * Mapping with a non-positional scale.
 *
 * @property aes the aesthetic attribute to be mapped to
 * @property sourceScaled the scaled non-positional source to which the mapping is applied
 * @property domainType type of domain
 */
data class ScaledNonPositionalMapping<DomainType : Any, RangeType : Any>(
    override val aes: AesName,
    override val sourceScaled: SourceScaledNonPositional<DomainType, RangeType>,
    override val domainType: KType,
) : BaseScaledNonPositionalMapping<DomainType, RangeType> {
    override var scaleParameters: ScaleParameters? = null
}
