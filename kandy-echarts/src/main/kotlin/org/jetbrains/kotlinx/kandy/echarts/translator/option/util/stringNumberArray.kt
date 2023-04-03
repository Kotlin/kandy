/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option.util

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.translator.serializers.StringNumberArraySerializer

@Serializable(with = StringNumberArraySerializer::class)
internal sealed interface StringNumberArray

@Serializable
@JvmInline
internal value class StringValue(val value: String): StringNumberArray