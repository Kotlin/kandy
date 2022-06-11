package org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide

import org.jetbrains.kotlinx.ggdsl.ir.scale.guide.Axis

class LetsPlotAxis<DomainType: Any>: Axis {
    var name: String? = null
    var breaks: List<DomainType>? = null
    var labels: List<String>? = null // todo pair list and format
    // fun overolad
    // breaks(.... format = ) / labeledBreaks(0.0 to "0", 0.4 to ".4" ...)
    // todo expand & trans
}
/* TODO
fun<DomainType : Any> PositionalScaleContext<DomainType>.axis(block: LetsPlotAxis<DomainType>.() -> Unit) {
    axis = LetsPlotAxis<DomainType>().apply(block)
}

 */
