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
<a href="#xfree"><format style="bold" color="DarkGray">xFree</format></a>
<include from="properties.topic" element-id="signature-of-axis"></include>
</li>
<li>
<a href="#yfree"><format style="bold" color="DarkGray">yFree</format></a>
<include from="properties.topic" element-id="signature-of-axis"></include>
</li>
</list>
<format style="italic">}</format>
</tldr>

The `abLine` function adds an `abLine` layer to the plot,
which is designed to draw a line defined by its slope ([slope](#slope)) and y-intercept ([intercept](#intercept)).

## Properties

### intercept

<p>
<format style="superscript" color="Red">Required</format>
<format style="superscript" color="#89CFF0">Positional</format>
</p>
<p>
<format style="superscript" color="#E8488B">Iterable</format>
<format style="superscript" color="#E8488B">Column</format>
<format style="superscript" color="#E8488B">String</format>
<format style="superscript" color="#E8488B">Any</format>
</p>

### slope

<p><format style="superscript">Positional</format></p>
<p><format style="superscript">Iterable</format>
<format style="superscript">Column</format>
<format style="superscript">String</format>
<format style="superscript">Any</format></p>

### alpha

<include from="properties.topic" element-id="alpha-property"/>

### color

<include from="properties.topic" element-id="color-property"/>

### type

<include from="properties.topic" element-id="type-property"/>

### width

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

### xFree

<p><format style="superscript" color="LightSlateGray">Optional</format></p>
<p><format style="superscript" color="#E8488B">AxisParameters</format></p>

### yFree

<p><format style="superscript" color="LightSlateGray">Optional</format></p>
<p><format style="superscript" color="#E8488B">AxisParameters</format></p>