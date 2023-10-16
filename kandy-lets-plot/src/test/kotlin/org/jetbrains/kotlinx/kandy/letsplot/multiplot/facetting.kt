package org.jetbrains.kotlinx.kandy.letsplot.multiplot

import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.feature.FacetGridFeature
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.feature.FacetWrapFeature
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.*
import kotlin.test.Test
import kotlin.test.assertEquals

class FacetTests {

    private val col1 by columnOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).named("col1")
    private val col2 by columnOf(11, 12, 13, 14, 15, 16, 17, 18, 19, 20).named("col2")
    private val col3 by columnOf("A", "A", "B", "B", "C", "C", "D", "D", "E", "E").named("col3")

    private val dataFrame = dataFrameOf(col1, col2, col3)

    @Test
    fun `test facetGridX with default parameters`() {
        val plot = plot(dataFrame) {
            line { x(col1);y(col2) }
            facetGridX(col3)
        }

        val expectedFacet = FacetGridFeature(
            x = "col3", y = null,
            scalesSharing = null,
            xOrder = OrderDirection.ASCENDING, yOrder = OrderDirection.ASCENDING,
            xFormat = null, yFormat = null
        )

        assertEquals(expectedFacet, plot.features[FacetGridFeature.FEATURE_NAME])
    }

    @Test
    fun `test facetGridX with custom parameters`() {
        val plot = plot(dataFrame) {
            line { x(col1);y(col2) }
            facetGridX(col3, ScalesSharing.FREE_X, OrderDirection.DESCENDING, "'Score: {}'")
        }

        val expectedFacet = FacetGridFeature(
            x = "col3", y = null,
            scalesSharing = ScalesSharing.FREE_X,
            xOrder = OrderDirection.DESCENDING, yOrder = OrderDirection.ASCENDING,
            xFormat = "'Score: {}'", yFormat = null
        )

        assertEquals(expectedFacet, plot.features[FacetGridFeature.FEATURE_NAME])
    }

    @Test
    fun `test facetGridY with default parameters`() {
        val plot = plot(dataFrame) {
            line { x(col1);y(col2) }
            facetGridY(col3)
        }

        val expectedFacet = FacetGridFeature(
            x = null, y = "col3",
            scalesSharing = null,
            xOrder = OrderDirection.ASCENDING, yOrder = OrderDirection.ASCENDING,
            xFormat = null, yFormat = null
        )

        assertEquals(expectedFacet, plot.features[FacetGridFeature.FEATURE_NAME])
    }

    @Test
    fun `test facetGridY with custom parameters`() {
        val plot = plot(dataFrame) {
            line { x(col1);y(col2) }
            facetGridY(col3, ScalesSharing.FIXED, OrderDirection.DESCENDING, "'Score: {.2f}'")
        }

        val expectedFacet = FacetGridFeature(
            x = null, y = "col3",
            scalesSharing = ScalesSharing.FIXED,
            xOrder = OrderDirection.ASCENDING, yOrder = OrderDirection.DESCENDING,
            xFormat = null, yFormat = "'Score: {.2f}'"
        )

        assertEquals(expectedFacet, plot.features[FacetGridFeature.FEATURE_NAME])
    }

    @Test
    fun `test facetGrid with default parameters`() {
        val plot = plot(dataFrame) {
            line { x(col1);y(col2) }
            facetGrid(col2, col3)
        }

        val expectedFacet = FacetGridFeature(
            x = "col2", y = "col3",
            scalesSharing = null,
            xOrder = OrderDirection.ASCENDING, yOrder = OrderDirection.ASCENDING,
            xFormat = null, yFormat = null
        )

        assertEquals(expectedFacet, plot.features[FacetGridFeature.FEATURE_NAME])
    }

    @Test
    fun `test facetGrid with custom parameters`() {
        val plot = plot(dataFrame) {
            line { x(col1);y(col2) }
            facetGrid(
                col1, col3,
                ScalesSharing.FIXED,
                OrderDirection.DESCENDING, OrderDirection.ASCENDING,
                null, "'Score: {.2f}'"
            )
        }

        val expectedFacet = FacetGridFeature(
            x = "col1", y = "col3",
            scalesSharing = ScalesSharing.FIXED,
            xOrder = OrderDirection.DESCENDING, yOrder = OrderDirection.ASCENDING,
            xFormat = null, yFormat = "'Score: {.2f}'"
        )

        assertEquals(expectedFacet, plot.features[FacetGridFeature.FEATURE_NAME])
    }

    @Test
    fun `test facetWrap with default parameters`() {
        val plot = plot(dataFrame) {
            line { x(col1);y(col2) }
            facetWrap(nCol = 3, scalesSharing = ScalesSharing.FREE) {
                facet(col1)
                facet(col2)
                facet(col3)
            }
        }

        val expectedFacet = FacetWrapFeature(
            facets = listOf("col1", "col2", "col3"),
            nCol = 3, nRow = null,
            orders = listOf(OrderDirection.ASCENDING, OrderDirection.ASCENDING, OrderDirection.ASCENDING),
            scalesSharing = ScalesSharing.FREE,
            direction = Direction.HORIZONTAL,
            formats = listOf(null, null, null)
        )

        assertEquals(expectedFacet, plot.features[FacetWrapFeature.FEATURE_NAME])
    }

    @Test
    fun `test facetWrap with custom parameters`() {
        val plot = plot(dataFrame) {
            facetWrap(nCol = 2, nRow = 2, scalesSharing = ScalesSharing.FIXED, direction = Direction.VERTICAL) {
                facet(col2, OrderDirection.DESCENDING, format = ".2f")
                facet(col3, format = "Score = {}")
                line { x(col1);y(col2) }
            }
        }
        plot.save("test_facet.png")

        val expectedFacet = FacetWrapFeature(
            facets = listOf("col2", "col3"),
            nCol = 2, nRow = 2,
            orders = listOf(OrderDirection.DESCENDING, OrderDirection.ASCENDING),
            scalesSharing = ScalesSharing.FIXED,
            direction = Direction.VERTICAL,
            formats = listOf(".2f", "Score = {}")
        )

        assertEquals(expectedFacet, plot.features[FacetWrapFeature.FEATURE_NAME])
    }


    @Test
    fun `simple facet test`() {
        val dataset: Map<String, List<*>> = mapOf(
            "xSrc" to listOf(1, 2, 3)
        )
        val plot = plot(dataset) {
            facetGridX(
                x = column<String>("xSrc")
            )
        }

        val expectedPlot = Plot(
            listOf(NamedData(dataset.toDataFrame())), emptyList(), emptyMap(), emptyMap(),
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
            emptyMap()
        )

        assertEquals(expectedPlot, plot)
    }

    @Test
    fun `complex facet test`() {
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

        val expectedPlot = Plot(
            listOf(NamedData(dataset.toDataFrame())), emptyList(), emptyMap(), emptyMap(),
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
        )
        assertEquals(expectedPlot, plot)
    }
}