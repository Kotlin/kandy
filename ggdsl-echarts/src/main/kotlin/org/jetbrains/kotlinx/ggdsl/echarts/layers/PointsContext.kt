/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.data.MutableNamedData
import org.jetbrains.kotlinx.ggdsl.echarts.*

<<<<<<< HEAD
class PointsContext(parent: LayerCollectorContext) : LayerContext(parent) {
    val x = XAes(this)
    val y = YAes(this)
=======
public class PointsContext(override var data: MutableNamedData) : LayerContext() {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)
>>>>>>> main

    public val size: SizeAes = SizeAes(this)
    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)

    public val borderWidth: BorderWidthAes = BorderWidthAes(this)
    public val borderColor: BorderColorAes = BorderColorAes(this)

    public val symbol: SymbolAes = SymbolAes(this)
}

<<<<<<< HEAD
class LineContext(parent: LayerCollectorContext) :
    LayerContext(parent) {
    val x = XAes(this)
    val y = YAes(this)
=======
public class LineContext(override var data: MutableNamedData) :
    LayerContext() {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)
>>>>>>> main

    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)

    public val width: WidthAes = WidthAes(this)

    public val lineType: LineTypeAes = LineTypeAes(this)
}

<<<<<<< HEAD
class BarsContext(parent: LayerCollectorContext) :
    LayerContext(parent) {
    val x = XAes(this)
    val y = YAes(this)
=======
public class BarsContext(override var data: MutableNamedData) :
    LayerContext() {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)
>>>>>>> main

    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)

    public val width: WidthAes = WidthAes(this)

    public val borderWidth: BorderWidthAes = BorderWidthAes(this)
    public val borderColor: BorderColorAes = BorderColorAes(this)
}