package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.*
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalFreeScale

/**
 * The class responsible for handling bindings. It is used by builders in which bindings are made.
 */
internal open class BindingHandler(private val datasetBuilderAccessor: () -> DatasetBuilder) {
    val bindingCollector: BindingCollector = BindingCollector()
    var firstMapping = true
    val datasetBuilder: DatasetBuilder
        get() = datasetBuilderAccessor()

    open fun checkMappingSourceSize(size: Int) {
        val rowsCount = datasetBuilder.rowsCount()
        if (rowsCount == 0) {
            return
        }
        if (rowsCount != size) {
            error("Unexpected size of mapping source: excepted $rowsCount, but received $size")
        }
    }

    /**
     * Adds a [non-positional setting][NonPositionalSetting] with the given aes and value.
     *
     * @param aes the aesthetic attribute (aes) to associate with the non-positional setting.
     * @param value the value of the setting.
     * @return the newly created [NonPositionalSetting] object with the provided aes and value.
     */
    fun <DomainType> addNonPositionalSetting(
        aes: Aes,
        value: DomainType
    ): NonPositionalSetting<DomainType> {
        return NonPositionalSetting(aes, value).also {
            bindingCollector.settings[aes] = it
        }
    }

    /**
     * Adds a [positional setting][PositionalSetting] with the given aes and value.
     *
     * @param aes the aesthetic attribute (aes) to associate with the positional setting.
     * @param value the value of the setting.
     * @return the newly created [PositionalSetting] object with the provided aes and value.
     */
    fun <DomainType> addPositionalSetting(aes: Aes, value: DomainType): PositionalSetting<DomainType> {
        return PositionalSetting(aes, value).also {
            bindingCollector.settings[aes] = it
        }
    }

    /**
     * Creates and adds a [positional mapping][PositionalMapping] for a given aesthetic attribute
     * ([aes]) and [values].
     *
     * @param aes the aesthetic attribute (aes) to be mapped.
     * @param values the list of values to be mapped.
     * @param name the name of the mapping (optional, defaults to the name of the aes).
     * @param parameters the positional mapping parameters (optional).
     * @return the created [positional mapping][PositionalMapping].
     */
    fun <DomainType> addPositionalMapping(
        aes: Aes, values: List<DomainType>, name: String?, parameters: PositionalMappingParameters<DomainType>?
    ): PositionalMapping<DomainType> {
        checkMappingSourceSize(values.size)
        val columnID = datasetBuilder.addColumn(values, name ?: aes.name)
        return PositionalMapping(aes, columnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }.also {
            firstMapping = false
        }
    }

    /**
     * Creates and adds a [positional mapping][PositionalMapping] for a given aesthetic attribute
     * ([aes]), [columnID], and [parameters].
     *
     * @param aes the aesthetic attribute (aes) to be mapped.
     * @param columnID the column ID from dataset to be mapped.
     * @param parameters the positional mapping parameters (optional).
     * @return the created [positional mapping][PositionalMapping].
     */
    fun <DomainType> addPositionalMapping(
        aes: Aes, columnID: String, parameters: PositionalMappingParameters<DomainType>?
    ): PositionalMapping<DomainType> {
        val newColumnID = datasetBuilder.takeColumn(columnID)
        return PositionalMapping(aes, newColumnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }.also {
            firstMapping = false
        }
    }

    /**
     * Creates and adds a [non-positional mapping][NonPositionalMapping] for a given aesthetic attribute
     * ([aes]) and [values].
     *
     * @param aes the aesthetic attribute (aes) to be mapped.
     * @param values the list of values to be mapped.
     * @param name the name of the mapping (optional, defaults to the name of the aes).
     * @param parameters the non-positional mapping parameters (optional).
     * @return the created [non-positional mapping][NonPositionalMapping].
     */
     fun <DomainType, RangeType> addNonPositionalMapping(
        aes: Aes,
        values: List<DomainType>,
        name: String?,
        parameters: NonPositionalMappingParameters<DomainType, RangeType>?
    ): NonPositionalMapping<DomainType, RangeType> {
        checkMappingSourceSize(values.size)
        val columnID = datasetBuilder.addColumn(values, name ?: aes.name)
        return NonPositionalMapping(aes, columnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }.also {
            firstMapping = false
        }
    }

    /**
     * Creates and adds a [non-positional mapping][NonPositionalMapping] for a given aesthetic attribute
     * ([aes]), [columnID], and [parameters].
     *
     * @param aes the aesthetic attribute (aes) to be mapped.
     * @param columnID the column ID from dataset to be mapped.
     * @param parameters the non-positional mapping parameters (optional).
     * @return the created [non-positional mapping][NonPositionalMapping].
     */
     fun <DomainType, RangeType> addNonPositionalMapping(
        aes: Aes,
        columnID: String,
        parameters: NonPositionalMappingParameters<DomainType, RangeType>?
    ): NonPositionalMapping<DomainType, RangeType> {
        val newColumnID = datasetBuilder.takeColumn(columnID)
        return NonPositionalMapping(aes, newColumnID, parameters).also {
            bindingCollector.mappings[aes] = it
        }.also {
            firstMapping = false
        }
    }

    /**
     * Adds a [non-positional mapping][NonPositionalMapping] for a given positional aesthetic attribute
     * ([aes]) and [parameters] to [binding collector][BindingCollector].
     *
     * @param aes the positional aesthetic attribute (aes) to be mapped.
     * @param parameters the positional mapping parameters (optional).
     */
    fun <DomainType> addPositionalFreeScale(
        aes: Aes,
        parameters: PositionalMappingParameters<DomainType>
    ) {
        bindingCollector.freeScales[aes] = PositionalFreeScale(aes, parameters)
    }
}
