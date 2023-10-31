package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.letsplot.internal.COLOR
import org.jetbrains.kotlinx.kandy.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BorderTests {
    private val type = listOf("dot").toColumn("type")
    private val color = listOf("blue").toColumn("color")
    private val width = listOf(0.9).toColumn("width")

    private val df = dataFrameOf(type, color, width)

    private val parentContext = DataFramePlotContext(df)
    private lateinit var context: BorderLineContext

    @BeforeTest
    fun setUp() {
        context = BorderLineContext(parentContext)
    }

    @Test
    fun `color const for border`() {
        context.color = Color.BLUE
        assertEquals(Color.BLUE, (context.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for border`() {
        context.color("color")
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for border`() {
        context.color(color)
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for border`() {
        context.color(listOf("red"))
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type const for border`() {
        context.type = LineType.DOTDASH
        assertEquals(LineType.DOTDASH, (context.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `type str mapping for border`() {
        context.type("type")
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border type dataColumn mapping for border`() {
        context.type(type)
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border type iterable mapping for border`() {
        context.type(listOf("dot"))
        assertEquals("linetype", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width const for border`() {
        context.width = .5
        assertEquals(.5, (context.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `border width str mapping for border`() {
        context.width("width")
        assertEquals("width", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width dataColumn mapping for border`() {
        context.width(width)
        assertEquals("width", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width iterable mapping for border`() {
        context.width(listOf(0.2, .1))
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }
}