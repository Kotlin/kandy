package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.aes.AesName

public fun<T: Comparable<T>> checkInRange(aesName: AesName, value: T, range: ClosedRange<T>){
    if (value !in range) {
        error("Value \"$value\" of \"${aesName.name}\" is outside the range ${
            range.let { 
                "[${it.start}, ${it.endInclusive}]"
            }
        }.")
    }
}

public fun checkRequiredAes(requiredAes: Set<AesName>, layerContext: LayerContextInterface, plotContext: PlotContext?) {
    val assignedAes: Set<AesName> = layerContext.bindingCollector.let {
        it.mappings.keys + it.settings.keys
    } + (plotContext?.bindingCollector?.let {
        it.mappings.keys + it.settings.keys
    } ?: setOf<AesName>())
    requiredAes.forEach {
        if (it !in assignedAes) {
            error("\"${it.name}\" is not assigned.")
        }
    }
}
