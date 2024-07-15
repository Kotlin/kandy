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
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.Font
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.TEXT
import org.jetbrains.kotlinx.kandy.letsplot.settings.font.FontFace
import org.jetbrains.kotlinx.kandy.letsplot.settings.font.FontFamily
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Suppress("INVISIBLE_MEMBER")
class FontTests {
    private val color = listOf("blue").toColumn("color")
    private val size = listOf(1.5).toColumn("size")
    private val face = listOf("Italic").toColumn("face")
    private val family = listOf("Sans").toColumn("family")

    private val df = dataFrameOf(color, size, face, family)

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: Font

    @BeforeTest
    fun setUp() {
        builder = Font(parentBuilder.bindingHandler)
    }

    @Test
    fun `color const for font`() {
        builder.color = Color.BLUE
        assertEquals(
            Color.BLUE,
            (builder.bindingHandler.bindingCollector.settings[COLOR] as NonPositionalSetting<*>).value
        )
    }

    @Test
    fun `color str mapping for font`() {
        builder.color("color")
        assertEquals(
            "color",
            (builder.bindingHandler.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `color dataColumn mapping for font`() {
        builder.color(color)
        assertEquals(
            "color",
            (builder.bindingHandler.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `color iterable mapping for font`() {
        builder.color(listOf("red"))
        assertEquals(
            "color",
            (builder.bindingHandler.bindingCollector.mappings[COLOR] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `face const for font`() {
        builder.face = FontFace.BOLD
        assertEquals(
            FontFace.BOLD,
            (builder.bindingHandler.bindingCollector.settings[FONT_FACE] as NonPositionalSetting<*>).value
        )
    }

    @Test
    fun `face str mapping for font`() {
        builder.face("face")
        assertEquals(
            "face",
            (builder.bindingHandler.bindingCollector.mappings[FONT_FACE] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `face dataColumn mapping for font`() {
        builder.face(face)
        assertEquals(
            "face",
            (builder.bindingHandler.bindingCollector.mappings[FONT_FACE] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `face iterable mapping for font`() {
        builder.face(listOf("1"))
        assertEquals(
            "fontface",
            (builder.bindingHandler.bindingCollector.mappings[FONT_FACE] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `family const for font`() {
        builder.family = FontFamily.MONO
        assertEquals(
            FontFamily.MONO,
            (builder.bindingHandler.bindingCollector.settings[FONT_FAMILY] as NonPositionalSetting<*>).value
        )
    }

    @Test
    fun `family str mapping for font`() {
        builder.family("family")
        assertEquals(
            "family",
            (builder.bindingHandler.bindingCollector.mappings[FONT_FAMILY] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `family dataColumn mapping for font`() {
        builder.family(family)
        assertEquals(
            "family",
            (builder.bindingHandler.bindingCollector.mappings[FONT_FAMILY] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `family iterable mapping for font`() {
        builder.family(listOf("1"))
        assertEquals(
            "family",
            (builder.bindingHandler.bindingCollector.mappings[FONT_FAMILY] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `size const for font`() {
        builder.size = 0.3
        assertEquals(0.3, (builder.bindingHandler.bindingCollector.settings[SIZE] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `size str mapping for font`() {
        builder.size("size")
        assertEquals(
            "size",
            (builder.bindingHandler.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `size dataColumn mapping for font`() {
        builder.size(size)
        assertEquals(
            "size",
            (builder.bindingHandler.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `size iterable mapping for font`() {
        builder.size(listOf(0.2))
        assertEquals(
            "size",
            (builder.bindingHandler.bindingCollector.mappings[SIZE] as NonPositionalMapping<*, *>).columnID
        )
    }
}

@Suppress("INVISIBLE_MEMBER")
class TextTests {
    private val xAxis = listOf(0.5).toColumn("xAxis")
    private val yAxis = listOf("first").toColumn("yAxis")
    private val label = listOf("dot").toColumn("label")
    private val alpha = listOf(0.5).toColumn("alpha")

    private val df = dataFrameOf(xAxis, yAxis, label, alpha)

    private val parentBuilder = DataFramePlotBuilder(df)
    private lateinit var builder: TextBuilder

    @BeforeTest
    fun setUp() {
        builder = TextBuilder(parentBuilder)
    }

    @Test
    fun `geom is TEXT`() {
        assertEquals(TEXT, builder.geom)
    }

    @Test
    fun `requiredAes contains X and Y`() {
        assertTrue(builder.requiredAes.contains(X))
        assertTrue(builder.requiredAes.contains(Y))
    }

    @Test
    fun `alpha const for text`() {
        builder.alpha = 0.1
        assertEquals(0.1, (builder.bindingHandler.bindingCollector.settings[ALPHA] as NonPositionalSetting<*>).value)
    }

    @Test
    fun `alpha str mapping for text`() {
        builder.alpha("alpha")
        assertEquals(
            "alpha",
            (builder.bindingHandler.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `alpha dataColumn mapping for text`() {
        builder.alpha(alpha)
        assertEquals(
            "alpha",
            (builder.bindingHandler.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `alpha iterable mapping for text`() {
        builder.alpha(listOf(0.2, 0.5, .1))
        assertEquals(
            "alpha",
            (builder.bindingHandler.bindingCollector.mappings[ALPHA] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `label const for text`() {
        builder.label = "label"
        assertEquals(
            "label",
            (builder.bindingHandler.bindingCollector.settings[LABEL] as NonPositionalSetting<*>).value
        )
    }

    @Test
    fun `label str mapping for text`() {
        builder.label("label")
        assertEquals(
            "label",
            (builder.bindingHandler.bindingCollector.mappings[LABEL] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `label dataColumn mapping for text`() {
        builder.label(label)
        assertEquals(
            "label",
            (builder.bindingHandler.bindingCollector.mappings[LABEL] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `label iterable mapping for text`() {
        builder.label(listOf("1", "2"))
        assertEquals(
            "label",
            (builder.bindingHandler.bindingCollector.mappings[LABEL] as NonPositionalMapping<*, *>).columnID
        )
    }

    @Test
    fun `x const for text`() {
        builder.x.constant(5.0)
        assertEquals(X, (builder.bindingHandler.bindingCollector.settings[X] as PositionalSetting<*>).aes)
        assertEquals(5.0, (builder.bindingHandler.bindingCollector.settings[X] as PositionalSetting<*>).value)
    }

    @Test
    fun `y const for text`() {
        builder.y.constant(10)
        assertEquals(Y, (builder.bindingHandler.bindingCollector.settings[Y] as PositionalSetting<*>).aes)
        assertEquals(10, (builder.bindingHandler.bindingCollector.settings[Y] as PositionalSetting<*>).value)
    }

    @Test
    fun `x for text`() {
        builder.x(xAxis) {
            axis {
                name = "x axis"
            }
            scale = continuous(0.1..3.7)
        }

        assertEquals(X, builder.bindingHandler.bindingCollector.mappings[X]?.aes)
        assertEquals("xAxis", builder.bindingHandler.bindingCollector.mappings[X]?.columnID)
        assertEquals(
            .1,
            (builder.bindingHandler.bindingCollector.mappings[X]?.parameters?.scale as PositionalContinuousScale<*>).min
        )
        assertEquals(
            3.7,
            (builder.bindingHandler.bindingCollector.mappings[X]?.parameters?.scale as PositionalContinuousScale<*>).max
        )
    }

    @Test
    fun `y for text`() {
        builder.y(yAxis) {
            axis {
                name = "x axis"
            }
            scale = categorical(listOf("one", "two"))
        }

        assertEquals(Y, builder.bindingHandler.bindingCollector.mappings[Y]?.aes)
        assertEquals("yAxis", builder.bindingHandler.bindingCollector.mappings[Y]?.columnID)
        assertEquals(
            listOf("one", "two"),
            (builder.bindingHandler.bindingCollector.mappings[Y]?.parameters?.scale as PositionalCategoricalScale<*>).categories
        )
    }
}
