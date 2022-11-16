/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorContext


//public inline fun LayerCollectorContext.points(block: PointsContext.() -> Unit) {
//    addLayer(PointsContext(this).apply(block), POINT)
//}

//public inline fun LayerCollectorContext.bars(block: BarsContext.() -> Unit) {
//    addLayer(BarsContext(this).apply(block), BAR)
//}

public inline fun LayerCollectorContext.line(block: LineContext.() -> Unit) {
    addLayer(LineContext(this).apply(block), LINE)
}
