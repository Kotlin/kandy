package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalSetting
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.ALPHA
import org.jetbrains.kotlinx.kandy.letsplot.internal.FILL
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.RIBBON
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Suppress("INVISIBLE_MEMBER")
class RibbonTests {
    private val xAxis = listOf(0.5).toColumn("xAxis")
    private val yMin = listOf(1).toColumn("yMin")
    private val yMax = listOf(5).toColumn("yMax")
    private val color = listOf("blue").toColumn("color")
    private val alpha = listOf(0.5).toColumn("alpha")

    private val df = dataFrameOf(xAxis, yMin, yMax, color, alpha)

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: RibbonBuilder

    @BeforeTest
    fun setUp() {
        builder = RibbonBuilder(parentBuilder)
    }

    @Test
    fun `geom is RIBBON`() {
        assertEquals(RIBBON, builder.geom)
    }

    @Test
    fun `requiredAes contains X_MIN, X_MAX, Y_MIN, Y_MAX`() {
        assertTrue(builder.requiredAes.contains(X))
        assertTrue(builder.requiredAes.contains(Y_MIN))
        assertTrue(builder.requiredAes.contains(Y_MAX))
    }

    @Test
    fun `color const for ribbon`() {
        builder.fillColor = Color.BLUE
        assertEquals(Color.BLUE, (builder.bindingCollector.settings[FILL] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for ribbon`() {
        builder.fillColor("color")
        assertEquals("color", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for ribbon`() {
        builder.fillColor(color)
        assertEquals("color", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for ribbon`() {
        builder.fillColor(listOf("red"))
        assertEquals("fill", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for ribbon`() {
        builder.alpha = 0.1
        assertEquals(0.1, (builder.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for ribbon`() {
        builder.alpha("alpha")
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for ribbon`() {
        builder.alpha(alpha)
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for ribbon`() {
        builder.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `x const for ribbon`() {
        builder.x.constant(5.0)
        assertEquals(X, (builder.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(5.0, (builder.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for ribbon`() {
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
    fun `yMin for ribbon`() {
        builder.yMin(yMin)
        assertEquals(Y_MIN, builder.bindingCollector.mappings[Y_MIN]?.aes)
        assertEquals("yMin", builder.bindingCollector.mappings[Y_MIN]?.columnID)
    }

    @Test
    fun `yMax for ribbon`() {
        builder.yMax(yMax)
        assertEquals(Y_MAX, builder.bindingCollector.mappings[Y_MAX]?.aes)
        assertEquals("yMax", builder.bindingCollector.mappings[Y_MAX]?.columnID)
    }
}