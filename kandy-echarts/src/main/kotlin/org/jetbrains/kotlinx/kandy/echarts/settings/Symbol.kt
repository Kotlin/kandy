/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.settings

import org.jetbrains.kotlinx.kandy.echarts.settings.Symbol.Companion.ARROW
import org.jetbrains.kotlinx.kandy.echarts.settings.Symbol.Companion.CIRCLE
import org.jetbrains.kotlinx.kandy.echarts.settings.Symbol.Companion.DIAMOND
import org.jetbrains.kotlinx.kandy.echarts.settings.Symbol.Companion.EMPTY_CIRCLE
import org.jetbrains.kotlinx.kandy.echarts.settings.Symbol.Companion.PIN
import org.jetbrains.kotlinx.kandy.echarts.settings.Symbol.Companion.RECT
import org.jetbrains.kotlinx.kandy.echarts.settings.Symbol.Companion.ROUND_RECT
import org.jetbrains.kotlinx.kandy.echarts.settings.Symbol.Companion.TRIANGLE
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.Measurement
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.pairOf
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.singleOf
import java.net.URI
import java.net.URL


/**
 * Symbol.
 *
 * It can be set to URL or URI of an image.
 *
 * Icon types:
 * - [EMPTY_CIRCLE]
 * - [CIRCLE]
 * - [TRIANGLE]
 * - [RECT]
 * - [DIAMOND]
 * - [ROUND_RECT]
 * - [PIN]
 * - [ARROW]
 */
public class Symbol private constructor(
    internal val name: String,
    private val size: Double? = null,
    private val width: Double? = null,
    private val height: Double? = null,
    internal val rotate: Int? = null
) {
    private constructor(name: String, size: Double?, rotate: Int?) : this(name, size, null, null, rotate = rotate)

    private constructor(name: String, width: Double?, height: Double?, rotate: Int?) : this(
        name, null, width, height, rotate
    )

    public companion object {
        public val EMPTY_CIRCLE: Symbol = Symbol("emptyCircle")

        /**
         * Returns an [EMPTY_CIRCLE] with the given [size] and [rotate].
         */
        public fun emptyCircle(size: Double, rotate: Int? = null): Symbol = Symbol("emptyCircle", size, rotate)

        /**
         * Returns an [EMPTY_CIRCLE] with the given [width], [height] and [rotate].
         */
        public fun emptyCircle(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("emptyCircle", width, height, rotate)

        public val CIRCLE: Symbol = Symbol("circle")

        /**
         * Returns an [CIRCLE] with the given [size] and [rotate].
         */
        public fun circle(size: Double, rotate: Int? = null): Symbol = Symbol("circle", size, rotate)

        /**
         * Returns an [CIRCLE] with the given [width], [height] and [rotate].
         */
        public fun circle(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("circle", width, height, rotate)

        public val TRIANGLE: Symbol = Symbol("triangle")

        /**
         * Returns an [TRIANGLE] with the given [size] and [rotate].
         */
        public fun triangle(size: Double, rotate: Int? = null): Symbol = Symbol("triangle", size, rotate)

        /**
         * Returns an [TRIANGLE] with the given [width], [height] and [rotate].
         */
        public fun triangle(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("triangle", width, height, rotate)

        public val RECT: Symbol = Symbol("rect")

        /**
         * Returns an [RECT] with the given [size] and [rotate].
         */
        public fun rect(size: Double, rotate: Int? = null): Symbol = Symbol("rect", size, rotate)

        /**
         * Returns an [RECT] with the given [width], [height] and [rotate].
         */
        public fun rect(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("rect", width, height, rotate)

        public val DIAMOND: Symbol = Symbol("diamond")

        /**
         * Returns an [DIAMOND] with the given [size] and [rotate].
         */
        public fun diamond(size: Double, rotate: Int? = null): Symbol = Symbol("diamond", size, rotate)

        /**
         * Returns an [DIAMOND] with the given [width], [height] and [rotate].
         */
        public fun diamond(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("diamond", width, height, rotate)

        public val ROUND_RECT: Symbol = Symbol("roundRect")

        /**
         * Returns an [ROUND_RECT] with the given [size] and [rotate].
         */
        public fun roundRect(size: Double, rotate: Int? = null): Symbol = Symbol("roundRect", size, rotate)

        /**
         * Returns an [ROUND_RECT] with the given [width], [height] and [rotate].
         */
        public fun roundRect(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("roundRect", width, height, rotate)

        public val PIN: Symbol = Symbol("pin")

        /**
         * Returns an [PIN] with the given [size] and [rotate].
         */
        public fun pin(size: Double, rotate: Int? = null): Symbol = Symbol("pin", size, rotate)

        /**
         * Returns an [PIN] with the given [width], [height] and [rotate].
         */
        public fun pin(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("pin", width, height, rotate)

        public val ARROW: Symbol = Symbol("arrow")

        /**
         * Returns an [ARROW] with the given [size] and [rotate].
         */
        public fun arrow(size: Double, rotate: Int? = null): Symbol = Symbol("arrow", size, rotate)

        /**
         * Returns an [ARROW] with the given [width], [height] and [rotate].
         */
        public fun arrow(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("arrow", width, height, rotate)

        /**
         * Returns icon by [url] with the given [size] and [rotate].
         */
        public fun fromUrl(url: String, size: Double? = null, rotate: Int? = null): Symbol =
            Symbol("image://$url", size, rotate)

        /**
         * Returns icon by [url] with the given [width], [height]] and [rotate].
         */
        public fun fromUrl(url: String, width: Double? = null, height: Double? = null, rotate: Int? = null): Symbol =
            Symbol("image://$url", width, height, rotate)

        /**
         * Returns icon by [url] with the given [size] and [rotate].
         */
        public fun fromUrl(url: URL, size: Double? = null, rotate: Int? = null): Symbol =
            fromUrl(url.path, size, rotate)

        /**
         * Returns icon by [url] with the given [width], [height]] and [rotate].
         */
        public fun fromUrl(url: URL, width: Double? = null, height: Double? = null, rotate: Int? = null): Symbol =
            fromUrl(url.path, width, height, rotate)

        /**
         * Returns icon by [url] with the given [size] and [rotate].
         */
        public fun fromUrl(url: URI, size: Double? = null, rotate: Int? = null): Symbol =
            fromUrl(url.path, size, rotate)

        /**
         * Returns icon by [url] with the given [width], [height]] and [rotate].
         */
        public fun fromUrl(url: URI, width: Double? = null, height: Double? = null, rotate: Int? = null): Symbol =
            fromUrl(url.path, width, height, rotate)

        /**
         * Returns icon from [path] with the given [size] and [rotate].
         */
        public fun fromPath(path: String, size: Double? = null, rotate: Int? = null): Symbol =
            Symbol("path://$path", size, rotate)

        /**
         * Returns icon from [path] with the given [width], [height]] and [rotate].
         */
        public fun fromPath(path: String, width: Double? = null, height: Double? = null, rotate: Int? = null): Symbol =
            Symbol("path://$path", width, height, rotate)
    }

    internal fun getSize(): List<Int>? = // TODO(!!!, change to list<number>)
        when {
            width != null && height != null -> listOf(width.toInt(), height.toInt())
//                pairOf(width, height)
            size != null -> listOf(size.toInt())
            //singleOf(size)
            else -> null
        }
}
