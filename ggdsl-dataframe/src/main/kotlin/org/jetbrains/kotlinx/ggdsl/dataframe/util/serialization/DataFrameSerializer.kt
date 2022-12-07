package org.jetbrains.kotlinx.ggdsl.dataframe.util.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.io.readJsonStr
import org.jetbrains.kotlinx.dataframe.io.toJson

public object DataFrameSerializer: KSerializer<DataFrame<*>> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("dataframe", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): DataFrame<*> {
        val jsonDataFrame = decoder.decodeString()
        return DataFrame.readJsonStr(jsonDataFrame)
    }

    override fun serialize(encoder: Encoder, value: DataFrame<*>) {
        encoder.encodeString(value.toJson())
    }
}
