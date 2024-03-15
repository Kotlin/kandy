package org.jetbrains.kotlinx.kandy.echarts.jupyter

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.jetbrains.kotlinx.jupyter.api.JupyterClientType
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration
import org.jetbrains.kotlinx.kandy.echarts.features.animation.PlotChangeAnimation
import org.jetbrains.kotlinx.kandy.echarts.io.toHTML
import org.jetbrains.kotlinx.kandy.echarts.io.toHtml
import org.jetbrains.kotlinx.kandy.echarts.io.toJupyterNotebookHtml
import org.jetbrains.kotlinx.kandy.echarts.io.toKotlinNotebookHtml
import org.jetbrains.kotlinx.kandy.ir.Plot
import java.util.*

@OptIn(ExperimentalSerializationApi::class)
internal class JupyterHtmlRenderer(private val builder: JupyterIntegration.Builder) {
    private val json = Json {
        explicitNulls = false
        encodeDefaults = true
        prettyPrint = false
        isLenient = true
    }

    private val chartID: String
        get() = UUID.randomUUID().toString().replace("-", "")

    fun Plot.renderHtml(): String =
        when (builder.notebook.jupyterClientType) {
            JupyterClientType.KOTLIN_NOTEBOOK -> this.toKotlinNotebookHtml(chartID, json)
            JupyterClientType.JUPYTER_NOTEBOOK -> this.toJupyterNotebookHtml(chartID, json)
            else -> this.toHtml()
        }

    fun PlotChangeAnimation.renderHtml(): String = this.toHTML(chartID, json)

//    fun DataChangeAnimation.renderHtml(): String = this.toHtml(chartID, json)
}