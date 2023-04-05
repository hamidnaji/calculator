package com.example.calculator.calculation.evalution

import calculator.MyStackInt


// to convert postfix expression to number

class Execution
{
    fun evaluate(e: String): String
    {
        // Get the length of expression
        val size: Int = e.length;
        var a: Int ;
        var b: Int ;
        val s: MyStackInt = MyStackInt();
        var isVaild: Boolean = true;
        var i: Int = 0;
        // work with (+,-,/,*,%) operator
        while (i < size && isVaild)
        {
            if (e.get(i) in '0'..'9')
            {
                a = e[i].toInt() - '0'.toInt();
                s.push(a);
            }
            else if (s.isSize() > 1)
            {
                a = s.pop();
                b = s.pop();
                // Perform arithmetic operations between 2 operands
                if (e[i] == '+')
                {
                    s.push(b + a);
                }
                else if (e[i] == '-')
                {
                    s.push(b - a);
                }
                else if (e[i] == '*')
                {
                    s.push(b * a);
                }
                else if (e[i] == '/')
                {
                    s.push(b / a);
                }
                else if (e[i] == '%')
                {
                    s.push(b % a);
                }
                else
                {
                    // When use other operator
                    isVaild = false;
                }
            }
            else if (s.isSize() == 1)
            {
                // Special case
                // When use +, - at the beginning
                if (e.get(i) == '-')
                {
                    a = s.pop();
                    s.push(-a);
                }
                else if (e.get(i) != '+')
                {
                    // When not use  +,-
                    isVaild = false;
                }
            }
            else
            {
                isVaild = false;
            }
            i += 1;
        }
        if (!isVaild)
        {
            // Possible case use other operators
            // 1) When using special operators
            // 2) Or expression is invalid
            return "Invalid expression"
        }

        return  (s.pop().toString())
    }
}