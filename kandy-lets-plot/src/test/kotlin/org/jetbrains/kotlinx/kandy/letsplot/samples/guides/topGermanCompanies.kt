package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import org.jetbrains.kotlinx.dataframe.ColumnsContainer
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.DataRow
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.read
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.layers.lineRanges
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.Ignore
import kotlin.test.Test

@Suppress("UNUSED_VARIABLE", "UNCHECKED_CAST")
class TopGermanCompanies : SampleHelper("top_12_german_companies", "guides") {
    private enum class BusinessSector(val simpleName: String) {
        AUTOMOTIVE("Automotive"),
        BANKING("Banking"),
        INDUSTRIAL_TECH("Industrial"),
        INSURANCE_FINANCE("Insurance"),
        TELECOMMUNICATIONS("Telecom"),
        IT_SOFTWARE("IT"),
        PHARMA_CHEMICAL("Pharma"),
        OTHER("Other")
    }

    private val format =
        LocalDate.Format {
            monthNumber(Padding.NONE)
            char('/')
            dayOfMonth()
            char('/')
            year()
        }

    interface DataFrameSchema

    val ColumnsContainer<DataFrameSchema>.period
        get() = get("period") as DataColumn<String>
    val ColumnsContainer<DataFrameSchema>.percentageDebtToEquity
        get() = get("percentageDebtToEquity") as DataColumn<String>
    val ColumnsContainer<DataFrameSchema>.ROA
        get() = get("ROA") as DataColumn<String>
    val ColumnsContainer<DataFrameSchema>.ROE
        get() = get("ROE") as DataColumn<String>
    val DataRow<DataFrameSchema>.company
        get() = get("company") as String


    interface CompaniesDfSchema

    private val company by column<String>()
    private val sector by column<BusinessSector>()

    @get:JvmName("period2")
    val ColumnsContainer<CompaniesDfSchema>.period
        get() = get("period") as DataColumn<LocalDate>
    val ColumnsContainer<CompaniesDfSchema>.revenue
        get() = get("revenue") as DataColumn<Double>
    val ColumnsContainer<CompaniesDfSchema>.netIncome
        get() = get("netIncome") as DataColumn<Double>

    @get:JvmName("ROA2")
    val ColumnsContainer<CompaniesDfSchema>.ROA
        get() = get("ROA") as DataColumn<Double>

    @get:JvmName("ROE2")
    val ColumnsContainer<CompaniesDfSchema>.ROE
        get() = get("ROE") as DataColumn<Double>
    val ColumnsContainer<CompaniesDfSchema>.liabilities
        get() = get("liabilities") as DataColumn<Double>
    val ColumnsContainer<CompaniesDfSchema>.assets
        get() = get("assets") as DataColumn<Double>
    val ColumnsContainer<CompaniesDfSchema>.equity
        get() = get("equity") as DataColumn<Double>

    @get:JvmName("percentageDebtToEquity2")
    val ColumnsContainer<CompaniesDfSchema>.percentageDebtToEquity
        get() = get("percentageDebtToEquity") as DataColumn<Double>

    val totalRevenue by column<Double>()
    val totalNetIncome by column<Double>()

    interface RoeAndRoaDfSchema

    val ColumnsContainer<RoeAndRoaDfSchema>.`Avg ROA`
        get() = get("Avg ROA") as DataColumn<Double>
    val ColumnsContainer<RoeAndRoaDfSchema>.`Std ROA`
        get() = get("Std ROA") as DataColumn<Double>
    val ColumnsContainer<RoeAndRoaDfSchema>.`Avg ROE`
        get() = get("Avg ROE") as DataColumn<Double>
    val ColumnsContainer<RoeAndRoaDfSchema>.`Std ROE`
        get() = get("Std ROE") as DataColumn<Double>

    val dataset = this.javaClass.classLoader.getResource("top_12_german_companies.csv")
    private val dataFrame =
        DataFrame.read(dataset)
            .renameToCamelCase().rename("rOA(%)", "rOE(%)").into("ROA", "ROE") as DataFrame<DataFrameSchema>


