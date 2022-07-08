package org.jetbrains.kotlinx.ggdsl.letsplot.position

/**
 * Position adjustment of this layer.
 *
 * @see [Position]
 */
var org.jetbrains.kotlinx.ggdsl.dsl.LayerContext.position: Position
    get() = Position.Identity // todo add backing property?
    set(pos) {
        features[POSITION_FEATURE_NAME] = pos
    }
