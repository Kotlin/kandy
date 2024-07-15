package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.TILE
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Suppress("INVISIBLE_MEMBER")
class TilesTests {
    private val xAxis = listOf(0.5).toColumn("xAxis")
    private val yAxis = listOf("first").toColumn("yAxis")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val color = listOf("blue").toColumn("color")
    private val width = listOf(1).toColumn("width")
    private val height = listOf(3).toColumn("height")

    private val df = dataFrameOf(xAxis, yAxis, alpha, color, width, height)

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: TilesBuilder

    @BeforeTest
    fun setUp() {
        builder = TilesBuilder(parentBuilder)
    }

    @Test
    fun `geom is TILE`() {
        assertEquals(TILE, builder.geom)
    }

    @Test
    fun `requiredAes contains X and Y`() {
        assertTrue(builder.requiredAes.contains(X))
        assertTrue(builder.requiredAes.contains(Y))
    }

    @Test
    fun `alpha const for tiles`() {
        builder.alpha = 0.1
        assertEquals(0.1, (builder.bindingCollector.settings[ALPHA] as? NonPositionalSetting<*>)?.value)
    }

    @Test
    fun `alpha str mapping for tiles`() {
        builder.alpha("alpha")
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `alpha dataColumn mapping for tiles`() {
        builder.alpha(alpha)
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `alpha iterable mapping for tiles`() {
        builder.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `width const for tiles`() {
        builder.width = 0.3
        assertEquals(0.3, (builder.bindingCollector.settings[WIDTH] as? NonPositionalSetting<*>)?.value)
    }

    @Test
    fun `width str mapping for tiles`() {
        builder.width("width")
        assertEquals("width", (builder.bindingCollector.mappings[WIDTH] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `width dataColumn mapping for tiles`() {
        builder.width(width)
        assertEquals("width", (builder.bindingCollector.mappings[WIDTH] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `width iterable mapping for tiles`() {
        builder.width(listOf(0.2, 0.5, .1))
        assertEquals("width", (builder.bindingCollector.mappings[WIDTH] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `height const for tiles`() {
        builder.height = 0.3
        assertEquals(0.3, (builder.bindingCollector.settings[HEIGHT] as? NonPositionalSetting<*>)?.value)
    }

    @Test
    fun `height str mapping for tiles`() {
        builder.height("height")
        assertEquals("height", (builder.bindingCollector.mappings[HEIGHT] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `height dataColumn mapping for tiles`() {
        builder.height(height)
        assertEquals("height", (builder.bindingCollector.mappings[HEIGHT] as? PositionalMapping<*>)?.columnID)
    }

    @Test
    fun `height iterable mapping for tiles`() {
        builder.height(listOf(0.2, 0.5, .1))
        assertEquals("height", (builder.bindingCollector.mappings[HEIGHT] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `x const for tiles`() {
        builder.x.constant(5.0)
        assertEquals(X, (builder.bindingCollector.settings[X] as? PositionalSetting<*>)?.aes)
        assertEquals(5.0, (builder.bindingCollector.settings[X] as? PositionalSetting<*>)?.value)
    }

    @Test
    fun `y const for tiles`() {
        builder.y.constant(10)
        assertEquals(Y, (builder.bindingCollector.settings[Y] as? PositionalSetting<*>)?.aes)
        assertEquals(10, (builder.bindingCollector.settings[Y] as? PositionalSetting<*>)?.value)
    }

    @Test
    fun `x for tiles`() {
        builder.x(xAxis) {
            axis {
                name = "x axis"
            }
            scale = continuous(0.1..3.7)
        }

        assertEquals(X, builder.bindingCollector.mappings[X]?.aes)
        assertEquals("xAxis", builder.bindingCollector.mappings[X]?.columnID)
        assertEquals(
            .1,
            (builder.bindingCollector.mappings[X]?.parameters?.scale as? PositionalContinuousScale<*>)?.min
        )
        assertEquals(
            3.7,
            (builder.bindingCollector.mappings[X]?.parameters?.scale as? PositionalContinuousScale<*>)?.max
        )
    }

    @Test
    fun `y for tiles`() {
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
            (builder.bindingCollector.mappings[Y]?.parameters?.scale as? PositionalCategoricalScale<*>)?.categories
        )
    }
}