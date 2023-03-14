/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide

// import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext

//todo
/*@PlotDslMarker*/
//@Serializable
public data class Axis<DomainType> @PublishedApi internal constructor(
    var name: String? = null,
    @PublishedApi
internal var breaks: List<DomainType>? = null,
@PublishedApi
internal var labels: List<String>? = null,
@PublishedApi
internal var format: String? = null,
    // todo expand & trans
) : SelfInvocationContext, ScaleParameters {


    /**
     * Sets axis breaks with formatting.
     *
     * @param breaks list of breaks.
     * @param format format string.
     */
    public fun breaks(breaks: List<DomainType>? = null, format: String? = null) {
        this.breaks = breaks
        this.format = format
    }

    /**
     * Sets axis breaks with labels.
     *
     * @param breaksToLabels list of breaks with corresponding labels.
     */
    public fun breaksLabeled(vararg breaksToLabels: Pair<DomainType & Any, String>) {
        breaks = breaksToLabels.map { it.first }
        labels = breaksToLabels.map { it.second }
    }


}