    private val companiesDf =
        dataFrame
            .convert { period }.with { LocalDate.parse(it, format) }
            .convert { percentageDebtToEquity }.with { it.removeSuffix("%").replace(',', '.').toDouble() }
            .convert { ROA and ROE }.with { it.replace(".", "").toDouble() }
            .sortBy { company and period }
            .add("sector") {
                when (company) {
                    "Volkswagen AG", "BMW AG", "Daimler AG", "Porsche AG" -> BusinessSector.AUTOMOTIVE
                    "Siemens AG", "BASF SE" -> BusinessSector.INDUSTRIAL_TECH
                    "Allianz SE" -> BusinessSector.INSURANCE_FINANCE
                    "Deutsche Bank AG" -> BusinessSector.BANKING
                    "Deutsche Telekom AG" -> BusinessSector.TELECOMMUNICATIONS
                    "SAP SE" -> BusinessSector.IT_SOFTWARE
                    "Bayer AG", "Merck KGaA" -> BusinessSector.PHARMA_CHEMICAL
                    else -> BusinessSector.OTHER
                }
            } as DataFrame<CompaniesDfSchema>
    private val timeSerDf =
        companiesDf.groupBy { period and sector }.aggregate {
            revenue.sum() into "totalRevenue"
            netIncome.sum() into "totalNetIncome"
        }
    private val listOfSectors =
        listOf(
            BusinessSector.AUTOMOTIVE,
            BusinessSector.BANKING,
            BusinessSector.INSURANCE_FINANCE,
            BusinessSector.INDUSTRIAL_TECH,
            BusinessSector.TELECOMMUNICATIONS,
            BusinessSector.IT_SOFTWARE,
            BusinessSector.PHARMA_CHEMICAL
        )
    private val listOfSectorColors =
        listOf(
            Color.hex("#ffaf00"),
            Color.hex("#f46920"),
            Color.hex("#f53255"),
            Color.hex("#f857c1"),
            Color.hex("#29bdfd"),
            Color.hex("#00cbbf"),
            Color.hex("#01c159")
        )
    private val roeAndRoaDf =
        companiesDf.groupBy { sector }.aggregate {
            ROA.mean() into "Avg ROA"
            ROA.std() into "Std ROA"
            ROE.mean() into "Avg ROE"
            ROE.std() into "Std ROE"
        } as DataFrame<RoeAndRoaDfSchema>


    @Ignore
    @Test
    fun notebook_test_top_12_german_companies_2() {
        // SampleStart
        // Read data from a CSV file into a DataFrame
        val dataFrame = DataFrame.read("top_12_german_companies.csv")
            .renameToCamelCase().rename("rOA(%)", "rOE(%)").into("ROA", "ROE")
        // SampleEnd
    }

    @Test
    fun notebook_test_top_12_german_companies_3() {
        // SampleStart
        dataFrame.head()
            // SampleEnd
            .saveDfHtmlSample()
    }

    @Test
    fun notebook_test_top_12_german_companies_4() {
        // SampleStart
        /*
        import kotlinx.datetime.format.Padding
        import kotlinx.datetime.format.char
         */

        // Define a custom date format without zero-padding for the month,
        // separating month/day/year with slashes
        val format = LocalDate.Format {
            monthNumber(Padding.NONE)
            char('/')
            dayOfMonth()
            char('/')
            year()
        }

        /*
        // Enum of Business Sectors
        enum class BusinessSector(val simpleName: String) {
            AUTOMOTIVE("Automotive"),
            BANKING("Banking"),
            INDUSTRIAL_TECH("Industrial"),
            INSURANCE_FINANCE("Insurance"),
            TELECOMMUNICATIONS("Telecom"),
            IT_SOFTWARE("IT"),
            PHARMA_CHEMICAL("Pharma"),
            OTHER("Other")
        }

         */

        // Create a new DataFrame by converting the "period" column to LocalDate using the custom format
        // and converting "percentageDebtToEquity" column to Double,
        // then sorting based on "company" and "period", and finally adding a "sector" column
        // depending on the company name
        val companiesDf = dataFrame
            .convert { period }.with { LocalDate.parse(it, format) }
            .convert { percentageDebtToEquity }.with { it.removeSuffix("%").replace(',', '.').toDouble() }
            .convert { ROA and ROE }.with { it.replace(".", "").toDouble() }
            .sortBy { company and period }
            .add("sector") {
                when (company) {
                    "Volkswagen AG", "BMW AG", "Daimler AG", "Porsche AG" -> BusinessSector.AUTOMOTIVE
                    "Siemens AG", "BASF SE" -> BusinessSector.INDUSTRIAL_TECH
                    "Allianz SE" -> BusinessSector.INSURANCE_FINANCE
                    "Deutsche Bank AG" -> BusinessSector.BANKING
                    "Deutsche Telekom AG" -> BusinessSector.TELECOMMUNICATIONS
                    "SAP SE" -> BusinessSector.IT_SOFTWARE
                    "Bayer AG", "Merck KGaA" -> BusinessSector.PHARMA_CHEMICAL
                    else -> BusinessSector.OTHER
                }
            }
        // SampleEnd
    }

