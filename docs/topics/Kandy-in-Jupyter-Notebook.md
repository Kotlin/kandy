# Kandy in Jupyter Notebook

<web-summary>
    Embark on your analytical journey with the Kandy library in the Jupyter Notebook.
    In this section, find a comprehensive guide to installing the Kotlin kernel,
    creating a Kotlin notebook, importing the Kandy library, and constructing your first simple example.
</web-summary>
<card-summary>
    Begin your data visualization venture with Kandy in the Jupyter Notebook.
    This segment equips you with a detailed roadmap for installing the Kotlin kernel
    and crafting your initial visualization using Kandy.
</card-summary>
<link-summary>
    Step-by-Step Guide to Unleashing Kandy's Potential in Jupyter Notebook
</link-summary>

[Jupyter](https://jupyter.org/) is a renowned open-source project offering Jupyter Notebook for interactive computations
across various programming languages.
Jupyter Notebook facilitates the creation and sharing of documents containing code, equations, visualizations,
and narrative text.
It has become a staple tool amongst data scientists, researchers,
and programmers for data visualization and analytical computations.
Notably, the Jupyter project supports the Kotlin language through the Kotlin Kernel.

## Getting Started with Jupyter Notebook and Kotlin

1. Jupyter Notebook and the Kotlin Kernel can be installed using either the conda package manager or pip.
   Here, we will outline both approaches:

<procedure>
<tabs>
<tab title="Using Conda">

* Jupyter Notebook is included in the Anaconda distribution.
  To install Anaconda, download the installer from the [official site](https://www.anaconda.com/download/)
  and follow the installation instructions.
* After completing the above step, install the Kotlin Kernel by executing the following command in the terminal:

```Bash
conda install -c jetbrains kotlin-jupyter-kernel
```

</tab>
<tab title="Using Pip">

* Pip is included with Python. Hence, install Python using a method that suits you,
  preferably by downloading the installer from the [official site](https://www.python.org/downloads/).
* Next, run the following command in the terminal to install Jupyter Notebook:

```Bash
pip install notebook
```

* Additionally, install the Kotlin Kernel by executing:

```Bash
pip install kotlin-jupyter-kernel
```

</tab>
</tabs>
</procedure>

You can find instructions for installing, uninstalling,
and updating the Kotlin Kernel in
the [Kotlin Notebook documentation](https://github.com/Kotlin/kotlin-jupyter/tree/master/docs#conda).

2. After installing both the Jupyter Notebook and Kotlin Kernel,
   launch Jupyter Notebook by entering one of the following commands in the terminal:

```Bash
jupyter notebook
```

or

```Bash
jupyter-notebook
```

3. The Jupyter Notebook will open in your browser.
   Create a new notebook in the current folder by clicking the <ui-path>New</ui-path> button located in the upper right
   corner and selecting <ui-path>Kotlin</ui-path> from the dropdown menu.

![Create a new notebook with Kotlin](create_ktn_jupyter.png) {width="250" border-effect="rounded"}

4. In the notebook, execute the following cell to add the Kandy library:

```
%use kandy
```

You now have access to the Kandy library within your Kotlin Notebook in Jupyter.

<include from="Getting-Started.md" element-id="plotting-a-simple-example"/>