/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.scale.guide


public class Legend<DomainType, RangeType> {
    public var name: String? = null
    public var show: Boolean? = null
    public var calculable: Boolean? = null // todo customize ? move to upper context???
    // todo more
}


public inline operator fun <DomainType, RangeType>
    Legend<DomainType, RangeType>.invoke(block: Legend<DomainType, RangeType>.() -> Unit) {
    apply(block)
}
