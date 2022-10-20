/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.NamedData
import org.jetbrains.kotlinx.ggdsl.dsl.columnPointer
import org.jetbrains.kotlinx.ggdsl.dsl.plot
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FacetGridFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.OrderDirection
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.facetGrid
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.facetGridX
import kotlin.test.Test
import kotlin.test.assertEquals

class FacetTest {
    private val emptyDataset = NamedData(mapOf())
    @Test
    fun testSimpleFacet() {
        val plot = plot(emptyDataset) {
            facetGridX(
                x = columnPointer<String>("xSrc")
            )
        }
        assertEquals(
            Plot(
                emptyDataset,
                listOf(),
                mapOf(),
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
                )
            ),
            plot
        )
    }

    @Test
    fun testComplexFacet() {
        val plot = plot(emptyDataset) {
            facetGrid(
                x = columnPointer<String>("xArg"),
                y = columnPointer<Int>("yArg"),
                xOrder = OrderDirection.ASCENDING,
                yOrder = OrderDirection.DESCENDING,
            )
        }
        assertEquals(
            Plot(
                emptyDataset,
                listOf(),
                mapOf(),
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
                )
            ),
            plot
        )
    }
}
