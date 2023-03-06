/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.unit

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.ggdsl.dsl.categorical
import org.jetbrains.kotlinx.ggdsl.dsl.categoricalPos
import org.jetbrains.kotlinx.ggdsl.dsl.continuous
import org.jetbrains.kotlinx.ggdsl.dsl.impl.*
import org.jetbrains.kotlinx.ggdsl.dsl.internal.DataFramePlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Mapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import kotlin.test.Test
import kotlin.test.assertEquals

internal class BindingTest {

    class TestContext(parent: LayerCollectorContext) : LayerContext(parent), WithColor, WithSize, WithX, WithY {

    }

    @Test
    fun testSetting() {
        val valueDouble = 34.831
        val plotContext = DataFramePlotContext(DataFrame.Empty)
        val context = TestContext(plotContext).apply {
            size = valueDouble
        }
        assertEquals(
            mapOf(SIZE to NonPositionalSetting<Double>(SIZE, valueDouble)),
            context.bindingCollector.settings.toMap()
        )

        val valueColor = Color.hex("#1337aa")
        context.apply {
            color = valueColor
        }
        assertEquals(
            mapOf(
                SIZE to NonPositionalSetting<Double>(SIZE, valueDouble),
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
        assertEquals<Map<AesName, Mapping>>(
            mapOf(
                X to PositionalMapping<Double>(
                    X,
                    mockSource.name(),
                    CommonPositionalMappingParameters()
                )
            ),
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
        assertEquals<Map<AesName, Mapping>>(
            mapOf(
                SIZE to NonPositionalMapping<Int, String>(
                    SIZE,
                    mockSource.name(),
                    CommonNonPositionalMappingParameters()
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
        assertEquals<Map<AesName, Mapping>>(
            mapOf(
                Y to PositionalMapping<String>(
                    Y, mockSource.name(),
                    CommonPositionalMappingParameters()
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
        assertEquals<Map<AesName, Mapping>>(
            mapOf(
                X to PositionalMapping<Float>(
                    X, mockSource.name(), CommonPositionalMappingParameters(
                        PositionalContinuousScale<Float>(null, null, null))
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
        assertEquals<Map<AesName, Mapping>>(
            mapOf(
                COLOR to NonPositionalMapping<String, Color>(
                    COLOR, mockSource.name(), CommonNonPositionalMappingParameters(
                        NonPositionalCategoricalScale<String, Color>(null, null)
                    )
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
        assertEquals<Map<AesName, Mapping>>(
            mapOf(
                Y to PositionalMapping<String>(
                    Y,
                    mockSource.name(),
                    CommonPositionalMappingParameters(
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
            rangeLimits = Color.rgb(1, 1, 1).. Color.rgb(1, 100, 100)
        )
        val context = TestContext(plotContext).apply {
            color(mockSource) {
                scale = scaleColorCont
            }
        }
        assertEquals<Map<AesName, Mapping>>(
            mapOf(
                COLOR to NonPositionalMapping<Int, Color>(
                    COLOR,
                    mockSource.name(),
                    CommonNonPositionalMappingParameters(
                        NonPositionalContinuousScale(
                            null,
                            Color.rgb(1, 1, 1) .. Color.rgb(1, 100, 100),
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



