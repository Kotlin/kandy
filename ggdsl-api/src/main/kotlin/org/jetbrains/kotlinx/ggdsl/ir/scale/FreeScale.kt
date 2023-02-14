/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir.scale

import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import kotlin.reflect.KType

/**
 * TODO: will be redesigned in the near future.
 * Free scale, i.e scale without a mapping.
 */
public sealed interface FreeScale {
    public val aes: AesName
    public val scale: Scale
    public val domainType: KType
    public var scaleParameters: ScaleParameters?
}

/**
 * TODO: will be redesigned in the near future.
 * Free positional scale, i.e scale without a mapping.
 */
//@Serializable
public data class FreePositionalScale<DomainType>(
    override val aes: AesName,
    override val scale: Scale,
    override val domainType: KType,
) : FreeScale {
    override var scaleParameters: ScaleParameters? = null
}
