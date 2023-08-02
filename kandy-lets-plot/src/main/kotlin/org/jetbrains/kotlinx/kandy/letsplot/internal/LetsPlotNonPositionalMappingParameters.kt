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
 * @property DomainType scale domain type.
 * @property scale positional scale of this mapping.
 * @property axis scale axis settings.
 */
public interface LetsPlotPositionalMappingParameters<DomainType> : PositionalMappingParameters<DomainType> {
    public val axis: Axis<DomainType>
}

/* TODO Do we need it?
*//**
 * Lets-plot positional mapping parameters for categorical aes.
 *
 * @property DomainType scale domain type.
 * @property scale positional scale of this mapping.
 * @property axis scale axis settings.
 *//*
public data class LetsPlotPositionalMappingParametersCategorical<DomainType>(
    override var scale: PositionalScale<out DomainType> = PositionalDefaultScale(),
    public override val axis: Axis<DomainType> = Axis()
) : LetsPlotPositionalMappingParameters<DomainType>*/

/**
 * Lets-plot positional mapping parameters for continuous aes.
 *
 * @property DomainType scale domain type.
 * @property scale positional scale of this mapping.
 * @property axis scale axis settings.
 */
public data class LetsPlotPositionalMappingParametersContinuous<DomainType>(
    override var scale: PositionalScale<out DomainType> = PositionalDefaultScale(),
    public override val axis: Axis<DomainType> = Axis()
) : LetsPlotPositionalMappingParameters<DomainType>,
    PositionalMappingParametersContinuous<DomainType>

/**
 * Lets-plot non-positional mapping parameters.
 *
 * @property DomainType scale domain type.
 * @property RangeType scale domain type.
 * @property scale positional scale of this mapping.
 * @property legend scale legend settings.
 */
public interface LetsPlotNonPositionalMappingParameters<DomainType, RangeType>
    : NonPositionalMappingParameters<DomainType, RangeType> {
    public val legend: Legend<DomainType, RangeType>
}

/**
 * Lets-plot non-positional mapping parameters for continuous aes.
 *
 * @property DomainType scale domain type.
 * @property RangeType scale domain type.
 * @property scale positional scale of this mapping.
 * @property legend scale legend settings.
 */
public data class LetsPlotNonPositionalMappingParametersCategorical<DomainType, RangeType>(
    override var scale: NonPositionalCategoricalScaleBase<out DomainType, out RangeType> =
        NonPositionalDefaultCategoricalScale<DomainType, RangeType>(),
    public override val legend: Legend<DomainType, RangeType> = Legend()
) : LetsPlotNonPositionalMappingParameters<DomainType, RangeType>

/**
 * Lets-plot non-positional mapping parameters.
 *
 * @property DomainType scale domain type.
 * @property RangeType scale domain type.
 * @property scale positional scale of this mapping.
 * @property legend scale legend settings.
 */
public data class LetsPlotNonPositionalMappingParametersContinuous<DomainType, RangeType>(
    override var scale: NonPositionalScale<out DomainType, out RangeType> = NonPositionalDefaultScale(),
    public override val legend: Legend<DomainType, RangeType> = Legend()
) : LetsPlotNonPositionalMappingParameters<DomainType, RangeType>,
    NonPositionalMappingParametersContinuous<DomainType, RangeType>
