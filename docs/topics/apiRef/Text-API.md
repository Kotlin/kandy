# text

<tldr>
<p><format style="bold" color="GoldenRod">text</format> <format style="italic">{ this: TextContext -></format></p>
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
<a href="#label"><format style="bold" color="CadetBlue">label</format></a> <format style="superscript">NonPositional</format>
<emphasis>(Iterable | Column | String) | String</emphasis>
</li>

<li>
<a href="#alpha"><format style="bold" color="DarkGray">alpha</format></a> <format style="superscript">NonPositional</format>
<include from="properties.topic" element-id="signature-of-nonpos-alpha"/>
</li>

<li>
<a href="#font"><format style="bold" color="DarkGray">font</format></a>
<format style="italic">{ this: FontContext -></format>
<list type="none">
    <li>
        <a href="#font-color">
            <format style="bold" color="DarkGray">color</format>
        </a>
        <format style="superscript">NonPositional</format>
        <include from="properties.topic" element-id="signature-of-nonpos-color"/>
    </li>
    <li>
        <a href="#font-face">
            <format style="bold" color="DarkGray">face</format>
        </a>
        <format style="superscript">NonPositional</format>
        <emphasis>(Iterable | Column | String) | FontFace</emphasis>
    </li>
    <li>
        <a href="#font-family">
            <format style="bold" color="DarkGray">family</format>
        </a>
        <format style="superscript">NonPositional</format>
        <emphasis>(Iterable | Column | String) | FontFamily</emphasis>
    </li>
    <li>
        <a href="#font-size">
            <format style="bold" color="DarkGray">size</format>
        </a>
        <format style="superscript">NonPositional</format>
        <include from="properties.topic" element-id="signature-of-nonpos-double"/>
    </li>
</list>
<format style="italic"> }</format>
</li>
</list>
<format style="italic">}</format>
</tldr>

>  Because `text` is still in development, this API may be subject to change.
{style="warning"}

## Properties

### x

<include from="properties.topic" element-id="x-property"/>

### y

<include from="properties.topic" element-id="y-property"/>

### label

### alpha

<include from="properties.topic" element-id="alpha-property"/>

### font

<chapter title="font.color" collapsible="true" id="font-color">

<include from="properties.topic" element-id="color-property"/>

</chapter>

<chapter title="font.face" collapsible="true" id="font-face">

<p>
    <format style="superscript" color="LightSlateGray">Optional</format>
    <format style="superscript" color="#89CFF0">NonPositional</format>
</p>
<p>
    <format style="superscript" color="#E8488B">Iterable</format>
    <format style="superscript" color="#E8488B">Column</format>
    <format style="superscript" color="#E8488B">String</format>
    <format style="superscript" color="#E8488B">FontFace</format>
</p>

The `face` aesthetic refers to the font face in text elements of a plot layer.

- `PLAIN`
- `ITALIC`
- `BOLD`
- `BOLD_ITALIC`

</chapter>

<chapter title="font.family" collapsible="true" id="font-family">

<p>
    <format style="superscript" color="LightSlateGray">Optional</format>
    <format style="superscript" color="#89CFF0">NonPositional</format>
</p>
<p>
    <format style="superscript" color="#E8488B">Iterable</format>
    <format style="superscript" color="#E8488B">Column</format>
    <format style="superscript" color="#E8488B">String</format>
    <format style="superscript" color="#E8488B">FontFamily</format>
</p>

The `family` aesthetic refers to the font family in text elements of a plot layer.

- `SANS` 
- `SERIF` 
- `MONO`
- `custom(name: String)`

</chapter>

<chapter title="font.size" collapsible="true" id="font-size">

<include from="properties.topic" element-id="size-property"/>

</chapter>
