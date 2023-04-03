package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*

public class ABLineContext(parent: LayerCollectorContext)
    : LayerContext(parent), WithType, WithColor, WithAlpha, WithWidthAsSize, WithSlope, WithIntercept

/*
/**
 * "ab-line" layer context interface.
 *
 *  @property slope line slope, positional.
 *  @property intercept line intercept, positional.
 *  @property color line color, non-positional,
 *  @property alpha layer alpha, non-positional.
 *  @property type line type, non-positional.
 *  @property width line width, non-positional.
 */
public interface ABLineContextInterface: BindingContext {
    public val slope: SlopeAes get() = SlopeAes(this)
    public val intercept: InterceptAes get() = InterceptAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val type: LineTypeAes get() = LineTypeAes(this)
    public val width: WidthAes get() = WidthAes(this)
}

/**
 * "ab-line" layer context with immutable mappings.
 *
 * @property slope line slope, positional.
 * @property intercept line intercept, positional.
 * @property color line color, non-positional,
 * @property alpha layer alpha, non-positional.
 * @property type line type, non-positional.
 * @property width line width, non-positional.
 */
/*@PlotDslMarker*/
public class ABLineContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerContextImmutable(parent), ABLineContextInterface

/**
 * "ab-line" layer context with mutable mappings.
 * @property slope line slope, positional.
 * @property intercept line intercept, positional.
 * @property color line color, non-positional,
 * @property alpha layer alpha, non-positional.
 * @property type line type, non-positional.
 * @property width line width, non-positional.
 */
/*@PlotDslMarker*/
public class ABLineContextMutable(parent: LayerCollectorContextMutable):
    LayerContextMutable(parent), ABLineContextInterface

 */