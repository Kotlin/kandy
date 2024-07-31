# countPlot

<tldr>
<p><format style="bold" color="GoldenRod">
countPlot&lt;<a href="#xtype"><format color="Blue">X</format></a>></format>(
<a href="#x"><format style="bold" color="CadetBlue">x</format></a>:
<include from="arguments.topic" element-id="signature-of-sample-x"></include>,
<a href="#weights"><format style="bold" color="DarkGray">weights</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include> = null
) <format style="italic">{ this: CountPlotLayerBuilder -></format></p>
<include from = "interfaces.topic" element-id="interface-of-bars"></include>
<format style="italic">}</format>
</tldr>

The `countPlot` adds a count-plot layer
function optionally creates a new context with [bars](https://kotlin.github.io/kandy/bars-api.html) aesthetics and new dataset
which contains the "count" statistic calculated on sample of a single categorical variable [x](#x). 
In this context, all required aesthetics are assigned by default but can be overridden.

It's a bar plot where each bar is representing one of the categories: 
its `x` coordinate is corresponding to the category 
and `y` to its count.
It's weighted, it means the weighted count for each category is calculated 
(each element within a category is counted along with its weight).

## Arguments

### X {id=xtype}

<p>Type of x categories</p>

### x

<include from="arguments.topic" element-id="x-argument"/>

### weights

<include from="arguments.topic" element-id="weights-argument"/>

## Statistic properties

In this context, there are the following statistical properties that can be used
to create mappings, customize tooltips, etc.

### Stat.x

<p><format style="superscript" color="#E8488B">Column&lt;X></format></p>
<p>The <code>Stat.x</code> contains categories. 
</p>

### Stat.count

<p><format style="superscript" color="#E8488B">Column&lt;Int></format></p>
<p>The <code>Stat.count</code> contains number of observations in this category 
</p>

### Stat.countWeighted

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.countWeighted</code> contains weighted count (sum of weights in this category). 
Without <code>weights</code> its values matches <code>Stat.count</code> values.
</p>

## Bars aesthetic properties

See [bars](https://kotlin.github.io/kandy/bars-api.html).

### x {id="x_bars"}

<include from="properties.topic" element-id="x-property-default"/>

### y {id="y_bars"}

<include from="properties.topic" element-id="y-property-default"/>

### alpha {id="alpha_bars"}

<include from="properties.topic" element-id="alpha-property"/>

### width {id="width_bars"}

<include from="properties.topic" element-id="width-property"/>

### fillColor {id="fill_color_bars"}

<include from="properties.topic" element-id="fillColor-property"/>

### borderLine

<include from="properties.topic" element-id="borderLine-property"/>