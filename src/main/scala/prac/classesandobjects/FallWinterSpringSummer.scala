package prac.classesandobjects

import ChecksumAccumulator.calculate

// main method inherited from trait App, thus saving typing it.But command line args are not accessible
object FallWinterSpringSummer extends App{
  for(season <- List("fall", "winter", "spring")){
    println(season+":"+calculate(season))
  }
}
