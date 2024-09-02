package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.*
import org.jetbrains.kotlinx.kandy.ir.scale.Scale
import org.jetbrains.kotlinx.kandy.letsplot.feature.Position
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.feature.position
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.boxes
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.*
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.scales.*
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.LegendType
import org.jetbrains.kotlinx.kandy.letsplot.settings.Symbol
import org.jetbrains.kotlinx.kandy.letsplot.style.LayoutParameters
import org.jetbrains.kotlinx.kandy.letsplot.style.Style
import org.jetbrains.kotlinx.kandy.letsplot.style.Inset
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.Anchor
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.tooltips
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.value
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.jetbrains.kotlinx.statistics.binning.BinsAlign
import org.jetbrains.kotlinx.statistics.binning.BinsOption
import org.jetbrains.kotlinx.statistics.kandy.layers.histogram
import org.jetbrains.kotlinx.statistics.kandy.stattransform.statBin
import org.jetbrains.letsPlot.core.spec.back.transform.bistro.util.tooltips
import kotlin.test.Test
import kotlin.test.assertNotNull

class QuickStartGuide : SampleHelper("quickStartGuide", "guides") {

    // Initial Dataset
    private val simpleDataset = mapOf(
        "time, ms" to listOf(12, 87, 130, 149, 200, 221, 250),
        "relativeHumidity" to listOf(0.45, 0.3, 0.21, 0.15, 0.22, 0.36, 0.8),
        "flowOn" to listOf(true, true, false, false, true, false, false),
    )

    private val timeMs = column<Int>("time, ms")
    private val humidity = column<Double>("relativeHumidity")
    private val flowOn by column<Boolean>()

    // Month Data
    private val month = listOf(
        "January", "February",
        "March", "April", "May",
        "June", "July", "August",
        "September", "October", "November",
        "December"
    )
    private val numberOfDays = listOf(31, 28, 31, 30, 31, 30, 31, 30, 31, 30, 31, 30)
    private val season = listOf(
        "winter", "winter",
        "spring", "spring", "spring",
        "summer", "summer", "summer",
        "autumn", "autumn", "autumn",
        "winter"
    )

    // Auto Dataset
    private val mpgDF =
        DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")

    private val manufacturer = column<String>("manufacturer")
    private val model = column<String>("model")
    private val displ = column<Double>("displ")
    private val cyl = column<Int>("cyl")
    private val trans = column<String>("trans")
    private val drv = column<String>("drv")
    private val cty = column<Int>("cty")
    private val hwy = column<Int>("hwy")

    // Line Dataset for Grouping
    private val lineDataset = mapOf(
        "timeG" to listOf(1.0, 2.2, 3.4, 6.6, 2.1, 4.4, 6.0, 1.5, 4.7, 6.7),
        "value" to listOf(112.0, 147.3, 111.1, 200.6, 90.8, 110.2, 130.4, 100.1, 90.0, 121.8),
        "c-type" to listOf("A", "A", "A", "A", "B", "B", "B", "C", "C", "C")
    )

    private val lineDF = lineDataset.toDataFrame()

    private val `c-type` = column<String>("c-type")
    private val value = column<Double>("value")
    private val timeG = column<Double>("timeG")

    // Dataset for statistics
    private val random = kotlin.random.Random(42)

    private val observation = List(1000) { random.nextDouble() }
    private val observationDataset = dataFrameOf(
        "observations" to observation
    )
    private val obs = column<Double>("observations")

    @Test
    fun basicsDataSimpleDataset() {
        // SampleStart
        val simpleDataset = mapOf(
            "time, ms" to listOf(12, 87, 130, 149, 200, 221, 250),
            "relativeHumidity" to listOf(0.45, 0.3, 0.21, 0.15, 0.22, 0.36, 0.8),
            "flowOn" to listOf(true, true, false, false, true, false, false),
        )
        // SampleEnd

        assertNotNull(simpleDataset["time, ms"])
        assertNotNull(simpleDataset["relativeHumidity"])
        assertNotNull(simpleDataset["flowOn"])
    }

