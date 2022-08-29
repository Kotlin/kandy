package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.aes.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.scale.FreePositionalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalScale
import kotlin.reflect.typeOf

/**
 * Maps the given scaled source to this positional aesthetic attribute.
 *
 * @param sourceScaledPositional the mapped source scaled positional.
 */
inline operator fun <reified DomainType : Any> ScalablePositionalAes.invoke(
    scale: PositionalScale<DomainType>
): FreePositionalScale<DomainType> {
    val freeScale = FreePositionalScale(
        this.name,
        scale,
        typeOf<DomainType>()
    )
    context.bindingCollector.freeScales[this.name] = freeScale
    return freeScale
}

