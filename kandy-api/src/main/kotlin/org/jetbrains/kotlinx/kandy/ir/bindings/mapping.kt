/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.bindings

import org.jetbrains.kotlinx.kandy.ir.aes.Aes

/**
 * Maps data from a dataset to an [aesthetic attribute][Aes].
 *
 * A mapping binds data to visual attributes of a plot, facilitating the visual representation of the data.
 *
 * Examples:
 * * `x(variable1)` represents a [positional mapping][PositionalMapping] _x_: _D&#8321;_ &#8594; _X_.
 * Where _D&#8321;_ is the set of values from `variable1` and _X_ denotes all possible positions on the x-axis.
 *
 * * `y(variable2)` is another example of a [positional mapping][PositionalMapping] _y_: _D&#8322;_ &#8594; _Y_.
 * Where _D&#8322;_ contains values from `variable2` and _Y_ captures all potential positions on the y-axis.
 *
 * * `color(variable3)` showcases a [non-positional mapping][NonPositionalMapping] _color_: _D&#8323;_ &#8594; _C_;
 * with _D&#8323;_ holding values from `variable3` and _C_
 * representing all available color options for plot elements.
 *
 * * `size(variable4)` is a [non-positional mapping][NonPositionalMapping] _size_: _D&#8324;_ &#8594; _S_.
 * Here, _D&#8324;_ consists of values from `variable4`, while _S_ details all potential sizes.
 *
 * @property aes the [aesthetic attribute][Aes] being mapped.
 * @property columnID the identifier for the column in the dataset that corresponds to the aesthetic attribute.
 * @property parameters the [mapping parameters][MappingParameters], optional.
 */
public sealed interface Mapping {
    public val aes: Aes
    public val columnID: String

    public val parameters: MappingParameters?
}

/**
 * Maps data from a dataset to a positional [aesthetic attribute][Aes].
 *
 * Positional mapping links data points to specific locations on the graphical space,
 * often determining the spatial placement of these points on the plot.
 * This ensures that the data is visually represented based on its intrinsic values and relations.
 *
 * Examples:
 * * `x(variable1)` represents a [positional mapping][PositionalMapping] _x_: _D&#8321;_ &#8594; _X_.
 * Where _D&#8321;_ is the set of values from `variable1` and _X_ denotes all possible positions on the x-axis.
 *
 * * `y(variable2)` is another example of a [positional mapping][PositionalMapping] _y_: _D&#8322;_ &#8594; _Y_.
 * Where _D&#8322;_ contains values from `variable2` and _Y_ captures all potential positions on the y-axis.
 *
 * Note: For non-positional mappings like `color(variable3)` or `size(variable4)`, refer to [NonPositionalMapping].
 *
 * @property aes the positional [aesthetic attribute][Aes] being mapped.
 * @property columnID the identifier for the column in the dataset that corresponds to the aesthetic attribute.
 * @property parameters the [positional mapping parameters][PositionalMappingParameters], optional.
 */
public data class PositionalMapping<DomainType>(
    override val aes: Aes,
    override val columnID: String,
    override val parameters: PositionalMappingParameters<DomainType>?
) : Mapping

/**
 * Maps data from a dataset to a non-positional [aesthetic attribute][Aes].
 *
 * Non-positional mapping connects data points to the visual attributes of plot elements, like `color` or `size`,
 * which aren't associated with a particular spatial position on the plot.
 * This enables the visual interpretation of the data based on various attributes other than their spatial distribution.
 *
 * Examples:
 * * `color(variable3)` showcases a [non-positional mapping][NonPositionalMapping] _color_: _D&#8323;_ &#8594; _C_;
 * with _D&#8323;_ holding values from `variable3` and _C_
 * representing all available color options for plot elements.
 *
 * * `size(variable4)` is a [non-positional mapping][NonPositionalMapping] _size_: _D&#8324;_ &#8594; _S_.
 * Here, _D&#8324;_ consists of values from `variable4`, while _S_ details all potential sizes.
 *
 * Note: For positional mappings like `x(variable1)` or `y(variable2)`, refer to [PositionalMapping].
 *
 * @property aes The non-positional [aesthetic attribute][Aes] being mapped.
 * @property columnID the identifier for the column in the dataset that corresponds to the aesthetic attribute.
 * @property parameters the [non-positional mapping parameters][NonPositionalMappingParameters], optional.
 */
public data class NonPositionalMapping<DomainType, RangeType>(
    override val aes: Aes,
    override val columnID: String,
    override val parameters: NonPositionalMappingParameters<DomainType, RangeType>?
) : Mapping