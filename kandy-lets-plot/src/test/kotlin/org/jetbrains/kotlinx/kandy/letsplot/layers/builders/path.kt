package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.PATH
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Suppress("INVISIBLE_MEMBER")
class PathTests {
    private val xAxis = listOf(0.5).toColumn("xAxis")
    private val yAxis = listOf("first").toColumn("yAxis")
    private val type = listOf("dot").toColumn("type")
    private val color = listOf("blue").toColumn("color")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val width = listOf(0.9).toColumn("width")

    private val df = dataFrameOf(xAxis, yAxis, type, color, alpha, width)

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: PathBuilder

    @BeforeTest
    fun setUp() {
        builder = PathBuilder(parentBuilder)
    }

    @Test
    fun `geom is PATH`() {
        assertEquals(PATH, builder.geom)
    }

    @Test
    fun `requiredAes contains X and Y`() {
        assertTrue(builder.requiredAes.contains(X))
        assertTrue(builder.requiredAes.contains(Y))
    }

    @Test
    fun `color const for path`() {
        builder.color = Color.BLUE
        assertEquals(Color.BLUE, (builder.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for path`() {
        builder.color("color")
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for path`() {
        builder.color(color)
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for path`() {
        builder.color(listOf("red"))
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type const for path`() {
        builder.type = LineType.DOTDASH
        assertEquals(LineType.DOTDASH, (builder.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `type str mapping for path`() {
        builder.type("type")
        assertEquals("type", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type dataColumn mapping for path`() {
        builder.type(type)
        assertEquals("type", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type iterable mapping for path`() {
        builder.type(listOf("dot"))
        assertEquals("linetype", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width const for path`() {
        builder.width = .5
        assertEquals(.5, (builder.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `width str mapping for path`() {
        builder.width("width")
        assertEquals("width", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width dataColumn mapping for path`() {
        builder.width(width)
        assertEquals("width", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width iterable mapping for path`() {
        builder.width(listOf(0.2, .1))
        assertEquals("size", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for path`() {
        builder.alpha = 0.1
        assertEquals(0.1, (builder.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for path`() {
        builder.alpha("alpha")
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for path`() {
        builder.alpha(alpha)
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for path`() {
        builder.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `x const for path`() {
        builder.x.constant(5.0)
        assertEquals(X, (builder.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(5.0, (builder.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `y const for path`() {
        builder.y.constant(10)
        assertEquals(Y, (builder.bindingCollector.settings[Y] as PositionalSetting<*>).aes)
        assertEquals(10, (builder.bindingCollector.settings[Y] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for path`() {
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
    fun `y for path`() {
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