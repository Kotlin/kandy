/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl


/*
/**
 * Sets the given constant value to this non-positional aesthetic attribute.
 *
 * @param value the assigned value.
 */
public inline operator fun <reified T> NonPositionalAes<T>.invoke(value: T) {
    context.bindingCollector.settings[this.name] = NonPositionalSetting<T>(this.name, value)
}

/**
 * Sets the given constant value to this positional aesthetic attribute.
 *
 * @param value the assigned value.
 */
public inline operator fun <reified T : Number> PositionalAes.invoke(value: T) {
    context.bindingCollector.settings[this.name] = PositionalSetting<T>(this.name, value)
}

 */
