/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir.bindings

import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName

/**
 * Setting base interface.
 */
public sealed interface Setting

/**
 * Setting of a non-positional aesthetic attribute.
 *
 * @param T the type specifying a non-positional attribute
 * @property aes the non-positional aesthetic attribute to be set to
 * @property value the assigned value
 */
public data class NonPositionalSetting<T : Any>(
    val aes: AesName,
    val value: T,
) : Setting

//TODO
public data class PositionalSetting<T : Any>(
    val aes: AesName,
    val value: T,
) : Setting
