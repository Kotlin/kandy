# statCount

<tldr>
<p><format style="bold" color="GoldenRod">
statCount<a href="#xtype"><format color="Blue">&lt;X></format></a></format>(
<a href="#x"><format style="bold" color="CadetBlue">x</format></a>:
<include from="arguments.topic" element-id="signature-of-sample-x"></include>,
<a href="#weights"><format style="bold" color="DarkGray">weights</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include> = null
) <format style="italic">{ this: StatCountContext -></format></p>

<format style="italic">}</format>
</tldr>

The `statCount` function creates a new plotting context with new dataset
which contains the "count" statistic calculated on sample of a single categorical variable [x](#x).
It counts the number of observations in each category. 
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
