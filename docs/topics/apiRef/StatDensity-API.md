# statDensity

<tldr>
<p><format style="bold" color="GoldenRod">
statDensity</format>(
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
) <format style="italic">{ this: StatDensityContext -></format></p>

<format style="italic">}</format>
</tldr>

The `statDensity` function creates a new plotting context with new dataset
which contains the "density" statistic calculated on sample of a single numeric variable [x](#x).
It approximates the Probability Density Function (PDF) of this sample. 
"Density" statistic samples this function points
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
