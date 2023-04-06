/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.internal

/*
/*
/**
 * Checks if all columns of dataset are the same size.
 */
public fun NamedData.validate() {
    if (nameToValues.isEmpty()) {
        return
    }
    val valuesLists = nameToValues.values
    val firstSize = valuesLists.first().values.size
    require(valuesLists.all {
        it.values.size == firstSize
    }
    ) {
        "All columns of dataset should be the same size"
    }
}

 */

/**
 * Checks if all group keys are the column names.
 */
public fun GroupedData.validate() {
    val df = origin.dataFrame
    keys.forEach {
        require(df.containsColumn(it)) {
            "$it not is the name of the column of the original dataframe"
        }
    }
}

/**
 * Returns dataframe from TableData object. If TableData is GroupedData, takes origin dataframe.
 */
internal fun TableData.getDataFrame(): DataFrame<*> {
    return when (this) {
        is NamedData -> dataFrame
        is GroupedData -> origin.dataFrame
    }
}
/* TODO
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

// TODO(this validation need only for case when we don't work with df initially)
internal fun Map<AesName, Mapping>.validate(df: DataFrame<*>) {
    forEach { (_, mapping) ->
        val column = when (mapping) {
            is ScaledMapping<*> -> mapping.columnScaled.source
            is NonScalableNonPositionalMapping<*> -> mapping.source
            is NonScalablePositionalMapping<*> -> mapping.source
        }
        require(df.containsColumn(column)) {
            "No column with name \"${column.name()}\" found in dataframe with columns ${df.columnNames()}"
        }
        val expectedType = df[column].type
        val actualType = mapping.domainType
        require(expectedType == actualType) {
            "Expected $expectedType as type of the pointer to column \"${column.name()}\" but actual type is $actualType"
        }
    }
}

/**
 * Checks presence of column with a given name.
 */
public fun TableData.validateColumn(columnName: String) {
    val df = getDataFrame()
    require(df.containsColumn(columnName)) {
        "No column with name \"$columnName\" found in dataframe with columns ${df.columnNames()}"
    }
}

/**
 * Validates plot: validates all global mappings and layers.
 */
public fun Plot.validate() {
    globalMappings.validate(dataset.getDataFrame())
    layers.forEach {
        // validate all mappings in each layer with layer or plot data
        it.mappings.validate((it.dataset ?: dataset).getDataFrame())
    }
}

 */
