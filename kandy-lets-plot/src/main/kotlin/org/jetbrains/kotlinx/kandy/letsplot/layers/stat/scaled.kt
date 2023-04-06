/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.stat
/*
import org.jetbrains.kotlinx.kandy.ir.bindings.*
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalUnspecifiedScale


<<<<<<< HEAD
inline fun <reified DomainType> Stat<DomainType>.scaled() =
    SourceScaledUnspecifiedDefault(this.toColumnReference())


inline fun <reified DomainType> Stat<DomainType>.scaled(scale: PositionalUnspecifiedScale) =
    SourceScaledPositionalUnspecified(this.toColumnReference(), scale)



inline fun <reified DomainType> Stat<DomainType>.scaled(scale: NonPositionalUnspecifiedScale) =
    SourceScaledNonPositionalUnspecified(this.toColumnReference(), scale)
=======
public inline fun <reified DomainType> Stat<DomainType>.scaled(): SourceScaledUnspecifiedDefault<DomainType> =
    SourceScaledUnspecifiedDefault(this.toDataSource())


public inline fun <reified DomainType> Stat<DomainType>.scaled(scale: PositionalUnspecifiedScale): SourceScaledPositionalUnspecified<DomainType> =
    SourceScaledPositionalUnspecified(this.toDataSource(), scale)


public inline fun <reified DomainType> Stat<DomainType>.scaled(scale: NonPositionalUnspecifiedScale): SourceScaledNonPositionalUnspecified<DomainType> =
    SourceScaledNonPositionalUnspecified(this.toDataSource(), scale)
>>>>>>> main


public inline fun <reified DomainType> Stat<DomainType>.scaled(
    scale: PositionalScale<DomainType>
<<<<<<< HEAD
) = SourceScaledPositional(this.toColumnReference(), scale)
=======
): SourceScaledPositional<DomainType> = SourceScaledPositional(this.toDataSource(), scale)
>>>>>>> main


public inline fun <reified DomainType, RangeType> Stat<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
<<<<<<< HEAD
) = SourceScaledNonPositional(this.toColumnReference(), scale)
=======
): SourceScaledNonPositional<DomainType, RangeType> = SourceScaledNonPositional(this.toDataSource(), scale)
>>>>>>> main

 */
