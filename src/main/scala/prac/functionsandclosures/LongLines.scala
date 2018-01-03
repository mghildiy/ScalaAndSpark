package prac.functionsandclosures

import scala.io.Source

//example of local functions
object LongLines {

  def processFile(filename: String, width: Int) {
    // this local function can access parameters of enclosing function
    def processLine(line: String) {
      if (line.length > width)
        println(filename +": "+ line)
    }
    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }

}
