package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.kandy.ir.data.TableData

internal class NamedDataBuilder(
    namedData: NamedData, initialBuilder: DatasetBuilderImpl? = null
) : DatasetBuilderImpl(initialBuilder) {
    constructor(df: DataFrame<*>, initialBuilder: DatasetBuilderImpl? = null) : this(NamedData(df), initialBuilder)

    override val baseDataFrame: DataFrame<*> = namedData.dataFrame

    override fun build(): TableData {
        return NamedData(buffer)
    }

}
