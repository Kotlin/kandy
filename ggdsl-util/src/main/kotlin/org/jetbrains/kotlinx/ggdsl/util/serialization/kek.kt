package org.jetbrains.kotlinx.ggdsl.util.serialization



@Suppress("UNCHECKED_CAST")
public fun MutableMap<String, Any>.applyColorSchemeToPlot(schemeName: String) {
    val themeMap = compute("theme") {
        _, prevVal -> if (prevVal == null) {
            mutableMapOf<String, Any>()
        } else {
            (prevVal as Map<String, Any>).toMutableMap()
        }
    } as MutableMap<String, Any>

    themeMap["flavor"] = schemeName
}

public fun MutableMap<String, Any>.applyColorSchemeToGGBunch(schemeName: String) {
        (this[ITEMS]!! as List<MutableMap<String, Any>>).map {
            (it[Option.GGBunch.Item.FEATURE_SPEC]
                    as MutableMap<String, Any>
                    ).applyColorSchemeToPlotSpec()
        }
    }
}

// todo
public fun MutableMap<String, Any>.applyColorSchemeToPlotGrid() {
    if (!applyColorScheme) return
    if (notebook.currentColorScheme == ColorScheme.DARK) {
        (this[Option.SubPlots.FIGURES]!! as List<MutableMap<String, Any>>).map {
            it.applyColorSchemeToPlotSpec()
        }
    }
}