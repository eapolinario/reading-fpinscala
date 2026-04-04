# List available recipes
default:
    @just --list

# Compile the project
build:
    scalac simple.scala

# Run the main program
run:
    scala simple.scala

# Clean build artifacts
clean:
    rm -f *.class *.tasty
