/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

@file:OptIn(ExperimentalSerializationApi::class)

package org.jetbrains.kotlinx.ggdsl.echarts

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.kotlinx.ggdsl.echarts.translator.Parser
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.Option
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.jupyter.api.HTML
import org.jetbrains.kotlinx.jupyter.api.annotations.JupyterLibrary
import org.jetbrains.kotlinx.jupyter.api.libraries.JupyterIntegration
import org.jetbrains.kotlinx.jupyter.api.libraries.resources

private const val ECHARTS_SRC: String = "https://cdn.jsdelivr.net/npm/echarts@5.2.2/dist/echarts.min.js"

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
//        render<MetaOption> { HTML(it.option.toHTML(it.size), true) } // TODO (size?)
//        render<DataChangeAnimation> { HTML(it.toHTML(), true) }
//        render<PlotChangeAnimation> { HTML(it.toHTML(), true) }

        import("org.jetbrains.kotlinx.ggdsl.echarts.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.aes.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.features.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.features.animation.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.features.marks.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.layers.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.settings.*")
//        import("org.jetbrains.kotlinx.ggdsl.echarts.animation.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.scale.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.scale.guide.*")
        import("org.jetbrains.kotlinx.ggdsl.echarts.translator.*")

    }
}

internal fun Plot.toOption(): Option {
    val parser = Parser(this)

    return parser.parse()
}

@OptIn(ExperimentalSerializationApi::class)
private val json = Json {
    explicitNulls = false
    encodeDefaults = true
//    useArrayPolymorphism = true
    isLenient = true
}

public fun Option.toJSON(): String {
    return json.encodeToString(this)
}

public fun Option.toHTML(size: Pair<Int, Int>? = null): String {
    val width = size?.first ?: 600
    val height = size?.second ?: 400
    return createHTML().html {
        head {
            meta {
                charset = "utf-8"
            }
            title("MY BEAUTIFUL PLOT") // TODO (this.title)
            script {
                type = "text/javascript"
                src = ECHARTS_SRC
            }
        }
        body {
            div {
                id = "main" // TODO (set unique id)
                style = "width: ${width}px;height:${height}px;background: white" // TODO (what does style need?)
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
            }
        }

    }


}

/// TODO!!! UNSAFE!!!

// todo sizes
//public fun DataChangeAnimation.toHTML(): String {
//    val encoder = Json {
//        explicitNulls = false
//        encodeDefaults = true
//    }
//    val maxStates = 100
//    // todo size
//    val initOption = plot.toOption().option.toJSON().replace('\"', '\'')
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

//public fun PlotChangeAnimation.toHTML(): String {
//    val encoder = Json {
//        explicitNulls = false
//        encodeDefaults = true
//    }
//
//    val encodedPlots = encoder.encodeToString(plots.map { it.toOption().option }).replace('\"', '\'')
//    val size = plots.size
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
//                    +("""
//                        var myChart = echarts.init(document.getElementById('main'));
//                        var options = $encodedPlots;
//                        var option = options[0];
//                        myChart.setOption(option);
//                        var maxStates = $size;
//                        var nextState = 1 % maxStates;
//                        setInterval(function () {
//                            option = options[nextState];
//                            nextState = (nextState + 1) % maxStates;
//                            myChart.setOption(option, true);
//                        }, $interval);
//                        """.trimIndent()
//                        )
//                }
//            }
//        }
//
//    }
//}


