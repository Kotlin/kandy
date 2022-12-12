package org.jetbrains.kotlinx.ggdsl.util.serialization

import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.modules.*
import org.jetbrains.kotlinx.ggdsl.dsl.LazyGroupedData
import org.jetbrains.kotlinx.ggdsl.dsl.NamedData
import org.jetbrains.kotlinx.ggdsl.ir.bindings.Mapping
import org.jetbrains.kotlinx.ggdsl.ir.data.TableData
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*

//todo
public val commonModules: SerializersModule
    get() = SerializersModule {
        polymorphic(TableData::class) {
            subclass(NamedData::class)
            subclass(LazyGroupedData::class)
        }
        polymorphic(Scale::class) {
            subclass(NonPositionalContinuousScale.serializer(PolymorphicSerializer(Any::class)
                , PolymorphicSerializer(Any::class)))
            subclass(PositionalContinuousScale.serializer(PolymorphicSerializer(Any::class)))
            subclass(NonPositionalCategoricalScale.serializer(PolymorphicSerializer(Any::class)
                , PolymorphicSerializer(Any::class)))
            subclass(PositionalCategoricalScale.serializer(PolymorphicSerializer(Any::class)))
            subclass(DefaultUnspecifiedScale::class)
            subclass(NonPositionalCategoricalUnspecifiedScale::class)
            subclass(NonPositionalContinuousUnspecifiedScale::class)
            subclass(PositionalCategoricalUnspecifiedScale::class)
            subclass(PositionalContinuousUnspecifiedScale::class)
        }
        polymorphic(PositionalUnspecifiedScale::class) {
            subclass(PositionalContinuousUnspecifiedScale::class)
            subclass(PositionalCategoricalUnspecifiedScale::class)
        }
        polymorphic(PositionalScale::class) {
            subclass(PositionalContinuousScale.serializer(PolymorphicSerializer(Any::class)))
            subclass(PositionalCategoricalScale.serializer(PolymorphicSerializer(Any::class)))
        }
        polymorphic(NonPositionalUnspecifiedScale::class) {
            subclass(NonPositionalContinuousUnspecifiedScale::class)
            subclass(NonPositionalCategoricalUnspecifiedScale::class)
        }
        polymorphic(NonPositionalScale::class) {
            subclass(NonPositionalContinuousScale.serializer(PolymorphicSerializer(Any::class), PolymorphicSerializer(Any::class)))
            subclass(NonPositionalCategoricalScale.serializer(PolymorphicSerializer(Any::class), PolymorphicSerializer(Any::class)))
        }
        polymorphic(ColumnScaled::class) {
            subclass(ColumnScaledUnspecifiedDefault.serializer(PolymorphicSerializer(Any::class)))
            subclass(ColumnScaledPositionalUnspecified.serializer(PolymorphicSerializer(Any::class)))
            subclass(ColumnScaledNonPositionalUnspecified.serializer(PolymorphicSerializer(Any::class)))
            subclass(ColumnScaledPositional.serializer(PolymorphicSerializer(Any::class)))
            subclass(ColumnScaledNonPositional.serializer(PolymorphicSerializer(Any::class), PolymorphicSerializer(Any::class)))
        }
        polymorphic(Mapping::class) {
            subclass(NonScalablePositionalMapping.serializer(PolymorphicSerializer(Any::class)))
            subclass(ScaledUnspecifiedDefaultPositionalMapping.serializer(PolymorphicSerializer(Any::class)))
            subclass(ScaledUnspecifiedDefaultNonPositionalMapping.serializer(PolymorphicSerializer(Any::class), PolymorphicSerializer(Any::class)))
            subclass(ScaledPositionalUnspecifiedMapping.serializer(PolymorphicSerializer(Any::class)))
            subclass(ScaledNonPositionalUnspecifiedMapping.serializer(PolymorphicSerializer(Any::class), PolymorphicSerializer(Any::class)))
            subclass(ScaledPositionalMapping.serializer(PolymorphicSerializer(Any::class)))
            subclass(ScaledNonPositionalMapping.serializer(PolymorphicSerializer(Any::class), PolymorphicSerializer(Any::class)))
        }
        polymorphic(Setting::class) {
            subclass(NonPositionalSetting.serializer(PolymorphicSerializer(Any::class)))
            subclass(PositionalSetting.serializer(PolymorphicSerializer(Any::class)))
        }
        polymorphic(FreeScale::class) {
            subclass(FreePositionalScale.serializer(PolymorphicSerializer(Any::class)))
        }
    /*
        polymorphic(Any::class){
            subclass(Int.serializer())
            subclass(Double.serializer())
            subclass(String.serializer())
        }

     */
}
