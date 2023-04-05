package calculator

import com.example.calculator.calculation.evalution.StackNodeInt

class MyStackInt() {
    var top: StackNodeInt? ;
    var count: Int;

    init {
        this.top = null
        this.count = 0
    }
    // Returns the number of element in stack
    fun isSize(): Int
    {
        return this.count;
    }
    fun isEmpty(): Boolean
    {
        return this.isSize() <= 0
    }
    // Add a new element in stack
    fun push(value: Int): Unit
    {
        // Make a new stack node
        // And set as top
        this.top = StackNodeInt(value, this.top);
        // Increase node value
        this.count += 1;
    }
    // Remove a top element
    fun pop(): Int
    {
        val value: Int = this.peek();
        if (this.isEmpty() == false)
        {
            this.top = this.top?.next;
            // Reduce the size
            this.count -= 1;
        }
        return value;
    }
    // Used to get top element of stack
    fun peek(): Int
    {
        if (!this.isEmpty())
        {
            return this.top!!.operand;
        }
        else
        {
            return 0;
        }
    }
}