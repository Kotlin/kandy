# pie

<tldr>
<p><format style="bold" color="GoldenRod">crossBars</format> <format style="italic">{ this: CrossBarsContext -></format></p>
<list type="none">
<li>
<a href="#x"><format style="bold" color="CadetBlue">x</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"/>
</li>
<li>
<a href="#y"><format style="bold" color="CadetBlue">y</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"/>
</li>
<li>
<a href="#slice"><format style="bold" color="CadetBlue">slice</format></a> <format style="superscript">Positional</format>
<emphasis>(Iterable | Column | String)</emphasis>
</li>
<li>
<a href="#explode"><format style="bold" color="DarkGray">explode</format></a> <format style="superscript">NonPositional</format>
<emphasis>(Iterable | Column | String)</emphasis>
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
<a href="#hole"><format style="bold" color="DarkGray">hole</format></a> <format style="superscript">NonPositional</format>
<emphasis>Double</emphasis>
</li>
<li>
<a href="#size"><format style="bold" color="DarkGray">size</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-double"/>
</li>
<li>
<a href="#stroke"><format style="bold" color="DarkGray">stroke</format></a> <format style="superscript">NonPositional</format>
<emphasis>Double</emphasis>
</li>
<li>
<a href="#strokecolor"><format style="bold" color="DarkGray">strokeColor</format></a> <format style="superscript">NonPositional</format>
<emphasis>Color</emphasis>
</li>
</list>
<format style="italic">}</format>
</tldr>

## Properties

### x

[//]: # (TODO required)
<include from="properties.topic" element-id="x-property"/>

### y

<include from="properties.topic" element-id="y-property"/>

### slice

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `slice` aesthetic is a fundamental component of pie plot visualizations, dictating the proportional angles and,
consequently, the sizes of each pie segment.
It enables the representation of categorical or numerical data in a visually compelling pie chart format,
where each slice corresponds to a data category,
and its area reflects the relative magnitude or proportion of the data point.

**Mapping**

* `slice(Iterable)`: maps each pie slice's angle to a value from an iterable collection.
  This method allows the visualization of data proportions directly correlated to the iterable's values,
  enabling diverse and dynamic pie chart constructions.
* `slice(ColumnReference | KProperty | DataColumn)`: associates pie slice sizes with a specific DataFrame column,
  dynamically adjusting slice proportions based on the column's data.
  This mapping is particularly useful
  for creating pie charts that directly reflect the dataset's categorical distributions or numerical proportions.
* `slice(String)`: links pie slice sizes to data based on the column name within a DataFrame or by a key in a Map. This
  flexibility supports varied data representations within pie charts, accommodating both categorical and numerical
  datasets.

### explode

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `explode` aesthetic is an innovative feature in pie chart visualization,
providing a unique way to highlight specific segments of the pie by "exploding" them out from the center.
This aesthetic allows for the emphasis of particular categories or data points,
making them stand out visually in the pie chart.

**Mapping**

* `explode(Iterable)`: associates the explosion of pie segments with values from an iterable collection.
  This approach allows specific segments to be emphasized based on the iterable's values,
  facilitating the creation of pie charts with selectively highlighted segments.
* `explode(ColumnReference | KProperty | DataColumn)`: dynamically links the explosion of pie segments to a specific
  DataFrame column.
  This mapping enables segments to be exploded based on data-driven criteria, providing a powerful
  tool for data visualization that can highlight significant trends or outliers.
* `explode(String)`: connects the explosion of pie segments to data based on the column name within a DataFrame or by
  key in a Map.
  This flexibility supports varied data representations within pie charts,
  accommodating both categorical and numerical datasets.

### alpha

<include from="properties.topic" element-id="alpha-property"/>

### fillColor

<include from="properties.topic" element-id="fillColor-property"/>

### hole

<p>
    <format style="superscript" color="LightSlateGray">Optional</format>
    <format style="superscript" color="#89CFF0">NonPositional</format>
</p>
<p>
    <format style="superscript" color="#E8488B">Double</format>
</p>

The `hole` aesthetic introduces a distinctive feature for pie chart visualization by allowing the creation of a circular
cut-out or "hole" in the center of the pie chart.
This aesthetic transforms a traditional pie chart into a donut chart,
offering a visually appealing way to compare parts of a whole while emphasizing the pie's structure and composition.

**Setting**

* `hole = Double`: assign a value directly to the hole property to define the hole's size.
  This setting is applied uniformly across the pie chart, affecting the visual representation of all slices.

### size

<include from="properties.topic" element-id="size-property"/>

### stroke

<p>
    <format style="superscript" color="LightSlateGray">Optional</format>
    <format style="superscript" color="#89CFF0">NonPositional</format>
</p>
<p>
    <format style="superscript" color="#E8488B">Iterable</format>
    <format style="superscript" color="#E8488B">Column</format>
    <format style="superscript" color="#E8488B">String</format>
    <format style="superscript" color="#E8488B">Color</format>
</p>

The `stroke` aesthetic is a critical attribute for pie chart visualizations, enabling customization of the outline
thickness around each pie slice.
This feature provides an additional layer of visual distinction, enhancing both the
aesthetic appeal and the readability of the pie chart by clearly delineating individual segments.

**Setting**

* `stroke = Double`: assign a numeric value to the stroke property to define the outline's width.
  This setting uniformly applies to all slices within the pie chart, ensuring consistency across the visualization.

### strokeColor

<p>
    <format style="superscript" color="LightSlateGray">Optional</format>
    <format style="superscript" color="#89CFF0">NonPositional</format>
</p>
<p>
    <format style="superscript" color="#E8488B">Iterable</format>
    <format style="superscript" color="#E8488B">Column</format>
    <format style="superscript" color="#E8488B">String</format>
    <format style="superscript" color="#E8488B">Color</format>
</p>

The `strokeColor`aesthetic provides the capability to customize the color of the outline or stroke that surrounds visual
elements in a pie chart.
This aesthetic enhances the visual differentiation and appeal of the chart
by allowing the outlines of pie slices to be colored distinctly.

**Setting**

* `strokeColor = Color`: Assign a Color value to the strokeColor property to define the color of the outline.
  This setting applies uniformly to the outlines of all slices within the pie chart, ensuring a consistent visual theme.
