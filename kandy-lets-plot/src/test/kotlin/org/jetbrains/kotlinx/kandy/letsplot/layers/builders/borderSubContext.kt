package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.letsplot.internal.COLOR
import org.jetbrains.kotlinx.kandy.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.BorderLine
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("INVISIBLE_MEMBER")
class BorderTests {
    private val type = listOf("dot").toColumn("type")
    private val color = listOf("blue").toColumn("color")
    private val width = listOf(0.9).toColumn("width")

    private val df = dataFrameOf(type, color, width)

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: BorderLine

    @BeforeTest
    fun setUp() {
        builder = BorderLine(parentBuilder.bindingHandler)
    }

    @Test
    fun `color const for border`() {
        builder.color = Color.BLUE
        assertEquals(Color.BLUE, (builder.bindingHandler.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for border`() {
        builder.color("color")
        assertEquals("color", (builder.bindingHandler.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for border`() {
        builder.color(color)
        assertEquals("color", (builder.bindingHandler.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for border`() {
        builder.color(listOf("red"))
        assertEquals("color", (builder.bindingHandler.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type const for border`() {
        builder.type = LineType.DOTDASH
        assertEquals(LineType.DOTDASH, (builder.bindingHandler.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `type str mapping for border`() {
        builder.type("type")
        assertEquals("type", (builder.bindingHandler.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border type dataColumn mapping for border`() {
        builder.type(type)
        assertEquals("type", (builder.bindingHandler.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border type iterable mapping for border`() {
        println(builder.bindingHandler.datasetBuilder.rowsCount())
        builder.type(listOf("dot"))
        assertEquals("linetype", (builder.bindingHandler.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width const for border`() {
        builder.width = .5
        assertEquals(.5, (builder.bindingHandler.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `border width str mapping for border`() {
        builder.width("width")
        assertEquals("width", (builder.bindingHandler.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width dataColumn mapping for border`() {
        builder.width(width)
        assertEquals("width", (builder.bindingHandler.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `border width iterable mapping for border`() {
        builder.width(listOf(0.2))
        assertEquals("size", (builder.bindingHandler.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }
}