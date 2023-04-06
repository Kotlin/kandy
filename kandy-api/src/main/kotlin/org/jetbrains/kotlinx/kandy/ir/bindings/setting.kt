/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.ir.bindings

import org.jetbrains.kotlinx.kandy.ir.aes.AesName

/**
 * Setting, i.e. assigning a constant value to an attribute .
 */
public sealed interface Setting

/**
 * Setting of non-positional aesthetic attribute.
 *
 * @param T type specifying a non-positional attribute.
 * @property aes non-positional aesthetic attribute to be set to.
 * @property value the assigned value.
 */
public data class NonPositionalSetting<T>(
    val aes: AesName,
    val value: T,
) : Setting

/**
 * Setting of positional aesthetic attribute.
 *
 * @param T type specifying a positional attribute.
 * @property aes positional aesthetic attribute to be set to.
 * @property value the assigned value.
 */
public data class PositionalSetting<T>(
    val aes: AesName,
    val value: T,
) : Setting
