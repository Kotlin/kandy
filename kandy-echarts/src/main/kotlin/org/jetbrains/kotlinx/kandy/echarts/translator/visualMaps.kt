package org.jetbrains.kotlinx.kandy.echarts.translator

import org.jetbrains.kotlinx.kandy.echarts.translator.option.ContinuousVisualMap
import org.jetbrains.kotlinx.kandy.echarts.translator.option.PiecewiseVisualMap
import org.jetbrains.kotlinx.kandy.echarts.translator.option.VisualMap
import org.jetbrains.kotlinx.kandy.echarts.translator.option.createInRange
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalContinuousScale
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalDefaultScale
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalScale
import kotlin.reflect.KType
import kotlin.reflect.typeOf

internal fun NonPositionalScale<*, *>.toVisualMap(
    aes: Aes, dim: String, seriesIndex: Int,
    data: List<Any?>?, visualMapSize: Int, domainType: KType
): VisualMap {
    return when (this) {
        is NonPositionalCategoricalScale<*, *> -> {
            val categoriesString =
                domainCategories?.map { value -> value?.toString() } ?: data?.toSet()?.map { it?.toString() }
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
            val min = domainMin?.toString()?.toDouble() ?: data?.asSequence()?.filterNotNull()
                ?.minOfOrNull { (it as Number).toDouble() }
            val max = domainMax?.toString()?.toDouble() ?: data?.asSequence()?.filterNotNull()
                ?.maxOfOrNull { (it as Number).toDouble() }

            val valuesString = if (rangeMin != null && rangeMax != null) listOf(rangeMin, rangeMax) else null

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

        is NonPositionalDefaultScale -> {
            when (domainType) {
//                 todo other, date
                typeOf<String>(), typeOf<String?>() -> PiecewiseVisualMap(
                    dimension = dim,
                    categories = data?.toSet()?.map { it?.toString() },
                    seriesIndex = seriesIndex,
                    right = 10,
                    top = visualMapSize * 100
                )

                else -> {
                    val d = data?.filterNotNull()
                    val min = d?.minOfOrNull { (it as Number).toDouble() }
                    val max = d?.maxOfOrNull { (it as Number).toDouble() }

                    ContinuousVisualMap(
                        dimension = dim,
                        min = min,
                        max = max,
                        seriesIndex = seriesIndex,
                        right = 10,
                        top = visualMapSize * 100
                    )
                }
            }
        }

        else -> throw Exception("Unknown scale on ${aes.name} aes in $seriesIndex layer.")
    }
}