/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable

inline fun LayerCollectorContextImmutable.points(block: PointsContextImmutable.() -> Unit) {
    addLayer(PointsContextImmutable(this).apply(block), POINT)
}

inline fun LayerCollectorContextImmutable.bars(block: BarsContextImmutable.() -> Unit) {
    addLayer(BarsContextImmutable(this).apply(block), BAR)
}

inline fun LayerCollectorContextImmutable.line(block: LineContextImmutable.() -> Unit) {
    addLayer(LineContextImmutable(this).apply(block), LINE)
}
