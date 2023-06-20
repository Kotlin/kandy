/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.kotlinx.jupyter.api.*
import org.jetbrains.kotlinx.kandy.echarts.features.animation.PlotChangeAnimation
import org.jetbrains.kotlinx.kandy.echarts.translator.Parser
import org.jetbrains.kotlinx.kandy.echarts.translator.option.Option
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.jupyter.api.annotations.JupyterLibrary
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration
import org.jetbrains.kotlinx.jupyter.api.libraries.resources
import java.util.UUID

private const val ECHARTS_SRC: String = "https://cdn.jsdelivr.net/npm/echarts@5.4.1/dist/echarts.min.js"

@JupyterLibrary
internal class Integration(private val notebook: Notebook, private val options: MutableMap<String, String?>) :
    JupyterIntegration() {
    override fun Builder.onLoaded() {
        resources {
            js("kandyEcharts") {
                url(ECHARTS_SRC, classpathFallBack = "scripts/echarts.min.js")
//                when (notebook.jupyterClientType) {
//                    JupyterClientType.KOTLIN_NOTEBOOK, JupyterClientType.JUPYTER_LAB -> classPath("scripts/init.js")
//                    else -> url(ECHARTS_SRC, classpathFallBack = "scripts/echarts.min.js")
//                }
            }
        }

        render<Plot> { HTML(it.toOption().toHTML()) }
//        render<DataChangeAnimation> { HTML(it.toHTML(), true) }
        render<PlotChangeAnimation> { HTML(it.toHTML()) }

        import("org.jetbrains.kotlinx.kandy.echarts.*")
        import("org.jetbrains.kotlinx.kandy.echarts.features.*")
        import("org.jetbrains.kotlinx.kandy.echarts.features.animation.*")
        import("org.jetbrains.kotlinx.kandy.echarts.features.label.*")
        import("org.jetbrains.kotlinx.kandy.echarts.features.marks.*")
        import("org.jetbrains.kotlinx.kandy.echarts.features.text.*")
        import("org.jetbrains.kotlinx.kandy.echarts.features.title.*")
        import("org.jetbrains.kotlinx.kandy.echarts.layers.*")
        import("org.jetbrains.kotlinx.kandy.echarts.layers.aes.*")
        import("org.jetbrains.kotlinx.kandy.echarts.layers.context.*")
        import("org.jetbrains.kotlinx.kandy.echarts.settings.*")
        import("org.jetbrains.kotlinx.kandy.echarts.scale.*")
        import("org.jetbrains.kotlinx.kandy.echarts.scale.guide.*")
        import("org.jetbrains.kotlinx.kandy.echarts.translator.*")

    }
}

private fun Plot.toOption(): Option {
    val parser = Parser(this)
    return parser.parse()
}

@OptIn(ExperimentalSerializationApi::class)
private val json = Json {
    prettyPrint = true
    isLenient = true
    encodeDefaults = true
    explicitNulls = false
    allowSpecialFloatingPointValues = true
}

public fun Plot.toJson(): String = this.toOption().toJSON()

private fun Option.toJSON(): String = json.encodeToString(this)

internal fun Option.toHTML(): String {
    val width = plotSize.first
    val height = plotSize.second
    val chartId = UUID.randomUUID().toString().replace("-", "")
    return createHTML().html {
        head {
            lang = "en"
            meta {
                charset = "utf-8"
            }
            this@toHTML.title?.text?.let { title(it) }
        }
        body {
            div {
                id = chartId
                classes += "chart-container"
                style = "width: ${width}px;height:${height}px;" // TODO (horizontal_center?)
            }
            script {
                type = "application/vnd.echarts+json"
                unsafe {
                    +"""                    
    var chart_$chartId = echarts.init(
        document.getElementById('$chartId'), 'white', {renderer: 'canvas'});
    var option_$chartId = ${toJSON()};
    chart_$chartId.setOption(option_$chartId);
                """
                }
            }
        }

    }
}


//public fun DataChangeAnimation.toHTML(): String {
//    val encoder = Json {
//        explicitNulls = false
//        encodeDefaults = true
//    }
//    val maxStates = 100
//    val initOption = plot.toOption().toJSON().replace('\"', '\'')
//    val dataset = plot.dataset
//    val datasets = mutableListOf<Dataset>()
//    repeat(maxStates) {
//        dataChange(dataset as NamedDataInterface)
//        datasets.add(Dataset(dataset.wrap().data.map { it.map { el -> el.toString() } }))
//    }
//    val encodedDatasets = encoder.encodeToString(datasets).replace('\"', '\'')
//    return createHTML().html {
//        head {
//            meta {
//                charset = "utf-8"
//            }
//            title("MY BEAUTIFUL PLOT")
//            script {
//                type = "text/javascript"
//                src = ECHARTS_SRC
//            }
//        }
//        body {
//            div {
//                id = "main" // TODO!!!
//                style = "width: 1000px;height:800px;background: white"
//            }
//            script {
//                type = "text/javascript"
//                unsafe {
//                    +(
//                        "\n        var myChart = echarts.init(document.getElementById('main'));\n" +
//                            "        var option = $initOption;\n" +
//                            "        myChart.setOption(option);\n" +
//                            "        var datasets = $encodedDatasets;\n" +
//                            "var nextState = 0;\n" +
//                            "var maxStates = $maxStates\n" +
//                            "setInterval(function () {\n" +
//                            "var newDataset = datasets[nextState];\n" +
//                            "option.dataset = newDataset;\n" +
//                            "nextState = Math.min(1 + nextState, maxStates-1); \n" +
//                            "  myChart.setOption(option, true);\n" +
//                            "}, $interval);\n"
//                        )
//                }
//            }
//        }
//
//    }
//}

@OptIn(ExperimentalSerializationApi::class)
internal fun PlotChangeAnimation.toHTML(): String {
    val encoder = Json {
        explicitNulls = false
        encodeDefaults = true
    }

    val encodedPlots = encoder.encodeToString(plots.map { it.toOption() }).replace('\"', '\'')
    val size = plots.size
    return createHTML().html {
        head {
            meta {
                charset = "utf-8"
            }
        }
        body {
            div {
                id = "main"
                style = "width: 1000px;height:800px;background: white"
            }
            script {
                type = "text/javascript"
                unsafe {
                    +("""
                        var myChart = echarts.init(document.getElementById('main'));
                        var options = $encodedPlots;
                        var option = options[0];
                        myChart.setOption(option);
                        var maxStates = $size;
                        var nextState = 1 % maxStates;
                        setInterval(function () {
                            option = options[nextState];
                            nextState = (nextState + 1) % maxStates;
                            myChart.setOption(option, true);
                        }, $interval);
                        """.trimIndent()
                            )
                }
            }
        }

    }
}
