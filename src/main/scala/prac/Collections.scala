package prac

import scala.collection.mutable.Set;
import scala.collection.mutable.Map;

object Collections {
  def main(args: Array[String]): Unit = {
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

    /*************************
    List
    *************************/

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

    /*************************
    Set
     *************************/
    //immutable Set
    var dlSet = Set("Tensorflow","Keras")
    var dlSet1 = dlSet
    dlSet += "MXNet"
    println(dlSet)
    println(dlSet1)
    println(dlSet.contains("DL4J"))

    //mutable set
    val playerSet = scala.collection.mutable.Set.apply("Messi", "Ronaldo")
    playerSet += "Hazard"
    println(playerSet)
    val playerSet1 = scala.collection.mutable.Set.apply("Messi", "Ronaldo","Hazard")
    println(playerSet == playerSet1)

    /*************************
    Map
    *************************/
    val treasureMap = Map[Int, String]()
    treasureMap += (1 -> "Go to island.")
    treasureMap += (2 -> "Find big X on ground.")
    treasureMap += (3 -> "Dig.")
    println(treasureMap(2))

    val romanNumeral = Map(1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV", 5 -> "V")
    println(romanNumeral(4))
  }
}
