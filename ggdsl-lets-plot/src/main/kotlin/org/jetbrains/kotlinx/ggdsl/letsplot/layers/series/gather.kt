package org.jetbrains.kotlinx.ggdsl.letsplot.layers.series

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import kotlin.reflect.typeOf

public typealias Line = LineGatheringContext

// TODO!!!
@Suppress("UNCHECKED_CAST")
public inline fun <reified T : GatheringContextInterface> NamedDataPlotContext<*>.gather(
    position: Position = Position.Identity,
    noinline block: T.() -> Unit
) {
    //todo
    when (typeOf<T>()) {
        typeOf<LineGatheringContext>() -> lineGather(position, block as (LineGatheringContext.() -> Unit))
    }
}
