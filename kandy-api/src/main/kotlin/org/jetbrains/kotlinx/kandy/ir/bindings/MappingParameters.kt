/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.bindings

import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalScale
import org.jetbrains.kotlinx.kandy.ir.scale.Scale

/**
 *  Parameters for data visualization mapping.
 *
 *  Describes how the data is visually represented on a plot.
 *
 *  This includes:
 *  - [`scale`][Scale] - establishes the mapping between data scales and positional aesthetic attributes.
 *
 *  @property scale the [scale][Scale] associated with the mapping, determining how data values map to visual attributes.
 */
public interface MappingParameters {
    public val scale: Scale
}

/**
 * Parameters for positional data visualization mapping.
 *
 * Specifies how the data is visually mapped to a plot's position.
 *
 * This includes:
 * - [`scale`][PositionalScale] -
 * establishes the mapping between data scales and positional aesthetic attributes.
 *
 * @property scale the [positional scale][PositionalScale] tied to the mapping, defining how data values are represented spatially.
 */
public interface PositionalMappingParameters<DomainType> : MappingParameters {
    public override val scale: PositionalScale<out DomainType>
}

/**
 * Parameters for continuous positional data visualization mapping.
 *
 * Specifies how continuous data is visually mapped to a plot's position.
 *
 * This includes:
 * - [`scale`][PositionalScale] -
 * establishes the mapping between continuous data scales and positional aesthetic attributes.
 *
 * @property scale The [continuous positional scale][PositionalScale] is tied to the mapping,
 * determining how data values are represented spatially.
 */
public interface PositionalMappingParametersContinuous<DomainType> : PositionalMappingParameters<DomainType>

/**
 * Parameters for non-positional data visualization mapping.
 *
 * Specifies how data is visually represented on a plot, excluding its positional attributes.
 *
 * This includes:
 * - [`scale`][NonPositionalScale] -
 * establishes the mapping between data values and non-positional aesthetic attributes.
 *
 * @property scale The non-positional scale is linked to the mapping, determining how data values correspond to aesthetic properties.
 */
public interface NonPositionalMappingParameters<DomainType, RangeType> : MappingParameters {
    public override val scale: NonPositionalScale<out DomainType, out RangeType>
}

/**
 * Parameters for continuous non-positional data visualization mapping.
 *
 * Details how continuous data is visually depicted on a plot outside its positional attributes.
 *
 * This entails:
 * - [`scale`][NonPositionalScale] -
 * establishes the mapping between continuous data values and non-positional aesthetic attributes.
 *
 * @property scale The non-positional scale is linked with this continuous mapping,
 * which determines how continuous data values correspond to aesthetic properties.
 */
public interface NonPositionalMappingParametersContinuous<DomainType, RangeType> :
    NonPositionalMappingParameters<DomainType, RangeType>
