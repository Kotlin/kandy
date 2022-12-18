/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName

/**
 * Aesthetic attribute (aesthetic, aes) - attributes of geometric objects on the plot.
 *
 * @property name the name of this attribute
 * @property context context of this attribute
 */
public sealed interface Aes {
    public val name: AesName
    public val context: BindingContext
}

/**
 * Aesthetic attributes that can be mapped to.
 */
public sealed interface MappableAes : Aes

/**
 * Aesthetic attributes that can be mapped to with an explicit scale.
 */
public sealed interface ScalableAes : MappableAes

/**
 * Positional aesthetic attributes.
 */
public sealed interface PositionalAes : MappableAes

/**
 * Positional aesthetic attribute that can be mapped to with an explicit scale.
 */
public interface ScalablePositionalAes : PositionalAes, ScalableAes

/**
 * Positional aesthetic attribute with an implicit scale ("sub-positional").
 */
public interface NonScalablePositionalAes : PositionalAes

/**
 * Non-positional aesthetic attribute.
 */
public interface NonPositionalAes<in T : Any> : Aes

/**
 * Non-positional aesthetic attribute that can be mapped with an explicit scale.
 */
public interface ScalableNonPositionalAes<in T : Any> : NonPositionalAes<T>, ScalableAes


/**
 * Non-positional aesthetic attribute that can be mapped but without an explicit scale.
 */
public interface NonScalableNonPositionalAes<in T : Any> :  NonPositionalAes<T>, MappableAes
