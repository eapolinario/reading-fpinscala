# List available recipes
default:
    @just --list

# Compile the project
build:
    sbt compile

# Run tests
test:
    sbt test

# Start a Scala REPL with project on the classpath
repl:
    sbt console

# Clean build artifacts
clean:
    sbt clean
