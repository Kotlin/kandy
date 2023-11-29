# Jitter Points

<web-summary>
Jitter Points in Kandy: A dynamic way to represent overlapping data points in Kotlin.
This guide demonstrates how to use jittering to enhance clarity and aesthetics in data visualizations.
</web-summary>

<card-summary>
Kandy Jitter Points: Discover how to effectively display dense,
overlapping data points in Kotlin projects, bringing clarity to complex datasets.
</card-summary>

<link-summary>
Unveil intricacies in dense datasets with Jitter Points in Kandy,
a Kotlin-based solution for enhancing data visualization clarity and detail.
</link-summary>

<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.guides.JitterPoints-->

```kotlin
val mpgDf = DataFrame.readCSV("https://raw.githubusercontent.com/JetBrains/lets-plot-kotlin/master/docs/examples/data/mpg.csv")
mpgDf.head()
```

<table style="header-row">
<tr>
   <td><b>untitled</b></td>
   <td><b>manufacturer</b></td>
   <td><b>model</b></td>
   <td><b>displ</b></td>
   <td><b>year</b></td>
   <td><b>cyl</b></td>
   <td><b>trans</b></td>
   <td><b>drv</b></td>
   <td><b>cty</b></td>
   <td><b>hwy</b></td>
   <td><b>fl</b></td>
   <td><b>class</b></td>
</tr>
<tr>
   <td>1</td>
   <td>audi</td>
   <td>a4</td>
   <td>18,0</td>
   <td>1999</td>
   <td>4</td>
   <td>auto(l5)</td>
   <td>f</td>
   <td>18</td>
   <td>29</td>
   <td>p</td>
   <td>compact</td>
</tr>
<tr>
   <td>2</td>
   <td>audi</td>
   <td>a4</td>
   <td>18,0</td>
   <td>1999</td>
   <td>4</td>
   <td>manual(m5)</td>
   <td>f</td>
   <td>21</td>
   <td>29</td>
   <td>p</td>
   <td>compact</td>
</tr>
<tr>
   <td>3</td>
   <td>audi</td>
   <td>a4</td>
   <td>2,0</td>
   <td>2008</td>
   <td>4</td>
   <td>manual(m6)</td>
   <td>f</td>
   <td>20</td>
   <td>31</td>
   <td>p</td>
   <td>compact</td>
</tr>
<tr>
   <td>4</td>
   <td>audi</td>
   <td>a4</td>
   <td>2,0</td>
   <td>2008</td>
   <td>4</td>
   <td>auto(av)</td>
   <td>f</td>
   <td>21</td>
   <td>30</td>
   <td>p</td>
   <td>compact</td>
</tr>
<tr>
   <td>5</td>
   <td>audi</td>
   <td>a4</td>
   <td>28,0</td>
   <td>1999</td>
   <td>6</td>
   <td>auto(l5)</td>
   <td>f</td>
   <td>16</td>
   <td>26</td>
   <td>p</td>
   <td>compact</td>
</tr>
</table>

<compare type="left-right" first-title="Simple Points" second-title="Jitter Points">
<!---FUN guideJitterPointsSimplePoints-->

```kotlin
mpgDf.plot {
    points {
        x(drv)
        y(hwy)
        color(drv)
    }
}
```

<!---END-->

<!---FUN guideJitterPointsJitterPoints-->

```kotlin
mpgDf.plot {
    points {
        x(drv)
        y(hwy)
        color(drv)
        position = Position.jitter()
    }
}
```

<!---END-->
</compare>

<list columns="2">
<li><img src="guideJitterPointsSimplePoints.svg" alt="Simple Points"/></li>
<li><img src="guideJitterPointsJitterPoints.svg" alt="Jitter Points"/></li>
</list>

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/guides/jitter_points.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/ffObHscoZ3iq8CDD8cMngG" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
