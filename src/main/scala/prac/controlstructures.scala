package prac

object controlstructures {
  def main(args: Array[String]): Unit = {
    //use of var makes it less functional
    var filename = "default.txt"
    if (!args.isEmpty)
      filename = args(0)

    // more functional style as now we use val
    val filenameFunc =
      if (!args.isEmpty) args(0)
      else "default.txt"

    val filesHere = (new java.io.File(".")).listFiles
    for (file <- filesHere)
      println(file)

    for(i <- 1 to 4)
      println(i)
  }
}
