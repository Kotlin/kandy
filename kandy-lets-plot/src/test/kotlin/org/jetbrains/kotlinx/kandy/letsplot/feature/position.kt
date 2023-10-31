package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.dataframe.api.columnOf
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.dataframe.api.named
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import kotlin.test.Test
import kotlin.test.assertEquals

class PositionTests {

    private val product by columnOf(
        "Apple", "Banana", "Carrot", "Apple", "Banana",
        "Carrot", "Apple", "Banana", "Carrot"
    ) named "product"
    private val sales by columnOf(10, 15, 20, 8, 18, 22, 12, 14, 24) named "sales"
    private val store by columnOf(
        "StoreA", "StoreA", "StoreA", "StoreB", "StoreB",
        "StoreB", "StoreC", "StoreC", "StoreC"
    ) named "store"

    private val groupedData = dataFrameOf(product, sales, store).groupBy(store)

    @Test
    fun `default position for bar test`() {
        val plot = groupedData.plot {
            bars { x(product); y(sales) }
        }

        assertEquals(Position.dodge(), plot.layers.first().features[Position.FEATURE_NAME])
    }


    @Test
    fun `identity position creation`() {
        val identity = Position.identity()
        val plot = groupedData.plot {
            bars { x(product); y(sales); position = identity }
        }

        assertEquals(identity, plot.layers.first().features[Position.FEATURE_NAME])
    }

    @Test
    fun `stack position creation`() {
        val stack = Position.stack()

        val plot = groupedData.plot {
            bars { x(product); y(sales); position = stack }
        }

        assertEquals(stack, plot.layers.first().features[Position.FEATURE_NAME])
    }

    @Test
    fun `dodge position creation`() {
        val dodge = Position.dodge(5.0)

        val plot = groupedData.plot {
            bars { x(product); y(sales); position = dodge }
        }

        assertEquals(dodge, plot.layers.first().features[Position.FEATURE_NAME])
        assertEquals(5.0, (plot.layers.first().features[Position.FEATURE_NAME] as Position.Dodge).width)
    }

    @Test
    fun `jitter position creation`() {
        val jitter = Position.jitter(5.0, 6.0)

        val plot = groupedData.plot {
            bars { x(product); y(sales); position = jitter }
        }


        assertEquals(jitter, plot.layers.first().features[Position.FEATURE_NAME])
        assertEquals(5.0, (plot.layers.first().features[Position.FEATURE_NAME] as Position.Jitter).width)
        assertEquals(6.0, (plot.layers.first().features[Position.FEATURE_NAME] as Position.Jitter).height)
    }

    @Test
    fun `nudge position creation`() {
        val nudge = Position.nudge(5.0, 6.0)

        val plot = groupedData.plot {
            bars { x(product); y(sales); position = nudge }
        }


        assertEquals(nudge, plot.layers.first().features[Position.FEATURE_NAME])
        assertEquals(5.0, (plot.layers.first().features[Position.FEATURE_NAME] as Position.Nudge).x)
        assertEquals(6.0, (plot.layers.first().features[Position.FEATURE_NAME] as Position.Nudge).y)
    }

    @Test
    fun `jitterDodge position creation`() {
        val jitterDodge = Position.jitterDodge(3.0, 5.0, 6.0)

        val plot = groupedData.plot {
            bars { x(product); y(sales); position = jitterDodge }
        }


        assertEquals(jitterDodge, plot.layers.first().features[Position.FEATURE_NAME])
        assertEquals(3.0, (plot.layers.first().features[Position.FEATURE_NAME] as Position.JitterDodge).dodgeWidth)
        assertEquals(5.0, (plot.layers.first().features[Position.FEATURE_NAME] as Position.JitterDodge).jitterWidth)
        assertEquals(6.0, (plot.layers.first().features[Position.FEATURE_NAME] as Position.JitterDodge).jitterHeight)
    }
}