package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import kotlin.reflect.typeOf

/**
 * Internal collector of mappings and settings.
 */
class BindingCollector internal constructor() {
    val mappings: MutableMap<Aes, Mapping> = mutableMapOf()
    val settings: MutableMap<Aes, Setting> = mutableMapOf()

    fun copyFrom(other: org.jetbrains.kotlinx.ggdsl.dsl.BindingCollector) {
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
abstract class BaseBindingContext {
    abstract var data: org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData

    // TODO remove or make internal
    protected val bindingCollector = org.jetbrains.kotlinx.ggdsl.dsl.BindingCollector()

    // TODO remove or make internal
    val bindingCollectorAccessor: org.jetbrains.kotlinx.ggdsl.dsl.BindingCollector
        get() = bindingCollector

    fun copyFrom(other: org.jetbrains.kotlinx.ggdsl.dsl.BaseBindingContext) {
        data = other.data
        bindingCollector.copyFrom(other.bindingCollector)
    }

    /**
     * Setting, i.e. assigning some constant value to an non-positional aesthetic attribute.
     *
     * @param value the assigned value.
     */
    operator fun <T : Any> NonPositionalAes<T>.invoke(value: T) {
        bindingCollector.settings[this] = NonPositionalSetting(this, value)
    }

    /**
     * Mapping to non-scalable ("sub-positional") aesthetic attribute.
     *
     * @param source the assigned raw data source.
     */
    inline operator fun <reified DomainType : Any> NonScalablePositionalAes.invoke(
        source: DataSource<DomainType>
    ) {
        bindingCollectorAccessor.mappings[this] =
            NonScalablePositionalMapping(this, source, typeOf<DomainType>())
    }

    /**
     * Mapping to an aesthetic attribute with default scale. TODO behavior
     *
     * @param source the assigned raw data source.
     */
    inline operator fun <reified DomainType : Any> ScalableAes.invoke(
        source: DataSource<DomainType>
    ) {
        bindingCollectorAccessor.mappings[this] = ScaledUnspecifiedDefaultMapping(
            this,
            source.scaled(),
            typeOf<DomainType>()
        )
    }

    /**
     * Mapping to an aesthetic attribute with default scale. TODO behavior
     *
     * @param sourceScaledDefault the assigned source scaled default.
     */
    inline operator fun <reified DomainType : Any> ScalableAes.invoke(
        sourceScaledDefault: SourceScaledUnspecifiedDefault<DomainType>
    ) {
        bindingCollectorAccessor.mappings[this] = ScaledUnspecifiedDefaultMapping(
            this,
            sourceScaledDefault,
            typeOf<DomainType>()
        )
    }

    /**
     * Mapping to a positional aesthetic attribute with unspecified scale. TODO behavior
     *
     * @param sourceScaledDefault the assigned source scaled unspecified positional.
     */
    inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
        sourceScaledDefault: SourceScaledPositionalDefault<DomainType>
    ) {
        bindingCollectorAccessor.mappings[this] = ScaledPositionalDefaultMapping(
            this,
            sourceScaledDefault,
            typeOf<DomainType>()
        )
    }

    /**
     * Mapping to a non-positional aesthetic attribute with unspecified scale. TODO behavior
     *
     * @param sourceScaledDefault the assigned source scaled unspecified non-positional.
     */
    inline operator fun <reified DomainType : Any> MappableNonPositionalAes<*>.invoke(
        sourceScaledDefault: SourceScaledNonPositionalDefault<DomainType>
    ) {
        bindingCollectorAccessor.mappings[this] = ScaledNonPositionalDefaultMapping(
            this,
            sourceScaledDefault,
            typeOf<DomainType>()
        )
    }

    /**
     * Mapping to a positional aesthetic attribute.
     *
     * @param sourceScaledPositional the assigned source scaled positional.
     */
    inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
        sourceScaledPositional: SourceScaledPositional<DomainType>
    ) {
        bindingCollectorAccessor.mappings[this] = ScaledPositionalMapping(
            this,
            sourceScaledPositional,
            typeOf<DomainType>()
        )
    }

    /**
     * Mapping to a non-positional aesthetic attribute. TODO behavior
     *
     * @param sourceScaledNonPositional the assigned source scaled non-positional.
     */
    inline operator fun <reified DomainType : Any, reified RangeType : Any>
            MappableNonPositionalAes<RangeType>.invoke(
        sourceScaledNonPositional: SourceScaledNonPositional<DomainType, RangeType>
    ) {
        bindingCollectorAccessor.mappings[this] = ScaledNonPositionalMapping(
            this,
            sourceScaledNonPositional,
            typeOf<DomainType>(),
           // typeOf<RangeType>()
        )
    }

    // TODO other????
    val x = X
    val y = Y

}

/**
 * Layer context interface.
 *
 * todo
 */
abstract class LayerContext : org.jetbrains.kotlinx.ggdsl.dsl.BaseBindingContext() {

    // todo hide
    val features: MutableMap<FeatureName, LayerFeature> = mutableMapOf()
}

class PointsContext(override var data: org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData) : org.jetbrains.kotlinx.ggdsl.dsl.LayerContext() {
    val size = SIZE
    val color = COLOR
    val alpha = ALPHA

    val borderWidth = BORDER_WIDTH
    val borderColor = BORDER_COLOR

    val symbol = SYMBOL
}

class LineContext(override var data: org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData) : org.jetbrains.kotlinx.ggdsl.dsl.LayerContext() {
    val color = COLOR
    val alpha = ALPHA

    val width = WIDTH

    val lineType = LINE_TYPE
}

class BarsContext(override var data: org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData) : org.jetbrains.kotlinx.ggdsl.dsl.LayerContext() {
    val color = COLOR
    val alpha = ALPHA

    val width = WIDTH

    val borderWidth = BORDER_WIDTH
    val borderColor = BORDER_COLOR
}

class PlotContext : org.jetbrains.kotlinx.ggdsl.dsl.BaseBindingContext() {

    override var data: org.jetbrains.kotlinx.ggdsl.dsl.MutableNamedData = mutableMapOf()

    val layout = Layout()

    val layers: MutableList<Layer> = mutableListOf()
    val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

}
