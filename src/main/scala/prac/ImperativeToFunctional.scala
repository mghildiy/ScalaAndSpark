package prac

object ImperativeToFunctional {
  def main(args: Array[String]): Unit = {
    // non-functional style
    def printArgsImperative(args:Array[String]):Unit = {
      var i = 0;
      while(i<args.length){
        println(args(i))
        i += 1
      }
    }

    //more functional style as no var used, but still some side effects as method writes to output stream(no return type)
    def printArgsNoVars(args:Array[String]):Unit = {
      for(arg <- args){
        println(arg)
      }
    }

    //pure functional style, as method has no side effects
    def noSideEffects(args:Array[String]): String ={
      args.mkString(",")
    }
  }
}
