package org.jetbrains.kotlinx.ggdsl.letsplot.layers


import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.dsl.toLayer
import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.ggdsl.letsplot.LetsPlotGeom
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import kotlin.reflect.KType
import kotlin.reflect.typeOf


@PublishedApi
internal val HISTOGRAM = LetsPlotGeom("histogram")

internal val BINS = AesName("bins")

data class BinsAes internal constructor(override val context: BindingContext) : NonPositionalAes<Int> {
    override val name = BINS
}

internal val BIN_WIDTH = AesName("binWidth")

data class BinWidthAes internal constructor(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name = BIN_WIDTH
}



val BOUNDARY = AesName("boundary")
data class BoundaryAes internal constructor(override val context: BindingContext) : NonPositionalAes<Double> {
    override val name = BOUNDARY
}

/*
data class NonMappableColorAes internal constructor(override val context: BindingContext) : NonPositionalAes<Color> {
    override val name = COLOR
}

data class NonMappableFillAes internal constructor(override val context: BindingContext) : NonPositionalAes<Color> {
    override val name = FILL
}

data class MappableOnlyColorAes internal constructor(override val context: BindingContext) : MappableOnlyNonPositionalAes<Color> {
    override val name = COLOR
}

data class MappableOnlyFillAes internal constructor(override val context: BindingContext) : MappableOnlyNonPositionalAes<Color> {
    override val name = FILL
}

 */

@PlotDslMarker
// todo move x/y?
class HistogramContext(override var data: MutableNamedData) : LayerContext() {

    data class Stat<T : Any> internal constructor(val name: String) {
        companion object {
            val count = Stat<Int>("..count..")
            val density = Stat<Double>("..density..")
        }
    }

    val alpha = AlphaAes(this)
    val fillColor = FillAes(this)
    val borderLineColor = ColorAes(this)

    val boundary = BoundaryAes(this)

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


    interface BinOption {
        data class ByNumber(val number: Int) : BinOption
        data class ByWidth(val width: Double) : BinOption
    }

    fun byNumber(number: Int) = BinOption.ByNumber(number)
    fun byWidth(width: Double) = BinOption.ByWidth(width)

    private val _bins = BinsAes(this)
    private val binWidth = BinWidthAes(this)

    var bins: BinOption? = null
        set(value) {
            when (value) {
                is BinOption.ByNumber -> {
                    bindingCollector.settings.remove(BIN_WIDTH)
                    _bins(value.number)
                }

                is BinOption.ByWidth -> {
                    bindingCollector.settings.remove(BINS)
                    binWidth(value.width)
                }

                else -> {
                    bindingCollector.settings.remove(BINS)
                    bindingCollector.settings.remove(BIN_WIDTH)
                }
            }
            field = null
        }

    /*
    @PublishedApi
    internal var groupOptions: GroupContext<*>? = null

    class GroupContext<T : Any>(val source: DataSource<T>, val type: KType, val context: HistogramContext) : BindingContext() {
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

inline fun <reified T : Any> PlotContext.histogram(source: DataSource<T>, block: HistogramContext.() -> Unit) {
    layers.add(
        HistogramContext(data)
            .apply {
                copyFrom(this@histogram)
                x(source)
            }
            .apply(block)
            .toLayer(HISTOGRAM)
    )
}


