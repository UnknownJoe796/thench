# Ideas

## Structural vs Assistive Typing

Structural typing is about creating an efficient, concrete shape of data in RAM.

Assistive typing is about helping the programmer.

Structural typing is necessary in a PL to make it efficient, but assistive is absolutely not.

Can one keep the two separated?

Pointer = structural, object pointed to = assistive

However, where this starts bleeding over is talking about *safety*.

What kind of safety do we need?

- Memory (out-of-bounds, alloc/dealloc safety)
- Parallelism (locking, etc)

Perhaps structural is pointer + size of memory pointed to?

## Requirements of the Typing System

- Memory Safety
- Parallelism Safety
- Requires no understanding of how computer programs run
  - Lifetimes, stack vs heap, etc. are not permitted

The above can still be achieved with bit-count types only.

### Newer thoughts

We track interfaces only - AKA what can I do with the thing?  That's what matters at the user level, and as long as that doesn't prevent compilation, we're good enough.

Functions that generate types are totally fine?

```
Number = interface {
  plus(other: Number): Number
  minus(other: Number): Number
}

multiply(this: Number, other: Number): Number = ...

List(type: Type) = interface {
  get(index: Int): type
}

concat(left: List(_), right: List(_)): List(_) = ...

sum(list: List(T)): Number
  where T >= HasNumber
  = ...
  
sum(T: Type >= HasNumber)(list: List(T)): Number

List(Element: Type) = interface {
  Element = Element
  get(index: Int): Element
}
sum(list: List(unknown)): list.Element {
  
}

sameTypePair(first: Any, second: Any): Commonality(first, second)

```

Refinement types are necessary to represent types as code?!?!

Options:

- Generics are functions that return functions
  - Fancy stuff later can infer it maybe
- Generics are functions where one input's type is used in another position

What is a type?  If a type is a collection of available functions or nominal requirements, then all types can be reduced to simply that.

A type variable could be used to indicate generics?

Typing could just be another language layer

```
appendToList
a (must be List<any>), a.element -> a.element 
```

Structural typing?

What is a structured type?  It is a type where certain functions are available on it

Adding a function, therefore, would modify what types satisfy what conditions...

Implementing interfaces could require an explicit directive, which would greatly simplify processing.  Except that you can't, because doing so would remove the usefulness of structural typing?

- Number has plus with self
- Anything with plus can have times
- Anything with times can have power

The typing system needs to have traditional generics still.  Otherwise, you can't infer generics because code doesn't run in reverse.

BUT WHAT IF IT DID

```
List(T) = ...
plus<T>(this: List(T), value: T): List(T)
plus(this: Int, other: Int): Int

listOf(1, 2, 3).plus(4.plus(5))

SomeList = interface()
  .onType("T", Type)
List(T) = concrete()
  .implements(SomeList)
  .onType("T", T)
  
plus<T>(this: SomeList, value: T): List(T)
plus(this: Int, other: Int): Int

listOf(1, 2, 3).plus(4.plus(5))

```

When looking up which function to use, we treat inputs as the lower bound of what can be entered

The lower bound of generics is particularly interesting out of the group.

Everything is a function declaration?

x = 2 is shorthand for x<>() = 2

Lambdas != Functions - functions can be overloaded, lambdas cannot

Protocol adherance is wack yo

Problem is that protocol adherance is a late-analysis thing; and what happens if there are multiple?




# CRITICAL DISCOVERY

A type's interface implementation / reflectively-identifiable information (i.e. does x conform to y?) MUST be declared at the same site as the definition of the type!

Swift can only get away with implementing protocols by extension due to not caring about globality - A.K.A, a Swift program is a single contained unit and the import of a module literally can affect another module's execution with even referencing it


# MODELS

GOALS: Support concatenative style

Module
    collection of functions

Function Declaration
    typeParams: ordered name/value pairs
    inputs: ordered name/type(ref) pairs
    outputs: ordered name/type(ref) pairs
    implementation: call graph
    
Type
    name basis
    typeParams: ordered name/value pairs
    annotations

Functions matching a very specific signature - single input, single output, having the correct marker - are implicit casts in the language.

There are no "casts" and type "inheritance".  Casting is done strictly through functions, including "casting" to supertypes.

# STANDARD LIBRARY

Every value is 
    equatable
    hashable
    sortable
    serializable
Every struct is
    field iterable

# Big Picture - Operating Systems

Keep every function in a huge database with upvotes
Visualizations are just functions - OS uses the highest upvoted visualization by default, but can be selected manually
Visualizers take information in about applicable functions too - for example, an image visualizer can accept a function that modifies an image and requires a point as a continually-applied pen-like tool.

# Next considerations

- Communication
- Encryption
- Networking
- If everything is easy, shouldn't making a database be easy too?
- Language-supported Documentation
- Language-supported Testing
  - Inline testing blocks?
- Complexity analysis for inserting progress measurements