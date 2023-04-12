# Features

## Operating System

### Direct Interaction with Code

Users will use functions directly through a _visualizer_, which creates the most convenient interface for the device for viewing data and performing operations on it.

### Vote Database

Modules can be submitted for use by others.  Submitted modules have vote trackers.  The highest rated packages are preferred for lookup by users.

## Intermediate Representation

### Purity

There are no untracked side effects.  Operations that have effects take and return an object representing the state.

### No Subtyping

No types are subtypes.  If you wish to achieve a similar effect, use implicit casting functions.  Interface-like behavior can be achieved with function pointers and virtual method tables.

### Unified Call Syntax

There is no differentiation between member functions and top-level functions.

### Metaprogramming

Modules are first-class and can be created dynamically.

### Default Values

Functions can have arguments with default values.  Default values can be calculated from other values.

### Annotations

Annotations (much like Java's) can be applied to both functions and parameters.  Documentation is to be provided through annotations rather than comments.
 
### Inline Tests

Tests can be inserted directly into the source.  Asserts can be made directly within the functions themselves.

### Multiple Return Values

Functions can have multiple, named return values.

### Function Overloading

Function overloading based on number of arguments, types, and argument names is permitted.

### Value Standardization

Every value is
- equatable
- hashable
- sortable
- serializable

Every struct is
- field iterable

These properties enable easier networking and data structures.

## Language

### Universal Call Syntax

Goal is to support as many of these as possible, prioritized in order:

```
x = x.plus(1)
x = plus(x, 1)
x = plus(left = x, right = 1)
x = x + 1
x = + 1
x = .plus(1)
x = plus(
    left = x  // implied comma
    right = 1
)
x = plus x 1
x = plus
    left = x
    right = 1
x = plus
    x
    1
```

### Access Sugar

```
point.x()
point.x

point = point.x(2)
point.x = 2

box = box.point(box.point().x(2))
box.point.x = 2

array = array.get(1)
array[1]

array = array.set(1, 2)
array[1] = 2

array = array.set(1, array.get(1).x(2))
array[1].x = 2
```

### Implied Arguments

```
testFunction(infer inout x: Int) = {
    x = x + 1
}
main(root: Root) = {
    x = 2
    testFunction(x = x)
    testFunction()
    assert(x == 4)
}
```

```
printLine(infer inout out: OutputStream, text: String) = {
    ...
}
main(root: Root) = {
    out = root.standardOut
    printLine("Hello world!")
}
```

### Implicit Casting Functions

```
cast(value: Int): Double = ...
takesDouble(value: Double) = ...

takesDouble(42)  // Implicitly casted here
```

### Monad Handling

```
Optional(32).monadMap(x => x + 4)
Optional(32) + 4

Optional(32).monadFlatMap(x => Optional(4).map(y => x + y))
Optional(32) + Optional(4)

// Prefers 'monadCombine' if available
Optional(32).monadCombine(Optional(4), (x, y) => x + y)
Optional(32) + Optional(4)
```

- Reference objects
- Local "variables"
- Lambdas

```
repeat(times: Int, action: inline ()->Unit): Unit = ...
repeat<...P>(times: Int, ...P, action: (...P)->Void) {
    action()
    (times == 0).if(
        then = { }
        else = {
 <           repeat(
                times = times - 1,
                action = action
            )
        }
    )
}

x = 0
repeat(32) {  // infers x as input and output, due to both a read and write below
    x =+ 1
}
```

### Language Elements
 
Text -> Typeless/Unresolved -> Executable
Note that for editor, the reverse must be findable

- Typeless/Unresolved
  - Import
  - Module
  - Type Declaration
  - Function Declaration
  - Anonymous Function
    - Implied parameters allowed
  - Block
  - Function Call
    - Implied parameters allowed
  - Constant
  - Named Function Access
    - Must provide full header (exc. return type) because of overloading
    - inference could win a lot here
  - Property Access
    - Could be mixed with get/set args like array access
  - Property Assignment
    - Could be mixed with get/set args like array access
    - Could be mixed with an operation
  - Type Reference
    - Lambda Types
  - Generics
    - Type
    - Parameter list

### AST vs Runtime

We need everything that's useful for autocomplete, editing, self-transpiling 

- Types resolved
- Static function references resolved
- Module functions indexed

WHAT IS THE TARGET?

- Transpile
  - JS/Kotlin/Swift?
  - More importantly, UI and server frameworks?
- Interpretation (required due to reflection)

IMGUI

MyView(inout num: Int)
    Stack
        Button("Inc") { num++ }
        Text(num.toString())
        Button("Dec") { num-- }