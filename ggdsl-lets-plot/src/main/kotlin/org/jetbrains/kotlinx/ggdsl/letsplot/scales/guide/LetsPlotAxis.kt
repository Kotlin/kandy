package org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker

@PlotDslMarker
public data class Axis<DomainType : Any>(
    var name: String? = null,
    var breaks: List<DomainType>? = null,
    var labels: List<String>? = null, // todo pair list and format
    var format: String? = null
    // fun overload?
    // breaks(.... format = ) / labeledBreaks(0.0 to "0", 0.4 to ".4" ...)
    // todo expand & trans
)

public inline operator fun <DomainType : Any> Axis<DomainType>.invoke(block: Axis<DomainType>.() -> Unit) {
    apply(block)
}

