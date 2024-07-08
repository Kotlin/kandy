@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.echarts.layers.aes

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.bindingHandler

/**
 * Interface for managing the aesthetic properties in [LayerBuilder].
 */
public interface WithAes

@PublishedApi
internal val WithAes.bindingHandler: BindingHandler
    get() =(this as LayerBuilder).bindingHandler
