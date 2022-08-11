package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableOnlyNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.ggdsl.letsplot.AlphaAes
import org.jetbrains.kotlinx.ggdsl.letsplot.ColorAes
import org.jetbrains.kotlinx.ggdsl.letsplot.FillAes
import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom
import kotlin.reflect.KType
import kotlin.reflect.typeOf
/*
@PublishedApi
internal val DENSITY = LetsPlotGeom("density")
@PlotDslMarker
// todo move x/y?
class DensityContext(override var data: MutableNamedData) : LayerContext() {

    /* TODO
    data class Stat<T : Any> internal constructor(val name: String) {
        companion object {
            val count = Stat<Int>("..count..")
            val density = Stat<Double>("..density..")
        }
    }

     */

    val alpha = AlphaAes(this)
    val fillColor = FillAes(this)
    val borderLineColor = ColorAes(this)

  //  val boundary = BoundaryAes(this)

    /*
    @PublishedApi
    internal inline fun <reified T : Any> Stat<T>.toDataSource(): DataSource<T> {
        return DataSource(name, typeOf<T>())
    }

    inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
        stat: Stat<DomainType>
    ): ScaledUnspecifiedDefaultPositionalMapping<DomainType> {
        val mapping = ScaledUnspecifiedDefaultPositionalMapping(
            this.name,
            stat.toDataSource().scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }

    inline operator fun <reified DomainType : Any, RangeType : Any>
            MappableNonPositionalAes<RangeType>.invoke(
        stat: Stat<DomainType>
    ): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
        val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
            this.name,
            stat.toDataSource().scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }

     */



/*
    @PublishedApi
    internal var groupOptions: GroupContext<*>? = null

    class GroupContext<T : Any>(val source: DataSource<T>, val type: KType, val context: DensityContext) : BindingContext() {
        override var data: MutableNamedData = mutableMapOf()

        val fillColor = NonMappableFillAes(context)
        val borderLineColor = NonMappableColorAes(context)

        inline operator fun <reified RangeType : Any>
                MappableOnlyNonPositionalAes<RangeType>.invoke(
            scale: NonPositionalCategoricalScale<T, RangeType>
        ): ScaledNonPositionalMapping<T, RangeType> {
            val mapping = ScaledNonPositionalMapping(
                this.name,
                source.scaled(scale),
                type
            )
            context.bindingCollector.mappings[this.name] = mapping
            return mapping
        }
    }




    @PlotDslMarker
    inline fun <reified T : Any> groupBy(source: DataSource<T>, block: GroupContext<T>.() -> Unit) {
        groupOptions = GroupContext(source, typeOf<T>(), this).apply(block)
    }
    */
}

inline fun <reified T : Any> PlotContext.density(source: DataSource<T>, block: DensityContext.() -> Unit) {
    layers.add(
        DensityContext(data)
            .apply {
                copyFrom(this@density)
                x(source)
            }
            .apply(block)
            .toLayer(DENSITY)
    )
}

 */
