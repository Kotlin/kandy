# boxplot

<tldr>
<p><format style="bold" color="GoldenRod">
boxplot&lt;<a href="#xtype"><format color="Blue">X</format></a>></format>(
<a href="#x"><format style="bold" color="CadetBlue">x</format></a>:
<include from="arguments.topic" element-id="signature-of-sample-x"></include>,
<a href="#y"><format style="bold" color="CadetBlue">y</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include>,
<a href="#whiskeriqrratio"><format style="bold" color="DarkGray">whiskerIQRRatio</format></a>: 
Double = 1.5)<format style="italic">{ this: BoxplotStatLayerContext&lt;X> -></format></p>
<list type="none">
<li>
<p><a href="#boxes_aes">boxes</a> <format style="italic">{ this: BoxesStatContext&lt;X> -></format></p>
<include from="interfaces.topic" element-id="interface-of-boxes"/>
<format style="italic">}</format>
</li>
<li>
<p><a href="#outliers_aes">outliers</a> <format style="italic">{ this: OutliersContext&lt;X> -></format></p>
<include from="interfaces.topic" element-id="interface-of-points"/>
<format style="italic">}</format>
</li>
</list>
<format style="italic">}</format>
</tldr>

The `boxplot` adds a boxplot layer
function optionally creates a new context, where boxplot boxes and outliers can be configured 
(through opening corresponding contexts which contain [points](https://kotlin.github.io/kandy/points-api.html)/[boxes](https://kotlin.github.io/kandy/boxes-api.html) 
aesthetics and "boxplot"/"boxplot outliers" statistic calculated
on sample of a single numeric variable [y](#y)
in several groups defined by [x](#x)).
In these contexts, all required aesthetics are assigned by default but can be overridden.

Boxplot serves as a statistical visualization technique,
illustrating the distribution and summary statistics of a dataset in a graphical format.

## Arguments

### X {id=xtype}

<p>Type of x categories</p>

### x

<p><format style="superscript" color="Red">Required</format> </p>
<p> 
<format style="superscript" color="#E8488B">Iterable</format> 
<format style="superscript" color="#E8488B">Column</format> 
<format style="superscript" color="#E8488B">String</format> 
</p>
<p> The <code>x</code> argument is used to provide the
x-categories of sample (one boxplot will be counted for each category). 
</p>

### y

<p><format style="superscript" color="Red">Required</format> </p>
<p> 
<format style="superscript" color="#E8488B">Iterable</format> 
<format style="superscript" color="#E8488B">Column</format> 
<format style="superscript" color="#E8488B">String</format> 
</p>
<p> The <code>y</code> argument is used to provide the sample on which the statistic is computed. 
</p>

### whiskerIQRRatio

<p> <format style="superscript" color="LightSlateGray">Optional</format> </p>
<p> <format style="superscript" color="#E8488B">Double</format></p>
<p> The <code>whiskerIQRRatio</code> argument is used to determine the
interquartile range multiplier of whiskers lengths.</p>

## Boxplot statistic properties

In `boxes` context, there are the following statistical properties that can be used
to create mappings, customize tooltips, etc.

### Stat.x

<p><format style="superscript" color="#E8488B">Column&lt;X></format></p>
<p>The <code>Stat.x</code> contains boxplot x category. 
</p>

### Stat.min

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.min</code> contains lower whisker ends - the minimum non-outlier data point. 
</p>

### Stat.lower

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.lower</code> contains lower box edges - the first quartile (Q1).
</p>

### Stat.middle

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.middle</code> contains medians - the second quartile (Q2).
</p>

### Stat.upper

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.upper</code> contains upper box edges - the third quartile (Q3). 
</p>

### Stat.max

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.upper</code> contains upper whisker ends - the maximum non-outlier data point. 
</p>

## Boxes aesthetic properties {id=boxes_aes}

### x {id=x_boxes}

<include from="properties.topic" element-id="x-property-default"/>

### lower {id=lower_boxes}

<include from="properties.topic" element-id="req-position-aes-desc-default"/>

<include from="properties.topic" element-id="lower-property-desc"/>

### middle {id=middle_boxes}

<include from="properties.topic" element-id="req-position-aes-desc-default"/>

<include from="properties.topic" element-id="middle-property-desc"/>

### upper {id=upper_boxes}

<include from="properties.topic" element-id="req-position-aes-desc-default"/>

<include from="properties.topic" element-id="upper-property-desc"/>

### yMin {id=ymin_boxes}

<include from="properties.topic" element-id="yMin-property-default"/>

### yMax {id=ymax_boxes}

<include from="properties.topic" element-id="yMax-property-default"/>

### alpha {id=alpha_boxes}

<include from="properties.topic" element-id="alpha-property"/>

### width {id=width_boxes}

<include from="properties.topic" element-id="width-property"/>

### fillColor {id=fill_color_boxes}

<include from="properties.topic" element-id="fillColor-property"/>

### fatten {id=fatten_boxes}

<include from="properties.topic" element-id="fatten-property"/>

### borderLine

<include from="properties.topic" element-id="borderLine-property"/>

## Free Scales

### y {id=y_boxes}

<include from="properties.topic" element-id="yFree-property"/>

## Outlers statistic properties

In `outliers` context, there are the following statistical properties that can be used
to create mappings, customize tooltips, etc.

### Stat.x {id=stat_x_outliers}

<p><format style="superscript" color="#E8488B">Column&lt;X></format></p>
<p>The <code>Stat.x</code> contains outlier x category. 
</p>

### Stat.y

<p><format style="superscript" color="#E8488B">Column&lt;Doule></format></p>
<p>The <code>Stat.y</code> contains outlier value. 
</p>

## Outliers aesthetic properties {id=outliers_aes}

See [points](https://kotlin.github.io/kandy/points-api.html).

### x {id=x_points}

<p>
<format style="superscript" color="Green">Has default mapping</format> 
<format style="superscript" color="#89CFF0">Positional</format> 
</p>
<include from="properties.topic" element-id="x-property-desc"/>

### y {id=y_points}

<p>
<format style="superscript" color="Green">Has default mapping</format> 
<format style="superscript" color="#89CFF0">Positional</format> 
</p>
<include from="properties.topic" element-id="y-property-desc"/>

### alpha {id=alpha_points}

<include from="properties.topic" element-id="alpha-property"/>

### symbol {id=symbol_points}

<include from="properties.topic" element-id="symbol-property"/>

### color {id=color_points}

<include from="properties.topic" element-id="color-property"/>

### fillColor {id=fill_color_points}

<include from="properties.topic" element-id="fillColor-property"/>

### size {id=size_points}

<include from="properties.topic" element-id="size-property"/>

### stroke {id=stroke_points}

<include from="properties.topic" element-id="stroke-point-property"/>
