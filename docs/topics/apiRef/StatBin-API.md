# statBin

<tldr>
<p><format style="bold" color="GoldenRod">
statBin</format>(
<a href="#x"><format style="bold" color="CadetBlue">x</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include>,
<a href="#weights"><format style="bold" color="DarkGray">weights</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include> = null,
<a href="#binsoption"><format style="bold" color="DarkGray">binsOption</format></a>: 
BinsOption = BinsOption.byNumber(20),
<a href="#binsalign"><format style="bold" color="DarkGray">binsAlign</format></a>: 
BinsAlign = BinsAlign.center(0.0)) <format style="italic">{ this: StatBinContext -></format></p>

<format style="italic">}</format>
</tldr>

The `statBin` function creates a new plotting context with new dataset
which contains the "bin" statistic calculated on sample of a single numeric variable [x](#x).
Firstly, it divides the range of values into bins (sequential, non-overlapping sections), 
and then it counts the number of observations in each bin. 
It's weighted, it means the weighted count for each bin is calculated 
(each element within a bin counted along with its weight). 
A bin-constructing methods can be configured.

## Arguments

### x

<include from="arguments.topic" element-id="x-argument"/>

### weights

<include from="arguments.topic" element-id="weights-argument"/>

### binsOption

<include from="arguments.topic" element-id="bins-option-argument"/>

### binsAlign

<include from="arguments.topic" element-id="bins-align-argument"/>

## Statistic properties

In this context, there are the following statistical properties that can be used 
to create mappings, customize tooltips, etc.

### Stat.x

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.x</code> contains centers of bins. 
</p>

### Stat.count

<p><format style="superscript" color="#E8488B">Column&lt;Int></format></p>
<p>The <code>Stat.count</code> contains numbers of observations in this bin. 
</p>

### Stat.countWeighted

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.countWeighted</code> contains weighted count (sum of observations weights in this bin). 
Without <code>weights</code> its values matches <code>Stat.count</code> values.
</p>

### Stat.density

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.count</code> contains empirically estimated density in bins. 
</p>

### Stat.densityWeighted

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.countWeighted</code> contains weighted density. 
Without <code>weights</code> its values matches <code>Stat.density</code> values.
</p>
