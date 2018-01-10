package prac.classesandobjects.compositionNinheritance.traits

// mixin the trait using 'extends'
class TestClassA extends TestTraitA{

  override def testMethod(): Unit ={
    println("Overrides trait method")
  }

}
