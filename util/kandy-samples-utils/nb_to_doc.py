import os
import sys
from typing import List, Tuple, Optional, Any

import nbformat
from nbformat.notebooknode import NotebookNode


def extract_content(notebook_path: str) -> List[Tuple[NotebookNode, Optional[str], Optional[str]]]:
    """
    Extracts markdown and code cells from a Jupyter notebook while preserving order, including the title cell.

    Args:
        notebook_path: Path to the Jupyter notebook file

    Returns:
        List of tuples containing (cell, output_type, output_content)
    """
    try:
        with open(notebook_path, "r", encoding="utf-8") as f:
            nb_data = nbformat.read(f, as_version=4)
    except FileNotFoundError:
        print(f"Error: Notebook file '{notebook_path}' not found.")
        sys.exit(1)
    except Exception as e:
        print(f"Error reading notebook: {str(e)}")
        sys.exit(1)

    cells = []
    for cell in nb_data.cells:
        output_type = None
        output_content = None

        if cell.cell_type == "code" and hasattr(cell, 'outputs') and cell.outputs:
            output_type, output_content = _extract_cell_output(cell.outputs[0])

        cells.append((cell, output_type, output_content))
    return cells


def _extract_cell_output(output: Any) -> Tuple[Optional[str], Optional[str]]:
    """
    Extract the type and content of a cell output.

    Args:
        output: The output object from a notebook cell

    Returns:
        Tuple of (output_type, output_content)
    """
    output_type = None
    output_content = None

    if hasattr(output, 'data'):
        if 'application/kotlindataframe+json' in output.data:
            output_type = 'dataframe'
        elif 'application/plot+json' in output.data:
            output_type = 'plot'
        else:
            output_type = 'generic'
            # Try to get output content from different possible fields
            if 'text/plain' in output.data:
                output_content = output.data['text/plain']
    elif hasattr(output, 'text'):
        output_type = 'generic'
        output_content = output.text
    elif hasattr(output, 'data'):
        output_type = 'generic'
        output_content = str(output.data)

    return output_type, output_content


def create_md_file(md_filename: str, cells: List[Tuple[NotebookNode, Optional[str], Optional[str]]], base_name: str) -> None:
    """
    Creates a markdown file preserving the order of markdown and code cells.

    Args:
        md_filename: Path to the output markdown file
        cells: List of tuples containing (cell, output_type, output_content)
        base_name: Base name for function names and class names
    """
    try:
        with open(md_filename, "w", encoding="utf-8") as md_file:
            # Extract title from the first markdown cell if it exists
            title, title_cell_index = _find_title_cell(cells)

            # Write the title at the very beginning if found
            if title:
                md_file.write(title + "\n\n")

            # Add summary tags after the title
            _write_summary_tags(md_file)

            # Add import tag with the full class name
            class_name = f"org.jetbrains.kotlinx.kandy.tests.{base_name.capitalize()}Tests"
            md_file.write(f"<!---IMPORT {class_name}-->\n\n")

            # Process each cell
            _process_cells_for_md(md_file, cells, title_cell_index, base_name, md_filename)
    except Exception as e:
        print(f"Error creating markdown file: {str(e)}")
        sys.exit(1)


def _find_title_cell(cells: List[Tuple[NotebookNode, Optional[str], Optional[str]]]) -> Tuple[Optional[str], Optional[int]]:
    """
    Find the title cell in the notebook.

    Args:
        cells: List of tuples containing (cell, output_type, output_content)

    Returns:
        Tuple of (title, title_cell_index)
    """
    for i, (cell, _, _) in enumerate(cells):
        if cell.cell_type == "markdown":
            lines = cell.source.strip().split('\n')
            if lines and lines[0].startswith('# '):
                return cell.source, i
    return None, None


def _write_summary_tags(file) -> None:
    """
    Write summary tags to the markdown file.

    Args:
        file: File object to write to
    """
    file.write("<web-summary>\nTODO: write summary\n</web-summary>\n\n")
    file.write("<card-summary>\nTODO: write summary\n</card-summary>\n\n")
    file.write("<link-summary>\nTODO: write summary\n</link-summary>\n\n")


