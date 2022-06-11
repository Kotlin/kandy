package org.jetbrains.kotlinx.ggdsl.echarts.scale.guide

import org.jetbrains.kotlinx.ggdsl.ir.scale.guide.Axis

class EchartsAxis<DomainType: Any>: Axis {
    var show: Boolean? = true
    var name: String? = null
   // var breaks: List<DomainType>? = null
   // var labels: List<String>? = null // todo pair list and format
    // fun overolad
    // breaks(.... format = ) / labeledBreaks(0.0 to "0", 0.4 to ".4" ...)
    // todo
}
/*
fun<DomainType : Any> PositionalScaleContext<DomainType>.axis(block: EchartsAxis<DomainType>.() -> Unit) {
    axis = EchartsAxis<DomainType>().apply(block)
}

 */