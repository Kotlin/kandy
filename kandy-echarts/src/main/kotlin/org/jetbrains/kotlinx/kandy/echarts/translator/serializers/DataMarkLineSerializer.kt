/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.listSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.marks.DataMarkArea
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings.marks.DataMarkPoint

@OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
internal object DataMarkAreaSerializer : KSerializer<DataMarkArea> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("DataMarkLine") {
        element("", listSerialDescriptor<DataMarkPoint>())
    }

    override fun deserialize(decoder: Decoder): DataMarkArea {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: DataMarkArea): Unit {
        encoder.encodeNullableSerializableValue(ListSerializer(DataMarkPoint.serializer()), value.listOfPoints)
    }
}