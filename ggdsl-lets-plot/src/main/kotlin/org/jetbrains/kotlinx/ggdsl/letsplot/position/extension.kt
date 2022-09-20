package org.jetbrains.kotlinx.ggdsl.letsplot.position

/**
 * Position adjustment of this layer.
 *
 * @see [Position]
 */
public var org.jetbrains.kotlinx.ggdsl.dsl.LayerContext.position: Position
    get() = Position.Identity // todo add backing property?
    set(pos) {
        features[Position.FEATURE_NAME] = pos
    }
