# statSmooth

<tldr>
<p><format style="bold" color="GoldenRod">
statSmooth</format>(
<a href="#x"><format style="bold" color="CadetBlue">x</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include>,
<a href="#y"><format style="bold" color="CadetBlue">y</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include>,
<a href="#method"><format style="bold" color="DarkGray">method</format></a>: SmoothMethod = SmoothMethod.LOESS(),
<a href="#smootherpointcount"><format style="bold" color="DarkGray">smootherPointCount</format></a>: Int = 100
) <format style="italic">{ this: StatSmoothContext -></format></p>

<format style="italic">}</format>
</tldr>

The `statSmooth` function creates a new plotting context with new dataset
which contains the "smooth" statistic calculated on sample of points (two numeric variables [x](#x) and [y](#y)).
It interpolates data points to create a smoother curve.

## Arguments

### x

<include from="arguments.topic" element-id="x-argument"/>

### y

<include from="arguments.topic" element-id="y-argument"/>


### method

<p> <format style="superscript" color="LightSlateGray">Optional</format> </p>
<p> <format style="superscript" color="#E8488B">SmoothMethod</format></p>
<p> 
The <code>method</code> argument is used to specify 
the smoothing method.</p>

<list>
<li>
<code>SmoothMethod.Linear(confidenceLevel: Double)</code> - linear model;
</li>
<li>
<code>SmoothMethod.Polynomial(degree: Int, confidenceLevel: Double)</code> - polynomial model;
</li>
<li>
<code>SmoothMethod.LOESS(span: Double, loessCriticalSize: Int, samplingSeed: Long, confidenceLevel: Double)</code> 
- Local Polynomial Regression model.
</li>
</list>

### smootherPointCount

<p><format style="superscript" color="LightSlateGray">Optional</format> </p>
<p> <format style="superscript" color="#E8488B">Int</format></p>
<p> 
The <code>n</code> argument is used to specify 
the number of sampled points.
</p>

## Statistic properties

In this context, there are the following statistical properties that can be used
to create mappings, customize tooltips, etc.

### Stat.x

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.x</code> contains points x-coordinate. 
</p>

### Stat.y

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.y</code> contains points y-coordinate. 
</p>

### Stat.yMin

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.yMin</code> lower point-wise confidence interval around the mean in this point.
</p>

### Stat.yMax

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.yMax</code> upper point-wise confidence interval around the mean in this point.
</p>

### Stat.se

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.se</code> contains standard error in this point.
</p>
