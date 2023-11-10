# Getting Started

<web-summary>
    Embark on your Kandy journey with an easy guide to creating compelling visualizations.
    Start by crafting your very first plot and explore further with more intricate visual scenarios.
</web-summary>
<card-summary>
    Dive into Kandy: Your guide to crafting captivating visualizations from your first plot to advanced graphs.
</card-summary>
<link-summary>
    Kandy's Introduction and Your First Graph Creation.
</link-summary>
<show-structure for="Getting Started" depth="0"/>

You can utilize Kandy both in Kotlin projects and in interactive editors.
For detailed instructions, refer to the respective pages:

* [![Kotlin Notebook logo](ktn_plugin_icon.svg){style="inline" width="16"} Kotlin Notebook](Kandy-in-Kotlin-Notebook.md)
* [![Datalore logo](datalore_icon.svg){style="inline" width="18"} Datalore](Kandy-in-Datalore.md)
* [![Jupyter Notebook logo](jupyter_icon.png){style="inline" width="20"} Jupyter Notebook](Kandy-in-Jupyter-Notebook.md)
* [![Gradle logo](gradle_icon.svg){style="inline" width="20"} Gradle Configuration](Kandy-with-Gradle.md)

## Getting Kandy

<note>
    With Kotlin Notebook, you can enjoy an unparalleled experience in data analysis and visualization.
    While this platform offers a premier user experience,
    rest assured that Kandy maintains a high standard of functionality and support across all other editors as well.
</note>

<tabs>
    <tab title="Kotlin Notebook">
        <procedure title="How to Install Kotlin Notebook?" id="procedure-install-ktn">
            <p>You can find detailed instructions <a href="Kandy-in-Kotlin-Notebook.md">here</a>.</p>
            <step>Install <a href="https://www.jetbrains.com/idea/download">IntelliJ IDEA Ultimate</a> if you don't already have it.</step>
            <step>Open your IDE and press <shortcut key="$Settings"/> to open the IDE settings.</step>
            <step>Select <ui-path>Plugins</ui-path> from the menu and install the <a href="https://plugins.jetbrains.com/plugin/16340-kotlin-notebook">Kotlin Notebook plugin</a>.</step>
            <step>Click <ui-path>Ok</ui-path> to apply the changes and restart your IDE if prompted.</step>
            <step>Create a new Kotlin Notebook file.</step>
            <step>Run this cell: <code-block>%use kandy</code-block></step>
            <p>Congratulations, you now have access to the Kandy library in Kotlin Notebook.</p>
        </procedure>
    </tab>
    <tab title="Datalore">
        <procedure title="How to Use Datalore?" id="procedure-use-datalore">
            <p>You can find detailed instructions <a href="Kandy-in-Datalore.md">here</a>.</p>
            <step>Open <a href="https://datalore.jetbrains.com/">Datalore</a>.</step>
            <step>Register if you don't have an account.</step>
            <step>Create a new notebook.</step>
            <step>Select the Kotlin Kernel.</step>
            <step>Run this cell: <code-block>%use kandy</code-block></step>
            <p>Congratulations, you now have access to the Kandy library in Datalore.</p>
        </procedure>
    </tab>
    <tab title="Jupyter Notebook">
        <procedure title="How to Install Jupyter with Kotlin?" id="procedure-install-jupyter">
            <p>You can find detailed instructions <a href="Kandy-in-Jupyter-Notebook.md">here</a>.</p>
            <step>Install <a href="https://jupyter.org/install">Jupyter</a>.</step>
            <step>Install the <a href="https://github.com/Kotlin/kotlin-jupyter/tree/master/docs#conda">Kotlin Kernel</a> using one of the following methods:
                <compare first-title="pip" second-title="conda" type="top-bottom">
                    <code-block>
                        pip install kotlin-jupyter-kernel
                    </code-block>
                    <code-block>
                        conda install -c jetbrains kotlin-jupyter-kernel
                    </code-block>
                </compare>
            </step>
            <step>Run Jupyter by executing the following command in the terminal: <code-block>jupyter notebook</code-block></step>
            <step>Create a new notebook by selecting the Kotlin kernel.</step>
            <step>Run this cell in the notebook: <code-block>%use kandy</code-block></step>
            <p>Congratulations, you now have access to the Kandy library in Jupyter Notebook.</p>
        </procedure>
    </tab>
        <tab title="Gradle">
        <procedure title="Gradle Configuration" id="procedure-gradle-conf">
            <p>You can find detailed instructions <a href="Kandy-with-Gradle.md">here</a>.</p>
            <step>Create a JVM project with Kotlin in your IDE.</step>
            <step>Add the Kandy dependency to your <path>build.gradle.kts</path> file:
                <code-block lang="kotlin">
                    dependencies {
                        implementation("org.jetbrains.kotlinx:kandy-lets-plot:%latest_version%"))
                    }
                </code-block>
            </step>
            <p>Congratulations, you now have access to the Kandy library in your Kotlin project.</p>
        </procedure>
    </tab>
</tabs>

## Plotting a Simple Example

> In interactive notebooks, the [Kotlin DataFrame library](https://kotlin.github.io/dataframe/overview.html) employs an
> on-the-fly generation mechanism for extension properties that correspond to the columns of a data frame.
> In IntelliJ IDEA, there is a Gradle plugin available for generating properties based on CSV or JSON files.
> In cases where this mechanism is not utilized,
> you can still access the columns by passing the column names as string variables.

Let's create data that will be used to construct the plot.
This data will represent the average annual temperatures in various cities.
When working in interactive notebooks,
it is advisable to divide the data creation and plot construction into two separate cells.
This approach ensures that extension properties are generated for our columns in the DataFrame,
allowing us to reference them easily.

First, create a DataFrame containing data on the average temperatures in different cities as follows:

```kotlin
// Create a DataFrame with data on average temperatures in various cities
val averageTemperature = dataFrameOf(
    "city" to listOf("New York", "London", "Berlin", "Erevan", "Tokyo"),
    "average temperature" to listOf(12.5, 11.0, 9.6, 11.5, 16.0)
)
```

Next, construct a simple plot using the data from the DataFrame:

```kotlin
// Construct a plot using the data from the DataFrame
averageTemperature.plot {
    // Add bars to the plot
    // Each bar represents the average temperature in a city
    bars {
        x(city) // Set the cities' data on the X-axis
        y(`average temperature`) { // Set the temperatures' data on the Y-axis
            axis.name = "Average Temperature (°C)" // Assign a name to the Y-axis
        }
    }
    // Set the title of the plot
    layout.title = "Kandy Getting Started Example"
}
```

![Kandy Getting Started Example](getting_sample.svg){ border-effect="rounded" }
