package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    return when (arg) {
        "Hello" -> "world";
        is String -> "Say what?"
        0 -> "zero"
        1 -> "one"
        in 2..10 -> "low number"
        is kotlin.Int -> "a number"
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int {
    return lhs + rhs
}


// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int {
    return lhs - rhs
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int): Int {
    return op(lhs, rhs)
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, var age: Int) {
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"

    override fun equals(other: Any?): Boolean {
        if(this.hashCode() == other.hashCode()) {
            return true
        } else {
            return false
        }
    }

    override fun hashCode(): Int {
        return this.firstName.hashCode() + this.lastName.hashCode() + this.age
    }
}

// write a class "Money"
class Money(val amount: Int, val currency: String) {
    init {
        if (this.amount < 0) {
          throw IllegalArgumentException("Amount must be greater than zero")
        } else if (currency != "USD" && currency != "GBP" && currency != "CAN" && currency != "EUR") {
          throw IllegalArgumentException("Currency must be USD, GBP, or CAN")
        }
    }
    fun convert(newCurr: String): Money {
        val curr = this.currency
        val amount = this.amount
        var result = amount
        if (curr == "USD") {
            if(newCurr == "GBP") {
                result = amount/2
            } 
            if(newCurr == "EUR") {
                result = amount*3/2
            } 
            if(newCurr == "CAN")
                result = amount*5/4
        } 
        if (curr == "EUR") {
            if(newCurr == "USD") {
                result = amount*2/3
            } 
            if(newCurr == "CAN") {
                result = amount*6/5
            } 
            if(newCurr == "GBP") {
                result = amount/3
            }
        } 
        if (curr == "GBP") {
            if(newCurr == "USD") {
                result = amount*2
            } 
            if(newCurr == "EUR") {
                result = amount*3
            } 
            if(newCurr == "CAN") {
                result = amount*5/2
            }
        } 
        if (curr == "CAN") {
            if(newCurr == "USD") {
                result = amount*4/5
            } 
            if(newCurr == "GBP") {
                result = amount*2/5
            } 
            if(newCurr == "EUR") {
                result = amount*6/5
            }
        }
        return Money(result, newCurr)
    }

    operator fun plus(other: Money): Money {
        val result: Int = this.amount + other.convert(this.currency).amount
        return Money(result, this.currency)
    }
    override fun toString(): String {
        return "Money(amount=$amount, currency=$currency)"
    }
}

fun main() {
    println(whenFn("Hello"))
    println(add(5,6))
    println(sub(8,7))
    println(mathOp(2, 4, ::add))
    val person = Person("Ted", "Neward", 45)
    val person2 = Person("Ted", "Neward", 45)
    println(person.debugString)
    println(person.hashCode())
    println(person == person2)
    val tenUSD = Money(10, "USD")
    //val twelveUSD = Money(12, "USD")
    //val fiveGBP = Money(5, "GBP")
    //val fifteenEUR = Money(15, "EUR")
    //val fifteenCAN = Money(15, "CAN")
    println(tenUSD.convert("CAN"))
}
