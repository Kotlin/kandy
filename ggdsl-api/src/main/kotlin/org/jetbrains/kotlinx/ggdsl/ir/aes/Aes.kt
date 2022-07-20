package org.jetbrains.kotlinx.ggdsl.ir.aes

import org.jetbrains.kotlinx.ggdsl.dsl.BindingContext

/**
 * Base interface for aesthetic attribute.
 *
 * @property name the name of this attribute
 */
sealed interface Aes {
    val name: String
    val context: BindingContext
}

/**
 * Interface for aesthetic attributes that can be mapped to.
 *
 * @property name the name of this attribute
 */
sealed interface MappableAes : Aes

/**
 * Interface for aesthetic attributes that can be given an explicit scale.
 *
 * @property name the name of this attribute
 */
sealed interface ScalableAes : MappableAes

/**
 * Interface for positional aesthetic attributes.
 *
 * @property name the name of this attribute
 */
sealed interface PositionalAes : MappableAes

/**
 * Ordinary positional aesthetic attribute.
 *
 * @property name the name of this attribute
 */
interface ScalablePositionalAes : PositionalAes, ScalableAes

/**
 * Positional aesthetic attribute with an implicit scale ("sub-positional").
 *
 * @property name the name of this attribute
 */
interface NonScalablePositionalAes : PositionalAes

// todo interface and data
/**
 * Non-positional aesthetic attribute.
 *
 * @property name the name of this attribute
 */
interface NonPositionalAes<in T : Any> : Aes

/**
 * Non-positional aesthetic attribute, that can be mapped to and have an explicit scale.
 *
 * @property name the name of this attribute
 */
interface MappableNonPositionalAes<in T : Any> : NonPositionalAes<T>, ScalableAes

// TODO Other exists??? Todo Settable?