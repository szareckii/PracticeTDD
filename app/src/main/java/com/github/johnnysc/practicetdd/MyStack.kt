package com.github.johnnysc.practicetdd

interface MyStack<T> {

    fun pop(): T
    fun push(item: T)

    abstract class Base<T>(maxCount: Int) : MyStack<T> {

        val myArray: Array<Any?>
        var position: Int = 0

        init {
            if (maxCount < 1)
                throw java.lang.IllegalStateException("Size of array must have more than 1")
            myArray = Array(maxCount) { null }
        }
    }

    class LIFO<T>(private val maxCount: Int) : Base<T>(maxCount) {

        override fun pop(): T {
            if (position == 0)
                throw java.lang.IllegalStateException("You should push before pop")
            else {
                return myArray[--position] as T
            }
        }

        override fun push(item: T) {
            if (position == maxCount)
                throw java.lang.IllegalStateException("Stack overflow exception, maximum is $maxCount")
            myArray[position++] = item
        }
    }

    class FIFO<T>(private val maxCount: Int) : Base<T>(maxCount) {

        override fun pop(): T {
            if (position == 0)
                throw java.lang.IllegalStateException("You should push before pop")
            else {
                position--
                return myArray[position] as T
            }
        }

        override fun push(item: T) {
            if (position == maxCount)
                throw java.lang.IllegalStateException("Stack overflow exception, maximum is $maxCount")
                for (i in position downTo 1) {
                    myArray[i] = myArray[i - 1]
                }
            myArray[0] = item
            position++
        }
    }
}
