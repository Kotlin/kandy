/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.scale

/**
 * Represents a positional scale that determines the placement of data points within the plot space.
 *
 * While continuous scales map continuous data values to visual attributes like gradients,
 * and categorical scales map discrete data to specific visual markers,
 * positional scales specifically handle the positioning of these visual elements on the plot,
 * such as along the x and y axes.
 * Positional scales ensure
 * that the data representation remains coherent and interpretable in terms of their positions relative to each other.
 */
public sealed interface PositionalScale<DomainType> : Scale

/**
 * Represents a [continuous scale][ContinuousScale]
 * specialized for determining the positioning of data points within the plotting space.
 *
 * The `PositionalContinuousScale` takes continuous data values and maps them to precise positions on the plot,
 * such as specific points along the x or y axes.
 * By defining the range of the scale through minimum and maximum values,
 * it ensures that the visual representation of the data is proportionate and accurate.
 * Transformation functions can be applied to adjust the distribution or representation of these positions.
 *
 * @property DomainType the type of data values this scale handles.
 * @property min specifies the lower boundary of the scale's domain.
 * @property max specifies the upper boundary of the scale's domain.
 * @property nullValue the value used when data is `null`.
 * @property transform an optional [transformation][Transform], specifically for adjusting positional values.
 */
public data class PositionalContinuousScale<DomainType>(
    val min: DomainType?,
    val max: DomainType?,
    override val nullValue: DomainType?,
    override val transform: PositionalTransform?,
) : ContinuousScale<DomainType>, PositionalScale<DomainType>

/**
 * Represents a [categorical scale][CategoricalScale]
 * specialized for determining the positioning of distinct categories within the plotting space.
 *
 * The `PositionalCategoricalScale` map discrete data values,
 * represented as categories, to specific positions on a plot.
 * This can be used to denote positions along axes for categorical data,
 * such as the x-axis ticks for bar plots or categories in a scatter plot.
 *
 * @property DomainType the type of data values (categories) this scale operates on.
 * @property categories specifies the list of unique categories within the domain.
 */
public data class PositionalCategoricalScale<DomainType>(
    val categories: List<DomainType>?,
) : CategoricalScale, PositionalScale<DomainType>

/**
 * Represents the default positional scale used when no explicit positional scale is provided.
 *
 * @property DomainType the type of data values this scale maps.
 */
public class PositionalDefaultScale<DomainType>
    : PositionalScale<DomainType>, DefaultScale {
    public override fun equals(other: Any?): Boolean {
        return other is PositionalDefaultScale<*>
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
