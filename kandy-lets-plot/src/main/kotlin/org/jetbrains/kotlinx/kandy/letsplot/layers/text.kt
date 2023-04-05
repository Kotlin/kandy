/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotGeom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.TextContext

@PublishedApi
internal val TEXT: LetsPlotGeom = LetsPlotGeom("text")

public inline fun LayerCollectorContext.text(block: TextContext.() -> Unit) {
    addLayer(TextContext(this).apply(block), TEXT)
}

/*
/**
 * Adds a new text layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][TextContextInterface.x]
 *  - [y][TextContextInterface.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [LayerPlotContext] (if they exist).
 *
 *  Non-positional:
 *  - [alpha][TextContextInterface.alpha] - this layer alpha, of the type [Double], mappable.
 *  - [label][TextContextInterface.label] - text label, of the type [String], mappable.
 *  - [angle][TextContextInterface.angle] - text angle, of the type [Double], mappable.
 *  - [horizontalJustification][TextContextInterface.horizontalJustification] -
 *  text horizontal justification, of type [HorizontalJustification].
 *  - [verticalJustification][TextContextInterface.verticalJustification] -
 *  text vertical justification, of type [VerticalJustification].
 *  - [font.color][FontSubContextInterface.color] - font color.
 *  - [font.size][FontSubContextInterface.size] - font size.
 *  - [font.family][FontSubContextInterface.family] - font family.
 *  - [font.face][FontSubContextInterface.face] - font face.
 *
 * ```
 * text {
 *    x(time) // mapping from `time` column to `X` with default scale.
 *    font.color(Color.BLUE) // setting of constant `color` value
 * }
 * ```
 */
public inline fun LayerCollectorContextImmutable.text(block: TextContextImmutable.() -> Unit) {
    addLayer(TextContextImmutable(this).apply(block), TEXT)
}

/**
 * Adds a new text layer.
 *
 * Creates a context in which you can create bindings using aesthetic attribute properties invocation.
 * In this context, you can use mutable mappings - that is, do mapping and scaling with iterables.
 *
 *  ### Aesthetic attributes:
 *
 *  Positional:
 *
 *  - [ x][TextContextInterface.x]
 *  - [y][TextContextInterface.y]
 *
 *  Initial mappings to positional attributes are inherited from the parent [LayerPlotContext] (if they exist).
 *
 *  Non-positional:
 *  - [alpha][TextContextInterface.alpha] - this layer alpha, of the type [Double], mappable.
 *  - [label][TextContextInterface.label] - text label, of the type [String], mappable.
 *  - [angle][TextContextInterface.angle] - text angle, of the type [Double], mappable.
 *  - [horizontalJustification][TextContextInterface.horizontalJustification] -
 *  text horizontal justification, of type [HorizontalJustification].
 *  - [verticalJustification][TextContextInterface.verticalJustification] -
 *  text vertical justification, of type [VerticalJustification].
 *  - [font.color][FontSubContextInterface.color] - font color.
 *  - [font.size][FontSubContextInterface.size] - font size.
 *  - [font.family][FontSubContextInterface.family] - font family.
 *  - [font.face][FontSubContextInterface.face] - font face.
 *
 * ```
 * text {
 *    label(listOf("tt", "vv", "egb")) // mapping from list to `label`.
 *    font.color(Color.BLUE) // setting of constant `color` value
 * }
 * ```
 */
public inline fun LayerCollectorContextMutable.text(block: TextContextMutable.() -> Unit) {
    addLayer(TextContextMutable(this).apply(block), TEXT)
}


 */