# Functional Programming in Scala

Exercise solutions for [Functional Programming in Scala](https://www.manning.com/books/functional-programming-in-scala-second-edition).

## Setup

Requires [Nix](https://nixos.org/) with flakes enabled.

```bash
nix develop
```

## Usage

```bash
just run   chapter-2   # run a chapter's main program
just test  chapter-3   # run a chapter's tests
just build chapter-3   # compile a chapter
just clean chapter-3   # remove build artifacts
just setup-ide         # regenerate BSP config for all chapters (run after adding a new chapter)
```

## Structure

```
chapter-2/   # Exercises from chapter 2
chapter-3/   # Exercises from chapter 3
```
