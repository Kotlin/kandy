/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.kotlinx.ggdsl.echarts.animation.DataChangeAnimation
import org.jetbrains.kotlinx.ggdsl.echarts.animation.PlotChangeAnimation
import org.jetbrains.kotlinx.ggdsl.echarts.translator.toOption
import org.jetbrains.kotlinx.ggdsl.echarts.translator.wrap
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.jupyter.api.HTML
import org.jetbrains.kotlinx.jupyter.api.annotations.JupyterLibrary
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration
import org.jetbrains.kotlinx.jupyter.api.libraries.resources

@JupyterLibrary
internal class Integration : JupyterIntegration() {

    override fun Builder.onLoaded() {
        resources {
            js("echarts") {
                url(ECHARTS_SRC)
                classPath("js/echarts.min.js")
            }
        }

        render<Plot> { it.toOption() }
        render<Option> { HTML(it.toHTML(800 to 600), true) }
        render<MetaOption> { HTML(it.option.toHTML(it.size), true) }
        render<DataChangeAnimation> { HTML(it.toHTML(), true) }
        render<PlotChangeAnimation> { HTML(it.toHTML(), true) }

        import("org.jetbrains.kotlinx.ggdsl.echarts.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.layers.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.animation.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.util.color.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.util.symbol.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.util.linetype.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.scale.guide.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.scale.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.stack.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.translator.*")
        //todo import layers
    }
}

const val ECHARTS_SRC = "https://cdn.jsdelivr.net/npm/echarts@5.2.2/dist/echarts.min.js"

@ExperimentalSerializationApi
fun Option.toJSON(): String {
    /*return Klaxon().toJsonString(this)*/

    return Json {
        explicitNulls = false
        encodeDefaults = true
        useArrayPolymorphism = true
        isLenient = true
    }.encodeToString(this)


}

fun Option.toHTML(size: Pair<Int, Int>? = null): String {
    val width = size?.first ?: 600
    val height = size?.second ?: 400
    return createHTML().html {
        head {
            meta {
                charset = "utf-8"
            }
            title("MY BEAUTIFUL PLOT")
            script {
                type = "text/javascript"
                src = ECHARTS_SRC
            }
        }
        body {
            div {
                id = "main"
                style = "width: ${width}px;height:${height}px;background: white"
            }
            script {
                type = "text/javascript"
                unsafe {
                    +"""
                    var myChart = echarts.init(document.getElementById('main'));
                    var option = ${toJSON()/*.replace('\"', '\'')*/};
                    myChart.setOption(option);
                """.trimIndent()
                }

                /*+("\n        var myChart = echarts.init(document.getElementById('main'));\n" +
                        "        var option = ${toJSON().replace('\"', '\'')};\n" +
                        "        myChart.setOption(option);")

                 */
            }
        }

    }


}

/// TODO!!! UNSAFE!!!

// todo sizes
@OptIn(ExperimentalSerializationApi::class)
fun DataChangeAnimation.toHTML(): String {
    val encoder = Json {
        explicitNulls = false
        encodeDefaults = true
    }
    val maxStates = 100
    // todo size
    val initOption = plot.toOption().option.toJSON().replace('\"', '\'')
    var dataset = plot.dataset
    val datasets = mutableListOf<Dataset>()
    repeat(maxStates) {
        dataChange(dataset)
        datasets.add(Dataset(dataset.wrap().data.map { it.map { it.toString() } }))
    }
    val encodedDatasets = encoder.encodeToString(datasets).replace('\"', '\'')
    return createHTML().html {
        head {
            meta {
                charset = "utf-8"
            }
            title("MY BEAUTIFUL PLOT")
            script {
                type = "text/javascript"
                src = ECHARTS_SRC
            }
        }
        body {
            div {
                id = "main" // TODO!!!
                style = "width: 1000px;height:800px;background: white"
            }
            script {
                type = "text/javascript"
                +(
                        "\n        var myChart = echarts.init(document.getElementById('main'));\n" +
                                "        var option = $initOption;\n" +
                                "        myChart.setOption(option);\n" +
                                "        var datasets = $encodedDatasets;\n" +
                                "var nextState = 0;\n" +
                                "var maxStates = $maxStates\n" +
                                "setInterval(function () {\n" +
                                "var newDataset = datasets[nextState];\n" +
                                "option.dataset = newDataset;\n" +
                                "nextState = Math.min(1 + nextState, maxStates-1); \n" +
                                "  myChart.setOption(option, true);\n" +
                                "}, $interval);\n"
                        )
            }
        }

    }
}

@OptIn(ExperimentalSerializationApi::class)
fun PlotChangeAnimation.toHTML(): String {
    val encoder = Json {
        explicitNulls = false
        encodeDefaults = true
    }

    val encodedPlots = encoder.encodeToString(plots.map { it.toOption().option }).replace('\"', '\'')
    val size = plots.size
    return createHTML().html {
        head {
            meta {
                charset = "utf-8"
            }
            title("MY BEAUTIFUL PLOT")
            script {
                type = "text/javascript"
                src = ECHARTS_SRC
            }
        }
        body {
            div {
                id = "main" // TODO!!!
                style = "width: 1000px;height:800px;background: white"
            }
            script {
                type = "text/javascript"
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


