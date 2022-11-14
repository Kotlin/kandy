package org.jetbrains.kotlinx.ggdsl.letsplot.layers.series

import org.jetbrains.kotlinx.ggdsl.dsl.internal.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.TableBindingContextInterfaceMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.TableDataContext
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedDataInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BorderLineContextImmutable
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BorderLineContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.WithBorderLineContextImmutable
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.WithBorderLineContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position

public abstract class SeriesWithBorderLineContextImmutable(parent: TableDataContext) :
    SeriesContextImmutable(parent), WithBorderLineContextImmutable {
    override val borderLine: BorderLineContextImmutable
            by lazy { BorderLineContextImmutable(this) }
}

public abstract class SeriesWithBorderLineContextMutable(parent: TableBindingContextInterfaceMutable) :
    SeriesContextMutable(parent), WithBorderLineContextMutable {
    override val borderLine: BorderLineContextMutable
            by lazy { BorderLineContextMutable(this) }
}

public abstract class GatheringWithBorderLineContextImmutable(parent: NamedDataPlotContext, position: Position) :
    GatheringContextBaseImmutable(parent, position), WithBorderLineContextImmutable {
    override val borderLine: BorderLineContextImmutable
            by lazy { BorderLineContextImmutable(this) }
}

public abstract class GatheringWithBorderLineContextMutable(
    parent: TableBindingContextInterfaceMutable,
    position: Position
) :
    GatheringSubContextMutable(parent, position), WithBorderLineContextMutable {
    override val borderLine: BorderLineContextMutable
            by lazy { BorderLineContextMutable(this) }
}

public abstract class SeriesPlotWithBorderLineContext(
    override val data: NamedDataInterface,
    override val position: Position
) : SeriesPlotContextBase(), WithBorderLineContextImmutable {
    override val borderLine: BorderLineContextImmutable
            by lazy { BorderLineContextImmutable(this) }
}

public abstract class SeriesPlotContextWithBorderLineMutable(
    position: Position,
) : SeriesPlotContextMutable(position), WithBorderLineContextMutable {
    override val borderLine: BorderLineContextMutable
            by lazy { BorderLineContextMutable(this) }
}
