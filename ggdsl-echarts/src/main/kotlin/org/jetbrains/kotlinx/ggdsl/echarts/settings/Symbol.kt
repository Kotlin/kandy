/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.settings

import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.Measurement
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.pairOf
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.singleOf
import java.net.URI
import java.net.URL

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
        public fun emptyCircle(size: Double, rotate: Int? = null): Symbol = Symbol("emptyCircle", size, rotate)
        public fun emptyCircle(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("emptyCircle", width, height, rotate)

        public val CIRCLE: Symbol = Symbol("circle")
        public fun circle(size: Double, rotate: Int? = null): Symbol = Symbol("circle", size, rotate)
        public fun circle(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("circle", width, height, rotate)

        public val TRIANGLE: Symbol = Symbol("triangle")
        public fun triangle(size: Double, rotate: Int? = null): Symbol = Symbol("triangle", size, rotate)
        public fun triangle(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("triangle", width, height, rotate)

        public val RECT: Symbol = Symbol("rect")
        public fun rect(size: Double, rotate: Int? = null): Symbol = Symbol("rect", size, rotate)
        public fun rect(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("rect", width, height, rotate)

        public val DIAMOND: Symbol = Symbol("diamond")
        public fun diamond(size: Double, rotate: Int? = null): Symbol = Symbol("diamond", size, rotate)
        public fun diamond(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("diamond", width, height, rotate)

        public val ROUND_RECT: Symbol = Symbol("roundRect")
        public fun roundRect(size: Double, rotate: Int? = null): Symbol = Symbol("roundRect", size, rotate)
        public fun roundRect(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("roundRect", width, height, rotate)

        public val PIN: Symbol = Symbol("pin")
        public fun pin(size: Double, rotate: Int? = null): Symbol = Symbol("pin", size, rotate)
        public fun pin(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("pin", width, height, rotate)

        public val ARROW: Symbol = Symbol("arrow")
        public fun arrow(size: Double, rotate: Int? = null): Symbol = Symbol("arrow", size, rotate)
        public fun arrow(width: Double, height: Double, rotate: Int? = null): Symbol =
            Symbol("arrow", width, height, rotate)

        public fun fromUrl(url: String, size: Double? = null, rotate: Int? = null): Symbol =
            Symbol("image://$url", size, rotate)

        public fun fromUrl(url: String, width: Double? = null, height: Double? = null, rotate: Int? = null): Symbol =
            Symbol("image://$url", width, height, rotate)

        public fun fromUrl(url: URL, size: Double? = null, rotate: Int? = null): Symbol =
            fromUrl(url.path, size, rotate)

        public fun fromUrl(url: URL, width: Double? = null, height: Double? = null, rotate: Int? = null): Symbol =
            fromUrl(url.path, width, height, rotate)

        public fun fromUrl(url: URI, size: Double? = null, rotate: Int? = null): Symbol =
            fromUrl(url.path, size, rotate)

        public fun fromUrl(url: URI, width: Double? = null, height: Double? = null, rotate: Int? = null): Symbol =
            fromUrl(url.path, width, height, rotate)

        public fun fromPath(path: String, size: Double? = null, rotate: Int? = null): Symbol =
            Symbol("path://$path", size, rotate)

        public fun fromPath(path: String, width: Double? = null, height: Double? = null, rotate: Int? = null): Symbol =
            Symbol("path://$path", width, height, rotate)
    }

    internal fun getSize(): Measurement? =
        when {
            width != null && height != null -> pairOf(width, height)
            size != null -> singleOf(size)
            else -> null
        }
}
