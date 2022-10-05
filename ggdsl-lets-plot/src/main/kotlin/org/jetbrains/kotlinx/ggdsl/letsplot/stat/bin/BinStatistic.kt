package org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin

import org.jetbrains.kotlinx.ggdsl.letsplot.stat.Statistic

sealed interface BinStatistic<T>: Statistic<T> {
    // todo type
    object Bins: BinStatistic<Double> {
        val NAME = "..x.."
        override val name = NAME

    }
    object Count: BinStatistic<Int> {
        val NAME = "..count.."
        override val name = NAME

    }
    object Density: BinStatistic<Double> {
        val NAME = "..density.."
        override val name = NAME

    }
}