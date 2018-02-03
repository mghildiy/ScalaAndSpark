package prac.implicits

object ImplicitClassDemo {

  implicit class StringImprovements(val s: String) {
    def increment = s.map(c => (c + 1).toChar)
  }

  def main(args: Array[String]): Unit = {
    val x = "Einsteim"
    println("Einstein".increment)
  }
}
