# Geo Plotting

Kandy-Geo adds extensions which allow plotting a `GeoDataFrame` — a [DataFrame](https://github.com/Kotlin/dataframe)
-based structure for geospatial
datasets. [All the plotting principles and features](https://kotlin.github.io/kandy/quick-start-guide.html) here are the
same as Kandy, the only difference is that you don't have to perform positional mapping — instead geometries will be
automatically mapped to Kandy layers.

## Kandy-Geo and DataFrame-Geo Usage

<tabs>
<tab title="Notebooks">

To integrate Kandy-Geo and Dataframe-Geo into an interactive notebook, use the following commands:

<tabs>
<tab title="Latest versions">

> Without specifying `%useLatestDescriptors`,
> the version included in the Kotlin Jupyter kernel will be used.
> {style="note"}

<br/>

```
// Fetches the latest versions
%useLatestDescriptors
// Adds both the kandy-geo and the dataframe-geo libraries with the latest versions
%use kandy-geo
```

</tab>
<tab title="Specify versions">

```
// Adds the kandy-geo and the dataframe-geo libraries with a specific versions
%use kandy-geo(kandyVersion=%kandy_latest_version%, dataframeVersion=%dataframe_latest_version%)
```

</tab>
</tabs>

> Kotlin notebook offers unique features with the dataframe library.


</tab>
<tab title="Gradle">

To include Kandy-Geo and DataFrame-Geo in your Gradle project, add the following to your dependencies:

```kotlin
repositories {
    // Maven repository with GeoTools releases
    maven("https://repo.osgeo.org/repository/release")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kandy-geo:%kandy_latest_version%")
    implementation("org.jetbrains.kotlinx:dataframe-geo:%dataframe_latest_version%")
}
```

</tab>
</tabs>

<!---IMPORT org.jetbrains.kotlinx.kandy.geo.samples.guides.GeoGuide-->

## Geometries

Geo plotting is essentially the visualization of geographic geometries on a map or coordinate
system. [GeoJSON](https://en.wikipedia.org/wiki/GeoJSON) is the most widely used standard for representing geospatial
data. It defines a set of geometry types that are simple yet powerful for modeling geographic features:

- **`Point`**: Represents a specific location as a single coordinate.
- **`MultiPoint`**: A collection of multiple `Point` geometries.
- **`LineString`**: A sequence of connected points, forming a path or linear feature.
- **`MultiLineString`**: A collection of multiple `LineString` geometries.
- **`Polygon`**: A closed shape with an outer boundary and optional inner holes.
- **`MultiPolygon`**: A collection of multiple `Polygon` geometries.
- **`GeometryCollection`**: A container for any combination of the above geometries.

[JTS](https://github.com/locationtech/jts) (Java Topology Suite) is a library that works seamlessly with these
geometries, adding a variety of operations. It allows you to perform tasks like combining geometries, finding
intersections, creating buffers, or simplifying shapes.

All classes for the aforementioned geometries are provided in JTS and inherit from the base class `Geometry`. 
**`GeoDataFrame`** is a wrapper around a standard `DataFrame` with a `geometry` column of type `Geometry`, enabling
convenient handling of geospatial datasets.

## Reading GeoDataFrame

Currently, the GeoDataFrame supports two of the most popular formats: Shapefile and GeoJSON. These formats can be read
into a `GeoDataFrame` using the corresponding `GeoDataFrame.read..()` functions. Each of these functions returns a
`GeoDataFrame`.

### GeoJSON {id="geojson_read"}

[GeoJSON](https://en.wikipedia.org/wiki/GeoJSON) is a widely used format for encoding geographic data structures.
It represents spatial features such as points, lines, and polygons, along with their properties, using JSON. Here's an
example of GeoJSON:

```
{
  "type": "Feature",
  "geometry": {
    "type": "Point",
    "coordinates": [125.6, 10.1]
  },
  "properties": {
    "name": "Dinagat Islands"
  }
}
```

Let's load a GeoJSON file that contains polygons representing the boundaries of US states:

<!---FUN usaStatesReadGeoJson-->

```kotlin
val usaStates =
    GeoDataFrame.readGeoJson("https://raw.githubusercontent.com/AndreiKingsley/datasets/refs/heads/main/USA.json")
```

<!---END-->

We can directly access the underlying DataFrame to take a closer look at its contents:

<!---FUN usaStatesAccessDataFrame-->

```kotlin
usaStates.df
```

<!---END-->

![usaStatesDf.png](usaStatesDf.png)

This DataFrame **is required** to have a geometry column of type `org.locationtech.jts.geom.Geometry`:


<!---FUN usaStatesCheckGeometryType-->

```kotlin
usaStates.df.geometry.type()
```

<!---END-->

Output:

```
org.locationtech.jts.geom.Geometry
```



We can also check the exact types of these geometries:

<!---FUN usaStatesDistinctGeometryTypes-->

```kotlin
usaStates.df.geometry.map { it::class }.distinct().toList()
```

<!---END-->

Output:

```
[class org.locationtech.jts.geom.Polygon, class org.locationtech.jts.geom.MultiPolygon]
```


As expected, these are `Polygon` and `MultiPolygon`.

The `GeoDataFrame` also contains a `.crs` field for the coordinate reference system (CRS). In GeoJSON, this field is not
explicitly defined* and is read as `null`. If this field is not explicitly set in the GeoDataFrame, it is assumed by
default to
use [WGS84](https://gisgeography.com/wgs84-world-geodetic-system/) —
the standard CRS for working with geospatial data.

\* *According to the [GeoJSON specification](https://datatracker.ietf.org/doc/html/rfc7946#section-4), all coordinates
are defined in WGS84. In the future, we may remove the nullability of the `crs` field, and WGS84 will be explicitly set
as the CRS when reading GeoJSON files.*



<!---FUN usaStatesGetCrs-->

```kotlin
usaStates.crs
```

<!---END-->

Output:

```
null
```

### Shapefile {id="shapefile_read"}

[Shapefile](https://en.wikipedia.org/wiki/Shapefile) is a popular geospatial vector data format developed by ESRI. It stores geometric features such as points,
lines, and polygons, along with their attributes, across multiple files. A Shapefile requires at least three parts:
`.shp` (geometry), `.shx` (spatial index), and `.dbf` (attributes), and it typically uses a defined coordinate reference
system.

To load a Shapefile, you need to specify the path to the file with the `.shp` extension. The other required files must
be in the same directory and share the same base name.

Let's load a Shapefile with the most populated cities in the world:


<!---FUN worldCitiesReadShapefile-->

```kotlin
val worldCities =
    GeoDataFrame.readShapefile("https://github.com/AndreiKingsley/datasets/raw/refs/heads/main/ne_10m_populated_places_simple/ne_10m_populated_places_simple.shp")
```

<!---END-->

Take a look inside the DataFrame:

<!---FUN worldCitiesAccessDataFrame-->

```kotlin
worldCities.df
```

<!---END-->

![worldCitiesDf.png](worldCitiesDf.png)

This `GeoDataFrame` contains only `Point` geometries:


<!---FUN worldCitiesCheckGeometryType-->

```kotlin
worldCities.df.geometry.type()
```

<!---END-->

Output: 

```
org.locationtech.jts.geom.Point
```

And has explicitly specified CRS:

```kotlin
    worldCities.crs
```

Output:

```
GEOGCS["GCS_WGS_1984", 
  DATUM["D_WGS_1984", 
    SPHEROID["WGS_1984", 6378137.0, 298.257223563]], 
  PRIMEM["Greenwich", 0.0], 
  UNIT["degree", 0.017453292519943295], 
  AXIS["Longitude", EAST], 
  AXIS["Latitude", NORTH]]
```

## Plot

Geo plotting in Kandy is not significantly different from usual plotting. The main distinction is that you need to
provide the aforementioned geometries instead of specifying positional mappings.

To facilitate this, Kandy-Geo introduces *geo layers*, which, unlike regular layers, accept geometries. These can be
provided as DataFrame columns, `Iterable`, or single instances. If a layer is built in the context of a `GeoDataFrame`
dataset, it is not necessary to explicitly specify the geometry, as the `geometry` column will be used by default.

### geoPolygon

The `geoPolygon()` adds a layer of polygons constructed using `Polygon` and `MultiPolygon` geometries.

Let's plot US states from `usaStates`:

<!---FUN usaStatesPlotGeoPolygon-->

```kotlin
usaStates.plot {
    // `geoPolygon` uses polygons and multipolygons
    // from the `geometry` column of `usaStates` inner DataFrame
    geoPolygon()
}
```

<!---END-->

![usaStatesPlotGeoPolygon](usaStatesPlotGeoPolygon.svg)

The customization process for such a layer is no different from a regular one. The function optionally opens a block
where you can configure all polygon aesthetic attributes as usual using mappings and settings. For example, you can
color each state by mapping the `name` column to `fillColor` and customize the `borderLine` as shown below:


<!---FUN usaStatesGeoPolygonPlotCustomized-->

```kotlin
usaStates.plot {
    geoPolygon {
        fillColor(name) { legend.type = LegendType.None } // Hide legend
        borderLine {
            width = 0.1
            color = Color.BLACK
        }
    }
}
```

<!---END-->

![usaStatesGeoPolygonPlotCustomized](usaStatesGeoPolygonPlotCustomized.svg)

#### Mercator coordinates transformation

The Mercator projection is widely used for map visualizations because it preserves angles and shapes locally, making it
ideal for navigation and geographical applications. It is particularly useful for rendering maps on flat surfaces, such
as screens or paper. The Mercator projection is compatible with coordinates in the WGS84 coordinate system, as it uses
latitude and longitude values to project the curved surface of the Earth onto a 2D plane. In this projection, only the
axes of the plot are transformed, while the actual values of the points remain unchanged.

<!---FUN usaStatesPlotWithMercator-->

```kotlin
usaStates.plot {
    geoPolygon()
    coordinatesTransformation = CoordinatesTransformation.mercator()
}
```

<!---END-->

![usaStatesPlotWithMercator](usaStatesPlotWithMercator.svg)

#### geoMap

`geoMap()` is a basically `geoPolygon()` but it also applies coordinates transformation based on the provided
`CoordinateReferenceSystem` (`GeoDataFrame.crs`). Now only _WGS84_ is supported (where the mercator projection is
applied by default).

<!---FUN usaStatesPlotGeoMap-->

```kotlin
// This plot is identical
// to the previous one.
usaStates.plot {
    geoMap()
}
```

<!---END-->

![usaStatesPlotGeoMap](usaStatesPlotGeoMap.svg)

When the Mercator projection is applied, we can still set axis limits as usual. However, there are inherent boundaries
at 180 and 90 degrees due to the nature of geographic coordinates.

<!---FUN usaStatesPlotWithAxisLimits-->

```kotlin
usaStates.plot {
    geoMap()
    x.axis.limits = -127..-65
    y.axis.limits = 23..50
}
```

<!---END-->

![usaStatesPlotWithAxisLimits](usaStatesPlotWithAxisLimits.svg)

### geoPoints

The `geoPoints()` adds a layer of points constructed using `Point` and `MultiPoint` geometries.

Let's add `worldCities` points over `usaStates` polygons:

<!---FUN usaStatesPlotWithWorldCities-->

```kotlin
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
```

<!---END-->

![usaStatesPlotWithWorldCities](usaStatesPlotWithWorldCities.svg)

## GeoDataFrame modifying

Before plotting, it is often necessary to modify the geo- dataframe. For example, you might filter points within a
specific area, translate or scale certain geometries, and so on. `GeoDataFrame` allows direct updates to its inner
`DataFrame` using the familiar [DataFrame Operations API](https://kotlin.github.io/dataframe/operations.html).

### DataFrame operations

The function `GeoDataFrame<T>.modify(block: DataFrame<T>.() -> DataFrame<T>): GeoDataFrame<T>` opens a new scope where
the receiver is the inner `DataFrame` of this GeoDataFrame. This allows you to perform operations such as `filter`,
`take`, `sort`, `update`, and others directly on it. The function returns a GeoDataFrame with the modified DataFrame
resulting from the block, while keeping the CRS unchanged.

Let's filter the points in `worldCities`, keeping only those located within the US. To do this, we will first combine
all polygons from `usaStates` into a single polygon for convenience:

```Kotlin
// import mergePolygons utility
import org.jetbrains.kotlinx.kandy.letsplot.geo.util.mergePolygons

// Experimental function that merges a collection of polygons and
// multipolygons into a single multipolygon
val usaPolygon: MultiPolygon = usaStates.df.geometry.mergePolygons()
```

<!---FUN usaStatesPlotMergedPolygon-->

```kotlin
plot {
    // `geoPolygon` and `geoMap` can accept a single `Polygon` or `MultiPolygon`
    geoMap(usaPolygon)
}
```

<!---END-->

![usaStatesPlotMergedPolygon](usaStatesPlotMergedPolygon.svg)

Now, let's create a GeoDataFrame `usaCities` containing only the cities located within the United States. To avoid over
plotting, we will select the 30 most populous cities. For this, we will modify `worldCities`:

<!---FUN worldCitiesFilterByUsaBounds-->

```kotlin
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
        .take(30) 
            as DataFrame<Nothing>
```

<!---END-->


Now we can visualize the result by overlaying the points representing these cities on the polygons of the states (as
above):

<!---FUN usaStatesPlotWithTopCities-->

```kotlin
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
```

<!---END-->

![usaStatesPlotWithTopCities](usaStatesPlotWithTopCities.svg)

As you can see, the map of the US is significantly stretched by distant territories such as Puerto Rico, Hawaii, and
Alaska. We can remove these regions, keeping only the continental part (48 states):


<!---FUN usaStatesFilterContiguous-->

```kotlin
val usa48 = usaStates.modify {
    filter {
        name !in listOf("Alaska", "Hawaii", "Puerto Rico")
    }
}

usa48.plot { geoMap() }
```

<!---END-->

![usaStatesFilterContiguous](usaStatesFilterContiguous.svg)

### Geometry operations

Another, more elegant way to improve the appearance of the US map is to scale and reposition these polygons, making the
plot more compact.

The DataFrame-Geo library provides Kotlin-style extensions for JTS geometries. For instance, `Geometry.translate(x, y)`
shifts a geometry by a specified vector, while `Geometry.scaleAroundCenter(factor)` scales a geometry relative to its
centroid.


<!---FUN usaStatesAdjusted-->

```kotlin
val usaAdjusted = usaStates.modify {
    // Custom extensions for `Geometry` based on JTS API.
    // Scale and move Alaska:
    update { geometry }.where { name == "Alaska" }.with {
        it.scaleAroundCenter(0.5).translate(40.0, -40.0)
    }
        // Move Hawaii and Puerto Rico:
        .update { geometry }.where { name == "Hawaii" }.with { it.translate(65.0, 0.0) }
        .update { geometry }.where { name == "Puerto Rico" }.with { it.translate(-10.0, 5.0) } 
            as DataFrame<Nothing>
```

<!---END-->

![usaStatesAdjusted](usaStatesAdjusted.svg)

An example of the states maps with their centroids:

<!---FUN usaStatesPlotWithCentroids-->

```kotlin
usa48.plot {
    geoMap()
    withData(usa48.modify {
        update { geometry }.with { it.centroid } 
    }) {
        geoPoints()
    }
}
```

<!---END-->

![usaStatesPlotWithCentroids](usaStatesPlotWithCentroids.svg)

#### Datasets Join

In geo-plotting, separate datasets are often used—one containing the geometries and others with specific data. To
combine them, you can [join](https://kotlin.github.io/dataframe/join.html) them using `modify`. Let's load a `DataFrame`
with the results of the 2024 US presidential election:


<!---FUN electionResultsLoadData-->

```kotlin
val usa2024electionResults =
    DataFrame.readCSV("https://gist.githubusercontent.com/AndreiKingsley/348687222aecc4f0eb39e3d81acd515b/raw/a9914352dbdfb426f9146dda633ee382d936b000/usa_2024_election_states.csv")

usa2024electionResults
```

<!---END-->

![electionResults.png](electionResults.png)

And join it to the US states `GeoDataFrame`:

<!---FUN electionResultsJoinWithStates-->

```kotlin
val usaStatesWithElectionResults = usaAdjusted.modify {
    innerJoin(usa2024electionResults) { name }
}

usaStatesWithElectionResults.df
```

<!---END-->

![usStatesWithElectionResults.png](usStatesWithElectionResults.png)

Now we can create a geo plot with a color scale based on state election results:

<!---FUN electionResultsPlotByParty-->

```kotlin
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
```

<!---END-->

![electionResultsPlotByParty](electionResultsPlotByParty.svg)

### Applying new CRS

A new coordinate system can be applied to a GeoDataFrame by projecting all geometries into it (note that this is not
always possible, so proceed with caution).

The *CONUS (Conterminous United States) Albers projection* is a widely used coordinate reference system tailored for the
contiguous United States (48 states). It is an equal-area projection, meaning it preserves area proportions while
slightly distorting shapes and distances. This projection is ideal for visualizing geographic features across large
regions of the continental US.

Let's apply the CONUS Albers projection to the state polygons:


<!---FUN usaStatesTransformToAlbers-->

```kotlin
val conusAlbersCrs = CRS.decode("EPSG:5070", true)
val usaAlbers = usa48.applyCrs(conusAlbersCrs)
usaAlbers.crs
println("CRS.equalsIgnoreMetadata(usaAlbers.crs, CRS.decode("EPSG:5070", true)) is ${CRS.equalsIgnoreMetadata(usaAlbers.crs, CRS.decode("EPSG:5070", true))}") // true
```

<!---END-->

Output :

```
PROJCS["NAD83 / Conus Albers", 
  GEOGCS["NAD83", 
    DATUM["North American Datum 1983", 
      SPHEROID["GRS 1980", 6378137.0, 298.257222101, AUTHORITY["EPSG","7019"]], 
...
```

<!---FUN usaStatesPlotWithAlbersCrs-->

```kotlin
usaAlbers.plot {
    // Polygons will work exactly the same -
    // no special coordinates transformation is applied
    // for GeoDF with unsupported CRS
    geoMap()
}
```

<!---END-->

![usaStatesPlotWithAlbersCrs](usaStatesPlotWithAlbersCrs.svg)

### geoPath

The `geoPath()` adds a layer of a path constructed using `LineString` and `MultiLineString` geometries.

The following function constructs the shortest path on the Earth's surface, known as a [*great-circle
line*](https://en.wikipedia.org/wiki/Great_circle). A great-circle line represents the shortest distance between two
points on a sphere, following the curvature of the Earth. The path is approximated using a `LineString` with a specified
number of points `n` for precision.


```Kotlin
import org.locationtech.jts.geom.*
import kotlin.math.*

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
```

This convenient function finds a city in `usaCities` by name and returns its geometry (point):

<!---FUN cityGeometryExtractionFunction-->

```kotlin
fun takeCity(name: String) = usaCities.df.filter { it.name == name }.single().geometry
```

<!---END-->

Use it to take points of New York and Los Angeles:

<!---FUN citiesExtractNewYorkLosAngeles-->

```kotlin
val newYork = takeCity("New York")
val losAngeles = takeCity("Los Angeles")
```

<!---END-->

Count the shortest path between them:

<!---FUN greatCircleNYToLA-->

```kotlin
val curveNY_LA = greatCircleLineString(newYork, losAngeles)
```

<!---END-->

Now, let's plot this curve using `geoPath`, overlaying it on top of the state polygons and highlighting the points
corresponding to the cities:

<!---FUN usaStatesPlotWithGreatCircle-->

```kotlin
usa48.plot {
    geoMap { alpha = 0.5 }
    geoPath(curveNY_LA) { width = 1.5 }
    geoPoints(listOf(newYork, losAngeles)) {
        size = 8.0
        color = Color.RED
    }
}
```

<!---END-->

![usaStatesPlotWithGreatCircle](usaStatesPlotWithGreatCircle.svg)

### geoRectangles

The `geoRectangles()` adds a layer of rectangles constructed using `Envelope`. The `Envelope` class represents a
rectangular region in the coordinate space, defined by its minimum and maximum coordinates. It is commonly used for
bounding boxes, spatial indexing, and efficient geometric calculations.

Let's get `usa48` common bounding box:

<!---FUN usaStatesCalculateBounds-->

```kotlin
// `.bounds()` function calculates the minimum bounding box
// of all geometries in the `geometry` column of a `GeoDataFrame`,
// returning it as an `Envelope`
val usa48Bounds: Envelope = usa48.bounds().also {
    // Use JTS API for in-place envelope expansion
    it.expandBy(1.0)
}
```

<!---END-->

And plot it with the polygon plot:

<!---FUN usaStatesPlotWithBounds-->

```kotlin
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
```

<!---END-->

![usaStatesPlotWithBounds](usaStatesPlotWithBounds.svg)

In addition, `geoRectangles` also works with polygons and multipolygon. In such cases, the bounding box of each geometry
will be calculated and used individually:

<!---FUN usaStatesPlotWithDefaultBounds-->

```kotlin
usa48.plot {
    geoMap()
    geoRectangles()
}
```

<!---END-->

![usaStatesPlotWithDefaultBounds](usaStatesPlotWithDefaultBounds.svg)

## Write GeoDataFrame

A `GeoDataFrame` can be saved to a file in both GeoJSON and Shapefile formats using the `GeoDataFrame.write..(filename)`
functions.

### GeoJSON

Let's save the modified GeoDataFrame containing US cities, which was initially in Shapefile format, to a GeoJSON file.


<!---FUN writeGeoJson1usaCitiesExportToGeoJson-->

```kotlin
usaCities.writeGeoJson("usa_cities.geojson")
```

<!---END-->

<!---FUN writeGeoJson2usaCitiesPlotFromGeoJson-->

```kotlin
GeoDataFrame.readGeoJson("usa_cities.geojson").plot { geoPoints() }
```

<!---END-->

![writeGeoJson2usaCitiesPlotFromGeoJson](writeGeoJson2usaCitiesPlotFromGeoJson.svg)

### Shapefile

Unlike GeoJSON, Shapefile supports **only one type of geometry**.

Let's save the GeoDataFrame containing the boundaries of US states, which was initially in GeoJSON format and included
both polygons and multipolygons, to a Shapefile. To do this, we will first cast all geometries to `MultiPolygon`.


<!---FUN writeShapefile1usaStatesExportToShapefile-->

```kotlin
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
    } 
}
    // All files comprising the Shapefile will be saved to
    // a directory named "usa_48" and will have the same base name
    .writeShapefile("usa_48")
```

<!---END-->

<!---FUN writeShapefile2usaStatesPlotFromShapefile-->

```kotlin
GeoDataFrame.readShapefile("usa_48/usa_48.shp").plot { geoMap() }
```

<!---END-->

![writeShapefile2usaStatesPlotFromShapefile](writeShapefile2usaStatesPlotFromShapefile.svg)
