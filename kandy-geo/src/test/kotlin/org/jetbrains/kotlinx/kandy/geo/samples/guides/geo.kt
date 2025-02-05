@file:Suppress("UNCHECKED_CAST", "UNUSED_EXPRESSION", "UNUSED_VARIABLE")

package org.jetbrains.kotlinx.kandy.geo.samples.guides

import org.geotools.referencing.CRS
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.DataRow
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.filter
import org.jetbrains.kotlinx.dataframe.api.innerJoin
import org.jetbrains.kotlinx.dataframe.api.convert
import org.jetbrains.kotlinx.dataframe.api.map
import org.jetbrains.kotlinx.dataframe.api.sortByDesc
import org.jetbrains.kotlinx.dataframe.api.take
import org.jetbrains.kotlinx.dataframe.api.update
import org.jetbrains.kotlinx.dataframe.api.where
import org.jetbrains.kotlinx.dataframe.api.with
import org.jetbrains.kotlinx.dataframe.api.single
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
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.Envelope
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.LineString
import org.locationtech.jts.geom.MultiPolygon
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.Polygon
import kotlin.math.*
import kotlin.reflect.typeOf
import kotlin.sequences.single
import kotlin.test.*
import kotlin.text.single

class NotebookTests : SampleHelper("geoGuide", "guides") {

    private val usaStates =
        GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndreiKingsley/datasets/refs/heads/main/USA.json")
    private val worldCities =
        GeoDataFrame.readShapefile("https://github.com/AndreiKingsley/datasets/raw/refs/heads/main/ne_10m_populated_places_simple/ne_10m_populated_places_simple.shp")

    private val name by column<String>()
    private val winner by column<String>()
    private val pop_min by column<Int>()
    private val usaPolygon: MultiPolygon = usaStates.df.geometry.mergePolygons()

    private val usaCities = worldCities.modify {
        // filter the DataFrame to include only points inside the `usaPolygon`
        filter {
            // `usaPolygon.contains(geometry)` checks if the `geometry` (a Point)
            // from the current row of `worldCities` is within the `usaPolygon`
            usaPolygon.contains(geometry)
        }
            // take 30 most populous cities -
            // sort the remaining rows by population size in descending order
            .sortByDesc {
                pop_min
            }
            // and select the top 30 rows
            .take(30) as DataFrame<Nothing>
    }

    private val usa48 = usaStates.modify {
        filter {
            name !in listOf("Alaska", "Hawaii", "Puerto Rico")
        }
    }

    private val usaAdjusted = usaStates.modify {
        // custom extensions for `Geometry` based on JTS API;
        // scale and move Alaska
        update { geometry }.where { name == "Alaska" }.with {
            it.scaleAroundCenter(0.5).translate(40.0, -40.0)
        }
            // move Hawaii and Puerto Rico
            .update { geometry }.where { name == "Hawaii" }.with { it.translate(65.0, 0.0) }
            .update { geometry }.where { name == "Puerto Rico" }.with { it.translate(-10.0, 5.0) }
                as DataFrame<Nothing>
    }

    private val usa2024electionResults =
        DataFrame.readCSV("https://gist.githubusercontent.com/AndreiKingsley/348687222aecc4f0eb39e3d81acd515b/raw/a9914352dbdfb426f9146dda633ee382d936b000/usa_2024_election_states.csv")

    private val usaStatesWithElectionResults = usaAdjusted.modify {
        innerJoin(usa2024electionResults) { name }
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
        // JTS API for in-place envelope expansion
        it.expandBy(1.0)
    }

    @Test
    fun testCase_0c0ac2b2() {
        // SampleStart
        val usaStates =
            GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndreiKingsley/datasets/refs/heads/main/USA.json")
        // SampleEnd
        assertNotNull(usaStates)
    }

    @Test
    fun testCase_83464e3f() {
        val usaStatesDf =
            // SampleStart
            usaStates.df
        // SampleEnd
        assertNotNull(usaStatesDf)
    }

    @Test
    fun testCase_fc63b59a() {
        val geometryType =
            // SampleStart
            usaStates.df.geometry.type()
        // SampleEnd
        assertEquals(typeOf<Geometry>(), geometryType)
    }

    @Test
    fun testCase_388c463e() {
        val distinctTypes =
            // SampleStart
            usaStates.df.geometry.map { it::class }.distinct()
        // SampleEnd
        assertEquals(setOf(Polygon::class, MultiPolygon::class), distinctTypes.toSet())
    }

