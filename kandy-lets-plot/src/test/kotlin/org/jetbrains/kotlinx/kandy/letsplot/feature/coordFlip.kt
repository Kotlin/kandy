package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.dsl.plot
import kotlin.test.Test
import kotlin.test.assertEquals

class CoordFlipTest {
    @Test
    fun `test coord flip feature`() {
        val plot = plot { coordFlip() }
        assertEquals(CoordFlip, plot.features[CoordFlip.FEATURE_NAME])
    }
}