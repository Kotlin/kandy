/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.position

import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

public sealed class Position private constructor(public val name: String) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("POSITION")
    }

    /**
     * Don't adjust position.
     */
    public object Identity : Position("identity")

    /**
     * Stack overlapping objects on top of each another
     */
    public object Stack : Position("stack")

    /**
     * Dodge overlapping objects side-to-side.
     *
     * @param width the dodging width, when different to the width of the individual elements.
     */
    public data class Dodge(val width: Number? = null) : Position("dodge")

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
    public data class Jitter(val width: Number? = null, val height: Number? = null) : Position("jitter")

    /**
     * Nudge points a fixed distance.
     *
     * @param x the amount of vertical distance to move.
     * @param y the amount of horizontal distance to move.
     */
    public data class Nudge(val x: Number? = null, val y: Number? = null) : Position("nudge")

    /**
     * Simultaneously, dodge and jitter.
     *
     * @param dodgeWidth the amount to dodge in the x direction.
     * @param jitterWidth the degree of jitter in the x direction.
     * @param jitterHeight the degree of jitter in the y direction.
     */
    public data class JitterDodge(
        val dodgeWidth: Number? = null,
        val jitterWidth: Number? = null,
        val jitterHeight: Number? = null,
    ) : Position("jitter_dodge")
}
