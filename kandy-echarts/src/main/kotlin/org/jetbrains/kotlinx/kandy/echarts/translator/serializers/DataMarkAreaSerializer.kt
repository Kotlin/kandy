/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.descriptors.listSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.marks.DataMarkLine
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.marks.DataMarkPoint

@OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
internal object DataMarkLineSerializer : KSerializer<DataMarkLine> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("DataMarkLine") {
        element<String>("name")
        element<String>("type")
        element<Double>("xAxis")
        element<Double>("yAxis")
        element("", listSerialDescriptor<DataMarkPoint>())
    }

    override fun deserialize(decoder: Decoder): DataMarkLine {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: DataMarkLine): Unit {
        if (value.listOfPoints.isNullOrEmpty()) {
            encoder.encodeStructure(descriptor) {
                value.name?.let { encodeStringElement(descriptor, 0, it) }
                value.type?.let { encodeStringElement(descriptor, 1, it) }
                value.xAxis?.let { encodeDoubleElement(descriptor, 2, it) }
                value.yAxis?.let { encodeDoubleElement(descriptor, 3, it) }
            }
        } else {
            encoder.encodeSerializableValue(ListSerializer(DataMarkPoint.serializer()), value.listOfPoints)
        }
    }
}