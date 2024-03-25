# statBoxplot

<tldr>
<p><format style="bold" color="GoldenRod">
statBoxplot&lt;<a href="#xtype"><format color="Blue">X</format></a>></format>(
<a href="#x"><format style="bold" color="CadetBlue">x</format></a>:
<include from="arguments.topic" element-id="signature-of-sample-x"></include>,
<a href="#y"><format style="bold" color="CadetBlue">y</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include>,
<a href="#whiskeriqrratio"><format style="bold" color="DarkGray">whiskerIQRRatio</format></a>: 
Double = 1.5)<format style="italic">{ this: StatBoxplotContext -></format></p>

<format style="italic">}</format>
</tldr>

The `statBoxplot` function creates a new plotting context with new dataset
which contains the "boxplot" statistic calculated on sample of a single numeric variable [y](#y) 
in several groups defined by [x](#x). It serves as a statistical visualization technique, 
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

## Statistic properties

In this context, there are the following statistical properties that can be used
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
