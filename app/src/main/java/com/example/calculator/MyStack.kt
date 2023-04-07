import java.util.*

class MyStack() {


    private val list:LinkedList<Any?> = LinkedList()


    fun push(element:Any?){
        list.add(element)
    }

    fun pop():Any?{

        return list.removeLast()



    }

    fun getFirst():Any?{
        return list.first
    }
    fun getLast():Any?{
        return list.last
    }

    fun getSize ():Int{
        return list.size
    }

    fun isEmpty():Boolean{
        return list.size == 0
    }

}