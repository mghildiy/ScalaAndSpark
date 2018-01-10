package prac.classesandobjects.compositionNinheritance.traits

//mixin the trait using 'with'
class TestClassB extends TestClass with TestTraitA with TestTraitB with Ordered[TestClassB] {
  override def compare(that: TestClassB) = {
    1
  }
}
