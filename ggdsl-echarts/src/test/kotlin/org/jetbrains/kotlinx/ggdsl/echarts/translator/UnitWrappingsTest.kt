/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.translator

//import org.jetbrains.kotlinx.ggdsl.dsl.NamedData
//import org.jetbrains.kotlinx.ggdsl.dsl.columnPointer
//import org.jetbrains.kotlinx.ggdsl.dsl.continuousPos
//import org.jetbrains.kotlinx.ggdsl.echarts.*
//import org.jetbrains.kotlinx.ggdsl.echarts.stack.Stack
//import org.jetbrains.kotlinx.ggdsl.echarts.util.color.ColorStop
//import org.jetbrains.kotlinx.ggdsl.echarts.util.color.GradientOption
//import org.jetbrains.kotlinx.ggdsl.echarts.util.color.LinearGradientColor
//import org.jetbrains.kotlinx.ggdsl.echarts.util.color.SimpleColorOption
//import org.jetbrains.kotlinx.ggdsl.ir.Layer
//import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
//import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
//import org.jetbrains.kotlinx.ggdsl.ir.scale.*
//import org.jetbrains.kotlinx.ggdsl.util.color.Color
//import kotlin.reflect.typeOf
//import kotlin.test.Test
//import kotlin.test.assertContentEquals
//import kotlin.test.assertEquals

