# smoothLine

<tldr>
<p><format style="bold" color="GoldenRod">
smoothLine</format>(
<a href="#x"><format style="bold" color="CadetBlue">x</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include>,
<a href="#y"><format style="bold" color="CadetBlue">y</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include>,
<a href="#method"><format style="bold" color="DarkGray">method</format></a>: SmoothMethod = SmoothMethod.LOESS(),
<a href="#smootherpointcount"><format style="bold" color="DarkGray">smootherPointCount</format></a>: Int = 100
) <format style="italic">{ this: SmoothLineLayerContext -></format></p>
<include from = "interfaces.topic" element-id="interface-of-line"></include>
<format style="italic">}</format>
</tldr>

The `smoothLine` adds a smoothed line layer
function optionally creates a new context with [line](https://kotlin.github.io/kandy/line-api.html) 
aesthetics and new dataset
which contains the "smooth" statistic calculated on sample of points 
(two numeric variables [x](#x) and [y](#y)).
In this context, all required aesthetics are assigned by default but can be overridden.

It makes the line smoother through regression and sampling new points.


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

## Line properties

See [line](https://kotlin.github.io/kandy/line-api.html).

### x {id=x_line}

<include from="properties.topic" element-id="x-property"/>

### y {id=y_line}

<include from="properties.topic" element-id="y-property"/>

### alpha {id=alpha_line}

<include from="properties.topic" element-id="alpha-property"/>

### width {id=width_line}

<include from="properties.topic" element-id="widthAsSize-property"/>

### color {id=color_line}

<include from="properties.topic" element-id="color-property"/>

### type {id=type_line}

<include from="properties.topic" element-id="type-property"/>
