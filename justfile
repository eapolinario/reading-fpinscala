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

# Clean build artifacts for a chapter
clean chapter:
    scala-cli clean {{chapter}}
