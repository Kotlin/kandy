/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*


public class TileContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent), WithX, WithY, WithAlpha,
    WithFillColor, WithWidth, WithHeight


