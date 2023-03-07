package org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.letsplot.stat.Statistic

public sealed interface BinStatistic<T>: Statistic<T> {
    // todo types
    //@Serializable
    public object Bins: BinStatistic<Double> {
        public const val NAME: String = "..x.."
        override val id: String = NAME

    }

    //@Serializable
    public object Count: BinStatistic<Double> {
        public const val NAME: String = "..count.."
        override val id: String = NAME

    }
    //@Serializable
    public object Density: BinStatistic<Double> {
        public const val NAME: String = "..density.."
        override val id: String = NAME
    }
}