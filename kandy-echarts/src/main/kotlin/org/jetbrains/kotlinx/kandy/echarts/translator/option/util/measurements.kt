/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option.util

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.settings.DoubleUnit
import org.jetbrains.kotlinx.kandy.echarts.settings.IntUnit
import org.jetbrains.kotlinx.kandy.echarts.settings.SizeUnit
import org.jetbrains.kotlinx.kandy.echarts.translator.serializers.MeasurementSerializer

internal fun <T : SizeUnit> singleOf(arg: T): Measurement.Single<T> = Measurement.Single(arg)
internal fun singleOf(arg: Int): Measurement.Single<IntUnit> = Measurement.Single(IntUnit(arg))
internal fun singleOf(arg: Double): Measurement.Single<DoubleUnit> = Measurement.Single(DoubleUnit(arg))

internal fun <T : SizeUnit> pairOf(arg0: T, arg1: T): Measurement.Pair<T> = Measurement.Pair(listOf(arg0, arg1))
internal fun pairOf(arg0: Int, arg1: Int): Measurement.Pair<IntUnit> =
    Measurement.Pair(listOf(IntUnit(arg0), IntUnit(arg1)))

internal fun pairOf(arg0: Double, arg1: Double): Measurement.Pair<DoubleUnit> =
    Measurement.Pair(listOf(DoubleUnit(arg0), DoubleUnit(arg1)))


internal fun <T : SizeUnit> rectangleOf(arg0: T, arg1: T, arg2: T, arg3: T): Measurement.Rectangle<T> =
    Measurement.Rectangle(listOf(arg0, arg1, arg2, arg3))

internal fun rectangleOf(arg0: Int, arg1: Int, arg2: Int, arg3: Int): Measurement.Rectangle<IntUnit> =
    Measurement.Rectangle(listOf(IntUnit(arg0), IntUnit(arg1), IntUnit(arg2), IntUnit(arg3)))

internal fun rectangleOf(arg0: Double, arg1: Double, arg2: Double, arg3: Double): Measurement.Rectangle<DoubleUnit> =
    Measurement.Rectangle(listOf(DoubleUnit(arg0), DoubleUnit(arg1), DoubleUnit(arg2), DoubleUnit(arg3)))

@Serializable(with = MeasurementSerializer::class)
internal sealed interface Measurement : StringNumberArray {

    @Serializable
    @JvmInline
    value class Single<T : SizeUnit>(val value: T) : Measurement

    @Serializable
    @JvmInline
    value class Pair<out T : SizeUnit>(val list: List<T>) : Measurement

    @Serializable
    @JvmInline
    value class Rectangle<out T : SizeUnit>(val list: List<T>) : Measurement
}