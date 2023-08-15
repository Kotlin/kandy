package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.aes.AesName

public fun <T : Comparable<T>> checkInRange(aesName: AesName, value: T, range: ClosedRange<T>): Unit =
    require(value in range) {
        "Value `$value` of `${aesName.name}` is outside the range [${range.start}, ${range.endInclusive}]."
    }

public fun checkRequiredAes(requiredAes: Set<AesName>, layerContext: LayerContextInterface, plotContext: PlotContext?) {
    val layerAssignedAes: Set<AesName> = with(layerContext.bindingCollector) {
        mappings.keys + settings.keys
    }
    val plotAssignedAes: Set<AesName>? = plotContext?.bindingCollector?.run {
        mappings.keys + settings.keys
    }
    val assignedAes: Set<AesName> = layerAssignedAes + (plotAssignedAes ?: setOf())

    requiredAes.forEach {
        require(it in assignedAes) { "`${it.name}` is not assigned." }
    }
}
