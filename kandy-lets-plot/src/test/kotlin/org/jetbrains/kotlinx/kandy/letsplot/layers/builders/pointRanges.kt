package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DataFramePlotBuilder
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
import org.jetbrains.kotlinx.kandy.letsplot.internal.STROKE
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.InnerLine
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.InnerPoint
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.POINT_RANGE
import org.jetbrains.kotlinx.kandy.letsplot.settings.LineType
import org.jetbrains.kotlinx.kandy.letsplot.settings.Symbol
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Suppress("INVISIBLE_MEMBER")
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

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: PointRangesBuilder

    @BeforeTest
    fun setUp() {
        builder = PointRangesBuilder(parentBuilder)
    }

    @Test
    fun `geom is POINT_RANGE`() {
        assertEquals(POINT_RANGE, builder.geom)
    }

    @Test
    fun `requiredAes contains X, Y, Y_MIN, Y_MAX`() {
        assertTrue(builder.requiredAes.contains(X))
        assertTrue(builder.requiredAes.contains(Y))
        assertTrue(builder.requiredAes.contains(Y_MIN))
        assertTrue(builder.requiredAes.contains(Y_MAX))
    }

    @Test
    fun `color const for pointRange`() {
        builder.color = Color.BLUE
        assertEquals(Color.BLUE, (builder.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for pointRange`() {
        builder.color("color")
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for pointRange`() {
        builder.color(color)
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for pointRange`() {
        builder.color(listOf("red"))
        assertEquals("color", (builder.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for pointRange`() {
        builder.alpha = 0.1
        assertEquals(0.1, (builder.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for pointRange`() {
        builder.alpha("alpha")
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for pointRange`() {
        builder.alpha(alpha)
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for pointRange`() {
        builder.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size const for pointRange`() {
        builder.size = .5
        assertEquals(.5, (builder.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `size str mapping for pointRange`() {
        builder.size("size")
        assertEquals("size", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size dataColumn mapping for pointRange`() {
        builder.size(size)
        assertEquals("size", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size iterable mapping for pointRange`() {
        builder.size(listOf(0.2, .1))
        assertEquals("size", (builder.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `x const for pointRange`() {
        builder.x.constant(5.0)
        assertEquals(X, (builder.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(5.0, (builder.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `y const for pointRange`() {
        builder.y.constant(10)
        assertEquals(Y, (builder.bindingCollector.settings[Y] as PositionalSetting<*>).aes)
        assertEquals(10, (builder.bindingCollector.settings[Y] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for pointRange`() {
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
    fun `y for pointRange`() {
        builder.y(yAxis) {
            axis {
                name = "x axis"
            }
            scale = categorical(listOf(1, 2, 3))
        }

        assertEquals(Y, builder.bindingCollector.mappings[Y]?.aes)
        assertEquals("yAxis", builder.bindingCollector.mappings[Y]?.columnID)
        assertEquals(
            listOf(1, 2, 3),
            (builder.bindingCollector.mappings[Y]?.parameters?.scale as PositionalCategoricalScale<*>).categories
        )
    }
}

@Suppress("INVISIBLE_MEMBER")
class InnerPointTests {
    private val symbol = listOf("rect").toColumn("symbol")
    private val color = listOf("blue").toColumn("color")
    private val fatten = listOf(0.5).toColumn("fatten")
    private val stroke = listOf(1.5).toColumn("stroke")

    private val df = dataFrameOf(symbol, color, fatten, stroke)

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: InnerPoint

    @BeforeTest
    fun setUp() {
        builder = InnerPoint(parentBuilder.bindingHandler)
    }

    @Test
    fun `fillColor const for innerPoint`() {
        builder.fillColor = Color.BLUE
        assertEquals(Color.BLUE, (builder.bindingHandler.bindingCollector.settings[FILL] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `fillColor str mapping for innerPoint`() {
        builder.fillColor("color")
        assertEquals("color", (builder.bindingHandler.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor dataColumn mapping for innerPoint`() {
        builder.fillColor(color)
        assertEquals("color", (builder.bindingHandler.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor iterable mapping for innerPoint`() {
        builder.fillColor(listOf("red"))
        assertEquals("fill", (builder.bindingHandler.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `symbol const for innerPoint`() {
        builder.symbol = Symbol.DIAMOND
        assertEquals(Symbol.DIAMOND, (builder.bindingHandler.bindingCollector.settings[SHAPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `symbol str mapping for innerPoint`() {
        builder.symbol("symbol")
        assertEquals("symbol", (builder.bindingHandler.bindingCollector.mappings[SHAPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `symbol dataColumn mapping for innerPoint`() {
        builder.symbol(symbol)
        assertEquals("symbol", (builder.bindingHandler.bindingCollector.mappings[SHAPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `symbol iterable mapping for innerPoint`() {
        builder.symbol(listOf(4))
        assertEquals("shape", (builder.bindingHandler.bindingCollector.mappings[SHAPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `stroke const for innerPoint`() {
        builder.stroke = 3.5
        assertEquals(3.5, (builder.bindingHandler.bindingCollector.settings[STROKE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `stroke str mapping for innerPoint`() {
        builder.stroke("stroke")
        assertEquals("stroke", (builder.bindingHandler.bindingCollector.mappings[STROKE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `stroke dataColumn mapping for innerPoint`() {
        builder.stroke(stroke)
        assertEquals("stroke", (builder.bindingHandler.bindingCollector.mappings[STROKE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `stroke iterable mapping for innerPoint`() {
        builder.stroke(listOf(1))
        assertEquals("stroke", (builder.bindingHandler.bindingCollector.mappings[STROKE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fatten const for innerPoint`() {
        builder.fatten = .3
        assertEquals(.3, (builder.bindingHandler.bindingCollector.settings[FATTEN] as NonPositionalSetting<*>).value)
    }
}

@Suppress("INVISIBLE_MEMBER")
class InnerLineTests {
    private val type = listOf("dot").toColumn("type")

    private val df = dataFrameOf(type)

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: InnerLine

    @BeforeTest
    fun setUp() {
        builder = InnerLine(parentBuilder.bindingHandler)
    }

    @Test
    fun `type const for innerLine`() {
        builder.type = LineType.TWODASH
        assertEquals(LineType.TWODASH, (builder.bindingHandler.bindingCollector.settings[LINE_TYPE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `type str mapping for innerLine`() {
        builder.type("type")
        assertEquals("type", (builder.bindingHandler.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type dataColumn mapping for innerLine`() {
        builder.type(type)
        assertEquals("type", (builder.bindingHandler.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `type iterable mapping for innerLine`() {
        builder.type(listOf("dot"))
        assertEquals("linetype", (builder.bindingHandler.bindingCollector.mappings[LINE_TYPE] as NonPositionalMapping<*, *>).columnID)
    }
}