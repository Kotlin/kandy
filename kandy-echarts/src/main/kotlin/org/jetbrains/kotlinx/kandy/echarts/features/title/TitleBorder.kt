/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.features.title

import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

/**
 * Border settings for title.
 *
 * @property color border [color][Color].
 * @property width border width. By default `1`.
 * @property radius border radius. By default `0`.
 *
 * @see Color
 */
public class TitleBorder(
    public var color: Color? = null,
    public var width: Double? = null,
    public var radius: Double? = null
) : SelfInvocationContext {

    internal fun isEmpty(): Boolean =
        color == null && width == null && radius == null

    internal fun isNotEmpty(): Boolean = !isEmpty()
}