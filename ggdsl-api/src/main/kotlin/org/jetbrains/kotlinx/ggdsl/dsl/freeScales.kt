package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.aes.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.scale.FreePositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalUnspecifiedScale
import kotlin.reflect.typeOf


inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
    scale: PositionalScale<DomainType>
): FreePositionalScale<DomainType> {
    val freeScale = FreePositionalScale<DomainType>(
        this.name,
        scale,
        typeOf<DomainType>()
    )
    context.bindingCollector.freeScales[this.name] = freeScale
    return freeScale
}

inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
    scale: PositionalUnspecifiedScale
): FreePositionalScale<DomainType> {
    val freeScale = FreePositionalScale<DomainType>(
        this.name,
        scale,
        typeOf<DomainType>()
    )
    context.bindingCollector.freeScales[this.name] = freeScale
    return freeScale
}


