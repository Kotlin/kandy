/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.series
/*
import org.jetbrains.kotlinx.kandy.dsl.internal.NamedDataPlotContext
import org.jetbrains.kotlinx.kandy.letsplot.position.Position
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


 */