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
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.BaseColor
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.EchartsColor
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.GradientColor

internal object ColorSerializer : KSerializer<EchartsColor> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("color", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): EchartsColor {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: EchartsColor) {
        when (value) {
            is BaseColor -> encoder.encodeString(value.hex)
            is GradientColor -> encoder.encodeSerializableValue(GradientColor.serializer(), value)
        }
    }
}