package org.jetbrains.kotlinx.ggdsl.letsplot

/*

interface Statistic<T> {
    val name: String
}

inline fun <reified DomainType : Any> Statistic<DomainType>.scaled() =
    SourceScaledUnspecifiedDefault(this.toDataSource())


inline fun <reified DomainType : Any> Statistic<DomainType>.scaled(scale: PositionalUnspecifiedScale) =
    SourceScaledPositionalUnspecified(this.toDataSource(), scale)



inline fun <reified DomainType : Any> Statistic<DomainType>.scaled(scale: NonPositionalUnspecifiedScale) =
    SourceScaledNonPositionalUnspecified(this.toDataSource(), scale)


inline fun <reified DomainType : Any> Statistic<DomainType>.scaled(
    scale: PositionalScale<DomainType>
) = SourceScaledPositional(this.toDataSource(), scale)


inline fun <reified DomainType : Any, RangeType : Any> Stat<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
) = SourceScaledNonPositional(this.toDataSource(), scale)

@PublishedApi
internal inline fun <reified T : Any> Statistic<T>.toDataSource(): ColumnPointer<T> {
    return ColumnPointer(name, typeOf<T>())
}

sealed interface BinStatistic<T>: Statistic<T> {
    class Bins<R>: BinStatistic<R> {
        override val name = NAME
        companion object {
            val NAME = "..x.."
        }
    }
    object Count: BinStatistic<Int> {
        val NAME = "..count.."
        override val name = NAME

    }
    object Density: BinStatistic<Double> {
        val NAME = "..density.."
        override val name = NAME

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

internal fun<T: Any> countBins(data: NamedDataInterface, sampleSource: ColumnPointer<T>): NamedDataInterface {
    // TODO
    val inputDF = DataFrame.Builder().putNumeric(TransformVar.X, data[sampleSource.id] as List<Double>).build()
    val ctx = SimpleStatContext(inputDF)
    val countedDF = BinStat(5, null, BinStat.XPosKind.CENTER, 0.0).apply(inputDF, ctx)
    return countedDF.let {
        mapOf(
            BinStatistic.Bins.NAME to countedDF[Stats.X] as List<Any>
        )
    }
}

@StatDslMarker
class BinStatisticContext<T: Any> @PublishedApi internal constructor(
    val inputData: MutableNamedData,
    val sampleSource: ColumnPointer<T>,
): LayerCollectorContext() {
    override val data: MutableNamedData = countBins(inputData, sampleSource).toMutableMap() // TODO
    val STAT_BINS = BinStatistic.Bins<T>()
    val STAT_COUNT = BinStatistic.Count
    val STAT_DENSITY = BinStatistic.Density

}

inline fun<T: Any> PlotContext.statBin(
    sampleSource: ColumnPointer<T>,
    inputData: NamedDataInterface? = null,
    block: BinStatisticContext<T>.() -> Unit
) {
    layers.addAll(
        BinStatisticContext(inputData?.toMutableMap() ?: data, sampleSource).apply(block).layers
    )
}

inline fun<reified T: Any> PlotContext.statBin(
    sampleSource: Iterable<T>,
    inputData: NamedDataInterface? = null,
    block: BinStatisticContext<T>.() -> Unit
) {
    layers.addAll(
        BinStatisticContext(inputData?.toMutableMap() ?: data, sampleSource.toDataSource()).apply(block).layers
    )
}



 */