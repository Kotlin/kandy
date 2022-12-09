package org.jetbrains.kotlinx.ggdsl.dataframe.util.serialization

import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.*
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.ggdsl.dataframe.*
import org.jetbrains.kotlinx.ggdsl.ir.data.CountedGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.LazyGroupedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData

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
