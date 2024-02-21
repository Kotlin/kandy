package org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model

/**
 * Axis position.
 *
 * `DEFAULT` - default axis position (bot for `x`, left for `y`);
 * `INVERSE` - inverse axis position (top for `x`, right for `y`);
 * `BOTH` - set both (default and inverse) axis positions.
 */
public enum class AxisPosition {
    DEFAULT, INVERSE, BOTH;
}