    @Test
    fun basicsDataDefineColumns() {
        // SampleStart
        // 1. Using the `column()` function with the specified column type and name:
        val timeMs = column<Int>("time, ms")
        // 2. Utilizing the String API, similar to the method above, but using String invocation:
        val humidity = "relativeHumidity"<Double>()
        // 3. Delegating an unnamed column - its name will be derived from the variable name:
        val flowOn by column<Boolean>()
        // SampleEnd

        assertNotNull(timeMs)
        assertNotNull(humidity)
        assertNotNull(flowOn)
    }

    @Test
    fun basicsPlotCreationHumidityData() {
        // SampleStart
        plot(simpleDataset) {
            points {
                // Maps values from the "time, ms" column to the X-axis
                x(timeMs)
                // Maps values from the "relativeHumidity" column to the Y-axis
                y(humidity)
                // Sets the size of the points to 4.5
                size = 4.5
                // Maps values from the "flowOn" column to the color attribute
                color(flowOn)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun basicsLayersCustomizedScales() {
        // SampleStart
        plot(simpleDataset) {
            points {
                x(listOf(12, 87, 130, 149, 200, 221, 250)) {
                    scale = continuous(min = 0, max = 270) // Using `min`/`max`
                }
                y(humidity) {
                    scale = continuous(0.0..1.0) // Using `Range`
                }
                size(humidity) {
                    scale = continuous(range = 5.0..20.0)
                }
                color(flowOn) {
                    scale = categorical(true to Color.RED, false to Color.BLUE)
                }
                symbol(flowOn) {
                    scale = categorical()// default scale
                }
                alpha = 0.9
            }
        }
            // SampleEnd
            .saveSample()
    }


    @Test
    fun basicsLayersDefineCustomScales() {
        // SampleStart
        val xReversedScale = Scale.continuousPos<Int>(transform = Transformation.REVERSE)
        val yScale = Scale.continuousPos(0.0..0.9)
        val colorScale = Scale.categorical<Color, Boolean>(
            range = listOf(Color.RED, Color.GREEN)
        )
        // SampleEnd

        assertNotNull(xReversedScale)
        assertNotNull(yScale)
        assertNotNull(colorScale)
    }

    @Test
    fun basicsLayersPlotWithCustomScales() {
        val xReversedScale = Scale.continuousPos<Int>(transform = Transformation.REVERSE)
        val yScale = Scale.continuousPos(0.0..0.9)
        val colorScale = Scale.categorical<Color, Boolean>(
            range = listOf(Color.RED, Color.GREEN)
        )
        // SampleStart
        plot(simpleDataset) {
            points {
                x(listOf(12, 87, 130, 149, 200, 221, 250)) {
                    scale = xReversedScale
                }
                y(humidity) {
                    scale = yScale
                }
                color(flowOn) {
                    scale = colorScale
                }
                symbol = Symbol.ASTERIX
                size = 6.6
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun basicsScaleParamTimeHumidityGraph() {
        // SampleStart
        // Creating a plot with customized axis and legend
        plot(simpleDataset) {
            points {
                // Configuring the x-axis for time
                x(timeMs) {
                    axis.name = "Time from start of counting,\n milliseconds"
                }
                // Configuring the y-axis for humidity
                y(humidity) {
                    scale = continuous(0.0..1.0) // Setting scale for humidity
                    axis {
                        name = "Relative humidity" // Axis label
                        breaksLabeled(0.0 to "0%", 0.3 to "30%", 0.6 to "60%", 0.9 to "90%") // Custom axis breaks
                    }
                }
                size = 12.0 // Set size of points
                // Configuring the legend for humidity
                color(humidity) {
                    scale = continuous()
                    legend {
                        name = "rel. humidity" // Legend label
                        type = LegendType.ColorBar(40.0, 190.0, 15) // Legend type and dimensions
                        breaks(format = "e") // Legend breaks format
                    }
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun basicsGlobalXYPlotLine() {
        // SampleStart
        // Using global mappings for X and Y
        plot(simpleDataset) {
            x(timeMs) { scale = continuous(max = 275) }
            y(humidity)
            points {
                // Inherits X and Y from the global context
                size = 4.5
                color(flowOn)
            }
            line {
                // Inherits X, overrides Y
                y(listOf(0.49, 0.39, 0.1, 0.4, 0.8, 0.8, 0.9))
                width = 3.0
                color = Color.RED
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun basicsGlobalXYConfigureAxisScales() {
        // SampleStart
        // Configuring axis scales independently
        plot(simpleDataset) {
            points {
                x(listOf(10, 20, 30, 40, 50, 60, 70))
                y(humidity)
                size = 4.5
                color(flowOn)
            }
            x.axis.name = "time, ms"
            y {
                scale = continuous(min = 0.0)
                axis.breaks(format = ".2f")
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun basicsGlobalXYConfigureAxisScalesWithAltApi() {
        // SampleStart
        plot(simpleDataset) {
            points {
                x(listOf(10, 20, 30, 40, 50, 60, 70))
                y(humidity)
                size = 4.5
                color(flowOn)
            }
            // Alternate brief notation if we want to set the axis limits (continuous scale)
            x.limits = 0..80
            y.limits = 0.0..1.0
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun basicsGlobalXYBoxplotWithFreeScale() {
        // SampleStart
        // Demonstrating free scale in a boxplot
        plot(
            mapOf(
                "x" to listOf("a", "b", "c"),
                // Boxplot sub-positional aesthetics
                "min" to listOf(0.8, 0.4, 0.6),
                "lower" to listOf(0.9, 1.4, 0.8),
                "middle" to listOf(1.5, 2.4, 1.6),
                "upper" to listOf(1.9, 3.4, 1.7),
                "max" to listOf(3.1, 4.4, 2.6),
                "width" to listOf(0.1, 1.4, 14.0)
            )
        ) {
            boxes {
                x("x"<String>())
                // Sub-y aesthetics with a free scale
                yMin("min"<Double>())
                lower("lower"<Double>())
                middle("middle"<Double>())
                upper("upper"<Double>())
                yMax("max"<Double>())
                fatten = 4.5
                alpha = 0.2
                borderLine.color = Color.hex(0x9A2A2A)
            }
            y {
                axis.name = "weight"
                limits = 0.0..5.0
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun basicsRawSourceInitializeMonthData() {
        // SampleStart
        // Sample data with months, number of days, and seasons
        val month = listOf(
            "January", "February",
            "March", "April", "May",
            "June", "July", "August",
            "September", "October", "November",
            "December"
        )
        val numberOfDays = listOf(31, 28, 31, 30, 31, 30, 31, 30, 31, 30, 31, 30)
        val season = listOf(
            "winter", "winter",
            "spring", "spring", "spring",
            "summer", "summer", "summer",
            "autumn", "autumn", "autumn",
            "winter"
        )
        // SampleEnd
        assertNotNull(month)
        assertNotNull(numberOfDays)
        assertNotNull(season)
    }

    @Test
    fun basicsRawSourceDaysPerMonthBarPlot() {
        // SampleStart
        // Plotting using raw source mapping
        plot {
            bars {
                // Mapping 'month' directly with a name
                x(month, "month") { scale = categorical() }
                // Mapping 'numberOfDays' directly
                y(numberOfDays, "number of days")
                // Mapping 'season' with color, named source, and categorical scale
                fillColor(season, "season") {
                    scale = categorical(
                        listOf(Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE),
                        listOf("winter", "spring", "summer", "autumn"),
                    )
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun kotlinDataframeApiPlotMpgInfo() {
        // SampleStart
        // Create a plot using the DataFrame
        val mpgInfoPlot = mpgDF.plot {
            points {
                x(displ) // Auto-generated DataFrame columns
                y(cty) {
                    scale = continuous(8..34)
                }
                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY
                alpha = 0.7
                fillColor(drv)
                size(hwy) {
                    scale = continuous(5.0..15.0)
                    legend.breaks(listOf(15, 30, 40), format = "d")
                }
            }
        }
        mpgInfoPlot
            // SampleEnd
            .saveSample()
    }

    @Test
    fun kotlinDataframeApiDrvCountPlot() {
        // SampleStart
        // Creating a plot with manual column pointers
        val mpgCountPlot = mpgDF.groupBy { drv }.aggregate {
            count() into "count"
        }.plot {
            bars {
                x(drv)
                y("count"<Int>())
                alpha = 0.75
            }
        }
        mpgCountPlot
            // SampleEnd
            .saveSample()
    }

    @Test
    fun groupingDatasetWithGrouping() {
        // SampleStart
        // Dataset with a grouping column
        val lineDataset = mapOf(
            "timeG" to listOf(1.0, 2.2, 3.4, 6.6, 2.1, 4.4, 6.0, 1.5, 4.7, 6.7),
            "value" to listOf(112.0, 147.3, 111.1, 200.6, 90.8, 110.2, 130.4, 100.1, 90.0, 121.8),
            "c-type" to listOf("A", "A", "A", "A", "B", "B", "B", "C", "C", "C")
        )
        // SampleEnd
        assertNotNull(lineDataset["timeG"])
    }

    @Test
    fun groupingSimpleGrouping() {
        // SampleStart
        // Using grouping in the plot
        plot(lineDataset) {
            groupBy("c-type") {
                line {
                    x("timeG")
                    y("value")
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun groupingMapToDataFrame() {
        // SampleStart
        // Convert the map to a DataFrame
        val lineDF = lineDataset.toDataFrame()
        // SampleEnd
        assertNotNull(lineDF["timeG"])
    }

    @Test
    fun groupingPlotOnDataFrame() {
        // SampleStart
        // Apply grouping on the DataFrame
        lineDF.groupBy {
            `c-type`
        }.plot {
            line {
                x(timeG)
                y(value)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun groupingWithColorMapping() {
        // SampleStart
        // Grouping with additional color mapping
        lineDF.plot {
            groupBy(`c-type`) {
                line {
                    x(timeG)
                    y(value)
                    color(`c-type`)
                    width = 4.0
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun implicitGroupingSimplePlot() {
        // SampleStart
        // Plotting with implicit grouping
        lineDF.plot {
            line {
                x(timeG)
                y(value)
                // Implicit grouping based on `c-type`
                color(`c-type`)
                width = 4.0
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun groupingPositionGroupedDataframeMean() {
        // SampleStart
        val meanCylDf = mpgDF.groupBy { cyl and drv }.mean { cty }
        // Display the first five rows of the grouped DataFrame
        meanCylDf.head(5)
        // SampleEnd
    }

    @Test
    fun groupingPositionDodgePlot() {
        val meanCylDf = mpgDF.groupBy { cyl and drv }.mean { cty }
        // SampleStart
        val meanCylPlot = meanCylDf.groupBy { cyl }.plot {
            bars {
                x(drv)
                y(cty)
                fillColor(cyl) { legend.breaks(format = "d") }

                position = Position.dodge()
            }
        }
        meanCylPlot
            // SampleEnd
            .saveSample()
    }

    @Test
    fun groupingPositionIdentityPlot() {
        val meanCylDf = mpgDF.groupBy { cyl and drv }.mean { cty }
        // SampleStart
        meanCylDf.groupBy { cyl }.plot {
            bars {
                x(drv)
                y(cty)
                fillColor(cyl) { legend.breaks(format = "d") }
                alpha = 0.4

                position = Position.identity()
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun groupingPositionStackPlot() {
        val meanCylDf = mpgDF.groupBy { cyl and drv }.mean { cty }
        // SampleStart
        meanCylDf.groupBy { cyl }.plot {
            bars {
                x(drv)
                y(cty)
                fillColor(cyl) { legend.breaks(format = "d") }

                position = Position.stack()
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalMultiplotPlotGrid() {
        val mpgInfoPlot = mpgDF.plot {
            points {
                x(displ) // Auto-generated DataFrame columns
                y(cty) {
                    scale = continuous(8..34)
                }
                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY
                alpha = 0.7
                fillColor(drv)
                size(hwy) {
                    scale = continuous(5.0..15.0)
                    legend.breaks(listOf(15, 30, 40), format = "d")
                }
            }
        }
        val mpgCountPlot = mpgDF.groupBy { drv }.count().plot {
            bars { x(drv); y("count"<Int>()); alpha = 0.75 }
        }
        val meanCylDf = mpgDF.groupBy { cyl and drv }.mean { cty }
        val meanCylPlot = meanCylDf.groupBy { cyl }.plot {
            bars {
                x(drv); y(cty); fillColor(cyl) { legend.breaks(format = "d") }; position = Position.dodge()
            }
        }
        // SampleStart
        plotGrid(listOf(mpgInfoPlot, mpgCountPlot, meanCylPlot), nCol = 2)
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun experimentalMultiplotPlotBunch() {
        val mpgInfoPlot = mpgDF.plot {
            points {
                x(displ) // Auto-generated DataFrame columns
                y(cty) {
                    scale = continuous(8..34)
                }
                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY
                alpha = 0.7
                fillColor(drv)
                size(hwy) {
                    scale = continuous(5.0..15.0)
                    legend.breaks(listOf(15, 30, 40), format = "d")
                }
            }
        }
        val mpgCountPlot = mpgDF.groupBy { drv }.count().plot {
            bars { x(drv); y("count"<Int>()); alpha = 0.75 }
        }
        val meanCylDf = mpgDF.groupBy { cyl and drv }.mean { cty }
        val meanCylPlot = meanCylDf.groupBy { cyl }.plot {
            bars {
                x(drv); y(cty); fillColor(cyl) { legend.breaks(format = "d") }; position = Position.dodge()
            }
        }
        // SampleStart
        plotBunch {
            add(mpgCountPlot, 0, 0, 400, 200)
            add(meanCylPlot, 400, 0, 300, 200)
            add(mpgInfoPlot, 0, 200, 700, 300)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalMultiplotFacetingGridX() {
        // SampleStart
        mpgDF.plot {
            points {
                x(displ)
                y(cty) {
                    scale = continuous(8..34)
                }
                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY
                alpha = 0.7
                fillColor(drv)
                size(hwy) {
                    scale = continuous(2.0..10.0)
                }
            }

            layout.size = 750 to 450

            facetGridX(drv, scalesSharing = ScalesSharing.FREE_X)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalMultiplotFacetingGridY() {
        // SampleStart
        mpgDF.plot {
            points {
                x(displ)
                y(cty) {
                    scale = continuous(8..34)
                }
                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY
                alpha = 0.7
                fillColor(drv)
                size(hwy) {
                    scale = continuous(2.0..10.0)
                }
            }

            layout.size = 750 to 450

            facetGridY(cyl, format = "d")
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalMultiplotFacetingGrid() {
        // SampleStart
        mpgDF.plot {
            points {
                x(displ)
                y(cty) {
                    scale = continuous(8..34)
                }

                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY
                fillColor(drv)
                size(hwy) {
                    scale = continuous(2.0..10.0)
                }
            }

            layout.size = 750 to 450

            facetGrid(drv, cyl, yFormat = "d")
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalMultiplotFacetingWrap() {
        // SampleStart
        mpgDF.plot {
            points {
                x(displ)
                y(cty) {
                    scale = continuous(8..34)
                }

                symbol = Symbol.CIRCLE_FILLED
                color = Color.GREY
                fillColor(drv)
                size(hwy) {
                    scale = continuous(2.0..10.0)
                }
            }

            layout.size = 750 to 450

            facetWrap(nCol = 3, scalesSharing = ScalesSharing.FREE) {
                facet(drv)
                facet(cyl, order = OrderDirection.DESCENDING, format = "d")
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalStatisticsObservationsDataset() {
        // SampleStart
        val random = kotlin.random.Random(42)

        val observation = List(1000) { random.nextDouble() }
        val observationDataset = dataFrameOf(
            "observations" to observation
        )
        // SampleEnd
        assertNotNull(observationDataset["observations"])
    }

    @Test
    fun experimentalStatisticsBinPlotWithBarsAndLines() {
        // SampleStart
        plot(observationDataset) {
            statBin(obs) {
                bars {
                    // Simple mapping
                    x(Stat.x)
                    // Mapping with the scale
                    y(Stat.count) {
                        scale = continuous(0..100, transform = Transformation.REVERSE)
                    }

                    alpha = 0.5

                    // Formatting of stat value format
                    tooltips {
                        // Line with the name of stat (i.e. "count") on the left side and its value on the right side
                        line(Stat.count, format = "d")
                    }
                }

                line {
                    x(Stat.x)
                    y(Stat.count)

                    width = 2.5
                    color = Color.RED
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalStatisticsShortcutHistogram() {
        // SampleStart
        val histPlot = plot(observationDataset) {
            histogram(obs)

            layout.title = "`histogram`"
        }
        histPlot
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalStatisticsStatBinsAndHist() {
        val histPlot = plot(observationDataset) { histogram(obs); layout.title = "`histogram`" }
        // SampleStart
        val binBarPlot = plot(observationDataset) {
            statBin(obs) {
                bars {
                    x(Stat.x)
                    y(Stat.count)
                }
            }
            layout.title = "`statBin` + `bar`"
        }

        plotGrid(listOf(histPlot, binBarPlot), 2)
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun experimentalStatisticsConfiguredHistogram() {
        // SampleStart
        plot(observationDataset) {
            histogram(obs, binsOption = BinsOption.byWidth(0.05), binsAlign = BinsAlign.boundary(0.1)) {
                y(Stat.density)

                fillColor(Stat.count) {
                    scale = continuous(Color.GREEN..Color.RED)
                }

                borderLine {
                    color = Color.BLACK
                    width = 0.3
                }

                tooltips(title = "${value(Stat.x)} ± 0.025") {
                    line(Stat.density)
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalStatisticsStatBinOnIterableData() {
        // SampleStart
        plotGrid(listOf(
            plot {
                statBin(observation) {
                    points {
                        x(Stat.density)
                        y(Stat.x)
                    }
                }
            },
            plot {
                histogram(observation)
            }
        ), 2)
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun experimentalLayoutTitleSubtitleCapSize() {
        // SampleStart
        mpgDF.plot {
            points {
                x(cty)
                y(hwy)
            }

            // Can access of layout parameters in this context
            layout {
                subtitle = "plot subtitle"
                caption = "plot caption \n important info"
                size = 800 to 600
            }
            // If you just want to put a title
            layout.title = "PLOT TITLE"
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalLayoutWithClassicStyle() {
        // SampleStart
        mpgDF.plot {
            points {
                x(cty)
                y(hwy)
            }
            layout.style(Style.Classic)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalLayoutFunPlotWithStyle() {
        // SampleStart
        fun plotWithStyle(style: Style? = null, title: String? = null): org.jetbrains.kotlinx.kandy.ir.Plot {
            return mpgDF.plot {
                points {
                    x(cty)
                    y(hwy)
                }
                layout {
                    style?.let {
                        style(it)
                    }
                    this.title = title
                }
            }
        }
        // SampleEnd
        assertNotNull(plotWithStyle(Style.Classic, "Test style"))
    }

    private fun plotWithStyle(style: Style? = null, title: String? = null): org.jetbrains.kotlinx.kandy.ir.Plot {
        return mpgDF.plot {
            points {
                x(cty)
                y(hwy)
            }
            layout {
                style?.let {
                    style(it)
                }
                this.title = title
            }
        }
    }

    @Test
    fun experimentalLayoutPlotAllStyles() {
        // SampleStart
        plotGrid(
            listOf(
                plotWithStyle(Style.Classic, "\"Classic\" style"),
                plotWithStyle(Style.Grey, "\"Grey\" style"),
                plotWithStyle(Style.Light, "\"Light\" style"),
                plotWithStyle(Style.Minimal, "\"Minimal\" style"),
                plotWithStyle(Style.Minimal2, "\"Minimal2\" style (by default)"),
                plotWithStyle(Style.None, "\"None\" style"),
            ), 2
        )
            // SampleEnd
            .saveSample(scaling = false)
    }

    @Test
    fun experimentalLayoutSimpleCustomStyle() {
        // SampleStart
        val redLine = LayoutParameters.line(Color.RED)

        val simpleCustomStyle = Style.createCustom {
            // use previously created parameters
            xAxis.line(redLine)
            // set up parameters
            yAxis.line {
                color = Color.RED
                width = 0.3
            }
            // remove ticks on both axes
            axis.ticks {
                blank = true
            }
        }

        plotWithStyle(simpleCustomStyle)
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalLayoutSimpleCustomStyleWithInset(){
        val redLine = LayoutParameters.line(Color.RED)
        val simpleCustomStyle = Style.createCustom {

            xAxis.line(redLine)

            yAxis.line {
                color = Color.RED
                width = 0.3
            }

            axis.ticks {
                blank = true
            }

            plotCanvas.inset(50.0)
        }
        plotWithStyle(simpleCustomStyle).saveSample()
    }

    @Test
    fun experimentalLayoutCustomStyleBlankAxes() {
        // SampleStart
        val blankAxesStyle = Style.createCustom {
            blankAxes()
        }
        plotWithStyle(blankAxesStyle)
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalCustomScalesCategoricalColor() {
        // SampleStart
        mpgDF.plot {
            points {
                x(cty)
                y(hwy)
                size = 5.0
                color(drv) {
                    scale = categoricalColorHue()
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalCustomScalesContinuousColorGrey() {
        // SampleStart
        mpgDF.plot {
            points {
                x(cty)
                y(hwy)
                size = 5.0
                color(cty) {
                    scale = continuousColorGrey()
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalCustomScalesContinuousColorGradient2() {
        // SampleStart
        mpgDF.plot {
            points {
                x(cty)
                y(hwy)
                size = 5.0
                color(hwy) {
                    scale = continuousColorGradient2(Color.BLUE, Color.ORANGE, Color.RED, 30.0)
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalCustomScalesContinuousColorGradientN() {
        // SampleStart
        mpgDF.plot {
            points {
                x(cty)
                y(hwy)
                size = 5.0
                color(cty) {
                    scale = continuousColorGradientN(
                        gradientColors = listOf(
                            Color.RED, Color.hex("#fa89c7"),
                            Color.rgb(200, 89, 12), Color.LIGHT_GREEN
                        )
                    )
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalCustomScalesCategoricalColorBrewer() {
        // SampleStart
        mpgDF.plot {
            points {
                x(cty)
                y(hwy)
                size = 5.0
                color(drv) {
                    scale = categoricalColorBrewer(BrewerPalette.Qualitative.Set1)
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalTooltipsHideTooltips() {
        // SampleStart
        mpgDF.plot {
            points {
                x(cty)
                y(hwy)
                color(drv)
                size = 3.5

                tooltips(enable = false)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalTooltipsByColumns() {
        // SampleStart
        mpgDF.plot {
            points {
                x(cty)
                y(hwy)
                color(drv)
                size = 3.5

                tooltips(drv, cyl, displ, formats = mapOf(displ to ".1g"))
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalTooltipsWithTitleAnchorMinWidth() {
        // SampleStart
        mpgDF.plot {
            points {
                x(cty)
                y(hwy)
                color(drv)
                size = 3.5

                tooltips(
                    cyl, displ,
                    title = "Car info",
                    anchor = Anchor.TOP_RIGHT,
                    minWidth = 80.0,
                )
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalTooltipsCustomizedWithLine() {
        // SampleStart
        mpgDF.plot {
            points {
                x(cty)
                y(hwy)
                color(drv)
                size = 3.5

                tooltips(
                    // use column values in the title
                    title = "${value(manufacturer)} ${value(model)}",
                ) {
                    // standard line with column name and value
                    line(displ)
                    // solid line
                    line(trans.tooltipValue())
                    // two-sided line
                    line("cty/hwy [mpg]", "${cty.tooltipValue()}/${hwy.tooltipValue()}")
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun experimentalExportPlotForExport() {
        // SampleStart
        val plotForExport = mpgDF.plot {
            points {
                x(cty)
                y(hwy)
                color(drv)
                size = 3.5
            }
            layout {
                title = "Plot for export"
                size = 600 to 400
            }
        }
        plotForExport
            // SampleEnd
            .saveSample()
    }
}