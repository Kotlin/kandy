/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithAes
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.BorderLine
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithBorderLine
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.bindingHandler

// todo doc
@Suppress("INVISIBLE_MEMBER")
public abstract class LayerWithBorderLineBuilder @PublishedApi internal constructor(parent: LayerCreatorScope) : LayerBuilderImpl(parent),
    WithBorderLine, WithAes {
    public override val borderLine: BorderLine = BorderLine(bindingHandler)
}
