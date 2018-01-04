package prac.functionsandclosures

object FirstClassFunction {
  var factor = 1
  def main(args: Array[String]): Unit = {
    var increment = (x : Int) => x + 1
    println(increment(10))

    increment = (x : Int) => x + 10
    println(increment(10))

    increment = (x : Int) => {
      println("Multiple line function")
      x + 100
    }
    println(increment(10))

    //passing function as argument to a function
    val printFunction = (x: Int) => println(x)
    val numbers = List(6,-12,44,7,1001,-22)
    numbers.foreach(printFunction)
    println(numbers.filter((x : Int) => x > 0))

    var more = 15
    increment = (x : Int) => x + more
    println(increment(100))

    more = 1
    println(increment(100))

    val n = null
    println(n)

    //val someName = prod NOT ALLOWED.Why??????
    increment = prod
    println(increment(1000))

    factor = factor+1
    println(increment(1000))
  }

  def prod(x : Int) = {
    x * factor;
  }
}