    @Test
    fun notebook_test_top_12_german_companies_5() {
        // SampleStart
        companiesDf.groupBy { company }.aggregate {
            val financeColumns =
                it.select { revenue and netIncome and liabilities and assets and equity and ROA and ROE and percentageDebtToEquity }
            financeColumns.mean() into "mean"
            financeColumns.median() into "median"
            financeColumns.std() into "std"
            financeColumns.min() into "min"
            financeColumns.max() into "max"
        }
            // SampleEnd
            .saveDfHtmlSample()
    }

    @Test
    fun notebook_test_top_12_german_companies_6() {
        // SampleStart
        // Group by "company" and aggregate key financial columns
        companiesDf.groupBy { sector }.aggregate {
            val financeColumns =
                it.select { revenue and netIncome and liabilities and assets and equity and ROA and ROE and percentageDebtToEquity }
            financeColumns.mean() into "mean"
            financeColumns.median() into "median"
            financeColumns.std() into "std"
            financeColumns.min() into "min"
            financeColumns.max() into "max"
        }
            // SampleEnd
            .saveDfHtmlSample()
    }

    @Test
    fun notebook_test_top_12_german_companies_7() {
        // SampleStart
        companiesDf.groupBy { sector }.aggregate {
            revenue.mean() into "Avg revenue"
            revenue.sum() into "Total revenue"
            netIncome.mean() into "Avg Net Income"
            netIncome.sum() into "Sum Net Income"
            ROA.mean() into "Avg ROA"
            ROE.mean() into "Avg ROE"
        }.sortBy { sector }
            // SampleEnd
            .saveDfHtmlSample()
    }

    @Test
    fun notebook_test_top_12_german_companies_8() {
        // SampleStart
        // Group by "period" and "sector" then compute total revenue and net income
        val timeSerDf = companiesDf.groupBy { period and sector }.aggregate {
            revenue.sum() into "totalRevenue"
            netIncome.sum() into "totalNetIncome"
        }

        // List of business sectors
        val listOfSectors = listOf(
            BusinessSector.AUTOMOTIVE,
            BusinessSector.BANKING,
            BusinessSector.INSURANCE_FINANCE,
            BusinessSector.INDUSTRIAL_TECH,
            BusinessSector.TELECOMMUNICATIONS,
            BusinessSector.IT_SOFTWARE,
            BusinessSector.PHARMA_CHEMICAL
        )

        // Matching colors for each sector
        val listOfSectorColors = listOf(
            Color.hex("#ffaf00"),
            Color.hex("#f46920"),
            Color.hex("#f53255"),
            Color.hex("#f857c1"),
            Color.hex("#29bdfd"),
            Color.hex("#00cbbf"),
            Color.hex("#01c159")
        )
        // SampleEnd
    }

    @Test
    fun notebook_test_top_12_german_companies_9() {
        // SampleStart
        // Plot total revenue by period and sector
        timeSerDf.plot {
            // Map the x-axis to the "period" column
            x(period) { axis.name = "Date" }
            // Map the y-axis to the aggregated "totalRevenue"
            y(totalRevenue) { axis.name = "Revenue" }

            // Draw a line chart
            line {
                // Color lines by the "sector" column
                color(sector) {
                    // Use a categorical color scale with predefined colors and sectors
                    scale = categorical(range = listOfSectorColors, domain = listOfSectors)
                    // Configure and label the legend
                    legend {
                        name = "Sector"
                        this.breaksLabeled(
                            BusinessSector.AUTOMOTIVE to BusinessSector.AUTOMOTIVE.simpleName,
                            BusinessSector.BANKING to BusinessSector.BANKING.simpleName,
                            BusinessSector.INSURANCE_FINANCE to BusinessSector.INSURANCE_FINANCE.simpleName,
                            BusinessSector.INDUSTRIAL_TECH to BusinessSector.INDUSTRIAL_TECH.simpleName,
                            BusinessSector.TELECOMMUNICATIONS to BusinessSector.TELECOMMUNICATIONS.simpleName,
                            BusinessSector.IT_SOFTWARE to BusinessSector.IT_SOFTWARE.simpleName,
                            BusinessSector.PHARMA_CHEMICAL to BusinessSector.PHARMA_CHEMICAL.simpleName
                        )
                    }
                }
            }
            // Add points on top of the line chart
            points {
                size = 3.0
                color(sector) { scale = categorical(range = listOfSectorColors, domain = listOfSectors) }
            }

            // Adjust the layout and overall plot appearance
            layout {
                title = "Revenue by Sector"
                size = 875 to 500
            }
        }
            // SampleEnd
            .savePlotSVGSample()
    }

