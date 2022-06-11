package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.ir.aes.MappableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonPositionalAes
import org.jetbrains.kotlinx.ggdsl.util.color.Color

// fix to positional non scalable???? SubPoistional
val LOWER = MappableNonPositionalAes<Double>("lower")
val UPPER = MappableNonPositionalAes<Double>("upper")
val MIDDLE = MappableNonPositionalAes<Double>("middle")
val Y_MIN = MappableNonPositionalAes<Double>("ymin")
val Y_MAX = MappableNonPositionalAes<Double>("ymax")
val FATTEN = NonPositionalAes<Double>("fatten")

// todo
val MAPPABLE_BORDER_COLOR = MappableNonPositionalAes<Color>("border_color")