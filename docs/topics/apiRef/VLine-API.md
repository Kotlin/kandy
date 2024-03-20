# vLine

<tldr>
<p><format style="bold" color="GoldenRod">vLine</format> <format style="italic">{ this: VLineContext -></format></p>
<list type="none">
<li>
<a href="#xintercept"><format style="bold" color="CadetBlue">xIntercept</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"></include>
</li>

<li>
<a href="#alpha"><format style="bold" color="DarkGray">alpha</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-alpha"></include>
</li>
<li>
<a href="#color"><format style="bold" color="DarkGray">color</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-color"></include>
</li>
<li>
<a href="#type"><format style="bold" color="DarkGray">type</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-linetype"></include>
</li>
<li>
<a href="#width"><format style="bold" color="DarkGray">width</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-double"></include>
</li>
<li>
<a href="#y"><format style="bold" color="DarkGray">y</format></a>
<include from="properties.topic" element-id="signature-of-axis"></include>
</li>
</list>
<format style="italic">}</format>
</tldr>

## Properties

### xIntercept

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `xIntercept`
aesthetic is crucial for defining the point at which vertical lines intersect the x-axis in vertical line plots.
This aesthetic specifies the x-coordinate for the intersection point,
allowing for precise placement of vertical lines within the plot to represent thresholds,
boundaries, or specific values of interest.

**Setting**

* `xIntercept.constant(Any)`: assigns a fixed x-coordinate for the intersection point of all vertical lines within the
  plot.
  This approach is useful for highlighting specific values or thresholds that are consistent across the plot.
  Example: `xIntercept.constant(0.5)` sets a uniform x-coordinate at 0.5 for all vertical lines,
  marking a specific threshold or value.

**Mapping**

* `xIntercept(Iterable)`: links the intersection point of each vertical line to values from an iterable collection,
  enabling variable placement of vertical lines based on the iterable's values.
* `xMax(ColumnReference | KProperty | DataColumn)`: dynamically associates the intersection point of vertical lines with
  a DataFrame column, allowing for data-driven placement of lines.
* `xIntercept(String)`: ties the intersection point of vertical lines to data based on the column name in the DataFrame
  or by key in a Map, offering flexibility in highlighting data-specific thresholds or values.

### alpha

<include from="properties.topic" element-id="alpha-property"/>

### color

<include from="properties.topic" element-id="color-property"/>

### type

<include from="properties.topic" element-id="type-property"/>

### width

<include from="properties.topic" element-id="widthAsSize-property"/>

## Free Scales

### y

<include from="properties.topic" element-id="yFree-property"/>
