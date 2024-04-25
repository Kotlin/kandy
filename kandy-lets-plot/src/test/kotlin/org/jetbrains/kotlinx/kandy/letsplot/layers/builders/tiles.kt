package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.ALPHA
import org.jetbrains.kotlinx.kandy.letsplot.internal.HEIGHT
import org.jetbrains.kotlinx.kandy.letsplot.internal.WIDTH
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.TILE
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TilesTests {
    private val xAxis = listOf(0.5).toColumn("xAxis")
    private val yAxis = listOf("first").toColumn("yAxis")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val color = listOf("blue").toColumn("color")
    private val width = listOf(1).toColumn("width")
    private val height = listOf(3).toColumn("height")

    private val df = dataFrameOf(xAxis, yAxis, alpha, color, width, height)

    private val parentContext = DataFramePlotBuilder(df)
    private lateinit var context: TilesContext

    @BeforeTest
    fun setUp() {
        context = TilesContext(parentContext)
    }

    @Test
    fun `geom is TILE`() {
        assertEquals(TILE, context.geom)
    }

    @Test
    fun `requiredAes contains X and Y`() {
        assertTrue(context.requiredAes.contains(X))
        assertTrue(context.requiredAes.contains(Y))
    }

    @Test
    fun `alpha const for tiles`() {
        context.alpha = 0.1
        assertEquals(0.1, (context.bindingCollector.settings[ALPHA] as? NonPositionalSetting<*>)?.value)
    }

    @Test
    fun `alpha str mapping for tiles`() {
        context.alpha("alpha")
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `alpha dataColumn mapping for tiles`() {
        context.alpha(alpha)
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `alpha iterable mapping for tiles`() {
        context.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `width const for tiles`() {
        context.width = 0.3
        assertEquals(0.3, (context.bindingCollector.settings[WIDTH] as? NonPositionalSetting<*>)?.value)
    }

    @Test
    fun `width str mapping for tiles`() {
        context.width("width")
        assertEquals("width", (context.bindingCollector.mappings[WIDTH] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `width dataColumn mapping for tiles`() {
        context.width(width)
        assertEquals("width", (context.bindingCollector.mappings[WIDTH] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `width iterable mapping for tiles`() {
        context.width(listOf(0.2, 0.5, .1))
        assertEquals("width", (context.bindingCollector.mappings[WIDTH] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `height const for tiles`() {
        context.height = 0.3
        assertEquals(0.3, (context.bindingCollector.settings[HEIGHT] as? NonPositionalSetting<*>)?.value)
    }

    @Test
    fun `height str mapping for tiles`() {
        context.height("height")
        assertEquals("height", (context.bindingCollector.mappings[HEIGHT] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `height dataColumn mapping for tiles`() {
        context.height(height)
        assertEquals("height", (context.bindingCollector.mappings[HEIGHT] as? PositionalMapping<*>)?.columnID)
    }

    @Test
    fun `height iterable mapping for tiles`() {
        context.height(listOf(0.2, 0.5, .1))
        assertEquals("height", (context.bindingCollector.mappings[HEIGHT] as? NonPositionalMapping<*, *>)?.columnID)
    }

    @Test
    fun `x const for tiles`() {
        context.x.constant(5.0)
        assertEquals(X, (context.bindingCollector.settings[X] as? PositionalSetting<*>)?.aes)
        assertEquals(5.0, (context.bindingCollector.settings[X] as? PositionalSetting<*>)?.value)
    }

    @Test
    fun `y const for tiles`() {
        context.y.constant(10)
        assertEquals(Y, (context.bindingCollector.settings[Y] as? PositionalSetting<*>)?.aes)
        assertEquals(10, (context.bindingCollector.settings[Y] as? PositionalSetting<*>)?.value)
    }

    @Test
    fun `x for tiles`() {
        context.x(xAxis) {
            axis {
                name = "x axis"
            }
            scale = continuous(0.1..3.7)
        }

        assertEquals(X, context.bindingCollector.mappings[X]?.aes)
        assertEquals("xAxis", context.bindingCollector.mappings[X]?.columnID)
        assertEquals(
            .1,
            (context.bindingCollector.mappings[X]?.parameters?.scale as? PositionalContinuousScale<*>)?.min
        )
        assertEquals(
            3.7,
            (context.bindingCollector.mappings[X]?.parameters?.scale as? PositionalContinuousScale<*>)?.max
        )
    }

    @Test
    fun `y for tiles`() {
        context.y(yAxis) {
            axis {
                name = "x axis"
            }
            scale = categorical(listOf("one", "two"))
        }

        assertEquals(Y, context.bindingCollector.mappings[Y]?.aes)
        assertEquals("yAxis", context.bindingCollector.mappings[Y]?.columnID)
        assertEquals(
            listOf("one", "two"),
            (context.bindingCollector.mappings[Y]?.parameters?.scale as? PositionalCategoricalScale<*>)?.categories
        )
    }
}