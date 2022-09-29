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

public interface Bins {
    public data class ByNumber internal constructor(val number: Int) : Bins
    public data class ByWidth internal constructor(val width: Double) : Bins

    public companion object {
        public fun byNumber(number: Int): ByNumber = ByNumber(number)
        public fun byWidth(width: Double): ByWidth = ByWidth(width)
    }
}

public abstract class WithBinsContext(bins: Bins?) : LayerContext() {
    private val bins = BinsAes(this)
    private val binWidth = BinWidthAes(this)

    init {
        bins?.let {
            countBins(it)
        }
    }

    @PublishedApi
    internal fun countBins(bins: Bins) {
        when (bins) {
            is Bins.ByNumber -> bins(bins.number)
            is Bins.ByWidth -> binWidth(bins.width)
        }
    }
}

public interface Bins2D {
    public data class ByNumber internal constructor(val numberX: Int, val numberY: Int) : Bins2D
    public data class ByWidth internal constructor(val widthX: Double, val widthY: Double) : Bins2D

    public companion object {
        public fun byNumber(numberX: Int, numberY: Int): ByNumber = ByNumber(numberX, numberY)
        public fun byWidth(widthX: Double, widthY: Double): ByWidth = ByWidth(widthX, widthY)
    }
}

public abstract class WithBins2DContext(bins: Bins2D?) : LayerContext() {
    private val bins = Bins2DAes(this)
    private val binWidth = BinWidth2DAes(this)

    init {
        bins?.let {
            countBins(it)
        }
    }

    @PublishedApi
    internal fun countBins(bins: Bins2D) {
        when (bins) {
            is Bins2D.ByNumber -> bins(bins.numberX to bins.numberY)
            is Bins2D.ByWidth -> binWidth(bins.widthX to bins.widthY)
        }
    }
}