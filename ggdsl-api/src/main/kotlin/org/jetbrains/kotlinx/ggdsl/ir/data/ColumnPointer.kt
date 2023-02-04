package org.jetbrains.kotlinx.ggdsl.ir.data

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

// todo nullable? ktype
/**
 * Pointer to the data source - a column in the table with the corresponding name.
 *
 * @param T a type of column
 * @property name the name of column in the table
 */
@Serializable(with=CPSerializer::class)
public data class ColumnPointer<T>(val name: String)


//todo
public object CPSerializer: KSerializer<ColumnPointer<*>> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ColumnPointer") {
        element<String>("name")
    }

    override fun deserialize(decoder: Decoder): ColumnPointer<*> {
        return ColumnPointer<Any>(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: ColumnPointer<*>) {
        encoder.encodeString(value.name)
    }

}
