/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.util.linetype


data class LineType internal constructor(val name: String) {
    companion object {
        val SOLID = LineType("solid")
        val DASHED = LineType("dashed")
        val DOTTED = LineType("dotted")
    }
}
