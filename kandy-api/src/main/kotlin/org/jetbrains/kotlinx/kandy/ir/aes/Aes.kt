/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.aes

/**
 * Represents a visual attribute (aesthetic), commonly referred to as "aes", in a graphic.
 * Aesthetics dictate how data is visually represented on a plot.
 * Examples include `x` and `y` coordinates, or `line types`.
 * Their corresponding [Aes] instances would be `Aes("x")`, `Aes("y")`, and `Aes("line_type")`.
 *
 * @property name the name of the aesthetic.
 */
public data class Aes(val name: String)
