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
    script = """
        function sendHeight() {
            const table = document.querySelector('table.dataframe');
            if (!table) return;
        
            let height = 0;
            
            const thead = table.querySelector('thead');
            if (thead) height += thead.offsetHeight;
            
            const rows = table.querySelectorAll(':scope > tbody > tr');
            rows.forEach(row => {
                height += row.offsetHeight;
            });
            
            height += getVerticalMargins(table) + 10;
        
            window.parent.postMessage({ type: 'iframeHeight', height: Math.ceil(height) }, '*');
        }
        
        function getVerticalMargins(element) {
            const style = getComputedStyle(element);
            return parseFloat(style.marginTop) + parseFloat(style.marginBottom);
        }
        
        function repeatHeightCalculation(maxRetries = 10, interval = 100) {
            let retries = 0;
            const intervalId = setInterval(() => {
                sendHeight();
                retries++;
                if (retries >= maxRetries) clearInterval(intervalId);
            }, interval);
        }
        
        window.addEventListener('load', repeatHeightCalculation);
        
        const observer = new MutationObserver(() => repeatHeightCalculation(5, 50));
        observer.observe(document.querySelector('.dataframe'), {
            childList: true,
            subtree: true,
            characterData: true,
        });


    """.trimIndent()
)

internal val WritersideFooter: (DataFrame<*>) -> String = { "" }
