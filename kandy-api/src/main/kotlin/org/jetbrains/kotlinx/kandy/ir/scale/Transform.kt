/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.scale

/**
 * [Scale] transformation - a function that transforms a scale via composition.
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
