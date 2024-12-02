@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.POLYGON
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PolygonTests {
    private val xAxis = listOf(0.0, 1.0, 2.0).toColumn("xAxis")
    private val yAxis = listOf("A", "B", "C").toColumn("yAxis")
    private val fillColor = listOf("green", "blue", "red").toColumn("fillColor")
    private val alpha = listOf(0.3, 0.7, 0.5).toColumn("alpha")
    private val borderColor = listOf("black", "gray", "white").toColumn("borderColor")
    private val borderSize = listOf(1.0, 2.0, 1.5).toColumn("borderSize")

    private val df = dataFrameOf(xAxis, yAxis, fillColor, alpha, borderColor, borderSize)

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: PolygonBuilder

    @BeforeTest
    fun setUp() {
        builder = PolygonBuilder(parentBuilder)
    }

    @Test
    fun `geom is POLYGON`() {
        assertEquals(POLYGON, builder.geom)
    }

    @Test
    fun `requiredAes contains X and Y`() {
        assertTrue(builder.requiredAes.contains(X))
        assertTrue(builder.requiredAes.contains(Y))
    }

    @Test
    fun `fillColor const for polygons`() {
        builder.fillColor = Color.GREEN
        assertEquals(Color.GREEN, (builder.bindingCollector.settings[FILL] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `fillColor str mapping for polygons`() {
        builder.fillColor("fillColor")
        assertEquals("fillColor", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor dataColumn mapping for polygons`() {
        builder.fillColor(fillColor)
        assertEquals("fillColor", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor iterable mapping for polygons`() {
        builder.fillColor(listOf("red", "blue", "green"))
        assertEquals("fill", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for polygons`() {
        builder.alpha = 0.5
        assertEquals(0.5, (builder.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for polygons`() {
        builder.alpha("alpha")
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for polygons`() {
        builder.alpha(alpha)
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `borderColor const for polygons`() {
        builder.borderLine.color = Color.BLACK
        assertEquals(Color.BLACK, (builder.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `borderColor str mapping for polygons`() {
        builder.borderLine.color("borderColor")
        assertEquals("borderColor", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `borderColor dataColumn mapping for polygons`() {
        builder.borderLine.color(borderColor)
        assertEquals("borderColor", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `borderSize const for polygons`() {
        builder.borderLine.width = 2.0
        assertEquals(2.0, (builder.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `borderSize str mapping for polygons`() {
        builder.borderLine.width("borderSize")
        assertEquals("borderSize", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `borderSize dataColumn mapping for polygons`() {
        builder.borderLine.width(borderSize)
        assertEquals("borderSize", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `x const for polygons`() {
        builder.x.constant(0.5)
        assertEquals(X, (builder.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(0.5, (builder.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `y const for polygons`() {
        builder.y.constant("B")
        assertEquals(Y, (builder.bindingCollector.settings[Y] as PositionalSetting<*>).aes)
        assertEquals("B", (builder.bindingCollector.settings[Y] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for polygons`() {
        builder.x(xAxis) {
            axis {
                name = "Polygon X Axis"
            }
            scale = continuous(0.0..2.0)
        }

        assertEquals(X, builder.bindingCollector.mappings[X]?.aes)
        assertEquals("xAxis", builder.bindingCollector.mappings[X]?.columnID)
        assertEquals(0.0, (builder.bindingCollector.mappings[X]?.parameters?.scale as PositionalContinuousScale<*>).min)
        assertEquals(2.0, (builder.bindingCollector.mappings[X]?.parameters?.scale as PositionalContinuousScale<*>).max)
    }

    @Test
    fun `y for polygons`() {
        builder.y(yAxis) {
            axis {
                name = "Polygon Y Axis"
            }
            scale = categorical(listOf("A", "B", "C"))
        }

        assertEquals(Y, builder.bindingCollector.mappings[Y]?.aes)
        assertEquals("yAxis", builder.bindingCollector.mappings[Y]?.columnID)
        assertEquals(
            listOf("A", "B", "C"),
            (builder.bindingCollector.mappings[Y]?.parameters?.scale as PositionalCategoricalScale<*>).categories
        )
    }
}
