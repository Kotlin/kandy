package org.jetbrains.kotlinx.ggdsl.letsplot.stat.bin

import jetbrains.datalore.plot.base.DataFrame
import jetbrains.datalore.plot.base.data.TransformVar
import jetbrains.datalore.plot.base.stat.BinStat
import jetbrains.datalore.plot.base.stat.SimpleStatContext
import jetbrains.datalore.plot.base.stat.Stats
import jetbrains.datalore.plot.builder.data.GroupUtil
import jetbrains.datalore.plot.builder.data.GroupingContext
import jetbrains.datalore.plot.common.data.SeriesUtil
import kotlinx.datetime.*
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.ir.data.GroupedData
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import kotlin.reflect.KType
import kotlin.reflect.typeOf


internal fun BinXPos.toKind(): BinStat.XPosKind = when(this) {
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

internal class TypeProcessor(val type: KType) {
   private val datetimeTypes = listOf(
        typeOf<Instant>(),
        typeOf<LocalDateTime>(),
        typeOf<LocalDate>(),
        typeOf<LocalTime>()
    )

    private fun Instant.toDouble(): Double {
        return toEpochMilliseconds().toDouble()
    }

    private fun Double.toInstant(): Instant {
        return Instant.fromEpochMilliseconds(toLong())
    }

    private val timezone = TimeZone.currentSystemDefault()

    private val typeToPreprocessor: Map<KType, (Any) -> Double> = mapOf(
        typeOf<Instant>() to {
            (it as Instant).toDouble()
        },
        typeOf<LocalDateTime>() to {
            (it as LocalDateTime).toInstant(timezone).toDouble()
        },
        typeOf<LocalDate>() to {
           (it as LocalDate).toEpochDays().toDouble()
        } ,
        typeOf<LocalTime>() to {
            (it as LocalTime).toNanosecondOfDay().toDouble()
        }
    )

    private val typeToPostprocessor: Map<KType, (Double) -> Any> = mapOf(
        typeOf<Instant>() to {
            it.toInstant()
        } ,
        typeOf<LocalDateTime>() to {
            it.toInstant().toLocalDateTime(timezone)
        },
        typeOf<LocalDate>() to {
            LocalDate.fromEpochDays(it.toInt())
            //it.toInstant().toLocalDateTime(timezone).date
        },
        typeOf<LocalTime>() to {
            LocalTime.fromNanosecondOfDay(it.toLong())
        }
    )

    fun preprocess(value: Any): Double {
        return when(value) {
            is Number -> value.toDouble()
            else -> typeToPreprocessor[type]!!(value)
        }
    }

    fun postprocess(value: Double): Any {
        return if (type in datetimeTypes) {
            typeToPostprocessor[type]!!(value)
        }  else {
            value
        }
    }
}

@PublishedApi
internal fun countBinsImpl(
    data: NamedData,
    column: ColumnReference<*>,
    bins: Bins,
    binXPos: BinXPos
): NamedData {
    val (binCount, binWidth) = when(bins) {
        is Bins.ByNumber -> bins.number to null
        is  Bins.ByWidth -> 0 to bins.width
    }
    val stat = BinStat(binCount, binWidth, binXPos.toKind(), binXPos.posValue)

    val typeProcessor = TypeProcessor(data.dataFrame[column].type())

    val df = data.toDataFrame(column, listOf(), typeProcessor)

    val statContext = SimpleStatContext(df)
    val dfCounted = stat.apply(df, statContext)

    //println(dfCounted[Stats.X])
    return NamedData(dataFrameOf(
        BINS to dfCounted[Stats.X].map { typeProcessor.postprocess(it as Double)},
        COUNT to dfCounted[Stats.COUNT].map { it as Double },
        DENSITY to dfCounted[Stats.DENSITY].map { it as Double },
    ))
}

@PublishedApi
internal fun countBinsImpl(
    data: GroupedData,
    column: ColumnReference<*>,
    bins: Bins,
    binXPos: BinXPos
): GroupedData {
    val variables = data.keys.map { DataFrame.Variable(it) }
    val typeProcessor = TypeProcessor(data.origin.dataFrame[column].type())
    val df = data.origin.toDataFrame(column, variables, typeProcessor)
    val groupingContext = GroupingContext(df, variables, null, true)

    val (binCount, binWidth) = when(bins) {
        is Bins.ByNumber -> bins.number to null
        is  Bins.ByWidth -> 0 to bins.width
    }
    val stat = BinStat(binCount, binWidth, binXPos.toKind(), binXPos.posValue)
    val statContext = SimpleStatContext(df)
    val buffer = mutableMapOf<String, MutableList<Any>>(
        BINS to mutableListOf(),
        COUNT to mutableListOf(),
        DENSITY to mutableListOf()
    )

    variables.forEach {
        buffer[it.name] = mutableListOf()
    }

    for (d in splitByGroup(df, groupingContext.groupMapper)) {
        val dfCounted = stat.apply(d, statContext)
        val size = dfCounted[Stats.X].size
        buffer[BINS]!!.addAll(dfCounted[Stats.X].map { typeProcessor.postprocess(it as Double) })
        buffer[COUNT]!!.addAll(dfCounted[Stats.COUNT].map { it as Double })
        buffer[DENSITY]!!.addAll(dfCounted[Stats.DENSITY].map { it as Double })
        variables.forEach { variable ->
            buffer[variable.name]!!.addAll(List(size) {d[variable].first()!!})
        }
    }


    return GroupedData(
        buffer.toDataFrame(),
        data.keys
    )
}

// tODO
internal fun NamedData.toDataFrame(
    column: ColumnReference<*>,
    variables: List<DataFrame.Variable>,
    typeProcessor: TypeProcessor
): DataFrame {
    //println(nameToValues)
    var builder =
        DataFrame.Builder().putNumeric(TransformVar.X, dataFrame[column.name()].values().map {
            typeProcessor.preprocess((it!!))
        })
    //println(builder.build()[TransformVar.X])
    variables.forEach {
        builder = builder.putDiscrete(it, dataFrame[it.name].values().toList())
    }

    return builder.build()
}
