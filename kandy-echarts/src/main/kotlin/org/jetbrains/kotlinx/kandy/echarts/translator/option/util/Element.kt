package org.jetbrains.kotlinx.kandy.echarts.translator.option.util

import kotlinx.serialization.Serializable

@Serializable
internal sealed interface Element {
    val value: Any?

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
    value class StringEl(override val value: String?) : Element

    companion object {
        fun of(value: Any?): Element = when (value) {
            is Boolean -> BooleanEl(value)
            is Byte -> ByteEl(value)
            is Short -> ShortEl(value)
            is Int -> IntEl(value)
            is Long -> LongEl(value)
            is Float -> FloatEl(value)
            is Double -> DoubleEl(value)
            is String -> StringEl(value)
            else -> { StringEl(value?.toString()) }
        }
    }
}