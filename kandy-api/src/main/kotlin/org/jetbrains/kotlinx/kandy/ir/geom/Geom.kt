/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.geom

import org.jetbrains.kotlinx.kandy.ir.Layer

/**
 * Represents a geometric object (or "geom") for data visualization.
 *
 * A geom specifies the type of visual representation or [layer][Layer] for the data on the plot.
 *
 * Common geoms include:
 * - `line` - For line charts.
 * - `bar` - For bar charts.
 * - `point` - For scatter-plots.
 */
public interface Geom
