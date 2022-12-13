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
import org.jetbrains.kotlinx.ggdsl.echarts.settings.SizeUnit
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.Measurement

internal object MeasurementSerializer : KSerializer<Measurement> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("measurement", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Measurement {
        TODO("Not yet implemented")
    }

    @Suppress("unchecked_cast")
    override fun serialize(encoder: Encoder, value: Measurement) {
        when (value) {
            is Measurement.Single<*> -> encoder.encodeSerializableValue(
                Measurement.Single.serializer(SizeUnit.serializer()),
                value as Measurement.Single<SizeUnit>
            )

            is Measurement.Pair<*> -> encoder.encodeSerializableValue(
                Measurement.Pair.serializer(SizeUnit.serializer()),
                value as Measurement.Pair<SizeUnit>
            )

            is Measurement.Rectangle<*> -> encoder.encodeSerializableValue(
                Measurement.Rectangle.serializer(SizeUnit.serializer()),
                value as Measurement.Rectangle<SizeUnit>
            )
        }
    }
}
