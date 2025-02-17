package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.letsplot.style.Style
import org.jetbrains.kotlinx.kandy.letsplot.style.Theme
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
            theme = Theme.DARCULA,
            size = Pair(800, 600)
        )

        assertEquals("Test Title", layout.title)
        assertEquals("Test Subtitle", layout.subtitle)
        assertEquals("Test Caption", layout.caption)
        assertEquals(Theme.DARCULA, layout.theme)
        assertEquals(Pair(800, 600), layout.size)
    }


    @Test
    fun `style function sets style`() {
        val layout = Layout().apply { style(Style.Classic) }
        val testStyle = Style.Classic
        assertEquals(testStyle, layout.style)

        layout.style(testStyle) {
            legend {
                title {
                    fontSize = 14.0
                }
            }
        }

        assertEquals(14.0, layout.customStyle?.legend?.title?.fontSize)
    }

//    @Test // TODO(issue #145/#127)
//    fun `style function sets custom style`() {
//        val layout = Layout()
//
//        layout.style {
//            panel {
//                grid.lineGlobal {
//                    color = Color.RED
//                }
//            }
//        }
//
//        assertEquals(Color.RED, layout.customStyle?.panel?.grid?.lineGlobal?.color)
//    }
}
