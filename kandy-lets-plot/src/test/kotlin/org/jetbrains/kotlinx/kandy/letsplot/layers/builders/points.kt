package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.POINT
import org.jetbrains.kotlinx.kandy.letsplot.settings.Symbol
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Suppress("INVISIBLE_MEMBER")
class PointsTests {
    private val xAxis = listOf(0.5).toColumn("xAxis")
    private val yAxis = listOf("first").toColumn("yAxis")
    private val symbol = listOf("dot").toColumn("symbol")
    private val color = listOf("black").toColumn("color")
    private val fillColor = listOf("blue").toColumn("fillColor")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val size = listOf(0.9).toColumn("size")
    private val stroke = listOf(1.9).toColumn("stroke")

    private val df = dataFrameOf(xAxis, yAxis, symbol, color, fillColor, alpha, size, stroke)

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: PointsBuilder

    @BeforeTest
    fun setUp() {
        builder = PointsBuilder(parentBuilder)
    }

    @Test
    fun `geom is POINT`() {
        assertEquals(POINT, builder.geom)
    }

    @Test
    fun `requiredAes contains X and Y`() {
        assertTrue(builder.requiredAes.contains(X))
        assertTrue(builder.requiredAes.contains(Y))
    }

    @Test
    fun `color const for points`() {
        builder.color = Color.YELLOW
        assertEquals(Color.YELLOW, (builder.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for points`() {
        builder.color("color")
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for points`() {
        builder.color(color)
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for points`() {
        builder.color(listOf("red"))
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor const for points`() {
        builder.fillColor = Color.BLUE
        assertEquals(Color.BLUE, (builder.bindingCollector.settings[FILL] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `fillColor str mapping for points`() {
        builder.fillColor("fillColor")
        assertEquals("fillColor", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor dataColumn mapping for points`() {
        builder.fillColor(fillColor)
        assertEquals("fillColor", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor iterable mapping for points`() {
        builder.fillColor(listOf("red", "white"))
        assertEquals("fill", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `symbol const for points`() {
        builder.symbol = Symbol.ASTERIX
        assertEquals(Symbol.ASTERIX, (builder.bindingCollector.settings[SHAPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `symbol str mapping for points`() {
        builder.symbol("symbol")
        assertEquals("symbol", (builder.bindingCollector.mappings[SHAPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `symbol dataColumn mapping for points`() {
        builder.symbol(symbol)
        assertEquals("symbol", (builder.bindingCollector.mappings[SHAPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `symbol iterable mapping for points`() {
        builder.symbol(listOf("diamond"))
        assertEquals("shape", (builder.bindingCollector.mappings[SHAPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for points`() {
        builder.alpha = 0.1
        assertEquals(0.1, (builder.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for points`() {
        builder.alpha("alpha")
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for points`() {
        builder.alpha(alpha)
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for points`() {
        builder.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size const for points`() {
        builder.size = 3.1
        assertEquals(3.1, (builder.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `size str mapping for points`() {
        builder.size("size")
        assertEquals("size", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size dataColumn mapping for points`() {
        builder.size(size)
        assertEquals("size", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size iterable mapping for points`() {
        builder.size(listOf(5, 10))
        assertEquals("size", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `stroke const for points`() {
        builder.stroke = 3
        assertEquals(3.0, (builder.bindingCollector.settings[STROKE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `stroke str mapping for points`() {
        builder.stroke("stroke")
        assertEquals("stroke", (builder.bindingCollector.mappings[STROKE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `stroke dataColumn mapping for points`() {
        builder.stroke(stroke)
        assertEquals("stroke", (builder.bindingCollector.mappings[STROKE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `stroke iterable mapping for points`() {
        builder.stroke(listOf(1.5, 8.0))
        assertEquals("stroke", (builder.bindingCollector.mappings[STROKE] as NonPositionalMapping<*, *>).columnID)
        println(((builder.bindingCollector.mappings[STROKE] as NonPositionalMapping<*, *>).parameters as NonPositionalMappingParameters).scale)

    }

    @Test
    fun `x const for points`() {
        builder.x.constant(5.0)
        assertEquals(X, (builder.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(5.0, (builder.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `y const for points`() {
        builder.y.constant(10)
        assertEquals(Y, (builder.bindingCollector.settings[Y] as PositionalSetting<*>).aes)
        assertEquals(10, (builder.bindingCollector.settings[Y] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for points`() {
        builder.x(xAxis) {
            axis {
                name = "x axis"
            }
            scale = continuous(0.1..3.7)
        }

        assertEquals(X, builder.bindingCollector.mappings[X]?.aes)
        assertEquals("xAxis", builder.bindingCollector.mappings[X]?.columnID)
        assertEquals(.1, (builder.bindingCollector.mappings[X]?.parameters?.scale as PositionalContinuousScale<*>).min)
        assertEquals(3.7, (builder.bindingCollector.mappings[X]?.parameters?.scale as PositionalContinuousScale<*>).max)
    }

    @Test
    fun `y for points`() {
        builder.y(yAxis) {
            axis {
                name = "x axis"
            }
            scale = categorical(listOf("one", "two"))
        }

        assertEquals(Y, builder.bindingCollector.mappings[Y]?.aes)
        assertEquals("yAxis", builder.bindingCollector.mappings[Y]?.columnID)
        assertEquals(
            listOf("one", "two"),
            (builder.bindingCollector.mappings[Y]?.parameters?.scale as PositionalCategoricalScale<*>).categories
        )
    }
}