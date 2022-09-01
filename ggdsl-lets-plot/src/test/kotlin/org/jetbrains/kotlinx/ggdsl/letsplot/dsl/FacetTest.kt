package org.jetbrains.kotlinx.ggdsl.letsplot.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.plot
import org.jetbrains.kotlinx.ggdsl.dsl.source
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.FacetGridFeature
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.OrderDirection
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.facetGrid
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.facetGridX
import kotlin.test.Test
import kotlin.test.assertEquals

class FacetTest {
    @Test
    fun testSimpleFacet() {
        val plot = plot {
            facetGridX(
                x = source<String>("xSrc")
            )
        }
        assertEquals(
            Plot(
                mapOf(),
                listOf(),
                null,
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
        val plot = plot {
            facetGrid(
                x = source<String>("xArg"),
                y = source<Int>("yArg"),
                xOrder = OrderDirection.ASCENDING,
                yOrder = OrderDirection.DESCENDING,
            )
        }
        assertEquals(
            Plot(
                mapOf(),
                listOf(),
                null,
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
