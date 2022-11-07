package org.jetbrains.kotlinx.ggdsl.echarts.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.NamedData
import org.jetbrains.kotlinx.ggdsl.dsl.columnPointer
import org.jetbrains.kotlinx.ggdsl.dsl.invoke
import org.jetbrains.kotlinx.ggdsl.dsl.plot
import org.jetbrains.kotlinx.ggdsl.echarts.layers.line
import org.jetbrains.kotlinx.ggdsl.echarts.x
import kotlin.test.Test

class LineTest {

    @Test
    fun `simple line test`() {
        val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val numbers = listOf(150, 230, 224, 218, 135, 147, 260)

        val daysPnt = columnPointer<String>("Days")
        val numberPnt = columnPointer<Int>("Numbers")

        val data = NamedData(
            mapOf(
                "Days" to days,
                "Numbers" to numbers
            )
        )

        val plot = plot(data) {
            x(daysPnt)

            line {
                y(numberPnt)
            }

        }

//        val json = """option = {
//  xAxis: {
//    type: 'category',
//    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
//  },
//  yAxis: {
//    type: 'value'
//  },
//  series: [
//    {
//      data: [150, 230, 224, 218, 135, 147, 260],
//      type: 'line'
//    }
//  ]
//};"""

//        println(plot.toJson())
    }
}