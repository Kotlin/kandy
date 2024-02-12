# rectangles

<tldr>
<p><format style="bold" color="GoldenRod">rectangles</format> <format style="italic">{ this: RectanglesContext -></format></p>
<list type="none">
<li>
<a href="#xmin"><format style="bold" color="CadetBlue">xMin</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"></include>
</li>
<li>
<a href="#xmax"><format style="bold" color="CadetBlue">xMax</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"></include>
</li>
<li>
<a href="#ymin"><format style="bold" color="CadetBlue">yMin</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"></include>
</li>
<li>
<a href="#ymax"><format style="bold" color="CadetBlue">yMax</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"></include>
</li>

<li>
<a href="#alpha"><format style="bold" color="DarkGray">alpha</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-alpha"></include>
</li>
<li>
<a href="#fillcolor"><format style="bold" color="DarkGray">fillColor</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-color"/>
</li>
<li>
<include from="properties.topic" element-id="signature-of-borderLine"/>
</li>
<li>
<a href="#x"><format style="bold" color="DarkGray">x</format></a>
<include from="properties.topic" element-id="signature-of-axis"/>
</li>
<li>
<a href="#y"><format style="bold" color="DarkGray">y</format></a>
<include from="properties.topic" element-id="signature-of-axis"/>
</li>
</list>
<format style="italic">}</format>
</tldr>

## Properties

### xMin

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `xMin` aesthetic is essential for defining the starting point of rectangles in various plots, particularly in
creating rectangle plots.
It specifies the minimum x-coordinate for each rectangle,
effectively determining where each rectangle begins along the x-axis.
This aesthetic is crucial for accurately positioning rectangles to represent data ranges,
distributions, or categories within a plot.

**Setting**

* `xMin.constant(Any)`: applies a fixed starting x-coordinate for all rectangles in the plot, useful for aligning
  rectangles or setting a uniform baseline across the plot.
  Example: `xMin.constant(0.3`) sets the starting x-coordinate of all rectangles to 0.3.

**Mapping**

* `xMin(Iterable)`: links each rectangle's starting x-coordinate to a value from an iterable collection, enabling
  variable starting points for rectangles based on the iterable's values.
* `xMin(ColumnReference | KProperty | DataColumn)`: dynamically associates the starting x-coordinate of rectangles with
  a DataFrame column, allowing for the visualization of data-driven starting points.
* `xMin(String)`: connects the starting x-coordinate of rectangles to data based on the column name in the DataFrame or
  by key in a Map, offering flexibility in representing data ranges or distributions.

### xMax

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `xMax` aesthetic is crucial for specifying the endpoint of rectangles in plots, particularly useful in creating
rectangle plots.
It defines the maximum x-coordinate for each rectangle, effectively determining the endpoint along the x-axis.
This aesthetic is key to accurately representing data ranges, distributions, or categories within a plot by
setting where each rectangle concludes.

**Setting**

* `xMax.constant(Any)`: applies a fixed ending x-coordinate for all rectangles within the plot, useful for standardizing
  the width of rectangles or ensuring uniform endpoints across the plot.
  Example: `xMax.constant(0.7)` uniformly sets the endpoint x-coordinate of all rectangles to 0.7.

**Mapping**

* `xMax(Iterable)`: associates each rectangle's endpoint x-coordinate with a value from an iterable collection,
  enabling varied endpoints for rectangles based on the iterable's values.
* `xMax(ColumnReference | KProperty | DataColumn)`: dynamically links the endpoint x-coordinate of rectangles to a
  DataFrame column, allowing for the visualization of data-driven endpoints.
* `xMax(String)`: connects the endpoint x-coordinate of rectangles to data based on the column name in the DataFrame or
  by key in a Map, offering flexibility in representing data ranges or distributions.

### yMin

<include from="properties.topic" element-id="yMin-property"/>

### yMax

<include from="properties.topic" element-id="yMax-property"/>

### alpha

<include from="properties.topic" element-id="alpha-property"/>

### fillColor

<include from="properties.topic" element-id="fillColor-property"/>

### borderLine

<include from="properties.topic" element-id="borderLine-property"/>

## Free Scales

### x

<include from="properties.topic" element-id="xFree-property"/>

### y

<include from="properties.topic" element-id="yFree-property"/>
