/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature

/**
 * Position adjustment of this layer.
 *
 * @see [Position]
 */
public var LayerContextInterface.position: Position
    get() = Position.Identity // todo
    set(pos) {
        layerFeatures[Position.FEATURE_NAME] = pos
    }

/**
 * The relative arrangement of groups within a layer. TODO grouping
 */
public sealed class Position private constructor(public val name: String) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public fun identity(): Position = Identity
        public fun stack(): Position = Stack
        public fun dodge(width: Double? = null): Position = Dodge(width)
        public fun jitter(width: Double? = null, height: Double? = null): Position = Jitter(width, height)
        public fun nudge(x: Double? = null, y: Double? = null): Position = Nudge(x, y)
        public fun jitterDodge(
            dodgeWidth: Double? = null,
            jitterWidth: Double? = null,
            jitterHeight: Double? = null
        ): Position = JitterDodge(dodgeWidth, jitterWidth, jitterHeight)

        public val FEATURE_NAME: FeatureName = FeatureName("POSITION")
    }

    /**
     * Don't adjust position.
     */
    internal object Identity : Position("identity")

    /**
     * Stack overlapping objects on top of each another
     */
    internal object Stack : Position("stack")

    /**
     * Dodge overlapping objects side-to-side.
     *
     * @param width the dodging width, when different to the width of the individual elements.
     */
    internal data class Dodge(val width: Double? = null) : Position("dodge")

    /**
     * Jitter points to avoid overplotting.
     *
     * @param width the amount of vertical jitter.
     * The jitter is added in both positive and negative directions,
     * so the total spread is twice the value specified here.
     * @param height the amount of horizontal jitter.
     * The jitter is added in both positive and negative directions,
     * so the total spread is twice the value specified here.
     */
    internal data class Jitter(val width: Double? = null, val height: Double? = null) : Position("jitter")

    /**
     * Nudge points a fixed distance.
     *
     * @param x the amount of vertical distance to move.
     * @param y the amount of horizontal distance to move.
     */
    internal data class Nudge(val x: Double? = null, val y: Double? = null) : Position("nudge")

    /**
     * Simultaneously, dodge and jitter.
     *
     * @param dodgeWidth the amount to dodge in the x direction.
     * @param jitterWidth the degree of jitter in the x direction.
     * @param jitterHeight the degree of jitter in the y direction.
     */
    internal data class JitterDodge(
        val dodgeWidth: Double? = null,
        val jitterWidth: Double? = null,
        val jitterHeight: Double? = null,
    ) : Position("jitter_dodge")
}
