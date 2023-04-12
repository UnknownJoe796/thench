# Reference

    something
    `normally illegal characters!@#$%^&*())`

# Constant

    123
    123.0
    "asdf"
    true
    false
    null

# Multi

    [1, 2, 3]

# Call

    name(first = first, second = second, third = third)
    name(this = something)
    something.name()
    name<argument = Number>(first = first, second = second, third = third)
    name(first = first, second = second, third = third, first -> first)
    name(first <-> first)

# Block

    {
        name = something()
        name = somethingElse()
        return = name
    }

# Module
    
    module {
        import EXPRESSION
        name = EXPRESSION
        name(first: ArgType, second: ArgType): ReturnType = EXPRESSION
        name(first: ArgType, second: ArgType = EXPRESSION): ReturnType = EXPRESSION
        name(first: ArgType, out first: ArgType): ReturnType = EXPRESSION
        name(first: ArgType, infer first: ArgType): ReturnType = EXPRESSION
        name(first: ArgType!): ReturnType = EXPRESSION
        name<T: TypeBound>(first: T!): T = EXPRESSION
    }

# Lazy / Lambda

    => x
    (x: Int): Int => x

# Operator Sugar

    4 + 5
        4.plus(5)
        Also for -*/%^==<>
    -4
        4.negative()
        Also for +!
    
# Assignment Sugar

    {
        a = 3
        a = .plus(4)
        a =+ 4
        a += 4
        a++
        a.something = 3
            a = a.something(3)
        a.something = .plus(3)
            a = a.something(a.something().plus(3))
    }