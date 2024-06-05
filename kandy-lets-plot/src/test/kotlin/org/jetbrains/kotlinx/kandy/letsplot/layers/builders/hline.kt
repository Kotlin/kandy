package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.H_LINE
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HLineTests {
    private val yIntercept = listOf("first").toColumn("yIntercept")
    private val type = listOf("dot").toColumn("type")
    private val color = listOf("blue").toColumn("color")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val width = listOf(0.9).toColumn("width")

    private val df = dataFrameOf(yIntercept, type, color, alpha, width)

    private val parentContext = DataFramePlotBuilder(df)
    private lateinit var context: HLineContext

    @BeforeTest
    fun setUp() {
        context = HLineContext(parentContext)
    }

    @Test
    fun `geom is H_LINE`() {
        assertEquals(H_LINE, context.geom)
    }

    @Test
    fun `requiredAes contains Y_INTERCEPT`() {
        assertTrue(context.requiredAes.contains(Y_INTERCEPT))
    }

    @Test
    fun `color const for hline`() {
        context.color = Color.BLUE
        assertEquals(Color.BLUE, (context.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for hline`() {
        context.color("color")
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for hline`() {
        context.color(color)
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for hline`() {
        context.color(listOf("red"))
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type const for hline`() {
        context.type = LineType.DOTDASH
        assertEquals(LineType.DOTDASH, (context.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `type str mapping for hline`() {
        context.type("type")
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type dataColumn mapping for hline`() {
        context.type(type)
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type iterable mapping for hline`() {
        context.type(listOf("dot"))
        assertEquals("linetype", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for hline`() {
        context.alpha = 0.1
        assertEquals(0.1, (context.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for hline`() {
        context.alpha("alpha")
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for hline`() {
        context.alpha(alpha)
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for hline`() {
        context.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width const for hline`() {
        context.width = .5
        assertEquals(.5, (context.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `width str mapping for hline`() {
        context.width("width")
        assertEquals("width", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width dataColumn mapping for hline`() {
        context.width(width)
        assertEquals("width", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width iterable mapping for hline`() {
        context.width(listOf(0.2, .1))
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `x free for hline`() {
        context.x {
            axis {
                name = "xAxis"
            }
            scale = continuous(-3.0, 3.0)
        }

        assertEquals(X, context.bindingCollector.freeScales[X]?.aes)
        assertEquals(
            "xAxis",
            (context.bindingCollector.freeScales[X]?.parameters as? LetsPlotPositionalMappingParameters<*>)?.axis?.name
        )
    }

    @Test
    fun `y intercept const for hline`() {
        context.yIntercept.constant(5)
        assertEquals(5, (context.bindingCollector.settings[Y_INTERCEPT] as? PositionalSetting<*>)?.value)
    }

    @Test
    fun `y intercept mapping for hline`() {
        context.yIntercept(yIntercept)

        assertEquals(Y_INTERCEPT, context.bindingCollector.mappings[Y_INTERCEPT]?.aes)
        assertEquals("yIntercept", (context.bindingCollector.mappings[Y_INTERCEPT] as? PositionalMapping<*>)?.columnID)
    }
}