/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.impl

/**
 * Symbol described by one string.
 *
 * @param description the string describing this symbol.
 */
data class Symbol(val description: String) {
    companion object {
        val CIRCLE = Symbol("circle")
        val TRIANGLE = Symbol("triangle")
        val RECT = Symbol("rect")
    }
}
