package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithAlpha
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithLabel
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithX
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithY
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.Font

/**
 * Interface defining the necessary aesthetics and methods for text layers.
 *
 * Text layers are used to display text labels on plots, typically indicating data points or other elements.
 * The interface provides aesthetics like `x`, `y`, `alpha`, and `label` for customization.
 * Additionally, it contains a nested `FontContext` for font styling.
 *
 * Required aesthetics for text layers are `X`, `Y`, and `LABEL`.
 */
public interface TextBuilderInterface : WithX, WithY, WithAlpha, WithLabel {
    public val font: Font
}