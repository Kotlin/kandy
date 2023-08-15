/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.impl

import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParameters
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalDefaultScale
import org.jetbrains.kotlinx.kandy.ir.scale.NonPositionalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalDefaultScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalScale

internal data class CommonPositionalMappingParameters<DomainType>(
    override var scale: PositionalScale<out DomainType> = PositionalDefaultScale()
) : PositionalMappingParameters<DomainType>

internal data class CommonPositionalMappingParametersContinuous<DomainType>(
    override var scale: PositionalScale<out DomainType> = PositionalDefaultScale()
) : PositionalMappingParametersContinuous<DomainType>

internal data class CommonNonPositionalMappingParameters<DomainType, RangeType>(
    override var scale: NonPositionalScale<out DomainType, out RangeType> = NonPositionalDefaultScale()
) : NonPositionalMappingParameters<DomainType, RangeType>

internal data class CommonNonPositionalMappingParametersContinuous<DomainType, RangeType>(
    override var scale: NonPositionalScale<out DomainType, out RangeType> = NonPositionalDefaultScale()
) : NonPositionalMappingParametersContinuous<DomainType, RangeType>
