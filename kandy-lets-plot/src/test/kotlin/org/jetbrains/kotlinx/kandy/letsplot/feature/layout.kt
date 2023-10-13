package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.letsplot.theme.Flavor
import org.jetbrains.kotlinx.kandy.letsplot.theme.Theme
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.Test
import kotlin.test.assertEquals

class LayoutTests {

    @Test
    fun `layout initializes with provided values`() {
        val layout = Layout(
            title = "Test Title",
            subtitle = "Test Subtitle",
            caption = "Test Caption",
            xAxisLabel = "x",
            yAxisLabel = "y",
            flavor = Flavor.DARCULA,
            size = Pair(800, 600)
        )

        assertEquals("Test Title", layout.title)
        assertEquals("Test Subtitle", layout.subtitle)
        assertEquals("Test Caption", layout.caption)
        assertEquals("x", layout.xAxisLabel)
        assertEquals("y", layout.yAxisLabel)
        assertEquals(Flavor.DARCULA, layout.flavor)
        assertEquals(Pair(800, 600), layout.size)
    }


    @Test
    fun `theme function sets theme`() {
        val layout = Layout().apply { theme = Theme.Classic }
        val testTheme = Theme.Classic
        assertEquals(testTheme, layout.theme)

        layout.theme(testTheme) {
            legend {
                title {
                    fontSize = 14.0
                }
            }
        }

        assertEquals(14.0, layout.customTheme?.legend?.title?.fontSize)
    }

//    @Test // TODO(issue #145/#127)
//    fun `theme function sets custom theme`() {
//        val layout = Layout()
//
//        layout.theme {
//            panel {
//                grid.lineGlobal {
//                    color = Color.RED
//                }
//            }
//        }
//
//        assertEquals(Color.RED, layout.customTheme?.panel?.grid?.lineGlobal?.color)
//    }
}
