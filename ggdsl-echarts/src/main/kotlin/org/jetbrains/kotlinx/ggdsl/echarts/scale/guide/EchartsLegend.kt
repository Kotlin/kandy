/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.scale.guide


class Legend<DomainType : Any, RangeType : Any> {
    var name: String? = null
    var show: Boolean? = null
    var calculable: Boolean? = null // todo customize ? move to upper context???
    // todo more
}


inline operator fun <DomainType : Any, RangeType : Any>
        Legend<DomainType, RangeType>.invoke(block: Legend<DomainType, RangeType>.() -> Unit) {
    apply(block)
}
