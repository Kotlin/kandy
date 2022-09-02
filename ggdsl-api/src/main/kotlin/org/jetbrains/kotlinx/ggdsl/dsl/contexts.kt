package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Layout
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import org.jetbrains.kotlinx.ggdsl.ir.scale.*

/**
 * Internal collector of mappings and settings.
 */
class BindingCollector internal constructor() {
    val mappings: MutableMap<AesName, Mapping> = mutableMapOf()
    val settings: MutableMap<AesName, Setting> = mutableMapOf()
    val freeScales: MutableMap<AesName, FreeScale> = mutableMapOf()

    fun copyFrom(other: BindingCollector) {
        mappings.putAll(other.mappings)
        settings.putAll(other.settings)
    }
}

/**
 * Base class for binding context.
 *
 * In this context, the mechanism of bindings, that is, mappings and settings, is defined.
 * It is implemented with aesthetic attribute properties invocation with raw or scaled source as an argument.
 *
 * @property data the mutual dataset context.
 */
@PlotDslMarker
abstract class BindingContext {
    abstract var data: MutableNamedData

    //todo
    @PublishedApi
    internal var counter = 0

    @PublishedApi
    internal fun generateID(): String = "*gen${counter++}"

    // todo add for arrays/others???
    @PublishedApi
    internal inline fun <reified T : Any> Iterable<T>.toDataSource(): DataSource<T> {
        val list = toList()
        val id = generateID()
        data[id] = list
        return source(id)
    }

    // todo how to hide?
    val bindingCollector = BindingCollector()

    // todo how to hide?
    fun copyFrom(other: BindingContext, copyData: Boolean = true) {
        if (copyData) {
            data = other.data
        }
        this.bindingCollector.copyFrom(other.bindingCollector)
    }


    // todo move???
    inline fun <reified DomainType : Any> Iterable<DomainType>.scaled() =
        SourceScaledUnspecifiedDefault(this.toDataSource())

    inline fun <reified DomainType : Any> Iterable<DomainType>.scaled(scale: PositionalUnspecifiedScale) =
        SourceScaledPositionalUnspecified(this.toDataSource(), scale)


    inline fun <reified DomainType : Any> Iterable<DomainType>.scaled(scale: NonPositionalUnspecifiedScale) =
        SourceScaledNonPositionalUnspecified(this.toDataSource(), scale)


    inline fun <reified DomainType : Any> Iterable<DomainType>.scaled(
        scale: PositionalScale<DomainType>
    ) = SourceScaledPositional(this.toDataSource(), scale)


    inline fun <reified DomainType : Any, RangeType : Any> Iterable<DomainType>.scaled(
        scale: NonPositionalScale<DomainType, RangeType>
    ) = SourceScaledNonPositional(this.toDataSource(), scale)


}
/*
// todo
abstract class BaseBindingContext : BindingContext() {
    val x = XAes(this)
    val y = YAes(this)
}


 */
/**
 * Layer context interface.
 *
 * todo
 */
@PlotDslMarker
abstract class LayerContext : BindingContext() {
    // todo hide?
    val features: MutableMap<FeatureName, LayerFeature> = mutableMapOf()
}

/**
 * Creates a new [Layer] from this [LayerContext]
 *
 * @return new [Plot]
 */
fun LayerContext.toLayer(geom: Geom): Layer {
    return Layer(
        data,
        geom,
        this.bindingCollector.mappings,
        this.bindingCollector.settings,
        features,
        this.bindingCollector.freeScales
    )
}

// todo
@PlotDslMarker
class PlotContext : BindingContext() {
    override var data: MutableNamedData = mutableMapOf()

    internal var _layout: Layout? = null

    // todo how to hide?
    val layers: MutableList<Layer> = mutableListOf()
    // todo how to hide?
    val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

}
