package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.*

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

    class SpecificAes(override val context: BindingContext) : MappableNonPositionalAes<CustomGeomType>{
        override val name = SPECIFIC_AES
    }

    companion object {
        val customGeom = CommonGeom("custom")
        val SPECIFIC_AES = AesName("customAes")
    }

    data class CustomGeomType(val name: String)

    class CustomGeomContext(override var data: MutableNamedData) :
        LayerContext() {
        val specificAes = SpecificAes(this)
    }

    fun PlotContext.customLayer(block: CustomGeomContext.() -> Unit) {
        layers.add(CustomGeomContext(data).apply { copyFrom(this@customLayer) }.apply(block).toLayer(customGeom))
    }

    data class MockLayerFeature(val value: Int) : LayerFeature {
        override val featureName = FEATURE_NAME

        companion object {
            val FEATURE_NAME = FeatureName("mock_layer_feature")
        }
    }

    fun LayerContext.mockFeatureFunction(value: Int) {
        features[MockLayerFeature.FEATURE_NAME] = MockLayerFeature(value)
    }

    data class MockPlotFeature(val title: String) : PlotFeature {
        override val featureName = FEATURE_NAME

        companion object {
            val FEATURE_NAME = FeatureName("mock_plot_feature")
        }
    }

    var PlotContext.mockFeatureProp: String
        get() = ""
        set(value) {
            features[MockPlotFeature.FEATURE_NAME] = MockPlotFeature(value)
        }

    val mockSrcDouble = source<Double>("mock_double")
    val mockSrcInt = source<Int>("mock_int")
    val mockSrcString = source<String>("mock_string")
    val mockSrcFloat = source<Float>("mock_float")

    val dataset: NamedData = mapOf()

    @Test
    fun testCustomLayer() {
        val plot = plot(dataset) {
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
                dataset,
                listOf(
                    Layer(
                        dataset,
                        customGeom,
                        mapOf(
                            X to ScaledPositionalDefaultMapping(
                                X,
                                SourceScaledPositionalUnspecified(mockSrcFloat, PositionalContinuousUnspecifiedScale()),
                                typeOf<Float>()
                            ),
                            Y to ScaledUnspecifiedDefaultPositionalMapping(
                                Y,
                                SourceScaledUnspecifiedDefault(mockSrcDouble),
                                typeOf<Double>()
                            ),
                            SPECIFIC_AES to ScaledNonPositionalMapping(
                                SPECIFIC_AES,
                                SourceScaledNonPositional(
                                    mockSrcString,
                                    NonPositionalCategoricalScale(
                                        listOf("A", "B"),
                                        listOf(CustomGeomType("x"), CustomGeomType("xxx"))
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
                        dataset,
                        customGeom,
                        mapOf(
                            X to ScaledUnspecifiedDefaultPositionalMapping(
                                X,
                                SourceScaledUnspecifiedDefault(mockSrcFloat),
                                typeOf<Float>()
                            ),
                            Y to ScaledUnspecifiedDefaultPositionalMapping(
                                Y,
                                SourceScaledUnspecifiedDefault(mockSrcDouble),
                                typeOf<Double>()
                            )
                        ),
                        mapOf(
                            SPECIFIC_AES to NonPositionalSetting(
                                SPECIFIC_AES,
                                CustomGeomType("yy")
                            )
                        ),
                        mapOf()
                    )
                ),
                null,
                mapOf()
            ),
            plot
        )
    }

    @Test
    fun testFeatures() {
        val plot = plot(dataset) {
            mockFeatureProp = "amentes ineptias"
            bars {
                mockFeatureFunction(14)
            }
        }

        assertEquals(
            Plot(
                dataset,
                listOf(
                    Layer(
                        dataset,
                        BAR,
                        mapOf(),
                        mapOf(),
                        mapOf(MockLayerFeature.FEATURE_NAME to MockLayerFeature(14))
                    )
                ),
                null,
                mapOf(MockPlotFeature.FEATURE_NAME to MockPlotFeature("amentes ineptias"))
            ),
            plot
        )
    }
}


