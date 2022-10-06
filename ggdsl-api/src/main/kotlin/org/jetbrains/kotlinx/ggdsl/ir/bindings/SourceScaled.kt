/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir.bindings

import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.scale.*

/**
 * Scaled source base interface
 *
 * @property DomainType the type of domain
 * @property source the source to which the scale is applied
 * @property scale applying scale
 */
public sealed interface SourceScaled<DomainType : Any> {
    public val source: ColumnPointer<DomainType>
    public val scale: Scale
}
/*
/**
 * Scaled default source interface
 *
 * @property DomainType the type of domain
 * @property source the source to which the scale is applied
 * @property scale applying default scale
 */
sealed interface SourceScaledUnspecified<DomainType : Any> : SourceScaled<DomainType> {
    override val scale: UnspecifiedScale
}

 */

/**
 * Scaled unspecified default source
 *
 * @property DomainType the type of domain
 * @property source the source to which the scale is applied
 * @property scale applying unspecified default scale
 */
public data class SourceScaledUnspecifiedDefault<DomainType : Any>(
    override val source: ColumnPointer<DomainType>,
) : SourceScaled<DomainType> {
    override val scale: DefaultUnspecifiedScale = DefaultUnspecifiedScale
}

/**
 * Scaled positional default source
 *
 * @property DomainType the type of domain
 * @property source the source to which the scale is applied
 * @property scale applying positional default scale
 */
public data class SourceScaledPositionalUnspecified<DomainType : Any>(
    override val source: ColumnPointer<DomainType>,
    override val scale: PositionalUnspecifiedScale
) : SourceScaled<DomainType>

/**
 * Scaled non-positional default source
 *
 * @property DomainType the type of domain
 * @property source the source to which the scale is applied
 * @property scale applying non-positional default scale
 */
public data class SourceScaledNonPositionalUnspecified<DomainType : Any>(
    override val source: ColumnPointer<DomainType>,
    override val scale: NonPositionalUnspecifiedScale
) : SourceScaled<DomainType>

/**
 * Scaled positional source
 *
 * @property DomainType the type of domain
 * @property source the source to which the scale is applied
 * @property scale applying positional scale
 */
public data class SourceScaledPositional<DomainType : Any>(
    override val source: ColumnPointer<DomainType>,
    override val scale: PositionalScale<DomainType>
) : SourceScaled<DomainType>

/**
 * Scaled a non-positional source
 *
 * @property DomainType the type of domain
 * @property source the source to which the scale is applied
 * @property scale applying non-positional scale
 */
public data class SourceScaledNonPositional<DomainType : Any, RangeType : Any>(
    override val source: ColumnPointer<DomainType>,
    override val scale: NonPositionalScale<DomainType, RangeType>
) : SourceScaled<DomainType>
