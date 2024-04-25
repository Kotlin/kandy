package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for Bars layers.
 *
 * Bars layers are used to create bar charts, which are often used to represent categorical data.
 * The interface provides aesthetics such as `x`, `y`, `alpha`, `fillColor`,`width`,
 * and `borderLine` customization to allow for full customization of the bars.
 *
 * Required aesthetics for Bars are `X` and `Y`.
 */
public interface BarsContextInterface : WithBorderLine, WithX, WithY,
    WithAlpha, WithFillColor, WithWidth