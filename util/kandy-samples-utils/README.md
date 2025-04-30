# Samples Utils (Kandy & DataFrame)

This module contains special utilities for creating samples (images, tables etc.)
for Writerside documentation.

The main class of this module `SampleHelper` is a base class for test classes.
It allows creating sample Kandy and DataFrame samples through
test running.

## How to use

1. Write a documentation page in [Kotlin Notebook](https://kotlinlang.org/docs/kotlin-notebook-overview.html)
   (or using [Kotlin Jupyter](https://github.com/Kotlin/kotlin-jupyter)).

2. Run the [nb_to_doc.py](nb_to_doc.py) script on it. It generates two files:
   .kt and .md.
    ```bash
   python nb_to_doc.py doc_notebook.ipynb
   ```
    * a .kt file contains a single test class inheriting from `SampleHelper`. For each cell, there's a
      corresponding test function. Function body copies whole cell code; this code is located between korro dirrectives
      (`//SampleStart` and `//SampleEnd`). Also, if the output of the cell is a Kandy plot or DataFrame table,
      there will be added an extension for the last cell statement (after `//SampleEnd`). Running this test causes
      creating a plot image or HTML table corresponding to this cell output.
      Run this test class to generate all samples.
    * a .md file contains all content from the Notebook.
      For Markdown cells, it copies text without any changes.
      For code cells, there are FUN directives with a name of corresponding test
      (`!---FUN test_from_notebook_n -->`). After `korro` run there will be code from tests inserted here.
      If a cell has an output, there will be an image or table (which are created through running tests) import added.

3. Place these files in required directories, run tests and `korro`.

4. Run the [update_ws_resources.py](update_ws_resources.py) with 2 arguments — path to the resource folder and path
   to a Writerside shadowed topic with all resources (need to be done to add resources to WS artifact).
   ```bash
   python update_ws_resources.py docs/resources docs/topics/_shadow_resources.md
   ```

5. Require additional Writerside HTML with custom JS.
   See docs/cfg/buildprofiles.xml, docs/cfg/include_in_head.html, docs/cfg/static/custom.js.

6. Don't work on preview! Deploy Writerside locally, using http-server; build WS web archive, 
unzip and run in command in folder:
    ```bash
    http-server
    ```
   or
   ```bash
   python -m http.server  
   ```
