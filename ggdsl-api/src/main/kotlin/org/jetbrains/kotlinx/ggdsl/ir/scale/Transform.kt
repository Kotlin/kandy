package org.jetbrains.kotlinx.ggdsl.ir.scale

sealed interface Transform

interface PositionalTransform : Transform
interface NonPositionalTransform : Transform
