/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_BEGIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_END
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_BEGIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_END
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.SEGMENT

/**
 * Interface defining the necessary aesthetics and methods for segment layers.
 *
 * Segment layers are generally used to draw straight lines between points, given starting and ending coordinates.
 * The interface provides aesthetics like `color`, `alpha`,
 * `lineType`, `width`, `xBegin`, `yBegin`, `xEnd`, `yEnd`, `x`, and `y` for customization.
 *
 * Required aesthetics for segment layers are `xBegin`, `yBegin`, `xEnd`, and `yEnd`.
 */
public interface SegmentsInterface : LayerContextInterface, WithColor, WithAlpha, WithLineType,
    WithWidthAsSize,
    WithXBegin, WithYBegin, WithXEnd, WithYEnd, WithXFree, WithYFree {

    /**
     * Gets the Geom object specific to **segment** layers.
     *
     * @return the [Geom] object for **segment**.
     */
    override val geom: Geom
        get() = SEGMENT

    /**
     * Gets the set of required aesthetics for **segment** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X_BEGIN, Y_BEGIN, X_END, Y_END)
}

/**
 * Context class for managing segment layers.
 *
 * This class provides the context in which segment layers can be configured.
 * It inherits from [LayerContext] and implements the [SegmentsInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class SegmentsContext(parent: LayerCollectorContext) : LayerContext(parent), SegmentsInterface
