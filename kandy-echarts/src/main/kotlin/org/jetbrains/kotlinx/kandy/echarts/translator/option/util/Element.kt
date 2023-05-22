/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option.util

import kotlinx.serialization.Serializable

@Serializable
internal sealed interface Element {
    val value: Any

    @Serializable
    @JvmInline
    value class BooleanEl(override val value: Boolean) : Element

    @Serializable
    @JvmInline
    value class ByteEl(override val value: Byte) : Element

    @Serializable
    @JvmInline
    value class ShortEl(override val value: Short) : Element

    @Serializable
    @JvmInline
    value class IntEl(override val value: Int) : Element

    @Serializable
    @JvmInline
    value class LongEl(override val value: Long) : Element

    @Serializable
    @JvmInline
    value class FloatEl(override val value: Float) : Element

    @Serializable
    @JvmInline
    value class DoubleEl(override val value: Double) : Element

    @Serializable
    @JvmInline
    value class StringEl(override val value: String) : Element
}