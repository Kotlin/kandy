# segments

<tldr>
<p><format style="bold" color="GoldenRod">segments</format> <format style="italic">{ this: SegmentsContext -></format></p>
<list type="none">
<li>
<a href="#xbegin"><format style="bold" color="CadetBlue">xBegin</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"/>
</li>
<li>
<a href="#xend"><format style="bold" color="CadetBlue">xEnd</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"/>
</li>
<li>
<a href="#ybegin"><format style="bold" color="CadetBlue">yBegin</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"/>
</li>
<li>
<a href="#yend"><format style="bold" color="CadetBlue">yEnd</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"/>
</li>

<li>
<a href="#alpha"><format style="bold" color="DarkGray">alpha</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-alpha"/>
</li>
<li>
<a href="#color"><format style="bold" color="DarkGray">color</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-color"/>
</li>
<li>
<a href="#width"><format style="bold" color="DarkGray">width</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-double"/>
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

### xBegin

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `xBegin` aesthetic plays a pivotal role in the creation of segment plots by specifying the starting x-coordinate for
each segment.
This aesthetic is essential for accurately plotting the beginning points of segments along the x-axis,
thereby defining where each segment originates in the plot.

**Setting**

* `xBegin.constant(Any)`: Assigns a fixed starting x-coordinate for all segments within the plot.
  This approach is beneficial for creating segments that share a common origin
  or for standardizing the initial positions of segments across the plot.
  Example: `xBegin.constant(0.1)` uniformly sets the starting x-coordinate of all segments to 0.1.

**Mapping**

* `xBegin(Iterable)`: links each segment's starting x-coordinate to a value from an iterable collection, enabling the
  creation of segments with variable beginnings based on the iterable's values.
* `xBegin(ColumnReference | KProperty | DataColumn)`: dynamically associates the starting x-coordinate of segments with
  a DataFrame column, allowing for the visualization of data-driven segment origins.
* `xBegin(String)`: connects the starting x-coordinate of segments to data based on the column name in the DataFrame or
  by key in a Map, offering flexibility in representing segment beginnings through data.

### xEnd

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `xEnd` aesthetic is pivotal for defining the termination point of segments in segment plots.
It specifies the ending x-coordinate for each segment,
effectively determining where each segment concludes along the x-axis.
This aesthetic is key for accurately representing the extent of data ranges or intervals within a plot
by setting the precise endpoint of segments.

**Setting**

* `xEnd.constant(Any)`: applies a fixed ending x-coordinate for all segments within the plot.
  This method is useful for creating segments with uniform lengths
  or ensuring consistent endpoint positioning across the plot.
  Example: `xEnd.constant(0.9)` uniformly sets the ending x-coordinate of all segments to 0.9.

**Mapping**

* `xEnd(Iterable)`: associates each segment's ending x-coordinate with a value from an iterable collection, enabling the
  creation of segments with variable endpoints based on the iterable's values.
* `xEnd(ColumnReference | KProperty | DataColumn)`: dynamically links the ending x-coordinate of segments to a DataFrame
  column, allowing for the visualization of data-driven segment endpoints.
* `xEnd(String)`: connects the ending x-coordinate of segments to data based on the column name in the DataFrame or by
  key in a Map, offering flexibility in representing data extents through segments.

### yBegin

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `yBegin` aesthetic is integral to segment plots, indicating the starting y-coordinate for each segment.
This aesthetic is particularly valuable
for accurately plotting the vertical beginning points of segments along the y-axis,
thereby defining the vertical initiation of data representation within the plot.

**Setting**

* `yBegin.constant(Any)`: Assigns a uniform starting y-coordinate for all segments within the plot.
  This method is advantageous for aligning segments vertically
  or setting a consistent baseline across the plot vertically.
  Example: `yBegin.constant(0.1)` sets the starting y-coordinate of all segments to 0.1.

**Mapping**

* `yBegin(Iterable)`: links each segment's starting y-coordinate to a value from an iterable collection, facilitating
  the creation of segments with variable vertical beginnings based on the iterable's values.
* `yBegin(ColumnReference | KProperty | DataColumn)`: dynamically associates the starting y-coordinate of segments with
  a DataFrame column, enabling the visualization of data-driven vertical starting points.
* `yBegin(String)`: connects the starting y-coordinate of segments to data based on the column name in the DataFrame or
  by key in a Map, offering versatility in representing vertical data ranges or distributions.

### yEnd

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `yEnd` aesthetic is crucial for delineating the termination point of segments in segment plots,
signifying the ending y-coordinate for each segment.
This aesthetic determines where each segment concludes vertically,
playing a vital role in accurately representing the vertical extent of data ranges or intervals within the plot.

**Setting**

* `yEnd.constant(Any)`: imposes a uniform ending y-coordinate for all segments within the plot, aiding in the creation
  of segments with consistent lengths or standardizing endpoints vertically across the plot.
  Example: `yEnd.constant(0.9)` sets the ending y-coordinate of all segments to 0.9.

**Mapping**

* `yEnd(Iterable)`: associates each segment's ending y-coordinate with a value from an iterable collection, facilitating
  segments with variable vertical endpoints based on the iterable's values.
* `yEnd(ColumnReference | KProperty | DataColumn)`: dynamically connects the ending y-coordinate of segments to a
  DataFrame column, allowing visualization that reflects data-driven vertical endpoints.
* `yEnd(String)`: ties the ending y-coordinate of segments to data based on the column name in the DataFrame or by key
  in a Map, providing versatility in depicting vertical data extents through segments.

### alpha

<include from="properties.topic" element-id="alpha-property"/>

### color

<include from="properties.topic" element-id="color-property"/>

### width

<include from="properties.topic" element-id="widthAsSize-property"/>

## Free Scales

### x

<include from="properties.topic" element-id="xFree-property"/>

### y

<include from="properties.topic" element-id="yFree-property"/>
