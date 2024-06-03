package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.letsplot.internal.ALPHA
import org.jetbrains.kotlinx.kandy.letsplot.internal.COLOR
import org.jetbrains.kotlinx.kandy.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_INTERCEPT
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.V_LINE
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Suppress("INVISIBLE_MEMBER")
class VLineTests {
    private val xIntercept = listOf(3).toColumn("xIntercept")
    private val type = listOf("dot").toColumn("type")
    private val color = listOf("blue").toColumn("color")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val width = listOf(0.9).toColumn("width")

    private val df = dataFrameOf(xIntercept, type, color, alpha, width)

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: VLineBuilder

    @BeforeTest
    fun setUp() {
        builder = VLineBuilder(parentBuilder)
    }

    @Test
    fun `geom is V_LINE`() {
        assertEquals(V_LINE, builder.geom)
    }

    @Test
    fun `requiredAes contains X_INTERCEPT`() {
        assertTrue(builder.requiredAes.contains(X_INTERCEPT))
    }

    @Test
    fun `color const for vLine`() {
        builder.color = Color.BLUE
        assertEquals(Color.BLUE, (builder.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for vLine`() {
        builder.color("color")
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for vLine`() {
        builder.color(color)
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for vLine`() {
        builder.color(listOf("red"))
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type const for vLine`() {
        builder.type = LineType.DOTDASH
        assertEquals(LineType.DOTDASH, (builder.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `type str mapping for vLine`() {
        builder.type("type")
        assertEquals("type", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type dataColumn mapping for vLine`() {
        builder.type(type)
        assertEquals("type", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type iterable mapping for vLine`() {
        builder.type(listOf("dot"))
        assertEquals("linetype", (builder.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for vLine`() {
        builder.alpha = 0.1
        assertEquals(0.1, (builder.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for vLine`() {
        builder.alpha("alpha")
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for vLine`() {
        builder.alpha(alpha)
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for vLine`() {
        builder.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width const for vLine`() {
        builder.width = .5
        assertEquals(.5, (builder.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `width str mapping for vLine`() {
        builder.width("width")
        assertEquals("width", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width dataColumn mapping for vLine`() {
        builder.width(width)
        assertEquals("width", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `width iterable mapping for vLine`() {
        builder.width(listOf(0.2, .1))
        assertEquals("size", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `y free for vLine`() {
        builder.y {
            axis {
                name = "yAxis"
            }
            scale = continuous(-3.0, 3.0)
        }

        assertEquals(Y, builder.bindingCollector.freeScales[Y]?.aes)
        assertEquals(
            "yAxis",
            (builder.bindingCollector.freeScales[Y]?.parameters as? LetsPlotPositionalMappingParameters<*>)?.axis?.name
        )
    }

    @Test
    fun `x intercept const for vLine`() {
        builder.xIntercept.constant(3)
        assertEquals(3, (builder.bindingCollector.settings[X_INTERCEPT] as? PositionalSetting<*>)?.value)
    }

    @Test
    fun `x intercept mapping for vLine`() {
        builder.xIntercept(xIntercept)

        assertEquals(X_INTERCEPT, builder.bindingCollector.mappings[X_INTERCEPT]?.aes)
        assertEquals("xIntercept", (builder.bindingCollector.mappings[X_INTERCEPT] as? PositionalMapping<*>)?.columnID)
    }
}
