package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.letsplot.internal.ALPHA
import org.jetbrains.kotlinx.kandy.letsplot.internal.COLOR
import org.jetbrains.kotlinx.kandy.letsplot.internal.INTERCEPT
import org.jetbrains.kotlinx.kandy.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import org.jetbrains.kotlinx.kandy.letsplot.internal.SLOPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.AB_LINE
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ABLineTests {
    private val type = listOf("dot").toColumn("type")
    private val color = listOf("blue").toColumn("color")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val width = listOf(0.9).toColumn("width")
    private val slope = listOf(2).toColumn("slope")
    private val intercept = listOf(0).toColumn("intercept")

    private val df = dataFrameOf(type, color, alpha, width, slope, intercept)

    private val parentContext = DataFramePlotContext(df)
    private lateinit var context: ABLineContext

    @BeforeTest
    fun setUp() {
        context = ABLineContext(parentContext)
    }

    @Test
    fun `geom is AB_LINE`() {
        assertEquals(AB_LINE, context.geom)
    }

    @Test
    fun `requiredAes contains SLOPE and INTERCEPT`() {
        assertTrue(context.requiredAes.contains(SLOPE))
        assertTrue(context.requiredAes.contains(INTERCEPT))
    }

    @Test
    fun `color const for abline`() {
        context.color = Color.BLUE
        assertEquals(Color.BLUE, (context.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for abline`() {
        context.color("color")
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for abline`() {
        context.color(color)
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for abline`() {
        context.color(listOf("red"))
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type const for abline`() {
        context.type = LineType.DOTDASH
        assertEquals(LineType.DOTDASH, (context.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `type str mapping for abline`() {
        context.type("type")
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type dataColumn mapping for abline`() {
        context.type(type)
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type iterable mapping for abline`() {
        context.type(listOf("dot"))
        assertEquals("linetype", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for abline`() {
        context.alpha = 0.1
        assertEquals(0.1, (context.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for abline`() {
        context.alpha("alpha")
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for abline`() {
        context.alpha(alpha)
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for abline`() {
        context.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width const for abline`() {
        context.width = .5
        assertEquals(.5, (context.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `width str mapping for abline`() {
        context.width("width")
        assertEquals("width", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width dataColumn mapping for abline`() {
        context.width(width)
        assertEquals("width", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width iterable mapping for abline`() {
        context.width(listOf(0.2, .1))
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `slope const for abline`() {
        context.slope.constant(2)
        assertEquals(2, (context.bindingCollector.settings[SLOPE] as PositionalSetting<*>).value)
    }

    @Test
    fun `slope str mapping for abline`() {
        context.slope("slope")
        assertEquals("slope", (context.bindingCollector.mappings[SLOPE] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `slope dataColumn mapping for abline`() {
        context.slope(slope)
        assertEquals("slope", (context.bindingCollector.mappings[SLOPE] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `slope iterable mapping for abline`() {
        context.slope(listOf(1, 2))
        assertEquals("slope", (context.bindingCollector.mappings[SLOPE] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `intercept const for abline`() {
        context.intercept.constant(0)
        assertEquals(0, (context.bindingCollector.settings[INTERCEPT] as PositionalSetting<*>).value)
    }

    @Test
    fun `intercept str mapping for abline`() {
        context.intercept("intercept")
        assertEquals("intercept", (context.bindingCollector.mappings[INTERCEPT] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `intercept dataColumn mapping for abline`() {
        context.intercept(intercept)
        assertEquals("intercept", (context.bindingCollector.mappings[INTERCEPT] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `intercept iterable mapping for abline`() {
        context.intercept(listOf(0.2, .1))
        assertEquals("intercept", (context.bindingCollector.mappings[INTERCEPT] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `xy modify for abline`() {
        context.x {
            axis {
                name = "xAxis"
            }
            scale = continuous(-0.9, 3.1)
        }
        context.y {
            axis {
                name = "yAxis"
            }
        }

        assertEquals(X, context.bindingCollector.freeScales[X]?.aes)
        assertEquals(
            "xAxis",
            (context.bindingCollector.freeScales[X]?.parameters as? LetsPlotPositionalMappingParameters<*>)?.axis?.name
        )

        assertEquals(Y, context.bindingCollector.freeScales[Y]?.aes)
        assertEquals(
            "yAxis",
            (context.bindingCollector.freeScales[Y]?.parameters as? LetsPlotPositionalMappingParameters<*>)?.axis?.name
        )

    }
}
