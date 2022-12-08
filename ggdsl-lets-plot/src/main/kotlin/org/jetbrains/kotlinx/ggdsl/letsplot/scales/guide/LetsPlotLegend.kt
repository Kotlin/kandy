/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext

public sealed interface LegendType

@Serializable
public class None internal constructor() : LegendType

@Serializable
public data class DiscreteLegend internal constructor(
    val nRow: Int? = null,
    val nCol: Int? = null,
    val byRow: Boolean? = null
) : LegendType

@Serializable
public data class ColorBar internal constructor(
    val barWidth: Double? = null,
    val barHeight: Double? = null,
    val nBin: Int? = null
) : LegendType

// TODO
@Serializable
@PlotDslMarker
public data class Legend<DomainType : Any, out RangeType : Any>(
    var name: String? = null,
    // todo expand & trans
    var type: LegendType? = null,
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

public fun Legend<*, *>.none(): None = None()

public fun Legend<*, *>.discreteLegend(
    nRow: Int? = null,
    nCol: Int? = null,
    byRow: Boolean? = null
): DiscreteLegend = DiscreteLegend(nRow, nCol, byRow)

public fun Legend<*, Color>.colorBar(
    barWidth: Double? = null,
    barHeight: Double? = null,
    nBin: Int? = null
): ColorBar = ColorBar(barWidth, barHeight, nBin)
