package org.jetbrains.kotlinx.kandy.echarts.translator

import kotlinx.datetime.*
import org.jetbrains.kotlinx.dataframe.AnyCol
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.fillNA
import org.jetbrains.kotlinx.dataframe.api.isNotEmpty
import org.jetbrains.kotlinx.dataframe.api.map
import org.jetbrains.kotlinx.dataframe.api.with
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.GroupedData
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.NamedData
import org.jetbrains.kotlinx.kandy.echarts.layers.*
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.*
import org.jetbrains.kotlinx.kandy.echarts.scale.EchartsPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.echarts.translator.option.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.Encode
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.Element
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalSetting
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import org.jetbrains.kotlinx.kandy.ir.scale.*
import kotlin.reflect.KType
import kotlin.reflect.typeOf

internal fun Plot.toOption(): Option = Parser(this).parse()

@Suppress("UNCHECKED_CAST")
internal fun <T> Map<Aes, Setting>.getNPSValue(key: Aes): T? {
    return (this[key] as? NonPositionalSetting<*>)?.value as? T
}

internal class Parser(plot: Plot) {

    // elements from plot
    private val datasets: MutableList<TableData> = plot.datasets as MutableList<TableData>
    private val globalMappings = plot.globalMappings
    private val layers = plot.layers
    private val features = plot.features

    // items for option
    private val xAxis = mutableListOf<Axis>()
    private val yAxis = mutableListOf<Axis>()

    private val typeMapping: Map<KType, AxisType> = mapOf(
        typeOf<String>() to AxisType.CATEGORY, typeOf<String?>() to AxisType.CATEGORY,
        typeOf<Char>() to AxisType.CATEGORY, typeOf<Char?>() to AxisType.CATEGORY,
        typeOf<Number>() to AxisType.VALUE, typeOf<Number?>() to AxisType.VALUE,
        typeOf<LocalDateTime>() to AxisType.TIME, typeOf<LocalDateTime?>() to AxisType.TIME,
        typeOf<java.time.LocalDateTime>() to AxisType.TIME, typeOf<java.time.LocalDateTime?>() to AxisType.TIME,
        typeOf<LocalDate>() to AxisType.TIME, typeOf<LocalDate?>() to AxisType.TIME,
        typeOf<java.time.LocalDate>() to AxisType.TIME, typeOf<java.time.LocalDate?>() to AxisType.TIME,
        typeOf<LocalTime>() to AxisType.CATEGORY, typeOf<LocalTime?>() to AxisType.CATEGORY,
        typeOf<Month>() to AxisType.CATEGORY, typeOf<Month?>() to AxisType.CATEGORY,
        typeOf<DayOfWeek>() to AxisType.CATEGORY, typeOf<DayOfWeek?>() to AxisType.CATEGORY,
        typeOf<java.time.LocalTime>() to AxisType.CATEGORY, typeOf<java.time.LocalTime?>() to AxisType.CATEGORY,
        typeOf<java.time.Month>() to AxisType.CATEGORY, typeOf<java.time.Month?>() to AxisType.CATEGORY,
        typeOf<java.time.DayOfWeek>() to AxisType.CATEGORY, typeOf<java.time.DayOfWeek?>() to AxisType.CATEGORY
    )


    /**
     * Processes a NamedData dataset by filling null values based on mappings.
     *
     * @param dataset The dataset to process
     * @param globalMappings The global mappings to apply
     * @param layerMappings The layer-specific mappings to apply
     * @return The processed dataset with null values filled
     */
    private fun processNamedDataset(
        dataset: NamedData,
        globalMappings: Map<Aes, Mapping>,
        layerMappings: Map<Aes, Mapping>
    ): NamedData {
        var processedDataFrame = dataset.dataFrame

        // Apply fillNA for global mappings
        globalMappings.values.forEach { mapping ->
            processedDataFrame = processedDataFrame.fillNA(mapping)
        }

        // Apply fillNA for layer-specific mappings
        layerMappings.values.forEach { mapping ->
            processedDataFrame = processedDataFrame.fillNA(mapping)
        }

        // Return a new NamedData if the dataframe was modified
        return if (processedDataFrame !== dataset.dataFrame) {
            NamedData(processedDataFrame)
        } else {
            dataset
        }
    }

