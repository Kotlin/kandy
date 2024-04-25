package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.letsplot.internal.ALPHA
import org.jetbrains.kotlinx.kandy.letsplot.internal.COLOR
import org.jetbrains.kotlinx.kandy.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_BEGIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_END
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_BEGIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_END
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.SEGMENT
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SegmentTests {
    private val xBegin = listOf(0.1).toColumn("xBegin")
    private val xEnd = listOf(2.1).toColumn("xEnd")
    private val yBegin = listOf(1).toColumn("yBegin")
    private val yEnd = listOf(5).toColumn("yEnd")
    private val color = listOf("blue").toColumn("color")
    private val type = listOf("dot").toColumn("type")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val width = listOf(1.5).toColumn("width")

    private val df = dataFrameOf(xBegin, xEnd, yBegin, yEnd, color, type, alpha, width)

    private val parentContext = DataFramePlotBuilder(df)
    private lateinit var context: SegmentsContext

    @BeforeTest
    fun setUp() {
        context = SegmentsContext(parentContext)
    }

    @Test
    fun `geom is SEGMENT`() {
        assertEquals(SEGMENT, context.geom)
    }

    @Test
    fun `requiredAes contains X_BEGIN, Y_BEGIN, X_END, Y_END`() {
        assertTrue(context.requiredAes.contains(X_BEGIN))
        assertTrue(context.requiredAes.contains(X_END))
        assertTrue(context.requiredAes.contains(Y_BEGIN))
        assertTrue(context.requiredAes.contains(Y_END))
    }

    @Test
    fun `color const for segment`() {
        context.color = Color.BLUE
        assertEquals(Color.BLUE, (context.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for segment`() {
        context.color("color")
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for segment`() {
        context.color(color)
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for segment`() {
        context.color(listOf("red"))
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for segment`() {
        context.alpha = 0.1
        assertEquals(0.1, (context.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for segment`() {
        context.alpha("alpha")
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for segment`() {
        context.alpha(alpha)
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for segment`() {
        context.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type const for segment`() {
        context.lineType = LineType.DOTTED
        assertEquals(LineType.DOTTED, (context.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `type str mapping for segment`() {
        context.lineType("type")
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type dataColumn mapping for segment`() {
        context.lineType(type)
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type iterable mapping for segment`() {
        context.lineType(listOf("1", "2"))
        assertEquals("linetype", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width const for segment`() {
        context.width = 0.3
        assertEquals(0.3, (context.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `width str mapping for segment`() {
        context.width("width")
        assertEquals("width", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width dataColumn mapping for segment`() {
        context.width(width)
        assertEquals("width", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width iterable mapping for segment`() {
        context.width(listOf(0.2, 0.5, .1))
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `xBegin for segment`() {
        context.xBegin(xBegin)
        assertEquals(X_BEGIN, context.bindingCollector.mappings[X_BEGIN]?.aes)
        assertEquals("xBegin", context.bindingCollector.mappings[X_BEGIN]?.columnID)
    }

    @Test
    fun `xEnd for segment`() {
        context.xEnd(xEnd)
        assertEquals(X_END, context.bindingCollector.mappings[X_END]?.aes)
        assertEquals("xEnd", context.bindingCollector.mappings[X_END]?.columnID)
    }

    @Test
    fun `yBegin for segment`() {
        context.yBegin(yBegin)
        assertEquals(Y_BEGIN, context.bindingCollector.mappings[Y_BEGIN]?.aes)
        assertEquals("yBegin", context.bindingCollector.mappings[Y_BEGIN]?.columnID)
    }

    @Test
    fun `yEnd for segment`() {
        context.yEnd(yEnd)
        assertEquals(Y_END, context.bindingCollector.mappings[Y_END]?.aes)
        assertEquals("yEnd", context.bindingCollector.mappings[Y_END]?.columnID)
    }
}