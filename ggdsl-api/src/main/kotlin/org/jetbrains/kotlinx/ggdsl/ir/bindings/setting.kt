package org.jetbrains.kotlinx.ggdsl.ir.bindings

import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonPositionalAes

/**
 * Setting base interface.
 */
sealed interface Setting

/**
 * Setting of a non-positional aesthetic attribute.
 *
 * @param T the type specifying a non-positional attribute
 * @property aes the non-positional aesthetic attribute to be set to
 * @property value the assigned value
 */
data class NonPositionalSetting<T : Any>(
    val aes: AesName,
    val value: T,
) : Setting
