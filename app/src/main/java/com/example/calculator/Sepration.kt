import java.util.*

//split expression to list of strings and double types
//to use in infix method
class Sepration {


    fun getSepratedList(exp: String): List<Any?> {

        val list = LinkedList<Any?>()
        var str = ""
        var i = 0

        while (i < exp.length) {

            val element = exp[i]

            if (element == '(' || exp[i] == ')')
                list.add((element.toString()))

            else if (isOoparator(element))
                list.add(element.toString())

            else if (isNumber(element)) {

                while (i < exp.length && !isOoparator(exp[i]) && exp[i] != ')' && exp[i] != '(') {

                    str += exp[i]
                    i++
                }
                list.add(str.trim().toDouble())

                continue
            }

            str = ""
            i++

        }


        return list

    }


    fun isOoparator(char: Char): Boolean {

        return (char == '+' || char == '*' || char == '/' || char == '-')

    }

    fun isNumber(char: Char): Boolean {

        return char in '0'..'9'
    }


}