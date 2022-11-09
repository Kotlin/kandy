/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.BindingContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName

/**
 * Base interface for aesthetic attribute.
 *
 * @property name the name of this attribute
 */
public sealed interface Aes {
    public val name: AesName
    public val context: BindingContext
}

/**
 * Interface for aesthetic attributes that can be mapped to.
 *
 * @property name the name of this attribute
 */
public sealed interface MappableAes : Aes

/**
 * Interface for aesthetic attributes that can be given an explicit scale.
 *
 * @property name the name of this attribute
 */
public sealed interface ScalableAes : MappableAes

/**
 * Interface for positional aesthetic attributes.
 *
 * @property name the name of this attribute
 */
public sealed interface PositionalAes : MappableAes

/**
 * Ordinary positional aesthetic attribute.
 *
 * @property name the name of this attribute
 */
public interface ScalablePositionalAes : PositionalAes, ScalableAes

/**
 * Positional aesthetic attribute with an implicit scale ("sub-positional").
 *
 * @property name the name of this attribute
 */
public interface NonScalablePositionalAes : PositionalAes

// todo interface and data
/**
 * Non-positional aesthetic attribute.
 *
 * @property name the name of this attribute
 */
public interface NonPositionalAes<in T : Any> : Aes

/**
 * Non-positional aesthetic attribute, that can be mapped to and have an explicit scale.
 *
 * @property name the name of this attribute
 */
public interface MappableNonPositionalAes<in T : Any> : NonPositionalAes<T>, ScalableAes

// TODO Other exists??? Todo Settable?
/*
// TODO
interface MappableOnlyNonPositionalAes<in T : Any> :  ScalableAes

 */