@file:Suppress("UNCHECKED_CAST")

package org.jetbrains.kotlinx.kandy.geo.samples.gallery

import org.geotools.referencing.CRS
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.geo.io.readGeoJson
import org.jetbrains.kotlinx.dataframe.geo.io.readShapefile
import org.jetbrains.kotlinx.dataframe.geo.jts.scaleAroundCenter
import org.jetbrains.kotlinx.dataframe.geo.jts.translate
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.withData
import org.jetbrains.kotlinx.kandy.letsplot.geo.layers.geoMap
import org.jetbrains.kotlinx.kandy.letsplot.geo.layers.geoPoints
import org.jetbrains.kotlinx.kandy.letsplot.geo.layers.geoPolygon
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.LegendType
import org.jetbrains.kotlinx.kandy.letsplot.style.LegendPosition
import org.jetbrains.kotlinx.kandy.letsplot.style.Style
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.tooltips
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.locationtech.jts.geom.Geometry
import kotlin.test.Test

class Geo : SampleHelper("geo") {

    @Test
    fun usa_simple_poly_json() {
        // SampleStart
        val usaStates =
            GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndrewKis/datasets/refs/heads/main/USA.json")

        usaStates.plot {
            geoPolygon()
        }
            // SampleEnd
            .savePlotSVGSample()
    }

    @Test
    fun germany_map_settings_shapefile() {
        // SampleStart
        val worldStates =
            GeoDataFrame.readShapefile("https://github.com/AndrewKis/datasets/raw/refs/heads/main/ne_10m_admin_1_states_provinces/ne_10m_admin_1_states_provinces.shp")

        val germanStates = worldStates.modify {
            filter { "admin"<String>() == "Germany" }//SampleEnd
                    as DataFrame<Nothing> // SampleStart
        }

        germanStates.plot {
            geoMap {
                fillColor = Color.hex("#E0E8D3")

                borderLine {
                    width = 0.35
                    color = Color.hex("#567A56")
                }
            }
            layout {
                title = "Germany States"
                layout.style(Style.Void)
            }
        }
            // SampleEnd
            .savePlotSVGSample()
    }

    @Test
    fun germany_map_categories() {
        // SampleStart
        val worldStates =
            GeoDataFrame.readShapefile("https://github.com/AndrewKis/datasets/raw/refs/heads/main/ne_10m_admin_1_states_provinces/ne_10m_admin_1_states_provinces.shp")

        val germanStates = worldStates.modify {
            filter { "admin"<String>() == "Germany" }//SampleEnd
                    as DataFrame<Nothing> // SampleStart
        }

        germanStates.plot {
            geoMap {
                fillColor("name") {
                    legend {
                        name = "State"
                        type = LegendType.DiscreteLegend(nCol = 2)
                    }
                }

                borderLine {
                    width = 0.5
                    color = Color.BLACK
                }
            }
            layout {
                title = "Germany States"
                size = 900 to 600
                layout.style(Style.Void)
            }
        }
            // SampleEnd
            .savePlotSVGSample(savePreview = true)
    }

    @Test
    fun usa_adjusted() {
        // SampleStart
        val usaStates =
            GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndrewKis/datasets/refs/heads/main/USA.json")
        val name by column<String>()
        val geometry by column<Geometry>()

        val usaAdjusted = usaStates.modify {
            update(geometry).where { name() == "Alaska" }.with {
                it.scaleAroundCenter(0.5).translate(40.0, -40.0)
            }
                // move Hawaii and Puerto Rico
                .update(geometry).where { name() == "Hawaii" }.with { it.translate(65.0, 0.0) }
                .update(geometry).where { name() == "Puerto Rico" }.with { it.translate(-10.0, 5.0) }// SampleEnd
                    as DataFrame<Nothing> // SampleStart
        }

        usaAdjusted.plot {
            geoMap()
        }
            // SampleEnd
            .savePlotSVGSample()
    }

    @Test
    fun usa_election_results_joined() {
        // SampleStart
        val usaStates =
            GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndrewKis/datasets/refs/heads/main/USA.json")
        val name by column<String>()
        val geometry by column<Geometry>()

        val usaAdjusted = usaStates.modify {
            update(geometry).where { name() == "Alaska" }.with {
                it.scaleAroundCenter(0.5).translate(40.0, -40.0)
            }
                // move Hawaii and Puerto Rico
                .update(geometry).where { name() == "Hawaii" }.with { it.translate(65.0, 0.0) }
                .update(geometry).where { name() == "Puerto Rico" }.with { it.translate(-10.0, 5.0) }// SampleEnd
                    as DataFrame<Nothing> // SampleStart
        }

        val usa2024electionResults =
            DataFrame.readCSV("https://raw.githubusercontent.com/AndrewKis/datasets/refs/heads/main/USA_2024_elections_results_by_state.csv")
        val winner by column<String>()

        val usaStatesWithElectionResults = usaAdjusted.modify {
            innerJoin(usa2024electionResults) { name }//SampleEnd
                    as DataFrame<Nothing> // SampleStart
        }

        usaStatesWithElectionResults.plot {
            geoMap {
                fillColor(winner) {
                    scale = categorical(
                        "Republican" to Color.hex("#CC3333"),
                        "Democrat" to Color.hex("#3366CC")
                    )
                }
                tooltips(name, winner)
            }
            layout {
                title = "USA 2024 President Election Results"
                size = 700 to 500
                style(Style.Void) {
                    legend.position = LegendPosition.Top
                }
            }
        }
            // SampleEnd
            .savePlotSVGSample(savePreview = true)
    }

    @Test
    fun usa_conus_albers() {
        // SampleStart
        val usaStates =
            GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndrewKis/datasets/refs/heads/main/USA.json")

        val usaAlbers = usaStates
            .modify {
                filter { "name"<String>() !in listOf("Alaska", "Hawaii", "Puerto Rico") }//SampleEnd
                        as DataFrame<Nothing>// SampleStart
            }
            .applyCrs(CRS.decode("EPSG:5070", true))

        usaAlbers.plot {
            geoPolygon()
            layout.title = "US With CONUS Albers Projection"
        }
            // SampleEnd
            .savePlotSVGSample()
    }

    @Test
    fun usa_with_cities() {
        // SampleStart
        val usaStates =
            GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndrewKis/datasets/refs/heads/main/USA.json")
        val name by column<String>()

        val usaCities =
            GeoDataFrame.readGeoJson("https://github.com/AndrewKis/datasets/raw/refs/heads/main/USA_cities.json")
        val pop_min by column<Int>()

        usaStates.modify {
            filter { name() !in listOf("Alaska", "Hawaii", "Puerto Rico") }//SampleEnd
                    as DataFrame<Nothing> // SampleStart
        }.plot {
            geoMap {
                fillColor = Color.hex("#E4F1FE")
                alpha = 0.7
                borderLine.color = Color.hex("#2A5D78")
            }
            withData(usaCities) {
                geoPoints {
                    color = Color.hex("#FF6F61")
                    alpha = 0.95
                    size(pop_min) {
                        scale = continuous(4.0..18.0)
                        legend.type = LegendType.None
                    }

                    tooltips(name, pop_min)
                }
            }

            layout {
                style(Style.Void)
            }
        }
            // SampleEnd
            .savePlotSVGSample()
    }
}
