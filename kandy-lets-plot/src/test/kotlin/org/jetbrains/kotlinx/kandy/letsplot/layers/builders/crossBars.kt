package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.first
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.CROSS_BAR
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Suppress("INVISIBLE_MEMBER")
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

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: CrossBarsBuilder

    @BeforeTest
    fun setUp() {
        builder = CrossBarsBuilder(parentBuilder)
    }

    @Test
    fun `geom is CROSS_BAR`() {
        assertEquals(CROSS_BAR, builder.geom)
    }

    @Test
    fun `requiredAes contains X, Y_MIN, Y_MAX`() {
        assertTrue(builder.requiredAes.contains(X))
        assertTrue(builder.requiredAes.contains(Y_MIN))
        assertTrue(builder.requiredAes.contains(Y_MAX))
    }

    @Test
    fun `fillColor const for crossbars`() {
        builder.fillColor = Color.BLUE
        assertEquals(Color.BLUE, (builder.bindingCollector.settings[FILL] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `fillColor str mapping for crossbars`() {
        builder.fillColor("color")
        assertEquals("color", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor dataColumn mapping for crossbars`() {
        builder.fillColor(color)
        assertEquals("color", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor iterable mapping for crossbars`() {
        builder.fillColor(listOf("red"))
        assertEquals("fill", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border type const for crossbars`() {
        builder.borderLine.type = LineType.DOTDASH
        assertEquals(LineType.DOTDASH, (builder.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `border type str mapping for crossbars`() {
        builder.borderLine.type("type")
        assertEquals("type", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border type dataColumn mapping for crossbars`() {
        builder.borderLine.type(type)
        assertEquals("type", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border type iterable mapping for crossbars`() {
        builder.borderLine.type(listOf("dot"))
        assertEquals("linetype", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width const for crossbars`() {
        builder.borderLine.width = .5
        assertEquals(.5, (builder.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `border width str mapping for crossbars`() {
        builder.borderLine.width("width")
        assertEquals("width", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width dataColumn mapping for crossbars`() {
        builder.borderLine.width(width)
        assertEquals("width", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width iterable mapping for crossbars`() {
        builder.borderLine.width(listOf(0.2, .1))
        assertEquals("size", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for crossbars`() {
        builder.alpha = 0.1
        assertEquals(0.1, (builder.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for crossbars`() {
        builder.alpha("alpha")
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for crossbars`() {
        builder.alpha(alpha)
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for crossbars`() {
        builder.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width const for crossbars`() {
        builder.width = .5
        assertEquals(.5, (builder.bindingCollector.settings[WIDTH] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `width str mapping for crossbars`() {
        builder.width("width")
        assertEquals("width", (builder.bindingCollector.mappings[WIDTH] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width dataColumn mapping for crossbars`() {
        builder.width(width)
        assertEquals("width", (builder.bindingCollector.mappings[WIDTH] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width iterable mapping for crossbars`() {
        builder.width(listOf(0.2, .1))
        assertEquals("width", (builder.bindingCollector.mappings[WIDTH] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `x const for crossbars`() {
        builder.x.constant(5.0)
        assertEquals(X, (builder.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(5.0, (builder.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for crossbars`() {
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
    fun `middle for crossbars`() {
        builder.y(y)
        assertEquals(Y, builder.bindingCollector.mappings[Y]?.aes)
        assertEquals("y", builder.bindingCollector.mappings[Y]?.columnID)
    }

    @Test
    fun `yMin for crossbars`() {
        builder.yMin(yMin)
        assertEquals(Y_MIN, builder.bindingCollector.mappings[Y_MIN]?.aes)
        assertEquals("yMin", builder.bindingCollector.mappings[Y_MIN]?.columnID)
    }

    @Test
    fun `yMax for crossbars`() {
        builder.yMax(yMax)
        assertEquals(Y_MAX, builder.bindingCollector.mappings[Y_MAX]?.aes)
        assertEquals("yMax", builder.bindingCollector.mappings[Y_MAX]?.columnID)
    }

    @Test
    fun `fatten for crossbars`() {
        builder.fatten = fatten.first()
        assertEquals(FATTEN, (builder.bindingCollector.settings[FATTEN] as NonPositionalSetting<*>).aes)
        assertEquals(1.5, (builder.bindingCollector.settings[FATTEN] as NonPositionalSetting<*>).value)
    }
}