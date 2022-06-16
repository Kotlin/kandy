package org.jetbrains.kotlinx.ggdsl.ir.geom

import org.jetbrains.kotlinx.ggdsl.ir.Layer

/**
 *  Geometrical entity that characterizes the [Layer].
 */
interface Geom {
    companion object {
        val POINT = CommonGeom("point")
        val BAR = CommonGeom("bar")
        val LINE = CommonGeom("line")
    }
}