    internal fun parse(): Option {
        val layout = features[EChartsLayout.FEATURE_NAME] as? EChartsLayout
        val mainDataset = datasets.first()

        // Process global mappings
        with(globalMappings) {
            this[X]?.also { xAxis.add(it.toAxis(mainDataset.getType(it))) }
            this[Y]?.also { yAxis.add(it.toAxis(mainDataset.getType(it))) }
        }

        val title = layout?.title?.toEchartsTitle()
        val legend = layout?.legend?.toEchartsLegend()
        val grid = layout?.grid?.toEchartsGrid()
        val tooltip = layout?.tooltip?.toEchartsTooltip()
        val textStyle = layout?.textStyle?.toTextStyle()
        val animation = layout?.animation?.toAnimationPlotFeature()

        val visualMaps = mutableListOf<VisualMap>()
        val series = mutableListOf<Series>()

        // Process layers and fill null values
        layers.forEachIndexed { index, layer ->
            // Process the dataset if it's a NamedData
            if (mainDataset is NamedData) {
                val processedDataset = processNamedDataset(mainDataset, globalMappings, layer.mappings)
                if (processedDataset !== mainDataset) {
                    datasets[0] = processedDataset
                }
            }

            // Process mappings for visualization
            layer.mappings.forEach { (aes, mapping) ->
                val df = datasets[layer.datasetIndex]
                when (aes) {
                    X -> xAxis.add(mapping.toAxis(df.getType(mapping)))
                    Y, Y_OPEN, Y_CLOSE, Y_LOW, Y_HIGH -> yAxis.add(mapping.toAxis(df.getType(mapping)))
                    PIE_DATA_NAME, PIE_DATA_VALUES -> {}
                    else -> {
                        val scale = mapping.parameters?.scale
                        if (scale is NonPositionalScale<*, *>) {
                            visualMaps.add(
                                scale.toVisualMap(
                                    aes,
                                    mapping.columnID,
                                    index,
                                    df[mapping].toList(),
                                    visualMaps.size,
                                    df.getType(mapping)
                                )
                            )
                        }
                    }
                }
            }

            when {
                layer.datasetIndex == 0 && datasets[layer.datasetIndex] !is GroupedData -> series.add(layer.toSeries())
                datasets[layer.datasetIndex] is GroupedData -> series.addAll(layer.toGroupedSeries())
                else -> throw IllegalStateException("Unsupported dataset configuration with index ${layer.datasetIndex}")
            }
        }

        // Create source data for the option
        val processedMainDataset = datasets.first()
        val source = if (processedMainDataset is NamedData && processedMainDataset.dataFrame.isNotEmpty()) {
            listOf(
                processedMainDataset.dataFrame.columnNames()
                    .map { Element.of(it) }) + processedMainDataset.dataFrame.map {
                it.values().map { value -> Element.of(value) }
            }
        } else null
        val dataset = source?.let { Dataset(source = it) }

        return Option(
            title = title,
            legend = legend,
            grid = grid,
            xAxis = xAxis.firstOrNull(),
            yAxis = yAxis.firstOrNull(),
            visualMap = visualMaps.ifEmpty { null },
            tooltip = tooltip,
            dataset = dataset,
            series = series.ifEmpty { null },
            textStyle = textStyle,
            animation = animation?.enable,
            animationThreshold = animation?.threshold,
            animationDuration = animation?.duration,
            animationEasing = animation?.easing,
            animationDelay = animation?.delay,
            plotSize = layout?.size ?: (800 to 600)
        )
    }

    /**
     * Retrieves the [AnyCol] from [DataFrame] data corresponding to the specified [mapping].
     *
     * @param mapping The [Mapping] object containing the `columnID` to map to the appropriate data in [TableData].
     * @return The [AnyCol] data corresponding to the specified [mapping].
     * @throws IllegalStateException if the TableData is not a supported type
     */
    private operator fun TableData.get(mapping: Mapping): AnyCol = when (this) {
        is NamedData -> dataFrame[mapping.columnID]
        is GroupedData -> dataFrame[mapping.columnID]
        else -> throw IllegalStateException("Unsupported TableData type: ${this::class.simpleName}")
    }

    /**
     * Gets the Kotlin type of the column specified by the mapping.
     *
     * @param mapping The mapping containing the column ID
     * @return The Kotlin type of the column
     * @throws IllegalStateException if the TableData is not a supported type
     */
    private fun TableData.getType(mapping: Mapping): KType = when (this) {
        is NamedData -> dataFrame[mapping.columnID].type()
        is GroupedData -> dataFrame[mapping.columnID].type()
        else -> throw IllegalStateException("Unsupported TableData type: ${this::class.simpleName}")
    }

    /**
     * Fills null values in the specified column with the nullValue from the scale if available.
     *
     * @param mapping The mapping containing the column ID and scale parameters
     * @return A new DataFrame with null values filled, or the original DataFrame if no nullValue is specified
     */
    private fun DataFrame<*>.fillNA(mapping: Mapping): DataFrame<*> {
        val scale = mapping.parameters?.scale
        val nullValue = when (scale) {
            is PositionalContinuousScale<*> -> scale.nullValue
            is NonPositionalContinuousScale<*, *> -> scale.nullValue
            else -> null
        }

        return when {
            nullValue != null -> fillNA(mapping.columnID).with { nullValue }
            else -> this
        }
    }

