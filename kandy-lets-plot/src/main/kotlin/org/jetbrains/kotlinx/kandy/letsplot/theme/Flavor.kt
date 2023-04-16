/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.theme

public enum class Flavor {
    DARCULA,
    SOLARIZED_LIGHT,
    SOLARIZED_DARK,
    HIGH_CONTRAST_LIGHT,
    HIGH_CONTRAST_DARK;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}
