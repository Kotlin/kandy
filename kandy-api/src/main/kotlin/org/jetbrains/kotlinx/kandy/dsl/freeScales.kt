/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl
/*
import org.jetbrains.kotlinx.kandy.ir.scale.FreePositionalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalUnspecifiedScale
import kotlin.reflect.typeOf

/**
 * TODO: will be redesigned in the near future.
 * Applies given [PositionalScale] for this aes (as [FreePositionalScale]).
 *
 * @param scale positional scale.
 * @return [FreePositionalScale]
 */
public inline operator fun <reified DomainType> ScalablePositionalAes.invoke(
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

/**
 * TODO: will be redesigned in the near future.
 * Applies given [PositionalUnspecifiedScale] for this aes (as [FreePositionalScale]).
 *
 * @param scale positional unspecified scale.
 * @return [FreePositionalScale]
 */
public inline operator fun <reified DomainType> ScalablePositionalAes.invoke(
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



 */
