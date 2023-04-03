package org.jetbrains.kotlinx.kandy.letsplot.multiplot.model

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.ir.Plot

//@Serializable
public data class PlotBunch(
    val items: List<Item>
) {
    //@Serializable
    public class Item(
        public val plot: Plot,
        public val x: Int,
        public val y: Int,
        public val width: Int?,
        public val height: Int?
    )
}