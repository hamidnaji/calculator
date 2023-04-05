package com.example.calculator.calculation.converting



//to converting infix expression to postfix

class Conversion
{
    private fun precedence(text: Char): Int
    {
        if (text == '+' || text == '-')
        {
            return 1;
        }
        else if (text == '*' || text == '/')
        {
            return 2;
        }
        else if (text == '^')
        {
            return 3;
        }
        return -1;
    }
    fun is_operator(text: Char): Boolean
    {
        if (text == '+' || text == '-' ||
            text == '*' || text == '/' || text == '^')
        {
            return true;
        }
        return false;
    }
    // Converting the given infix expression to postfix expression
    fun infixToPostfix(infix: String): String
    {
        var result: String = "";
        // Get the size
        val size: Int = infix.length;
        // Create empty stack
        val s: MyStackChar = MyStackChar();
        var i: Int = 0;
        while (i < size)
        {
            if ((infix.get(i) in '0'..'9') ||
                (infix.get(i) in 'a'..'z') ||
                (infix.get(i) in 'A'..'Z'))
            {
                // When getting a operands
                result += infix.get(i).toString();
            }
            else
            {
                if (s.isEmpty() || infix.get(i) == '(')
                {
                    // Base case
                    // When getting a open parenthesis
                    // Or stack is empty
                    s.push(infix.get(i));
                }
                else if (infix.get(i) == ')')
                {
                    // Need to remove stack element until the close bracket
                    while (!s.isEmpty() && s.peek() != '(')
                    {
                        // Get top element
                        result += s.peek();
                        // Remove stack element
                        s.pop();
                    }
                    if (s.peek() == '(')
                    {
                        // Remove stack element
                        s.pop();
                    }
                }
                else
                {
                    // Remove stack element until precedence of
                    // top is greater than current infix operator
                    while (!s.isEmpty() && this.precedence(infix.get(i)) <= this.precedence(s.peek()))
                    {
                        // Get top element
                        result += s.peek();
                        // Remove stack element
                        s.pop();
                    }
                    // Add new operator
                    s.push(infix.get(i));
                }
            }
            i += 1;
        }
        // Add remaining elements
        while (!s.isEmpty())
        {
            result += s.peek();
            s.pop();
        }
        // Display result

        return result
    }
}