/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.series
/*
import org.jetbrains.kotlinx.kandy.dsl.internal.NamedDataPlotContext
import org.jetbrains.kotlinx.kandy.dsl.internal.TableBindingContextInterfaceMutable
import org.jetbrains.kotlinx.kandy.dsl.internal.TableDataContext
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.BorderLineContextImmutable
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.BorderLineContextMutable
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.WithBorderLineContextImmutable
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.WithBorderLineContextMutable
import org.jetbrains.kotlinx.kandy.letsplot.position.Position

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
    override val data: NamedData,
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

 */
