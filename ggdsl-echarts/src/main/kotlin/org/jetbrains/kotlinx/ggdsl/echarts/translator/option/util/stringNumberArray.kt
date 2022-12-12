package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers.StringNumberArraySerializer

@Serializable(with = StringNumberArraySerializer::class)
internal sealed interface StringNumberArray

@Serializable
@JvmInline
internal value class StringValue(val value: String): StringNumberArray