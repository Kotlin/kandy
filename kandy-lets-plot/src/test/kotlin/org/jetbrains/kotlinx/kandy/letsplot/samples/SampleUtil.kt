package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.feature.Layout
import org.jetbrains.kotlinx.kandy.letsplot.theme.Flavor
import org.junit.Rule
import org.junit.rules.TestName

abstract class SampleHelper(sampleName: String) {
    protected val pathToImageFolder = "../docs/images/samples/$sampleName"

    @JvmField
    @Rule
    val testName: TestName = TestName()

    fun Plot.saveSample(scale: Number = 1.175) {
        val name = testName.methodName.replace("_dataframe", "")
        this.save("$name.png", scale, path = pathToImageFolder)
        val layout = (this.features as MutableMap)[FeatureName("layout")] as? Layout
        (this.features as MutableMap)[FeatureName("layout")] =
            layout?.copy(flavor = Flavor.DARCULA).also {
                it?.theme = layout?.theme
                it?.customTheme = layout?.customTheme
            } ?: Layout(flavor = Flavor.DARCULA)
        this.save("${name}_dark.png", scale, path = pathToImageFolder)
    }
}