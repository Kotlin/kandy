/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.translator.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.marks.DataMarkPoint

@OptIn(ExperimentalSerializationApi::class)
internal object DataMarkPointSerializer : KSerializer<DataMarkPoint> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("DataMarkPoint") {
        element<String>("name")
        element<String>("type")
        element<String>("value")
        element<List<Double>>("coord")
        element<String>("x")
        element<String>("y")
        element<String>("xAxis")
        element<String>("yAxis")
    }

    override fun deserialize(decoder: Decoder): DataMarkPoint {
        TODO("Not yet implemented")
    }

    @Suppress("UNCHECKED_CAST")
    override fun serialize(encoder: Encoder, value: DataMarkPoint): Unit {
        encoder.encodeStructure(descriptor) {
            value.name?.let { encodeStringElement(descriptor, 0, it) }
            value.type?.let { encodeStringElement(descriptor, 1, it) }
            value.value?.let { encodeStringElement(descriptor, 2, it) }

            value.coord?.let {
                when {
                    value.coord[0]::class == Int::class && value.coord[1]::class == Int::class ->
                        encodeNullableSerializableElement(descriptor, 3, ListSerializer(Int.serializer()), value.coord as List<Int>)
                    value.coord[0]::class == Float::class || value.coord[1]::class == Float::class ->
                        encodeNullableSerializableElement(descriptor, 3, ListSerializer(Float.serializer()), value.coord as List<Float>)
                    value.coord[0]::class == Double::class || value.coord[1]::class == Double::class ->
                        encodeNullableSerializableElement(descriptor, 3, ListSerializer(Double.serializer()), value.coord as List<Double>)
                    value.coord[0]::class == Long::class && value.coord[1]::class == Long::class ->
                        encodeNullableSerializableElement(descriptor, 3, ListSerializer(Long.serializer()), value.coord as List<Long>)
                    value.coord[0]::class == Short::class && value.coord[1]::class == Short::class ->
                        encodeNullableSerializableElement(descriptor, 3, ListSerializer(Short.serializer()), value.coord as List<Short>)
                    value.coord[0]::class == Byte::class && value.coord[1]::class == Byte::class ->
                        encodeNullableSerializableElement(descriptor, 3, ListSerializer(Byte.serializer()), value.coord as List<Byte>)
                }
            }

            value.x?.let { encodeStringElement(descriptor, 4, it) }
            value.y?.let { encodeStringElement(descriptor, 5, it) }
            value.xAxis?.let { encodeStringElement(descriptor, 6, it) }
            value.yAxis?.let { encodeStringElement(descriptor, 7, it) }
        }
    }
}