# boxes

<tldr>
<p><format style="bold" color="GoldenRod">boxes</format> <format style="italic">{ this: BoxesContext -></format></p>
<list type="none">
<li>
<a href="#x"><format style="bold" color="CadetBlue">x</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"></include>
</li>
<li>
<a href="#lower"><format style="bold" color="CadetBlue">lower</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"></include>
</li>
<li>
<a href="#middle"><format style="bold" color="CadetBlue">middle</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"></include>
</li>
<li>
<a href="#upper"><format style="bold" color="CadetBlue">upper</format></a> <format style="superscript">Positional</format>
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
<a href="#width"><format style="bold" color="DarkGray">width</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-double"></include>
</li>
<li>
<a href="#fillcolor"><format style="bold" color="DarkGray">fillColor</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-color"></include>
</li>
<li>
<a href="#fatten"><format style="bold" color="DarkGray">fatten</format></a> <format style="superscript">NonPositional</format>
<emphasis>Double</emphasis>
</li>
<li>
<include from="properties.topic" element-id="signature-of-borderLine"/>
</li>
<li>
<a href="#y"><format style="bold" color="DarkGray">y</format></a>
<include from="properties.topic" element-id="signature-of-axis"></include>
</li>
</list>
<format style="italic">}</format>
</tldr>

## Properties

### x

<include from="properties.topic" element-id="x-property"/>

### lower

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `lower` aesthetic is specifically designed for use in box plots, where it represents the lower quartile of the data.
This quartile marks the bottom edge of the box, indicating the value below which 25% of the data points fall.
The `lower` aesthetic is critical for accurately displaying the distribution of data within a box plot.

**Setting**

* `lower.constant(Any)`: directly sets the lower quartile value for all boxes in the plot.
  This approach is useful for highlighting a specific threshold or for comparative analysis across different datasets.
  Example: `lower.constant(0.7)` uniformly sets the lower quartile to 0.7 for all boxes.

**Mapping**

* `lower(Iterable)`: associates the lower quartile of each box with values from an iterable collection. This method
  allows for variable lower quartiles across boxes, useful for visualizing distributions from different groups or
  categories.
* `lower(ColumnReference | KProperty | DataColumn)`: dynamically links the lower quartile of boxes to a DataFrame
  column.
  This mapping is essential for directly visualizing the statistical properties of the dataset.
* `lower(String)`: connects the lower quartile to data based on the column name in the DataFrame or by key in a Map,
  offering flexibility in data representation.

### middle

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `middle` aesthetic is instrumental in visualizing the central tendency within box plots and crossbars,
representing the median of the data in box plots and the central value in crossbars.
This aesthetic is key to conveying the central point around which the distribution or error bars are constructed,
providing a clear visual indicator of the dataset's middle value.

**Setting**

* `middle.constant(Any)`: applies a fixed value for the median or central value across all applicable elements within a
  layer.
  This method is useful for comparative analysis or highlighting specific median values across groups.
  Example: `middle.constant(1.1)` uniformly sets the median or central value to 1.1 for all elements.

**Mapping**

* `middle(Iterable)`: links each element's median or central value to a value from an iterable collection, allowing for
  the visualization of variable middle points across different data groups or categories.
* `middle(ColumnReference | KProperty | DataColumn)`: dynamically associates the median or central value with a
  DataFrame column, enabling the representation of data-driven median values.
* `middle(String)`: connects the median or central value to data based on the column name in the DataFrame or by key in
  a Map, offering flexibility in representing central tendencies through data.

### upper

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `upper` aesthetic plays a crucial role in box plots by representing the upper quartile of the dataset.
This quartile marks the top edge of the box, indicating the value above which 25% of the data points fall.
The `upper` aesthetic is essential for accurately displaying the spread and distribution of data within a box plot.

**Setting**

* `upper.constant(Any)`: assigns a uniform upper quartile value across all boxes in the plot.
  This method is beneficial for standardizing comparisons or highlighting specific thresholds across datasets.
  Example: `upper.constant(1.5)` sets a consistent upper quartile to 1.5 for all boxes.

**Mapping**

* `upper(Iterable)`: each box's upper quartile is associated with a value from an iterable collection, enabling the
  visualization of varying upper quartiles across different data groups or categories.
* `upper(ColumnReference | KProperty | DataColumn)`: dynamically links the upper quartile of boxes to a DataFrame
  column, allowing for the visualization of data-driven upper quartiles.
* `upper(String)`: connects the upper quartile to data based on the column name in the DataFrame or by key in a Map,
  offering flexibility in representing upper limits through data.

### yMin

<include from="properties.topic" element-id="yMin-property"/>

### yMax

<include from="properties.topic" element-id="yMax-property"/>

### alpha

<include from="properties.topic" element-id="alpha-property"/>

### width

<include from="properties.topic" element-id="width-property"/>

### fillColor

<include from="properties.topic" element-id="fillColor-property"/>

### fatten

<include from="properties.topic" element-id="fatten-property"/>

### borderLine

<include from="properties.topic" element-id="borderLine-property"/>

## Free Scales

### y

<include from="properties.topic" element-id="yFree-property"/>
