package org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.BaseColor
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.Color
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.GradientColor

internal object ColorSerializer : KSerializer<Color> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("color", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Color {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: Color) {
        when (value) {
            is BaseColor -> encoder.encodeString(value.hex)
            is GradientColor -> encoder.encodeSerializableValue(GradientColor.serializer(), value)
        }
    }
}