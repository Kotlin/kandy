/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.util.serialization

/*
@Suppress("UNCHECKED_CAST")
public fun MutableMap<String, Any>.applyColorSchemeToPlot(schemeName: String) {
    val themeMap = compute("theme") { _, prevVal ->
        if (prevVal == null) {
            mutableMapOf<String, Any>()
        } else {
            (prevVal as Map<String, Any>).toMutableMap()
        }
    } as MutableMap<String, Any>

    themeMap.putIfAbsent("flavor", schemeName)
}

@Suppress("UNCHECKED_CAST")
public fun MutableMap<String, Any>.applyColorSchemeToGGBunch(schemeName: String) {
    (this["items"]!! as List<MutableMap<String, Any>>).map {
        (it["feature_spec"]
                as MutableMap<String, Any>).applyColorSchemeToPlot(schemeName)
    }
}


@Suppress("UNCHECKED_CAST")
public fun MutableMap<String, Any>.applyColorSchemeToPlotGrid(schemeName: String) {
    (this["figures"]!! as List<MutableMap<String, Any>>).map {
        it.applyColorSchemeToPlot(schemeName)
    }
}

 */
