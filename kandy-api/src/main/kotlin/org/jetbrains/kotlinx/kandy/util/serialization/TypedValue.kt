/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.util.serialization

/*
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import org.jetbrains.kotlinx.kandy.ir.data.AnySerializer
import kotlin.reflect.KType
import kotlin.reflect.typeOf

public object TypedValueSerializer : KSerializer<TypedValue> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("TypedList") {
        element<String>("kType")
        element<String>("values")
    }

    // todo
    private fun String.toKType(): KType {
        return when (this) {
            typeOf<Int>().toString() -> typeOf<Int>()
            typeOf<Long>().toString() -> typeOf<Long>()
            typeOf<Double>().toString() -> typeOf<Double>()
            typeOf<Float>().toString() -> typeOf<Float>()
            typeOf<Char>().toString() -> typeOf<Char>()
            typeOf<Boolean>().toString() -> typeOf<Boolean>()
            typeOf<String>().toString() -> typeOf<String>()
            typeOf<Instant>().toString() -> typeOf<Instant>()
            typeOf<LocalDateTime>().toString() -> typeOf<LocalDateTime>()
            typeOf<LocalDate>().toString() -> typeOf<LocalDate>()
            else -> TODO()
        }
    }

    override fun deserialize(decoder: Decoder): TypedValue {
        return decoder.decodeStructure(descriptor) {
            decodeElementIndex(descriptor)
            val kTypeString = decodeStringElement(descriptor, 0)
            val kType = kTypeString.toKType()
            decodeElementIndex(descriptor)
            val value = decodeSerializableElement(descriptor, 1, AnySerializer(kType))
            TypedValue(kType, value)
        }
    }

    override fun serialize(encoder: Encoder, value: TypedValue) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.kType.toString())
            encodeSerializableElement(descriptor, 1, AnySerializer(value.kType).nullable, value.value)
        }
    }

}

/**
 * Special wrapper that stores a value of some type and that type
 * in the reified representation.
 *
 * @property kType type of list elements.
 * @property value stored values.
 */
@Serializable(TypedValueSerializer::class)
public data class TypedValue(
    val kType: KType,
    val value: Any?
)

 */
