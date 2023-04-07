
//conversion postfix expression to number

class Evaluation(var postfix: List<Any?>) {


    fun postfixToNumber(): Double {
        val stack = MyStack()
        val list = postfix

        for (element in list) {

            if (element is Double)
                stack.push(element)

            if (element is String) {

                val e1 = stack.pop() as Double
                val e2 = stack.pop() as Double

                val result = operate(e2, element, e1)

                stack.push(result)

            }

        }


        return stack.pop() as Double
    }


    fun isOperator(e: String): Boolean {

        return (e == "+" || e == "*" || e == "/" || e == "-")

    }


    fun operate(e1: Double, operator: String, e2: Double): Double {

        return when (operator) {
            "+" -> e1 + e2
            "-" -> e1 - e2
            "*" -> e1 * e2
            "/" -> e1 / e2
            else -> 0.0
        }
    }

}