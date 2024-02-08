# hLine

<tldr>
<p><format style="bold" color="GoldenRod">hLine</format> <format style="italic">{ this: HLineContext -></format></p>
<list type="none">
<li>
<a href="#yintercept"><format style="bold" color="CadetBlue">yIntercept</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"></include>
</li>

<li>
<a href="#alpha"><format style="bold" color="DarkGray">alpha</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-alpha"></include>
</li>
<li>
<a href="#width"><format style="bold" color="DarkGray">width</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-double"></include>
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
<a href="#x"><format style="bold" color="DarkGray">x</format></a>
<include from="properties.topic" element-id="signature-of-axis"></include>
</li>
</list>
<format style="italic">}</format>
</tldr>

## Properties

### yIntercept

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `yIntercept` aesthetic is vital for creating horizontal line plots,
defining the specific y-coordinate at which each line intercepts the y-axis.
This aesthetic is used primarily in hLine plots to represent thresholds,
benchmarks, or specific values of interest across the y-axis.

**Setting**

* `yIntercept.constant(Any)`: assigns a fixed y-coordinate for the horizontal line's interception with the y-axis,
  effectively positioning the line at a specified level across the plot.
  Example: `yIntercept.constant(0.5)` places a horizontal line at the y-coordinate of 0.5.

**Mapping**

* `yIntercept(Iterable)`: each line's y-intercept is linked to a value from an iterable collection, allowing for the
  drawing of multiple horizontal lines at different y-coordinates to represent various data points or thresholds.
* `yIntercept(ColumnReference | KProperty | DataColumn)`: dynamically associates the y-intercept of lines with a
  DataFrame column, enabling the representation of data-driven y-intercepts.
* `yIntercept(String)`: connects the y-intercept of lines to data based on the column name in the DataFrame or by key in
  a Map, offering adaptability in representing y-intercepts through data.

### alpha

<include from="properties.topic" element-id="alpha-property"/>

### width

<include from="properties.topic" element-id="widthAsSize-property"/>

### color

<include from="properties.topic" element-id="color-property"/>

### type

<include from="properties.topic" element-id="type-property"/>

## Free Scales

### x

<include from="properties.topic" element-id="xFree-property"/>