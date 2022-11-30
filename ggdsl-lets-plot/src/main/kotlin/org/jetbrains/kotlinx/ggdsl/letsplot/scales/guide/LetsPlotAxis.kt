/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext

@PlotDslMarker
public data class Axis<DomainType : Any>(
    var name: String? = null,
    // todo expand & trans
) : SelfInvocationContext {
    internal var breaks: List<DomainType>? = null
    internal var labels: List<String>? = null
    internal var format: String? = null

    public fun breaks(breaks: List<DomainType>?, format: String?) {
        this.breaks = breaks
        this.format = format
    }

    public fun breaksLabeled(breaksToLabels: List<Pair<DomainType, String>>) {
        breaks = breaksToLabels.map { it.first }
        labels = breaksToLabels.map { it.second }
    }
}
