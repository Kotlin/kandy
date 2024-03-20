package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.aes.Aes

/**
 * Checks if a given value for an aesthetic (aes) lies within a specified range.
 * Throws an exception if the value is outside the range.
 *
 * @param T The type of the value which should be comparable.
 * @param aes The aesthetic whose value is being checked.
 * @param value The actual value of the aesthetic.
 * @param range The permissible range for the aesthetics's value.
 *
 * @throws IllegalArgumentException If the provided aesthetic value is not within the specified range.
 */
public fun <T : Comparable<T>> checkInRange(aes: Aes, value: T, range: ClosedRange<T>): Unit =
    require(value in range) {
        "Value `$value` of `${aes.name}` is outside the range [${range.start}, ${range.endInclusive}]."
    }

/**
 * Ensures that all required aesthetics are assigned either in the layer or plot context.
 * If any of the required aesthetics are not found, an exception is thrown.
 *
 * @param requiredAes A set of aesthetics that need to be assigned.
 * @param layerContext The context of the layer where the aesthetics could be assigned.
 * @param plotContext The context of the plot where the aesthetics could be assigned (optional).
 *
 * @throws IllegalArgumentException If any of the required aesthetics is not assigned in either the layer or the plot context.
 */
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
