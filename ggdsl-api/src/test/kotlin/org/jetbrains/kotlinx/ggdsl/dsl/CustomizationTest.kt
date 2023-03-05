/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl
/*
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.ggdsl.dsl.impl.*
import org.jetbrains.kotlinx.ggdsl.dsl.internal.*
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalContinuousUnspecifiedScale
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CustomizationTest {

    class SpecificAes(override val context: BindingContext) : ScalableNonPositionalAes<CustomGeomType> {
        override val name = SPECIFIC_AES
    }

    companion object {
        val CUSTOM_GEOM = CommonGeom("custom")
        val SPECIFIC_AES = AesName("customAes")
    }

    data class CustomGeomType(val name: String)

    class CustomGeomContextImmutable(parent: LayerCollectorContextImmutable) :
        LayerContextImmutable(parent) {
        val x = XAes(this)
        val y = YAes(this)
        val specificAes = SpecificAes(this)
    }

    fun LayerCollectorContextImmutable.customLayer(block: CustomGeomContextImmutable.() -> Unit) {
        addLayer(CustomGeomContextImmutable(this).apply(block), CUSTOM_GEOM)
    }

    data class MockLayerFeature(val value: Int) : LayerFeature {
        override val featureName = FEATURE_NAME

        companion object {
            val FEATURE_NAME = FeatureName("mock_layer_feature")
        }
    }

    private fun LayerContext.mockFeatureFunction(value: Int) {
        features[MockLayerFeature.FEATURE_NAME] = MockLayerFeature(value)
    }

    data class MockPlotFeature(val title: String) : PlotFeature {
        override val featureName = FEATURE_NAME

        companion object {
            val FEATURE_NAME = FeatureName("mock_plot_feature")
        }
    }

    private var LayerPlotContext.mockFeatureProp: String
        get() = ""
        set(value) {
            features[MockPlotFeature.FEATURE_NAME] = MockPlotFeature(value)
        }


    val mockSrcDouble = column<Double>("mock_double")
    val mockSrcInt = column<Int>("mock_int")
    val mockSrcString = column<String>("mock_string")
    val mockSrcFloat = column<Float>("mock_float")

    val dataset =
        dataFrameOf(
            "mock_double" to listOf<Double>(),
            "mock_int" to listOf<Int>(),
            "mock_string" to listOf<String>(),
            "mock_float" to listOf<Float>(),
        )

    @Test
    fun testCustomLayer() {
        val plot = dataset.plot {
            y(mockSrcDouble)
            customLayer {
                x(mockSrcFloat.scaled(continuousPos()))
                specificAes(
                    mockSrcString.scaled(
                        categorical(
                            listOf("A", "B"),
                            listOf(CustomGeomType("x"), CustomGeomType("xxx"))
                        )
                    )
                )
            }
            customLayer {
                x(mockSrcFloat)
                specificAes(CustomGeomType("yy"))
            }
        }
        assertEquals(
            Plot(
                NamedData(dataset),
                listOf(
                    Layer(
                        null,
                        CUSTOM_GEOM,
                        mapOf(
                            X to ScaledPositionalUnspecifiedMapping(
                                X,
                                ColumnScaledPositionalUnspecified(mockSrcFloat, PositionalContinuousUnspecifiedScale()),
                                typeOf<Float>()
                            ),
                            Y to ScaledUnspecifiedDefaultPositionalMapping(
                                Y,
                                ColumnScaledUnspecifiedDefault(mockSrcDouble),
                                typeOf<Double>()
                            ),
                            SPECIFIC_AES to ScaledNonPositionalMapping(
                                SPECIFIC_AES,
                                ColumnScaledNonPositional(
                                    mockSrcString,
                                    NonPositionalCategoricalScale<String, CustomGeomType>(
                                        listOf("A", "B"),
                                        listOf(CustomGeomType("x"), CustomGeomType("xxx")),
                                        //null
                                    )
                                ),
                                typeOf<String>(),
                                //typeOf<CustomGeomType>()
                            )
                        ),
                        mapOf(),
                        mapOf()
                    ),
                    Layer(
                        null,
                        CUSTOM_GEOM,
                        mapOf(
                            X to ScaledUnspecifiedDefaultPositionalMapping(
                                X,
                                ColumnScaledUnspecifiedDefault(mockSrcFloat),
                                typeOf<Float>()
                            ),
                            Y to ScaledUnspecifiedDefaultPositionalMapping(
                                Y,
                                ColumnScaledUnspecifiedDefault(mockSrcDouble),
                                typeOf<Double>()
                            )
                        ),
                        mapOf(
                            SPECIFIC_AES to NonPositionalSetting<CustomGeomType>(
                                SPECIFIC_AES,
                                CustomGeomType("yy")
                            )
                        ),
                        mapOf()
                    )
                ),
                mapOf(
                    Y to ScaledUnspecifiedDefaultPositionalMapping(
                    Y,
                    ColumnScaledUnspecifiedDefault(mockSrcDouble),
                    typeOf<Double>()
                ),),
                emptyMap(),
                emptyMap()
            ),
            plot
        )
    }

    @Test
    fun testFeatures() {
        val plot = dataset.plot {
            mockFeatureProp = "amentes ineptias"
            bars {
                mockFeatureFunction(14)
            }
        }

        assertEquals(
            Plot(
                NamedData(dataset),
                listOf(
                    Layer(
                        null,
                        BAR,
                        mapOf(),
                        mapOf(),
                        mapOf(MockLayerFeature.FEATURE_NAME to MockLayerFeature(14))
                    )
                ),
                mapOf(),
                mapOf(MockPlotFeature.FEATURE_NAME to MockPlotFeature("amentes ineptias")),
                emptyMap()
            ),
            plot
        )
    }
}

 */


