package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.*
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData

/**
 * Creates a new [Layer] from this [LayerContext]
 *
 * @return new [Plot]
 */
fun org.jetbrains.kotlinx.ggdsl.dsl.LayerContext.toLayer(geom: Geom): Layer {
    return Layer(
        data,
        geom,
        bindingCollectorAccessor.mappings,
        bindingCollectorAccessor.settings,
        //collectorAccessor.scales,
        features
    )
}

/*
operator fun <RangeType> Axis<RangeType>.invoke(block: Axis<RangeType>.() -> Unit) {
    this.apply(block)
}

 */

inline operator fun Layout.invoke(block: Layout.() -> Unit) {
    this.apply(block)
}

/*
operator fun Guide.invoke(block: Guide.() -> Unit) {
    this.apply(block)
}

 */
