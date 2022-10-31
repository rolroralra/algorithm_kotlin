package leetcode

import java.util.*

fun main() {
    println(isValid("()"))
    println(isValid("(){}[]"))
    println(isValid("({[]})"))
    println(isValid("(){"))
}

fun isValid(s: String): Boolean {
    val stack = Stack<Char>()

    for (c in s) {
        if (c == '(') {
            stack.push(')')
        } else if (c == '{') {
            stack.push('}')
        } else if (c == '[') {
            stack.push(']')
        } else if (stack.isEmpty() || stack.pop() != c) {
            return false
        }
    }

    return stack.isEmpty()
}

fun isValid2(s: String): Boolean {
    val stack = Stack<Char>()

    for (c in s) {
        if (isOpenedChar(c)) {
            stack.push(convertToClosedChar(c))
        } else if (isClosedChar(c)) {
            if (stack.isEmpty() || c != stack.pop()) {
                return false
            }
        } else {
            return false
        }
    }

    return stack.isEmpty()
}

fun convertToClosedChar(c: Char): Char = when (c) {
    '{' -> '}'
    '[' -> ']'
    '(' -> ')'
    else -> throw IllegalArgumentException("Illegal character : $c")
}

fun isOpenedChar(c: Char): Boolean = c == '{' || c == '(' || c== '['

fun isClosedChar(c: Char): Boolean = c == '}' || c == ')' || c== ']'