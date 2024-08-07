/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option

import kotlinx.serialization.Serializable

@Serializable
internal data class Dataset(
    val id: String? = null,
    val source: List<List<String?>>? = null, // TODO
    val sourceHeader: Boolean? = null, // number?
//    val transform: Transform? = null,
    val fromDatasetIndex: Int? = null,
    val fromDatasetId: String? = null,
    val fromTransformResult: Int? = null
) {

    fun isEmpty(): Boolean =
        id == null && source?.all { it.isEmpty() } ?: true && sourceHeader == null && fromDatasetIndex == null
                && fromDatasetId == null && fromTransformResult == null

    fun isNotEmpty(): Boolean = !this.isEmpty()
}
