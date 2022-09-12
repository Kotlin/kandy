/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

class PointsContext(override var data: MutableNamedData) : LayerContext() {
    val x = XAes(this)
    val y = YAes(this)

    val size = SizeAes(this)
    val color = ColorAes(this)
    val alpha = AlphaAes(this)

    val borderWidth = BorderWidthAes(this)
    val borderColor = BorderColorAes(this)

    val symbol = SymbolAes(this)
}

class LineContext(override var data: MutableNamedData) :
    LayerContext() {
    val x = XAes(this)
    val y = YAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)

    val width = WidthAes(this)

    val lineType = LineTypeAes(this)
}

class BarsContext(override var data: MutableNamedData) :
    LayerContext() {
    val x = XAes(this)
    val y = YAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)

    val width = WidthAes(this)

    val borderWidth = BorderWidthAes(this)
    val borderColor = BorderColorAes(this)
}