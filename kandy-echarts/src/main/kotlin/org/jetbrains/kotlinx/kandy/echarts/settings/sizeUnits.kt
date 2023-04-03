/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.settings

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.translator.serializers.PercentageSerializer
import org.jetbrains.kotlinx.kandy.echarts.translator.serializers.SizeUnitSerializer


public val Int.px: Pixel get() = Pixel(this)

public val Int.pct: Percentage get() = Percentage(this)

/**
 * Interface for size units.
 */
@Serializable(with = SizeUnitSerializer::class)
public sealed interface SizeUnit

/**
 * Absolute pixel values.
 */
@Serializable
@JvmInline
public value class Pixel(public val pixels: Int) : SizeUnit

/**
 * Relative percentage.
 */
@Serializable(with = PercentageSerializer::class)
@JvmInline
public value class Percentage(public val percentage: Int) : SizeUnit

@Serializable
@JvmInline
internal value class IntUnit(val value: Int) : SizeUnit

@Serializable
@JvmInline
internal value class DoubleUnit(val value: Double) : SizeUnit
