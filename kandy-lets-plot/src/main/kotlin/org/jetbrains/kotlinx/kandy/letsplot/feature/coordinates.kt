@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.dsl.internal.PlotBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.plotFeatures
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.PlotFeature

/**
 * Represents different types of coordinate systems for plotting data.
 */
public sealed interface Coordinates : PlotFeature {
    override val featureName: FeatureName
        get() = FEATURE_NAME

    public companion object {
        internal val FEATURE_NAME: FeatureName = FeatureName("COORDINATES")

        /**
         * Returns a `Coordinates` instance representing a Cartesian coordinate system.
         *
         * This coordinate system is the standard 2-dimensional coordinate system used in most plots,
         * where the horizontal axis represents X values and the vertical axis represents Y values.
         *
         * @return an instance of `Coordinates` configured as a Cartesian coordinate system.
         */
        public fun cartesian(): Coordinates = CartesianCoordinates

        /**
         * Returns a `Coordinates` instance representing a Cartesian coordinate system with a fixed aspect ratio.
         *
         * This coordinate system maintains a consistent proportional relationship between the X and Y axes,
         * controlled by the `ratio` parameter. The `ratio` specifies how many units on the y-axis
         * correspond to a single unit on the x-axis. When `ratio` is set to 1.0 (default), one unit on the x-axis
         * is equivalent to one unit on the y-axis, resulting in equal scaling for both axes.
         * A `ratio` greater than 1.0 will make units on the y-axis proportionally longer than those on the x-axis,
         * whereas a `ratio` less than 1.0 will make units on the x-axis proportionally longer.
         *
         * @param ratio the aspect ratio between the X and Y axes, where a value of 1.0 (default)
         * results in equal scaling for both axes.
         * @return a `Coordinates` instance configured as a Cartesian coordinate system with a fixed aspect ratio.
         */
        public fun cartesianFixed(ratio: Double = 1.0): Coordinates = CartesianFixedCoordinates(ratio)

        /**
         * Returns a `Coordinates` instance representing a Cartesian coordinate system with swapped axes.
         *
         * In this coordinate system, the x-axis is oriented vertically,
         * and the y-axis is oriented horizontally.
         *
         * @return a `Coordinates` instance with swapped axes.
         */
        public fun cartesianFlipped(): Coordinates = CartesianFlippedCoordinates

        /**
         * Returns a `Coordinates` instance representing a Cartesian coordinate system with swapped axes and a fixed aspect ratio.
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
        public fun cartesianFlippedFixed(ratio: Double = 1.0): Coordinates = CartesianFlippedFixedCoordinates(ratio)
    }
}

/**
 * Specifies the coordinate system used in the plot.
 *
 * The `coordinates` property allows the customization of the plot's coordinate system,
 * which defines how data points are mapped onto the plot's axes.
 * If the coordinate system is not explicitly specified,
 * it is automatically determined based on the data provided.
 *
 * Examples:
 * ```kotlin
 * plot {
 *    // Using the default Cartesian coordinate system
 *    coordinates = Coordinates.cartesian()
 *
 *    // Using a Cartesian coordinate system with a fixed aspect ratio
 *    coordinates = Coordinates.cartesianFixed(ratio = 1.5)
 *
 *    // Using a Cartesian coordinate system with flipped axes
 *    coordinates = Coordinates.cartesianFlipped()
 * }
 * ```
 */
public var PlotBuilder.coordinates: Coordinates?
    // TODO(https://github.com/Kotlin/kandy/issues/412)
    get() = null
    set(value) {
        value?.let { plotFeatures[Coordinates.FEATURE_NAME] = it }
    }

internal data object DefaultCoordinates : Coordinates
internal data object CartesianCoordinates : Coordinates
internal data class CartesianFixedCoordinates(val ratio: Double) : Coordinates
internal data object CartesianFlippedCoordinates : Coordinates
internal data class CartesianFlippedFixedCoordinates(val ratio: Double) : Coordinates
internal interface CustomCoordinates : Coordinates, ExternalLetsPlotFeature
