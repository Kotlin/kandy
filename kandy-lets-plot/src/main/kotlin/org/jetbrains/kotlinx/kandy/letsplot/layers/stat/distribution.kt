/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.stat
/*
public sealed interface Distribution {
    public val name: String

    public data class Norm(val mean: Double = 0.0, val std: Double = 1.0) : Distribution {
        override val name: String = "norm"
    }

    public data class Uniform(val a: Double = 0.0, val b: Double = 1.0) : Distribution {
        override val name: String = "uniform"
    }

    public data class T(val d: Int = 1) : Distribution {
        override val name: String = "t"
    }

    public data class Gamma(val alpha: Double = 1.0, val beta: Double = 1.0) : Distribution {
        override val name: String = "gamma"
    }

    public data class Exp(val lambda: Double = 0.0) : Distribution {
        override val name: String = "exp"
    }

    public data class Chi2(val k: Int = 1) : Distribution {
        override val name: String = "chi2"
    }
}

public fun Distribution.toList(): List<Number> {
    return when (this) {
        is Distribution.Norm -> listOf(mean, std)
        is Distribution.Chi2 -> listOf(k)
        is Distribution.Exp -> listOf(lambda)
        is Distribution.Gamma -> listOf(alpha, beta)
        is Distribution.T -> listOf(d)
        is Distribution.Uniform -> listOf(a, b)
    }
}
 */
