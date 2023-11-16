# Kandy in Kotlin Notebook

<web-summary>
    Discover the seamless integration of Kandy in Kotlin Notebook.
    Follow our guided steps to set up and start creating immersive data visualizations right within your Kotlin notebook.
</web-summary>
<card-summary>
    Embark on a seamless visualization journey with Kandy in Kotlin Notebook.
    This section guides you from installation to crafting your first graph,
    offering a smooth start to your data visualization project.
</card-summary>
<link-summary>
    Kickstart Your Data Visualization Journey with Kandy in Kotlin Notebook
</link-summary>

<video src="https://www.youtube.com/watch?v=2PLYlDJPelQ"/>

[Kotlin Notebook](https://plugins.jetbrains.com/plugin/16340-kotlin-notebook)
represent interactive notebooks equipped with rich output capabilities,
allowing you to explore and experiment with Kotlin code without the need for additional environment setup.
The Kotlin Notebook plugin facilitates the creation and editing of notebooks directly within IntelliJ IDEA.
This plugin not only encapsulates the various functionalities available in regular Kotlin files in the IDE but also
incorporates additional extensions exclusive to Kotlin notebooks. These features include advanced syntax highlighting,
code insertion hints, checks, and the utilization of search and refactoring functions, all aiding in enhancing your
Kotlin coding efficiency.

The Kotlin Notebook plugin infuses IntelliJ IDEA with interactive development capacities, complementing the robust
language support Kotlin offers within the IDE, paired with the versatile visualization potentials browsers provide.

> Check out the [blog post](https://blog.jetbrains.com/kotlin/2023/07/introducing-kotlin-notebook/) for a quick
> introduction to Kotlin Notebook.

## Install Kotlin Notebook and Use Kandy

1. To start, install [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/)

2. The plugin can be installed through IDEA settings, from the marketplace, or from a local archive file (ZIP or JAR).

<procedure>
<tabs>
<tab title="Install plugin from IDEA settings">

* Open IDEA and press <shortcut key="$Settings"/> to open the IDE settings
* Select <ui-path>Plugins</ui-path>
* Navigate to the <ui-path>Marketplace</ui-path> tab
  ![Marketplace Tab](idea_plugins.png)
* In the search bar, type `Kotlin Notebook`
* Locate the plugin and initiate the installation by clicking the <ui-path>Install</ui-path> button
  ![Install Kotlin Notebook plugin](install_ktn_plugin.png)
* Click <ui-path>Ok</ui-path> to apply the changes and restart your IDE if prompted

</tab>
<tab title="Install plugin from Jetbrains Marketplace">

* Open the plugin page in [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/16340-kotlin-notebook)
* Click on <ui-path>Install to IntelliJ IDEA</ui-path>
  ![Kotlin Notebook on JetBrains Marketplace](jb_marketplace.png) { border-effect="rounded" }
* Restart your IDE if prompted

</tab>
<tab title="Install Plugin from Disk">

* Open the plugin page in [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/16340-kotlin-notebook)
* Go to the <ui-path>Versions</ui-path> tab
* Download the specific version of plugin
  ![Kotlin Notebook Versions on JetBrains Marketplace](jb_marketplace_versions.png) { border-effect="rounded" }
* Open IDEA and press <shortcut key="$Settings"/> to open the IDE settings
* Select <ui-path>Plugins</ui-path>
* On the Plugins page, click on ![The Settings](gearPlain.svg) button and then click on <ui-path>Install Plugin from
  Disk…</ui-path>
  ![Install Plugin from Disk](install_plugin_from_disk.png)
* Restart your IDE if prompted

</tab>
</tabs>
</procedure>

3. Create a New Project in IDEA

<procedure>

* From the main menu, select <ui-path>File | New | Project</ui-path>.
* In the panel on the left, select <ui-path>New Project</ui-path>.
  Select Kotlin language as language for the new project.

![New Project in IDEA](new_project.png)

* Click <ui-path>Create</ui-path> button.

* In the newly opened project, create a new Kotlin Notebook file.
  To do this, press <shortcut key="$NewFile"/> in the project tree or right-click with your mouse.
  Select <ui-path>Kotlin Notebook</ui-path> file.

![Create Kotlin Notebook file](new_ktn_file.png) {width="200"}

</procedure>

4. In the notebook, execute the following cell to add the Kandy library:

```
%use kandy
```

You now have access to the Kandy library within your Kotlin Notebook.

<include from="Getting-Started.md" element-id="plotting-a-simple-example"></include>

<seealso>
<category ref="ext">
<a href="https://plugins.jetbrains.com/plugin/16340-kotlin-notebook">Kotlin Notebook on Marketplace</a>
<a href="https://blog.jetbrains.com/kotlin/2023/07/introducing-kotlin-notebook/">Introducing Kotlin Notebook</a>
<a href="https://github.com/Kotlin/kotlin-jupyter/tree/master/docs">Kotlin Notebook Docs</a>
</category>
</seealso>
