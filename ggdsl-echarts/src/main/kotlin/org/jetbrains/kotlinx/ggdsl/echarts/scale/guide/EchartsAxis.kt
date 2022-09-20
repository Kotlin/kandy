package org.jetbrains.kotlinx.ggdsl.echarts.scale.guide


public class Axis<DomainType : Any> {
    public var show: Boolean? = true
    public var name: String? = null
}


public inline operator fun <DomainType : Any> Axis<DomainType>.invoke(block: Axis<DomainType>.() -> Unit) {
    apply(block)
}
