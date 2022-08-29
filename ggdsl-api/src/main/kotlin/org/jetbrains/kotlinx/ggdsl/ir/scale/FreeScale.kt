package org.jetbrains.kotlinx.ggdsl.ir.scale

import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import kotlin.reflect.KType

sealed interface FreeScale {
    val aes: AesName
    val scale: Scale
    val domainType: KType
    var scaleParameters: ScaleParameters?
}

data class FreePositionalScale<DomainType : Any>(
    override val aes: AesName,
    override val scale: PositionalScale<DomainType>,
    override val domainType: KType,
) : FreeScale {
    override var scaleParameters: ScaleParameters? = null
}
