/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.descriptors.listSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Symbol
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.Range

@OptIn(ExperimentalSerializationApi::class)
internal object RangeSerializer : KSerializer<Range> {
    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("Range") {
            element<List<String>?>("symbol")
            element<List<Double>?>("symbolSize")
            element("color", listSerialDescriptor(ColorSerializer.descriptor))
            element<List<Double>?>("colorAlpha")
            element<List<Double>?>("opacity")
            element<List<Double>?>("colorLightness")
            element<List<Double>?>("colorSaturation")
            element<List<Double>?>("colorHue")
        }

    override fun deserialize(decoder: Decoder): Range {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: Range) {
        encoder.encodeStructure(descriptor) {
            encodeNullableSerializableElement(
                descriptor, 0, ListSerializer(String.serializer()), value.symbol?.map { (it as Symbol).name }
            )
            encodeNullableSerializableElement(
                descriptor, 1, ListSerializer(Double.serializer()), value.symbolSize?.map { (it as Number).toDouble() }
            )
            encodeNullableSerializableElement(descriptor, 2, ListSerializer(ColorSerializer), value.color)

            encodeNullableSerializableElement(
                descriptor, 3, ListSerializer(Double.serializer()), value.colorAlpha?.map { (it as Number).toDouble() }
            )
            encodeNullableSerializableElement(
                descriptor, 4, ListSerializer(Double.serializer()), value.opacity?.map { (it as Number).toDouble() }
            )
            encodeNullableSerializableElement(
                descriptor, 5, ListSerializer(Double.serializer()), value.colorLightness?.map { (it as Number).toDouble() }
            )
            encodeNullableSerializableElement(
                descriptor, 6, ListSerializer(Double.serializer()), value.colorSaturation?.map { (it as Number).toDouble() }
            )
            encodeNullableSerializableElement(
                descriptor, 7, ListSerializer(Double.serializer()), value.colorHue?.map { (it as Number).toDouble() }
            )

        }
    }

}