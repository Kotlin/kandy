package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.ALPHA
import org.jetbrains.kotlinx.kandy.letsplot.internal.COLOR
import org.jetbrains.kotlinx.kandy.letsplot.internal.FATTEN
import org.jetbrains.kotlinx.kandy.letsplot.internal.FILL
import org.jetbrains.kotlinx.kandy.letsplot.internal.LINE_TYPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.SHAPE
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.POINT_RANGE
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.letsplot.settings.Symbol
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PointRangeTests {
    private val xAxis = listOf(0.5).toColumn("xAxis")
    private val yAxis = listOf(3).toColumn("yAxis")
    private val yMin = listOf(1).toColumn("yMin")
    private val yMax = listOf(5).toColumn("yMax")
    private val type = listOf("dot").toColumn("type")
    private val color = listOf("blue").toColumn("color")
    private val alpha = listOf(0.5).toColumn("alpha")
    private val size = listOf(1.9).toColumn("size")

    private val df = dataFrameOf(xAxis, yAxis, yMin, yMax, type, color, alpha, size)

    private val parentContext = DataFramePlotContext(df)
    private lateinit var context: PointRangesContext

    @BeforeTest
    fun setUp() {
        context = PointRangesContext(parentContext)
    }

    @Test
    fun `geom is POINT_RANGE`() {
        assertEquals(POINT_RANGE, context.geom)
    }

    @Test
    fun `requiredAes contains X, Y, Y_MIN, Y_MAX`() {
        assertTrue(context.requiredAes.contains(X))
        assertTrue(context.requiredAes.contains(Y))
        assertTrue(context.requiredAes.contains(Y_MIN))
        assertTrue(context.requiredAes.contains(Y_MAX))
    }

    @Test
    fun `color const for pointRange`() {
        context.color = Color.BLUE
        assertEquals(Color.BLUE, (context.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for pointRange`() {
        context.color("color")
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for pointRange`() {
        context.color(color)
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for pointRange`() {
        context.color(listOf("red"))
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for pointRange`() {
        context.alpha = 0.1
        assertEquals(0.1, (context.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for pointRange`() {
        context.alpha("alpha")
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for pointRange`() {
        context.alpha(alpha)
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for pointRange`() {
        context.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size const for pointRange`() {
        context.size = .5
        assertEquals(.5, (context.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `size str mapping for pointRange`() {
        context.size("size")
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size dataColumn mapping for pointRange`() {
        context.size(size)
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size iterable mapping for pointRange`() {
        context.size(listOf(0.2, .1))
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `x const for pointRange`() {
        context.x.constant(5.0)
        assertEquals(X, (context.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(5.0, (context.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `y const for pointRange`() {
        context.y.constant(10)
        assertEquals(Y, (context.bindingCollector.settings[Y] as PositionalSetting<*>).aes)
        assertEquals(10, (context.bindingCollector.settings[Y] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for pointRange`() {
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
    fun `y for pointRange`() {
        context.y(yAxis) {
            axis {
                name = "x axis"
            }
            scale = categorical(listOf(1, 2, 3))
        }

        assertEquals(Y, context.bindingCollector.mappings[Y]?.aes)
        assertEquals("yAxis", context.bindingCollector.mappings[Y]?.columnID)
        assertEquals(
            listOf(1, 2, 3),
            (context.bindingCollector.mappings[Y]?.parameters?.scale as PositionalCategoricalScale<*>).categories
        )
    }
}

class InnerPointTests {
    private val symbol = listOf("rect").toColumn("symbol")
    private val color = listOf("blue").toColumn("color")
    private val fatten = listOf(0.5).toColumn("fatten")

    private val df = dataFrameOf(symbol, color, fatten)

    private val parentContext = DataFramePlotContext(df)
    private lateinit var context: InnerPointContext

    @BeforeTest
    fun setUp() {
        context = InnerPointContext(parentContext)
    }

    @Test
    fun `fillColor const for innerPoint`() {
        context.fillColor = Color.BLUE
        assertEquals(Color.BLUE, (context.bindingCollector.settings[FILL] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `fillColor str mapping for innerPoint`() {
        context.fillColor("color")
        assertEquals("color", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor dataColumn mapping for innerPoint`() {
        context.fillColor(color)
        assertEquals("color", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor iterable mapping for innerPoint`() {
        context.fillColor(listOf("red"))
        assertEquals("fill", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `symbol const for innerPoint`() {
        context.symbol = Symbol.DIAMOND
        assertEquals(Symbol.DIAMOND, (context.bindingCollector.settings[SHAPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `symbol str mapping for innerPoint`() {
        context.symbol("symbol")
        assertEquals("symbol", (context.bindingCollector.mappings[SHAPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `symbol dataColumn mapping for innerPoint`() {
        context.symbol(symbol)
        assertEquals("symbol", (context.bindingCollector.mappings[SHAPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `symbol iterable mapping for innerPoint`() {
        context.symbol(listOf(1, 2, 3, 4))
        assertEquals("shape", (context.bindingCollector.mappings[SHAPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fatten const for innerPoint`() {
        context.fatten = .3
        assertEquals(.3, (context.bindingCollector.settings[FATTEN] as NonPositionalSetting<*>).value)
    }
}

class InnerLineTests {
    private val type = listOf("dot").toColumn("type")

    private val df = dataFrameOf(type)

    private val parentContext = DataFramePlotContext(df)
    private lateinit var context: InnerLineContext

    @BeforeTest
    fun setUp() {
        context = InnerLineContext(parentContext)
    }

    @Test
    fun `type const for innerLine`() {
        context.type = LineType.TWODASH
        assertEquals(LineType.TWODASH, (context.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `type str mapping for innerLine`() {
        context.type("type")
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type dataColumn mapping for innerLine`() {
        context.type(type)
        assertEquals("type", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type iterable mapping for innerLine`() {
        context.type(listOf("dot"))
        assertEquals("linetype", (context.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }
}