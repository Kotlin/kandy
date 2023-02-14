/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide

import kotlinx.serialization.Serializable
// import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.ir.data.TypedList
import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext
import kotlin.reflect.KType
import kotlin.reflect.typeOf

//todo
/*@PlotDslMarker*/
//@Serializable
public data class Axis<DomainType> @PublishedApi internal constructor(
    var kType: KType,
    // todo expand & trans
) : SelfInvocationContext {
    var name: String? = null
    @PublishedApi
    internal var breaks: TypedList? = null
    @PublishedApi
    internal var labels: List<String>? = null
    @PublishedApi
    internal var format: String? = null

    /**
     * Sets axis breaks with formatting.
     *
     * @param breaks list of breaks.
     * @param format format string.
     */
    public fun breaks(breaks: List<DomainType & Any>? = null, format: String? = null) {
        this.breaks = breaks?.let {
            TypedList(kType, it)
        }
        this.format = format
    }

    /**
     * Sets axis breaks with labels.
     *
     * @param breaksToLabels list of breaks with corresponding labels.
     */
    public fun breaksLabeled(breaksToLabels: List<Pair<DomainType & Any, String>>) {
        breaks = TypedList(kType, breaksToLabels.map { it.first })
        labels = breaksToLabels.map { it.second }
    }

    public companion object {
        @PublishedApi
        internal inline fun<reified DomainType> create(): Axis<DomainType> = Axis(typeOf<DomainType>())
    }
}
