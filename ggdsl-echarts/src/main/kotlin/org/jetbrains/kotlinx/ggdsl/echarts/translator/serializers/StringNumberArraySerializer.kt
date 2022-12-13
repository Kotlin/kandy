/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.Measurement
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringNumberArray
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringValue

internal object StringNumberArraySerializer : KSerializer<StringNumberArray> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("stringNumberArray", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): StringNumberArray {
        TODO("Not yet implemented")
    }

    @Suppress("unchecked_cast")
    override fun serialize(encoder: Encoder, value: StringNumberArray) {
        when (value) {
            is StringValue -> encoder.encodeString(value.value)
            is Measurement -> encoder.encodeSerializableValue(Measurement.serializer(), value)
        }
    }
}