# statCount2D

<tldr>
<p><format style="bold" color="GoldenRod">
statCount2D<a href="#xtype"><format color="Blue">&lt;X></format></a>
<a href="#ytype"><format color="Blue">&lt;Y></format></a>
</format>(
<a href="#x"><format style="bold" color="CadetBlue">x</format></a>:
<include from="arguments.topic" element-id="signature-of-sample-x"></include>,
<a href="#y"><format style="bold" color="CadetBlue">y</format></a>:
<include from="arguments.topic" element-id="signature-of-sample-y"></include>,
<a href="#weights"><format style="bold" color="DarkGray">weights</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include> = null
) <format style="italic">{ this: StatCount2DContext -></format></p>

<format style="italic">}</format>
</tldr>

The `statCount2D` function creates a new plotting context with new dataset
which contains the "count2D" statistic calculated on sample of a two categorical variable [x](#x) and [y](#y). 
It counts the number of observations in each pair of x-category and y-category. 
It's weighted, it means the weighted count for each pair is calculated 
(each element within a pair is counted along with its weight).

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
