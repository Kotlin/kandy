package org.jetbrains.kotlinx.ggdsl.echarts.features.animation

public class AnimationEasing private constructor(public val name: String) {
    public companion object {
        // todo (add function?)
        public val LINEAR: AnimationEasing = AnimationEasing("linear")
        public val CUBIC_OUT: AnimationEasing = AnimationEasing("cubicOut")
        public val ELASTIC_OUT: AnimationEasing = AnimationEasing("elasticOut")
    }
}