/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.aes

/**
 * Represents the [name] of a visual attribute (aesthetic) in a graphic.
 * Aesthetics dictate how data is visually represented on a plot.
 * Examples of aesthetics include `x` and `y` coordinates, or `line types`.
 * Their corresponding [AesName] would be `AesName("x")`, `AesName("y")`, and `AesName("line_type")`.
 *
 * @property name the name of the aesthetic.
 */
public data class AesName(val name: String)
