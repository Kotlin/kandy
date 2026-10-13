package org.jetbrains.kotlinx.kandy.letsplot.samples

import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// The sample file name is derived from the test method name,
// so the method names here are the actual test inputs.
class SampleFileNameTest : SampleHelper(
    sampleName = SAMPLE_NAME,
    imagesFolder = "$TMP_DIR/images",
    resourcesFolder = "$TMP_DIR/resources",
    namePartsToRemove = listOf("_properties", "_strings", "_kotlin", "_java"),
) {

    @Test
    fun plot_properties() {
        assertEquals("plot", sampleFileName())
    }

    @Test
    fun plot_strings() {
        assertEquals("plot", sampleFileName())
    }

    @Test
    fun plot_kotlin() {
        assertEquals("plot", sampleFileName())
    }

    @Test
    fun plot_java() {
        assertEquals("plot", sampleFileName())
    }

    @Test
    fun plot_properties_kotlin() {
        assertEquals("plot", sampleFileName())
    }

    @Test
    fun plot() {
        assertEquals("plot", sampleFileName())
    }

    @Test
    fun plot_dataframe() {
        // default parts are not applied when custom ones are passed
        assertEquals("plot_dataframe", sampleFileName())
    }

    @Test
    fun savedHtml_strings() {
        dataFrameOf("a")(1).saveDfHtmlSample()
        assertTrue(File("$TMP_DIR/resources/samples/$SAMPLE_NAME/savedHtml.html").exists())
    }

    private companion object {
        const val SAMPLE_NAME = "sampleFileName"
        val TMP_DIR: String = File(System.getProperty("java.io.tmpdir"), "kandy-sample-helper-test").path
    }
}
