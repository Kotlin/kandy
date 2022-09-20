package org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat

import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import kotlin.reflect.typeOf

public sealed interface Stat<T> {
    public val name: String
}

@PublishedApi
internal inline fun <reified T : Any> Stat<T>.toDataSource(): DataSource<T> {
    return DataSource(name, typeOf<T>())
}

// TODO TYPES

public sealed interface BinStat<T> : Stat<T> {
    public object X : BinStat<Double> {
        override val name: String = "..count.."
    }

    public object Count : BinStat<Double> {
        override val name: String = "..count.."
    }

    public object Density : BinStat<Double> {
        override val name: String = "..density.."
    }
}

public sealed interface DensityStat<T> : Stat<T> {
    public object X : DensityStat<Double> {
        override val name: String = "..x.."
    }

    public object Count : DensityStat<Int> {
        override val name: String = "..count.."
    }

    public object Density : DensityStat<Double> {
        override val name: String = "..density.."
    }

    public object Scaled : DensityStat<Double> {
        override val name: String = "..scaled.."
    }
}


public sealed interface Bin2DStat<T> : Stat<T> {
    public object X : Bin2DStat<Double> {
        override val name: String = "..x.."
    }

    public object Y : Bin2DStat<Double> {
        override val name: String = "..y.."
    }

    public object Density : Bin2DStat<Double> {
        override val name: String = "..density.."
    }
}

public sealed interface ContourStat<T> : Stat<T> {
    public object X : ContourStat<Double> {
        override val name: String = "..x.."
    }

    public object Y : ContourStat<Double> {
        override val name: String = "..y.."
    }

    public object Level : ContourStat<Double> {
        override val name: String = "..level.."
    }

    public object Group : ContourStat<Double> {
        override val name: String = "..group.."
    }
}
/* TODO
abstract class WithDensity2DStat {
     inline operator fun <reified DomainType : Any, RangeType : Any>
            MappableNonPositionalAes<RangeType>.invoke(
        stat: Density2DStat<DomainType>
    ): ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType> {
        val mapping = ScaledUnspecifiedDefaultNonPositionalMapping<DomainType, RangeType>(
            this.name,
            stat.toDataSource().scaled(),
            typeOf<DomainType>()
        )
        context.bindingCollector.mappings[this.name] = mapping
        return mapping
    }
}

 */

public sealed interface ViolinStat<T> : Stat<T> {
    public object X : ViolinStat<Double> {
        override val name: String = "..x.."
    }

    public object Y : ViolinStat<Double> {
        override val name: String = "..y.."
    }

    public object ViolinWidth : ViolinStat<Double> {
        override val name: String = "..violinwidth.."
    }

    public object Density : ViolinStat<Double> {
        override val name: String = "..density.."
    }

    public object Count : ViolinStat<Double> {
        override val name: String = "..count.."
    }

    public object Scaled : ViolinStat<Double> {
        override val name: String = "..scaled.."
    }
}

public sealed interface BoxplotStat<T> : Stat<T> {

    public class X<T> : BoxplotStat<T> {
        override val name: String = "..x.."
    }

    public object Middle : BoxplotStat<Double> {
        override val name: String = "..middle.."
    }

    public object YMin : BoxplotStat<Double> {
        override val name: String = "..ymin.."
    }

    public object YMax : BoxplotStat<Double> {
        override val name: String = "..ymax.."
    }

    public object Lower : BoxplotStat<Double> {
        override val name: String = "..lower.."
    }

    public object Upper : BoxplotStat<Double> {
        override val name: String = "..upper.."
    }
}


public sealed interface QQStat<T> : Stat<T> {
    public object Theoretical : QQStat<Double> {
        override val name: String = "..theoretical.."
    }

    public object Sample : QQStat<Double> {
        override val name: String = "..sample.."
    }
}

public sealed interface QQ2Stat<T> : Stat<T> {
    public object X : QQ2Stat<Double> {
        override val name: String = "..x.."
    }

    public object Y : QQ2Stat<Double> {
        override val name: String = "..y.."
    }
}

public sealed interface SmoothStat<T> : Stat<T> {
    public object X : SmoothStat<Double> {
        override val name: String = "..x.."
    }

    public object Y : SmoothStat<Double> {
        override val name: String = "..y.."
    }

    public object YMin : SmoothStat<Double> {
        override val name: String = "..ymin.."
    }

    public object YMax : SmoothStat<Double> {
        override val name: String = "..ymax.."
    }

    public object SE : SmoothStat<Double> {
        override val name: String = "..se.."
    }
}

