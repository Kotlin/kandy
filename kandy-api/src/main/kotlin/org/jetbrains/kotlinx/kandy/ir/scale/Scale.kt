/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.scale

import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.data.TableData

/**
 * Represents the concept of a scale, a pivotal element in the visualization library.
 * A scale provides the mapping between the data space, raw [data values][TableData], and the [aesthetic][Aes] space,
 * which can be visual attributes like `colors`, `positions`, or `sizes`.
 * In essence, a `scale` converts data values into geometric, visual representations.
 *
 * Key types of scales:
 * - [Continuous scale][ContinuousScale] - Maps continuous data to continuous aesthetics (e.g., mapping a range of data values to a gradient of colors).
 * - [Categorical (discrete) scale][CategoricalScale] - Maps discrete data to discrete aesthetics (e.g., mapping categories to specific colors).
 * - [Position scale][PositionalScale] - Determines the placement of data points along the `x` or `y` axes.
 */
public sealed interface Scale {
    public companion object
}

/**
 * Represents a continuous [scale][Scale], which maps data using a continuous function.
 *
 * Continuous scales are used to represent continuous data values and map them to aesthetic attributes,
 * such as color gradients or point sizes.
 *
 * @property nullValue the default value to be used when data is `null`.
 * @property transform an optional [transformation][Transform] applied to the data values before mapping.
 */
public interface ContinuousScale<RangeType> : Scale {
    public val nullValue: RangeType?
    public val transform: Transform?
}

/**
 * Represents a categorical [scale][Scale], which maps discrete, finite data values to specific aesthetic attributes.
 * Such scales are used to assign unique `colors`, `shapes`, or `sizes` to distinct categories within the data.
 */
public interface CategoricalScale : Scale
