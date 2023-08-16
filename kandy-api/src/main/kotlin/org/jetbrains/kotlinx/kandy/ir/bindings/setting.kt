/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.bindings

import org.jetbrains.kotlinx.kandy.ir.aes.AesName

/**
 * Assigning a constant value to an attribute.
 *
 * Represents a mechanism through which users can specify and assign fixed,
 * unchanging values directly to [aesthetic attributes][AesName] of a plot, as opposed to dynamic values derived from data.
 *
 * This is used when you wish to set a specific static value to an aesthetic rather than mapping it to a data-driven value.
 * Examples:
 * - Setting a static color for a plot: `color = Color.RED`.
 * - Specifying a fixed size: `size = 3.7`.
 * - Defining a constant value for an axis: `x.constant(8.1)`.
 */
public sealed interface Setting

/**
 * Represents settings specific to non-positional [aesthetic attributes][AesName].
 *
 * Non-positional aesthetic attributes are those not associated with axes or coordinates on the plot.
 * Examples:
 * - Setting a static color for the plot: `color = Color.RED`
 * - Assigning a fixed size: `size = 3.7`
 *
 * @property aes non-positional aesthetic attribute to be set to.
 * @property value the constant value assigned to the aesthetic attribute.
 */
public data class NonPositionalSetting<T>(
    val aes: AesName,
    val value: T,
) : Setting

/**
 * Represents settings specific to positional [aesthetic attributes][AesName].
 *
 * Positional aesthetic attributes are those associated directly with axes or coordinates on the plot.
 * Examples:
 * - Fixing a specific x-axis value: `x.constant(8.1)`
 * - Assigning a constant y-axis value: `y.constant(3.5)`
 *
 * @property aes positional aesthetic attribute to be set to.
 * @property value the constant value assigned to the positional aesthetic.
 */
public data class PositionalSetting<T>(
    val aes: AesName,
    val value: T,
) : Setting
