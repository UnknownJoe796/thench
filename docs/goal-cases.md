# Goal Cases

These are things we would like to be able to support without issue:

## Self-transpile

```thench
fun test(): Int = 42
fun compile(inout folder: File) {
    transpileToJs(folder.resolve("out.js"), ::test)
}
// cli: compile(File("."))
```

## Importing a platform library

```thench
val yaml: module = npm("yaml", "2.2.1")
val result: TsAny = yaml.parse("user content")
```

## Importing an OpenAPI spec

```thench
val onePassword: module = openApiSpec("https://api.apis.guru/v2/specs/1password.local/connect/1.5.7/openapi.json")
onePassword.getVaultById(vaultUuid = "72454cfd-7ace-4832-ac3f-324a72cc0b53".uuid)
```

## Compile-time field mapping

Part of this would need to be inlined for translation to other languages

```thench
fun fieldMap<A: struct, B: struct>(a: A): B {
    return B(...B.parameters.associate {
        it to a[A.parameters.find { aParam -> aParam.name == it.name && aParam.type == it.type }!]
    })
}

struct First {
    a: Int
    b: Int
    c: Int
}
struct Second {
    a: Int
    b: Int
}

val second: Second = first.fieldMap()
val firstAlt: First = second.fieldMap()  // Compile-time error, as 'c' isn't present
```

## Interfaces as a library feature

Whether this should be done is an [open question](./open-questions.md#should-interfaces-have-first-class-support) at the moment.

```thench
val TestInterface = makeInterface(
    function = funHeader testA(this: This, a: Int): Int,
    function = funHeader testB(this: This, b: String): String,
)

struct TestImplementation {
    value: Int
}

fun testA(this: TestImplementation, a: Int): Int = this.value + a
fun testB(this: TestImplementation, b: String): String = "${this.value} + ${b}"

implicit fun toTestInterface(this: TestImplementation): TestInterface = TestInterface.implement(this)  // finds testA and testB in the environment

fun example(infer inout io: IO, input: TestInterface) {
    println(input.testB("Test"))
}

fun main(infer inout io: IO) {
    // Implicit cast from function "toTestInterface" here
    example(input = TestImplementation(value = 2))
    // Outputs "2 + Test"
}
```
