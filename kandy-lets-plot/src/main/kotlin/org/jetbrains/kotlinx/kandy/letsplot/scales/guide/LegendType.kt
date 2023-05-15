package org.jetbrains.kotlinx.kandy.letsplot.scales.guide

public sealed interface LegendType {
    /**
     * Do not display the legend.
     */
    public object None : LegendType

    /**
     * Legend guide.
     * Legend type guide shows key (i.e., geoms) mapped onto values.
     *
     * @param nRow number of rows in legend's guide.
     * @param nCol number of columns in legend's guide.
     * @param byRow type of output: by row (default), or by column.
     */
    public data class DiscreteLegend(
        val nRow: Int? = null,
        val nCol: Int? = null,
        val byRow: Boolean? = null
    ) : LegendType

    /**
     * Continuous color bar guide.
     * Color bar guide shows continuous color scales mapped onto values.
     * @param barWidth color bar width.
     * @param barHeight color bar height.
     * @param nBin number of bins in color bar.
     */
    public data class ColorBar(
        val barWidth: Double? = null,
        val barHeight: Double? = null,
        val nBin: Int? = null
    ) : LegendType
}
