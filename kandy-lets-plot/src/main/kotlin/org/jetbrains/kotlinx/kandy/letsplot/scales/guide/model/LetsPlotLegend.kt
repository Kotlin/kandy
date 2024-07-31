/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model

import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.LegendType
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext


// TODO(https://github.com/Kotlin/kandy/issues/410)
/**
 * Legend, i.e. guide for non-positional scale.
 *
 * @property name legend name.
 * @property type legend type.
 */
public data class Legend<DomainType, out RangeType> @PublishedApi internal constructor(
    var name: String? = null,
    var type: LegendType? = null,
    internal var breaks: List<DomainType>? = null,
    internal var labels: List<String>? = null,
    internal var format: String? = null,
) : SelfInvocationContext, ScaleParameters {


    /**
     * Sets legend breaks with formatting.
     *
     * @param breaks list of breaks.
     * @param format format string.
     */
    public fun breaks(breaks: List<DomainType>? = null, format: String? = null) {
        this.breaks = breaks
        this.format = format
    }

    /**
     * Sets legend breaks with labels.
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
        // TODO(https://github.com/Kotlin/kandy/issues/416)
        this.breaks = breaks
        this.labels = labels
    }
}

