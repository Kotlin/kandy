/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir.scale

import kotlinx.serialization.Serializable


/**
 * Unspecified scale - a scale without a specified type and parameters;
 * they will be defined automatically.
 */
@Serializable
public sealed interface UnspecifiedScale : Scale

/**
 * Default unspecified scale - a scale without a specified type and parameters;
 * they will be defined automatically; can be both used for positional and non-positional mappings.
 */
@Serializable
public object DefaultUnspecifiedScale : UnspecifiedScale

/**
 * Interface of positional unspecified scale - a scale without a specified type and parameters;
 * they will be defined automatically.
 */
public sealed interface PositionalUnspecifiedScale : UnspecifiedScale

/**
 * Interface of non-positional unspecified scale - a scale without a specified type and parameters;
 * they will be defined automatically.
 */
public sealed interface NonPositionalUnspecifiedScale : UnspecifiedScale

/**
 * Positional continuous scale with an unspecified domain; it will be defined automatically.
 *
 * @property transform transformation of the scale.
 */
@Serializable
public data class PositionalContinuousUnspecifiedScale(override val transform: PositionalTransform? = null) :
    PositionalUnspecifiedScale, ContinuousScale

/**
 * Positional categorical scale with an unspecified domain; it will be defined automatically.
 */
@Serializable
public object PositionalCategoricalUnspecifiedScale : PositionalUnspecifiedScale, CategoricalScale

/**
 * Non-positional continuous scale with an unspecified domain and range; they will be defined automatically.
 *
 * @property transform transformation of the scale.
 */
@Serializable
public data class NonPositionalContinuousUnspecifiedScale(override val transform: NonPositionalTransform? = null) :
    NonPositionalUnspecifiedScale, ContinuousScale

/**
 * Non-positional categorical scale with an unspecified domain and range; they will be defined automatically.
 */
@Serializable
public object NonPositionalCategoricalUnspecifiedScale : NonPositionalUnspecifiedScale, CategoricalScale
