@file:Suppress("UNCHECKED_CAST", "UNUSED_EXPRESSION", "UNUSED_VARIABLE")

package org.jetbrains.kotlinx.kandy.geo.samples.guides

import org.geotools.referencing.CRS
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.DataRow
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.geo.GeoDataFrame
import org.jetbrains.kotlinx.dataframe.geo.bounds
import org.jetbrains.kotlinx.dataframe.geo.geometry
import org.jetbrains.kotlinx.dataframe.geo.io.readGeoJson
import org.jetbrains.kotlinx.dataframe.geo.io.readShapefile
import org.jetbrains.kotlinx.dataframe.geo.io.writeGeoJson
import org.jetbrains.kotlinx.dataframe.geo.io.writeShapefile
import org.jetbrains.kotlinx.dataframe.geo.jts.scaleAroundCenter
import org.jetbrains.kotlinx.dataframe.geo.jts.toMultiPolygon
import org.jetbrains.kotlinx.dataframe.geo.jts.translate
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.feature.CoordinatesTransformation
import org.jetbrains.kotlinx.kandy.letsplot.feature.coordinatesTransformation
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.geo.dsl.withData
import org.jetbrains.kotlinx.kandy.letsplot.geo.layers.*
import org.jetbrains.kotlinx.kandy.letsplot.geo.mercator
import org.jetbrains.kotlinx.kandy.letsplot.geo.util.mergePolygons
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.LegendType
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.limits
import org.jetbrains.kotlinx.kandy.letsplot.style.LegendPosition
import org.jetbrains.kotlinx.kandy.letsplot.style.Style
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.tooltips
import org.jetbrains.kotlinx.kandy.letsplot.tooltips.value
import org.jetbrains.kotlinx.kandy.letsplot.x
import org.jetbrains.kotlinx.kandy.letsplot.y
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import org.junit.AfterClass
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters
import org.locationtech.jts.geom.*
import java.io.File
import kotlin.math.*
import kotlin.reflect.typeOf
import kotlin.test.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class GeoGuide : SampleHelper("geoGuide", "guides") {

    private val usaStates =
        GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndreiKingsley/datasets/refs/heads/main/USA.json")
    private val worldCities =
        GeoDataFrame.readShapefile("https://github.com/AndreiKingsley/datasets/raw/refs/heads/main/ne_10m_populated_places_simple/ne_10m_populated_places_simple.shp")

    private val name by column<String>()
    private val winner by column<String>()
    private val pop_min by column<Int>()
    private val usaPolygon: MultiPolygon = usaStates.df.geometry.mergePolygons()

    private val usaCities = worldCities.modify {
        // Filter the DataFrame to include only points inside the `usaPolygon`
        filter {
            // `usaPolygon.contains(geometry)` checks if the `geometry` (a Point)
            // from the current row of `worldCities` is within the `usaPolygon`
            usaPolygon.contains(geometry)
        }
            // Take 30 most populous cities:
            // Sort the remaining rows by population size in descending order
            .sortByDesc {
                pop_min
            }
            // And select the top 30 rows.
            .take(30) as DataFrame<Nothing>
    }

    private val usa48 = usaStates.modify {
        filter {
            name !in listOf("Alaska", "Hawaii", "Puerto Rico")
        } as DataFrame<Nothing>
    }

    private val usaAdjusted = usaStates.modify {
        // Custom extensions for `Geometry` based on JTS API
        // Scale and move Alaska
        update { geometry }.where { name == "Alaska" }.with {
            it.scaleAroundCenter(0.5).translate(40.0, -40.0)
        }
            // Move Hawaii and Puerto Rico
            .update { geometry }.where { name == "Hawaii" }.with { it.translate(65.0, 0.0) }
            .update { geometry }.where { name == "Puerto Rico" }.with { it.translate(-10.0, 5.0) }
                as DataFrame<Nothing>
    }

    private val usa2024electionResults =
        DataFrame.readCSV("https://gist.githubusercontent.com/AndreiKingsley/348687222aecc4f0eb39e3d81acd515b/raw/a9914352dbdfb426f9146dda633ee382d936b000/usa_2024_election_states.csv")

    private val usaStatesWithElectionResults = usaAdjusted.modify {
        innerJoin(usa2024electionResults) { name } as DataFrame<Nothing>
    }

    private val conusAlbersCrs = CRS.decode("EPSG:5070", true)
    private val usaAlbers = usa48.applyCrs(conusAlbersCrs)

    private fun takeCity(name: String): Point = usaCities.df.filter { it.name == name }.single().geometry as Point

    private fun greatCircleLineString(start: Point, end: Point, n: Int = 100): LineString {
        val factory = GeometryFactory()

        val startLat = Math.toRadians(start.y)
        val startLon = Math.toRadians(start.x)
        val endLat = Math.toRadians(end.y)
        val endLon = Math.toRadians(end.x)

        val deltaLon = endLon - startLon
        val cosStartLat = cos(startLat)
        val cosEndLat = cos(endLat)
        val sinStartLat = sin(startLat)
        val sinEndLat = sin(endLat)
        val a = cosStartLat * cosEndLat * cos(deltaLon) + sinStartLat * sinEndLat
        val angularDistance = acos(a)

        if (angularDistance == 0.0) {
            return factory.createLineString(arrayOf(start.coordinate, end.coordinate))
        }

        val coordinates = mutableListOf<Coordinate>()
        for (i in 0..n) {
            val fraction = i.toDouble() / n
            val sinAngularDistance = sin(angularDistance)
            val A = sin((1 - fraction) * angularDistance) / sinAngularDistance
            val B = sin(fraction * angularDistance) / sinAngularDistance

            val x = A * cosStartLat * cos(startLon) + B * cosEndLat * cos(endLon)
            val y = A * cosStartLat * sin(startLon) + B * cosEndLat * sin(endLon)
            val z = A * sinStartLat + B * sinEndLat

            val lat = atan2(z, sqrt(x * x + y * y))
            val lon = atan2(y, x)

            coordinates.add(Coordinate(Math.toDegrees(lon), Math.toDegrees(lat)))
        }

        return factory.createLineString(coordinates.toTypedArray())
    }

    private val newYork = takeCity("New York")
    private val losAngeles = takeCity("Los Angeles")

    private val curveNY_LA = greatCircleLineString(newYork, losAngeles)

    private val usa48Bounds: Envelope = usa48.bounds().also {
        // Use JTS API for in-place envelope expansion:
        it.expandBy(1.0)
    }

    @Test
    fun usaStatesReadGeoJson() {
        // SampleStart
        val usaStates =
            GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndreiKingsley/datasets/refs/heads/main/USA.json")
        // SampleEnd
        assertNotNull(usaStates)
    }

    @Test
    fun usaStatesAccessDataFrame() {
        val usaStatesDf =
            // SampleStart
            usaStates.df
        // SampleEnd
        assertNotNull(usaStatesDf)
    }

    @Test
    fun usaStatesCheckGeometryType() {
        val geometryType =
            // SampleStart
            usaStates.df.geometry.type()
        // SampleEnd
        assertEquals(typeOf<Geometry>(), geometryType)
    }

    @Test
    fun usaStatesDistinctGeometryTypes() {
        val distinctTypes =
            // SampleStart
            usaStates.df.geometry.map { it::class }.distinct().toList()
        // SampleEnd
        assertEquals(setOf(Polygon::class, MultiPolygon::class), distinctTypes.toSet())
    }

    @Test
    fun usaStatesGetCrs() {
        val crs =
            // SampleStart
            usaStates.crs
        // SampleEnd
        assertNull(crs)
    }

    @Test
    fun worldCitiesReadShapefile() {
        // SampleStart
        val worldCities =
            GeoDataFrame.readShapefile("https://github.com/AndreiKingsley/datasets/raw/refs/heads/main/ne_10m_populated_places_simple/ne_10m_populated_places_simple.shp")
        // SampleEnd
        assertNotNull(worldCities)
    }

    @Test
    fun worldCitiesAccessDataFrame() {
        val worldCitiesDf =
            // SampleStart
            worldCities.df
        // SampleEnd
        assertNotNull(worldCitiesDf)
    }

    @Test
    fun worldCitiesCheckGeometryType() {
        val geometryType =
            // SampleStart
            worldCities.df.geometry.type()
        // SampleEnd
        assertEquals(typeOf<Point>(), geometryType)
    }

    // todo korro bug
    // Manual adding for now.
    @Test
    fun worldCitiesGetCrs() {
        val crs =
            // SampleStart
            worldCities.crs
        // SampleEnd
        assertTrue(CRS.equalsIgnoreMetadata(crs, GeoDataFrame.DEFAULT_CRS))
    }

    @Test
    fun usaStatesPlotGeoPolygon() {
        // SampleStart
        usaStates.plot {
            // `geoPolygon` uses polygons and multipolygons
            // from the `geometry` column of `usaStates` inner DataFrame
            geoPolygon()
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun usaStatesGeoPolygonPlotCustomized() {
        // SampleStart
        usaStates.plot {
            geoPolygon {
                fillColor(name) { legend.type = LegendType.None } // Hide legend
                borderLine {
                    width = 0.1
                    color = Color.BLACK
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun usaStatesPlotWithMercator() {
        // SampleStart
        usaStates.plot {
            geoPolygon()
            coordinatesTransformation = CoordinatesTransformation.mercator()
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun usaStatesPlotGeoMap() {
        // SampleStart
        // This plot is identical
        // to the previous one.
        usaStates.plot {
            geoMap()
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun usaStatesPlotWithAxisLimits() {
        // SampleStart
        usaStates.plot {
            geoMap()
            x.axis.limits = -127..-65
            y.axis.limits = 23..50
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun usaStatesPlotWithWorldCities() {
        // SampleStart
        usaStates.plot {
            // `geoMap` takes polygons from the `geometry`
            // column of `usaStates` inner DataFrame
            geoMap()
            // Add a new dataset using the `worldCities` GeoDataFrame.
            // Layers created within this scope will use it as their base dataset
            // instead of the initial one
            withData(worldCities) {
                // `geoPoints` takes points from the `geometry`
                // column of `worldCities` inner DataFrame
                geoPoints() {
                    size = 1.5
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    // TODO Need Korro import support.
    // Manual adding for now.
    @Test
    fun usaStatesMergeIntoSinglePolygon() {
        // SampleStart
        // import mergePolygons utility
        // import org.jetbrains.kotlinx.kandy.letsplot.geo.util.mergePolygons

        // Experimental function that merges a collection of polygons and
        // multipolygons into a single multipolygon
        val usaPolygon: MultiPolygon = usaStates.df.geometry.mergePolygons()
        // SampleEnd
        assertNotNull(usaPolygon)
    }

    @Test
    fun usaStatesPlotMergedPolygon() {
        // SampleStart
        plot {
            // `geoPolygon` and `geoMap` can accept a single `Polygon` or `MultiPolygon`
            geoMap(usaPolygon)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun worldCitiesFilterByUsaBounds() {
        // SampleStart
        val usaCities = worldCities.modify {
            // Filter the DataFrame to include only points inside the `usaPolygon`
            filter {
                // `usaPolygon.contains(geometry)` checks if the `geometry` (a Point)
                // from the current row of `worldCities` is within the `usaPolygon`
                usaPolygon.contains(geometry)
            }
                // Take 30 most populous cities.
                // Sort the remaining rows by population size in descending order
                .sortByDesc {
                    pop_min
                }
                // Select the top 30 rows.
                .take(30) //SampleEnd
                    // SampleStart
                    as DataFrame<Nothing>  // SampleEnd
        }
        // SampleEnd
    }

    @Test
    fun usaStatesPlotWithTopCities() {
        // SampleStart
        usaStates.plot {
            geoMap()
            withData(usaCities) {
                geoPoints {
                    tooltips(title = value(name)) {
                        line("population", value(pop_min))
                    }
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    private val DataRow<*>.name: String
        get() = "name"<String>()

    @Test
    fun usaStatesFilterContiguous() {
        // SampleStart
        val usa48 = usaStates.modify {
            filter {
                name !in listOf("Alaska", "Hawaii", "Puerto Rico")
            }//SampleEnd
                    as DataFrame<Nothing>//SampleStart
        }

        usa48.plot { geoMap() }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun usaStatesAdjusted() {
        // SampleStart
        val usaAdjusted = usaStates.modify {
            // Custom extensions for `Geometry` based on JTS API.
            // Scale and move Alaska:
            update { geometry }.where { name == "Alaska" }.with {
                it.scaleAroundCenter(0.5).translate(40.0, -40.0)
            }
                // Move Hawaii and Puerto Rico:
                .update { geometry }.where { name == "Hawaii" }.with { it.translate(65.0, 0.0) }
                .update { geometry }.where { name == "Puerto Rico" }.with { it.translate(-10.0, 5.0) } // SampleEnd
                    // SampleStart
                    as DataFrame<Nothing> // SampleEnd
        }

        usaAdjusted.plot { geoMap() }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun usaStatesPlotWithCentroids() {
        // SampleStart
        usa48.plot {
            geoMap()
            withData(usa48.modify {
                update { geometry }.with { it.centroid } //SampleEnd
                        as DataFrame<Nothing> // SampleStart
            }) {
                geoPoints()
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun electionResultsLoadData() {
        // SampleStart
        val usa2024electionResults =
            DataFrame.readCSV("https://gist.githubusercontent.com/AndreiKingsley/348687222aecc4f0eb39e3d81acd515b/raw/a9914352dbdfb426f9146dda633ee382d936b000/usa_2024_election_states.csv")

        usa2024electionResults
        // SampleEnd
    }

    @Test
    fun electionResultsJoinWithStates() {
        // SampleStart
        val usaStatesWithElectionResults = usaAdjusted.modify {
            innerJoin(usa2024electionResults) { name }//SampleEnd
                    as DataFrame<Nothing>//SampleStart
        }

        usaStatesWithElectionResults.df
        // SampleEnd
    }

    @Test
    fun electionResultsPlotByParty() {
        // SampleStart
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
            .saveSample()
    }

    @Test
    fun usaStatesTransformToAlbers() {
        // SampleStart
        val conusAlbersCrs = CRS.decode("EPSG:5070", true)
        val usaAlbers = usa48.applyCrs(conusAlbersCrs)
        usaAlbers.crs
        // SampleEnd
        assertTrue(CRS.equalsIgnoreMetadata(usaAlbers.crs, CRS.decode("EPSG:5070", true)))
    }

    @Test
    fun usaStatesPlotWithAlbersCrs() {
        // SampleStart
        usaAlbers.plot {
            // Polygons will work exactly the same -
            // no special coordinates transformation is applied
            // for GeoDF with unsupported CRS
            geoMap()
        }
            // SampleEnd
            .saveSample()
    }

    // TODO Need Korro import support.
    // Manual adding for now.
    @Test
    fun greatCircleCalculationFunction() {
        // SampleStart
        /* import required packages
           import org.locationtech.jts.geom.*
           import kotlin.math.* */

        fun greatCircleLineString(start: Point, end: Point, n: Int = 100): LineString {
            val factory = GeometryFactory()

            val startLat = Math.toRadians(start.y)
            val startLon = Math.toRadians(start.x)
            val endLat = Math.toRadians(end.y)
            val endLon = Math.toRadians(end.x)

            val deltaLon = endLon - startLon
            val cosStartLat = cos(startLat)
            val cosEndLat = cos(endLat)
            val sinStartLat = sin(startLat)
            val sinEndLat = sin(endLat)
            val a = cosStartLat * cosEndLat * cos(deltaLon) + sinStartLat * sinEndLat
            val angularDistance = acos(a)

            if (angularDistance == 0.0) {
                return factory.createLineString(arrayOf(start.coordinate, end.coordinate))
            }

            val coordinates = mutableListOf<Coordinate>()
            for (i in 0..n) {
                val fraction = i.toDouble() / n
                val sinAngularDistance = sin(angularDistance)
                val A = sin((1 - fraction) * angularDistance) / sinAngularDistance
                val B = sin(fraction * angularDistance) / sinAngularDistance

                val x = A * cosStartLat * cos(startLon) + B * cosEndLat * cos(endLon)
                val y = A * cosStartLat * sin(startLon) + B * cosEndLat * sin(endLon)
                val z = A * sinStartLat + B * sinEndLat

                val lat = atan2(z, sqrt(x * x + y * y))
                val lon = atan2(y, x)

                coordinates.add(Coordinate(Math.toDegrees(lon), Math.toDegrees(lat)))
            }

            return factory.createLineString(coordinates.toTypedArray())
        }
        // SampleEnd
    }

    @Test
    fun cityGeometryExtractionFunction() {
        // SampleStart
        fun takeCity(name: String) = usaCities.df.filter { it.name == name }.single().geometry
        // SampleEnd
    }

    @Test
    fun citiesExtractNewYorkLosAngeles() {
        // SampleStart
        val newYork = takeCity("New York")
        val losAngeles = takeCity("Los Angeles")
        // SampleEnd
    }

    @Test
    fun greatCircleNYToLA() {
        // SampleStart
        val curveNY_LA = greatCircleLineString(newYork, losAngeles)
        // SampleEnd
    }

    @Test
    fun usaStatesPlotWithGreatCircle() {
        // SampleStart
        usa48.plot {
            geoMap { alpha = 0.5 }
            geoPath(curveNY_LA) { width = 1.5 }
            geoPoints(listOf(newYork, losAngeles)) {
                size = 8.0
                color = Color.RED
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun usaStatesCalculateBounds() {
        // SampleStart
        // `.bounds()` function calculates the minimum bounding box
        // of all geometries in the `geometry` column of a `GeoDataFrame`,
        // returning it as an `Envelope`
        val usa48Bounds: Envelope = usa48.bounds().also {
            // Use JTS API for in-place envelope expansion
            it.expandBy(1.0)
        }
        // SampleEnd
    }

    @Test
    fun usaStatesPlotWithBounds() {
        // SampleStart
        usa48.plot {
            geoMap()
            geoRectangles(usa48Bounds) {
                alpha = 0.0
                borderLine {
                    width = 2.0
                    color = Color.GREY
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun usaStatesPlotWithDefaultBounds() {
        // SampleStart
        usa48.plot {
            geoMap()
            geoRectangles()
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun writeGeoJson1usaCitiesExportToGeoJson() {
        // SampleStart
        usaCities.writeGeoJson("usa_cities.geojson")
        // SampleEnd
    }

    @Test
    fun writeGeoJson2usaCitiesPlotFromGeoJson() {
        // SampleStart
        GeoDataFrame.readGeoJson("usa_cities.geojson").plot { geoPoints() }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun writeShapefile1usaStatesExportToShapefile() {
        // SampleStart
        // All geometries should be the same type (Shapefile restriction),
        // but we have both `Polygon` and `MultiPolygon`.
        // Cast them all into MultiPolygons
        usa48.modify {
            convert { geometry }.with {
                when (it) {
                    // Cast `Polygon` to `MultiPolygon` with a single entity
                    is Polygon -> it.toMultiPolygon()
                    is MultiPolygon -> it
                    else -> error("not a polygonal geometry")
                }
            } //SampleEnd
                    as DataFrame<Nothing>//SampleStart
        }
            // All files comprising the Shapefile will be saved to
            // a directory named "usa_48" and will have the same base name
            .writeShapefile("usa_48")
        // SampleEnd
    }

    @Test
    fun writeShapefile2usaStatesPlotFromShapefile() {
        // SampleStart
        GeoDataFrame.readShapefile("usa_48/usa_48.shp").plot { geoMap() }
            // SampleEnd
            .saveSample()
    }

    companion object {
        @AfterClass
        @JvmStatic
        fun cleanup() {
            File("usa_48").deleteRecursively()
            File("usa_cities.geojson").delete()
        }
    }
}
