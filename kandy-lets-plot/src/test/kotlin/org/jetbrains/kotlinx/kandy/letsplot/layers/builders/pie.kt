package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.ALPHA
import org.jetbrains.kotlinx.kandy.letsplot.internal.EXPLODE
import org.jetbrains.kotlinx.kandy.letsplot.internal.FILL
import org.jetbrains.kotlinx.kandy.letsplot.internal.HOLE
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import org.jetbrains.kotlinx.kandy.letsplot.internal.SLICE
import org.jetbrains.kotlinx.kandy.letsplot.internal.STROKE
import org.jetbrains.kotlinx.kandy.letsplot.internal.STROKE_COLOR
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.PIE
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PieTests {
    private val xAxis = listOf(0.5).toColumn("xAxis")
    private val yAxis = listOf("first").toColumn("yAxis")
    private val slice = listOf("second").toColumn("slice")
    private val explode = listOf(0.1).toColumn("explode")
    private val size = listOf(1.7).toColumn("size")
    private val color = listOf("blue").toColumn("color")
    private val alpha = listOf(0.5).toColumn("alpha")

    private val df = dataFrameOf(xAxis, yAxis, slice, explode, size, color, alpha)

    private val parentContext = DataFramePlotBuilder(df)
    private lateinit var context: PieContext

    @BeforeTest
    fun setUp() {
        context = PieContext(parentContext)
    }

    @Test
    fun `geom is PIE`() {
        assertEquals(PIE, context.geom)
    }

    @Test
    fun `requiredAes contains `() {
        // TODO(add required aes)
    }

    @Test
    fun `slice str mapping for pie`() {
        context.slice("slice")
        assertEquals("slice", (context.bindingCollector.mappings[SLICE] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `slice dataColumn mapping for pie`() {
        context.slice(slice)
        assertEquals("slice", (context.bindingCollector.mappings[SLICE] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `slice iterable mapping for pie`() {
        context.slice(listOf("two"))
        assertEquals("slice", (context.bindingCollector.mappings[SLICE] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `explode str mapping for pie`() {
        context.explode("explode")
        assertEquals("explode", (context.bindingCollector.mappings[EXPLODE] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `explode dataColumn mapping for pie`() {
        context.explode(explode)
        assertEquals("explode", (context.bindingCollector.mappings[EXPLODE] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `explode iterable mapping for pie`() {
        context.explode(listOf(0.3, 0, .1))
        assertEquals("explode", (context.bindingCollector.mappings[EXPLODE] as PositionalMapping<*>).columnID)
    }

    @Test
    fun `hole const for pie`() {
        context.hole = 0.7
        assertEquals(0.7, (context.bindingCollector.settings[HOLE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `fillColor const for pie`() {
        context.fillColor = Color.BLUE
        assertEquals(Color.BLUE, (context.bindingCollector.settings[FILL] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `fillColor str mapping for pie`() {
        context.fillColor("color")
        assertEquals("color", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor dataColumn mapping for pie`() {
        context.fillColor(color)
        assertEquals("color", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor iterable mapping for pie`() {
        context.fillColor(listOf("red"))
        assertEquals("fill", (context.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size const for pie`() {
        context.size = .5
        assertEquals(.5, (context.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `size str mapping for pie`() {
        context.size("size")
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size dataColumn mapping for pie`() {
        context.size(size)
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size iterable mapping for pie`() {
        context.size(listOf(0.2, .1))
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for pie`() {
        context.alpha = 0.1
        assertEquals(0.1, (context.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for pie`() {
        context.alpha("alpha")
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for pie`() {
        context.alpha(alpha)
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for pie`() {
        context.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `stroke const for pie`() {
        context.stroke = 1.0
        assertEquals(1.0, (context.bindingCollector.settings[STROKE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `strokeColor const for pie`() {
        context.strokeColor = Color.ORANGE
        assertEquals(Color.ORANGE, (context.bindingCollector.settings[STROKE_COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `x const for pie`() {
        context.x.constant(5.0)
        assertEquals(X, (context.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(5.0, (context.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `y const for pie`() {
        context.y.constant(10)
        assertEquals(Y, (context.bindingCollector.settings[Y] as PositionalSetting<*>).aes)
        assertEquals(10, (context.bindingCollector.settings[Y] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for pie`() {
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
    fun `y for pie`() {
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
