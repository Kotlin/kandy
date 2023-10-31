/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.export

import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotGrid
import org.jetbrains.kotlinx.kandy.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.kandy.letsplot.translator.wrap
import org.jetbrains.letsPlot.export.ggsave

/**
 * Exports a plot to a file in one of the supported formats: `SVG`, `HTML`, `PNG`, `JPG`/`JPEG`, or `TIFF`.
 *
 * > In certain configurations, raster formats might not be supported.
 *
 * The exported file will be created in the directory `${user.dir}/lets-plot-images`,
 * unless a different directory is specified using the [path] parameter.
 *
 * @receiver [Plot] - the plot to export.
 * @param filename the name of the output file.
 * The filename must include an extension corresponding to one of the supported formats:
 * **svg**, **html** (or **htm**), **png**, **jpeg** (or **jpg**), or **tiff** (or **tif**).
 * @param scale The scaling is applied to the plot when exporting to raster formats (`PNG`, `JPEG`, `TIFF`).
 * This parameter is ignored for vector formats (`SVG`, `HTML`).
 * The default value is 1.
 * @param dpi the resolution of the exported image in dots per inch (DPI) when exporting to raster formats.
 * This parameter is ignored for vector formats.
 * By default, no DPI metadata is stored in the file.
 * @param path the path to the directory where the image files will be saved.
 * If not specified, the default directory is `${user.dir}/lets-plot-images`.
 *
 * @return The absolute pathname of the created file.
 */
public fun Plot.save(
    filename: String,
    scale: Number = 1,
    dpi: Number? = null,
    path: String? = null
): String = ggsave(toLetsPlot(), filename, scale, dpi, path)

/**
 * Exports a plot grid to a file in one of the supported formats: `SVG`, `HTML`, `PNG`, `JPG`/`JPEG`, or `TIFF`.
 *
 * > In certain configurations, raster formats might not be supported.
 *
 * The exported file will be created in the directory `${user.dir}/lets-plot-images`,
 * unless a different directory is specified using the [path] parameter.
 *
 * @receiver [Plot] - the plot to export.
 * @param filename the name of the output file.
 * The filename must include an extension corresponding to one of the supported formats:
 * **svg**, **html** (or **htm**), **png**, **jpeg** (or **jpg**), or **tiff** (or **tif**).
 * @param scale The scaling is applied to the plot when exporting to raster formats (`PNG`, `JPEG`, `TIFF`).
 * This parameter is ignored for vector formats (`SVG`, `HTML`).
 * The default value is 1.
 * @param dpi the resolution of the exported image in dots per inch (DPI) when exporting to raster formats.
 * This parameter is ignored for vector formats.
 * By default, no DPI metadata is stored in the file.
 * @param path the path to the directory where the image files will be saved.
 * If not specified, the default directory is `${user.dir}/lets-plot-images`.
 *
 * @return The absolute pathname of the created file.
 */
public fun PlotGrid.save(
    filename: String,
    scale: Number = 1,
    dpi: Number? = null,
    path: String? = null
): String = ggsave(wrap(), filename, scale, dpi, path)

/**
 * Exports a plot bunch to a file in one of the supported formats: `SVG`, `HTML`, `PNG`, `JPG`/`JPEG`, or `TIFF`.
 *
 * > In certain configurations, raster formats might not be supported.
 *
 * The exported file will be created in the directory `${user.dir}/lets-plot-images`,
 * unless a different directory is specified using the [path] parameter.
 *
 * @receiver [Plot] - the plot to export.
 * @param filename the name of the output file.
 * The filename must include an extension corresponding to one of the supported formats:
 * **svg**, **html** (or **htm**), **png**, **jpeg** (or **jpg**), or **tiff** (or **tif**).
 * @param scale The scaling is applied to the plot when exporting to raster formats (`PNG`, `JPEG`, `TIFF`).
 * This parameter is ignored for vector formats (`SVG`, `HTML`).
 * The default value is 1.
 * @param dpi the resolution of the exported image in dots per inch (DPI) when exporting to raster formats.
 * This parameter is ignored for vector formats.
 * By default, no DPI metadata is stored in the file.
 * @param path the path to the directory where the image files will be saved.
 * If not specified, the default directory is `${user.dir}/lets-plot-images`.
 *
 * @return The absolute pathname of the created file.
 */
public fun PlotBunch.save(
    filename: String,
    scale: Number = 1,
    dpi: Number? = null,
    path: String? = null
): String = ggsave(wrap(), filename, scale, dpi, path)
