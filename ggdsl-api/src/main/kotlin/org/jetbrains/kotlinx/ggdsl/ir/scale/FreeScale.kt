/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir.scale

import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import kotlin.reflect.KType

public sealed interface FreeScale {
    public val aes: AesName
    public val scale: Scale
    public val domainType: KType
    public var scaleParameters: ScaleParameters?
}

// TODO (Generic type)
public data class FreePositionalScale<DomainType : Any>(
    override val aes: AesName,
    override val scale: Scale,
    override val domainType: KType,
) : FreeScale {
    override var scaleParameters: ScaleParameters? = null
}
