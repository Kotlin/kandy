package org.jetbrains.kotlinx.ggdsl.echarts.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.bars
import org.jetbrains.kotlinx.ggdsl.dsl.plot
import org.jetbrains.kotlinx.ggdsl.echarts.stack.Stack
import org.jetbrains.kotlinx.ggdsl.echarts.stack.stack
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import kotlin.test.Test
import kotlin.test.assertTrue

internal class FeaturesTest {

    private fun Layer.checkStack(expectedStack: Stack?): Boolean {
        val stack = features[Stack.FEATURE_NAME]
        return if(expectedStack == null) {
            stack == null
        } else {
            stack?.equals(expectedStack) == true
        }
    }

    @Test
    fun testStack() {
        val stack1 = Stack("Stack #1")
        val stack2 = Stack("staCk №2")
        val plot = plot {
            bars {
                stack = stack1
            }
            bars {

            }
            bars {
                stack = stack2
            }
            bars {
                stack = stack1
            }
        }
        plot.layers.let {
            assertTrue(it[0].checkStack(stack1))
            assertTrue(it[1].checkStack(null))
            assertTrue(it[2].checkStack(stack2))
            assertTrue(it[3].checkStack(stack1))
        }
    }


}