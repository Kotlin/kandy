package org.jetbrains.kotlinx.kandy.echarts.io

import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.id
import kotlinx.html.meta
import kotlinx.html.script
import kotlinx.html.stream.createHTML
import kotlinx.html.style
import kotlinx.html.title
import kotlinx.html.unsafe
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.kotlinx.kandy.echarts.features.animation.PlotChangeAnimation
import org.jetbrains.kotlinx.kandy.echarts.translator.option.Option
import org.jetbrains.kotlinx.kandy.echarts.translator.toOption
import org.jetbrains.kotlinx.kandy.ir.Plot
import java.util.*


public fun Plot.toHtml(): String {
    val option = this.toOption()
    val chartID = UUID.randomUUID().toString().replace("-", "")
    return createHTML().html {
        head {
            meta {
                charset = "utf-8"
            }
            option.title?.text?.let { title(it) }
            script {
                type = "text/javascript"
                src = EchartsConfig.ECHARTS_JSDELIVER_SRC
            }
        }
        body {
            div {
                id = chartID
                style = "width:${option.plotSize.first}px;height:${option.plotSize.second}px;"
            }
            script {
                unsafe {
                    +option.initializeChart(chartID, EchartsConfig.json)
                }
            }
        }
    }
}

private fun Option.initializeChart(chartID: String, json: Json) =
    """
        var chart_$chartID = echarts.init(document.getElementById('$chartID'), {renderer: 'canvas'});
        var option_$chartID = ${this.toJSON(json)};
        chart_$chartID.setOption(option_$chartID);
    """.trimIndent()

internal fun Plot.toKotlinNotebookHtml(chartID: String, json: Json): String {
    val option = this.toOption()
    return with(createHTML(false)) {
        script {
            unsafe { +"require.config({paths: {'echarts':'${EchartsConfig.ECHARTS_JSDELIVER_PACK}'}});" }
        }
        div {
            id = chartID
            style = "width:${option.plotSize.first}px; height:${option.plotSize.second}px;"
        }
        script {
            unsafe {
                +"""
                require(['echarts'], function (echarts) {
                    ${option.initializeChart(chartID, json)}
                });
                """.trimIndent()
            }
        }
    }
}

internal fun Plot.toJupyterNotebookHtml(chartID: String, json: Json): String {
    val option = this.toOption()
    return createHTML(false).html {
        head {
            meta {
                charset = "utf-8"
            }
            option.title?.text?.let { title(it) }
            script {
                type = "text/javascript"
                src = EchartsConfig.ECHARTS_JSDELIVER_SRC
            }
        }
        body {
            div {
                id = chartID
                style = "width:${option.plotSize.first}px;height:${option.plotSize.second}px;"
            }
            script {
                unsafe {
                    +option.initializeChart(chartID, json)
                }
            }
        }
    }
}

internal fun PlotChangeAnimation.toHTML(chartID: String, json: Json): String {
    val encodedPlots = json.encodeToString(plots.map { it.toOption() })
    val size = plots.size
    return createHTML().html {
        head {
            meta {
                charset = "utf-8"
            }
            script {
                type = "text/javascript"
                src = EchartsConfig.ECHARTS_JSDELIVER_SRC
            }
        }
        body {
            div {
                id = chartID
                style = "width: 1000px;height:800px;"
            }
            script {
                type = "text/javascript"
                unsafe {
                    +"""
                        var chart_$chartID = echarts.init(document.getElementById('main'));
                        var options = $encodedPlots;
                        var option = options[0];
                        myChart.setOption(option);
                        var maxStates = $size;
                        var nextState = 1 % maxStates;
                        setInterval(function () {
                            option = options[nextState];
                            nextState = (nextState + 1) % maxStates;
                            chart_$chartID.setOption(option, true);
                        }, $interval);
                    """.trimIndent()
                }
            }
        }

    }
}

//internal fun DataChangeAnimation.toHTML(chartID: String, json: Json): String {
//    val maxStates = 100
//    val initOption = plot.toOption().toJSON(json)
//    val dataset = plot.datasets.first()
//    val datasets = mutableListOf<Dataset>()
//    repeat(maxStates) {
//        dataChange(dataset as NamedData)
//        datasets.add(Dataset(dataset.wrap().data.map { it.map { el -> el.toString() } }))
//    }
//    val encodedDatasets = json.encodeToString(datasets)
//    return createHTML().html {
//        head {
//            meta {
//                charset = "utf-8"
//            }
//        }
//        body {
//            div {
//                id = chartID
//                style = "width: 1000px;height:800px;"
//            }
//            script {
//                type = "text/javascript"
//                unsafe {
//                    +(
//                            "\n        var myChart = echarts.init(document.getElementById('main'));\n" +
//                                    "        var option = $initOption;\n" +
//                                    "        myChart.setOption(option);\n" +
//                                    "        var datasets = $encodedDatasets;\n" +
//                                    "var nextState = 0;\n" +
//                                    "var maxStates = $maxStates\n" +
//                                    "setInterval(function () {\n" +
//                                    "var newDataset = datasets[nextState];\n" +
//                                    "option.dataset = newDataset;\n" +
//                                    "nextState = Math.min(1 + nextState, maxStates-1); \n" +
//                                    "  myChart.setOption(option, true);\n" +
//                                    "}, $interval);\n"
//                            )
//                }
//            }
//        }
//
//    }
//}