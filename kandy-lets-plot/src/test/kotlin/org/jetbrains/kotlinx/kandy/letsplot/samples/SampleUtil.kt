package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.letsplot.feature.Layout
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotGrid
import org.jetbrains.kotlinx.kandy.letsplot.theme.Flavor
import org.jetbrains.kotlinx.kandy.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.kandy.letsplot.translator.wrap
import org.jetbrains.letsPlot.Figure
import org.jetbrains.letsPlot.awt.plot.PlotSvgExport
import org.jetbrains.letsPlot.intern.toSpec
import org.junit.Rule
import org.junit.rules.TestName
import java.io.File

abstract class SampleHelper(sampleName: String) {
    protected val pathToImageFolder = "../docs/images/samples/$sampleName"

    private fun Figure.toSVG(): String {
        return replaceIdsWithConstant(PlotSvgExport.buildSvgImageFromRawSpecs(this.toSpec()))
    }

    private fun Plot.toSVG(): String {
        return toLetsPlot().toSVG()
    }

    private fun PlotGrid.toSVG(): String {
        return wrap().toSVG()
    }

    private fun Plot.saveAsSVG(name: String) {
        File(pathToImageFolder,"$name.svg").writeText(toSVG())
    }

    private fun PlotGrid.saveAsSVG(name: String) {
        File(pathToImageFolder,"$name.svg").writeText(toSVG())
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

    @JvmField
    @Rule
    val testName: TestName = TestName()

    fun Plot.saveSample() {
        val name = testName.methodName.replace("_dataframe", "")
        saveAsSVG(name)
        val layout = (this.features as MutableMap)[FeatureName("layout")] as? Layout
        (this.features as MutableMap)[FeatureName("layout")] =
            layout?.copy(flavor = Flavor.DARCULA).also {
                it?.theme = layout?.theme
                it?.customTheme = layout?.customTheme
            } ?: Layout(flavor = Flavor.DARCULA)
        saveAsSVG("${name}_dark")
    }

    fun PlotGrid.saveSample() {
        val name = testName.methodName.replace("_dataframe", "")
        saveAsSVG(name)
        plots.forEach {
            it ?: return
            val layout = (it.features as MutableMap)[FeatureName("layout")] as? Layout
            (it.features as MutableMap)[FeatureName("layout")] =
                layout?.copy(flavor = Flavor.DARCULA).also {
                    it?.theme = layout?.theme
                    it?.customTheme = layout?.customTheme
                } ?: Layout(flavor = Flavor.DARCULA)
        }
        saveAsSVG("${name}_dark")
    }
}