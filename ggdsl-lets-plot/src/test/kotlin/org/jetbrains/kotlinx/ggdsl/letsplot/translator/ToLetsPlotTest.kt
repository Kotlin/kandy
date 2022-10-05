package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.OrderDirection
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.facetGrid
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.bar
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.line
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.points
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.position.position
import org.jetbrains.kotlinx.ggdsl.letsplot.util.linetype.LineType
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.letsplot.x
import org.jetbrains.kotlinx.ggdsl.letsplot.y
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.letsPlot.intern.toSpec
import kotlin.test.Test
import kotlin.test.assertEquals

class ToLetsPlotTest {
    private val emptyDataset = NamedData(mapOf())
    @Test
    fun testSimple() {
        val plot = plot(emptyDataset) {
            x(columnPointer<String>("origin"))
            points {
                y(columnPointer<Double>("mpg").scaled(continuousPos(limits = 1.0 to 5.0)))
                symbol(Symbol.CIRCLE_FILLED)
                fillColor(Color.RED)
            }
        }

        assertEquals(
            mapOf<String, Any>(
                "mapping" to mapOf<String, String>(),
                "data" to mapOf<String, Any>(),
                "kind" to "plot",
                "scales" to listOf<Map<String, Any>>(
                    mapOf(
                        "aesthetic" to "x",
                    ),
                    mapOf(
                        "aesthetic" to "y",
                        "limits" to listOf(1.0, 5.0)
                    )
                ),
                "layers" to listOf(
                    mapOf<String, Any>(
                        "mapping" to mapOf<String, String>(
                            "x" to "origin",
                            "y" to "mpg"
                        ),
                        "stat" to "identity",
                        "data" to mapOf<String, Any>(),
                        "shape" to 21.0,
                        "position" to "identity",
                        "geom" to "point",
                        "fill" to "red"
                    )
                ),
            ),
            plot.toLetsPlot().toSpec()
        )
    }

    @Test
    fun testComplex() {
        val clM = columnPointer<Int>("clM")
        val plot = plot(emptyDataset) {
            x(columnPointer<Double>("time").scaled(continuousPos(limits = -12.0 to 4.4)))
            y(columnPointer<String>("svalue").scaled(categoricalPos(categories = listOf("A", "B", "C"))))

            bar {
                color(
                    clM.scaled(
                        categorical(
                            rangeValues = listOf(Color.RED, Color.fromHex("#bb11aa"))
                        )
                    )
                )
                width(0.5)
                alpha(0.8)

                position = Position.Stack
            }
            line {
                width(2.2)
                type(LineType.DOTTED)
                position = Position.Identity
            }

            facetGrid(
                x = clM,
                y = columnPointer<String>("clX"),
                yOrder = OrderDirection.DESCENDING
            )
        }
        println(plot.toLetsPlot().toSpec())
        assertEquals(
            mapOf<String, Any>(
                "mapping" to mapOf<String, String>(),
                "data" to mapOf<String, Any>(),
                "kind" to "plot",
                "scales" to listOf<Map<String, Any>>(
                    mapOf(
                        "aesthetic" to "x",
                        "limits" to listOf(-12.0, 4.4)
                    ),
                    mapOf(
                        "aesthetic" to "y",
                        "limits" to listOf("A", "B", "C")
                    ),
                    mapOf(
                        "aesthetic" to "fill",
                        "values" to listOf("red", "#bb11aa")
                    ),
                    mapOf(
                        "aesthetic" to "x",
                        "limits" to listOf(-12.0, 4.4)
                    ),
                    mapOf(
                        "aesthetic" to "y",
                        "limits" to listOf("A", "B", "C")
                    ),
                ),
                "layers" to listOf(
                    mapOf<String, Any>(
                        "mapping" to mapOf<String, String>(
                            "x" to "time",
                            "y" to "svalue",
                            "fill" to "clM"
                        ),
                        "stat" to "identity",
                        "data" to mapOf<String, Any>(),
                        "alpha" to 0.8,
                        "width" to 0.5,
                        "position" to "stack",
                        "geom" to "bar",
                        "data_meta" to mapOf<String, Any>(
                            "mapping_annotations" to
                                    listOf(
                                        mapOf<String, Any>(
                                            "aes" to "y",
                                            "annotation" to "as_discrete",
                                            "parameters" to mapOf<String, Any?>(
                                                "label" to "svalue",
                                                "order_by" to null,
                                                "order" to null
                                            )
                                        ),
                                        mapOf<String, Any>(
                                            "aes" to "fill",
                                            "annotation" to "as_discrete",
                                            "parameters" to mapOf<String, Any?>(
                                                "label" to "clM",
                                                "order_by" to null,
                                                "order" to null
                                            )
                                        )
                                    )
                        )
                    ),
                    mapOf<String, Any>(
                        "mapping" to mapOf<String, String>(
                            "x" to "time",
                            "y" to "svalue",
                        ),
                        "stat" to "identity",
                        "data" to mapOf<String, Any>(),
                        "size" to 2.2,
                        "linetype" to "dotted",
                        "position" to "identity",
                        "geom" to "line",
                        "data_meta" to mapOf<String, Any>(
                            "mapping_annotations" to
                                    listOf(
                                        mapOf<String, Any>(
                                            "aes" to "y",
                                            "annotation" to "as_discrete",
                                            "parameters" to mapOf<String, Any?>(
                                                "label" to "svalue",
                                                "order_by" to null,
                                                "order" to null
                                            )
                                        )
                                    )
                        )
                    ),
                ),
                "facet" to mapOf<String, Any>(
                    "name" to "grid",
                    "x" to "clM",
                    "y" to "clX",
                    "x_order" to 1.0,
                    "y_order" to -1.0
                ),
            ),
            plot.toLetsPlot().toSpec()
        )
    }
}