//internal class UnitWrappingsTest {
//    @Test
//    fun testDataWrap() {
//        val names = listOf("cabook123", "mamakeksa", "EREVAN2077")
//        val dataset: NamedDataInterface = NamedData(mapOf(
//            names[0] to listOf(1, 2, 4, 5),
//            names[1] to listOf("keka", "meka", "shusha", "dusha"),
//            names[2] to listOf(3.4, 11.1, 10.0, 100.4)
//        ))
//        val (wrappedData, idToDim) = dataset.wrap()
//        assertContentEquals(
//            listOf(
//                names,
//                listOf(1, "keka", 3.4),
//                listOf(2, "meka", 11.1),
//                listOf(4, "shusha", 10.0),
//                listOf(5, "dusha", 100.4),
//            ), wrappedData
//        )
//        assertEquals(
//            names.withIndex().associate { it.value to it.index }.toMap(),
//            idToDim
//        )
//    }
//
//    @Test
//    fun testToSeriesSimple() {
//        val layer = Layer(
//            NamedData(mapOf()),
//            POINT,
//            mapOf(
//                X to ScaledPositionalMapping(
//                    X,
//                    ColumnScaledPositional(
//                        columnPointer<Double>("srcX"),
//                        continuousPos(1.0 to 5.0)
//                    ),
//                    typeOf<Double>()
//                ),
//                Y to ScaledUnspecifiedDefaultPositionalMapping(
//                    Y,
//                    ColumnScaledUnspecifiedDefault(
//                        columnPointer<Int>("yy")
//                    ),
//                    typeOf<Int>()
//                )
//            ),
//            mapOf(
//                COLOR to NonPositionalSetting(
//                    COLOR,
//                    Color.RED
//                )
//            )
//        )
//        val expectedSeries = Series(
//            type = "scatter",
//            encode = XYEncode(
//                x = "srcX",
//                y = "yy"
//            ),
//            itemStyle = ItemStyle(
//                color = SimpleColorOption("red")
//            ),
//        )
//        assertEquals(expectedSeries, layer.toSeries(null))
//    }
//
//    @Test
//    fun testToSeriesComplex() {
//        val layer = Layer(
//            NamedData(mapOf()),
//            LINE,
//            mapOf(
//                X to ScaledPositionalUnspecifiedMapping(
//                    X, ColumnScaledPositionalUnspecified(
//                        columnPointer<Float>("time"),
//                        PositionalContinuousUnspecifiedScale()
//                    ),
//                    typeOf<Double>()
//                ),
//                Y to ScaledPositionalMapping(
//                    Y, ColumnScaledPositional(
//                        columnPointer<Float>("size"),
//                        PositionalContinuousScale(
//                            limits = 1f to 15f
//                        )
//                    ),
//                    typeOf<Float>()
//                ),
//            ),
//            mapOf(
//                COLOR to NonPositionalSetting(
//                    COLOR,
//                    LinearGradientColor(
//                        0.0,
//                        0.0,
//                        1.0,
//                        1.0,
//                        colors = listOf(0.0 to Color.RED, 1.0 to Color.GREEN)
//                    )
//                ),
//                WIDTH to NonPositionalSetting(
//                    WIDTH,
//                    15.0
//                )
//            ),
//            mapOf(Stack.FEATURE_NAME to Stack("stack_name"))
//        )
//        val mockData = listOf(
//            listOf("Laudate", "omnes", "gentes", "laudate"),
//            listOf("2", "12", "333", "-10")
//        )
//        val expectedSeries = Series(
//            type = "line",
//            encode = XYEncode(
//                x = "time",
//                y = "size"
//            ),
//            lineStyle = LineStyle(
//                color = GradientOption(
//                    "linear",
//                    0.0,
//                    0.0,
//                    1.0,
//                    1.0,
//                    colorStops = listOf(ColorStop(0.0, "red"), ColorStop(1.0, "green"))
//                ),
//                width = 15.0
//            ),
//            stack = "stack_name",
//            showSymbol = false,
//            data = mockData
//        )
//        assertEquals(expectedSeries, layer.toSeries(mockData))
//    }
//
//    @Test
//    fun testToAxisCat() {
//        val categories = listOf("cat1", "cat10", "cat11")
//        val scale = PositionalCategoricalScale(categories)
//        val axis = scale.toAxis(typeOf<String>())
//        assertEquals(
//            Axis(
//                POS_CAT_NAME,
//                data = categories
//            ),
//            axis
//        )
//    }
//
//    @Test
//    fun testToAxisCont() {
//        val range = -23.1f to 51.3f
//        val scale = PositionalContinuousScale(range)
//        val axis = scale.toAxis(typeOf<Float>())
//        assertEquals(
//            Axis(
//                POS_CONT_NAME,
//                min = range.first.toString(),
//                max = range.second.toString()
//            ),
//            axis
//        )
//    }
//
//    @Test
//    fun testToAxisCatDefault() {
//        val scale = PositionalCategoricalUnspecifiedScale
//        assertEquals(
//            Axis(
//                POS_CAT_NAME,
//            ),
//            scale.toAxis(typeOf<String>())
//        )
//    }
//
//    @Test
//    fun testToAxisContDefault() {
//        val scale = PositionalContinuousUnspecifiedScale()
//        assertEquals(
//            Axis(
//                POS_CONT_NAME,
//            ),
//            scale.toAxis(typeOf<Double>())
//        )
//    }
//
//    @Test
//    fun testToAxisDefault() {
//        assertEquals(
//            Axis(
//                POS_CONT_NAME,
//            ),
//            DefaultUnspecifiedScale.toAxis(typeOf<Int>())
//        )
//        assertEquals(
//            Axis(
//                POS_CAT_NAME,
//            ),
//            DefaultUnspecifiedScale.toAxis(typeOf<String>())
//        )
//        // todo others?
//    }
//
//    @Test
//    fun testToVisualMapCategorical() {
//        visualMapCounter = 0// todo
//        val categories = listOf(2, 4, 8, 16)
//        val values = listOf(Color.RED, Color.GREEN, Color.BLUE, Color.GREY)
//        val scale = NonPositionalCategoricalScale(categories, values)
//        val dim = 3
//        val seriesIndex = 1
//        assertEquals(
//            VisualMap(
//                type = NON_POS_CAT_NAME,
//                dimension = dim,
//                seriesIndex = seriesIndex,
//                categories = categories.map { it.toString() },// todo
//                inRange = InRange(
//                    color = values.map { wrapColor(it) }
//                ),
//                // todo
//                top = 0,
//                right = 10
//            ),
//            scale.toVisualMap(COLOR, dim, seriesIndex, listOf(), typeOf<Int>())
//        )
//    }
//
//    @Test
//    fun testToVisualMapContinuous() {
//        visualMapCounter = 0// todo
//        val domain = -1.0F to 10.5F
//        val range = 10 to 22
//        val scale = NonPositionalContinuousScale(domain, range)
//        val dim = 5
//        val seriesIndex = 2
//        assertEquals(
//            VisualMap(
//                type = NON_POS_CONT_NAME,
//                dimension = dim,
//                seriesIndex = seriesIndex,
//                min = domain.first.toDouble(),
//                max = domain.second.toDouble(),
//                inRange = InRange(
//                    symbolSize = listOf(range.first.toDouble(), range.second.toDouble())
//                ),
//                // todo
//                top = 0,
//                right = 10
//            ),
//            scale.toVisualMap(SIZE, dim, seriesIndex, listOf(), typeOf<Int>())
//        )
//    }
//}
