/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir.scale

import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import kotlin.reflect.KType

sealed interface FreeScale {
    val aes: AesName
    val scale: Scale
    val domainType: KType
    var scaleParameters: ScaleParameters?
}

data class FreePositionalScale<DomainType: Any>(
    override val aes: AesName,
    override val scale: Scale,
    override val domainType: KType,
) : FreeScale {
    override var scaleParameters: ScaleParameters? = null
}
