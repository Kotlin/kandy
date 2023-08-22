package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.aes.Aes

public fun <T : Comparable<T>> checkInRange(aes: Aes, value: T, range: ClosedRange<T>): Unit =
    require(value in range) {
        "Value `$value` of `${aes.name}` is outside the range [${range.start}, ${range.endInclusive}]."
    }

public fun checkRequiredAes(requiredAes: Set<Aes>, layerContext: LayerContextInterface, plotContext: PlotContext?) {
    val layerAssignedAes: Set<Aes> = with(layerContext.bindingCollector) {
        mappings.keys + settings.keys
    }
    val plotAssignedAes: Set<Aes>? = plotContext?.bindingCollector?.run {
        mappings.keys + settings.keys
    }
    val assignedAes: Set<Aes> = layerAssignedAes + (plotAssignedAes ?: setOf())

    requiredAes.forEach {
        require(it in assignedAes) { "`${it.name}` is not assigned." }
    }
}
