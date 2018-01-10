package prac.classesandobjects.compositionNinheritance.traits

object Demo {

  def main(args: Array[String]): Unit = {
    val tc = new TestClassA
    tc.testMethod()
    val tt: TestTraitA = tc
    tt.testMethod()
  }

}
