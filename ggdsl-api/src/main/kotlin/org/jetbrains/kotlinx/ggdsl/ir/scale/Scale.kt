package org.jetbrains.kotlinx.ggdsl.ir.scale

/**
 * Scale base interface. Scale wraps a function that converts
 * a value from a data source into some geometric value (value of aesthetic attribute).
 */
public sealed interface Scale

/**
 * Continuous scale interface. Continuous scale wraps a function
 * that acts from one segment to another.
 */
public interface ContinuousScale : Scale {
    public val transform: Transform?
}

/**
 * Categorical scale interface. Categorical scale wraps a function
 * that acts from one finite set to another.
 */
public interface CategoricalScale : Scale
