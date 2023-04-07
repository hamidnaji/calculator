import java.util.*


//conversion infix expression to postfix
class Conversion(var infix: List<Any?>) {


    fun infixToPostfix(): List<Any?> {

        val list = infix
        val stack = MyStack()
        val postfixList = LinkedList<Any?>()


        for (element in list) {

            if (element is Double)
                postfixList.add(element)


            if (element is String) {

                if (isOoparator(element)) {
                    if (stack.isEmpty())
                        stack.push(element)

                    else if (stack.getLast() as String == "(")
                        stack.push(element)

                    else if (validatePrecedence(element , stack.getLast() as String) > 0)
                        stack.push(element)

                    else {
                        //Pop all the operators from the stack which are greater than or equal to
                        // in precedence than that of the scanned operator.

                        var last = stack.getLast() as String
                        while (last != "(" && !stack.isEmpty() && validatePrecedence(element , last) <= 0){

                            postfixList.add(stack.pop())

                            if (!stack.isEmpty())
                            last = stack.getLast() as String

                        }
                        stack.push(element)


                    }
                }

                if (element == "(")
                    stack.push(element)

                // pop the stack and output it until a ‘(‘ is encountered
                if (element == ")") {

                    var pop = stack.pop() as String
                    while (pop != "(") {
                        postfixList.add(pop)

                        pop = stack.pop() as String
                    }

                }
            }//string if


        }

        while (!stack.isEmpty())
        {
            postfixList.add(stack.pop())
        }


        return postfixList
    }


    fun validatePrecedence(operator1: String, operator2: String): Int {
        val map = linkedMapOf<String, Int>()
        map.put("+", 1)
        map.put("-", 1)
        map.put("*", 2)
        map.put("/", 2)

        return map.getValue(operator1) - map.getValue(operator2)
    }


    fun isOoparator(e: String): Boolean {

        return (e == "+" || e == "*" || e == "/" || e == "-")

    }

    fun isNumber(char: Char): Boolean {

        return char in '0'..'9'
    }

}