def _process_cells_for_md(
    md_file, 
    cells: List[Tuple[NotebookNode, Optional[str], Optional[str]]], 
    title_cell_index: Optional[int], 
    base_name: str,
    md_filename: str
) -> None:
    """
    Process cells for markdown output.

    Args:
        md_file: File object to write to
        cells: List of tuples containing (cell, output_type, output_content)
        title_cell_index: Index of the title cell
        base_name: Base name for function names
        md_filename: Path to the output markdown file
    """
    code_index = 1
    for i, (cell, output_type, output_content) in enumerate(cells):
        # Skip the title cell as we've already written it
        if i == title_cell_index:
            continue

        if cell.cell_type == "markdown":
            md_file.write(cell.source + "\n\n")
        elif cell.cell_type == "code":
            function_name = f"{base_name}_{code_index}"
            md_file.write(f"<!---FUN {function_name}-->\n\n")
            md_file.write("<!---END-->\n\n")

            if output_type == 'dataframe':
                # Add intermediate folder with the file name for table resources
                folder_name = os.path.splitext(os.path.basename(md_filename))[0]
                md_file.write(
                    f'<inline-frame src="./resources/{function_name}.html" width="705px" height="500px"></inline-frame>\n\n')
            elif output_type == 'plot':
                md_file.write(f'![{function_name}]({function_name}.svg)\n\n')
            elif output_type == 'generic' and output_content:
                md_file.write("Output:\n```\n")
                md_file.write(output_content)
                md_file.write("\n```\n\n")

            code_index += 1


def extract_declarations(code: str) -> List[str]:
    """
    Extract variable, function, and class declarations.

    Args:
        code: Kotlin code to extract declarations from

    Returns:
        List of declaration strings
    """
    declarations = []
    lines = code.split('\n')
    i = 0
    # Track nesting level to only extract top-level declarations
    nesting_level = 0

    while i < len(lines):
        line = lines[i].rstrip()
        stripped = line.strip()

        # Skip empty lines and comments
        if not stripped or stripped.startswith('//'):
            i += 1
            continue

        # Only process declarations at the top level (nesting_level == 0)
        if nesting_level == 0:
            # Handle import statements
            if stripped.startswith('import '):
                declarations.append(('import', stripped))
                i += 1
                continue

            # Handle class-like declarations
            elif _is_class_declaration(stripped):
                declaration, i = _extract_block_declaration(lines, i, 'class')
                declarations.append(declaration)
                # Update nesting level after extraction
                continue

            # Handle variable declarations (val/var)
            elif stripped.startswith(('val ', 'var ')):
                declaration, i = _extract_variable_declaration(lines, i)
                declarations.append(declaration)
                continue

            # Handle function declarations
            elif stripped.startswith('fun '):
                declaration, i = _extract_function_declaration(lines, i)
                if declaration:
                    declarations.append(declaration)
                continue
            else:
                # Update nesting level for non-declaration lines
                nesting_level += line.count('{') - line.count('}')
                i += 1
        else:
            # Update nesting level for lines inside nested blocks
            nesting_level += line.count('{') - line.count('}')
            # Skip lines inside nested blocks
            i += 1

    # Make all declarations private
    final_declarations = _make_declarations_private(declarations)

    return final_declarations


def _is_class_declaration(line: str) -> bool:
    """
    Check if a line is a class declaration.

    Args:
        line: Line to check

    Returns:
        True if the line is a class declaration, False otherwise
    """
    class_keywords = ('enum class', 'interface', 'class', 'data class', 'value class')
    return any(line.startswith(kw) for kw in class_keywords)


def _extract_block_declaration(lines: List[str], start_index: int, decl_type: str) -> Tuple[Tuple[str, str], int]:
    """
    Extract a block declaration (class, function) from lines.

    Args:
        lines: List of code lines
        start_index: Index to start extraction from
        decl_type: Type of declaration ('class' or 'function')

    Returns:
        Tuple of ((declaration_type, declaration_text), new_index)
    """
    line = lines[start_index].rstrip()
    current_lines = [line]
    brace_count = line.count('{') - line.count('}')
    i = start_index + 1

    while i < len(lines) and (brace_count > 0 or (brace_count == 0 and '{' not in line)):
        next_line = lines[i]
        current_lines.append(next_line)
        brace_count += next_line.count('{') - next_line.count('}')
        i += 1

    full_decl = '\n'.join(current_lines)
    return (decl_type, full_decl), i


