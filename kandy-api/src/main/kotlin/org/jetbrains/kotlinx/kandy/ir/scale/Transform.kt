/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.scale

import org.jetbrains.kotlinx.kandy.ir.aes.Aes

/**
 * Represents a transformation function applied to raw data values prior to their mapping to an aesthetic attribute.
 */
public sealed interface Transform

/**
 * Represents a transformation function for [positional scales][PositionalScale],
 * applied to data values to determine their placement within the plot space.
 */
public interface PositionalTransform : Transform

/**
 * Represents a transformation function for [non-positional scales][NonPositionalScale],
 * adjusting data values before they are mapped to [aesthetic attributes][Aes].
 */
public interface NonPositionalTransform : Transform
