package org.jetbrains.kotlinx.ggdsl.dataframe.util.serialization

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.jetbrains.kotlinx.ggdsl.dataframe.data.DataFrameWrapper
import org.jetbrains.kotlinx.ggdsl.dataframe.data.LazyGroupedDataFrame
import org.jetbrains.kotlinx.ggdsl.ir.data.CountedGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
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
        polymorphic(NamedDataInterface::class) {
            subclass(DataFrameWrapper.serializer())
        }
        polymorphic(LazyGroupedDataInterface::class) {
            subclass(LazyGroupedDataFrame.serializer())
        }
        polymorphic(CountedGroupedDataInterface::class) {
            // TODO subclass(GroupedByWrapper.serializer(PolymorphicSerializer(Any::class), PolymorphicSerializer(Any::class)))
        }
    }
