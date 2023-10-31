package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.ALPHA
import org.jetbrains.kotlinx.kandy.letsplot.internal.COLOR
import org.jetbrains.kotlinx.kandy.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.STEP
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StepTests {
    private val xAxis = listOf(0.5).toColumn("xAxis")
    private val yAxis = listOf("first").toColumn("yAxis")
    private val color = listOf("blue").toColumn("color")
    private val type = listOf("dot").toColumn("type")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val width = listOf(1.5).toColumn("width")

    private val df = dataFrameOf(xAxis, yAxis, color, type, alpha, width)

    private val parentContext = DataFramePlotContext(df)
    private lateinit var context: StepContext

    @BeforeTest
    fun setUp() {
        context = StepContext(parentContext)
    }

    @Test
    fun `geom is STEP`() {
        assertEquals(STEP, context.geom)
    }

    @Test
    fun `requiredAes contains X and Y`() {
        assertTrue(context.requiredAes.contains(X))
        assertTrue(context.requiredAes.contains(Y))
    }

    @Test
    fun `color const for step`() {
        context.color = Color.BLUE
        assertEquals(Color.BLUE, (context.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for step`() {
        context.color("color")
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for step`() {
        context.color(color)
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for step`() {
        context.color(listOf("red"))
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for step`() {
        context.alpha = 0.1
        assertEquals(0.1, (context.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for step`() {
        context.alpha("alpha")
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for step`() {
        context.alpha(alpha)
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for step`() {
        context.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type const for step`() {
        context.lineType = LineType.DOTTED
        assertEquals(LineType.DOTTED, (context.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `type str mapping for step`() {
        context.lineType("type")
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type dataColumn mapping for step`() {
        context.lineType(type)
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type iterable mapping for step`() {
        context.lineType(listOf("1", "2"))
        assertEquals("linetype", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width const for step`() {
        context.width = 0.3
        assertEquals(0.3, (context.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `width str mapping for step`() {
        context.width("width")
        assertEquals("width", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width dataColumn mapping for step`() {
        context.width(width)
        assertEquals("width", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width iterable mapping for step`() {
        context.width(listOf(0.2, 0.5, .1))
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `x const for step`() {
        context.x.constant(5.0)
        assertEquals(X, (context.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(5.0, (context.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `y const for step`() {
        context.y.constant(10)
        assertEquals(Y, (context.bindingCollector.settings[Y] as PositionalSetting<*>).aes)
        assertEquals(10, (context.bindingCollector.settings[Y] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for step`() {
        context.x(xAxis) {
            axis {
                name = "x axis"
            }
            scale = continuous(0.1..3.7)
        }

        assertEquals(X, context.bindingCollector.mappings[X]?.aes)
        assertEquals("xAxis", context.bindingCollector.mappings[X]?.columnID)
        assertEquals(.1, (context.bindingCollector.mappings[X]?.parameters?.scale as PositionalContinuousScale<*>).min)
        assertEquals(3.7, (context.bindingCollector.mappings[X]?.parameters?.scale as PositionalContinuousScale<*>).max)
    }

    @Test
    fun `y for step`() {
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
            (context.bindingCollector.mappings[Y]?.parameters?.scale as PositionalCategoricalScale<*>).categories
        )
    }
}