# withData

<tldr>
<p><format style="bold" color="GoldenRod">
withData&lt;<a href="#t"><format color="Blue">T</format></a>></format>(
<a href="#dataset"><format style="bold" color="CadetBlue">dataset</format></a>:
<emphasis>DataFrame&lt;T> | Map&lt;String, List&lt;*>></emphasis>) <format style="italic">{ this: DataFrameScope&lt;T> -></format></p>

<format style="italic">}</format>
</tldr>

The `withData` function creates a new plotting context with a new provided [dataset](#dataset).
All layers created in this context use this dataset.
If `DataFrame` is provided as a dataset, you can access its columns in this context.

## Arguments

### T

<p>Type of DataFrame</p>

### dataset

 <p>
            <format style="superscript" color="Red">Required</format>
        </p>
        <p>
            <format style="superscript" color="#E8488B">DataFrame&lt;T></format>
            <format style="superscript" color="#E8488B">Map&lt;String, List&lt;*>></format>
</p>

 <p>
New dataset used for layers created in a new context.
</p>