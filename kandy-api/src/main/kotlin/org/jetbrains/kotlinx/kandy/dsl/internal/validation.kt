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

