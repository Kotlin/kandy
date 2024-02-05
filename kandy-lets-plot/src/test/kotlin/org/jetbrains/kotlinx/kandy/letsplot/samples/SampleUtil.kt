package org.jetbrains.kotlinx.kandy.letsplot.samples

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

abstract class SampleHelper(sampleName: String, folder: String = "samples") {

    @JvmField
    @Rule
    val testName: TestName = TestName()

    private val pathToImageFolder = "../docs/images/$folder/$sampleName"

    private val darkColor = Color.hex("#19191c")

    init {
        File(pathToImageFolder).mkdirs()
    }

    private val defaultWidth = 600
    private val defaultHeight = 400
    private val previewSize = ggsize(defaultWidth, defaultHeight)
    private val fixedWidth = 705

    fun Plot.saveSample(savePreview: Boolean = false) {
        val name = testName.methodName.replace("_dataframe", "")
        saveAsSVG(name, savePreview)
        this.changeThemeToDarkMode()
        saveAsSVG("${name}_dark", savePreview)
    }

    fun PlotGrid.saveSample(savePreview: Boolean = false, scaling: Boolean = true) {
        val name = testName.methodName.replace("_dataframe", "")
        saveAsSVG(name, savePreview, scaling)
        plots.forEach {
            it ?: return
            it.changeThemeToDarkMode()
        }
        saveAsSVG("${name}_dark", savePreview, scaling)
    }

    fun PlotBunch.saveSample() {
        val name = testName.methodName.replace("_dataframe", "")
        saveAsSVG(name)
        this.items.forEach {
            it.plot.changeThemeToDarkMode()
        }
        saveAsSVG("${name}_dark")
    }

    private fun Plot.changeThemeToDarkMode() {
        val layout = (this.features as MutableMap)[FeatureName("layout")] as? Layout
        val darkBackground = BackgroundParameters(fillColor = darkColor)
        val customTheme = CustomStyle(legend = Legend(darkBackground), plotCanvas = PlotCanvas(darkBackground))

        val darkLayout = layout?.apply {
            theme = Theme.DARCULA
            val cusTheme = when {
                this.style != null && this.customStyle == null && style is CustomStyle -> style as CustomStyle
                style != null -> this.customStyle
                else -> this.customStyle
            }

            println(cusTheme)

            cusTheme?.let {
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
        return replaceIdsWithConstant(PlotSvgExport.buildSvgImageFromRawSpecs(this.toSpec()))
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

    private fun replaceIdsWithConstant(svgString: String): String {
        val regex = Regex("""(id\s*=\s*["'])([^"']*)["']""")
        var count = 0
        var result = svgString
        regex.findAll(svgString).forEach {
            result = result.replace(it.groupValues[2], "xXxprefixXx${count++}")
        }
        return result
    }
}