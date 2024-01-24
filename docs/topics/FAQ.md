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


We would welcome any feedback, question, feature request or suggestion related to Kandy you may have on 
[issues in the GitHub repository](https://github.com/Kotlin/kandy/issues).
Also, you can ask any question or discuss anything related to Kandy on 
[#datascience](https://slack-chats.kotlinlang.org/c/datascience?_ga=2.2771440.1927802643.1705492406-2056581211.1698239845&_gl=1*i9jk3b*_ga*MjA1NjU4MTIxMS4xNjk4MjM5ODQ1*_ga_9J976DJZ68*MTcwNTU3MjM0NC42NC4xLjE3MDU1NzIzNjUuMzkuMC4w) Kotlin Slack channel

## Does kandy support Compose Multiplatform?
Currently, kandy has JVM as the only target. Follow the updates in the
[corresponding issue](https://github.com/Kotlin/kandy/issues/27)

## Can I run Kandy on Android?
Yes, but for now it is necessary to use it in combination with 
[Lets-Plot-Skia](https://github.com/JetBrains/lets-plot-skia) for drawing. 
See an [example app](https://github.com/AndreiKingsley/kandy_android_skiko)

## Can I use Kandy plots with Java Swing/JavaFX?
* There is currently no native support. However, you can convert Kandy plot into Lets-Plot specification and use 
[Lets-Plot tools](https://github.com/JetBrains/lets-plot-kotlin/blob/master/USAGE_BATIK_JFX_JS.md) 
 for this purpose. See an [example app](https://github.com/AndreiKingsley/kandy-swing-app-example)
* Also follow the updates in the [corresponding issue](https://github.com/Kotlin/kandy/issues/283)
* You can export Kandy Plot to `BufferedImage` ([see](https://kotlin.github.io/kandy/quick-start-guide.html#export))

## Is there geo tools/geo visualization support?
At the moment, no. Follow the updates in the [corresponding issue](https://github.com/Kotlin/kandy/issues/280)

## Does Kandy support 3D plotting?
At the moment, no. Follow the updates in the [corresponding issue](https://github.com/Kotlin/kandy/issues/209)

## Does Kandy support dynamic plotting / animations?
At the moment, no.

## Is there matplotlib-like (series) plotting?
Because of Lets-Plot restrictions, it’s not supported for now, but you can use 
[series hack](https://kotlin.github.io/kandy/series-hack-guide.html)
