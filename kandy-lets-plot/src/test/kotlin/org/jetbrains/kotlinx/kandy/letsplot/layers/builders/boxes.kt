package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.first
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.ALPHA
import org.jetbrains.kotlinx.kandy.letsplot.internal.FATTEN
import org.jetbrains.kotlinx.kandy.letsplot.internal.FILL
import org.jetbrains.kotlinx.kandy.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.LOWER
import org.jetbrains.kotlinx.kandy.letsplot.internal.MIDDLE
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import org.jetbrains.kotlinx.kandy.letsplot.internal.UPPER
import org.jetbrains.kotlinx.kandy.letsplot.internal.WIDTH
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.BOXPLOT
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BoxplotTests {
    private val xAxis = listOf(0.5).toColumn("xAxis")
    private val lower = listOf(10).toColumn("lower")
    private val upper = listOf(30).toColumn("upper")
    private val middle = listOf(20).toColumn("middle")
    private val yMin = listOf(5).toColumn("yMin")
    private val yMax = listOf(35).toColumn("yMax")
    private val fatten = listOf(1.5).toColumn("fatten")
    private val type = listOf("dot").toColumn("type")
    private val color = listOf("blue").toColumn("color")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val width = listOf(0.9).toColumn("width")

    private val df = dataFrameOf(xAxis, lower, upper, middle, yMin, yMax, fatten, type, color, alpha, width)

    private val parentContext = DataFramePlotBuilder(df)
    private lateinit var context: org.jetbrains.kotlinx.kandy.letsplot.layers.builders.BoxesBuilder

    @BeforeTest
    fun setUp() {
        context = org.jetbrains.kotlinx.kandy.letsplot.layers.builders.BoxesBuilder(parentContext)
    }

    @Test
    fun `geom is BOXPLOT`() {
        assertEquals(BOXPLOT, context.geom)
    }

    @Test
    fun `requiredAes contains X, LOWER, UPPER, MIDDLE, Y_MIN, Y_MAX`() {
        assertTrue(context.requiredAes.contains(X))
        assertTrue(context.requiredAes.contains(LOWER))
        assertTrue(context.requiredAes.contains(UPPER))
        assertTrue(context.requiredAes.contains(MIDDLE))
        assertTrue(context.requiredAes.contains(Y_MIN))
        assertTrue(context.requiredAes.contains(Y_MAX))
    }

    @Test
    fun `fillColor const for boxes`() {
        context.fillColor = Color.BLUE
        assertEquals(Color.BLUE, (context.bindingCollector.settings[FILL] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `fillColor str mapping for boxes`() {
        context.fillColor("color")
        assertEquals("color", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor dataColumn mapping for boxes`() {
        context.fillColor(color)
        assertEquals("color", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor iterable mapping for boxes`() {
        context.fillColor(listOf("red"))
        assertEquals("fill", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border type const for boxes`() {
        context.borderLine.type = LineType.DOTDASH
        assertEquals(LineType.DOTDASH, (context.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `border type str mapping for boxes`() {
        context.borderLine.type("type")
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border type dataColumn mapping for boxes`() {
        context.borderLine.type(type)
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border type iterable mapping for boxes`() {
        context.borderLine.type(listOf("dot"))
        assertEquals("linetype", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width const for boxes`() {
        context.borderLine.width = .5
        assertEquals(.5, (context.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `border width str mapping for boxes`() {
        context.borderLine.width("width")
        assertEquals("width", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width dataColumn mapping for boxes`() {
        context.borderLine.width(width)
        assertEquals("width", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width iterable mapping for boxes`() {
        context.borderLine.width(listOf(0.2, .1))
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for boxes`() {
        context.alpha = 0.1
        assertEquals(0.1, (context.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for boxes`() {
        context.alpha("alpha")
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for boxes`() {
        context.alpha(alpha)
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for boxes`() {
        context.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width const for boxes`() {
        context.width = .5
        assertEquals(.5, (context.bindingCollector.settings[WIDTH] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `width str mapping for boxes`() {
        context.width("width")
        assertEquals("width", (context.bindingCollector.mappings[WIDTH] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width dataColumn mapping for boxes`() {
        context.width(width)
        assertEquals("width", (context.bindingCollector.mappings[WIDTH] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width iterable mapping for boxes`() {
        context.width(listOf(0.2, .1))
        assertEquals("width", (context.bindingCollector.mappings[WIDTH] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `x const for boxes`() {
        context.x.constant(5.0)
        assertEquals(X, (context.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(5.0, (context.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for boxes`() {
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
    fun `lower for boxes`() {
        context.lower(lower)
        assertEquals(LOWER, context.bindingCollector.mappings[LOWER]?.aes)
        assertEquals("lower", context.bindingCollector.mappings[LOWER]?.columnID)
    }

    @Test
    fun `upper for boxes`() {
        context.upper(upper)
        assertEquals(UPPER, context.bindingCollector.mappings[UPPER]?.aes)
        assertEquals("upper", context.bindingCollector.mappings[UPPER]?.columnID)
    }

    @Test
    fun `middle for boxes`() {
        context.middle(middle)
        assertEquals(MIDDLE, context.bindingCollector.mappings[MIDDLE]?.aes)
        assertEquals("middle", context.bindingCollector.mappings[MIDDLE]?.columnID)
    }

    @Test
    fun `yMin for boxes`() {
        context.yMin(yMin)
        assertEquals(Y_MIN, context.bindingCollector.mappings[Y_MIN]?.aes)
        assertEquals("yMin", context.bindingCollector.mappings[Y_MIN]?.columnID)
    }

    @Test
    fun `yMax for boxes`() {
        context.yMax(yMax)
        assertEquals(Y_MAX, context.bindingCollector.mappings[Y_MAX]?.aes)
        assertEquals("yMax", context.bindingCollector.mappings[Y_MAX]?.columnID)
    }

    @Test
    fun `fatten for boxes`() {
        context.fatten = fatten.first()
        assertEquals(FATTEN, (context.bindingCollector.settings[FATTEN] as NonPositionalSetting<*>).aes)
        assertEquals(1.5, (context.bindingCollector.settings[FATTEN] as NonPositionalSetting<*>).value)
    }
}