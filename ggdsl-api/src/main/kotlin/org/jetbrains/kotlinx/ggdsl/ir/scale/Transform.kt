package org.jetbrains.kotlinx.ggdsl.ir.scale

// TODO
sealed interface Transform

interface PositionalTransform : Transform
interface NonPositionalTransform : Transform
