# Thench

A very early work in progress collaboration between Joseph Ivie and Garrett Jennings.

*The 'then' wrench, a programming language for all*

```thench
fun main(infer inout io: IO)
    println("Welcome to Thench!  What is your name?")
    val name = readln()
    println("Welcome, $name!")
```

## Status

Very, very early in exploration.

## High-Level Goals

- Scales effectively - the language should not present structural issues with large projects or dependency trees
  - A good way of stating it is that a whole computer's code should be usable in a single project without namespacing or interface adaption nightmares
- Useful for scripting and direct user interaction
- Useful for higher-level development, such as apps and HTTP servers
- Non-committal - The ability to back out of using the language or use it temporarily as part of editing an existing project
  - I imagine it as opening an existing code file, converting it to Thench, modifying it, then converting it back

## Functional Goals

- Purely functional programming
- Metaprogramming, but with full autocomplete support
  - Possible specifically due to the 'pure' (all computation dependencies tracked) trait of the language
- Excellent code editing tools
  - Full LSP support
  - Refactoring actions
- Syntax feels extremely comfortable and similar to the most commonly used programming languages
  - "If it ain't broke, don't fix it."
  - Look at similarities between the current forerunners of similar purpose - Typescript, Kotlin, Swift, Dart, C#, Python
- Self-transpiling using reflection
  - Import existing libraries in the platform
  - Export readable code for the target platform
- Acts as its own build system
- External code management at the function and type layer instead of the package layer
  - Publishing a single function or type should be easy
  - Versioning must be smooth to support this
  - Separate API and implementation versions to support bug and security fixes with deep dependency trees
  - Unit tests built-in to each declaration
- Theoretically highly optimizable
  - Concrete optimizations can come later

## Inspiration

- Kotlin - encourages pieces of the language to be self-similar where possible, making more of the language implemented in the standard library
- Koka - min-gen philosophy, universal function call syntax, Perceus memory management
- Nim - self-transpiling

## Links

- [Features](docs/features.md)
- [Interesting Goal Cases](docs/goal-cases.md)