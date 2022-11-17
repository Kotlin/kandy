package org.jetbrains.kotlinx.ggdsl.letsplot.series

import org.jetbrains.kotlinx.ggdsl.dsl.internal.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import kotlin.reflect.typeOf

public typealias Line = LineGatheringContextImmutable
public typealias Point = PointGatheringContextImmutable
public typealias Bar = BarGatheringContextImmutable

// TODO!!!
@Suppress("UNCHECKED_CAST")
public inline fun <reified T : GatheringContextInterface> NamedDataPlotContext.gather(
    position: Position = Position.Identity,
    noinline block: T.() -> Unit
) {
    //todo
    when (typeOf<T>()) {
        typeOf<Line>() -> lineGather(position, block as (Line.() -> Unit))
        typeOf<Point>() -> pointGather(position, block as (Point.() -> Unit))
        typeOf<Bar>() -> barGather(position, block as (Bar.() -> Unit))
    }
}
