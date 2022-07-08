package org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide


data class Axis<DomainType : Any>(
    var name: String? = null,
    var breaks: List<DomainType>? = null,
    var labels: List<String>? = null // todo pair list and format
    // fun overolad
    // breaks(.... format = ) / labeledBreaks(0.0 to "0", 0.4 to ".4" ...)
    // todo expand & trans
)

inline operator fun <DomainType : Any> Axis<DomainType>.invoke(block: Axis<DomainType>.() -> Unit) {
    apply(block)
}

