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
import org.jetbrains.kotlinx.kandy.letsplot.internal.FONT_FACE
import org.jetbrains.kotlinx.kandy.letsplot.internal.FONT_FAMILY
import org.jetbrains.kotlinx.kandy.letsplot.internal.LABEL
import org.jetbrains.kotlinx.kandy.letsplot.internal.SIZE
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.TEXT
import org.jetbrains.kotlinx.kandy.letsplot.settings.font.FontFace
import org.jetbrains.kotlinx.kandy.letsplot.settings.font.FontFamily
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FontTests {
    private val color = listOf("blue").toColumn("color")
    private val size = listOf(1.5).toColumn("size")
    private val face = listOf("Italic").toColumn("face")
    private val family = listOf("Sans").toColumn("family")

    private val df = dataFrameOf(color, size, face, family)

    private val parentContext = DataFramePlotContext(df)
    private lateinit var context: FontContext

    @BeforeTest
    fun setUp() {
        context = FontContext(parentContext)
    }

    @Test
    fun `color const for font`() {
        context.color = Color.BLUE
        assertEquals(Color.BLUE, (context.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `color str mapping for font`() {
        context.color("color")
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color dataColumn mapping for font`() {
        context.color(color)
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `color iterable mapping for font`() {
        context.color(listOf("red"))
        assertEquals("color", (context.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `face const for font`() {
        context.face = FontFace.BOLD
        assertEquals(FontFace.BOLD, (context.bindingCollector.settings[FONT_FACE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `face str mapping for font`() {
        context.face("face")
        assertEquals("face", (context.bindingCollector.mappings[FONT_FACE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `face dataColumn mapping for font`() {
        context.face(face)
        assertEquals("face", (context.bindingCollector.mappings[FONT_FACE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `face iterable mapping for font`() {
        context.face(listOf("1", "2"))
        assertEquals("fontface", (context.bindingCollector.mappings[FONT_FACE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `family const for font`() {
        context.family = FontFamily.MONO
        assertEquals(FontFamily.MONO, (context.bindingCollector.settings[FONT_FAMILY] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `family str mapping for font`() {
        context.family("family")
        assertEquals("family", (context.bindingCollector.mappings[FONT_FAMILY] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `family dataColumn mapping for font`() {
        context.family(family)
        assertEquals("family", (context.bindingCollector.mappings[FONT_FAMILY] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `family iterable mapping for font`() {
        context.family(listOf("1", "2"))
        assertEquals("family", (context.bindingCollector.mappings[FONT_FAMILY] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size const for font`() {
        context.size = 0.3
        assertEquals(0.3, (context.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `size str mapping for font`() {
        context.size("size")
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size dataColumn mapping for font`() {
        context.size(size)
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `size iterable mapping for font`() {
        context.size(listOf(0.2, 0.5, .1))
        assertEquals("size", (context.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID)
    }
}

class TextTests {
    private val xAxis = listOf(0.5).toColumn("xAxis")
    private val yAxis = listOf("first").toColumn("yAxis")
    private val label = listOf("dot").toColumn("label")
    private val alpha = listOf(0.5).toColumn("alpha")

    private val df = dataFrameOf(xAxis, yAxis, label, alpha)

    private val parentContext = DataFramePlotContext(df)
    private lateinit var context: TextContext

    @BeforeTest
    fun setUp() {
        context = TextContext(parentContext)
    }

    @Test
    fun `geom is TEXT`() {
        assertEquals(TEXT, context.geom)
    }

    @Test
    fun `requiredAes contains X and Y`() {
        assertTrue(context.requiredAes.contains(X))
        assertTrue(context.requiredAes.contains(Y))
    }

    @Test
    fun `alpha const for text`() {
        context.alpha = 0.1
        assertEquals(0.1, (context.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for text`() {
        context.alpha("alpha")
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha dataColumn mapping for text`() {
        context.alpha(alpha)
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `alpha iterable mapping for text`() {
        context.alpha(listOf(0.2, 0.5, .1))
        assertEquals("alpha", (context.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `label const for text`() {
        context.label = "label"
        assertEquals("label", (context.bindingCollector.settings[LABEL] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `label str mapping for text`() {
        context.label("label")
        assertEquals("label", (context.bindingCollector.mappings[LABEL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `label dataColumn mapping for text`() {
        context.label(label)
        assertEquals("label", (context.bindingCollector.mappings[LABEL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `label iterable mapping for text`() {
        context.label(listOf("1", "2"))
        assertEquals("label", (context.bindingCollector.mappings[LABEL] as NonPositionalMapping<*, *>).columnID)
    }

    @Test
    fun `x const for text`() {
        context.x.constant(5.0)
        assertEquals(X, (context.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(5.0, (context.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `y const for text`() {
        context.y.constant(10)
        assertEquals(Y, (context.bindingCollector.settings[Y] as PositionalSetting<*>).aes)
        assertEquals(10, (context.bindingCollector.settings[Y] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for text`() {
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
    fun `y for text`() {
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
