# Geo plotting

Kandy-Geo adds extensions which allow plotting a `GeoDataFrame` — a [DataFrame](https://github.com/Kotlin/dataframe)
-based structure for geospatial
datasets. [All the plotting principles and features](https://kotlin.github.io/kandy/quick-start-guide.html) here are the
same as Kandy, the only difference is that you don't have to perform positional mapping — instead geometries will be
automatically mapped to Kandy layers.

## Kandy-geo and GeoDataFrame Usage

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

All classes for the aforementioned geometries are provided in JTS and inherit from the base class `Geometry`. *
*`GeoDataFrame`** is a wrapper around a standard `DataFrame` with a `geometry` column of type `Geometry`, enabling
convenient handling of geospatial datasets.

## Reading GeoDataFrame

Currently, the GeoDataFrame supports two of the most popular formats: Shapefile and GeoJSON. These formats can be read
into a `GeoDataFrame` using the corresponding `GeoDataFrame.read..()` functions. Each of these functions returns a
`GeoDataFrame`.

### GeoJSON {id="geojson_read"}

[GeoJSON](examples/notebooks/lets-plot/guides/usa_48) is a widely used format for encoding geographic data structures.
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

<!---FUN testCase_28d053fc-->

<!---END-->

We can directly access the underlying DataFrame to take a closer look at its contents:

<!---FUN testCase_824f0b34-->

<!---END-->

This DataFrame **is required** to have a geometry column of type `org.locationtech.jts.geom.Geometry`:



<!---FUN testCase_05fa7b2c-->

<!---END-->

We can also check the exact types of these geometries:

<!---FUN testCase_203024af-->

<!---END-->

As expected, these are `Polygon` and `MultiPolygon`.

The `GeoDataFrame` also contains a `.crs` field for the coordinate reference system (CRS). In GeoJSON, this field is not
explicitly defined* and is read as `null`. If this field is not explicitly set in the GeoDataFrame, it is assumed by
default to
use [WGS84]([https://gisgeography.com/wgs84-world-geodetic-system/](https://gisgeography.com/wgs84-world-geodetic-system/)) —
the standard CRS for working with geospatial data.

\* *According to the [GeoJSON specification](https://datatracker.ietf.org/doc/html/rfc7946#section-4), all coordinates
are defined in WGS84. In the future, we may remove the nullability of the `crs` field, and WGS84 will be explicitly set
as the CRS when reading GeoJSON files.*



<!---FUN testCase_f22b3c21-->

<!---END-->

### Shapefile {id="shapefile_read"}

[Shapefile]() is a popular geospatial vector data format developed by ESRI. It stores geometric features such as points,
lines, and polygons, along with their attributes, across multiple files. A Shapefile requires at least three parts:
`.shp` (geometry), `.shx` (spatial index), and `.dbf` (attributes), and it typically uses a defined coordinate reference
system.

To load a Shapefile, you need to specify the path to the file with the `.shp` extension. The other required files must
be in the same directory and share the same base name.

Let's load a Shapefile with the most populated cities in the world:


<!---FUN testCase_70575e82-->

<!---END-->

Take a look inside the DataFrame:

<!---FUN testCase_655e3182-->

<!---END-->

This `GeoDataFrame` contains only `Point` geometries:


<!---FUN testCase_8e3f18c7-->

<!---END-->

And has explicitly specified CRS:

<!---FUN testCase_768bdc9f-->

<!---END-->

## Plot

Geoplotting in Kandy is not significantly different from usual plotting. The main distinction is that you need to
provide the aforementioned geometries instead of specifying positional mappings.

To facilitate this, Kandy-geo introduces *geo layers*, which, unlike regular layers, accept geometries. These can be
provided as DataFrame columns, `Iterable`, or single instances. If a layer is built in the context of a `GeoDataFrame`
dataset, it is not necessary to explicitly specify the geometry, as the `geometry` column will be used by default.

### geoPolygon

The `geoPolygon()` adds a layer of polygons constructed using `Polygon` and `MultiPolygon` geometries.

Let's plot US states from `usaStates`:

<!---FUN testCase_de9ead75-->

<!---END-->

The customization process for such a layer is no different from a regular one. The function optionally opens a block
where you can configure all polygon aesthetic attributes as usual using mappings and settings. For example, you can
color each state by mapping the `name` column to `fillColor` and customize the `borderLine` as shown below:


<!---FUN testCase_01e9b60f-->

<!---END-->

#### Mercator coordinates transformation

The Mercator projection is widely used for map visualizations because it preserves angles and shapes locally, making it
ideal for navigation and geographical applications. It is particularly useful for rendering maps on flat surfaces, such
as screens or paper. The Mercator projection is compatible with coordinates in the WGS84 coordinate system, as it uses
latitude and longitude values to project the curved surface of the Earth onto a 2D plane. In this projection, only the
axes of the plot are transformed, while the actual values of the points remain unchanged.

<!---FUN testCase_f68b82bc-->

<!---END-->

#### geoMap

`geoMap()` is a basically `geoPolygon()` but it also applies coordinates transformation based on provided
`CoordinateReferenceSystem` (`GeoDataFrame.crs`). Now only _WGS84_ is supported (where the mercator projection is
applied by default).

<!---FUN testCase_2db26825-->

<!---END-->

When the Mercator projection is applied, we can still set axis limits as usual. However, there are inherent boundaries
at 180 and 90 degrees due to the nature of geographic coordinates.

<!---FUN testCase_281e7247-->

<!---END-->

### geoPoints

The `geoPoints()` adds a layer of points constructed using `Point` and `MultiPoint` geometries.

Let's add `worldCities` points over `usaStates` polygons:

<!---FUN testCase_99163515-->

<!---END-->

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

<!---FUN testCase_ac5a73e9-->

<!---END-->

<!---FUN testCase_ad2f55fa-->

<!---END-->

Now, let's create a GeoDataFrame `usaCities` containing only the cities located within the United States. To avoid over
plotting, we will select the 30 most populous cities. For this, we will modify `worldCities`:

<!---FUN testCase_9104ce15-->

<!---END-->

Now we can visualize the result by overlaying the points representing these cities on the polygons of the states (as
above):

<!---FUN testCase_3d30dae8-->

<!---END-->

As you can see, the map of the US is significantly stretched by distant territories such as Puerto Rico, Hawaii, and
Alaska. We can remove these regions, keeping only the continental part (48 states):


<!---FUN testCase_92e1aea0-->

<!---END-->

### Geometry operations

Another, more elegant way to improve the appearance of the US map is to scale and reposition these polygons, making the
plot more compact.

The DataFrame-Geo library provides Kotlin-style extensions for JTS geometries. For instance, `Geometry.translate(x, y)`
shifts a geometry by a specified vector, while `Geometry.scaleAroundCenter(factor)` scales a geometry relative to its
centroid.


<!---FUN testCase_3083dbc6-->

<!---END-->

An example of the states maps with their centroids:

<!---FUN testCase_ac55e24a-->

<!---END-->

#### Datasets Join

In geo-plotting, separate datasets are often used—one containing the geometries and others with specific data. To
combine them, you can [join](https://kotlin.github.io/dataframe/join.html) them using `modify`. Let's load a `DataFrame`
with the results of the 2024 US presidential election:


<!---FUN testCase_41973f84-->

<!---END-->

And join it to the US states `GeoDataFrame`:

<!---FUN testCase_42b02240-->

<!---END-->

Now we can create a geo plot with a color scale based on state election results:

<!---FUN testCase_da868872-->

<!---END-->

### Applying New CRS

A new coordinate system can be applied to a GeoDataFrame by projecting all geometries into it (note that this is not
always possible, so proceed with caution).

The *CONUS (Conterminous United States) Albers projection* is a widely used coordinate reference system tailored for the
contiguous United States (48 states). It is an equal-area projection, meaning it preserves area proportions while
slightly distorting shapes and distances. This projection is ideal for visualizing geographic features across large
regions of the continental US.

Let's apply the CONUS Albers projection to the state polygons:



<!---FUN testCase_dd473136-->

<!---END-->

<!---FUN testCase_2fd465cb-->

<!---END-->

### geoPath

The `geoPath()` adds a layer of a path constructed using `LineString` and `MultiLineString` geometries.

The following function constructs the shortest path on the Earth's surface, known as a [*great-circle
line*](https://en.wikipedia.org/wiki/Great_circle). A great-circle line represents the shortest distance between two
points on a sphere, following the curvature of the Earth. The path is approximated using a `LineString` with a specified
number of points `n` for precision.


<!---FUN testCase_faabf8ea-->

<!---END-->

This convenient function find city in `usaCities` by name and returns its geometry (point):

<!---FUN testCase_8bfec06d-->

<!---END-->

Use it to take points of New York and Los Angeles:

<!---FUN testCase_d94f55ff-->

<!---END-->

Count the shortest path between them:

<!---FUN testCase_443f759b-->

<!---END-->

Now, let's plot this curve using `geoPath`, overlaying it on top of the state polygons and highlighting the points
corresponding to the cities:

<!---FUN testCase_1be9a60f-->

<!---END-->

### geoRectangles

The `geoRectangles()` adds a layer of rectangles constructed using `Envelope`. The `Envelope` class represents a
rectangular region in the coordinate space, defined by its minimum and maximum coordinates. It is commonly used for
bounding boxes, spatial indexing, and efficient geometric calculations.

Let's get `usa48` common bounding box:

<!---FUN testCase_506a3e01-->

<!---END-->

And plot it with the polygon plot:

<!---FUN testCase_1c228bce-->

<!---END-->

In addition, `geoRectangles` also works with polygons and multipolygon. In such cases, the bounding box of each geometry
will be calculated and used individually:

<!---FUN testCase_e49d3248-->

<!---END-->

## Write GeoDataFrame

A `GeoDataFrame` can be saved to a file in both GeoJSON and Shapefile formats using the `GeoDataFrame.write..(filename)`
functions.

### GeoJSON

Let's save the modified GeoDataFrame containing US cities, which was initially in Shapefile format, to a GeoJSON file.


<!---FUN testCase_30c07ac6-->

<!---END-->

<!---FUN testCase_0a9c6018-->

<!---END-->

### Shapefile

Unlike GeoJSON, Shapefile supports **only one type of geometry**.

Let's save the GeoDataFrame containing the boundaries of US states, which was initially in GeoJSON format and included
both polygons and multipolygons, to a Shapefile. To do this, we will first cast all geometries to `MultiPolygon`.


<!---FUN testCase_6dbaf55a-->

<!---END-->

<!---FUN testCase_65ed3d55-->

<!---END-->

