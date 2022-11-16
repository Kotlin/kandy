/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir.scale

/**
 * A basic interface for [Scale] transformation
 * (that is, a function that transforms a scale via composition)
 */
public sealed interface Transform

public interface PositionalTransform : Transform
public interface NonPositionalTransform : Transform
