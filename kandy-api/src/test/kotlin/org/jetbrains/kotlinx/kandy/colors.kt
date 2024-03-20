/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy

import org.jetbrains.kotlinx.kandy.util.color.StandardColor
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ColorTests {
    @Test
    fun `RGB to Hex conversion`() {
        val color = StandardColor.RGB(255, 0, 128)
        assertEquals("#FF0080", color.hexString)
    }

    @Test
    fun `RGB to RGBA conversion`() {
        val rgb = StandardColor.RGB(255, 0, 128)
        val rgba = rgb.toRGBA(0.5)
        assertEquals(rgb, rgba.toRGB())
        assertEquals(0.5, rgba.a)
    }

    @Test
    fun `RGBA to Hex conversion`() {
        val rgba = StandardColor.RGBA(StandardColor.RGB(255, 0, 128), 0.5)
        assertEquals("#FF008080", rgba.hexString)
    }

    @Test
    fun `Named color description`() {
        val namedColor = StandardColor.Named("Red")
        assertEquals("Red", namedColor.description)
    }

    @Test
    fun `Hex color representation`() {
        val hexColor = StandardColor.Hex("#FF0080")
        assertEquals("#FF0080", hexColor.hexString)
    }

    @Test
    fun `RGB values out of bounds`() {
        assertFailsWith<IllegalArgumentException> {
            StandardColor.RGB(300, 0, 0)
        }
        assertFailsWith<IllegalArgumentException> {
            StandardColor.RGB(-1, 0, 0)
        }
    }

    @Test
    fun `RGBA alpha out of bounds`() {
        assertFailsWith<IllegalArgumentException> {
            StandardColor.RGBA(StandardColor.RGB(255, 0, 0), 1.5)
        }
        assertFailsWith<IllegalArgumentException> {
            StandardColor.RGBA(StandardColor.RGB(255, 0, 0), -0.5)
        }
    }

    @Test
    fun `Hex string invalid format`() {
        assertFailsWith<IllegalArgumentException> {
            StandardColor.Hex("invalid")
        }
    }

    @Test
    fun `Named color empty name`() {
        assertFailsWith<IllegalArgumentException> {
            StandardColor.Named("")
        }
    }

    @Test
    fun `RGB to RGBA and back conversion`() {
        val originalRGB = StandardColor.RGB(255, 127, 64)
        val rgba = originalRGB.toRGBA()
        val convertedRGB = rgba.toRGB()
        assertEquals(originalRGB, convertedRGB)
    }
}
