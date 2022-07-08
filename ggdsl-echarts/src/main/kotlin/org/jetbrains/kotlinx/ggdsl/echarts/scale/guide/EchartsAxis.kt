package org.jetbrains.kotlinx.ggdsl.echarts.scale.guide


class Axis<DomainType : Any> {
    var show: Boolean? = true
    var name: String? = null
}


inline operator fun <DomainType : Any> Axis<DomainType>.invoke(block: Axis<DomainType>.() -> Unit) {
    apply(block)
}
