/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.*

internal object SeriesSerializer : KSerializer<Series> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("series", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Series {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: Series) {
        when (value) {
            is LineSeries -> encoder.encodeSerializableValue(LineSeries.serializer(), value)
            is BarSeries -> encoder.encodeSerializableValue(BarSeries.serializer(), value)
            is ScatterSeries -> encoder.encodeSerializableValue(ScatterSeries.serializer(), value)
            is BoxplotSeries -> encoder.encodeSerializableValue(BoxplotSeries.serializer(), value)
            is PieSeries -> encoder.encodeSerializableValue(PieSeries.serializer(), value)
            is CandlestickSeries -> encoder.encodeSerializableValue(CandlestickSeries.serializer(), value)
        }
    }
}
