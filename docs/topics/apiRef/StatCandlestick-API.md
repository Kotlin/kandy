# statCandlestick

<tldr>
<p><format style="bold" color="GoldenRod">
statCandlestick&lt;<a href="#xtype"><format color="Blue">X</format></a>></format>(
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
<format style="italic">{ this: StatCandlestickContext -></format></p>

<format style="italic">}</format>
</tldr>

The `statCandlestick` function creates a new plotting context with new dataset
which contains the "candlestick" statistic calculated on sample of four numeric variables 
([open](#open), [high](#high), [low](#low), [close](#close))
in several groups defined by [x](#x).
This statistic is used to plot financial data, showing the increase or decrease in value.

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
