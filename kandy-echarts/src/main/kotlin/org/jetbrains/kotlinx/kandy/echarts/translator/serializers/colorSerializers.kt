package org.jetbrains.kotlinx.kandy.echarts.translator.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.ColorStop
import org.jetbrains.kotlinx.kandy.echarts.translator.option.EchartsColor

@OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
internal object ColorSerializer : KSerializer<EchartsColor> {

    private fun JsonPrimitive.toEchartsColor(decoder: Decoder): EchartsColor {
        val str = this.content
        return when {
            str.startsWith("#") -> EchartsColor.Hex(str)
            str.startsWith("rgba") -> RgbaColorSerializer.deserialize(decoder)
            str.startsWith("rgb") -> RgbColorSerializer.deserialize(decoder)
            else -> EchartsColor.Named(str)
        }
    }

    override val descriptor: SerialDescriptor = buildSerialDescriptor("color", PolymorphicKind.SEALED) {
        element<String>("EchartsColor.Hex")
        element<String>("EchartsColor.Named")
        element<String>("EchartsColor.Rgb")
        element<String>("EchartsColor.Rgba")
        element("EchartsColor.LinearGradient", buildClassSerialDescriptor("EchartsColor.LinearGradient") {
            element<String>("type")
            element<Double>("x")
            element<Double>("y")
            element<Double>("x2")
            element<Double>("y2")
            element<List<ColorStop>>("colorStops")
        })
        element("EchartsColor.RadialGradient", buildClassSerialDescriptor("EchartsColor.RadialGradient") {
            element<String>("type")
            element<Double>("x")
            element<Double>("y")
            element<Double>("r")
            element<List<ColorStop>>("colorStops")
        })
    }

    override fun deserialize(decoder: Decoder): EchartsColor {
        // this class can be decoded only by Json
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        return when {
            element is JsonPrimitive -> element.toEchartsColor(decoder)
            element is JsonObject && "type" in element -> {
                val x = element["x"]?.jsonPrimitive?.double
                val y = element["y"]?.jsonPrimitive?.double

                val colorStops = element["colorStops"]?.jsonArray?.map {
                    val offset = it.jsonObject["offset"]?.jsonPrimitive?.double
                        ?: throw SerializationException("Missing or invalid 'offset' in colorStop")
                    val color = it.jsonObject["color"]?.jsonPrimitive?.let { primitive ->
                        primitive.toEchartsColor(decoder)
                    } ?: throw SerializationException("Missing or invalid 'color' in colorStop")
                    ColorStop(offset, color)
                } ?: emptyList()

                val type = element["type"]?.jsonPrimitive?.content
                    ?: throw SerializationException("Missing or invalid 'type' in gradient")

                if (type == "linear") {
                    val x2 = element["x2"]?.jsonPrimitive?.double
                    val y2 = element["y2"]?.jsonPrimitive?.double
                    EchartsColor.LinearGradient(x, y, x2, y2, colorStops)
                } else if (type == "radial") {
                    val r = element["r"]?.jsonPrimitive?.double
                    EchartsColor.RadialGradient(x, y, r, colorStops)
                } else {
                    throw SerializationException("Unknown gradient type: $type")
                }
            }

            else -> throw SerializationException("Unknown json object")
        }
    }

    override fun serialize(encoder: Encoder, value: EchartsColor) {
        when (value) {
            is EchartsColor.Hex -> encoder.encodeString(value.hex)
            is EchartsColor.Named -> encoder.encodeString(value.name)
            is EchartsColor.Rgb -> encoder.encodeString(value.toString())
            is EchartsColor.Rgba -> encoder.encodeString(value.toString())
            is EchartsColor.Gradient -> encoder.encodeSerializableValue(EchartsColor.Gradient.serializer(), value)
        }
    }

}


internal object RgbColorSerializer : KSerializer<EchartsColor.Rgb> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("RgbColor", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): EchartsColor.Rgb {
        val rgbString = decoder.decodeString()
        val (r, g, b) = rgbString.substringAfter("(").substringBefore(")")
            .split(",").map { it.trim().toInt() }
        return EchartsColor.Rgb(r, g, b)
    }

    override fun serialize(encoder: Encoder, value: EchartsColor.Rgb) = encoder.encodeString(value.toString())
}

internal object RgbaColorSerializer : KSerializer<EchartsColor.Rgba> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("RgbaColor", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): EchartsColor.Rgba {
        val rgbaString = decoder.decodeString()
        val (r, g, b, a) = rgbaString.substringAfter("(").substringBefore(")")
            .split(",").map { it.trim().toDouble() }
        return EchartsColor.Rgba(r.toInt(), g.toInt(), b.toInt(), a)
    }

    override fun serialize(encoder: Encoder, value: EchartsColor.Rgba) = encoder.encodeString(value.toString())
}
