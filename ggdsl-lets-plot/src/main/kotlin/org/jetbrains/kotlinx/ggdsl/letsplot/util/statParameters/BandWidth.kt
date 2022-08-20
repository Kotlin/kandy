package org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters

interface BandWidth : SimpleValueWrapper {
    data class ByValue(override val value: Double): BandWidth
    // todo or ByMethod???
    object NRD0: BandWidth {
        override val value = "nrd0"
    }
    object NRD: BandWidth {
        override val value = "nrd"
    }
}
