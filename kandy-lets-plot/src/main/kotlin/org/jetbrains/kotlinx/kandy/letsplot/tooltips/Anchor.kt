package org.jetbrains.kotlinx.kandy.letsplot.tooltips

/**
 * Tooltips fixed position.
 */
public data class Anchor(val value: String) {
    public companion object {
        public val TOP_RIGHT: Anchor = Anchor("top_right")
        public val TOP_CENTER: Anchor = Anchor("top_center")
        public val TOP_LEFT: Anchor = Anchor("top_left")
        public val BOTTOM_RIGHT: Anchor = Anchor("bottom_right")
        public val BOTTOM_CENTER: Anchor = Anchor("bottom_center")
        public val BOTTOM_LEFT: Anchor = Anchor("bottom_left")
        public val MIDDLE_RIGHT: Anchor = Anchor("middle_right")
        public val MIDDLE_CENTER: Anchor = Anchor("middle_center")
        public val MIDDLE_LEFT: Anchor = Anchor("middle_left")
    }
}
