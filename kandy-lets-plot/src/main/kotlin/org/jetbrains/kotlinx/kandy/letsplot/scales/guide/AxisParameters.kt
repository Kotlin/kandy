/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.scales.guide

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParameters

/**
 * Parameters of positional mapping unattached to mapping.
 *
 * @property limits axis limits; simple version of continuous scale domain limits.
 * @property axis axis settings.
 */
public open class AxisParameters(
    private val mappingParameters: LetsPlotPositionalMappingParameters<Any?>,
) : PositionalMappingParameters<Any?> by mappingParameters {
    public var limits: ClosedRange<*>? = null
        set(value) {
            mappingParameters.scale = PositionalContinuousScale(
                value?.start,
                value?.endInclusive,
                null, null
            )
            field = value
        }
    public val axis: Axis<Any?>
        get() = mappingParameters.axis

}

/**
 * [AxisParameters] with additional setter for related aes.
 */
public class AxisParametersWithSetter(
    mappingParameters: LetsPlotPositionalMappingParameters<Any?>,
    private val aesName: AesName,
    private val bindingContext: BindingContext,
) : AxisParameters(mappingParameters) {
    public fun constant(value: Any?) {
        bindingContext.addPositionalSetting(aesName, value)
    }
}
