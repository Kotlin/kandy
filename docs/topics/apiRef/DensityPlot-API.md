# densityPlot

<tldr>
<p><format style="bold" color="GoldenRod">
densityPlot</format>(
<a href="#x"><format style="bold" color="CadetBlue">x</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include>,
<a href="#weights"><format style="bold" color="DarkGray">weights</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include> = null,
<a href="#n"><format style="bold" color="DarkGray">n</format></a>:Int = 512,
<a href="#trim"><format style="bold" color="DarkGray">trim</format></a>:Boolean = false,
<a href="#adjust"><format style="bold" color="DarkGray">adjust</format></a>:Double = 1.0,
<a href="#kernel"><format style="bold" color="DarkGray">kernel</format></a>: Kernel = Kernel.GAUSSIAN,
<a href="#fullscanmax"><format style="bold" color="DarkGray">fullScanMax</format></a>: Int = 5000,
<a href="#bandwidth"><format style="bold" color="DarkGray">bandWidth</format></a>: BandWidth = BandWidth.Method.NRD0,
) <format style="italic">{ this: DensityPlotLayerBuilder -></format></p>
<include from = "interfaces.topic" element-id="interface-of-area"></include>
<format style="italic">}</format>
</tldr>

The `densityPlot` adds a density-plot layer
function optionally creates a new context with [area](https://kotlin.github.io/kandy/area-api.html) aesthetics and new dataset
which contains the "density" statistic calculated on sample of a single numeric variable [x](#x).
In this context, all required aesthetics are assigned by default but can be overridden.

Density plot is a statistical plot used for visualizing the distribution of continuous variables. 
It's an area plot of kernel-estimated Probability Density Function (PDF).
It's weighted, it means the counted density depends on observation weights.

## Arguments

### x

<include from="arguments.topic" element-id="x-argument"/>

### weights

<include from="arguments.topic" element-id="weights-argument"/>

### n

<p><format style="superscript" color="LightSlateGray">Optional</format> </p>
<p> <format style="superscript" color="#E8488B">Double</format></p>
<p> 
The <code>n</code> argument is used to specify 
the number of sampled points. 
</p>

### trim

<p><format style="superscript" color="LightSlateGray">Optional</format> </p>
<p> <format style="superscript" color="#E8488B">Boolean</format></p>
<p> 
The <code>n</code> argument is used to specify 
the range of density calculation for grouped data: if <code>false</code>, each density is computed on the 
full range of the data, 
if <code>true</code>, each density is computed over the range of that group (only for grouped inputs). 
</p>

### adjust

<p><format style="superscript" color="LightSlateGray">Optional</format> </p>
<p> <format style="superscript" color="#E8488B">Double</format></p>
<p> 
The <code>adjust</code> argument is used to adjust the value of 
bandwidth by multiplying it; changes how smooth the frequency curve is. 
</p>

### kernel

<p><format style="superscript" color="LightSlateGray">Optional</format> </p>
<p> <format style="superscript" color="#E8488B">Kernel</format></p>
<p> 
The <code>kernel</code> argument is used to adjust the kernel used to calculate the density function. 
</p>

<list>
<li> 
<p><code>Kernel.GAUSSIAN</code>;</p> 
</li> 
<li> 
<p><code>Kernel.RECTANGULAR</code>;</p> 
</li> 
<li> 
<p><code>Kernel.TRIANGULAR</code>;</p> 
</li> 
<li> 
<p><code>Kernel.BIWEIGHT</code>;</p> 
</li> 
<li> 
<p><code>Kernel.EPANECHNIKOV</code>;</p> 
</li> 
<li> 
<p><code>Kernel.OPTCOSINE</code>.</p> 
</li>
</list>

### fullScanMax

<p><format style="superscript" color="LightSlateGray">Optional</format> </p>
<p> <format style="superscript" color="#E8488B">Int</format></p>
<p> 
The <code>adjust</code> argument is used to specify the maximum size of data to use density computation with 
"full scan". For bigger data, less accurate but more efficient density computation is applied.
</p>

### bandWidth

<p><format style="superscript" color="LightSlateGray">Optional</format> </p>
<p> <format style="superscript" color="#E8488B">BandWidth</format></p>
<p> 
The <code>bandWidth</code> argument is used to adjust the method (or exact value) of bandwidth:
</p>

<list>
<li> 
<p><code>BandWidth.Method.NRD</code>;</p> 
</li> 
<li> 
<p><code>BandWidth.Method.NRD0</code>;</p> 
</li> 
<li> 
<p><code>BandWidth.value(value: Double)</code>.</p> 
</li>
</list>

## Statistic properties

In this context, there are the following statistical properties that can be used
to create mappings, customize tooltips, etc.

### Stat.x

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.x</code> contains points x-coordinate. 
</p>

### Stat.density

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.density</code> contains estimated density in points. 
</p>

### Stat.densityWeighted

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.densityWeighted</code> contains weighted density. 
Without <code>weights</code> its values matches <code>Stat.density</code> values.
</p>

### Stat.scaled

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.count</code> contains estimated density scaled to maximin of 1.0.. 
</p>

### Stat.scaledWeighted

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.countWeighted</code> contains weighted scaled density. 
Without <code>weights</code> its values matches <code>Stat.scaled</code> values.
</p>

## Area aesthetic properties

See [area](https://kotlin.github.io/kandy/area-api.html).

### x {id="x_area"}

<include from="properties.topic" element-id="x-property"/>

### y {id="y_area"}

<include from="properties.topic" element-id="y-property"/>

### alpha {id="alpha_area"}

<include from="properties.topic" element-id="alpha-property"/>

### fillColor {id="fill_color_area"}

<include from="properties.topic" element-id="fillColor-property"/>

### borderLine

<include from="properties.topic" element-id="borderLine-property"/>
