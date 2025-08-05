# Formatting

<web-summary>
Hone your skills in precise communication through data visualization with Kandy's Label Format guide.
Discover how to tailor label formats to improve the clarity and accuracy in your data plots.
</web-summary>

<card-summary>
Enhance your data storytelling with Kandy's Label Format guide.
Discover how to format labels for clearer, more precise data representation.
</card-summary>

<link-summary>
Unlock the potential of effective labeling in data visualization with Kandy's Label Format guide.
Tailor your plot labels for maximum clarity and impact.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.LabelFormat-->

> This guide is taken from lets-plot:
> [Formatting](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/formats.md).

Formatting provides the ability to do complex variable substitutions and value formatting.

## Number format

The numeric format strings are used to format common numeric types. The general form of a format specifier is:

```text
[[fill]align][sign][symbol][0][width][,][.precision][type]
```

* *`fill`* — can be any character, defaults to a space if omitted.
  The presence of a fill character is signaled by the `*align*` character following it,
  which must be one of the alignment options.

* *`align`* — the various alignment options are as follows:

    * `>` — forces the field to be right-aligned within the available space (default behavior);
    * `<` — forces the field to be left-aligned within the available space;
    * `^` — forces the field to be centered within the available space;
    * `=` — like `>`, but with any sign and symbol to the left of any padding.

* *`sign`* can be:

    * `-` — nothing for zero or positive and a minus sign for negative (default behavior);
    * `+` — a plus sign for zero or positive and a minus sign for negative;
    * ` ` (space) — a space for zero or positive and a minus sign for negative.

* *`symbol`* can be:

    * `$` — apply currency symbols per the locale definition;
    * `#` — for binary, octal, or hexadecimal notation, prefix by `0b`, `0o`, or `0x`, respectively.

* *`zero`* (`0`) option enables zero-padding; this implicitly sets *fill* to `0` and *align* to `=`.

* *`width`* defines the minimum field width; if not specified, then the width will be determined by the content.

* *`comma`* (`,`) option enables the use of a group separator, such as a comma, for thousands.

* *`precision`* depending on the *`type`*, the *`precision`* either indicates the number of digits that follow the
  decimal point (types `f` and `%`), or the number of significant digits (types ` `, `e`, `g`, `r`, `s` and `p`).
  If the precision is not specified, it defaults to 6 for all types except (none), which defaults to 12.
  Precision is ignored for integer formats (types `b`, `o`, `d`, `x`, `X` and `c`).

* *`type`* determines how the data should be presented:

    * `e` — exponent notation;
    * `f` — fixed point notation;
    * `g` — either decimal or exponent notation, rounded to significant digits;
    * `s` — decimal notation with an SI prefix, rounded to significant digits;
    * `%` — multiply by 100, and then decimal notation with a percent sign;
    * `b` — binary notation, rounded to integer;
    * `o` — octal notation, rounded to integer;
    * `d` — decimal notation, rounded to integer;
    * `x` — hexadecimal notation, using lower-case letters, rounded to integer;
    * `X` — hexadecimal notation, using upper-case letters, rounded to integer;
    * `c` — simple toString.

  The following SI prefixes are supported for `s` type:

    * `y` — yocto, 10⁻²⁴
    * `z` — zepto, 10⁻²¹
    * `a` — atto, 10⁻¹⁸
    * `f` — femto, 10⁻¹⁵
    * `p` — pico, 10⁻¹²
    * `n` — nano, 10⁻⁹
    * `µ` — micro, 10⁻⁶
    * `m` — milli, 10⁻³
    * ` ` (none) - 10⁰
    * `k` — kilo, 10³
    * `M` — mega, 10⁶
    * `G` — giga, 10⁹
    * `T` — tera, 10¹²
    * `P` — peta, 10¹⁵
    * `E` — exa, 10¹⁸
    * `Z` — zetta, 10²¹
    * `Y` — yotta, 10²⁴

### Examples of Number Format

Let's format the number `42`:

```text
08d       -->  "00000042"
_<8d      -->  "______42"
_=8d      -->  "___42___"
_=+8d     -->  "+_____42"
_^11.0%   -->  "____42%____"
_^11,.0%  -->  "__42,200%__"
+08,d     -->  "+0,000,042"
.1f       -->  "42.0"
+.3f      -->  "+42.000"
b         -->  "101010"
#b        -->  "0b101010"
o         -->  "52"
e         -->  "4.200000e+1"
s         -->  "42.0000"
020,s     -->  "000,000,000,042.0000"
020.0%    -->  "0000000000000004200%"
```

