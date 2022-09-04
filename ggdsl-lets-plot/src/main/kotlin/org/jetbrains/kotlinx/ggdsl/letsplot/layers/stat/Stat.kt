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

sealed interface BinStat<T> : Stat<T>{
    object Count: BinStat<Int> {
        override val name = "..count.."
    }
    object Density: BinStat<Double> {
        override val name = "..density.."
    }
}

sealed interface Density2DStat<T> : Stat<T>{
    object Level: Density2DStat<Double> {
        override val name = "..level.."
    }
}

sealed interface Bin2DStat<T> : Stat<T>{
    object Density: Bin2DStat<Double> {
        override val name = "..density.."
    }
}

sealed interface ContourStat<T> : Stat<T>{
    object Level: ContourStat<Double> {
        override val name = "..level.."
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

