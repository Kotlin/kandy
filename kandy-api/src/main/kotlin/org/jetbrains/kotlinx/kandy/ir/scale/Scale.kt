/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.scale

/**
 * Scale wraps a function that converts a value from a data source into
 * some geometric value (value of an aesthetic attribute).
 */
public sealed interface Scale {
    public companion object
}

/**
 * Continuous scale, i.e. scale defined by a continuous function.
 */
public interface ContinuousScale<RangeType> : Scale {
    public val nullValue: RangeType?
    public val transform: Transform?
}

/**
 * CategoricalScale scale, i.e. scale defined by a discrete finite function.
 */
public interface CategoricalScale : Scale