Some other examples:

```text
format   number        result
.1f      0.42          "0.4"
.3g      0.4449        "0.445"
,.12g    -4200000      "-4,200,000" 
0,.2f    1234567.449   "1,234,567.45"
+$,.2f   1e4           "+$10,000.00"
```

## String template

The number format can be used in a template to create a string with variable substitution.
The string template contains “replacement fields” surrounded by curly braces `{}`.
Anything not contained in braces is considered literal text, which is copied unchanged to the result string.
If you need to include a brace character in the literal text, it can be escaped by doubling: {{ and }}.
This approach is used in function `tooltips()` to customize the content of tooltips.

## Date and Time Format

Provides formats for date and time values.

The list of supported directives to format date/time values:

* `%a` — weekday as an abbreviated name (Sun, Mon, …, Sat);
* `%A` — weekday as a full name (Sunday, Monday, …, Saturday)
* `%b` — month as an abbreviated name (Jan, Feb, …, Dec);
* `%B` — month as a full name (January, February, …, December);
* `%d` — day of the month as a zero-padded decimal number (01, 02, …, 31);
* `%e` — day of the month as a decimal number (1, 2, …, 31);
* `%j` — day of the year as a zero-padded decimal number (001, 002, …, 366).
* `%m` — month as a zero-padded decimal number (01, 02, …, 12);
* `%w` — weekday as a decimal number, where 0 is Sunday and 6 is Saturday (0, 1, …, 6);
* `%y` — year without century as a zero-padded decimal number (00, 01, …, 99);
* `%Y` — year with century as a decimal number (0001, 0002, …, 2013, 2014, …, 9998, 9999);
* `%H` — hour (24-hour clock) as a zero-padded decimal number (00, 01, …, 23);
* `%I` — hour (12-hour clock) as a zero-padded decimal number (01, 02, …, 12);
* `%l` — hour (12-hour clock) as a decimal number (1, 2, …, 12);
* `%M` — minute as a zero-padded decimal number (00, 01, …, 59);
* `%p` — "AM" or "PM" according to the given time value;
* `%P` — like %p but in lowercase: "am" or "pm";
* `%S` — second as a zero-padded decimal number (00, 01, …, 59).

### Examples of Date and Time Format

Let's apply the format string to the date `Aug 6, 2019` and the time `4:46:35`:

```text
%a  -->  "Tue"
%A  -->  "Tuesday"
%b  -->  "Aug"
%B  -->  "August"
%d  -->  "06"
%e  -->  "6"
%j  -->  "218"
%m  -->  "08"
%w  -->  "2" 
%y  -->  "19"
%Y  -->  "2019"
%H  -->  "04"
%I  -->  "04"
%l  -->  "4"
%M  -->  "46"
%P  -->  "am"
%p  -->  "AM"
%S  -->  "35"


%Y-%m-%dT%H:%M:%S      -->  "2019-08-06T04:46:35"
%m/%d/%Y               -->  "08/06/2019"
%m-%d-%Y %H:%M         -->  "08-06-2019 04:46"
%d.%m.%y               -->  "06.08.19"
%A, %b %e, %Y          -->  "Tuesday, Aug 6, 2019"
%b %d, %l:%M %p        -->  "Aug 06, 4:46 AM"
%B %Y                  -->  "August 2019"
%b %e, %Y              -->  "Aug 6, 2019"
%a, %e %b %Y %H:%M:%S  -->  "Tue, 6 Aug 2019 04:46:35"
%B %e %Y %H:%M %p      -->  "August 6 2019 04:46 AM"
```

## Label Format

<!---FUN guideLabelReadData-->

```kotlin
// The US Unemployment Rates 2000-2016
val economics = DataFrame.readCSV(
    "https://vincentarelbundock.github.io/Rdatasets/csv/ggplot2/economics.csv",
    parserOptions = ParserOptions(Locale.ENGLISH)
).filter { "date"<LocalDate>() >= LocalDate(2001, 1, 1) }

economics.head()
```

<!---END-->

