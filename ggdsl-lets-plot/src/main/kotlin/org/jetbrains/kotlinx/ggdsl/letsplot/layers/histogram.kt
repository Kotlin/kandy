package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.dsl.toLayer
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import kotlin.reflect.KType
import kotlin.reflect.typeOf

// TODO

@PublishedApi
internal val HISTOGRAM = LetsPlotGeom("histogram")

@PlotDslMarker
class HistogramContext(override var data: MutableNamedData) : WithBorderLineContext() {

    interface BinOption {
        data class ByNumber(val number: Int): BinOption
        data class ByWidth(val width: Double): BinOption
    }

    fun byNumber(number: Int) = BinOption.ByNumber(number)
    fun byWidth(width: Double) = BinOption.ByWidth(width)

    var bins: BinOption? = null

    @PublishedApi
    internal var groupOptions: GroupContext<*>? = null

    class GroupContext<T: Any>(val source: DataSource<T>, val type: KType) : BindingContext() {
        override var data: MutableNamedData = mutableMapOf()
        val color = ColorAes(this)

        inline operator fun <reified RangeType : Any>
                MappableNonPositionalAes<RangeType>.invoke(
            scale: NonPositionalCategoricalScale<T, RangeType>
        ): ScaledNonPositionalMapping<T, RangeType> {
            val mapping = ScaledNonPositionalMapping(
                this.name,
                source.scaled(scale),
                typeOf<RangeType>() // todo
            )
            context.bindingCollector.mappings[this.name] = mapping
            return mapping
        }
    }


    inline fun<reified T: Any> groupBy(source: DataSource<T>, block: GroupContext<T>.() -> Unit){
        groupOptions = GroupContext(source, typeOf<T>()).apply(block)
    }

}

inline fun PlotContext.histogram(block: HistogramContext.() -> Unit) {
    layers.add(
        HistogramContext(data)
        .apply { copyFrom(this@histogram) }
        .apply(block)
        .toLayer(HISTOGRAM)
    )
}
