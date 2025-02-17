/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model

import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

// TODO(https://github.com/Kotlin/kandy/issues/410)
/**
 * Axis, i.e. guide for positional scale.
 *
 * @property name name of axis.
 * @property position position of axis.
 */
public data class Axis<DomainType> @PublishedApi internal constructor(
    var name: String? = null,
    var position: AxisPosition = AxisPosition.DEFAULT,
    var min: DomainType? = null,
    var max: DomainType? = null,
    @PublishedApi
    internal var breaks: List<DomainType>? = null,
    @PublishedApi
    internal var labels: List<String>? = null,
    @PublishedApi
    internal var format: String? = null,
    @PublishedApi
    internal var expand: List<Double>? = null,
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
     * @throws IllegalArgumentException if the size of breaks and labels do not match.
     */
    public fun breaksLabeled(breaks: List<DomainType>, labels: List<String>) {
        if (breaks.size != labels.size) {
            throw IllegalArgumentException(
                "The number of breaks (${breaks.size}) must match the number of labels (${labels.size})."
            )
        }
        this.breaks = breaks
        this.labels = labels
    }

    /**
     * Sets multiplicative and additive expansion constants.
     */
    public fun expand(multiplicative: Double = 0.0, additive: Double = 0.2) {
        expand = listOf(multiplicative, additive)
    }
}

public var <T : Comparable<T>> Axis<T>.limits: ClosedRange<T>?
    // TODO(https://github.com/Kotlin/kandy/issues/412)
    get() = null
    set(value) {
        min = value?.start
        max = value?.endInclusive
    }

@get:JvmName("limitsAny")
@set:JvmName("limitsAny")
public var Axis<Any?>.limits: ClosedRange<*>?
    // TODO(https://github.com/Kotlin/kandy/issues/412)
    get() = null
    set(value) {
        min = value?.start
        max = value?.endInclusive
    }
