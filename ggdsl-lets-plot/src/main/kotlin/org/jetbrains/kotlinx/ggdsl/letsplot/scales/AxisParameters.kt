package org.jetbrains.kotlinx.ggdsl.letsplot.scales

import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.LetsPlotPositionalMappingParameters
import org.jetbrains.kotlinx.ggdsl.letsplot.scales.guide.Axis

public open class AxisParameters(
    private val mappingParameters: LetsPlotPositionalMappingParameters<Any?>,
): PositionalMappingParameters<Any?> by mappingParameters {
    public var limits: ClosedRange<*>? = null
        set(value) {
            mappingParameters.scale = PositionalContinuousScale(value?.start,
                value?.endInclusive,
                null, null)
            field = value
        }
   public val axis: Axis<Any?>
        get() = mappingParameters.axis
    
}

public class AxisParametersWithSetter(
    mappingParameters: LetsPlotPositionalMappingParameters<Any?>,
    private val aesName: AesName,
    private val bindingContext: BindingContext,
): AxisParameters(mappingParameters) {
    public fun constant(value: Any?) {
        bindingContext.addPositionalSetting(aesName, value)
    }
}
