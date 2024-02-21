# pointRanges

<tldr>
<p><format style="bold" color="GoldenRod">pointRanges</format> <format style="italic">{ this: PointRangesContext -></format></p>
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
<a href="#ymin"><format style="bold" color="CadetBlue">yMin</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"/>
</li>
<li>
<a href="#ymax"><format style="bold" color="CadetBlue">yMax</format></a> <format style="superscript">Positional</format>
<include from="properties.topic" element-id="signature-of-positional"/>
</li>

<li>
<a href="#alpha"><format style="bold" color="DarkGray">alpha</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-alpha"/>
</li>
<li>
<a href="#size"><format style="bold" color="DarkGray">size</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-double"/>
</li>
<li>
<a href="#color"><format style="bold" color="DarkGray">color</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-color"/>
</li>

<li>
<a href="#innerline"><format style="bold" color="DarkGray">innerLine</format></a>
<format style="italic">{ this: InnerLineContext -></format>
<list type="none">
    <li>
        <a href="#innerline-type">
            <format style="bold" color="DarkGray">type</format>
        </a>
        <format style="superscript">NonPositional</format>
        <include from="properties.topic" element-id="signature-of-nonpos-linetype"/>
    </li>
</list>
<format style="italic"> }</format>
</li>
<li>
<a href="#innerpoint"><format style="bold" color="DarkGray">innerPoint</format></a>
<format style="italic">{ this: InnerPointContext -></format>
<list type="none">
    <li>
        <a href="#innerpoint-symbol">
            <format style="bold" color="DarkGray">symbol</format>
        </a>
        <format style="superscript">NonPositional</format>
        <emphasis>(Iterable | Column | String) | Symbol</emphasis>
    </li>
    <li>
        <a href="#innerpoint-fillcolor">
            <format style="bold" color="DarkGray">fillColor</format>
        </a>
        <format style="superscript">NonPositional</format>
        <include from="properties.topic" element-id="signature-of-nonpos-color"/>
    </li>
    <li>
        <a href="#innerpoint-fatten">
            <format style="bold" color="DarkGray">fatten</format>
        </a>
        <format style="superscript">NonPositional</format>
        <emphasis>Double</emphasis>
    </li>
</list>
<format style="italic"> }</format>
</li>
</list>
<format style="italic">}</format>
</tldr>


## Properties

### x

<include from="properties.topic" element-id="x-property"/>

### y

<include from="properties.topic" element-id="y-property"/>

### yMin

<include from="properties.topic" element-id="yMin-property"/>

### yMax

<include from="properties.topic" element-id="yMax-property"/>

### alpha

<include from="properties.topic" element-id="alpha-property"/>

### size

<include from="properties.topic" element-id="size-property"/>

### color

<include from="properties.topic" element-id="color-property"/>

### innerLine

<chapter title="innerLine.type" collapsible="true" id="innerline-type">

<include from="properties.topic" element-id="type-property"/>

</chapter>

### innerPoint

<chapter title="innerPoint.symbol" collapsible="true" id="innerpoint-symbol">

<include from="properties.topic" element-id="symbol-property"/>

</chapter>

<chapter title="innerPoint.fillColor" collapsible="true" id="innerpoint-fillcolor">

<include from="properties.topic" element-id="fillColor-property"/>

</chapter>

<chapter title="innerPoint.fatten" collapsible="true" id="innerpoint-fatten">

<include from="properties.topic" element-id="fatten-property"/>

</chapter>