def _extract_variable_declaration(lines: List[str], start_index: int) -> Tuple[Tuple[str, str], int]:
    """
    Extract a variable declaration from lines.

    Args:
        lines: List of code lines
        start_index: Index to start extraction from

    Returns:
        Tuple of ((declaration_type, declaration_text), new_index)
    """
    line = lines[start_index].rstrip()
    current_lines = [line]
    brace_count = line.count('{') - line.count('}')
    open_parens = line.count('(') - line.count(')')
    i = start_index + 1

    # Check if the declaration is complete on the first line
    is_complete = '=' in line and brace_count == 0 and open_parens == 0 and not line.endswith('=')

    # Continue extraction if the declaration is not complete or if there are unbalanced braces/parentheses
    while i < len(lines):
        next_line = lines[i].rstrip()
        next_line_stripped = next_line.strip()

        # Check if this line is a continuation of the variable declaration
        is_continuation = (
            brace_count > 0 or 
            open_parens > 0 or 
            next_line_stripped.startswith('.') or  # Method chaining
            next_line_stripped.startswith('=') or  # Assignment operator
            line.endswith('=') or  # Previous line ended with =
            # Common operators that might continue a declaration
            any(line.endswith(op) for op in ['+', '-', '*', '/', '%', '&&', '||', '?', ':', '??']) or
            # Check for method chaining patterns
            (i > start_index and any(current_lines[-1].rstrip().endswith(op) for op in ['.', '(', '{', '[', ','])) or
            # Check for method calls with .into() pattern
            '.into(' in next_line_stripped
        )

        # Check if this is a comment line
        is_comment = next_line_stripped.startswith('//')

        # Check if we should continue extraction
        should_continue = not is_complete or brace_count > 0 or open_parens > 0 or is_continuation

        if not should_continue:
            # If this is a comment line, check the next line for method chaining
            if is_comment and i + 1 < len(lines):
                next_next_line = lines[i + 1].strip()
                if next_next_line.startswith('.') or '.into(' in next_next_line:
                    # Include the comment line and continue extraction
                    current_lines.append(next_line)
                    i += 1
                    continue

            break

        if not is_continuation:
            # Check if this is a new declaration or statement
            if (next_line_stripped.startswith(('val ', 'var ', 'fun ', 'class ', 'if ', 'for ', 'while ')) or 
                next_line_stripped == ''):
                break

            # If this is a comment, include it and continue
            if is_comment:
                current_lines.append(next_line)
                i += 1
                continue

        current_lines.append(next_line)
        brace_count += next_line.count('{') - next_line.count('}')
        open_parens += next_line.count('(') - next_line.count(')')

        # Update line for the next iteration to check for operators
        line = next_line

        # Check if the declaration is now complete
        is_complete = brace_count == 0 and open_parens == 0

        i += 1

    full_decl = '\n'.join(current_lines)
    return ('property', full_decl), i


def _extract_function_declaration(lines: List[str], start_index: int) -> Tuple[Optional[Tuple[str, str]], int]:
    """
    Extract a function declaration from lines.

    Args:
        lines: List of code lines
        start_index: Index to start extraction from

    Returns:
        Tuple of ((declaration_type, declaration_text), new_index) or (None, new_index)
    """
    declaration, i = _extract_block_declaration(lines, start_index, 'function')
    _, full_decl = declaration

    # Skip test functions
    if '@Test' in full_decl or 'test' in full_decl.lower():
        return None, i

    return declaration, i


def _make_declarations_private(declarations: List[Tuple[str, str]]) -> List[str]:
    """
    Make all declarations private.

    Args:
        declarations: List of (declaration_type, declaration_text) tuples

    Returns:
        List of declaration strings
    """
    final_declarations = []

    for decl_type, decl in declarations:
        if decl_type == 'import':
            final_declarations.append(decl)
        else:
            lines = decl.split('\n')
            if not lines[0].lstrip().startswith('private '):
                indent = len(lines[0]) - len(lines[0].lstrip())
                lines[0] = ' ' * indent + 'private ' + lines[0].lstrip()
            final_declarations.append('\n'.join(lines))

    return final_declarations


