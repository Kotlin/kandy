package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.OrderDirection
import org.jetbrains.kotlinx.ggdsl.letsplot.facet.facetGrid
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.position.position
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.linetype.LineType
import jetbrains.letsPlot.intern.toSpec
import kotlin.test.Test
import kotlin.test.assertEquals

class ToLetsPlotTest {
    @Test
    fun testSimple(){
        val plot = plot {
            x(source<String>("origin"))
            points {
                y(source<Double>("mpg").scaled(continuousPos(limits = 1.0 to 5.0)))
                color(Color.RED)
            }
        }
      //  println(plot.toLestPlot().toSpec())
        assertEquals(
            mapOf<String, Any>(
                "mapping" to mapOf<String, String>(),
                "data" to mapOf<String, Any>(),
                "kind" to "plot",
                "scales" to listOf<Map<String, Any>>(
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
    fun testComplex(){
        val clM = source<Int>("clM")
        val plot = plot {
            x(source<Double>("time").scaled(continuousPos(limits = -12.0 to 4.4)))
            y(source<String>("svalue").scaled(categoricalPos(categories = listOf("A", "B", "C"))))

            bars {
                color(clM.scaled(categorical(
                    rangeValues = listOf(Color.RED, Color.fromHex("#bb11aa"))
                )))
                width(0.5)
                alpha(0.8)

                position = Position.Stack
            }
            line {
                width(2.2)
                lineType(LineType.DOTTED)
                position = Position.Identity
            }

            facetGrid {
                x(clM)
                y(source<String>("clX"))
                yOrder = OrderDirection.DESCENDING
            }
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
                        "geom" to "path",
                    ),
                ),
                "facet" to mapOf<String, Any>(
                    "name" to "grid",
                    "x" to "clM",
                    "y" to "clX",
                    "x_order" to 1.0,
                    "y_order" to -1.0
                )
            ),
            plot.toLetsPlot().toSpec()
        )
    }
}