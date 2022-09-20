/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat

import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.invoke
import org.jetbrains.kotlinx.ggdsl.letsplot.BinWidth2DAes
import org.jetbrains.kotlinx.ggdsl.letsplot.BinWidthAes
import org.jetbrains.kotlinx.ggdsl.letsplot.Bins2DAes
import org.jetbrains.kotlinx.ggdsl.letsplot.BinsAes

interface Bins {
    data class ByNumber internal constructor(val number: Int) : Bins
    data class ByWidth internal constructor(val width: Double) : Bins

    companion object {
        fun byNumber(number: Int) = ByNumber(number)
        fun byWidth(width: Double) = ByWidth(width)
    }
}

abstract class WithBinsContext(bins: Bins?): LayerContext() {
    private val bins = BinsAes(this)
    private val binWidth = BinWidthAes(this)

    init {
        bins?.let {
            countBins(it)
        }
    }

    @PublishedApi
    internal fun countBins(bins: Bins) {
        when(bins) {
            is Bins.ByNumber -> bins(bins.number)
            is Bins.ByWidth -> binWidth(bins.width)
        }
    }
}

interface Bins2D {
    data class ByNumber internal constructor(val numberX: Int, val numberY: Int) : Bins2D
    data class ByWidth internal constructor (val widthX: Double, val widthY: Double) : Bins2D

    companion object {
        fun byNumber(numberX: Int, numberY: Int) = ByNumber(numberX, numberY)
        fun byWidth(widthX: Double, widthY: Double) = ByWidth(widthX, widthY)
    }
}

abstract class WithBins2DContext(bins: Bins2D?): LayerContext() {
    private val bins = Bins2DAes(this)
    private val binWidth = BinWidth2DAes(this)

    init {
        bins?.let {
            countBins(it)
        }
    }

    @PublishedApi
    internal fun countBins(bins: Bins2D) {
        when(bins) {
            is Bins2D.ByNumber -> bins(bins.numberX to bins.numberY)
            is Bins2D.ByWidth -> binWidth(bins.widthX to bins.widthY)
        }
    }
}