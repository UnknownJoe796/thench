# Open Questions

## Should interfaces have first-class support?

- Pros:
  - The target translation languages all have them first class already, therefore translation and adaption might be easier
  - Might make the standard library better
  - Might make implicit conversions unnecessary
  - Everyone is forced to talk about interfaces in the same way
- Cons:
  - Not leveraging the language as well
  - C translator would have to manually implement this concept
  - This might affect the OS later
  - Implementation by extension wouldn't translate nicely

## How should reference objects be handled?

One option looks like this, if it's not first class:

```thench
type Reference<T>

fun get<T>(this: Reference<T>, infer inout io: IO): T
fun set<T>(this: Reference<T>, value: T, infer inout io: IO)

fun increment(this: Reference<Int>, infer inout io: IO)

val x = Ref<Int>(1)
x += 1  // uses IO, a.k.a it's forced order
```

If it was first class, that might play with translation better.  Nim did it, and probably did for a reason?  Then again, they didn't care about functional purity.

Translational efficiency would probably be created by making it first class.  Referenced access could be optimized, like:

```thench
struct Point
    x: Int
    y: Int

val point = Ref<Point>(Point(1, 2))
point.get().x  // Only part of the referenced struct is used, no reason to copy it all out
```

Using `Ref<X>` actually forces atomicity inside a function - perhaps that's good?

Question: can we implement a concurrent-skip list with that?

Answer: if locking is a part of the referencing system, yes.

```thench
val pt: Ref<Point>
pt.lock { point ->
    point.y += point.x
}
```

## Should the typed format store calculation graphs or simply a typed version of the syntax?

- Calculation Graph:
  - Potentially results in better analysis and transpiling, due to ease of analyzing allocations and reuse (functional-but-in-place)
- Typed Syntax:
  - Can reproduce the syntax without issue for transpiling
  - Sometimes code structure has additional meaning in communication
  - 