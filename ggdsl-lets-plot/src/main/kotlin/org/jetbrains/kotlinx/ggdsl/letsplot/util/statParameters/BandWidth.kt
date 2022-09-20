package org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters

public interface BandWidth : SimpleValueWrapper {
    public data class ByValue(override val value: Double) : BandWidth

    // todo or ByMethod???
    public object NRD0 : BandWidth {
        override val value: String = "nrd0"
    }

    public object NRD : BandWidth {
        override val value: String = "nrd"
    }
}
