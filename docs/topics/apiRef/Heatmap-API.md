# heatmap

<tldr>
<p><format style="bold" color="GoldenRod">
heatmap&lt;<a href="#xtype"><format color="Blue">X</format></a>,
<a href="#ytype"><format color="Blue">Y</format></a>>
</format>(
<a href="#x"><format style="bold" color="CadetBlue">x</format></a>:
<include from="arguments.topic" element-id="signature-of-sample-x"></include>,
<a href="#y"><format style="bold" color="CadetBlue">y</format></a>:
<include from="arguments.topic" element-id="signature-of-sample-y"></include>,
<a href="#weights"><format style="bold" color="DarkGray">weights</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include> = null
) <format style="italic">{ this: HeatmapLayerBuilder -></format></p>
<include from="interfaces.topic" element-id="interface-of-tiles"></include>
<format style="italic">}</format>
</tldr>

The `heatmap` adds a heatmap layer
function optionally creates a new context with [tiles](https://kotlin.github.io/kandy/tiles-api.html) aesthetics 
and new dataset which contains the "count2D" statistic calculated 
on sample of two categorical variables [x](#x) and [y](#y). It's a tile plot 
where each tile is representing one of a pair of categories: 
its `x` coordinate is corresponding to x-category, 
`y` to y-category, and its filling color is to [count](#stat-x) of this pair.

## Arguments

### X {id=xtype}

<p>Type of x categories</p>

### Y {id=ytype}

<p>Type of y categories</p>

### x

<include from="arguments.topic" element-id="x-argument"/>

### y

<include from="arguments.topic" element-id="y-argument"/>

### weights

<include from="arguments.topic" element-id="weights-argument"/>

## Statistic properties

In this context, there are the following statistical properties that can be used
to create mappings, customize tooltips, etc.

### Stat.x

<p><format style="superscript" color="#E8488B">Column&lt;X></format></p>
<p>The <code>Stat.x</code> contains x-part of categories. 
</p>

### Stat.y

<p><format style="superscript" color="#E8488B">Column&lt;Y></format></p>
<p>The <code>Stat.x</code> contains y-part categories. 
</p>

### Stat.count

<p><format style="superscript" color="#E8488B">Column&lt;Int></format></p>
<p>The <code>Stat.count</code> contains number of observations in this category 
(each one is specified by its x- and y- part)
</p>

### Stat.countWeighted

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.countWeighted</code> contains weighted count (sum of weights in this category). 
Without <code>weights</code> its values matches <code>Stat.count</code> values.
</p>

## Tiles properties

See [tiles](https://kotlin.github.io/kandy/tiles-api.html).

### x {id=x_tiles}

<include from="properties.topic" element-id="x-property"/>

### y {id=y_tiles}

<include from="properties.topic" element-id="y-property"/>

### alpha {id=alpha_tiles}

<include from="properties.topic" element-id="alpha-property"/>

### fillColor {id=fill_color_tiles}

<include from="properties.topic" element-id="fillColor-property"/>

### height {id=height_tiles}

<include from="properties.topic" element-id="height-property"/>

### width {id=width_tiles}

<include from="properties.topic" element-id="width-property"/>

### borderLine

<include from="properties.topic" element-id="borderLine-property"/>