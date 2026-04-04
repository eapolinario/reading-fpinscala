# List available recipes
default:
    @just --list

# Compile a chapter (e.g. just build chapter-2)
build chapter:
    scalac {{chapter}}/*.scala

# Run a chapter's main program (e.g. just run chapter-2)
run chapter:
    scala {{chapter}}/*.scala

# Clean build artifacts for a chapter, or all chapters
clean chapter="*":
    rm -f {{chapter}}/*.class {{chapter}}/*.tasty
