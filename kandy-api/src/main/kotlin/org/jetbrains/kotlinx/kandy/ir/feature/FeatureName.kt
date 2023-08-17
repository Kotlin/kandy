/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.feature

/**
 * Represents a feature within the plotting system.
 * Features define various characteristics or enhancements
 * that can be introduced by the user to modify the behavior or overall appearance of a plot.
 *
 * For instance:
 * - `tooltips` - Pop-up hints that appear when hovering over layers or points on the plot.
 * - `coord_flip` - Switches the plot axes, converting between horizontal and vertical representations.
 * - `position` - Dictates the position of plot elements, such as the legend.
 *
 * @property name the unique identifier or name of the feature.
 */
public data class FeatureName(val name: String)
