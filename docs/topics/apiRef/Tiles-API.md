# tiles

<tldr>
<p><format style="bold" color="GoldenRod">tiles</format> <format style="italic">{ this: TilesContext -></format></p>
<list type="none">
<li>
<a href="#x"><format style="bold" color="CadetBlue">x</format></a> <format style="superscript">Positional</format>
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
<a href="#height"><format style="bold" color="DarkGray">height</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-double"></include>
</li>
<li>
<a href="#width"><format style="bold" color="DarkGray">width</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-double"></include>
</li>
<li>
<include from="properties.topic" element-id="signature-of-borderLine"/>
</li>
</list>
<format style="italic">}</format>
</tldr>

## Properties

### x

<include from="properties.topic" element-id="x-property"/>

### y

<include from="properties.topic" element-id="y-property"/>

### alpha

<include from="properties.topic" element-id="alpha-property"/>

### fillColor

<include from="properties.topic" element-id="fillColor-property"/>

### height

<p>
    <format style="superscript" color="LightSlateGray">Optional</format>
    <format style="superscript" color="#89CFF0">NonPositional</format>
</p>
<p>
    <format style="superscript" color="#E8488B">Iterable</format>
    <format style="superscript" color="#E8488B">Column</format>
    <format style="superscript" color="#E8488B">String</format>
    <format style="superscript" color="#E8488B">Double</format>
</p>

The `height` aesthetic is fundamental in plotting tile-like elements,
offering control over the vertical dimension of these elements within the plot.
This aesthetic allows for precise adjustment of the height of tiles,
crucial for visualizing data in formats like heatmaps or tile-based visualizations
where the height of each tile may represent a value or category.

**Setting**

* `height = Double`: directly assigns a fixed height to all tile-like plot elements, providing a uniform appearance
  across the plot.
  This is particularly useful for standardizing the presentation of data when the differentiation is
  conveyed through other aesthetics like color.
  Example: `height = 3.5` sets a uniform height of 3.5 units for all tiles in the plot.

**Mapping**

* `height(Iterable)`: dynamically sets the height of tiles based on values from an iterable collection, allowing the
  height of each tile to vary according to the iterable's values.
* `height(ColumnReference | KProperty | DataColumn)`: links the height of tiles to a DataFrame column, enabling the
  visualization of data-driven heights for each tile.
* `height(String)`: associates the height of tiles with data based on the column name in the DataFrame or by key in a
  Map, offering flexibility in data representation through tile height.

### width

<include from="properties.topic" element-id="width-property"/>

### borderLine

<include from="properties.topic" element-id="borderLine-property"/>
