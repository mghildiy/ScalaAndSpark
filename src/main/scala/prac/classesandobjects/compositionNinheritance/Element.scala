package prac.classesandobjects.compositionNinheritance

import Element.elem

abstract class Element {

  def contents: Array[String]

  def height: Int = contents.length

  def width: Int = if(height == 0) 0 else contents(0).length

  def above(other: Element): Element = {
    elem(this.contents ++ other.contents)
  }

  def beside(that: Element): Element = {
    val contents = new Array[String](this.contents.length)
    elem(for ((line1, line2) <- this.contents zip that.contents
    ) yield line1 + line2)
  }

  override def toString = this.contents mkString "\n"

}

object Element{

  def elem(contents: Array[String]): Element = new ArrayElement(contents)

  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)

  def elem(line: String): Element =
    new LineElement(line)

}