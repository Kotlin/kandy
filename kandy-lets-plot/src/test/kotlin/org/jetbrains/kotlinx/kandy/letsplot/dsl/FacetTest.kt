/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.dsl

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.letsplot.facet.OrderDirection
import org.jetbrains.kotlinx.kandy.letsplot.facet.facetGrid
import org.jetbrains.kotlinx.kandy.letsplot.facet.facetGridX
import org.jetbrains.kotlinx.kandy.letsplot.facet.feature.FacetGridFeature
import kotlin.test.Test
import kotlin.test.assertEquals

class FacetTest {
    @Test
    fun testSimpleFacet() {
        val dataset: Map<String, List<*>> = mapOf(
            "xSrc" to listOf(1, 2, 3)
        )
        val plot = plot(dataset) {
            facetGridX(
                x = column<String>("xSrc")
            )
        }
        assertEquals(
            Plot(
                listOf(NamedData(dataset.toDataFrame())),
                listOf(),
                emptyMap(),
                emptyMap(),
                mapOf(
                    FacetGridFeature.FEATURE_NAME to FacetGridFeature(
                        "xSrc",
                        null,
                        null,
                        OrderDirection.ASCENDING,
                        OrderDirection.ASCENDING,
                        null,
                        null
                    )
                ),
                emptyMap(),
            ),
            plot
        )
    }

    @Test
    fun testComplexFacet() {
        val dataset: Map<String, List<*>> = mapOf(
            "xArg" to listOf(1, 2, 3),
            "yArg" to listOf(0.1, 0.2, 0.3)
        )
        val plot = plot(dataset) {
            facetGrid(
                x = column<String>("xArg"),
                y = column<Int>("yArg"),
                xOrder = OrderDirection.ASCENDING,
                yOrder = OrderDirection.DESCENDING,
            )
        }
        assertEquals(
            Plot(
                listOf(NamedData(dataset.toDataFrame())),
                listOf(),
                emptyMap(),
                emptyMap(),
                mapOf(
                    FacetGridFeature.FEATURE_NAME to FacetGridFeature(
                        x = "xArg",
                        y = "yArg",
                        null,
                        xOrder = OrderDirection.ASCENDING,
                        yOrder = OrderDirection.DESCENDING,
                        null,
                        null,
                    )
                ),
                emptyMap()
            ),
            plot
        )
    }
}
