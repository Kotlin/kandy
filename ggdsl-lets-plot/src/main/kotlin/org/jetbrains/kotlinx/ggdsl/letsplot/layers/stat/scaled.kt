/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat

import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalUnspecifiedScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale


public inline fun <reified DomainType : Any> Stat<DomainType>.scaled(): SourceScaledUnspecifiedDefault<DomainType> =
    SourceScaledUnspecifiedDefault(this.toDataSource())


public inline fun <reified DomainType : Any> Stat<DomainType>.scaled(scale: PositionalUnspecifiedScale): SourceScaledPositionalUnspecified<DomainType> =
    SourceScaledPositionalUnspecified(this.toDataSource(), scale)


public inline fun <reified DomainType : Any> Stat<DomainType>.scaled(scale: NonPositionalUnspecifiedScale): SourceScaledNonPositionalUnspecified<DomainType> =
    SourceScaledNonPositionalUnspecified(this.toDataSource(), scale)


public inline fun <reified DomainType : Any> Stat<DomainType>.scaled(
    scale: PositionalScale<DomainType>
): SourceScaledPositional<DomainType> = SourceScaledPositional(this.toDataSource(), scale)


public inline fun <reified DomainType : Any, RangeType : Any> Stat<DomainType>.scaled(
    scale: NonPositionalScale<DomainType, RangeType>
): SourceScaledNonPositional<DomainType, RangeType> = SourceScaledNonPositional(this.toDataSource(), scale)