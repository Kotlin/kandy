package org.jetbrains.kotlinx.ggdsl.echarts.translator

import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.ContinuousVisualMap
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.PiecewiseVisualMap
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.VisualMap
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.createInRange
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import kotlin.reflect.KType
import kotlin.reflect.typeOf

internal fun Scale.toVisualMap(
    aes: AesName, dim: String, seriesIndex: Int,
    data: List<Any>?, visualMapSize: Int, domainType: KType
): VisualMap {
    return when (this) {
        is NonPositionalCategoricalScale<*, *> -> {
            val categoriesString =
                domainCategories?.map { value -> value.toString() } ?: data?.toSet()?.map { it.toString() }
            val inRange = createInRange(aes, rangeValues)
            PiecewiseVisualMap(
                dimension = dim,
                categories = categoriesString,
                inRange = inRange,
                seriesIndex = seriesIndex,
                right = 10,  // TODO настройка отступов и расположении легенды
                top = visualMapSize * 100
            )
        }

        is NonPositionalContinuousScale<*, *> -> {
            val min = domainLimits?.first?.toString()?.toDouble()
            val max = domainLimits?.second?.toString()?.toDouble()

            val valuesString = rangeLimits?.let { listOf(it.first, it.second) }

            val inRange = createInRange(aes, valuesString)
            ContinuousVisualMap(
                dimension = dim,
                min = min,
                max = max,
                inRange = inRange,
                seriesIndex = seriesIndex,
                right = 10,
                top = visualMapSize * 100
            )
        }

        is DefaultUnspecifiedScale -> {
            when (domainType) {
//                 todo other, date
                typeOf<String>() -> PiecewiseVisualMap(
                    dimension = dim,
                    categories = data?.toSet()?.map { it.toString() },
                    seriesIndex = seriesIndex,
                    right = 10,
                    top = visualMapSize * 100
                )

                else -> ContinuousVisualMap(
                    dimension = dim,
                    seriesIndex = seriesIndex,
                    right = 10,
                    top = visualMapSize * 100
                )
            }
        }

        is NonPositionalCategoricalUnspecifiedScale -> PiecewiseVisualMap(
            dimension = dim,
            categories = data?.toSet()?.map { it.toString() },
            seriesIndex = seriesIndex,
            right = 10,
            top = visualMapSize * 100
        )

        is NonPositionalContinuousUnspecifiedScale -> ContinuousVisualMap(
            dimension = dim,
            seriesIndex = seriesIndex,
            right = 10,
            top = visualMapSize * 100
        )

        else -> throw Exception("Unknown scale on ${aes.name} aes in $seriesIndex layer.")
    }
}