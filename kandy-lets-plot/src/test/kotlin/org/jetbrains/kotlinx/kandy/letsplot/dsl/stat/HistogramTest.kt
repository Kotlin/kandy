package org.jetbrains.kotlinx.kandy.letsplot.dsl.stat

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.stat.bin.Bins
import org.jetbrains.kotlinx.kandy.letsplot.stat.layers.histogram
import org.jetbrains.kotlinx.kandy.letsplot.translator.toLetsPlot
import org.jetbrains.letsPlot.intern.toSpec
import kotlin.test.Test
import kotlin.test.assertEquals

internal class HistogramTest {

    @Test
    fun testHist() {
        val df = dataFrameOf("vals" to List(10) { it })
        val histPlot = plot(df) {
            histogram("vals", Bins.byNumber(5))
        }

        assertEquals(
            mapOf<String, Any>(
                "mapping" to mapOf<String, Any>(),
                "data" to mapOf<String, Any>(),
                "kind" to "plot",
                "scales" to listOf(
                    mapOf(
                        "aesthetic" to "x",
                        "limits" to listOf(null, null)
                    ),
                    mapOf(
                        "aesthetic" to "y",
                        "limits" to listOf(null, null)
                    ),
                ),
                "layers" to listOf(
                    mapOf(
                        "mapping" to mapOf(
                            "x" to "STAT_BINS",
                            "y" to "STAT_COUNT"
                        ),
                        "stat" to "identity",
                        "data" to mapOf(
                            "STAT_COUNT" to listOf(2.0, 2.0, 2.0, 2.0, 2.0),
                            "STAT_BINS" to listOf(
                                -0.1080000000000001,
                                2.1959999999999997,
                                4.5,
                                6.8039999999999985,
                                9.107999999999999
                            )
                        ),
                        "sampling" to "none",
                        "position" to "dodge",
                        "geom" to "bar"
                    )
                )
            ), histPlot.toLetsPlot().toSpec()
        )
    }
}