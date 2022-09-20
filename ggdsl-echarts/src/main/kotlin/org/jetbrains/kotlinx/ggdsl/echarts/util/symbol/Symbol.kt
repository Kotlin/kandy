package org.jetbrains.kotlinx.ggdsl.echarts.util.symbol

public data class Symbol internal constructor(val name: String) {
    public companion object {
        public val CIRCLE: Symbol = Symbol("circle")
        public val TRIANGLE: Symbol = Symbol("triangle")
        public val RECT: Symbol = Symbol("rect")
        public val DIAMOND: Symbol = Symbol("diamond")
        public val ROUND_RECT: Symbol = Symbol("roundRect")
        public val PIN: Symbol = Symbol("pin")
        public val ARROW: Symbol = Symbol("arrow")
        public val NONE: Symbol = Symbol("none")
    }
}
