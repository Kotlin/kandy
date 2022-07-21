package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Layout
import org.jetbrains.kotlinx.ggdsl.old.DefaultLayout
import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalDefaultScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalDefaultScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import kotlin.reflect.typeOf

/**
 * Internal collector of mappings and settings.
 */
class BindingCollector internal constructor() {
    val mappings: MutableMap<AesName, Mapping> = mutableMapOf()
    val settings: MutableMap<AesName, Setting> = mutableMapOf()

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
abstract class BindingContext {
    abstract var data: MutableNamedData

    var counter = 0
     fun generateID(): String = "*gen${counter++}"

    // todo add for arrays/others???
     inline fun<reified T: Any> Iterable<T>.toDataSource(): DataSource<T> {
        val list = toList()
        val id = generateID()
        data[id] = list
        return source(id)
    }

    // TODO remove or make internal
    protected val bindingCollector = BindingCollector()

    // TODO remove or make internal
    val bindingCollectorAccessor: BindingCollector
        get() = bindingCollector

    fun copyFrom(other: BindingContext, copyData: Boolean = true) {
        if (copyData) {
            data = other.data
        }
        bindingCollector.copyFrom(other.bindingCollector)
    }


    // todo move???
    inline fun <reified DomainType : Any> Iterable<DomainType>.scaled() =
        SourceScaledUnspecifiedDefault(this.toDataSource())

    inline fun <reified DomainType : Any> Iterable<DomainType>.scaled(scale: PositionalDefaultScale) =
        SourceScaledPositionalDefault(this.toDataSource(), scale)


    inline fun <reified DomainType : Any> Iterable<DomainType>.scaled(scale: NonPositionalDefaultScale) =
        SourceScaledNonPositionalDefault(this.toDataSource(), scale)


    inline fun <reified DomainType : Any> Iterable<DomainType>.scaled(
        scale: PositionalScale<DomainType>
    ) = SourceScaledPositional(this.toDataSource(), scale)


    inline fun <reified DomainType : Any, RangeType : Any> Iterable<DomainType>.scaled(
        scale: NonPositionalScale<DomainType, RangeType>
    ) = SourceScaledNonPositional(this.toDataSource(), scale)


}

/**
 * Setting, i.e. assigning some constant value to an non-positional aesthetic attribute.
 *
 * @param value the assigned value.
 */
operator fun <T : Any> NonPositionalAes<T>.invoke(value: T) {
    context.bindingCollectorAccessor.settings[this.name] = NonPositionalSetting(this.name, value)
}

/**
 * Mapping to non-scalable ("sub-positional") aesthetic attribute.
 *
 * @param source the assigned raw data source.
 */
inline operator fun <reified DomainType : Any> NonScalablePositionalAes.invoke(
    source: DataSource<DomainType>
) {
    context.bindingCollectorAccessor.mappings[this.name] =
        NonScalablePositionalMapping(this.name, source, typeOf<DomainType>())
}

inline operator fun <reified DomainType : Any> NonScalablePositionalAes.invoke(
    data: Iterable<DomainType>
) {
    context.bindingCollectorAccessor.mappings[this.name] =
        NonScalablePositionalMapping(this.name, with(context){ data.toDataSource()}, typeOf<DomainType>())
}

/**
 * Mapping to an aesthetic attribute with default scale. TODO behavior
 *
 * @param source the assigned raw data source.
 */
inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
    source: DataSource<DomainType>
): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
    val mapping = ScaledUnspecifiedDefaultPositionalMapping(
        this.name,
        source.scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollectorAccessor.mappings[this.name] = mapping
    return mapping
}

inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
    data: Iterable<DomainType>
): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
    val mapping = ScaledUnspecifiedDefaultPositionalMapping(
        this.name,
        with(context){ data.toDataSource()}.scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollectorAccessor.mappings[this.name] = mapping
    return mapping
}

inline operator fun <reified DomainType : Any, RangeType : Any> MappableNonPositionalAes<RangeType>.invoke(
    source: DataSource<DomainType>
): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
    val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
        this.name,
        source.scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollectorAccessor.mappings[this.name] = mapping
    return mapping
}

