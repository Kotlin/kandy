package org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat

import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import kotlin.reflect.typeOf

sealed interface Stat<T> {
    val name: String
}

@PublishedApi
internal inline fun <reified T : Any> Stat<T>.toDataSource(): DataSource<T> {
    return DataSource(name, typeOf<T>())
}

// TODO TYPES

sealed interface BinStat<T> : Stat<T>{
    object X: BinStat<Double> {
        override val name = "..count.."
    }
    object Count: BinStat<Double> {
        override val name = "..count.."
    }
    object Density: BinStat<Double> {
        override val name = "..density.."
    }
}

sealed interface DensityStat<T> : Stat<T>{
    object X: DensityStat<Double> {
        override val name = "..x.."
    }
    object Count: DensityStat<Int> {
        override val name = "..count.."
    }
    object Density: DensityStat<Double> {
        override val name = "..density.."
    }
    object Scaled: DensityStat<Double> {
        override val name = "..scaled.."
    }
}


sealed interface Bin2DStat<T> : Stat<T>{
    object X: Bin2DStat<Double> {
        override val name = "..x.."
    }
    object Y: Bin2DStat<Double> {
        override val name = "..y.."
    }
    object Density: Bin2DStat<Double> {
        override val name = "..density.."
    }
}

sealed interface ContourStat<T> : Stat<T>{
    object X: ContourStat<Double> {
        override val name = "..x.."
    }
    object Y: ContourStat<Double> {
        override val name = "..y.."
    }
    object Level: ContourStat<Double> {
        override val name = "..level.."
    }
    object Group: ContourStat<Double> {
        override val name = "..group.."
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

sealed interface ViolinStat<T> : Stat<T>{
    object X: ViolinStat<Double> {
        override val name = "..x.."
    }
    object Y: ViolinStat<Double> {
        override val name = "..y.."
    }
    object ViolinWidth: ViolinStat<Double> {
        override val name = "..violinwidth.."
    }
    object Density: ViolinStat<Double> {
        override val name = "..density.."
    }
    object Count: ViolinStat<Double> {
        override val name = "..count.."
    }
    object Scaled: ViolinStat<Double> {
        override val name = "..scaled.."
    }
}

sealed interface BoxplotStat<T> : Stat<T>{

    class X<T>: BoxplotStat<T> {
        override val name = "..x.."
    }

    object Middle: BoxplotStat<Double> {
        override val name = "..middle.."
    }
    object YMin: BoxplotStat<Double> {
        override val name = "..ymin.."
    }
    object YMax: BoxplotStat<Double> {
        override val name = "..ymax.."
    }
    object Lower: BoxplotStat<Double> {
        override val name = "..lower.."
    }
    object Upper: BoxplotStat<Double> {
        override val name = "..upper.."
    }
}


sealed interface QQStat<T> : Stat<T>{
    object Theoretical: QQStat<Double> {
        override val name = "..theoretical.."
    }
    object Sample: QQStat<Double> {
        override val name = "..sample.."
    }
}

sealed interface QQ2Stat<T> : Stat<T>{
    object X: QQ2Stat<Double> {
        override val name = "..x.."
    }
    object Y: QQ2Stat<Double> {
        override val name = "..y.."
    }
}

sealed interface SmoothStat<T> : Stat<T>{
    object X: SmoothStat<Double> {
        override val name = "..x.."
    }
    object Y: SmoothStat<Double> {
        override val name = "..y.."
    }
    object YMin: SmoothStat<Double> {
        override val name = "..ymin.."
    }
    object YMax: SmoothStat<Double> {
        override val name = "..ymax.."
    }
    object SE: SmoothStat<Double> {
        override val name = "..se.."
    }
}

