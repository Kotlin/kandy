package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.ALPHA
import org.jetbrains.kotlinx.kandy.letsplot.internal.COLOR
import org.jetbrains.kotlinx.kandy.letsplot.internal.FILL
import org.jetbrains.kotlinx.kandy.letsplot.internal.SHAPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import org.jetbrains.kotlinx.kandy.letsplot.internal.STROKE
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.POINT
import org.jetbrains.kotlinx.kandy.letsplot.settings.Symbol
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PointsTests {
    private val xAxis = listOf(0.5).toColumn("xAxis")
    private val yAxis = listOf("first").toColumn("yAxis")
    private val symbol = listOf("dot").toColumn("symbol")
    private val color = listOf("black").toColumn("color")
    private val fillColor = listOf("blue").toColumn("fillColor")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val size = listOf(0.9).toColumn("size")
    private val stroke = listOf(1.9).toColumn("stroke")

    private val df = dataFrameOf(xAxis, yAxis, symbol, color, fillColor, alpha, size, stroke)

    private val parentContext = DataFramePlotBuilder(df)
    private lateinit var context: PointsContext

    @BeforeTest
    fun setUp() {
        context = PointsContext(parentContext)
    }

    @Test
    fun `geom is POINT`() {
        assertEquals(POINT, context.geom)
    }

    @Test
    fun `requiredAes contains X and Y`() {
        assertTrue(context.requiredAes.contains(X))
        assertTrue(context.requiredAes.contains(Y))
    }

    @Test
    fun `color const for points`() {
        context.color = Color.YELLOW
        assertEquals(Color.YELLOW, (context.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for points`() {
        context.color("color")
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for points`() {
        context.color(color)
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for points`() {
        context.color(listOf("red"))
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor const for points`() {
        context.fillColor = Color.BLUE
        assertEquals(Color.BLUE, (context.bindingCollector.settings[FILL] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `fillColor str mapping for points`() {
        context.fillColor("fillColor")
        assertEquals("fillColor", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor dataColumn mapping for points`() {
        context.fillColor(fillColor)
        assertEquals("fillColor", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor iterable mapping for points`() {
        context.fillColor(listOf("red", "white"))
        assertEquals("fill", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `symbol const for points`() {
        context.symbol = Symbol.ASTERIX
        assertEquals(Symbol.ASTERIX, (context.bindingCollector.settings[SHAPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `symbol str mapping for points`() {
        context.symbol("symbol")
        assertEquals("symbol", (context.bindingCollector.mappings[SHAPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `symbol dataColumn mapping for points`() {
        context.symbol(symbol)
        assertEquals("symbol", (context.bindingCollector.mappings[SHAPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `symbol iterable mapping for points`() {
        context.symbol(listOf("diamond"))
        assertEquals("shape", (context.bindingCollector.mappings[SHAPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for points`() {
        context.alpha = 0.1
        assertEquals(0.1, (context.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for points`() {
        context.alpha("alpha")
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for points`() {
        context.alpha(alpha)
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for points`() {
        context.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size const for points`() {
        context.size = 3.1
        assertEquals(3.1, (context.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `size str mapping for points`() {
        context.size("size")
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size dataColumn mapping for points`() {
        context.size(size)
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size iterable mapping for points`() {
        context.size(listOf(5, 10))
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `stroke const for points`() {
        context.stroke = 3
        assertEquals(3.0, (context.bindingCollector.settings[STROKE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `stroke str mapping for points`() {
        context.stroke("stroke")
        assertEquals("stroke", (context.bindingCollector.mappings[STROKE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `stroke dataColumn mapping for points`() {
        context.stroke(stroke)
        assertEquals("stroke", (context.bindingCollector.mappings[STROKE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `stroke iterable mapping for points`() {
        context.stroke(listOf(1.5, 8.0))
        assertEquals("stroke", (context.bindingCollector.mappings[STROKE] as NonPositionalMapping<*, *>).columnID)
        println(((context.bindingCollector.mappings[STROKE] as NonPositionalMapping<*, *>).parameters as NonPositionalMappingParameters).scale)

    }

    @Test
    fun `x const for points`() {
        context.x.constant(5.0)
        assertEquals(X, (context.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(5.0, (context.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `y const for points`() {
        context.y.constant(10)
        assertEquals(Y, (context.bindingCollector.settings[Y] as PositionalSetting<*>).aes)
        assertEquals(10, (context.bindingCollector.settings[Y] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for points`() {
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
    fun `y for points`() {
        context.y(yAxis) {
            axis {
                name = "x axis"
            }
            scale = categorical(listOf("one", "two"))
        }

        assertEquals(Y, context.bindingCollector.mappings[Y]?.aes)
        assertEquals("yAxis", context.bindingCollector.mappings[Y]?.columnID)
        assertEquals(
            listOf("one", "two"),
            (context.bindingCollector.mappings[Y]?.parameters?.scale as PositionalCategoricalScale<*>).categories
        )
    }
}