def create_kt_file(kt_filename: str, cells: List[Tuple[NotebookNode, Optional[str], Optional[str]]], base_name: str) -> None:
    """
    Creates a Kotlin test file preserving order of code cells.

    Args:
        kt_filename: Path to the output Kotlin file
        cells: List of tuples containing (cell, output_type, output_content)
        base_name: Base name for function names and class names
    """
    try:
        # Collect all definitions and imports
        defs, imports = _collect_definitions_and_imports(cells)

        with open(kt_filename, "w", encoding="utf-8") as kt_file:
            _write_kt_file_header(kt_file, base_name)
            _write_kt_file_imports(kt_file, imports)
            _write_kt_file_definitions(kt_file, defs, base_name)
            _write_kt_file_tests(kt_file, cells, base_name)
            _write_kt_file_footer(kt_file)
    except Exception as e:
        print(f"Error creating Kotlin file: {str(e)}")
        sys.exit(1)


def _collect_definitions_and_imports(cells: List[Tuple[NotebookNode, Optional[str], Optional[str]]]) -> Tuple[List[str], List[str]]:
    """
    Collect all definitions and imports from code cells.

    Args:
        cells: List of tuples containing (cell, output_type, output_content)

    Returns:
        Tuple of (definitions, imports)
    """
    defs = []
    imports = []

    for cell, _, _ in cells:
        if cell.cell_type == "code":
            cell_decls_all = extract_declarations(cell.source)
            cell_imports = [decl for decl in cell_decls_all if decl.startswith('import')]
            cell_defs = [decl for decl in cell_decls_all if not decl.startswith('import')]
            defs.extend(cell_defs)
            imports.extend(cell_imports)

    return defs, imports


def _write_kt_file_header(file, base_name: str) -> None:
    """
    Write the header of the Kotlin file.

    Args:
        file: File object to write to
        base_name: Base name for class name
    """
    _write_line(file, "package org.jetbrains.kotlinx.kandy.tests")
    _write_line(file, "")
    _write_line(file, "import kotlin.test.Test")


def _write_kt_file_imports(file, imports: List[str]) -> None:
    """
    Write imports to the Kotlin file.

    Args:
        file: File object to write to
        imports: List of import statements
    """
    # Add required imports
    required_imports = [
        "import org.jetbrains.kotlinx.kandy.letsplot.*",
        "import org.jetbrains.kotlinx.kandy.letsplot.export.*",
        "import org.jetbrains.kotlinx.kandy.letsplot.feature.*",
        "import org.jetbrains.kotlinx.kandy.letsplot.layers.*",
        "import org.jetbrains.kotlinx.kandy.letsplot.multiplot.*",
        "import org.jetbrains.kotlinx.kandy.letsplot.multiplot.facet.*",
        "import org.jetbrains.kotlinx.kandy.letsplot.translator.*",
        "import org.jetbrains.kotlinx.kandy.letsplot.scales.*",
        "import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.*",
        "import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.AxisPosition",
        "import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.limits",
        "import org.jetbrains.kotlinx.kandy.letsplot.style.*",
        "import org.jetbrains.kotlinx.kandy.letsplot.tooltips.*",
        "import org.jetbrains.kotlinx.kandy.letsplot.settings.*",
        "import org.jetbrains.kotlinx.kandy.letsplot.settings.font.*",
        "import org.jetbrains.kotlinx.kandy.letsplot.samples.SampleHelper",
        "import org.jetbrains.kotlinx.kandy.ir.scale.Scale",
        "import org.jetbrains.kotlinx.kandy.dsl.*",
        "import org.jetbrains.kotlinx.kandy.util.color.*",
        "import org.jetbrains.kotlinx.kandy.util.context.*",
        "import org.jetbrains.kotlinx.dataframe.api.*",
        "import org.jetbrains.kotlinx.dataframe.*",
        "import org.jetbrains.kotlinx.dataframe.annotations.*",
        "import org.jetbrains.kotlinx.dataframe.io.*",
        "import org.jetbrains.kotlinx.dataframe.columns.*",
        "import org.jetbrains.kotlinx.dataframe.jupyter.ImportDataSchema",
        "import org.jetbrains.kotlinx.dataframe.jupyter.importDataSchema",
        "import org.jetbrains.kotlinx.dataframe.jupyter.KotlinNotebookPluginUtils",
        "import java.net.URL",
        "import java.io.File",
        "import kotlinx.datetime.Instant",
        "import kotlinx.datetime.LocalDateTime",
        "import kotlinx.datetime.LocalDate",
        "import org.jetbrains.kotlinx.dataframe.dataTypes.*",
        "import org.jetbrains.kotlinx.dataframe.impl.codeGen.urlCodeGenReader"
    ]

    # Write all required imports first
    for imp in required_imports:
        _write_line(file, imp)

    # Write user imports
    for imp in imports:
        _write_line(file, imp)

    _write_line(file, "")