    /**
     * Converts a Mapping to an Axis configuration.
     *
     * @param ktype The Kotlin type of the column
     * @return The configured Axis
     */
    private fun Mapping.toAxis(ktype: KType): Axis {
        this as PositionalMapping<*>
        val params = parameters as EchartsPositionalMappingParameters
        val axis = params.axis
        val axisScale = params.scale

        // Initialize min and max as null
        var min: String? = null
        var max: String? = null

        // Determine axis type and set min/max if applicable
        val type = when (axisScale) {
            is PositionalCategoricalScale<*> -> AxisType.CATEGORY
            is PositionalContinuousScale<*> -> {
                // Convert min/max values to string representation for the axis
                min = axisScale.min?.toString()
                max = axisScale.max?.toString()
                AxisType.VALUE
            }

            is PositionalDefaultScale -> typeMapping[ktype] ?: AxisType.VALUE
        }

        return Axis(
            name = axis.name,
            show = axis.show,
            type = type.value,
            min = min,
            max = max
        )
    }

    /**
     * Converts a Layer to a Series configuration.
     *
     * @return The configured Series
     */
    private fun Layer.toSeries(): Series {
        // Get x and y column IDs from layer mappings or global mappings
        val x = mappings[X]?.columnID ?: globalMappings[X]?.columnID
        val y = when {
            mappings[Y] != null -> {
                listOf(mappings[Y]!!.columnID)
            }

            globalMappings[Y] != null -> listOf(globalMappings[Y]!!.columnID)
            mappings[Y_OPEN] != null && mappings[Y_CLOSE] != null && mappings[Y_LOW] != null && mappings[Y_HIGH] != null -> listOf(
                mappings[Y_OPEN]!!.columnID,
                mappings[Y_CLOSE]!!.columnID,
                mappings[Y_LOW]!!.columnID,
                mappings[Y_HIGH]!!.columnID
            )

            else -> null
        }

        // Create encode object if x or y is not null
        val encode = Encode(x, y).takeIf { it.isNotEmpty() }

        // Get name from settings or create one from x and y column IDs
        val name = settings.getNPSValue(NAME) ?: buildString {
            x?.let { append(it) }
            if (x != null && y != null) append(" ")
            y?.let { append(it.joinToString(separator = " ", prefix = "", postfix = "")) }
        }.takeIf { it.isNotEmpty() }


        val data = if (mappings[PIE_DATA_NAME]?.columnID != null && mappings[PIE_DATA_VALUES]?.columnID != null)
            (datasets[0] as NamedData).dataFrame.map {
                mapOf(
                    "name" to Element.of(it[mappings[PIE_DATA_NAME]!!.columnID]),
                    "value" to Element.of(it[mappings[PIE_DATA_VALUES]!!.columnID])
                )
            }
        else null

        return getSeries(name, encode, data)
    }

    /**
     * Converts a Layer with grouped data to a list of Series configurations.
     *
     * @return List of configured Series
     */
    private fun Layer.toGroupedSeries(): List<Series> {
        val groupedData = datasets[datasetIndex] as GroupedData
        val x = mappings[X]?.columnID ?: globalMappings[X]?.columnID
        val y = when {
            mappings[Y] != null -> {
                listOf(mappings[Y]!!.columnID)
            }

            globalMappings[Y] != null -> listOf(globalMappings[Y]!!.columnID)
            mappings[Y_OPEN] != null && mappings[Y_CLOSE] != null && mappings[Y_LOW] != null && mappings[Y_HIGH] != null -> listOf(
                mappings[Y_OPEN]!!.columnID,
                mappings[Y_CLOSE]!!.columnID,
                mappings[Y_LOW]!!.columnID,
                mappings[Y_HIGH]!!.columnID
            )

            else -> null
        }

        // Ensure x and y are not null
        requireNotNull(x) { "X mapping is required for grouped series" }
        requireNotNull(y) { "Y mapping is required for grouped series" }

        val groupedSeries = mutableListOf<Series>()

        // TODO: Implement proper handling of grouped data
        // This is a simplified implementation that creates a single series for the grouped data
        // A more complete implementation would create a series for each group

        // Create a series with the original data
        val name = buildString {
            append("Grouped by ")
            append(groupedData.keys.joinToString(", "))
        }

        groupedSeries.add(getSeries(name, Encode(x, y), null))

        return groupedSeries
    }

    /**
     * Creates a Series configuration based on the layer's geometry type.
     *
     * @param name The name of the series
     * @param encode The encode configuration for the series
     * @param data The data for the series
     * @return The configured Series
     * @throws IllegalArgumentException if the geometry type is not supported
     */
    @Suppress("UNCHECKED_CAST")
    private fun Layer.getSeries(name: String?, encode: Encode?, data: List<Any?>? = null): Series =
        when (geom) {
            LINE -> toLineSeries(name, encode, data as List<List<Element?>>?)
            AREA -> toAreaSeries(name, encode, data as List<List<Element?>>?)
            BAR -> toBarSeries(name, encode, data as List<List<Element?>>?)
            PIE -> toPieSeries(name, encode, data as List<Map<String, Element?>>?)
            POINT -> toPointSeries(name, encode, data as List<List<Element?>>?)
            CANDLESTICK -> toCandlestickSeries(name, encode, data as List<List<Element?>>?)
            BOXPLOT -> toBoxplotSeries(name, encode, data as List<List<Element?>>?)
            else -> throw IllegalArgumentException("Unsupported geometry type: $geom")
        }
}
