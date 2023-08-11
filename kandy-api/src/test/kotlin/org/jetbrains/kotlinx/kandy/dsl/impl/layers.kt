/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.impl

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext

internal inline fun LayerCollectorContext.points(block: PointsContext.() -> Unit) {
    addLayer(PointsContext(this).apply(block), POINT)
}

internal inline fun LayerCollectorContext.bars(block: BarsContext.() -> Unit) {
    addLayer(BarsContext(this).apply(block), BAR)
}

internal inline fun LayerCollectorContext.line(block: LineContext.() -> Unit) {
    addLayer(LineContext(this).apply(block), LINE)
}
