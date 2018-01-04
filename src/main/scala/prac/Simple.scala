package prac

//standalone object
object Simple extends App{
  var c = 55;

  override  def main(args: Array[String]) = {
    println(3.+(2));
    println("Hello,Scala!".size)
    println(1.to(10))
    c = 7;
    println(c)
    var i = 5 + Simple.c
    var x = "foo";
    x = "Hi";
    println(x+i)
    println(i)
    println(i.toHexString)
    println(Simple.square(5))
    println("Area of the circle:"+Simple.circleArea(3))
    println("Area of the rectangle:"+Simple.rectAngleArea(2.3,7))

    Simple.callByValue(Simple.something())
    Simple.callByName(Simple.something())

    val fact = factorial(6);
    println("Factorial of 6 is:"+fact)

    def g() = {
      21
    }
    println(g)


    val z = 2;
    def f(y: Int) = {
      y + 1
    }

    val result = {
      // shadow the block defined outside by same name
      def f(y: Int) = {
        y + 2
      }
      val x = f(3)
      x * x
    }+z
    println(result)

    changeC();
    println(c);

    var im = 1
    im += 1
    println("After increment:"+im)

    //parametrize the object:
    //[] is for type parametrization
    //() is for value parametrization
    val strArray = new Array[String](4)
    strArray(0) = "Scala"//same as:strArray.update(0,"Scala ")
    strArray(1) = "is"////same as:strArray.update(1,"is ")
    strArray(2) = "scalable"////same as:strArray.update(2,"scalable\n")

    //if a method takes only one parameter, it can be called without a dot or parentheses
    //0.to(3)
    for(i<-0 to 3){
      print(strArray(i))
      print(" ")
      print(strArray apply i)
      print(" ")
    }
    println()

    // Array.apply("zero","one","two"),Array is a factory with method apply
    val numName = Array("zero","one","two")
    println(numName.length)

    //Scala provides immutable classes for functional programming paradigm
    val oneTwoThreee = List(1,2,"Third Element")//same as:List.apply(1,2,3)
    for(i<-0 to 2){
      println(oneTwoThreee.apply(i))
    }
    val fourFive = List(4,5)
    //prepending with :::
    val conc = oneTwoThreee ::: fourFive//same as:fourFive.:::(oneTwoThreee)
    println(oneTwoThreee)
    println(fourFive)
    println(conc)

    println(oneTwoThreee.:::(fourFive))

    var threeFourFive = 3::fourFive//method '::' is called on threeFourFive, not 3(that's a rule for methods with name ending in :)
    println(threeFourFive)
    threeFourFive = fourFive.::(3)
    println(threeFourFive)

    val emptyList = Nil
    println(emptyList.length)

    val thrill = "Will"::"Fill"::"Until"::Nil
    println(thrill.count(str=>str.length==4))

    val hex = 0x5
    println(hex)

    val little: Short = 9999

    //infix operator notation: object method/operator argument
    var x1 = 1 + 2 //same as 1.+(2)
    println(x1)
    var ind = "Hello" indexOf 'l' //same as "Hello".indexOf('l')
    println(ind)

    //prefix operator notation: method/operator object
    var x2 = -2 //same as: 2.unary_-
    println(x2)
    x2 = 4.unary_- //same as -4
    println(x2)

    //postfix operator notation: object method/operator
    val s = "HELLO" toLowerCase; //same as HELLO".toLowerCase()
    println(s)

    //equality
    println(List(1,2,3) == List(1,2,3))
    println(List(1,2,3) == List(2,1,3))
    println(null == null)

    val str1 = "Hi"
    val str2 = "Hi"
    println(str1.eq(str2))
    //str1 = "Hi"

    val any: Int = 12
    println(any)

    println(isEqual(11,12))
    println("ac" eq "ab")

    val n: Null = null
  }

  def isEqual(x: Int,y: Int) : Boolean = {
    x == y
  }

  def changeC() = {
    c = 101;
  }

  def factorial(n:Int):Int =  {
    if (n == 0){
      1
    }
    else{
      factorial(n-1) * n
    }
  }

  def square(x:Double) = {
    println("Inside double version")
    x*x
  }

  def square(x:Int):Int = {
    println("Inside int version")
    x*x
  }

  def circleArea(radius:Double):Double = {
    println("Inside circle area");
    val area = 3.14159 * radius * radius;
    return area;
  }

  def rectAngleArea(x:Double,y:Double):Double = {
    println("Inside rectangle area");
    val area = x*y;
    return area;
  }

  def something() = {
    println("calling something")
    1 // return value
  }

  def callByValue(x:Int) ={
    println("x1=" + x)
    println("x2=" + x)
  }

  def callByName(x: =>Int) ={
    println("x1=" + x)
    println("x2=" + x)
  }
}
