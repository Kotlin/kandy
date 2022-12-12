package org.jetbrains.kotlinx.ggdsl.dataframe.util.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import org.jetbrains.kotlinx.dataframe.api.GroupBy
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.api.toDataFrame

public object GroupBySerializer: KSerializer<GroupBy<*, *>> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("GroupBy") {
        element<String>("keys")
        element<List<String>>("groups")
    }

    override fun deserialize(decoder: Decoder): GroupBy<*, *> {
        return decoder.decodeStructure(descriptor) {
            decodeElementIndex(descriptor)
            val keys = decodeSerializableElement(descriptor, 0, DataFrameSerializer)
            decodeElementIndex(descriptor)
            val list = decodeSerializableElement(descriptor, 1, ListSerializer(DataFrameSerializer))
            val mergedDF = list.toDataFrame()
            mergedDF.groupBy(keys.columns())
        }
    }

    override fun serialize(encoder: Encoder, value: GroupBy<*, *>) {
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, DataFrameSerializer, value.keys)
            encodeSerializableElement(descriptor, 1, ListSerializer(DataFrameSerializer), value.groups.toList())
        }
    }
}
