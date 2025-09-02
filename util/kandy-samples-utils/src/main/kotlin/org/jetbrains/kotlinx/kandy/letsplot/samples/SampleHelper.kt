@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.FormattedFrame
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.io.toStandaloneHtml
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.letsplot.feature.Layout
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotGrid
import org.jetbrains.kotlinx.kandy.letsplot.style.*
import org.jetbrains.kotlinx.kandy.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.kandy.letsplot.translator.wrap
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.awt.plot.PlotSvgExport
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.intern.toSpec
import org.junit.Rule
import org.junit.rules.TestName
import java.io.File

/**
 * Base test class with methods configured for saving plots and dataframes samples.
 * Allows saving samples through tests running.
 *
 * This class is designed to facilitate saving visual outputs such as [Plot], [PlotGrid], and [PlotBunch],
 * as well as dataframe-like structures [DataFrame] and [GroupBy].
 *
 * It provides methods for saving plots as SVG files and for saving dataframes as HTML files for Writerside docs.
 * These methods provide default configurations for plot scaling and preview sizes, as well as
 * duplicating images for dark themes.
 *
 * Replace IDS inside the XML (SVG/HTML) with the static ones, allowing to track changes after test rerun.
 *
 * @param sampleName      Identifier of the sample used to name output folders.
 * @param subFolder       Subdirectory under the root folders where sample files are placed. Defaults to `"samples"`.
 * @param imagesFolder    Base directory for Writerside images in the docs. Defaults to `"../docs/images"`.
 * @param resourcesFolder Base directory for Writerside resources in the docs. Defaults to `"../docs/resources"`.
 */
