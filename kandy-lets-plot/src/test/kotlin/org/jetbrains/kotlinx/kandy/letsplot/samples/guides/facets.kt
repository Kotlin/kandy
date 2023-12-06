package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.ParserOptions
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.head
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.invoke
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.*
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.theme.Theme
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import java.util.*
import kotlin.test.Test

class Facets : SampleHelper("multiplot", "guides") {

    private val data = DataFrame.readCSV(
        "https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg2.csv",
        parserOptions = ParserOptions(Locale.ENGLISH)
    )
    private val `engine horsepower` = column<Int>("engine horsepower")
    private val `miles per gallon` = column<Double>("miles per gallon")
    private val `origin of car` = column<String>("origin of car")
    private val `number of cylinders` = column<Int>("number of cylinders")

    @Test
    fun guideFacetsReadDataFrame() {
        // SampleStart
        val data = DataFrame.readCSV(
            "https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg2.csv",
            parserOptions = ParserOptions(Locale.ENGLISH)
        )

        data.head(3)
        // SampleEnd
    }

    @Test
    fun guideFacetsScatterPlotByHorsePower() {
        // SampleStart
        data.plot {
            x(`engine horsepower`)
            y(`miles per gallon`)
            points {
                color(`origin of car`)
            }
            layout {
                theme(Theme.Grey)
                size = 800 to 350
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideFacetsGridXPlot() {
        // SampleStart
        data.plot {
            points {
                x(`engine horsepower`)
                y(`miles per gallon`)
                color(`origin of car`)
            }
            layout {
                theme(Theme.Grey)
            }
            facetGridX(`number of cylinders`)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideFacetsTwoPlotsGrid() {
        // SampleStart
        data.plot {
            points {
                x(`engine horsepower`)
                y(`miles per gallon`)
                color(`origin of car`)
            }
            layout {
                theme(Theme.Grey)
            }
            facetGrid(`number of cylinders`, `origin of car`)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideFacetsWithFormattingAndSorting() {
        // SampleStart
        data.plot {
            points {
                x(`engine horsepower`)
                y(`miles per gallon`)
                color(`origin of car`)
            }
            layout {
                theme(Theme.Grey)
            }
            facetGrid(`number of cylinders`, `origin of car`, xFormat = "{d} cyl", yOrder = OrderDirection.DESCENDING)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideFacetsWrapOnePlot() {
        // SampleStart
        data.plot {
            points {
                x(`engine horsepower`)
                y(`miles per gallon`)
                color(`origin of car`)
            }
            layout {
                theme(Theme.Grey)
            }
            facetWrap(nRow = 2) {
                facet(`number of cylinders`)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideFacetsWrapTwoPlots() {
        // SampleStart
        data.plot {
            points {
                x(`engine horsepower`)
                y(`miles per gallon`)
                color(`origin of car`)
            }
            layout {
                theme(Theme.Grey)
            }
            facetWrap(nCol = 5) {
                facet(`origin of car`)
                facet(`number of cylinders`)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideFacetsArrangeVertically() {
        // SampleStart
        data.plot {
            points {
                x(`engine horsepower`)
                y(`miles per gallon`)
                color(`origin of car`)
            }
            layout {
                theme(Theme.Grey)
            }
            facetWrap(nCol = 3, direction = Direction.VERTICAL) {
                facet(`origin of car`, OrderDirection.ASCENDING, null)
                facet(`number of cylinders`, OrderDirection.DESCENDING, "{} cyl")
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideFreeFacetSimplePlot() {
        // SampleStart
        data.plot {
            x(`engine horsepower`)
            y("engine displacement (cu. inches)"<Double>())
            points {
                color(`origin of car`)
            }
            layout {
                theme(Theme.Grey)
                size = 800 to 350
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideFreeFacetWithFixedScales() {
        // SampleStart
        data.plot {
            x(`engine horsepower`)
            y("engine displacement (cu. inches)"<Double>())
            points {
                color(`origin of car`)
            }
            layout {
                theme(Theme.Grey)
                size = 800 to 500
            }
            facetGridY(`origin of car`)
        }
        // SampleEnd
            .saveSample()
    }

    @Test
    fun guideFreeFacetGridYFree() {
        // SampleStart
        data.plot {
            x(`engine horsepower`)
            y("engine displacement (cu. inches)"<Double>())
            points {
                color(`origin of car`)
            }
            layout {
                theme(Theme.Grey)
                size = 800 to 500
            }
            facetGridY(`origin of car`, scalesSharing = ScalesSharing.FREE_Y)
        }
        // SampleEnd
            .saveSample()
    }

    @Test
    fun guideFreeFacetWrapWithFixedScale() {
        // SampleStart
        data.plot {
            x(`engine horsepower`)
            y("engine displacement (cu. inches)"<Double>())
            points {
                color(`origin of car`)
            }
            layout {
                theme(Theme.Grey)
                size = 800 to 500
            }
            facetWrap {
                facet(`number of cylinders`, order = OrderDirection.ASCENDING)
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun guideFreeFacetWrapWithFreeScale() {
        // SampleStart
        data.plot {
            x(`engine horsepower`)
            y("engine displacement (cu. inches)"<Double>())
            points {
                color(`origin of car`)
            }
            layout {
                theme(Theme.Grey)
                size = 800 to 500
            }
            facetWrap(scalesSharing = ScalesSharing.FREE) {
                facet(`number of cylinders`, order = OrderDirection.ASCENDING)
            }
        }
            // SampleEnd
            .saveSample()
    }
}

