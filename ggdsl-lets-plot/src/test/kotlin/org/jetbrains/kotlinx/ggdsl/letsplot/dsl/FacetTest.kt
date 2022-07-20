package org.jetbrains.kotlinx.ggdsl.letsplot.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.plot
import org.jetbrains.kotlinx.ggdsl.dsl.source
import org.jetbrains.kotlinx.ggdsl.old.DefaultLayout
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.*
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

class FacetTest {
    @Test
    fun testSimpleFacet() {
        val plot = plot {
            facetGrid {
                x(source<String>("xSrc"))
            }
        }
        assertEquals(
            Plot(
                mapOf(),
                listOf(),
                null,
                mapOf(
                    FacetGridFeature.FEATURE_NAME to FacetGridFeature().apply {
                        mappings[FACET_X] = DataSource<String>("xSrc", typeOf<String>())
                    }
                )
            ),
            plot
        )
    }

    @Test
    fun testComplexFacet() {
        val plot = plot {
            facetGrid {
                x(source<String>("xArg"))
                y(source<Int>("yArg"))
                xOrder = OrderDirection.ASCENDING
                yOrder = OrderDirection.DESCENDING
            }
        }
        assertEquals(
            Plot(
                mapOf(),
                listOf(),
                null,
                mapOf(
                    FacetGridFeature.FEATURE_NAME to FacetGridFeature( xOrder = OrderDirection.ASCENDING,
                            yOrder = OrderDirection.DESCENDING).apply {
                        mappings[FACET_X] = DataSource<String>("xArg", typeOf<String>())
                        mappings[FACET_Y] = DataSource<Int>("yArg", typeOf<Int>())

                    }
                )
            ),
            plot
        )
    }
}