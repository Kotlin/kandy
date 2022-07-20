package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.old.DefaultLayout
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom

/**
 * Creates a new [Layer] from this [LayerContext]
 *
 * @return new [Plot]
 */
fun LayerContext.toLayer(geom: Geom): Layer {
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


/*
operator fun Guide.invoke(block: Guide.() -> Unit) {
    this.apply(block)
}

 */
