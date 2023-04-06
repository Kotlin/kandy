/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.stat
/*
import org.jetbrains.kotlinx.kandy.dsl.contexts.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.column.invoke
import org.jetbrains.kotlinx.kandy.letsplot.BinWidth2DAes
import org.jetbrains.kotlinx.kandy.letsplot.BinWidthAes
import org.jetbrains.kotlinx.kandy.letsplot.Bins2DAes
import org.jetbrains.kotlinx.kandy.letsplot.BinsAes
<<<<<<< HEAD

interface Bins {
    data class ByNumber internal constructor(val number: Int) : Bins
    data class ByWidth internal constructor(val width: Double) : Bins
=======

public interface Bins {
    public data class ByNumber internal constructor(val number: Int) : Bins
    public data class ByWidth internal constructor(val width: Double) : Bins
>>>>>>> main

    public companion object {
        public fun byNumber(number: Int): ByNumber = ByNumber(number)
        public fun byWidth(width: Double): ByWidth = ByWidth(width)
    }
}

<<<<<<< HEAD
abstract class WithBinsContext(bins: Bins?): LayerContext(parent) {
=======
public abstract class WithBinsContext(bins: Bins?) : LayerContext() {
>>>>>>> main
    private val bins get() = BinsAes(this)
    private val binWidth get() = BinWidthAes(this)

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

<<<<<<< HEAD
abstract class WithBins2DContext(bins: Bins2D?): LayerContext(parent) {
=======
public abstract class WithBins2DContext(bins: Bins2D?) : LayerContext() {
>>>>>>> main
    private val bins get() = Bins2DAes(this)
    private val binWidth get() = BinWidth2DAes(this)

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

 */