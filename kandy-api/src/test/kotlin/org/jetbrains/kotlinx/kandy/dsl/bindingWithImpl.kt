/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl

import io.mockk.mockk
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.kandy.dsl.impl.*
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.ir.scale.*
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.test.Test
import kotlin.test.assertEquals

class BindingImplTest {

    class TestContext(parent: LayerCollectorContext) : LayerContext(parent), WithColor, WithSize, WithX, WithY {
        override val geom: Geom
            get() = mockk()
        override val requiredAes: Set<Aes> = setOf()
    }

    @Test
    fun testSetting() {
        val valueDouble = 34.831
        val plotContext = DataFramePlotContext(DataFrame.Empty)
        val context = TestContext(plotContext).apply {
            size = valueDouble
        }
        assertEquals(
            mapOf(SIZE to NonPositionalSetting(SIZE, valueDouble)),
            context.bindingCollector.settings.toMap()
        )

        val valueColor = Color.hex("#1337aa")
        context.apply {
            color = valueColor
        }
        assertEquals(
            mapOf(
                SIZE to NonPositionalSetting(SIZE, valueDouble),
                COLOR to NonPositionalSetting<Color>(COLOR, valueColor)
            ),
            context.bindingCollector.settings.toMap()
        )
    }

    @Test
    fun testMappingNonScalable() {
        val plotContext = DataFramePlotContext(
            dataFrameOf("mock_source" to listOf<Double>())
        )
        val mockSource = column<Double>("mock_source")
        val context = TestContext(plotContext).apply {
            x(mockSource)
        }
        assertEquals<Map<Aes, Mapping>>(
            mapOf(X to PositionalMapping<Double>(X, mockSource.name(), CommonPositionalMappingParametersContinuous())),
            context.bindingCollector.mappings
        )
    }

    @Test
    fun testMappingUnscaled() {
        val plotContext = DataFramePlotContext(
            dataFrameOf("mock_source" to listOf<Int>())
        )
        val mockSource = column<Int>("mock_source")
        val context = TestContext(plotContext).apply {
            size(mockSource)
        }
        assertEquals<Map<Aes, Mapping>>(
            mapOf(
                SIZE to NonPositionalMapping<Int, String>(
                    SIZE,
                    mockSource.name(),
                    CommonNonPositionalMappingParametersContinuous()
                )
            ),
            context.bindingCollector.mappings
        )
    }

    @Test
    fun testMappingScaledUnspecified() {
        val plotContext = DataFramePlotContext(
            dataFrameOf("mock_source" to listOf<String>())
        )
        val mockSource = column<String>("mock_source")
        val context = TestContext(plotContext).apply {
            y(mockSource)
        }
        assertEquals<Map<Aes, Mapping>>(
            mapOf(
                Y to PositionalMapping<String>(
                    Y, mockSource.name(),
                    CommonPositionalMappingParametersContinuous()
                )
            ),
            context.bindingCollector.mappings
        )
    }

    @Test
    fun testMappingScaledPositionalDefault() {
        val plotContext = DataFramePlotContext(
            dataFrameOf("mock_source" to listOf<Float>())
        )
        val mockSource = column<Float>("mock_source")
        val context = TestContext(plotContext).apply {
            x(mockSource) {
                scale = continuous()
            }
        }
        assertEquals<Map<Aes, Mapping>>(
            mapOf(
                X to PositionalMapping(
                    X, mockSource.name(), CommonPositionalMappingParametersContinuous(
                        PositionalContinuousScale<Float>(null, null, null, null)
                    )
                )
            ),
            context.bindingCollector.mappings
        )
    }

    @Test
    fun testMappingScaledNonPositionalDefault() {
        val plotContext = DataFramePlotContext(
            dataFrameOf("mock_source" to listOf<String>())
        )
        val mockSource = column<String>("mock_source")
        val context = TestContext(plotContext).apply {
            color(mockSource)
            color(mockSource) {
                scale = categorical()
            }
        }
        assertEquals<Map<Aes, Mapping>>(
            mapOf(
                COLOR to NonPositionalMapping<String, Color>(
                    COLOR,
                    mockSource.name(),
                    CommonNonPositionalMappingParametersContinuous(NonPositionalCategoricalScale(null, null))
                )
            ),
            context.bindingCollector.mappings
        )
    }

    @Test
    fun testMappingScaledPositional() {
        val plotContext = DataFramePlotContext(
            dataFrameOf("mock_source" to listOf<String>())
        )
        val mockSource = column<String>("mock_source")

        val scaleCatPos = Scale.categoricalPos(
            categories = listOf("cat1", "cat2", "cat3")
        )
        val context = TestContext(plotContext).apply {
            y(mockSource) {
                scale = scaleCatPos
            }
        }
        assertEquals<Map<Aes, Mapping>>(
            mapOf(
                Y to PositionalMapping(
                    Y,
                    mockSource.name(),
                    CommonPositionalMappingParametersContinuous(
                        PositionalCategoricalScale(listOf("cat1", "cat2", "cat3"))
                    )
                )
            ),
            context.bindingCollector.mappings
        )
    }

    @Test
    fun testMappingScaledNonPositional() {
        val plotContext = DataFramePlotContext(
            dataFrameOf("mock_source" to listOf<Int>())
        )
        val mockSource = column<Int>("mock_source")
        val scaleColorCont = Scale.continuous<Int, Color>(
            range = Color.rgb(1, 1, 1)..Color.rgb(1, 100, 100)
        )
        val context = TestContext(plotContext).apply {
            color(mockSource) {
                scale = scaleColorCont
            }
        }
        assertEquals<Map<Aes, Mapping>>(
            mapOf(
                COLOR to NonPositionalMapping<Int, Color>(
                    COLOR,
                    mockSource.name(),
                    CommonNonPositionalMappingParametersContinuous(
                        NonPositionalContinuousScale(
                            null,
                            null,
                            Color.rgb(1, 1, 1),
                            Color.rgb(1, 100, 100),
                            null,
                            null
                        )
                    )
                )
            ),
            context.bindingCollector.mappings
        )
    }
}
