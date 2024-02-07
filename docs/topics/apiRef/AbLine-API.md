# abLine

<tldr>
<p><format style="bold" color="GoldenRod">abLine</format> <format style="italic">{ this: ABLineContext -></format></p>
<list type="none">
<li>
<a href="#intercept"><format style="bold" color="CadetBlue">intercept</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"></include>
</li>
<li>
<a href="#slope"><format style="bold" color="CadetBlue">slope</format></a> <format style="superscript">Positional</format>
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
<a href="#x"><format style="bold" color="DarkGray">x</format></a>
<include from="properties.topic" element-id="signature-of-axis"></include>
</li>
<li>
<a href="#y"><format style="bold" color="DarkGray">y</format></a>
<include from="properties.topic" element-id="signature-of-axis"></include>
</li>
</list>
<format style="italic">}</format>
</tldr>

The `abLine` function adds an `abLine` layer to the plot,
which is designed to draw a line defined by its slope ([slope](#slope)) and y-intercept ([intercept](#intercept)).

## Properties

### intercept

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `intercept` aesthetic is pivotal in defining the y-intercept of a line, particularly within `abLine` plots.
This aesthetic allows for the precise positioning of a line at a specific y-coordinate where it intercepts the y-axis,
enabling the representation of fixed values, trends, or benchmarks in data visualization.

**Setting**

* `intercept.constant(Any)`: applies a fixed intercept for the line across the plot,
  setting its starting point on the y-axis.
  This is particularly useful for drawing reference lines or showcasing specific thresholds.
  Example usage: `intercept.constant(12)` sets the y-intercept of the line to 12.

**Mapping**

* `intercept(Iterable)`: each line's intercept is associated with a value from an iterable collection, enabling the
  drawing of multiple lines at different y-intercepts based on the provided values.
* `intercept(ColumnReference | KProperty | DataColumn)`: links the line's intercept to a DataFrame column, facilitating
  the dynamic positioning of lines based on data-driven y-intercepts.
* `intercept(String)`: connects the line's intercept to data based on the column name in the DataFrame or by key in a
  Map, offering flexibility in representing intercepts through data.

### slope

<include from="properties.topic" element-id="req-position-aes-desc"/>

The `slope` aesthetic is integral to defining the inclination of lines within `abLine` plots,
facilitating the depiction of linear relationships or trends in the data.
By controlling the slope of a line,
this aesthetic allows for a visual representation of varying degrees of relationships,
whether they indicate increase, decrease, or stability over a dimension.

**Setting**

* `slope.constant(Any)`: assigns a fixed slope to a line, determining its angle of inclination across the plot.
  This is crucial for illustrating specific trends or theoretical models.
  Example usage: `slope.constant(0.5)` sets the slope of the line to 0.5, creating an upward angle from left to right.

**Mapping**

* `slope(Iterable)`: associates the slope of each line with a value from an iterable collection, enabling the depiction
  of multiple lines with varied slopes to represent different trends or data segments.
* `slope(ColumnReference | KProperty | DataColumn)`: links the slope of the line to a DataFrame column, allowing for
  dynamic representations of slopes based on the dataset.
* `slope(String)`: connects the slope of the line to data based on the column name in the DataFrame or by key in a Map,
  offering adaptability in representing linear trends through data.

### alpha

<include from="properties.topic" element-id="alpha-property"/>

### color

<include from="properties.topic" element-id="color-property"/>

### type

<include from="properties.topic" element-id="type-property"/>

### width

<include from="properties.topic" element-id="widthAsSize-property"/>

## Free Scales

### x

<include from="properties.topic" element-id="xFree-property"></include>

### y

<include from="properties.topic" element-id="yFree-property"/>