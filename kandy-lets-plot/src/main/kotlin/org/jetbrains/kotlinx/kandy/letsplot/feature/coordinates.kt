@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.dsl.internal.PlotBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.plotFeatures
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

/**
 * Represents different types of coordinate system transformations for plotting data.
 */
public sealed interface CoordinatesTransformation : PlotFeature {
    override val featureName: FeatureName
        get() = FEATURE_NAME

    public companion object {
        internal val FEATURE_NAME: FeatureName = FeatureName("COORDINATES")

        /**
         * Returns a `CoordinatesTransformation` instance representing a Cartesian coordinate system transformation.
         *
         * This coordinate system is the standard 2-dimensional coordinate system used in most plots,
         * where the horizontal axis represents X values and the vertical axis represents Y values.
         *
         * Default for most layers.
         *
         * @return an instance of `Coordinates` configured as a Cartesian coordinate system.
         */
        public fun cartesian(): CoordinatesTransformation = CartesianCoordinatesTransformation

        /**
         * Returns a `CoordinatesTransformation` instance representing a Cartesian coordinate system
         * with a fixed aspect ratio transformation.
         *
         * This coordinate system maintains a consistent proportional relationship between the X and Y axes,
         * controlled by the `ratio` parameter. The `ratio` specifies how many units on the y-axis
         * correspond to a single unit on the x-axis. When `ratio` is set to 1.0 (default), one unit on the x-axis
         * is equivalent to one unit on the y-axis, resulting in equal scaling for both axes.
         * A `ratio` greater than 1.0 will make units on the y-axis proportionally longer than those on the x-axis,
         * whereas a `ratio` less than 1.0 will make units on the x-axis proportionally longer.
         *
         * Default for `tiles` layer.
         *
         * @param ratio the aspect ratio between the X and Y axes, where a value of 1.0 (default)
         * results in equal scaling for both axes.
         * @return a `Coordinates` instance configured as a Cartesian coordinate system with a fixed aspect ratio.
         */
        public fun cartesianFixed(ratio: Double = 1.0): CoordinatesTransformation =
            CartesianFixedCoordinatesTransformation(ratio)

        /**
         * Returns a `CoordinatesTransformation` instance representing a Cartesian coordinate system with
         * swapped axes transformation.
         *
         * In this coordinate system, the x-axis is oriented vertically,
         * and the y-axis is oriented horizontally.
         *
         * @return a `Coordinates` instance with swapped axes.
         */
        public fun cartesianFlipped(): CoordinatesTransformation = CartesianFlippedCoordinatesTransformation

        /**
         * Returns a `CoordinatesTransformation` instance representing a Cartesian coordinate system
         * with swapped axes and a fixed aspect ratio transformation.
         *
         * This coordinate system flips the orientation of the axes, so the x-axis is oriented vertically,
         * and the y-axis is oriented horizontally, while maintaining a consistent proportional relationship
         * between these axes, controlled by the `ratio` parameter.
         * The `ratio` specifies how many units on the new y-axis (originally the x-axis) correspond to a single unit
         * on the new x-axis (originally the y-axis). When `ratio` is set to 1.0 (default), one unit on the new x-axis
         * is equivalent to one unit on the new y-axis, resulting in equal scaling for both axes.
         * A `ratio` greater than 1.0 will make units on the new y-axis proportionally longer than those on the new x-axis,
         * whereas a `ratio` less than 1.0 will make units on the new x-axis proportionally longer.
         *
         * @param ratio the aspect ratio between the swapped X and Y axes, where a value of 1.0 (default)
         * results in equal scaling for both axes after flipping.
         * @return a `Coordinates` instance configured as a Cartesian coordinate system with swapped axes and a fixed aspect ratio.
         */
        public fun cartesianFlippedFixed(ratio: Double = 1.0): CoordinatesTransformation =
            CartesianFlippedFixedCoordinatesTransformation(ratio)
    }
}

/**
 * Specifies the coordinate system transformation used in the plot.
 *
 * The `coordinatesTransformation` property allows the customization of the plot's coordinate system transformation,
 * which defines how data points are mapped onto the plot's axes.
 * If the coordinate system transformation is not explicitly specified,
 * it is automatically determined based on the data provided and layers used.
 *
 * Examples:
 * ```kotlin
 * plot {
 *    // Using the default Cartesian coordinate system transformation
 *    coordinatesTransformation = CoordinatesTransformation.cartesian()
 *
 *    // Using a Cartesian coordinate system with a fixed aspect ratio transformation
 *    coordinatesTransformation = CoordinatesTransformation.cartesianFixed(ratio = 1.5)
 *
 *    // Using a Cartesian coordinate system with flipped axes transformation
 *    coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
 * }
 * ```
 */
public var PlotBuilder.coordinatesTransformation: CoordinatesTransformation?
    // TODO(https://github.com/Kotlin/kandy/issues/412)
    get() = null
    set(value) {
        value?.let { plotFeatures[CoordinatesTransformation.FEATURE_NAME] = it }
    }

internal data object DefaultCoordinatesTransformation : CoordinatesTransformation
internal data object CartesianCoordinatesTransformation : CoordinatesTransformation
internal data class CartesianFixedCoordinatesTransformation(val ratio: Double) : CoordinatesTransformation
internal data object CartesianFlippedCoordinatesTransformation : CoordinatesTransformation
internal data class CartesianFlippedFixedCoordinatesTransformation(val ratio: Double) : CoordinatesTransformation
internal interface CustomCoordinatesTransformation : CoordinatesTransformation, ExternalLetsPlotFeature
