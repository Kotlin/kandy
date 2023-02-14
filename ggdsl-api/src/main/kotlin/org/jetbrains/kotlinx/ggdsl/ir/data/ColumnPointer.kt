package org.jetbrains.kotlinx.ggdsl.ir.data

/*
// todo nullable? ktype
/**
 * Pointer to the data source - a column in the table with the corresponding name.
 *
 * @param T a type of column
 * @property name the name of column in the table
 */
@Serializable(with=CPSerializer::class)
public data class ColumnReference<T>(val name: String)


//todo
public object CPSerializer: KSerializer<ColumnReference<*>> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ColumnReference") {
        element<String>("name")
    }

    override fun deserialize(decoder: Decoder): ColumnReference<*> {
        return ColumnReference<Any>(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: ColumnReference<*>) {
        encoder.encodeString(value.name)
    }

}


 */