inline operator fun <reified DomainType : Any, RangeType : Any> MappableNonPositionalAes<RangeType>.invoke(
    data: Iterable<DomainType>
): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
    val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
        this.name,
        with(context){ data.toDataSource()}.scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollectorAccessor.mappings[this.name] = mapping
    return mapping
}

/**
 * Mapping to an aesthetic attribute with default scale. TODO behavior
 *
 * @param sourceScaledDefault the assigned source scaled default.
 */
inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
    sourceScaledDefault: SourceScaledUnspecifiedDefault<DomainType>
): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
    val mapping = ScaledUnspecifiedDefaultPositionalMapping(
        this.name,
        sourceScaledDefault,
        typeOf<DomainType>()
    )
    context.bindingCollectorAccessor.mappings[this.name] = mapping
    return mapping
}

inline operator fun <reified DomainType : Any, RangeType : Any> MappableNonPositionalAes<RangeType>.invoke(
    sourceScaledDefault: SourceScaledUnspecifiedDefault<DomainType>
): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
    val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
        this.name,
        sourceScaledDefault,
        typeOf<DomainType>()
    )
    context.bindingCollectorAccessor.mappings[this.name] = mapping
    return mapping
}

/**
 * Mapping to a positional aesthetic attribute with unspecified scale. TODO behavior
 *
 * @param sourceScaledDefault the assigned source scaled unspecified positional.
 */
inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
    sourceScaledDefault: SourceScaledPositionalDefault<DomainType>
): ScaledPositionalDefaultMapping<DomainType> {
    val mapping = ScaledPositionalDefaultMapping(
        this.name,
        sourceScaledDefault,
        typeOf<DomainType>()
    )
    context.bindingCollectorAccessor.mappings[this.name] = mapping
    return mapping
}

/**
 * Mapping to a non-positional aesthetic attribute with unspecified scale. TODO behavior
 *
 * @param sourceScaledDefault the assigned source scaled unspecified non-positional.
 */
inline operator fun <reified DomainType : Any, RangeType : Any> MappableNonPositionalAes<RangeType>.invoke(
    sourceScaledDefault: SourceScaledNonPositionalDefault<DomainType>
): ScaledNonPositionalDefaultMapping<DomainType, RangeType> {
    val mapping = ScaledNonPositionalDefaultMapping<DomainType, RangeType>(
        this.name,
        sourceScaledDefault,
        typeOf<DomainType>()
    )
    context.bindingCollectorAccessor.mappings[this.name] = mapping
    return mapping
}

/**
 * Mapping to a positional aesthetic attribute.
 *
 * @param sourceScaledPositional the assigned source scaled positional.
 */
inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
    sourceScaledPositional: SourceScaledPositional<DomainType>
): ScaledPositionalMapping<DomainType> {
    val mapping = ScaledPositionalMapping(
        this.name,
        sourceScaledPositional,
        typeOf<DomainType>()
    )
    context.bindingCollectorAccessor.mappings[this.name] = mapping
    return mapping
}

/**
 * Mapping to a non-positional aesthetic attribute. TODO behavior
 *
 * @param sourceScaledNonPositional the assigned source scaled non-positional.
 */
inline operator fun <reified DomainType : Any, reified RangeType : Any>
        MappableNonPositionalAes<RangeType>.invoke(
    sourceScaledNonPositional: SourceScaledNonPositional<DomainType, RangeType>
): ScaledNonPositionalMapping<DomainType, RangeType> {
    val mapping = ScaledNonPositionalMapping(
        this.name,
        sourceScaledNonPositional,
        typeOf<DomainType>()
    )
    context.bindingCollectorAccessor.mappings[this.name] = mapping
    return mapping
}


// todo
abstract class BaseBindingContext: BindingContext() {
    val x = XAes(this)
    val y = YAes(this)
}

/**
 * Layer context interface.
 *
 * todo
 */
abstract class LayerContext : BaseBindingContext() {

    // todo hide
    val features: MutableMap<FeatureName, LayerFeature> = mutableMapOf()
}

class PlotContext : BaseBindingContext() {

    override var data: MutableNamedData = mutableMapOf()

    //val layout = DefaultLayout()
    var layout: Layout? = null

    val layers: MutableList<Layer> = mutableListOf()
    val features: MutableMap<FeatureName, PlotFeature> = mutableMapOf()

}
