package org.jetbrains.kotlinx.kandy.dsl.internal

/**
 * Marks the DSL for creating and configuring plots within the Kandy library.
 *
 * The `PlotDslMarker` annotation is used to restrict the scope of DSL functions to prevent unintentional
 * interference between different DSL builders. By applying this marker, we ensure a clear and structured
 * separation of concerns within the DSL context, leading to safer and more predictable DSL designs.
 *
 * Now only works for `DataFramePlotBuilder.withData {}`
 */
@DslMarker
internal annotation class PlotDslMarker
