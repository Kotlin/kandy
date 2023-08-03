package org.jetbrains.kotlinx.kandy.dsl.contexts

import io.mockk.every
import io.mockk.mockk
import org.jetbrains.kotlinx.kandy.dsl.internal.BaseContext
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetHandler
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for BaseContext interface
 */
class BaseContextTest {

    /**
     * A dummy implementation of the BaseContext interface.
     */
    private class DummyBaseContext(override val plotContext: PlotContext, override val datasetIndex: Int) : BaseContext

    /**
     * Test the implementation of the BaseContext class.
     *
     * This method tests the functionality of the BaseContext class by creating a DummyBaseContext object
     * with a mock PlotContext and dataset index. It then retrieves the dataset handler, plot context,
     * and dataset index from the DummyBaseContext object and asserts that they match the expected values.
     */
    @Test
    fun `test BaseContext implementation`() {
        val mockPlotContext = mockk<PlotContext>()
        val datasetIndex = 3
        val datasetHandler = mockk<DatasetHandler>()

        every { mockPlotContext.datasetHandlers[datasetIndex] } returns datasetHandler

        val dummyBaseContext = DummyBaseContext(mockPlotContext, datasetIndex)

        val actualDatasetHandler = dummyBaseContext.datasetHandler
        val actualPlotContext = dummyBaseContext.plotContext
        val actualDatasetIndex = dummyBaseContext.datasetIndex

        assertEquals(datasetHandler, actualDatasetHandler)
        assertEquals(mockPlotContext, actualPlotContext)
        assertEquals(datasetIndex, actualDatasetIndex)
    }
}