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
import org.jetbrains.kotlinx.ggdsl.echarts.settings.*
import org.jetbrains.kotlinx.ggdsl.echarts.settings.DoubleUnit
import org.jetbrains.kotlinx.ggdsl.echarts.settings.IntUnit

internal object SizeUnitSerializer : KSerializer<SizeUnit> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("sizeUnit", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): SizeUnit {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: SizeUnit) {
        when (value) {
            is Pixel -> encoder.encodeInt(value.pixels)
            is IntUnit -> encoder.encodeInt(value.value)
            is DoubleUnit -> encoder.encodeDouble(value.value)
            is Percentage -> encoder.encodeSerializableValue(Percentage.serializer(), value)
        }
    }
}

internal object PercentageSerializer : KSerializer<Percentage> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("percentage", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Percentage {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: Percentage) {
        encoder.encodeString("${value.percentage}%")
    }
}