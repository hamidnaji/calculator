package com.example.calculator.calculation.evalution

class StackNodeInt(var operand: Int,  top: StackNodeInt?) {
    var next: StackNodeInt? = top;
}