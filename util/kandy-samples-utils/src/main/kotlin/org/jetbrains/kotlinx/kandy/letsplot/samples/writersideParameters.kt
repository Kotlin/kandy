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

    const thead = table.querySelector('thead');
    const tbody = table.querySelector('tbody');
    if (!thead || !tbody || tbody.children.length === 0) return;

    let height = thead.offsetHeight;
    const rows = tbody.querySelectorAll('tr');
    for (let i = 0; i < Math.min(rows.length, 5); i++) {
        height += rows[i].offsetHeight;
    }

    const margin = parseFloat(getComputedStyle(table).marginTop) +
                   parseFloat(getComputedStyle(table).marginBottom);

    const totalHeight = Math.ceil(height + margin + 10);

    window.parent.postMessage({ type: 'iframeHeight', height: totalHeight }, '*');
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

const observer = new MutationObserver(sendHeight);
observer.observe(document.querySelector('.dataframe'), {
    childList: true,
    subtree: true,
    characterData: true
});

    """.trimIndent()
)

internal val WritersideFooter: (DataFrame<*>) -> String = { "" }