    @Test
    fun notebook_test_top_12_german_companies_10() {
        // SampleStart
        // Plot total net income by period and sector
        timeSerDf.plot {
            // Map the x-axis to the "period" column
            x(period) { axis.name = "Date" }
            // Map the y-axis to the aggregated "totalNetIncome"
            y(totalNetIncome) { axis.name = "Net Income" }

            // Draw a line chart
            line {
                // Color lines by the "sector" column
                color(sector) {
                    // Use the same categorical color scale and sector list
                    scale = categorical(range = listOfSectorColors, domain = listOfSectors)
                    // Configure and label the legend
                    legend {
                        name = "Sector"
                        this.breaksLabeled(
                            BusinessSector.AUTOMOTIVE to BusinessSector.AUTOMOTIVE.simpleName,
                            BusinessSector.BANKING to BusinessSector.BANKING.simpleName,
                            BusinessSector.INSURANCE_FINANCE to BusinessSector.INSURANCE_FINANCE.simpleName,
                            BusinessSector.INDUSTRIAL_TECH to BusinessSector.INDUSTRIAL_TECH.simpleName,
                            BusinessSector.TELECOMMUNICATIONS to BusinessSector.TELECOMMUNICATIONS.simpleName,
                            BusinessSector.IT_SOFTWARE to BusinessSector.IT_SOFTWARE.simpleName,
                            BusinessSector.PHARMA_CHEMICAL to BusinessSector.PHARMA_CHEMICAL.simpleName
                        )
                    }
                }

            }

            // Add points on top of the line chart
            points {
                size = 3.0
                color(sector) { scale = categorical(range = listOfSectorColors, domain = listOfSectors) }
            }

            // Adjust the layout and overall plot appearance
            layout {
                title = "Net Income by Sector"
                size = 875 to 500
            }
        }
            // SampleEnd
            .savePlotSVGSample()
    }

    @Test
    fun notebook_test_top_12_german_companies_11() {
        // SampleStart
        // Group data by sector to compute average and standard deviations of ROA and ROE
        val roeAndRoaDf = companiesDf.groupBy { sector }.aggregate {
            ROA.mean() into "Avg ROA"
            ROA.std() into "Std ROA"
            ROE.mean() into "Avg ROE"
            ROE.std() into "Std ROE"
        }

        roeAndRoaDf
            // SampleEnd
            .saveDfHtmlSample()
    }

    @Test
    fun notebook_test_top_12_german_companies_12() {
        // SampleStart
        // Plot average ROA by sector with error bars representing one standard deviation
        roeAndRoaDf.plot {
            // Set the x-axis to the sector names
            x(sector.map { it.simpleName }) { axis.name = "Sector of Business" }

            bars {
                // Use the "Avg ROA" column for the bar heights
                y(`Avg ROA`) { scale = continuous(min = .0, max = 4.5e+9) }
                // Fill bars with a chosen color
                fillColor = Color.hex("#ffaf00")
            }
            lineRanges {
                // Calculate the min and max for the error bars (Std ROA)
                yMin(`Avg ROA`.toList().zip(`Std ROA`.toList()).map { it.first - it.second })
                yMax(`Avg ROA`.toList().zip(`Std ROA`.toList()).map { it.first + it.second })
                // Color the line of the ranges
                borderLine.color = Color.GREY
            }

            // Adjust layout options such as title and overall size
            layout {
                title = "Average ROA By Sector With Standard Deviation"
                size = 875 to 500
            }
        }
            // SampleEnd
            .savePlotSVGSample()
    }

    @Test
    fun notebook_test_top_12_german_companies_13() {
        // SampleStart
        // Plot average ROE by sector with error bars representing one standard deviation
        roeAndRoaDf.plot {
            // Set the x-axis to the sector names
            x(sector.map { it.simpleName }) { axis.name = "Sector of Business" }

            bars {
                // Use the "Avg ROE" column for the bar heights
                y(`Avg ROE`)
                // Fill bars with a chosen color
                fillColor = Color.hex("#ffaf00")
            }
            lineRanges {
                // Calculate the min and max for the error bars (Std ROE)
                yMin(`Avg ROE`.toList().zip(`Std ROE`.toList()).map { it.first - it.second })
                yMax(`Avg ROE`.toList().zip(`Std ROE`.toList()).map { it.first + it.second })
                // Color the line of the ranges
                borderLine.color = Color.GREY
            }

            // Adjust layout options such as title and overall size
            layout {
                title = "Average ROE By Sector With Standard Deviation"
                size = 875 to 500
            }
        }
            // SampleEnd
            .savePlotSVGSample()
    }
}
