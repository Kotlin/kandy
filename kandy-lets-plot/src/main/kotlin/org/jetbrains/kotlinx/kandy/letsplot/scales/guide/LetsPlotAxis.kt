/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.scales.guide

// import org.jetbrains.kotlinx.kandy.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

// todo separate model and context
/**
 * Axis, i.e. guide for positional scale.
 *
 * @property name name of axis.
 */
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
    public fun breaksLabeled(vararg breaksToLabels: Pair<DomainType, String>) {
        breaks = breaksToLabels.map { it.first }
        labels = breaksToLabels.map { it.second }
    }

    /**
     * Sets legend breaks with labels.
     *
     * @param breaks list of breaks.
     * @param labels list of corresponding labels.
     */
    public fun breaksLabeled(breaks: List<DomainType>, labels: List<String>) {
        // todo check equal sizes
        this.breaks = breaks
        this.labels = labels
    }

}
