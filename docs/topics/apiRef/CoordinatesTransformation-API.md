# Coordinates Transformation

<tldr>
<p> <format style="bold" color="#A020F0">var</format> PlotBuilder. <format style="bold" color="GoldenRod">coordinatesTransformation</format>: <a href="#coordinates_transformation_class"><format style="bold" color="CadetBlue">CoordinatesTransformation?</format></a> </p>
</tldr>

Defines the coordinate system transformation applied to the plot.

The `coordinatesTransformation` property enables customization of how data points are projected onto the plot's axes. If
not explicitly set, the transformation is automatically selected based on the provided data and configured layers.

## `CoordinatesTransformation` {id="coordinates_transformation_class"}

A class that represents plotting coordinates system transformation.

* `CoordinatesTransformation.cartesian()` — a Cartesian coordinate system transformation.
  This coordinate system is the standard 2-dimensional coordinate system used in most plots,
  where the horizontal axis represents X values and the vertical axis represents Y values.
  Default for most layers.
* `CoordinatesTransformation.cartesianFixed(ratio: Double = 1.0)` — a Cartesian coordinate system
  with a fixed aspect ratio transformation.
  This coordinate system maintains a consistent proportional relationship between the X and Y axes,
  controlled by the `ratio` parameter. The `ratio` specifies how many units on the y-axis
  correspond to a single unit on the x-axis. When `ratio` is set to 1.0 (default), one unit on the x-axis
  is equivalent to one unit on the y-axis, resulting in equal scaling for both axes.
  A `ratio` greater than 1.0 will make units on the y-axis proportionally longer than those on the x-axis,
  whereas a `ratio` less than 1.0 will make units on the x-axis proportionally longer.
  Default for [`tiles`](https://kotlin.github.io/kandy/tiles-api.html) layer.
* `CoordinatesTransformation.cartesianFlipped()` — a Cartesian coordinate system with
  swapped axes transformation.
  In this coordinate system, the x-axis is oriented vertically,
  and the y-axis is oriented horizontally.
* `CoordinatesTransformation.cartesianFlippedFixed(ratio: Double = 1.0)` — a Cartesian coordinate system
  with swapped axes and a fixed aspect ratio transformation.
  This coordinate system flips the orientation of the axes, so the x-axis is oriented vertically,
  and the y-axis is oriented horizontally, while maintaining a consistent proportional relationship
  between these axes, controlled by the `ratio` parameter.
  The `ratio` specifies how many units on the new y-axis (originally the x-axis) correspond to a single unit
  on the new x-axis (originally the y-axis). When `ratio` is set to 1.0 (default), one unit on the new x-axis
  is equivalent to one unit on the new y-axis, resulting in equal scaling for both axes.
  A `ratio` greater than 1.0 will make units on the new y-axis proportionally longer than those on the new x-axis,
  whereas a `ratio` less than 1.0 will make units on the new x-axis proportionally longer.
  
## Examples

```kotlin
plot {
    ...
    // Using the default Cartesian coordinate system transformation
    coordinatesTransformation = CoordinatesTransformation.cartesian()
    // Using a Cartesian coordinate system with a fixed aspect ratio transformation
    coordinatesTransformation = CoordinatesTransformation.cartesianFixed(ratio = 1.5)
    // Using a Cartesian coordinate system with flipped axes transformation 
    coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
}
```
