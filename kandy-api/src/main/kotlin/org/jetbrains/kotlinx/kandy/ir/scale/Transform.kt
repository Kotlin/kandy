/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.scale

/**
 * [ContinuousScale] transformation - function that converts the scale through a composition with the base function.
 */
public sealed interface Transform

/**
 * Positional scale transformation.
 */
public interface PositionalTransform : Transform

/**
 * Non-positional scale transformation.
 */
public interface NonPositionalTransform : Transform
