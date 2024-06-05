package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toColumn
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.RECT
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Suppress("INVISIBLE_MEMBER")
class RectTests {
    private val xMin = listOf(0.1).toColumn("xMin")
    private val xMax = listOf(2.1).toColumn("xMax")
    private val yMin = listOf(1).toColumn("yMin")
    private val yMax = listOf(5).toColumn("yMax")
    private val color = listOf("blue").toColumn("color")
    private val alpha = listOf(0.5).toColumn("alpha")

    private val df = dataFrameOf(xMin, xMax, yMin, yMax, color, alpha)

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: RectanglesBuilder

    @BeforeTest
    fun setUp() {
        builder = RectanglesBuilder(parentBuilder)
    }

    @Test
    fun `geom is RECT`() {
        assertEquals(RECT, builder.geom)
    }

    @Test
    fun `requiredAes contains X_MIN, X_MAX, Y_MIN, Y_MAX`() {
        assertTrue(builder.requiredAes.contains(X_MIN))
        assertTrue(builder.requiredAes.contains(X_MAX))
        assertTrue(builder.requiredAes.contains(Y_MIN))
        assertTrue(builder.requiredAes.contains(Y_MAX))
    }

    @Test
    fun `fillColor const for rect`() {
        builder.fillColor = Color.BLUE
        assertEquals(Color.BLUE, (builder.bindingCollector.settings[FILL] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `fillColor str mapping for rect`() {
        builder.fillColor("color")
        assertEquals("color", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor dataColumn mapping for rect`() {
        builder.fillColor(color)
        assertEquals("color", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `fillColor iterable mapping for rect`() {
        builder.fillColor(listOf("red"))
        assertEquals("fill", (builder.bindingCollector.mappings[FILL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha const for rect`() {
        builder.alpha = 0.1
        assertEquals(0.1, (builder.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for rect`() {
        builder.alpha("alpha")
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for rect`() {
        builder.alpha(alpha)
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for rect`() {
        builder.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (builder.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `xMin for rect`() {
        builder.xMin(xMin)
        assertEquals(X_MIN, builder.bindingCollector.mappings[X_MIN]?.aes)
        assertEquals("xMin", builder.bindingCollector.mappings[X_MIN]?.columnID)
    }

    @Test
    fun `xMax for rect`() {
        builder.xMax(xMax)
        assertEquals(X_MAX, builder.bindingCollector.mappings[X_MAX]?.aes)
        assertEquals("xMax", builder.bindingCollector.mappings[X_MAX]?.columnID)
    }

    @Test
    fun `yMin for rect`() {
        builder.yMin(yMin)
        assertEquals(Y_MIN, builder.bindingCollector.mappings[Y_MIN]?.aes)
        assertEquals("yMin", builder.bindingCollector.mappings[Y_MIN]?.columnID)
    }

    @Test
    fun `yMax for rect`() {
        builder.yMax(yMax)
        assertEquals(Y_MAX, builder.bindingCollector.mappings[Y_MAX]?.aes)
        assertEquals("yMax", builder.bindingCollector.mappings[Y_MAX]?.columnID)
    }
}
