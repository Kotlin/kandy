package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.io.DataFrameHtmlData
import org.jetbrains.kotlinx.dataframe.io.DisplayConfiguration

internal val SamplesDisplayConfiguration = DisplayConfiguration(enableFallbackStaticTables = false)

internal val WritersideStyle = DataFrameHtmlData(
    // copy writerside stlyles
    style =
        """
        body {
            font-family: "JetBrains Mono",SFMono-Regular,Consolas,"Liberation Mono",Menlo,Courier,monospace;
        }       
        
        :root {
            color: #19191C;
            background-color: #fff;
        }
        
        :root[theme="dark"] {
            background-color: #19191C;
            color: #FFFFFFCC
        }
        
        details details {
            margin-left: 20px; 
        }
        
        summary {
            padding: 6px;
        }
        """.trimIndent(),
)

internal val WritersideFooter: (DataFrame<*>) -> String = { "" }
