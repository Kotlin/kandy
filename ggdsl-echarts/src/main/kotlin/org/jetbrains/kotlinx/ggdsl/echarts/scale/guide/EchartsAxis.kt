/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.scale.guide


class Axis<DomainType : Any> {
    var show: Boolean? = true
    var name: String? = null
}


inline operator fun <DomainType : Any> Axis<DomainType>.invoke(block: Axis<DomainType>.() -> Unit) {
    apply(block)
}
