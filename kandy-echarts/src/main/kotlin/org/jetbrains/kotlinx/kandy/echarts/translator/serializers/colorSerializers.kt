/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

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
            str.startsWith("rgb") -> RgbColorSerializer.deserialize(decoder)
            str.startsWith("rgba") -> RgbColorSerializer.deserialize(decoder)
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
        element("EchartsColor.RadialGradient", buildClassSerialDescriptor("EchartsColor.LinearGradient") {
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
                val x = element["x"]!!.jsonPrimitive.double
                val y = element["y"]!!.jsonPrimitive.double
                val colorStops = element["colorStops"]!!.jsonArray.map {
                    val offset = it.jsonObject["offset"]!!.jsonPrimitive.double
                    val color = it.jsonObject["color"]!!.jsonPrimitive.toEchartsColor(decoder)
                    ColorStop(offset, color)
                }
                if (element["type"]!!.jsonPrimitive.content == "linear") {
                    val x2 = element["x2"]!!.jsonPrimitive.double
                    val y2 = element["y2"]!!.jsonPrimitive.double
                    EchartsColor.LinearGradient(x, y, x2, y2, colorStops)
                } else {
                    val r = element["r"]!!.jsonPrimitive.double
                    EchartsColor.RadialGradient(x, y, r, colorStops)
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
        val rgba = rgbaString.substringAfter("(").substringBefore(")")
            .split(",").map { it.trim().toDouble() }
        return EchartsColor.Rgba(rgba[0].toInt(), rgba[1].toInt(), rgba[2].toInt(), rgba[3])
    }

    override fun serialize(encoder: Encoder, value: EchartsColor.Rgba) = encoder.encodeString(value.toString())
}