| rownames | date       | pce    | pop    | psavert | uempmed | unemploy |
|:---------|:-----------|:-------|:-------|:--------|:--------|:---------|
| 403      | 2001-01-01 | 6977   | 283920 | 4.8     | 5.8     | 6023     |
| 404      | 2001-02-01 | 6995.8 | 284137 | 4.9     | 6.1     | 6089     |
| 405      | 2001-03-01 | 6987.9 | 284350 | 5.3     | 6.6     | 6141     |
| 406      | 2001-04-01 | 7001.2 | 284581 | 5       | 5.9     | 6271     |
| 407      | 2001-05-01 | 7047.1 | 284810 | 4.5     | 6.3     | 6226     |

### Default plot (no formatting)

<!---FUN guideLabelDefaultPlot-->

```kotlin
economics.plot {
    line {
        x(date)
        y(uempmed) { axis.name = "unemployment rate" }
    }
    layout.size = 900 to 400
}
```

<!---END-->

![Plot without Formatting](guideLabelDefaultPlot.svg)

### Apply formatting to X and Y axis labels

Use the `format` parameter in `breaks()` for axis.


> Text in tooltips is now also formatted.
>
{style="note"}

<!---FUN guideLabelFormattingXAndY-->

```kotlin
economics.plot {
    line {
        x(date) { axis.breaks(format = "%b %Y") }
        y(uempmed) {
            axis {
                name = "unemployment rate"
                breaks(format = "{} %")
            }
        }
    }
    layout.size = 900 to 400
}
```

<!---END-->

![Formatting for X and Y axis](guideLabelFormattingXAndY.svg)

### Format axis labels for breaks specified manually

<!---FUN guideLabelFormattingBreaksManually-->

```kotlin
economics.plot {
    line {
        x(date) { axis.breaks(format = "%b %Y") }
        y(uempmed) {
            axis {
                name = "unemployment rate"
                breaks(listOf(5.0, 15.0, 25.0), format = "{} %")
            }
        }
    }
    layout.size = 900 to 400
}
```

<!---END-->

![Manually formatting breaks](guideLabelFormattingBreaksManually.svg)

### Configure tooltip's text and location

<!---FUN guideLabelConfigureTooltips-->

```kotlin
economics.plot {
    line {
        x(date) { axis.breaks(format = "%b %Y") }
        y(uempmed) {
            axis {
                name = "unemployment rate"
                breaks(format = "{} %")
            }
        }
        tooltips(anchor = Anchor.TOP_CENTER, minWidth = 170.0) {
            line("Unemployment rate:|^y")
        }
    }
    layout.size = 900 to 400
}
```

<!---END-->

![Configured Tooltips](guideLabelConfigureTooltips.svg)

### Format value shown in tooltip

<!---FUN guideLabelFormatValueTooltip-->

```kotlin
economics.plot {
    line {
        x(date)
        y(uempmed) {
            axis {
                name = "unemployment rate"
            }
        }
        tooltips(formats = mapOf(date to "%B %Y"), anchor = Anchor.TOP_CENTER, minWidth = 170.0) {
            line("@uempmed % in @date")
        }
    }
    layout.size = 900 to 400
}
```

<!---END-->

![Format value in tooltip](guideLabelFormatValueTooltip.svg)

### Adding to the unemployment rate mean

<!---FUN guideLabelMeanMarkLine-->

```kotlin
val unemploymentMean = economics[uempmed].mean()


economics.plot {
    x(date)
    y(uempmed)
    line {
        tooltips(formats = mapOf(date to "%B %Y"), anchor = Anchor.TOP_CENTER, minWidth = 170.0) {
            line("Unemployment rate:|^y %")
        }
    }
    hLine {
        yIntercept.constant(unemploymentMean)
        color = Color.RED
        type = LineType.DASHED
        tooltips(enable = false)
    }
    text {
        label = "${String.format("%.2f", unemploymentMean)} %"
        x.constant(LocalDate(2001, 1, 1).atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds())
        y.constant(unemploymentMean + 0.5)
    }
    y.axis.name = "unemployment rate"
    layout {
        title = "The US Unemployment Rates 2000-2016."
        size = 900 to 400
    }
}
```

<!---END-->

![Mean Mark Line](guideLabelMeanMarkLine.svg)

## Tooltip Customization

You can format text in tooltips, see: [Tooltips](Tooltips-Guide.md).

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/label_format.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/ennUCoZSC0MWjPqpMwXYBB" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