    @Test
    fun testCase_01c97026() {
        val crs =
            // SampleStart
            usaStates.crs
        // SampleEnd
        assertNull(crs)
    }

    @Test
    fun testCase_4329905c() {
        // SampleStart
        val worldCities =
            GeoDataFrame.readShapefile("https://github.com/AndreiKingsley/datasets/raw/refs/heads/main/ne_10m_populated_places_simple/ne_10m_populated_places_simple.shp")
        // SampleEnd
        assertNotNull(worldCities)
    }

    @Test
    fun testCase_bdb8c73d() {
        val worldCitiesDf =
            // SampleStart
            worldCities.df
        // SampleEnd
        assertNotNull(worldCitiesDf)
    }

    @Test
    fun testCase_5767c6f6() {
        val geometryType =
            // SampleStart
            worldCities.df.geometry.type()
        // SampleEnd
        assertEquals(typeOf<Point>(), geometryType)
    }

    @Test
    fun testCase_bfcea27a() {
        val crs =
            // SampleStart
            worldCities.crs
        // SampleEnd
        assertTrue(CRS.equalsIgnoreMetadata(crs, GeoDataFrame.DEFAULT_CRS))
    }

    @Test
    fun testCase_99650ca2() {
        // SampleStart
        usaStates.plot {
            // `geoPolygon` uses polygons and multipolygons
            // from `geometry` column of `usaStates` inner DataFrame.
            geoPolygon()
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun testCase_0e6526e4() {
        // SampleStart
        usaStates.plot {
            geoPolygon {
                fillColor(name) { legend.type = LegendType.None } // hide legend
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
    fun usaStatesWithMercatorTransformation() {
        // SampleStart
        usaStates.plot {
            geoPolygon()
            coordinatesTransformation = CoordinatesTransformation.mercator()
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun testCase_5d1706f5() {
        // SampleStart
        // You can see that this plot is identical
        // with the previous one.
        usaStates.plot {
            geoMap()
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun testCase_c07a359d() {
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
    fun testCase_c7fd7069() {
        // SampleStart
        usaStates.plot {
            // `geoMap` takes polygons from `geometry`
            // column of `usaStates` inner DataFrame
            geoMap()
            // Add a new dataset using the `worldCities` GeoDataFrame.
            // Layers created within this scope will use it as their base dataset
            // instead of the initial one.
            withData(worldCities) {
                // `geoPoints` takes points from the ` geometry `
                // column of `worldCities` inner DataFrame
                geoPoints() {
                    size = 1.5
                }
            }
        }
            // SampleEnd
            .saveSample()
    }

    // TODO need korro import support
    // manual adding for now
    @Test
    fun testCase_49189967() {
        // SampleStart
        //     import org . jetbrains . kotlinx . kandy . letsplot . geo . util . mergePolygons

        // experimental function that merges a collection of polygons and
        // multipolygons into a single multipolygon
        val usaPolygon: MultiPolygon = usaStates.df.geometry.mergePolygons()
        // SampleEnd
        assertNotNull(usaPolygon)
    }

    @Test
    fun testCase_6b6c85c0() {
        // SampleStart
        plot {
            // `geoPolygon` and `geoMap` can accept a single `Polygon` / `MultiPolygon`
            geoMap(usaPolygon)
        }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun testCase_87042fe4() {
        // SampleStart
        val usaCities = worldCities.modify {
            // filter the DataFrame to include only points inside the `usaPolygon`
            filter {
                // `usaPolygon.contains(geometry)` checks if the `geometry` (a Point)
                // from the current row of `worldCities` is within the `usaPolygon`
                usaPolygon.contains(geometry)
            }
                // take 30 most populous cities -
                // sort the remaining rows by population size in descending order
                .sortByDesc {
                    pop_min
                }
                // and select the top 30 rows
                .take(30) //SampleEnd
                    // SampleStart
                    as DataFrame<Nothing>  // SampleEnd
        }
        // SampleEnd
    }

    @Test
    fun testCase_bc30d96e() {
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
    fun testCase_3bc837a1() {
        // SampleStart
        val usa48 = usaStates.modify {
            filter {
                name !in listOf("Alaska", "Hawaii", "Puerto Rico")
            }
        }

        usa48.plot { geoMap() }
            // SampleEnd
            .saveSample()
    }

    @Test
    fun testCase_47b47644() {
        // SampleStart
        val usaAdjusted = usaStates.modify {
            // custom extensions for `Geometry` based on JTS API;
            // scale and move Alaska
            update { geometry }.where { name == "Alaska" }.with {
                it.scaleAroundCenter(0.5).translate(40.0, -40.0)
            }
                // move Hawaii and Puerto Rico
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
    fun testCase_2b31bb9a() {
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
    fun testCase_71a35421() {
        // SampleStart
        val usa2024electionResults =
            DataFrame.readCSV("https://gist.githubusercontent.com/AndreiKingsley/348687222aecc4f0eb39e3d81acd515b/raw/a9914352dbdfb426f9146dda633ee382d936b000/usa_2024_election_states.csv")

        usa2024electionResults
        // SampleEnd
    }

    @Test
    fun testCase_fb104863() {
        // SampleStart
        val usaStatesWithElectionResults = usaAdjusted.modify {
            innerJoin(usa2024electionResults) { name }
        }

        usaStatesWithElectionResults.df
        // SampleEnd
    }

    @Test
    fun testCase_949e5086() {
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
    fun testCase_42b9affd() {
        // SampleStart
        val conusAlbersCrs = CRS.decode("EPSG:5070", true)
        val usaAlbers = usa48.applyCrs(conusAlbersCrs)
        usaAlbers.crs
        // SampleEnd
        assertTrue(CRS.equalsIgnoreMetadata(usaAlbers.crs, CRS.decode("EPSG:5070", true)))
    }

    @Test
    fun testCase_f05aadc2() {
        // SampleStart
        usaAlbers.plot {
            // polygons will work exactly the same -
            // no special coorinates transformation is applied
            // for GeoDF with unsupported crs
            geoMap()
        }
        // SampleEnd
            .saveSample()
    }

    // TODO need korro import support
    // manual adding for now
    @Test
    fun testCase_2ff23ffc() {
        // SampleStart
       /* import org . locationtech . jts . geom . *
                import kotlin . math . **/

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
    fun testCase_2d8f87bc() {
        // SampleStart
        fun takeCity(name: String) = usaCities.df.filter { it.name == name }.single().geometry
        // SampleEnd
    }

    @Test
    fun testCase_51470893() {
        // SampleStart
        val newYork = takeCity("New York")
        val losAngeles = takeCity("Los Angeles")
        // SampleEnd
    }

    @Test
    fun testCase_d4788fe1() {
        // SampleStart
        val curveNY_LA = greatCircleLineString(newYork, losAngeles)
        // SampleEnd
    }

    @Test
    fun testCase_143262fc() {
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
    fun testCase_27d7a586() {
        // SampleStart
        // The `.bounds()` function calculates the minimum bounding box
        // of all geometries in the `geometry` column of a `GeoDataFrame`,
        // returning it as an `Envelope`.
        val usa48Bounds: Envelope = usa48.bounds().also {
            // JTS API for in-place envelope expansion
            it.expandBy(1.0)
        }
        // SampleEnd
    }

    @Test
    fun testCase_6b6ed9ad() {
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
    fun testCase_90fc4240() {
        // SampleStart
        usa48.plot {
            geoMap()
            geoRectangles()
        }
        // SampleEnd
            .saveSample()
    }

    @Test
    fun testCase_8b9a7c16() {
        // SampleStart
        usaCities.writeGeoJson("usa_cities.geojson")
        // SampleEnd
    }

    @Test
    fun testCase_e2a8f29f() {
        // SampleStart
        GeoDataFrame.readGeoJson("usa_cities.geojson").plot { geoPoints() }
        // SampleEnd
            .saveSample()
    }

    @Test
    fun testCase_1a942ecf() {
        // SampleStart
// All geometries should be the same type - Shapefile restriction,
// but we have `Polygon` and `MultiPolygon`.
// Cast them all into multipolygons
        usa48.modify {
            convert { geometry }.with {
                when (it) {
                    // Casts `Polygon` to a `MultiPolygon` with a single entity
                    is Polygon -> it.toMultiPolygon()
                    is MultiPolygon -> it
                    else -> error("not a polygonal")
                }
            } //SampleEnd
         as DataFrame<Nothing>//SampleStart
        }
            // All files comprising the Shapefile will be saved to
            // a directory named "usa_48" and will have the same base name.
            .writeShapefile("usa_48")
        // SampleEnd
    }

    @Test
    fun testCase_5adebe2e() {
        // SampleStart
        GeoDataFrame.readShapefile("usa_48/usa_48.shp").plot { geoMap() }
        // SampleEnd
            .saveSample()
    }


}
