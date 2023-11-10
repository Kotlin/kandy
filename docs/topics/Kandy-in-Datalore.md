# Kandy in Datalore

<web-summary>
    Embark on your journey with the Kandy library in Datalore.
    This section guides you through the initial steps of opening and creating a notebook in Datalore
    and introduces you to crafting simple graphs with Kandy.
</web-summary>
<card-summary>
    Kickstart your data visualization journey in Datalore with Kandy.
    Find a step-by-step guide to setting up a notebook and creating your first graph,
    paving the way for more advanced explorations.
</card-summary>
<link-summary>
    Getting Started with Kandy in Datalore: Your First Notebook and Graph
</link-summary>

[Datalore](https://datalore.jetbrains.com/) is a collaborative data science platform designed to streamline insight
delivery, facilitating more productive collaboration between data science and business teams.
This platform is adept for tasks such as data collection, exploration, machine learning, interactive visualization,
and reporting.

Datalore supports Python, Kotlin, Scala, and R notebooks without the need for preliminary setup,
offering computational resources tailored to your requirements.

## Getting Started with Datalore

1. Open [Datalore](https://datalore.jetbrains.com/).
2. Register if you don't already have an account.
3. On the home page, click on the <ui-path>New Notebook</ui-path> button.

   ![New Notebook button](new_ntbk_datalore.png) {width="200"}

   You will be redirected to the <ui-path>New Notebook</ui-path> page where you can set the notebook's title, select a
   kernel, choose a computation mode, and configure various other settings.

4. Select the <emphasis>Kotlin</emphasis> Kernel and click <ui-path>Create Notebook</ui-path>.

   ![New Notebook page](create_ntbk_datalore.png)

5. In the notebook, execute the following cell to add the Kandy library:

```
%use kandy
```

You now have access to the Kandy library within your Kotlin Notebook in Datalore.

<include from="Getting-Started.md" element-id="plotting-a-simple-example"/>
