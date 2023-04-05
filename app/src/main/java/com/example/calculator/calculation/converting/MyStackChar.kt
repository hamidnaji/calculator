package com.example.calculator.calculation.converting

import calculator.StackNodeChar

class MyStackChar() {
    var top: StackNodeChar? ;
    var size: Int;

    init {
        top = null
        size = 0
    }
    // Add node at the top of stack
    fun push(element: Char): Unit
    {
        top = StackNodeChar(element, top);
        size += 1;
    }
    fun isEmpty(): Boolean
    {
        return !(size > 0 && top != null)
    }
    // Remove top element of stack
    fun pop(): Unit
    {
        if (size > 0 && top != null)
        {
            // Change top element of stack
            top = top!!.next;
            size -= 1;
        }
    }
    // Return top element of stack
    fun peek(): Char
    {
        if (top == null)
        {
            return ' ';
        }
        return top!!.element;
    }
}