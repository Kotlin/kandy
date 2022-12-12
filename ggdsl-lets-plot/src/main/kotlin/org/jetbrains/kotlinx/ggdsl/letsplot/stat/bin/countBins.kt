package org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin

import jetbrains.datalore.plot.base.DataFrame
import jetbrains.datalore.plot.base.data.TransformVar
import jetbrains.datalore.plot.base.stat.BinStat
import jetbrains.datalore.plot.base.stat.SimpleStatContext
import jetbrains.datalore.plot.base.stat.Stats
import jetbrains.datalore.plot.builder.data.GroupUtil
import jetbrains.datalore.plot.builder.data.GroupingContext
import jetbrains.datalore.plot.common.data.SeriesUtil
import org.jetbrains.kotlinx.ggdsl.dsl.LazyGroupedData
import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.dsl.internal.toTyped
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface

internal fun BinXPos.toKind():BinStat.XPosKind = when(this) {
    is BinXPos.None -> BinStat.XPosKind.NONE
    is BinXPos.Boundary -> BinStat.XPosKind.BOUNDARY
    is BinXPos.Center ->  BinStat.XPosKind.CENTER
}

// TODO internal from lp core
internal fun splitByGroup(data: DataFrame, groups: (Int) -> Int): List<DataFrame> {
    return GroupUtil.indicesByGroup(data.rowCount(), groups).values.map { indices ->
        data.variables().fold(DataFrame.Builder()) { b, variable ->
            when (data.isNumeric(variable)) {
                true -> b.putNumeric(variable, SeriesUtil.pickAtIndices(data.getNumeric(variable), indices))
                false -> b.putDiscrete(variable, SeriesUtil.pickAtIndices(data[variable], indices))
            }
        }
    }.map(DataFrame.Builder::build)
}

@PublishedApi
internal fun countBinsImpl(
    data: NamedDataInterface,
    column: ColumnPointer<*>,
    bins: Bins,
    binXPos: BinXPos
): NamedData {
    val (binCount, binWidth) = when(bins) {
        is Bins.ByNumber -> bins.number to null
        is  Bins.ByWidth -> 0 to bins.width
    }
    val stat = BinStat(binCount, binWidth, binXPos.toKind(), binXPos.posValue)
    val df = data.toDataFrame(column, listOf())
    val statContext = SimpleStatContext(df)
    val dfCounted = stat.apply(df, statContext)

    return dataOf {
        BinStatistic.Bins.NAME to dfCounted[Stats.X].map { it as Double }
        BinStatistic.Count.NAME to dfCounted[Stats.COUNT].map { it as Double }
        BinStatistic.Density.NAME to dfCounted[Stats.DENSITY].map { it as Double }
    }
}

@PublishedApi
internal fun countBinsImpl(
    data: LazyGroupedDataInterface,
    column: ColumnPointer<*>,
    bins: Bins,
    binXPos: BinXPos
): LazyGroupedData {
    val variables = data.keys.map { DataFrame.Variable(it) }
    val df = data.origin.toDataFrame(column, variables)
    val groupingContext = GroupingContext(df, variables, null, true)

    val (binCount, binWidth) = when(bins) {
        is Bins.ByNumber -> bins.number to null
        is  Bins.ByWidth -> 0 to bins.width
    }
    val stat = BinStat(binCount, binWidth, binXPos.toKind(), binXPos.posValue)
    val statContext = SimpleStatContext(df)
    val buffer = mutableMapOf<String, MutableList<Any>>(
        BinStatistic.Bins.NAME to mutableListOf(),
        BinStatistic.Count.NAME to mutableListOf(),
        BinStatistic.Density.NAME to mutableListOf()
    )

    variables.forEach {
        buffer[it.name] = mutableListOf()
    }

    for (d in splitByGroup(df, groupingContext.groupMapper)) {
        val dfCounted = stat.apply(d, statContext)
        val size = dfCounted[Stats.X].size
        buffer[BinStatistic.Bins.NAME]!!.addAll(dfCounted[Stats.X].map { it as Any })
        buffer[BinStatistic.Count.NAME]!!.addAll(dfCounted[Stats.COUNT].map { it as Any })
        buffer[BinStatistic.Density.NAME]!!.addAll(dfCounted[Stats.DENSITY].map { it as Any })
        variables.forEach { variable ->
            buffer[variable.name]!!.addAll(List(size) {d[variable].first()!!})
        }
    }


    return LazyGroupedData(
        data.keys,
        NamedData(buffer.toTyped())
    )
}

// tODO
internal fun NamedDataInterface.toDataFrame(
    column: ColumnPointer<*>,
    variables: List<DataFrame.Variable>,
): DataFrame {
    //println(nameToValues)
    var builder =
        DataFrame.Builder().putNumeric(TransformVar.X, nameToValues[column.name]!!.values.map { (it as Number).toDouble() })
    variables.forEach {
        builder = builder.putDiscrete(it, nameToValues[it.name]!!.values)
    }


    return builder.build()
}
