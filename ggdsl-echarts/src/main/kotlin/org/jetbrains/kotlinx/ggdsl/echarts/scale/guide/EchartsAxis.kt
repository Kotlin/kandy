/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.scale.guide


public class Axis<DomainType> {
    public var show: Boolean? = true
    public var name: String? = null
}


public inline operator fun <DomainType> Axis<DomainType>.invoke(block: Axis<DomainType>.() -> Unit) {
    apply(block)
}
