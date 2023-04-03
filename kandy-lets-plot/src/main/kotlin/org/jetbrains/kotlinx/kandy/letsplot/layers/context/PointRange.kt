package org.jetbrains.kotlinx.kandy.letsplot.layers.context

/*
public interface InnerPointSubContextInterface: SelfInvocationContext {
    // todo hide
    public val parentContext: BindingContext
    public val symbol: ShapeAes get() = ShapeAes(parentContext)
    public val fillColor: FillAes get() = FillAes(parentContext)
    public val fatten: FattenAes get() = FattenAes(parentContext)
}

public class InnerPointSubContextImmutable(override val parentContext: BindingContext) : InnerPointSubContextInterface
public class InnerPointSubContextMutable(override val parentContext: TableBindingContextInterfaceMutable) :
    InnerPointSubContextInterface, TableSubContextMutable(parentContext, false, false)

public interface InnerLineSubContextInterface: SelfInvocationContext {
    // todo hide
    public val parentContext: BindingContext
    public val type: LineTypeAes get() = LineTypeAes(parentContext)
}

public class InnerLineSubContextImmutable(override val parentContext: BindingContext) : InnerLineSubContextInterface
public class InnerLineSubContextMutable(override val parentContext: TableBindingContextInterfaceMutable) :
    InnerLineSubContextInterface, TableSubContextMutable(parentContext, false, false)

public interface PointRangeContextInterface : BindingContext {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)
    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)

    public val alpha: AlphaAes get() = AlphaAes(this)
    public val color: ColorAes get() = ColorAes(this)

    // todo separate????
    public val size: SizeAes get() = SizeAes(this)

    public val innerPoint: InnerPointSubContextInterface

    public val innerLine: InnerLineSubContextInterface
}

*/
/*@PlotDslMarker*//*

public class PointRangeContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), PointRangeContextInterface {
    override val innerPoint: InnerPointSubContextImmutable =
        InnerPointSubContextImmutable(this)
    override val innerLine: InnerLineSubContextImmutable =
        InnerLineSubContextImmutable(this)
}

*/
/*@PlotDslMarker*//*

public class PointRangeContextMutable(parent: LayerCollectorContextMutable) : LayerWithBorderLineContextMutable(parent),
    PointRangeContextInterface {
    override val innerPoint: InnerPointSubContextMutable =
        InnerPointSubContextMutable(this)
    override val innerLine: InnerLineSubContextMutable =
        InnerLineSubContextMutable(this)

}*/
