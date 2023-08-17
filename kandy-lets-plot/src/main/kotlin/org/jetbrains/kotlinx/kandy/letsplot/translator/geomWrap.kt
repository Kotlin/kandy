/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.*
import org.jetbrains.letsPlot.intern.layer.GeomOptions


internal fun Geom.wrap(): GeomOptions {
    return when (this) {
        is LetsPlotGeom -> when(this) {
            AB_LINE -> org.jetbrains.letsPlot.Geom.abline()
            AREA -> org.jetbrains.letsPlot.Geom.area()
            BAR -> org.jetbrains.letsPlot.Geom.bar()
            BOXPLOT -> org.jetbrains.letsPlot.Geom.boxplot()
            CROSS_BAR -> org.jetbrains.letsPlot.Geom.crossbar()
            ERROR_BAR -> org.jetbrains.letsPlot.Geom.errorbar()
            H_LINE -> org.jetbrains.letsPlot.Geom.hline()
            LINE -> org.jetbrains.letsPlot.Geom.line()
            PATH -> org.jetbrains.letsPlot.Geom.path()
            PIE -> org.jetbrains.letsPlot.Geom.pie()
            LINE_RANGE -> org.jetbrains.letsPlot.Geom.linerange()
            POINT -> org.jetbrains.letsPlot.Geom.point()
            POINT_RANGE -> org.jetbrains.letsPlot.Geom.pointrange()
            RASTER -> org.jetbrains.letsPlot.Geom.raster()
            RECT -> org.jetbrains.letsPlot.Geom.rect()
            RIBBON -> org.jetbrains.letsPlot.Geom.ribbon()
            SEGMENT -> org.jetbrains.letsPlot.Geom.segment()
            STEP -> org.jetbrains.letsPlot.Geom.step()
            TILE -> org.jetbrains.letsPlot.Geom.tile()
            TEXT -> org.jetbrains.letsPlot.Geom.text()
            V_LINE -> org.jetbrains.letsPlot.Geom.vline()
            else -> TODO("error unexpected geom")
        }
        else -> TODO("error unexpected geom")
    }
}