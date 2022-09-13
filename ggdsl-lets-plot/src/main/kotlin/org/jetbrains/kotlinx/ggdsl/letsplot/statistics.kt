package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.scaled
import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultNonPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.bindings.ScaledUnspecifiedDefaultPositionalMapping
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import kotlin.reflect.typeOf

interface Statistic<T> {
    val name: String
}

@PublishedApi
internal inline fun <reified T : Any> Statistic<T>.toDataSource(): DataSource<T> {
    return DataSource(name, typeOf<T>())
}

sealed interface BinStatistic<T>: Statistic<T> {
    class Bins<R>: BinStatistic<R> {
        override val name = "..x.."
    }
    object Count: BinStatistic<Int> {
        override val name = "..count.."
    }
    object Density: BinStatistic<Double> {
        override val name = "..density.."
    }
}

inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
    stat: Statistic<DomainType>
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
    stat: Statistic<DomainType>
): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
    val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
        this.name,
        stat.toDataSource().scaled(),
        typeOf<DomainType>()
    )
    context.bindingCollector.mappings[this.name] = mapping
    return mapping
}

class BinStatisticContext<T: Any> @PublishedApi internal constructor(
    val sampleSource: DataSource<T>,
    inputData: NamedData,
    ) {
  //  val data = countBins(inputData, sampleSource)
    val STAT_BINS = BinStatistic.Bins<T>()
    val STAT_COUNT = BinStatistic.Count
    val STAT_DENSITY = BinStatistic.Density

}

inline fun<T: Any> PlotContext.statBin(
    sampleSource: DataSource<T>,
    inputData: NamedData? = null,
    block: BinStatisticContext<T>.() -> Unit) {
    BinStatisticContext(sampleSource, inputData ?: data).apply(block)
}

/*
inline fun<reified T: Any> BindingContext.statBin(
    sampleSource: Iterable<T>,
    binsOption: Bins? = null,
    block: BinStatisticContext<T>.() -> Unit
) {
    BinStatisticContext(sampleSource.toDataSource()).apply(block)
}

 */