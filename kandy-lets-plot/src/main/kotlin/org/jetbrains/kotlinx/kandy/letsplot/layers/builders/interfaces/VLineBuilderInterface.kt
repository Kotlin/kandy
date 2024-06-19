package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface for defining the necessary aesthetics and methods for vertical line layers.
 *
 * Vertical line layers are useful for indicating specific values along the X-axis.
 * This interface provides the required aesthetics like `xIntercept`,
 * `alpha`, `color`, `width`, and `type` for customization.
 *
 * The set of required aesthetics for vertical line layers includes `X_INTERCEPT`.
 */
public interface VLineBuilderInterface : WithXIntercept, WithAlpha, WithColor,
    WithWidthAsSize, WithType, WithYFree