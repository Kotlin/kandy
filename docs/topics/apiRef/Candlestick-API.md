# candlestick

<tldr>
<p><format style="bold" color="GoldenRod">
candlestick&lt;<a href="#xtype"><format color="Blue">X</format></a>></format>(
<a href="#x"><format style="bold" color="CadetBlue">x</format></a>:
<include from="arguments.topic" element-id="signature-of-sample-x"></include>,
<a href="#open"><format style="bold" color="CadetBlue">open</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include>,
<a href="#high"><format style="bold" color="CadetBlue">high</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include>,
<a href="#low"><format style="bold" color="CadetBlue">low</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include>,
<a href="#close"><format style="bold" color="CadetBlue">close</format></a>:
<include from="arguments.topic" element-id="signature-of-sample"></include>
<format style="italic">{ this: CandlestickLayerBuilder&lt;X> -></format></p>
<include from = "interfaces.topic" element-id="interface-of-boxes"></include>
<list type="none">
<li>
<a href="#inc_dec">increase </a><format style="italic">{ this: CandlestickConfiguration -></format>
<list type="none">
<li>
 <a href="#fill_color_boxes"><format style="bold" color="DarkGray">fillColor</format></a>: Color = Color.GREEN
</li>
<li>
 <a href="#alpha_boxes"><format style="bold" color="DarkGray">alpha</format></a>:
<include from = "properties.topic" element-id="signature-of-nonpos-alpha-setting"></include>= 0.6
</li>
<li>
<a href="#borderline">
            <format style="bold" color="DarkGray">borderLine</format>
        </a>
        <format style="italic">{ this: CandlestickBorderLineContext -></format>
        <list type="none">
            <li>
                <a href="#borderline-color">
                    <format style="bold" color="DarkGray">color</format>
                </a>: Color = Color.GREEN
            </li>
            <li>
                <a href="#borderline-type">
                    <format style="bold" color="DarkGray">type</format>
                </a>: LineType = LineType.SOLID
            </li>
            <li>
                <a href="#borderline-width">
                    <format style="bold" color="DarkGray">width</format>
                </a>: Double = 1.0
            </li>
        </list>
        <format style="italic"> }</format>
</li>
</list>
<format style="italic">}</format>
</li>
<li>
<a href="#inc_dec">decrease </a><format style="italic">{ this: CandlestickConfiguration -></format>
<list type="none">
<li>
 <a href="#fill_color_boxes"><format style="bold" color="DarkGray">fillColor</format></a>: Color = Color.RED
</li>
<li>
 <a href="#alpha_boxes"><format style="bold" color="DarkGray">alpha</format></a>:
<include from = "properties.topic" element-id="signature-of-nonpos-alpha-setting"></include> = 0.6
</li>
<li>
<a href="#borderline">
            <format style="bold" color="DarkGray">borderLine</format>
        </a>
        <format style="italic">{ this: CandlestickBorderLineContext -></format>
        <list type="none">
            <li>
                <a href="#borderline-color">
                    <format style="bold" color="DarkGray">color</format>
                </a>: Color = Color.RED
            </li>
            <li>
                <a href="#borderline-type">
                    <format style="bold" color="DarkGray">type</format>
                </a>: LineType = LineType.SOLID
            </li>
            <li>
                <a href="#borderline-width">
                    <format style="bold" color="DarkGray">width</format>
                </a>: Double = 1.0
            </li>
        </list>
        <format style="italic"> }</format>
</li>
</list>
<format style="italic">}</format>
</li>
</list>
<format style="italic">}</format>
</tldr>

The `candlestick` adds a candlestick layer
function optionally creates a new context with [boxes](https://kotlin.github.io/kandy/boxes-api.html) aesthetics and new dataset
which contains the "candlestick" statistic calculated on sample of four numeric variables
([open](#open), [high](#high), [low](#low), [close](#close))
in several groups defined by [x](#x).

Candlestick is a type of chart commonly used in financial markets to represent the price movement 
of an asset, such as stocks or cryptocurrencies. 
It consists of individual "candles" that display the 
<emphasis>opening</emphasis>, <emphasis>closing</emphasis>, 
<emphasis>high</emphasis>, and <emphasis>low</emphasis> prices for a specific time period. 
Each candle has a rectangular body, representing the opening and closing prices, 
and thin lines, called wicks or shadows, indicating the highest and lowest prices during that time frame.

## Arguments

### X {id=xtype}

<p>Type of x categories</p>

### x

<p><format style="superscript" color="Red">Required</format> </p>
<p> 
<format style="superscript" color="#E8488B">Iterable&lt;X></format> 
<format style="superscript" color="#E8488B">Column&lt;X></format> 
<format style="superscript" color="#E8488B">String</format> 
</p>
<p> The <code>x</code> argument is used to provide the
x-categories of candle. 
</p>

### open

<p><format style="superscript" color="Red">Required</format> </p>
<p> 
<format style="superscript" color="#E8488B">Iterable</format> 
<format style="superscript" color="#E8488B">Column</format> 
<format style="superscript" color="#E8488B">String</format> 
</p>
<p> The <code>open</code> argument is used to provide the
<format style="italic">opening</format> value of candle. 
</p>

### high

<p><format style="superscript" color="Red">Required</format> </p>
<p> 
<format style="superscript" color="#E8488B">Iterable</format> 
<format style="superscript" color="#E8488B">Column</format> 
<format style="superscript" color="#E8488B">String</format> 
</p>
<p> The <code>high</code> argument is used to provide the
<format style="italic">high </format> (maximum) value of candle. 
</p>

### low

<p><format style="superscript" color="Red">Required</format> </p>
<p> 
<format style="superscript" color="#E8488B">Iterable</format> 
<format style="superscript" color="#E8488B">Column</format> 
<format style="superscript" color="#E8488B">String</format> 
</p>
<p> The <code>low</code> argument is used to provide the
<format style="italic">low</format> (minimum) value of candle. 
</p>

### close

<p><format style="superscript" color="Red">Required</format> </p>
<p> 
<format style="superscript" color="#E8488B">Iterable</format> 
<format style="superscript" color="#E8488B">Column</format> 
<format style="superscript" color="#E8488B">String</format> 
</p>
<p> The <code>close</code> argument is used to provide the
<format style="italic">closing</format> value of candle. 
</p>

## Statistic properties

In this context, there are the following statistical properties that can be used
to create mappings, customize tooltips, etc.

### Stat.x

<p><format style="superscript" color="#E8488B">Column&lt;X></format></p>
<p>The <code>Stat.x</code> contains candle x category. 
</p>

### Stat.open

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.open</code> contains candle open. Equals to input <a href="#open">open</a>. 
</p>

### Stat.close

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.close</code> contains candle close. Equals to input <a href="#close">close</a>. 
</p>

### Stat.min

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.min</code> contains candle minimum. Equals to input <a href="#low">low</a>. 
</p>

### Stat.lower

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.lower</code> contains lower candle edges (i.e. 
smaller of <a href="#open">open</a>/<a href="#close">close</a>).
</p>

### Stat.upper

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.upper</code> contains upper candle edges (i.e. 
greater of <a href="#open">open</a>/<a href="#close">close</a>).
</p>

### Stat.max

<p><format style="superscript" color="#E8488B">Column&lt;Double></format></p>
<p>The <code>Stat.min</code> contains candle maximum. Equals to input <a href="#high">high</a>. 
</p>

### Stat.isIncreased

<p><format style="superscript" color="#E8488B">Column&lt;Boolean></format></p>
<p>The <code>Stat.isIncreased</code> contains candle increase indicator: 
<code>true</code> if <a href="#close">close</a> value is greater than <a href="#open">open</a> value.
</p>

## Boxes aesthetic properties

See [boxes](https://kotlin.github.io/kandy/boxes-api.html).

### x {id=x_boxes}

<include from="properties.topic" element-id="x-property-default"/>

### lower {id=lower_boxes}

<include from="properties.topic" element-id="req-position-aes-desc-default"/>

<include from="properties.topic" element-id="lower-property-desc"/>

### middle {id=middle_boxes}

<include from="properties.topic" element-id="req-position-aes-desc-default"/>

<include from="properties.topic" element-id="middle-property-desc"/>

### upper {id=upper_boxes}

<include from="properties.topic" element-id="req-position-aes-desc-default"/>

<include from="properties.topic" element-id="upper-property-desc"/>

### yMin {id=ymin_boxes}

<include from="properties.topic" element-id="yMin-property-default"/>

### yMax {id=ymax_boxes}

<include from="properties.topic" element-id="yMax-property-default"/>

### alpha {id=alpha_boxes}

<include from="properties.topic" element-id="alpha-property"/>

### width {id=width_boxes}

<include from="properties.topic" element-id="width-property"/>

### fillColor {id=fill_color_boxes}

<include from="properties.topic" element-id="fillColor-property"/>

### fatten {id=fatten_boxes}

<include from="properties.topic" element-id="fatten-property"/>

### borderLine

<include from="properties.topic" element-id="borderLine-property"/>

## increase / decrease {id = inc_dec}

In addition to the usual customization using mappings on statistics, `candlestick`
has an additional simplified API that allows you to quickly customize 
a candle depending on its character (is it increase or decrease), using `increase` and `decrease` contexts.
In them, you can assign a value to several aesthetics ([fillColor](#fill_color_boxes), [alpha](#alpha_boxes),
[borderLine](#borderline)).

## Free Scales

### y {id=y_boxes}

<include from="properties.topic" element-id="yFree-property"/>
