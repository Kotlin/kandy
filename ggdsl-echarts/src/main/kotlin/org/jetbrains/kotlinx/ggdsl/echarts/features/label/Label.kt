/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.label

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.features.TextStyle
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

/**
 * Label settings for plots.
 *
 * @property position label [position][LabelPosition].
 * Includes position, distance and rotation settings.
 * `LabelPosition.position` may not apply to some types of plots.
 * By default, position is `top`, distance is `5`, rotate isn't defined
 * @property formatter data label formatter, supports string template.
 *
 * *String template*
 *
 * Model variation includes:
 *
 * - `{a}` - layers name.
 * - `{b}` - the name of a data item.
 * - `{c}` - the value of a data item.
 * - `{@xxx}` - the value of a column named _`xxx`_, for example, `{@product}` refers the value of `product` column.
 * - `{@[n]}` - the value of a column at the index of `n`, for example, `{@[3]}` refers the value at column[3].
 *
 * @property textStyle [text style][TextStyle] settings
 * @property border [border][Border] settings
 */
@PlotDslMarker
public class LabelContext(
    public var position: LabelPosition? = null,
    public var formatter: String? = null,
    public var textStyle: TextStyle = TextStyle(),
    public var border: Border = Border()

) {
    internal fun toLabelFeature(): LabelFeature? {
        val textStyle = textStyle.takeIf { it.isNotEmpty() }
        val border = border.takeIf { !it.isNotEmpty() }
        return if (position == null && formatter == null && textStyle == null && border == null)
            null
        else
            LabelFeature(position, formatter, textStyle, border)
    }
}

internal class LabelFeature(
    val position: LabelPosition?,
    val formatter: String?,
    val textStyle: TextStyle?,
    val border: Border?
) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    companion object {
        val FEATURE_NAME: FeatureName = FeatureName("LABEL_FEATURE")
    }
}
