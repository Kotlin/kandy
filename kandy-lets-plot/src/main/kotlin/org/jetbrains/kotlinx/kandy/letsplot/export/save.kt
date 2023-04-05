/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.export

import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotGrid
import org.jetbrains.kotlinx.kandy.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.kandy.letsplot.translator.wrap
import org.jetbrains.letsPlot.export.ggsave

/**
 * Exports plot to a file.
 * Supported formats: SVG, HTML, PNG, JPEG and TIFF.
 * Note: in some configurations raster formats might not be supported.
 *
 * The exported file is created in directory ${user.dir}/lets-plot-images
 * if not specified otherwise (see the `path` parameter).
 *
 * @receiver [Plot] to export.
 * @param filename The name of file. It must end with file extention corresponding
 *      to one of the supported formats: svg, html (or htm), png, jpeg (or jpg) or tiff (or tif)
 * @param scale Scaling factor (only for raster formats). Default: 2.0
 * @param dpi Dot-per-Inch value to store in the exported file metadata (only for raster formats).
 *      By default, no metadata is stored.
 * @param path Path to a directory to save image files in.
 *      By default, it is `${user.dir}/lets-plot-images`
 *
 * @return Absolute pathname of created file.
 */
public fun Plot.save(
    filename: String,
    scale: Number = 1,
    dpi: Number? = null,
    path: String? = null
): String = ggsave(toLetsPlot(), filename, scale, dpi, path)

/**
 * Exports plot grid to a file.
 * Supported formats: SVG, HTML, PNG, JPEG and TIFF.
 * Note: in some configurations raster formats might not be supported.
 *
 * The exported file is created in directory ${user.dir}/lets-plot-images
 * if not specified otherwise (see the `path` parameter).
 *
 * @receiver [PlotGrid] to export.
 * @param filename The name of file. It must end with file extention corresponding
 *      to one of the supported formats: svg, html (or htm), png, jpeg (or jpg) or tiff (or tif)
 * @param scale Scaling factor (only for raster formats). Default: 2.0
 * @param dpi Dot-per-Inch value to store in the exported file metadata (only for raster formats).
 *      By default, no metadata is stored.
 * @param path Path to a directory to save image files in.
 *      By default, it is `${user.dir}/lets-plot-images`
 *
 * @return Absolute pathname of created file.
 */
public fun PlotGrid.save(
    filename: String,
    scale: Number = 1,
    dpi: Number? = null,
    path: String? = null
): String = ggsave(wrap(), filename, scale, dpi, path)

/**
 * Exports plot bunch to a file.
 * Supported formats: SVG, HTML, PNG, JPEG and TIFF.
 * Note: in some configurations raster formats might not be supported.
 *
 * The exported file is created in directory ${user.dir}/lets-plot-images
 * if not specified otherwise (see the `path` parameter).
 *
 * @receiver [PlotBunch] to export.
 * @param filename The name of file. It must end with file extention corresponding
 *      to one of the supported formats: svg, html (or htm), png, jpeg (or jpg) or tiff (or tif)
 * @param scale Scaling factor (only for raster formats). Default: 2.0
 * @param dpi Dot-per-Inch value to store in the exported file metadata (only for raster formats).
 *      By default, no metadata is stored.
 * @param path Path to a directory to save image files in.
 *      By default, it is `${user.dir}/lets-plot-images`
 *
 * @return Absolute pathname of created file.
 */
public fun PlotBunch.save(
    filename: String,
    scale: Number = 1,
    dpi: Number? = null,
    path: String? = null
): String = ggsave(wrap(), filename, scale, dpi, path)
