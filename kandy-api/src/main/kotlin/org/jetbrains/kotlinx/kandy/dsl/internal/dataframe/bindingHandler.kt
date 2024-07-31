package org.jetbrains.kotlinx.kandy.dsl.internal.dataframe

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParameters

/**
 * Creates and adds a [positional mapping][PositionalMapping] for a given aesthetic attribute
 * ([aes]), [values], and [parameters].
 *
 * @param aes the aesthetic attribute (aes) to be mapped.
 * @param values the column of values to be mapped.
 * @param parameters the positional mapping parameters (optional).
 * @return the created [positional mapping][PositionalMapping].
 */
internal fun <DomainType> BindingHandler.addPositionalMapping(
    aes: Aes, values: DataColumn<DomainType>, parameters: PositionalMappingParameters<DomainType>?
): PositionalMapping<DomainType> {
    checkMappingSourceSize(values.size())
    val columnID = (datasetBuilder as DatasetBuilderImpl).addColumn(values)
    return PositionalMapping(aes, columnID, parameters).also {
        bindingCollector.mappings[aes] = it
    }.also {
        firstMapping = false
    }
}

/**
 * Creates and adds a [non-positional mapping][NonPositionalMapping] for a given aesthetic attribute
 * ([aes]), [values], and [parameters].
 *
 * @param aes the aesthetic attribute (aes) to be mapped.
 * @param values the [DataColumn] of values to be mapped.
 * @param parameters the non-positional mapping parameters (optional).
 * @return the created [non-positional mapping][NonPositionalMapping].
 */
internal fun <DomainType, RangeType> BindingHandler.addNonPositionalMapping(
    aes: Aes,
    values: DataColumn<DomainType>,
    parameters: NonPositionalMappingParameters<DomainType, RangeType>?
): NonPositionalMapping<DomainType, RangeType> {
    checkMappingSourceSize(values.size())
    val columnID = (datasetBuilder as DatasetBuilderImpl).addColumn(values)
    return NonPositionalMapping(aes, columnID, parameters).also {
        bindingCollector.mappings[aes] = it
    }.also {
        firstMapping = false
    }
}
