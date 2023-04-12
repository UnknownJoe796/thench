# Features

## Universal Call Syntax

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
```

## Access Sugar

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

## Inferred Arguments

```
fun testFunction(infer inout x: Int) {
    x = x + 1
}
fun main(root: Root) {
    x = 2
    testFunction(x = x)
    testFunction()
    assert(x == 4)
}
```

```
fun printLine(infer inout out: OutputStream, text: String) {
    ...
}
fun main(root: Root) {
    out = root.standardOut
    printLine("Hello world!")
}
```

## Implicit Casting Functions

```
implicit fun toDouble(value: Int): Double = ...
fun takesDouble(value: Double) = ...

takesDouble(42)  // Implicitly casted here
```

## Monad Handling

```
Optional(32).monadMap(x => x + 4)
Optional(32) + 4

Optional(32).monadFlatMap(x => Optional(4).map(y => x + y))
Optional(32) + Optional(4)

// Prefers 'monadCombine' if available
Optional(32).monadCombine(Optional(4), (x, y) => x + y)
Optional(32) + Optional(4)
```

## Generic parameter lists

```
repeat<...P>(times: Int, ...P, action: (...P)->Void) {
    action()
    if(
        condition = times == 0
        then = { }
        else = {
            repeat(
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