public abstract class SampleHelper(
    sampleName: String,
    subFolder: String = "samples",
    imagesFolder: String = "../docs/images",
    resourcesFolder: String = "../docs/resources"
) {

    @JvmField
    @Rule
    public val testName: TestName = TestName()

    private val pathToImageFolder = "$imagesFolder/$subFolder/$sampleName"

    private val pathToResourceFolder = "$resourcesFolder/$subFolder/$sampleName"

    private val darkColor = Color.hex("#19191c")

    init {
        File(pathToImageFolder).mkdirs()
        File(pathToResourceFolder).mkdirs()
    }

    private val defaultWidth = 600
    private val defaultHeight = 400
    private val previewSize = ggsize(defaultWidth, defaultHeight)
    private val fixedWidth = 705

    /**
     * Saves the current [Plot] as an SVG file in both default and dark mode themes.
     *
     * The method generates two SVG files:
     * 1. A default theme SVG file.
     * 2. A dark mode theme SVG file by altering the plot's theme.
     *
     * @param savePreview Boolean flag that indicates whether to also generate and save preview versions
     * of the SVG files. When `true`, additional preview files are created with "preview_" prefixed to the file name.
     */
    public fun Plot.savePlotSVGSample(savePreview: Boolean = false) {
        val name = testName.methodName.replace("_dataframe", "")
        saveAsSVG(name, savePreview)
        this.changeThemeToDarkMode()
        saveAsSVG("${name}_dark", savePreview)
    }

    /**
     * Saves the current [PlotGrid] as SVG files in both default and dark mode themes.
     *
     * The method generates two SVG files for the plot grid:
     * 1. A default theme SVG file.
     * 2. A dark mode theme SVG file by altering the plots' themes.
     *
     * @param savePreview Boolean flag that indicates whether to also generate and save preview versions
     * of the SVG files. When `true`, additional preview files are created with "preview_" prefixed to the file name.
     * @param scaling Boolean flag that determines whether the SVG output should scale to an appropriate size.
     * If `true`, the method scales the output appropriately; otherwise, it does not.
     */
    public fun PlotGrid.savePlotSVGSample(savePreview: Boolean = false, scaling: Boolean = true) {
        val name = testName.methodName.replace("_dataframe", "")
        saveAsSVG(name, savePreview, scaling)
        plots.forEach {
            it ?: return
            it.changeThemeToDarkMode()
        }
        saveAsSVG("${name}_dark", savePreview, scaling)
    }

    /**
     * Saves the current [PlotBunch] as SVG files in both default and dark mode themes.
     *
     * The method generates two SVG files for the plot grid:
     * 1. A default theme SVG file.
     * 2. A dark mode theme SVG file by altering the plots' themes.
     *
     * @param savePreview Boolean flag that indicates whether to also generate and save preview versions
     * of the SVG files. When `true`, additional preview files are created with "preview_" prefixed to the file name.
     * @param scaling Boolean flag that determines whether the SVG output should scale to an appropriate size.
     * If `true`, the method scales the output appropriately; otherwise, it does not.
     */
    public fun PlotBunch.savePlotSVGSample() {
        val name = testName.methodName.replace("_dataframe", "")
        saveAsSVG(name)
        this.items.forEach {
            it.plot.changeThemeToDarkMode()
        }
        saveAsSVG("${name}_dark")
    }

    /**
     * Saves this [FormattedFrame] as HTML.
     */
    public fun FormattedFrame<*>.saveDfHtmlSample() {
        val name = testName.methodName.replace("_dataframe", "")
        val dfHtml = df.toStandaloneHtml(
            configuration = getDisplayConfiguration(SamplesDisplayConfiguration),
            getFooter = WritersideFooter
        ) + WritersideStyle
        // TODO fix static ids
        val htmlWithStaticIDs = dfHtml.toString() // replaceIdsWithStatic(dfHtml.toString())
        File(pathToResourceFolder, "$name.html").writeText(htmlWithStaticIDs)
    }

    /**
     * Saves this [DataFrame] as HTML.
     */
    public fun DataFrame<*>.saveDfHtmlSample() {
        val name = testName.methodName.replace("_dataframe", "")
        val dfHtml = this.toStandaloneHtml(
            configuration = SamplesDisplayConfiguration,
            getFooter = WritersideFooter
        ) + WritersideStyle
        // TODO fix static ids
        val htmlWithStaticIDs = replaceIdsWithStaticDataFrame(dfHtml.toString()) // replaceIdsWithStatic(dfHtml.toString())
        File(pathToResourceFolder, "$name.html").writeText(htmlWithStaticIDs)
    }

    /**
     * Saves this [GroupBy] as HTML.
     */
    public fun GroupBy<*, *>.saveDfHtmlSample(): Unit = toDataFrame().saveDfHtmlSample()

    private fun Plot.changeThemeToDarkMode() {
        val layout = (this.features as MutableMap)[FeatureName("layout")] as? Layout
        val darkBackground = BackgroundParameters(fillColor = darkColor)
        val customTheme = CustomStyle(legend = Legend(darkBackground), plotCanvas = PlotCanvas(darkBackground))

        val darkLayout = layout?.apply {
            theme = Theme.DARCULA
            val customStyle = when {
                this.style != null && this.customStyle == null && style is CustomStyle -> style as CustomStyle
                style != null -> this.customStyle
                else -> this.customStyle
            }

            println(customStyle)

            customStyle?.let {
                if (it.plotCanvas.background?.fillColor == null) {
                    it.plotCanvas.background = it.plotCanvas.background?.copy(fillColor = darkColor)
                        ?: darkBackground
                }
                if (it.legend.background?.fillColor == null) {
                    it.legend.background = it.legend.background?.copy(fillColor = darkColor)
                        ?: darkBackground
                }
            } ?: run { style = customTheme }
        } ?: Layout(theme = Theme.DARCULA).apply { this.customStyle = customTheme }

        (this.features as MutableMap)[FeatureName("layout")] = darkLayout
    }

    private fun scaledHeight(width: Int, height: Int): Int = (fixedWidth.toFloat() * height / width).toInt()
    private fun scaledHeight(plot: Plot): Int {
        val (width, height) = (plot.features[Layout.NAME] as? Layout)?.size ?: (defaultWidth to defaultHeight)
        return scaledHeight(width, height)
    }

    private fun scaledHeight(plotGrid: PlotGrid): Int {
        val size = plotGrid.plots.size
        val nCol = plotGrid.nCol ?: size
        val nRow = (size + 1) % 2
        return scaledHeight(nCol * defaultWidth, nRow * defaultHeight)
    }

    private fun Plot.toFullSvg(): String {
        val scaledHeight = scaledHeight(this)
        return (toLetsPlot() + ggsize(fixedWidth, scaledHeight)).toSVG()
    }

    private fun Plot.toPreviewSvg(): String {
        return (toLetsPlot() + previewSize).toSVG()
    }

    private fun PlotGrid.toFullSvg(): String {
        val scaledHeight = scaledHeight(this)
        return (wrap() + ggsize(fixedWidth, scaledHeight)).toSVG()
    }

    private fun PlotGrid.toPreviewSvg(): String {
        return (wrap() + previewSize).toSVG()
    }

    private fun Figure.toSVG(): String {
        return replaceIdsWithStatic(PlotSvgExport.buildSvgImageFromRawSpecs(this.toSpec()))
    }

    private fun Plot.saveAsSVG(name: String, savePreview: Boolean = false) {
        File(pathToImageFolder, "$name.svg").writeText(toFullSvg())
        if (savePreview) {
            File(pathToImageFolder, "preview_$name.svg").writeText(toPreviewSvg())
        }
    }

    private fun PlotGrid.saveAsSVG(name: String, savePreview: Boolean = false, scaling: Boolean) {
        if (scaling)
            File(pathToImageFolder, "$name.svg").writeText(toFullSvg())
        else
            File(pathToImageFolder, "$name.svg").writeText(wrap().toSVG())
        if (savePreview) {
            File(pathToImageFolder, "preview_$name.svg").writeText(toPreviewSvg())
        }
    }

    private fun PlotBunch.saveAsSVG(name: String) {
        File(pathToImageFolder, "$name.svg").writeText(wrap().toSVG())
    }

    private val idPrefix = "_sample_helper_static_id_prefix_"

    private fun replaceIdsWithStatic(xmlString: String): String {
        val regex = Regex("""(id\s*=\s*["'])([^"']*)["']""")
        var count = 0
        var result = xmlString
        regex.findAll(xmlString).forEach {
            result = result.replace(it.groupValues[2], "$idPrefix${count++}")
        }
        return result
    }

    internal companion object {
        private const val DF_PREFIX = "df_"
    }

    internal fun replaceIdsWithStaticDataFrame(html: String): String {
        // normalize all minus signs to ASCII '-'
        fun normNum(s: String) = s.replace(Regex("[\u2212\\p{Pd}]"), "-")

        val dash = "[-\u2212\\p{Pd}]?"                 // any minus sign (or absence)
        val esc  = Regex.escape(DF_PREFIX)

        // The number is always in the LAST group
        val domIdRe   = Regex("""\bid\s*=\s*(['"])$esc($dash\d+)\1""")
        val jsFieldRe = Regex("""\b(id|rootId|frameId)\s*:\s*($dash\d+)\b""")

        // keep what is to the left of the number and the closing parenthesis
        val renderRe  = Regex("""\b((?:DataFrame\.)?renderTable\()\s*($dash\d+)\s*(\))""")

        val urlRe     = Regex("""\burl\(#$esc($dash\d+)\)""")
        val hrefRe    = Regex("""\bhref\s*=\s*(['"])#$esc($dash\d+)\1""")

        // 1) collect all ids in order of appearance
        val seen = linkedSetOf<String>()
        listOf(domIdRe, jsFieldRe, renderRe, urlRe, hrefRe).forEach { re ->
            re.findAll(html).forEach { seen += normNum(it.groupValues.last()) }
        }
        if (seen.isEmpty()) return html

        // 2) map to stable non-negative numbers: 0,1,2...
        val map = linkedMapOf<String, String>()
        var next = 0
        if ("0" in seen) { map["0"] = "0"; next = 1 }
        for (old in seen) if (old !in map) map[old] = (next++).toString()

        fun remap(old: String) = map[normNum(old)] ?: old

        // 3) replacements
        var out = domIdRe.replace(html) { m ->
            val q   = m.groupValues[1]
            val num = remap(m.groupValues.last())
            """id=$q$DF_PREFIX$num$q"""
        }
        out = jsFieldRe.replace(out) { m ->
            val key = m.groupValues[1]
            val num = remap(m.groupValues.last())
            "$key: $num"
        }
        out = renderRe.replace(out) { m ->
            val front = m.groupValues[1]
            val num   = remap(m.groupValues[2])
            val back  = m.groupValues[3]
            front + num + back
        }
        out = urlRe.replace(out)  { m -> "url(#$DF_PREFIX${remap(m.groupValues.last())})" }
        out = hrefRe.replace(out) { m -> """href=${m.groupValues[1]}#$DF_PREFIX${remap(m.groupValues.last())}${m.groupValues[1]}""" }

        return out
    }

}