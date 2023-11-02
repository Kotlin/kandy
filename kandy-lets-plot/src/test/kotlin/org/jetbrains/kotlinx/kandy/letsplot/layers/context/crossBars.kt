package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.first
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.internal.ALPHA
import org.jetbrains.kotlinx.kandy.letsplot.internal.FATTEN
import org.jetbrains.kotlinx.kandy.letsplot.internal.FILL
import org.jetbrains.kotlinx.kandy.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import org.jetbrains.kotlinx.kandy.letsplot.internal.WIDTH
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.CROSS_BAR
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CrossbarTests {
    private val xAxis = listOf(0.5).toColumn("xAxis")
    private val y = listOf(20).toColumn("y")
    private val yMin = listOf(5).toColumn("yMin")
    private val yMax = listOf(35).toColumn("yMax")
    private val fatten = listOf(1.5).toColumn("fatten")
    private val type = listOf("dot").toColumn("type")
    private val color = listOf("blue").toColumn("color")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val width = listOf(0.9).toColumn("width")

    private val df = dataFrameOf(xAxis, y, yMin, yMax, fatten, type, color, alpha, width)

    private val parentContext = DataFramePlotContext(df)
    private lateinit var context: CrossBarsContext

    @BeforeTest
    fun setUp() {
        context = CrossBarsContext(parentContext)
    }

    @Test
    fun `geom is CROSS_BAR`() {
        assertEquals(CROSS_BAR, context.geom)
    }

    @Test
    fun `requiredAes contains X, MIDDLE, Y_MIN, Y_MAX`() {
        assertTrue(context.requiredAes.contains(X))
        assertTrue(context.requiredAes.contains(Y))
        assertTrue(context.requiredAes.contains(Y_MIN))
        assertTrue(context.requiredAes.contains(Y_MAX))
    }

    @Test
    fun `fillColor const for crossbars`() {
        context.fillColor = Color.BLUE
        assertEquals(Color.BLUE, (context.bindingCollector.settings[FILL] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `fillColor str mapping for crossbars`() {
        context.fillColor("color")
        assertEquals("color", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor dataColumn mapping for crossbars`() {
        context.fillColor(color)
        assertEquals("color", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor iterable mapping for crossbars`() {
        context.fillColor(listOf("red"))
        assertEquals("fill", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border type const for crossbars`() {
        context.borderLine.type = LineType.DOTDASH
        assertEquals(LineType.DOTDASH, (context.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `border type str mapping for crossbars`() {
        context.borderLine.type("type")
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border type dataColumn mapping for crossbars`() {
        context.borderLine.type(type)
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border type iterable mapping for crossbars`() {
        context.borderLine.type(listOf("dot"))
        assertEquals("linetype", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width const for crossbars`() {
        context.borderLine.width = .5
        assertEquals(.5, (context.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `border width str mapping for crossbars`() {
        context.borderLine.width("width")
        assertEquals("width", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width dataColumn mapping for crossbars`() {
        context.borderLine.width(width)
        assertEquals("width", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width iterable mapping for crossbars`() {
        context.borderLine.width(listOf(0.2, .1))
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for crossbars`() {
        context.alpha = 0.1
        assertEquals(0.1, (context.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for crossbars`() {
        context.alpha("alpha")
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for crossbars`() {
        context.alpha(alpha)
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for crossbars`() {
        context.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width const for crossbars`() {
        context.width = .5
        assertEquals(.5, (context.bindingCollector.settings[WIDTH] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `width str mapping for crossbars`() {
        context.width("width")
        assertEquals("width", (context.bindingCollector.mappings[WIDTH] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width dataColumn mapping for crossbars`() {
        context.width(width)
        assertEquals("width", (context.bindingCollector.mappings[WIDTH] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width iterable mapping for crossbars`() {
        context.width(listOf(0.2, .1))
        assertEquals("width", (context.bindingCollector.mappings[WIDTH] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `x const for crossbars`() {
        context.x.constant(5.0)
        assertEquals(X, (context.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(5.0, (context.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for crossbars`() {
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
    fun `middle for crossbars`() {
        context.y(y)
        assertEquals(Y, context.bindingCollector.mappings[Y]?.aes)
        assertEquals("y", context.bindingCollector.mappings[Y]?.columnID)
    }

    @Test
    fun `yMin for crossbars`() {
        context.yMin(yMin)
        assertEquals(Y_MIN, context.bindingCollector.mappings[Y_MIN]?.aes)
        assertEquals("yMin", context.bindingCollector.mappings[Y_MIN]?.columnID)
    }

    @Test
    fun `yMax for crossbars`() {
        context.yMax(yMax)
        assertEquals(Y_MAX, context.bindingCollector.mappings[Y_MAX]?.aes)
        assertEquals("yMax", context.bindingCollector.mappings[Y_MAX]?.columnID)
    }

    @Test
    fun `fatten for crossbars`() {
        context.fatten = fatten.first()
        assertEquals(FATTEN, (context.bindingCollector.settings[FATTEN] as NonPositionalSetting<*>).aes)
        assertEquals(1.5, (context.bindingCollector.settings[FATTEN] as NonPositionalSetting<*>).value)
    }
}