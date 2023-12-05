package org.jetbrains.kotlinx.kandy.letsplot.samples.guides

import org.apache.commons.math3.distribution.NormalDistribution
import org.apache.commons.math3.distribution.UniformRealDistribution
import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper
import kotlin.test.assertNotNull

class Smoothing : SampleHelper("stat", "guides") {

    @Test
    fun guideStatGenerateSampleAndWeights() {
        // SampleStart
        // Generate sample from normal distribution
        val sample = NormalDistribution().sample(1000).toList()
        // Generate weights from uniform distribution
        val weights = UniformRealDistribution(0.0, 1.0).sample(1000).toList()
        // SampleEnd
        assertNotNull(sample)
        assertNotNull(weights)
    }
}