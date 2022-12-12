package org.jetbrains.kotlinx.ggdsl.echarts.settings

import java.net.URI
import java.net.URL

public class Symbol private constructor(internal val name: String) {
    internal var size: Int? = null
        set(value) {
            if (value != null)
                field = value
        }
//    internal var arraySize: List<Int>? = null // TODO
    internal var rotate: Int? = null
        set(value) {
            if (value != null)
                field = value
        }

    private constructor(name: String, size: Int?, rotate: Int?): this(name) {
        this.size = size
        this.rotate = rotate
    }

    public companion object {
        public val EMPTY_CIRCLE: Symbol = Symbol("emptyCircle")
        public fun emptyCircle(size: Int, rotate: Int?): Symbol = Symbol("emptyCircle", size, rotate)

        public val CIRCLE: Symbol = Symbol("circle")
        public fun circle(size: Int, rotate: Int?): Symbol = Symbol("circle", size, rotate)

        public val TRIANGLE: Symbol = Symbol("triangle")
        public fun triangle(size: Int, rotate: Int?): Symbol = Symbol("triangle", size, rotate)

        public val RECT: Symbol = Symbol("rect")
        public fun rect(size: Int, rotate: Int?): Symbol = Symbol("rect", size, rotate)

        public val DIAMOND: Symbol = Symbol("diamond")
        public fun diamond(size: Int, rotate: Int?): Symbol = Symbol("diamond", size, rotate)

        public val ROUND_RECT: Symbol = Symbol("roundRect")
        public fun roundRect(size: Int, rotate: Int?): Symbol = Symbol("roundRect", size, rotate)

        public val PIN: Symbol = Symbol("pin")
        public fun pin(size: Int, rotate: Int?): Symbol = Symbol("pin", size, rotate)

        public val ARROW: Symbol = Symbol("arrow")
        public fun arrow(size: Int, rotate: Int?): Symbol = Symbol("arrow", size, rotate)

        public fun fromUrl(url: String, size: Int?, rotate: Int?): Symbol = Symbol("image://$url", size, rotate)

        public fun fromUrl(url: URL, size: Int?, rotate: Int?): Symbol = Symbol("image://$url", size, rotate)

        public fun fromUrl(url: URI, size: Int?, rotate: Int?): Symbol = Symbol("image://$url", size, rotate)

        public fun fromPath(path: String, size: Int?, rotate: Int?): Symbol = Symbol("path://$path", size, rotate)
    }

    public fun set(size: Int, rotate: Int? = null): Symbol {
        this.size = size
        this.rotate = rotate
        return this
    }

//    public fun set(size: List<Int>, rotate: Int? = null) {
//        this.arraySize = size
//        this.rotate = rotate
//    }
}
