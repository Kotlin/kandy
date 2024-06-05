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
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.STEP
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Suppress("INVISIBLE_MEMBER")
class StepTests {
    private val xAxis = listOf(0.5).toColumn("xAxis")
    private val yAxis = listOf("first").toColumn("yAxis")
    private val color = listOf("blue").toColumn("color")
    private val type = listOf("dot").toColumn("type")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val width = listOf(1.5).toColumn("width")

    private val df = dataFrameOf(xAxis, yAxis, color, type, alpha, width)

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: StepBuilder

    @BeforeTest
    fun setUp() {
        builder = StepBuilder(parentBuilder)
    }

    @Test
    fun `geom is STEP`() {
        assertEquals(STEP, builder.geom)
    }

    @Test
    fun `requiredAes contains X and Y`() {
        assertTrue(builder.requiredAes.contains(X))
        assertTrue(builder.requiredAes.contains(Y))
    }

    @Test
    fun `color const for step`() {
        builder.color = Color.BLUE
        assertEquals(Color.BLUE, (builder.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for step`() {
        builder.color("color")
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for step`() {
        builder.color(color)
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for step`() {
        builder.color(listOf("red"))
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for step`() {
        builder.alpha = 0.1
        assertEquals(0.1, (builder.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for step`() {
        builder.alpha("alpha")
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for step`() {
        builder.alpha(alpha)
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for step`() {
        builder.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type const for step`() {
        builder.lineType = LineType.DOTTED
        assertEquals(LineType.DOTTED, (builder.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `type str mapping for step`() {
        builder.lineType("type")
        assertEquals("type", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type dataColumn mapping for step`() {
        builder.lineType(type)
        assertEquals("type", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type iterable mapping for step`() {
        builder.lineType(listOf("1", "2"))
        assertEquals("linetype", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width const for step`() {
        builder.width = 0.3
        assertEquals(0.3, (builder.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `width str mapping for step`() {
        builder.width("width")
        assertEquals("width", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width dataColumn mapping for step`() {
        builder.width(width)
        assertEquals("width", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width iterable mapping for step`() {
        builder.width(listOf(0.2, 0.5, .1))
        assertEquals("size", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `x const for step`() {
        builder.x.constant(5.0)
        assertEquals(X, (builder.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(5.0, (builder.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `y const for step`() {
        builder.y.constant(10)
        assertEquals(Y, (builder.bindingCollector.settings[Y] as PositionalSetting<*>).aes)
        assertEquals(10, (builder.bindingCollector.settings[Y] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for step`() {
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
    fun `y for step`() {
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