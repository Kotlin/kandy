/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model

import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParametersContinuous

/**
 * Parameters of positional mapping unattached to mapping.
 *
 * @property limits axis limits; simple version of continuous scale domain limits.
 * @property axis axis settings.
 */
public open class AxisParameters internal constructor(
    private val mappingParameters: LetsPlotPositionalMappingParametersContinuous<Any?>,
) : PositionalMappingParametersContinuous<Any?> by mappingParameters {
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

    override var scale: PositionalScale<out Any?> = mappingParameters.scale
        set(value) {
            mappingParameters.scale = value
            field = value
        }
}

/**
 * [AxisParameters] with additional setter for related aes.
 */
@Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
public class AxisParametersWithSetter internal constructor(
    mappingParameters: LetsPlotPositionalMappingParametersContinuous<Any?>,
    private val aes: Aes,
    private val bindingHandler: BindingHandler,
) : AxisParameters(mappingParameters) {
    public fun constant(value: Any?) {
        bindingHandler.addPositionalSetting(aes, value)
    }
}
