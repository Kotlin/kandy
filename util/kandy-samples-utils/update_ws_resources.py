#!/usr/bin/env python3
import argparse
import os
from pathlib import Path

def find_all_files(resources_dir):
    """
    Recursively find all files in the given directory.

    Args:
        resources_dir (str): Path to the resources directory

    Returns:
        list: List of file paths
    """
    resources_path = Path(resources_dir)
    all_files = []

    for root, _, files in os.walk(resources_path):
        for file in files:
            all_files.append(os.path.join(root, file))

    return all_files

def update_md_file(md_file_path, resource_files, resources_dir):
    """
    Completely overwrite the markdown file with resource tags for each file.

    Args:
        md_file_path (str): Path to the markdown file
        resource_files (list): List of resource file paths
        resources_dir (str): Base directory for resources
    """
    resources_path = Path(resources_dir)

    # Prepare the resource tags
    resource_tags = []
    for file_path in resource_files:
        # Get just the filename without the path
        file_path = Path(file_path)
        filename = file_path.name

        # Create the resource tag
        resource_tag = f'<resource src="{filename}"></resource>'
        resource_tags.append(resource_tag)

    # Create content with only the resource tags
    content = '\n'.join(resource_tags)

    # Write the content to the MD file (completely overwriting it)
    with open(md_file_path, 'w', encoding='utf-8') as f:
        f.write(content)

def main():
    # Parse command-line arguments
    parser = argparse.ArgumentParser(description='Update markdown file with resource tags for files in a directory.')
    parser.add_argument('resources_dir', help='Path to the directory containing resource files')
    parser.add_argument('md_file', help='Path to the markdown file to update')

    args = parser.parse_args()

    # Find all files in the resources directory
    resource_files = find_all_files(args.resources_dir)

    # Update the markdown file with resource tags
    update_md_file(args.md_file, resource_files, args.resources_dir)

    print(f"Updated {args.md_file} with {len(resource_files)} resource tags.")

if __name__ == "__main__":
    main()
