package prac.rational

import prac.rational.Rational

object RationalObject {
  def main(args: Array[String]): Unit = {
    val rn1 = new Rational(1,2)
    val rn2 = new Rational(2,3)
    println(rn1 add rn2)

    val rn3 = new Rational(1,3)
    println(rn1 lessThan rn3)
    println(rn1 max rn3)

    val rn4 = new Rational(22,84)
    println(rn4)

    println(rn4 + rn1)
  }
}
