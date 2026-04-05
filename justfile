# List available recipes
default:
    @just --list

# Compile a chapter (e.g. just build chapter-2)
build chapter:
    scala-cli compile {{chapter}}

# Run a chapter's main program (e.g. just run chapter-2)
run chapter:
    scala-cli run {{chapter}}

# Run tests for a chapter (e.g. just test chapter-2)
test chapter:
    scala-cli test {{chapter}}

# Start a debug session suspended on port 5005, attach your DAP client to it
debug chapter:
    scala-cli test -J -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005 {{chapter}}

# Regenerate root BSP config for all chapters (run after adding a new chapter)
setup-ide:
    #!/usr/bin/env python3
    import json, glob, os
    bsp_path = '.bsp/scala-cli.json'
    with open(bsp_path) as f:
        bsp = json.load(f)
    chapters = sorted(os.path.abspath(d) for d in glob.glob('chapter-*') if os.path.isdir(d))
    base = [a for a in bsp['argv'] if '/chapter-' not in a and a != os.path.abspath('.')]
    bsp['argv'] = base + chapters
    with open(bsp_path, 'w') as f:
        json.dump(bsp, f, indent=2)
    print('Updated .bsp/scala-cli.json with:', chapters)

# Clean build artifacts for a chapter
clean chapter:
    scala-cli clean {{chapter}}
