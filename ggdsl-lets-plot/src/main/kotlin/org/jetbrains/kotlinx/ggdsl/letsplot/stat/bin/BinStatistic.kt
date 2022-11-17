package org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin

import org.jetbrains.kotlinx.ggdsl.letsplot.stat.Statistic

public sealed interface BinStatistic<T>: Statistic<T> {
    // todo type
    public object Bins: BinStatistic<Double> {
        public const val NAME: String = "..x.."
        override val id: String = NAME

    }
    public object Count: BinStatistic<Int> {
        public const val NAME: String = "..count.."
        override val id: String = NAME

    }
    public object Density: BinStatistic<Double> {
        public const val NAME: String = "..density.."
        override val id: String = NAME

    }
}