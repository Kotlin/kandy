package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotBuilder
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

@Suppress("INVISIBLE_MEMBER")
class ABLineTests {
    private val type = listOf("dot").toColumn("type")
    private val color = listOf("blue").toColumn("color")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val width = listOf(0.9).toColumn("width")
    private val slope = listOf(2).toColumn("slope")
    private val intercept = listOf(0).toColumn("intercept")

    private val df = dataFrameOf(type, color, alpha, width, slope, intercept)

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: ABLineBuilder

    @BeforeTest
    fun setUp() {
        builder = ABLineBuilder(parentBuilder)
    }

    @Test
    fun `geom is AB_LINE`() {
        assertEquals(AB_LINE, builder.geom)
    }

    @Test
    fun `requiredAes contains SLOPE and INTERCEPT`() {
        assertTrue(builder.requiredAes.contains(SLOPE))
        assertTrue(builder.requiredAes.contains(INTERCEPT))
    }

    @Test
    fun `color const for abline`() {
        builder.color = Color.BLUE
        assertEquals(Color.BLUE, (builder.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for abline`() {
        builder.color("color")
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for abline`() {
        builder.color(color)
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for abline`() {
        builder.color(listOf("red"))
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type const for abline`() {
        builder.type = LineType.DOTDASH
        assertEquals(LineType.DOTDASH, (builder.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `type str mapping for abline`() {
        builder.type("type")
        assertEquals("type", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type dataColumn mapping for abline`() {
        builder.type(type)
        assertEquals("type", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type iterable mapping for abline`() {
        builder.type(listOf("dot"))
        assertEquals("linetype", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for abline`() {
        builder.alpha = 0.1
        assertEquals(0.1, (builder.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for abline`() {
        builder.alpha("alpha")
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for abline`() {
        builder.alpha(alpha)
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for abline`() {
        builder.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width const for abline`() {
        builder.width = .5
        assertEquals(.5, (builder.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `width str mapping for abline`() {
        builder.width("width")
        assertEquals("width", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width dataColumn mapping for abline`() {
        builder.width(width)
        assertEquals("width", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width iterable mapping for abline`() {
        builder.width(listOf(0.2, .1))
        assertEquals("size", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `slope const for abline`() {
        builder.slope.constant(2)
        assertEquals(2, (builder.bindingCollector.settings[SLOPE] as PositionalSetting<*>).value)
    }

    @Test
    fun `slope str mapping for abline`() {
        builder.slope("slope")
        assertEquals("slope", (builder.bindingCollector.mappings[SLOPE] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `slope dataColumn mapping for abline`() {
        builder.slope(slope)
        assertEquals("slope", (builder.bindingCollector.mappings[SLOPE] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `slope iterable mapping for abline`() {
        builder.slope(listOf(1, 2))
        assertEquals("slope", (builder.bindingCollector.mappings[SLOPE] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `intercept const for abline`() {
        builder.intercept.constant(0)
        assertEquals(0, (builder.bindingCollector.settings[INTERCEPT] as PositionalSetting<*>).value)
    }

    @Test
    fun `intercept str mapping for abline`() {
        builder.intercept("intercept")
        assertEquals("intercept", (builder.bindingCollector.mappings[INTERCEPT] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `intercept dataColumn mapping for abline`() {
        builder.intercept(intercept)
        assertEquals("intercept", (builder.bindingCollector.mappings[INTERCEPT] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `intercept iterable mapping for abline`() {
        builder.intercept(listOf(0.2, .1))
        assertEquals("intercept", (builder.bindingCollector.mappings[INTERCEPT] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `xy modify for abline`() {
        builder.x {
            axis {
                name = "xAxis"
            }
            scale = continuous(-0.9, 3.1)
        }
        builder.y {
            axis {
                name = "yAxis"
            }
        }

        assertEquals(X, builder.bindingCollector.freeScales[X]?.aes)
        assertEquals(
            "xAxis",
            (builder.bindingCollector.freeScales[X]?.parameters as? LetsPlotPositionalMappingParameters<*>)?.axis?.name
        )

        assertEquals(Y, builder.bindingCollector.freeScales[Y]?.aes)
        assertEquals(
            "yAxis",
            (builder.bindingCollector.freeScales[Y]?.parameters as? LetsPlotPositionalMappingParameters<*>)?.axis?.name
        )
    }
}
