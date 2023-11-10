/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.internal

import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.ir.scale.*
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.Axis
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.Legend

/**
 * Lets-plot positional mapping parameters.
 *
 * @property DomainType the type of the domain values for the scale.
 * @property scale the positional scale used in the mapping.
 * @property axis configuration settings for the scale axis.
 */
public interface LetsPlotPositionalMappingParameters<DomainType> : PositionalMappingParameters<DomainType> {
    public val axis: Axis<DomainType>
}

/**
 * Lets-plot positional mapping parameters for continuous aes.
 *
 * @property DomainType the type of the domain values for the scale.
 * @property scale the positional scale used in this mapping.
 * @property axis configuration settings for the scale axis.
 */
public data class LetsPlotPositionalMappingParametersContinuous<DomainType>(
    override var scale: PositionalScale<out DomainType> = PositionalDefaultScale(),
    public override val axis: Axis<DomainType> = Axis()
) : LetsPlotPositionalMappingParameters<DomainType>,
    PositionalMappingParametersContinuous<DomainType>

/**
 * Lets-plot non-positional mapping parameters.
 *
 * @property DomainType the type of the domain values for the scale.
 * @property RangeType the type of the range values for the scale.
 * @property scale the scale used in the mapping.
 * @property legend configuration settings for the scale legend.
 */
public interface LetsPlotNonPositionalMappingParameters<DomainType, RangeType>
    : NonPositionalMappingParameters<DomainType, RangeType> {
    public val legend: Legend<DomainType, RangeType>
}

/**
 * Lets-plot non-positional mapping parameters for continuous aes.
 *
 * @property DomainType the type of the domain values for the scale.
 * @property RangeType the type of the range values for the scale.
 * @property scale the scale used in this mapping.
 * @property legend configuration settings for the scale legend.
 */
public data class LetsPlotNonPositionalMappingParametersCategorical<DomainType, RangeType>(
    override var scale: NonPositionalCategoricalScaleBase<out DomainType, out RangeType> =
        NonPositionalDefaultCategoricalScale<DomainType, RangeType>(),
    public override val legend: Legend<DomainType, RangeType> = Legend()
) : LetsPlotNonPositionalMappingParameters<DomainType, RangeType>

/**
 * Lets-plot non-positional mapping parameters.
 *
 * @property DomainType the type of the domain values for the scale.
 * @property RangeType the type of the range values for the scale.
 * @property scale the scale used in this mapping.
 * @property legend configuration settings for the scale legend.
 */
public data class LetsPlotNonPositionalMappingParametersContinuous<DomainType, RangeType>(
    override var scale: NonPositionalScale<out DomainType, out RangeType> = NonPositionalDefaultScale(),
    public override val legend: Legend<DomainType, RangeType> = Legend()
) : LetsPlotNonPositionalMappingParameters<DomainType, RangeType>,
    NonPositionalMappingParametersContinuous<DomainType, RangeType>
