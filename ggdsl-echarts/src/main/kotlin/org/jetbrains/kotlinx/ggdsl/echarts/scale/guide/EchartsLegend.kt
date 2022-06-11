package org.jetbrains.kotlinx.ggdsl.echarts.scale.guide


import org.jetbrains.kotlinx.ggdsl.ir.scale.guide.Legend


class EchartsLegend<DomainType: Any, RangeType: Any>: Legend {
    var name: String? = null
    var show: Boolean? = null
    var calculable: Boolean? = null // todo customize ? move to upper context???
    //var breaks: List<DomainType>? = null
    //var labels: List<String>? = null // todo pair list and format
    // todo expand & trans
}
/*
inline fun<DomainType : Any, RangeType : Any> NonPositionalScaleContext<DomainType, RangeType>.
        legend(block: EchartsLegend<DomainType, RangeType>.() -> Unit) {
    legend = EchartsLegend<DomainType, RangeType>().apply(block)
}

 */
