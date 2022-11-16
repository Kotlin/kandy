package org.jetbrains.kotlinx.ggdsl.echarts.settings

import java.net.URI
import java.net.URL

public class Symbol private constructor(internal val name: String) {
    internal var size: Int? = null
//    internal var arraySize: List<Int>? = null // TODO
    internal var rotate: Int? = null
        set(value) {
            if (value != null)
                field = value
        }

    public companion object {
        public val CIRCLE: Symbol = Symbol("circle")
        public val TRIANGLE: Symbol = Symbol("triangle")
        public val RECT: Symbol = Symbol("rect")
        public val DIAMOND: Symbol = Symbol("diamond")
        public val ROUND_RECT: Symbol = Symbol("roundRect")
        public val PIN: Symbol = Symbol("pin")
        public val ARROW: Symbol = Symbol("arrow")

        public fun fromUrl(url: String): Symbol = Symbol("image://$url")

        public fun fromUrl(url: URL): Symbol = Symbol("image://$url")

        public fun fromUrl(url: URI): Symbol = Symbol("image://$url")

        public fun fromPath(path: String): Symbol = Symbol("path://$path")
    }

    public fun set(size: Int, rotate: Int? = null) {
        this.size = size
        this.rotate = rotate
    }

//    public fun set(size: List<Int>, rotate: Int? = null) {
//        this.arraySize = size
//        this.rotate = rotate
//    }
}
