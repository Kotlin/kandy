package org.jetbrains.kotlinx.kandy.echarts.jupyter

import org.jetbrains.kotlinx.jupyter.api.HTML
import org.jetbrains.kotlinx.jupyter.api.annotations.JupyterLibrary
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration
import org.jetbrains.kotlinx.jupyter.api.libraries.resources
import org.jetbrains.kotlinx.kandy.echarts.features.animation.PlotChangeAnimation
import org.jetbrains.kotlinx.kandy.echarts.io.EchartsConfig
import org.jetbrains.kotlinx.kandy.ir.Plot

@JupyterLibrary
internal class Integration : JupyterIntegration() {
    override fun Builder.onLoaded() {
        resources {
            js("echarts") {
                url(EchartsConfig.ECHARTS_JSDELIVER_SRC, classpathFallBack = "js/echarts.min.js")
            }
        }

        import("org.jetbrains.kotlinx.kandy.echarts.*")
        import("org.jetbrains.kotlinx.kandy.echarts.features.*")
        import("org.jetbrains.kotlinx.kandy.echarts.features.animation.*")
        import("org.jetbrains.kotlinx.kandy.echarts.features.label.*")
        import("org.jetbrains.kotlinx.kandy.echarts.features.marks.*")
        import("org.jetbrains.kotlinx.kandy.echarts.features.text.*")
        import("org.jetbrains.kotlinx.kandy.echarts.features.title.*")
        import("org.jetbrains.kotlinx.kandy.echarts.io.*")
        import("org.jetbrains.kotlinx.kandy.echarts.layers.*")
        import("org.jetbrains.kotlinx.kandy.echarts.layers.aes.*")
        import("org.jetbrains.kotlinx.kandy.echarts.layers.context.*")
        import("org.jetbrains.kotlinx.kandy.echarts.settings.*")
        import("org.jetbrains.kotlinx.kandy.echarts.scale.*")
        import("org.jetbrains.kotlinx.kandy.echarts.scale.guide.*")
        import("org.jetbrains.kotlinx.kandy.echarts.translator.*")

        with(JupyterHtmlRenderer(this)) {
            render<Plot> { HTML(it.renderHtml(), true) }
            render<PlotChangeAnimation> { HTML(it.renderHtml(), true) }
//            render<DataChangeAnimation> { HTML(it.toHTML(), true) }
        }
    }
}
