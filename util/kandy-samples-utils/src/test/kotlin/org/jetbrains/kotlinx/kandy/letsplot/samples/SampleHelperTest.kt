package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.format
import org.jetbrains.kotlinx.dataframe.api.with
import org.junit.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class SampleHelperTest: SampleHelper("name") {

    @Test
    fun `ids become static for Modify samples`() {
        val html = """
            <table class="dataframe" id="df_-1811939324"></table>
            <script>
            call_DataFrame(function() { 
                DataFrame.addTable({ id: -1811939324, rootId: -1811939324, totalRows: 7 });
            });
            call_DataFrame(function() { DataFrame.renderTable(-1811939324) });
            </script>
        """.trimIndent()

        val out = replaceIdsWithStaticDataFrame(html)

        // the root is consistently 0
        assertTrue(out.contains("""id="df_0""""))
        assertTrue(out.contains("id: 0"))
        assertTrue(out.contains("rootId: 0"))
        assertTrue(out.contains("renderTable(0)"))

        // no old dynamic ids remain (including Unicode minus)
        assertFalse(Regex("""df_[-\u2212\p{Pd}]\d+""").containsMatchIn(out))
    }

    @Test
    fun `js fields do not contain negative or unicode-minus ids`() {
        val out = replaceIdsWithStaticDataFrame(sampleHtml)

        // 1) no negative values remain in JS fields
        assertFalse(Regex("""\b(?:id|rootId|frameId)\s*:\s*[-\u2212\p{Pd}]\d+""")
            .containsMatchIn(out))

        // 2) only non-negative "static" numbers are allowed
        Regex("""\b(?:id|rootId|frameId)\s*:\s*(\d+)\b""")
            .findAll(out)
            .forEach { m ->
                val v = m.groupValues[1].toInt()
                assertTrue(v >= 0)  // optionally, can be narrowed to range 0..N
            }
    }

    @Test
    fun `dom ids are static and non-negative`() {
        val out = replaceIdsWithStaticDataFrame(sampleHtml)

        // no df_ with a minus sign
        assertFalse(Regex("""id\s*=\s*['"]df_[-\u2212\p{Pd}]""").containsMatchIn(out))

        // there is at least one stabilized id
        assertTrue(out.contains("""id="df_0""""))
    }

    @Test
    fun `renderTable keeps DataFrame prefix and ids are non-negative`() {
        val out = replaceIdsWithStaticDataFrame(sampleHtml)

        assertTrue(Regex("""\bDataFrame\.renderTable\(\s*\d+\s*\)""").containsMatchIn(out))
        assertFalse(Regex("""\bDataFrame\.renderTable\(\s*[-\u2212\p{Pd}]""").containsMatchIn(out))
    }

    private val sampleHtml = """
        <table class="dataframe" id="df_-1811939324"></table>
        <script>
          call_DataFrame(function() {
            DataFrame.addTable({ id: -1811939324, rootId: -1811939324, frameId: -42, totalRows: 10 });
          });
          call_DataFrame(function() { DataFrame.renderTable(-1811939324) });
        </script>
    """.trimIndent()

    @Test
    fun `unicode minus also handled`() {
        val html = """
            <table id="df_−2046820349"></table>
            <script>
              DataFrame.addTable({ id: −2046820349, rootId: −2046820349 });
              DataFrame.renderTable(−2046820349)
            </script>
        """.trimIndent()

        val out = replaceIdsWithStaticDataFrame(html)
        assertTrue(out.contains("""id="df_0""""))
        assertTrue(out.contains("id: 0"))
        assertTrue(out.contains("rootId: 0"))
        assertTrue(out.contains("renderTable(0)"))
    }

    @Test
    fun testKekius() {
        val df = dataFrameOf("a" to listOf(1, 2, 3)).format("a").with { background(red) }

        df.saveDfHtmlSample()
    }
}
