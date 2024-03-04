package org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model

/**
 * Axis position.
 *
 * @property DEFAULT set axis to default position (bot for `X`, left for `Y`);
 * @property OPPOSITE set axis to opposite of the default position
 * (top for `X`, right for `Y`);
 * @property BOTH set axis on both (default and opposite) positions
 * (bot and top for `X`, left and right for `Y`).
 */
public enum class AxisPosition {
    DEFAULT, OPPOSITE, BOTH;
}
