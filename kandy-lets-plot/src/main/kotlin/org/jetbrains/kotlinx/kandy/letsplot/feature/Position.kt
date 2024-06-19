/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/
@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.layerFeatures
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature

/**
 * Specifies the position adjustment for a layer.
 *
 * You can control how data points are positioned relative to each other using different
 * [Position] options like `identity`, `stack`, `dodge`, etc.
 *
 * @see [Position]
 */
public var LayerBuilder.position: Position
    get() = Position.Identity // todo
    set(pos) {
        layerFeatures[Position.FEATURE_NAME] = pos
    }

/**
 * Describes different strategies for positioning elements within a layer.
 *
 * Use [Position.identity], [Position.stack], [Position.dodge], [Position.jitter], [Position.nudge],
 * [Position.jitterDodge] to control the relative arrangement of elements.
 * TODO grouping
 */
public sealed class Position(public val name: String) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        // Methods for creating Position instances

        /**
         * Returns an [Identity] position adjustment.
         *
         * With `Identity`, no position adjustment will be done.
         *
         * @return [Identity] position adjustment object.
         */
        public fun identity(): Position = Identity

        /**
         * Returns a [Stack] position adjustment.
         *
         * With `Stack`, overlapping elements will be stacked on top of each other.
         *
         * @return [Stack] position adjustment object.
         */
        public fun stack(): Position = Stack

        /**
         * Returns a [Dodge] position adjustment.
         *
         * With `Dodge`, overlapping elements will be dodged side-to-side.
         *
         * @param width the dodging width, different from the individual element's width.
         * @return [Dodge] position adjustment object.
         */
        public fun dodge(width: Double? = null): Position = Dodge(width)

        /**
         * Returns a [Jitter] position adjustment.
         *
         * With `Jitter`, a small random offset will be applied to avoid overlap.
         *
         * @param width the amount of vertical jitter.
         * @param height the amount of horizontal jitter.
         * @return [Jitter] position adjustment object.
         */
        public fun jitter(width: Double? = null, height: Double? = null): Position = Jitter(width, height)

        /**
         * Returns a [Nudge] position adjustment.
         *
         * With `Nudge`, elements will be moved a fixed distance vertically and/or horizontally.
         *
         * @param x the vertical distance to move.
         * @param y the horizontal distance to move.
         * @return [Nudge] position adjustment object.
         */
        public fun nudge(x: Double? = null, y: Double? = null): Position = Nudge(x, y)

        /**
         * Returns a [JitterDodge] position adjustment.
         *
         * With `JitterDodge`, dodge and jitter adjustments will be combined.
         *
         * @param dodgeWidth the dodging width in the x-direction.
         * @param jitterWidth the jitter width in the x-direction.
         * @param jitterHeight the jitter height in the y-direction.
         * @return [JitterDodge] position adjustment object.
         */

        public fun jitterDodge(
            dodgeWidth: Double? = null, jitterWidth: Double? = null, jitterHeight: Double? = null
        ): Position = JitterDodge(dodgeWidth, jitterWidth, jitterHeight)

        public val FEATURE_NAME: FeatureName = FeatureName("POSITION")
    }

    /**
     * Does not adjust the position of the elements.
     */
    internal data object Identity : Position("identity")

    /**
     * Stacks elements on top of each other.
     */
    internal data object Stack : Position("stack")

    /**
     * Dodges elements side-to-side to avoid overlap.
     *
     * @param width the dodging width, different from the individual element's width.
     */
    internal data class Dodge(val width: Double? = null) : Position("dodge")

    /**
     * Adds a small random jitter to elements for avoiding overlap.
     *
     * @param width the amount of vertical jitter.
     * @param height the amount of horizontal jitter.
     */
    internal data class Jitter(val width: Double? = null, val height: Double? = null) : Position("jitter")

    /**
     * Moves elements a fixed distance vertically and horizontally.
     *
     * @param x The vertical distance to move.
     * @param y The horizontal distance to move.
     */
    internal data class Nudge(val x: Double? = null, val y: Double? = null) : Position("nudge")

    /**
     * Combines dodge and jitter adjustments.
     *
     * @param dodgeWidth the dodging width in the x-direction.
     * @param jitterWidth the jitter width in the x-direction.
     * @param jitterHeight the jitter height in the y-direction.
     */
    internal data class JitterDodge(
        val dodgeWidth: Double? = null, val jitterWidth: Double? = null, val jitterHeight: Double? = null,
    ) : Position("jitter_dodge")
}