def _write_kt_file_definitions(file, defs: List[str], base_name: str) -> None:
    """
    Write class definition and member definitions to the Kotlin file.

    Args:
        file: File object to write to
        defs: List of definitions
        base_name: Base name for class name
    """
    sample_name = os.path.splitext(os.path.basename(file.name))[0]
    _write_line(file, f"class {base_name.capitalize()}Tests : SampleHelper(\"{sample_name}\") {{")

    for definition in defs:
        _write_line(file, definition, indent=4)


def _write_kt_file_tests(file, cells: List[Tuple[NotebookNode, Optional[str], Optional[str]]], base_name: str) -> None:
    """
    Write test methods to the Kotlin file.

    Args:
        file: File object to write to
        cells: List of tuples containing (cell, output_type, output_content)
        base_name: Base name for function names
    """
    test_index = 1
    for cell, output_type, _ in cells:
        if cell.cell_type == "code":
            cell_code = cell.source
            if cell_code.strip():
                function_name = f"{base_name}_{test_index}"
                save_method = _get_save_method(output_type)

                _write_line(file, "", indent=4)
                _write_line(file, "@Test", indent=4)
                _write_line(file, f"fun {function_name}() {{", indent=4)
                _write_line(file, "// SampleStart", indent=8)

                for code_line in cell_code.split('\n'):
                    _write_line(file, code_line, indent=8)

                _write_line(file, "// SampleEnd" + save_method, indent=8)
                _write_line(file, "}", indent=4)
                test_index += 1


def _get_save_method(output_type: Optional[str]) -> str:
    """
    Get the appropriate save method based on output type.

    Args:
        output_type: Type of output ('dataframe', 'plot', or None)

    Returns:
        Save method string
    """
    if output_type == 'dataframe':
        return "\n        .saveDfHtmlSample()"
    elif output_type == 'plot':
        return "\n        .savePlotSVGSample()"
    return ""


def _write_kt_file_footer(file) -> None:
    """
    Write the footer of the Kotlin file.

    Args:
        file: File object to write to
    """
    _write_line(file, "}")


def _write_line(file, line: str, indent: int = 0) -> None:
    """
    Write a line to a file with the specified indentation.

    Args:
        file: File object to write to
        line: Line to write
        indent: Number of spaces to indent
    """
    file.write(" " * indent + line + "\n")


def process_notebook(notebook_path: str) -> None:
    """
    Processes a Jupyter notebook and generates corresponding Markdown and Kotlin test files while preserving order.

    Args:
        notebook_path: Path to the Jupyter notebook file
    """
    try:
        base_name = os.path.splitext(os.path.basename(notebook_path))[0]
        md_output = f"{base_name}.md"
        kt_output = f"{base_name}.kt"

        test_base_name = 'notebook_test_' + base_name

        cells = extract_content(notebook_path)
        create_md_file(md_output, cells, test_base_name)
        create_kt_file(kt_output, cells, test_base_name)

        print(f"Generated: {md_output}, {kt_output}")
    except Exception as e:
        print(f"Error processing notebook: {str(e)}")
        sys.exit(1)


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python nb_to_doc.py <notebook_path>")
        sys.exit(1)

    process_notebook(sys.argv[1])
