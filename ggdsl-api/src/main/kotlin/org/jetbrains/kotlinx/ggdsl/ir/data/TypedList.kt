package org.jetbrains.kotlinx.ggdsl.ir.data

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlin.reflect.KType
import kotlin.reflect.typeOf

public class AnySerializer(public val type: KType) : KSerializer<Any> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Any", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Any {
        return when (type) {
            typeOf<Int>() -> decoder.decodeInt()
            //typeOf<Short>() -> decoder.decodeShort()
            typeOf<Long>() -> decoder.decodeLong()
            typeOf<Double>() -> decoder.decodeDouble()
            typeOf<Float>() -> decoder.decodeFloat()
            //  typeOf<Byte>() -> decoder.decodeByte()
            //typeOf<Number>() -> TODO()
            typeOf<Char>() -> decoder.decodeChar()
            typeOf<Boolean>() -> decoder.decodeBoolean()
            typeOf<String>() -> decoder.decodeString()
            typeOf<Instant>() -> decoder.decodeSerializableValue(Instant.serializer())
            typeOf<LocalDateTime>() -> decoder.decodeSerializableValue(LocalDateTime.serializer())
            typeOf<LocalDate>() -> decoder.decodeSerializableValue(LocalDate.serializer())
            else -> TODO()
        }
    }

    override fun serialize(encoder: Encoder, value: Any) {
        return when (type) {
            typeOf<Int>() -> encoder.encodeInt(value as Int)
            typeOf<Long>() -> encoder.encodeLong(value as Long)
            typeOf<Double>() -> encoder.encodeDouble(value as Double)
            typeOf<Float>() -> encoder.encodeFloat(value as Float)
            typeOf<Char>() -> encoder.encodeChar(value as Char)
            typeOf<Boolean>() -> encoder.encodeBoolean(value as Boolean)
            typeOf<String>() -> encoder.encodeString(value as String)
            typeOf<Instant>() -> encoder.encodeSerializableValue(Instant.serializer(), value as Instant)
            typeOf<LocalDateTime>() -> encoder.encodeSerializableValue(
                LocalDateTime.serializer(),
                value as LocalDateTime
            )

            typeOf<LocalDate>() -> encoder.encodeSerializableValue(LocalDate.serializer(), value as LocalDate)
            else -> TODO()
        }
    }

}

public object KTypeSerializer : KSerializer<KType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("kType", PrimitiveKind.STRING)

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

    override fun deserialize(decoder: Decoder): KType {
        return decoder.decodeString().toKType()
    }

    override fun serialize(encoder: Encoder, value: KType) {
        encoder.encodeString(value.toString())
    }

}

public object TypedListSerializer : KSerializer<TypedList> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("TypedList") {
        element<String>("kType")
        element<List<String>>("values")
    }

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

    override fun deserialize(decoder: Decoder): TypedList {
        return decoder.decodeStructure(descriptor) {
            decodeElementIndex(descriptor)
            val kTypeString = decodeStringElement(descriptor, 0)
            val kType = kTypeString.toKType()
            decodeElementIndex(descriptor)
            val list = decodeSerializableElement(descriptor, 1, ListSerializer(AnySerializer(kType)))
            TypedList(kType, list)
        }
    }

    override fun serialize(encoder: Encoder, value: TypedList) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.kType.toString())
            encodeSerializableElement(descriptor, 1, ListSerializer(AnySerializer(value.kType)), value.values)
        }
    }

}

/**
 * Special wrapper that stores a list of elements of some type and that type
 * in the reified representation.
 *
 * @property kType type of list elements.
 * @property values [List] of values.
 */
@Serializable(with = TypedListSerializer::class)
public data class TypedList(
    val kType: KType,
    val values: List<Any>,
)
