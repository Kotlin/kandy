/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.impl

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope

internal inline fun LayerCreatorScope.points(block: PointsHandler.() -> Unit) {
    createLayer(PointsHandler(this), block)
}

internal inline fun LayerCreatorScope.bars(block: BarsHandler.() -> Unit) {
    createLayer(BarsHandler(this), block)
}

internal inline fun LayerCreatorScope.line(block: LineHandler.() -> Unit) {
    createLayer(LineHandler(this), block)
}
