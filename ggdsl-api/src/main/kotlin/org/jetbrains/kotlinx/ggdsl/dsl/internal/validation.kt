package org.jetbrains.kotlinx.ggdsl.dsl.internal

import org.jetbrains.kotlinx.ggdsl.dsl.LazyGroupedData
import org.jetbrains.kotlinx.ggdsl.dsl.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.ContinuousScale
import kotlin.reflect.KType

public fun NamedData.validate() {
    val valuesLists = nameToValues.values
    val firstSize = valuesLists.first().values.size
    require(valuesLists.all {
        it.values.size == firstSize
    }
    ) {
        "All columns of dataset should be the same size"
    }
}

public fun LazyGroupedData.validate() {
    keys.forEach {
        require(it in origin.nameToValues.keys) {
            "$it not is the name of the column of the original dataframe"
        }
    }
}

public fun Layer.validate(plotDataset: TableData) {
    val columns = (dataset ?: plotDataset).columns()
    mappings.validate(columns)
}

internal fun TableData.columns(): Map<String, KType> {
    return when(this) {
        is NamedDataInterface -> nameToValues.map {
            it.key to it.value.kType
        }.toMap()
        is LazyGroupedDataInterface -> origin.columns()
        is CountedGroupedDataInterface -> this.toLazy().columns()
    }
}
/*
internal fun Map<AesName, Mapping>.validateGroups(groupKys: Set<String>) {
    forEach { (_, mapping) ->
        if (mapping is BaseScaledNonPositionalMapping<*, *>) {
            val columnName = mapping.columnScaled.source.name
            val scale = mapping.columnScaled.scale
            if (scale is Categor)
        }
    }
}

 */

internal fun Map<AesName, Mapping>.validate(columns: Map<String, KType>) {
    val columnNames = columns.keys
    forEach { (_, mapping) ->
        val columnName = when(mapping) {
            is ScaledMapping<*> -> mapping.columnScaled.source.name
            is NonScalableNonPositionalMapping<*> -> mapping.source.name
            is NonScalablePositionalMapping<*> -> mapping.source.name
        }
        require(columnName in columnNames) {
            "No column with name \"$columnName\" found in dataframe with columns $columnNames"
        }
        val expectedType = columns[columnName]
        val actualType = mapping.domainType
        require(expectedType == actualType) {
            "Expected $expectedType as type of the pointer to column \"$columnName\" but actual type is $actualType"
        }
    }
}

public fun TableData.validateColumn(columnName: String) {
    val columns = columns()
    require(columnName in columns) {
        "No column with name \"$columnName\" found in dataframe with columns ${columns.keys}"
    }
}

public fun Plot.validate() {
    globalMappings.validate(dataset.columns())
    layers.forEach {
        it.validate(dataset)
    }
}
