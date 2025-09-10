@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.echarts.layers.context

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.echarts.features.animation.AnimationEasing
import org.jetbrains.kotlinx.kandy.echarts.features.animation.AnimationPie
import org.jetbrains.kotlinx.kandy.echarts.features.animation.AnimationType
import org.jetbrains.kotlinx.kandy.echarts.layers.PIE
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.PIE_DATA_NAME
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.PIE_DATA_VALUES
import org.jetbrains.kotlinx.kandy.echarts.scale.nonPosMappingCat
import org.jetbrains.kotlinx.kandy.ir.geom.Geom

public class PieHandler(parent: LayerCreatorScope) : EchartsLayerBuilder(parent) {
    override val geom: Geom
        get() = PIE

    public fun <K, V> data(name: ColumnReference<K>, values: ColumnReference<V>) {
        bindingHandler.nonPosMappingCat<K, Any?>(PIE_DATA_NAME, name)
        bindingHandler.nonPosMappingCat<V, Any?>(PIE_DATA_VALUES, values)
    }

    public fun <K, V> data(data: Map<K, V>) {
        bindingHandler.nonPosMappingCat<K, Any?>(PIE_DATA_NAME, data.keys)
        bindingHandler.nonPosMappingCat<V, Any?>(PIE_DATA_VALUES, data.values)
    }

    public fun data(name: String, values: String) {
        bindingHandler.nonPosMappingCat<Any?>(PIE_DATA_NAME, name)
        bindingHandler.nonPosMappingCat<Any?>(PIE_DATA_VALUES, values)
    }

    public fun <K, V> data(name: Iterable<K>, values: Iterable<V>) {
        bindingHandler.nonPosMappingCat<K, Any?>(PIE_DATA_NAME, name)
        bindingHandler.nonPosMappingCat<V, Any?>(PIE_DATA_VALUES, values)
    }

    public fun <K, V> data(name: DataColumn<K>, values: DataColumn<V>) {
        bindingHandler.nonPosMappingCat<K, Any?>(PIE_DATA_NAME, name)
        bindingHandler.nonPosMappingCat<V, Any?>(PIE_DATA_VALUES, values)
    }

    /**
     * Animation options settings for [pie][org.jetbrains.kotlinx.kandy.echarts.layers.pie].
     * If a property isn't set or set to null, a default value will be used.
     *
     * * [enable][AnimationPie.enable] - responsible for enabling animation.
     * By default `true`.
     * * [type][AnimationPie.type] - initial [animation type][AnimationType].
     * By default `expansion`.
     * * [threshold][AnimationPie.threshold] - sets a graphic number threshold for animation.
     * Animation will be disabled when graphic number exceeds a threshold.
     * By default `2000`.
     * * [duration][AnimationPie.duration] - duration of the first animation.
     * By default `1000`.
     * * [easing][AnimationPie.easing] - [easing effect][AnimationEasing] used for the first animation.
     * By default `cubicOut`.
     * * [delay][AnimationPie.delay] - delay before updating the first animation.
     * By default `0`.
     *
     * ```kotlin
     * plot {
     *  pie {
     *      animation {
     *          enable = true
     *          type = AnimationType.EXPANSION
     *          threshold = 2000
     *          duration = 1000
     *          easing = AnimationEasing.CUBIC_OUT
     *          delay = 0
     *      }
     *  }
     * }
     * ```
     *
     * @see AnimationEasing
     */
    public val animation: AnimationPie = AnimationPie()
}