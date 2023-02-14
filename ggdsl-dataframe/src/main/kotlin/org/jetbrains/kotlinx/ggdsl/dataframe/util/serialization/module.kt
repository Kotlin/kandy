package org.jetbrains.kotlinx.ggdsl.dataframe.util.serialization

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.jetbrains.kotlinx.ggdsl.dataframe.data.DataFrameWrapper
import org.jetbrains.kotlinx.ggdsl.dataframe.data.LazyGroupedDataFrame
import org.jetbrains.kotlinx.ggdsl.ir.data.CountedGroupedData
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedData
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData

/**
 * [SerializersModule] for IR elements from DataFrame API.
 */
public val dataframeModule: SerializersModule
    get() = SerializersModule {
        polymorphic(TableData::class) {
            subclass(DataFrameWrapper.serializer())
            subclass(LazyGroupedDataFrame.serializer())
            // TODO subclass(GroupedByWrapper.serializer(PolymorphicSerializer(Any::class), PolymorphicSerializer(Any::class)))
        }
        polymorphic(NamedData::class) {
            subclass(DataFrameWrapper.serializer())
        }
        polymorphic(LazyGroupedData::class) {
            subclass(LazyGroupedDataFrame.serializer())
        }
        polymorphic(CountedGroupedData::class) {
            // TODO subclass(GroupedByWrapper.serializer(PolymorphicSerializer(Any::class), PolymorphicSerializer(Any::class)))
        }
    }
