package org.jetbrains.kotlinx.ggdsl.ir.scale

//TODO behavior

/**
 * Interface of default scale.
 */
public sealed interface UnspecifiedScale : Scale

/**
 * Default unspecified scale
 */
public object DefaultUnspecifiedScale : UnspecifiedScale

/**
 * Interface of positional unspecified scale
 */
public sealed interface PositionalUnspecifiedScale : UnspecifiedScale

/**
 * Interface of non-positional unspecified scale
 */
public sealed interface NonPositionalUnspecifiedScale : UnspecifiedScale

/**
 * Positional continuous scale with an unspecified domain.
 */
public data class PositionalContinuousUnspecifiedScale(override val transform: PositionalTransform? = null) :
    PositionalUnspecifiedScale, ContinuousScale

/**
 * Positional categorical scale with an unspecified domain.
 */
public object PositionalCategoricalUnspecifiedScale : PositionalUnspecifiedScale, CategoricalScale

/**
 * Non-positional continuous scale with an unspecified domain and range.
 */
public data class NonPositionalContinuousUnspecifiedScale(override val transform: NonPositionalTransform? = null) :
    NonPositionalUnspecifiedScale, ContinuousScale

/**
 * Non-positional categorical scale with an unspecified domain and range.
 */
public object NonPositionalCategoricalUnspecifiedScale : NonPositionalUnspecifiedScale, CategoricalScale
