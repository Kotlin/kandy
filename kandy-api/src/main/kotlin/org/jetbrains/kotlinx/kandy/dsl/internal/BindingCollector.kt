/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.ir.scale.FreeScale

/**
 * Collects and manages the [mappings][Mapping], [settings][Setting], and [free scales][FreeScale] associated with various aesthetics.
 *
 * This class is designed to internally aggregate and maintain the [aesthetic][Aes] bindings
 * required to translate data into visual representations on a plot. Each aesthetic (or "aes")
 * can have its own mapping, setting, and free scale, which dictate how the data is presented.
 *
 * @property mappings a mutable map associating aesthetics to their respective mappings.
 * @property settings a mutable map associating aesthetics to their respective settings.
 * @property freeScales a mutable map associating aesthetics to their respective free scales.
 */
internal class BindingCollector {
    val mappings: MutableMap<Aes, Mapping> = mutableMapOf()
    val settings: MutableMap<Aes, Setting> = mutableMapOf()
    val freeScales: MutableMap<Aes, FreeScale> = mutableMapOf()
}