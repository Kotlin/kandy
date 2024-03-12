# histogram

<tldr>
<p><format style="bold" color="GoldenRod">
histogram</format>(
<a href="#x"><format style="bold" color="CadetBlue">x</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include>,
<a href="#weights"><format style="bold" color="DarkGray">weights</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include> = null,
<a href="#binsoption"><format style="bold" color="DarkGray">binsOption</format></a>: 
BinsOption = BinsOption.byNumber(20),
<a href="#binsalign"><format style="bold" color="DarkGray">binsAlign</format></a>: 
BinsAlign = BinsAlign.center(0.0)) <format style="italic">{ this: StatBinContext -></format></p>
<list type="none">
<li>
<a href="#xaes"><format style="bold" color="CadetBlue">x</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"></include>
</li>
<li>
<a href="#y"><format style="bold" color="CadetBlue">y</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"></include>
</li>
<li>
<a href="#alpha"><format style="bold" color="DarkGray">alpha</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-alpha"></include>
</li>
<li>
<a href="#fillcolor"><format style="bold" color="DarkGray">fillColor</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-color"></include>
</li>
<li>
<include from="properties.topic" element-id="signature-of-borderLine"/>
</li>
</list>
<format style="italic">}</format>
<format style="italic">}</format>
</tldr>

The `histogram` adds a histogram layer 
(bars layer with bin statistics counted)
function optionally creates a new context with bars aesthetics and new dataset
which contains the "bin" statistic calculated on sample of a single numeric variable [x](#x). 
In this context, all required aesthetics are assigned by default but can be overridden.

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

## Aesthetic properties

See [bars](https://kotlin.github.io/kandy/bars-api.html).

### x {id="xaes"}

<p>
<format style="superscript" color="Green">Has default mapping</format> 
<format style="superscript" color="#89CFF0">Positional</format> 
</p>
<include from="properties.topic" element-id="x-property-desc"/>

### y

<p>
<format style="superscript" color="Green">Has default mapping</format> 
<format style="superscript" color="#89CFF0">Positional</format> 
</p>
<include from="properties.topic" element-id="y-property-desc"/>

### alpha

<include from="properties.topic" element-id="alpha-property"/>

### width

<include from="properties.topic" element-id="width-property"/>

### fillColor

<include from="properties.topic" element-id="fillColor-property"/>

### borderLine

<include from="properties.topic" element-id="borderLine-property"/>
