package org.jetbrains.kotlinx.kandy.echarts.translator.serializers

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonTransformingSerializer
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.Element

internal object IntListSerializer : JsonTransformingSerializer<List<Int>>(ListSerializer(Int.serializer())) {
    override fun transformDeserialize(element: JsonElement): JsonElement =
        if (element !is JsonArray) JsonArray(listOf(element)) else element
    override fun transformSerialize(element: JsonElement): JsonElement {
        require(element is JsonArray) // this serializer is used only with lists
        return element.singleOrNull() ?: element
    }
}

internal object DataElementListSerializer :
    JsonTransformingSerializer<List<List<Element>>>(ListSerializer(ListSerializer(Element.serializer()))) {
    override fun transformDeserialize(element: JsonElement): JsonElement =
        if (element !is JsonArray) JsonArray(listOf(element)) else element
    override fun transformSerialize(element: JsonElement): JsonElement {
        require(element is JsonArray)
        return element.singleOrNull() ?: element
    }
}

internal object ElementListSerializer :
    JsonTransformingSerializer<List<Element>>(ListSerializer(Element.serializer())) {
    override fun transformDeserialize(element: JsonElement): JsonElement =
        if (element !is JsonArray) JsonArray(listOf(element)) else element
    override fun transformSerialize(element: JsonElement): JsonElement {
        require(element is JsonArray)
        return element.singleOrNull() ?: element
    }
}