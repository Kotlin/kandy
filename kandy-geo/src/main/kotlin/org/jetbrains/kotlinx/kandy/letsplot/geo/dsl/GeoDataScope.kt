package org.jetbrains.kotlinx.kandy.letsplot.geo.dsl

/**
 * A marker interface used within the geographic data plotting DSL to define a common scope for
 * geographic data operations.
 *
 * GeoDataScope serves as a unified context for manipulating and accessing geographic data,
 * such as geometries and coordinate reference systems (CRS) within the DSL. It is primarily
 * used as a parent type for other specific scope classes that add functionality or constraints
 * related to geographic data.
 */
public sealed interface GeoDataScope
