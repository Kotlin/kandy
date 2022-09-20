/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker

@PlotDslMarker
data class Axis<DomainType : Any>(
    var name: String? = null,
    var breaks: List<DomainType>? = null,
    var labels: List<String>? = null, // todo pair list and format
    var format: String? = null
    // fun overload?
    // breaks(.... format = ) / labeledBreaks(0.0 to "0", 0.4 to ".4" ...)
    // todo expand & trans
)

inline operator fun <DomainType : Any> Axis<DomainType>.invoke(block: Axis<DomainType>.() -> Unit) {
    apply(block)
}

