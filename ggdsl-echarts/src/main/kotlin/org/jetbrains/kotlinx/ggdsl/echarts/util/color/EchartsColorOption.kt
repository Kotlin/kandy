package org.jetbrains.kotlinx.ggdsl.echarts.util.color

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@kotlinx.serialization.Serializable(with = EchartsColorOptionSerializer::class)
sealed class EchartsColorOption

@kotlinx.serialization.Serializable
data class SimpleColorOption(val name: String): EchartsColorOption()

object EchartsColorOptionSerializer: KSerializer<EchartsColorOption> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("color", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): EchartsColorOption {
        // todo
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: EchartsColorOption) {
        when(value) {
            is GradientOption -> {
                encoder.encodeSerializableValue(GradientOption.serializer(), value)
            }
            is SimpleColorOption -> {
                encoder.encodeString(value.name)
            }
        }
    }
}

@kotlinx.serialization.Serializable
internal data class GradientOption(
    val type: String,
    val x: Double,
    val y: Double,

    val x2: Double? = null,
    val y2: Double? = null,
    val r: Double? = null,

    val colorStops: List<ColorStop>
): EchartsColorOption()

@kotlinx.serialization.Serializable
internal data class ColorStop(
    val offset: Double,
    val color: String,
)
