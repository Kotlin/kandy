package org.jetbrains.kotlinx.ggdsl.ir.scale

//TODO behavior

/**
 * Interface of default scale.
 */
sealed interface UnspecifiedScale : Scale

/**
 * Default unspecified scale
 */
object DefaultUnspecifiedScale : UnspecifiedScale

/**
 * Interface of positional unspecified scale
 */
sealed interface PositionalUnspecifiedScale : UnspecifiedScale

/**
 * Interface of non-positional unspecified scale
 */
sealed interface NonPositionalUnspecifiedScale : UnspecifiedScale

/**
 * Positional continuous scale with an unspecified domain.
 */
data class PositionalContinuousUnspecifiedScale(override val transform: PositionalTransform? = null) :
    PositionalUnspecifiedScale, ContinuousScale

/**
 * Positional categorical scale with an unspecified domain.
 */
object PositionalCategoricalUnspecifiedScale : PositionalUnspecifiedScale, CategoricalScale

/**
 * Non-positional continuous scale with an unspecified domain and range.
 */
data class NonPositionalContinuousUnspecifiedScale(override val transform: NonPositionalTransform? = null) :
    NonPositionalUnspecifiedScale, ContinuousScale

/**
 * Non-positional categorical scale with an unspecified domain and range.
 */
object NonPositionalCategoricalUnspecifiedScale : NonPositionalUnspecifiedScale, CategoricalScale
