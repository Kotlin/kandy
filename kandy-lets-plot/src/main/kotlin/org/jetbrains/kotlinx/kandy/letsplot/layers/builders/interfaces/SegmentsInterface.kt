package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for segment layers.
 *
 * Segment layers are generally used to draw straight lines between points, given starting and ending coordinates.
 * The interface provides aesthetics like `color`, `alpha`,
 * `lineType`, `width`, `xBegin`, `yBegin`, `xEnd`, `yEnd`, `x`, and `y` for customization.
 *
 * Required aesthetics for segment layers are `xBegin`, `yBegin`, `xEnd`, and `yEnd`.
 */
public interface SegmentsInterface : WithColor, WithAlpha, WithLineType,
    WithWidthAsSize,
    WithXBegin, WithYBegin, WithXEnd, WithYEnd, WithXFree, WithYFree