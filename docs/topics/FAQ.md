# FAQ

<web-summary>
    Answers to the most frequently asked questions about Kandy
</web-summary>
<card-summary>
    Find answers to the most frequently asked questions about Kandy
</card-summary>
<link-summary>
    Answers to the most frequently asked questions about Kandy
</link-summary>


We’re keen to receive any Kandy-related feedback, questions, feature requests, or suggestions you may have. 
You can submit these by using the [Issues](https://github.com/Kotlin/kandy/issues) section of 
[Kandy GitHub repository](https://github.com/Kotlin/kandy). 
You can also ask your questions or start a discussion on anything related to Kandy 
on the [#datascience](https://slack-chats.kotlinlang.org/c/datascience?_ga=2.2771440.1927802643.1705492406-2056581211.1698239845&_gl=1*i9jk3b*_ga*MjA1NjU4MTIxMS4xNjk4MjM5ODQ1*_ga_9J976DJZ68*MTcwNTU3MjM0NC42NC4xLjE3MDU1NzIzNjUuMzkuMC4w) 
Kotlin Slack channel.


## Does Kandy support Compose Multiplatform?
 * We’re looking into it, but currently Kandy’s only target is JVM. 
If you need Compose Multiplatform support, please follow the
[corresponding issue](https://github.com/Kotlin/kandy/issues/27) for updates and cast your vote there as well. 
We’d also appreciate it if you could describe your case and current toolset.


## Can I run Kandy on Android?
 * Yes, you can create Kandy plots on Android. 
You can then export them to SVG or HTML, 
or use the plots in combination with [Lets-Plot-Skia](https://github.com/JetBrains/lets-plot-skia) to render them. 
See an [example app](https://github.com/AndreiKingsley/kandy_android_skiko).

## Can I use Kandy plots with Java Swing/JavaFX?
 * While there’s currently no native support for Java Swing/JavaFX, you can build a Kandy plot, 
convert it to the Lets-Plot specification, and then use Lets-Plot tools for these purposes
([Let's Plot Java Swing Frontend / JavaFX Frontend](https://github.com/JetBrains/lets-plot-kotlin/blob/master/USAGE_BATIK_JFX_JS.md), 
or [Let’s-Plot Skia Frontend)](https://github.com/JetBrains/lets-plot-skia))
See an [example app](https://github.com/AndreiKingsley/kandy-swing-app-example).
 * Please follow the [corresponding issue](https://github.com/Kotlin/kandy/issues/283) for updates 
and cast your vote there as well.
 * You can export Kandy Plot to `BufferedImage`, SVG, PNG, JPG, or HTML. 
See an [example](https://kotlin.github.io/kandy/quick-start-guide.html#export).


## Is there geo tools/geo visualization support?
 * At the moment, no. We’re currently looking into adding support for 
[GeoTools](https://www.geotools.org/) using 
[lets-plot-geo](https://github.com/JetBrains/lets-plot-kotlin/blob/master/docs/geotools.md). 
If you need this support, please follow the [corresponding issue](https://github.com/Kotlin/kandy/issues/280) 
for updates and cast your vote there as well. 
It would also be appreciated if you could describe your case and current toolset.


## Does Kandy support 3D plotting?
 * At the moment, no. This is a limitation of the Lets-Plot engine, 
which specializes in 2D charts only. If you need this support, 
please follow the [corresponding issue](https://github.com/Kotlin/kandy/issues/209) 
for updates and cast your vote there as well.

## Does Kandy support dynamic plotting / animations?
 * Kandy supports tooltips: See the [documentation](https://kotlin.github.io/kandy/tooltips-guide.html).
 * At the moment, the Lets-Plot version of Kandy doesn’t support any kind of animations
or dynamic data plotting. This is a limitation of the Lets-Plot library,
which specializes in static charts. If you need this support,
please create an issue describing your use case 
 * The Apache Echarts (a JavaScript plotting library) engine for Kandy 
(experimental; still in development) supports different kinds of animations and can work with dynamic data.


## My use case involves creating several layers with multiple series of data, and I can’t figure out how to create a legend for them (for their colors, for example). Is it possible?

 * Due to the limitations of Lets-Plot, you can’t create a legend for a color or any other aesthetic 
parameter without mapping on this parameter. 
That means you can’t create several layers and distinguish them using legend. 
We’re currently working on support for this approach (which we call a “series” approach).
For the time being, you can use a [series hack](https://kotlin.github.io/kandy/series-hack-guide.html) 
solution as a workaround. 
 * The Apache Echarts engine for Kandy (experimental; still in development) supports this functionality.

## My use case involves creating several bars/areas/other layers that are overlapped. Is there a way to adjust their position?
 * Lets-Plot does not allow for any customization between different layers, 
so at the moment the only solution is to use a [series hack](https://kotlin.github.io/kandy/series-hack-guide.html) 
workaround.
 * Kandy with the Apache Echarts engine for Kandy (experimental; still in development) 
supports an API with flexible position adjustment for data series.
